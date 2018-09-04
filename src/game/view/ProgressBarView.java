package game.view;

import game.entity.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Progress bar for the game play
 * @author Weijia Tao
 *
 */
public class ProgressBarView extends JPanel{
	/** Game related to this view */
	protected Game game;
	
	/** Foreground image of the progress bar */
	protected Image foreImg;
	
	/** Background image of the progress bar */
	protected Image backImg;
	
	/** Off screen graphics for the progress bar */
	protected Graphics offscreenGraphics;
	
	/** Off screen image for the progress bar */
	protected Image offscreenImage;
	
	/** Actual graphics for the progress bar */
	protected Graphics actualGraphics;
	
	// Size of the progress bar view
	protected final int width = 700;
	
	protected final int height = 29;
	
	/**
	 * Initialize the progress bar 
	 * @param g
	 * Game related to this view 
	 */
	public ProgressBarView(Game g){
		this.game = g;
		
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
		
		
		try{
			foreImg = ImageIO.read(new File("images"+File.separator+"progressbar_fore.png"));
			backImg = ImageIO.read(new File("images"+File.separator+"progressbar_back.png"));
		} catch (IOException e){
			System.out.println("No image found");
		}
		
	}
	
	/** Make sure that image is created as needed. */
	void ensureImageAvailable(Graphics g) {
		if (offscreenImage == null) {
			offscreenImage = this.createImage(this.getWidth(), this.getHeight());
			offscreenGraphics = offscreenImage.getGraphics();
			actualGraphics = g;
			
			redraw();
		}
	}
	
	/**
	 * Redraw the progress bar based on changes. 
	 */
	public void redraw() {
		// nothing to draw into? Must stop here.
		if (offscreenImage == null) return;
		
		// clear the image.
		offscreenGraphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		// Make sure offscreenImage is white
		offscreenGraphics.setColor(Color.WHITE);
		offscreenGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
		// Draw 
		offscreenGraphics.drawImage(backImg, 0, 0, getCurrentWidth(), getHeight(), 0, 0, getCurrentWidth(), getHeight(), this);
		offscreenGraphics.drawImage(foreImg, 0, 0, getWidth(), getHeight(), this);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	/**
	 * Calculate the current width of the progress bar 
	 * based on the current score of the game
	 * @return
	 * Current width of the progress bar 
	 */
	public int getCurrentWidth(){
		int w = game.getCurrentLevel().getBoard().getCurrentScore()*getWidth()/(2*game.getCurrentLevel().getGoalScore());
		if (w > getWidth()){
			w = getWidth();
		}
		return w;
	}
	
	/**
	 * Update the progress bar
	 */
	public void update(){
		redraw();
		repaint();
	}
	
	/**
	 * Override to draw the progress bar 
	 */
	public void paint(Graphics g){
		ensureImageAvailable(g);
		g.drawImage(offscreenImage, 0, 0, getWidth(), getHeight(), this);
	}
}
