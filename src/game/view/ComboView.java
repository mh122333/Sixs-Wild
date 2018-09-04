package game.view;

import game.entity.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Combo view, an animation for stars changed. 
 * @author Weijia Tao
 *
 */
public class ComboView extends JPanel implements ActionListener{
	/** Game related to this view */
	protected Game game;
	
	/** One star combo image */
	protected Image oneStarImg;
	
	/** Two star combo image */
	protected Image twoStarImg;
	
	/** Three star combo image */
	protected Image threeStarImg;
	
	/** Buffered image */
	protected BufferedImage bi;
	
	/** Timer for the animation */
	protected Timer timer;
	 
	/** Graphics for the buffered image*/
	protected Graphics bufferedGraphics;
	
	/** Resclae operation for the image, opacity is used */
	protected RescaleOp rop;
	
	/** Current opacity */
	protected int opacity;
	
	/** Current timer count */
	protected int timerCnt;
	
	/** Keep track of last star to detect changes */
	protected int lastStar;
	
	/** Maximum timer count to limit animation time */
	protected final int timerCntMax = 100;
	
	/** Scale factors for the image */
	protected float[] scales = {1f,1f,1f,1f};
	
	/** Offsets for the image */
	protected float[] offsets = {0f,0f,0f,0f};
	
	/** Timer fire interval */
	protected final int DELAY = 30;

	protected final int width = 300;
	
	protected final int hight = 400;
	
	/**
	 * Read in all the images and set the buffered graphics and image.
	 * Also create the timer for future use. 
	 * @param game
	 */
	public ComboView(Game game){
		this.game = game;
		this.opacity = 100;
		this.timerCnt = 0;
		this.lastStar = 0;
		
		try{
			oneStarImg = ImageIO.read(new File("images"+File.separator+"combo1.png"));
			twoStarImg = ImageIO.read(new File("images"+File.separator+"combo2.png"));
			threeStarImg = ImageIO.read(new File("images"+File.separator+"combo3.png"));
		} catch (IOException e){
			System.out.println("No image found");
		}
		
		bi = new BufferedImage(width, hight, BufferedImage.TYPE_INT_ARGB);		
		bufferedGraphics = bi.getGraphics();
		
		setPreferredSize(new Dimension(width, hight));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		setLayout(null);	
		
		timer = new Timer(DELAY, this);
	}
	
	/**
	 * Update the opacity using scale factors. 
	 * @param opacity
	 * Opacity from 0 - 100. 
	 */
	private void setOpacity(int opacity){
        scales[3] = ((float)opacity)/100;
        rop = new RescaleOp(scales, offsets, null);
	}
    
	/**
	 * Override to draw the image
	 */
	@Override
    public void paint(Graphics g) {    	
    	super.paint(g);
    	
		Graphics2D g2d = (Graphics2D) g;
    	
    	g2d.drawImage(bi, rop, calcXpos(), 0);
        Toolkit.getDefaultToolkit().sync();
    }
	
	/**
	 * Allow other classes to stop the timer
	 */
	public void stopTimer(){
		timer.stop();
		timerCnt = 0;
	}
    
	/**
	 * Action for the timer, update the timer count and opacity. 
	 */
    @Override
    public void actionPerformed(ActionEvent e) {
    	this.timerCnt ++;
    	
    	if(timerCnt >= timerCntMax){
    		timer.stop();
    		timerCnt = 0;
    	}
    	
    	setOpacity(calcOpacity());
        repaint();
    }
    
    /**
     * Calculate the horizontal position of the image based on time. 
     * @return
     * The horizontal position of the image.
     */
    private int calcXpos(){
    	int x = 0;
    	if (0 <= timerCnt && timerCnt <= timerCntMax/2){
        	x = timerCnt*width/(timerCntMax/2)-300;
    	}

    	return x;
    }
    
    /**
     * Calculate the opacity of the image based on time. 
     * @return
     * The opacity of the image.
     */
    private int calcOpacity(){
    	int opacity = 0;
    	if (0 <= timerCnt && timerCnt <= timerCntMax/2){
    		opacity = timerCnt*100/(timerCntMax/2);
    	}
    	else{
    		opacity = 100-(timerCnt-timerCntMax/2)*100/(timerCntMax/2);
    	}
    	return opacity;
    }
    
    /**
     * Update the combo view based on the game status.
     */
    public void update(){
    	int currentStar = game.getCurrentLevel().getStar();
    	if (currentStar == 1 && lastStar < currentStar){
    		bufferedGraphics.drawImage(oneStarImg, 0, 0, this);
            timer.start();
    	}
    	else if (currentStar == 2 && lastStar < currentStar){
    		bufferedGraphics.drawImage(twoStarImg, 0, 0, this);
            timer.start();
    	}
    	else if (currentStar == 3 && lastStar < currentStar){
    		bufferedGraphics.drawImage(threeStarImg, 0, 0, this);
            timer.start();
    	}
    	
    	lastStar = currentStar;
    }
}
