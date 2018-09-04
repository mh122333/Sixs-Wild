package game.view;

import game.entity.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;

/**
 * Result view when the game is finished
 * @author Weijia Tao
 *
 */
public class ResultView extends JPanel{
	/** Game related to this view */
	protected Game game;

	protected JLabel scoreLabel;
	
	protected JLabel starLabel;
	
	protected JButton levelButton;
	
	protected JButton retryButton;
	
	protected JButton continueButton;
	
	protected Image passImg;
	
	protected Image failImg;
	
	protected Image resultImg;
	
	protected Image star1Img;
	
	protected Image star2Img;
	
	protected Image star3Img;
	
	protected Image starImg;
	
	/**
	 * Initialize all the buttons and labels
	 * @param g
	 * Game related to this view 
	 */
	public ResultView(Game g) {
		this.game = g;
		setPreferredSize(new Dimension(1280, 720));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
		setDoubleBuffered(true);
		
		try{
			passImg = ImageIO.read(new File("images"+File.separator+"pass.png"));
			failImg = ImageIO.read(new File("images"+File.separator+"fail.png"));
			star1Img = ImageIO.read(new File("images"+File.separator+"star1.png"));
			star2Img = ImageIO.read(new File("images"+File.separator+"star2.png"));
			star3Img = ImageIO.read(new File("images"+File.separator+"star3.png"));
		} catch (IOException e){
			System.out.println("No image found");
		}
		
		scoreLabel = new JLabel("Score");
		scoreLabel.setForeground(new Color(0, 128, 0));
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Arial", Font.PLAIN, 34));
		scoreLabel.setBounds(540, 466, 200, 50);
		add(scoreLabel);
		
		starLabel = new JLabel("Star");
		starLabel.setFont(new Font("Arial", Font.PLAIN, 28));
		starLabel.setHorizontalAlignment(SwingConstants.CENTER);
		starLabel.setBounds(540, 219, 200, 50);
		// add(starLabel);
		
		levelButton = new JButton("Levels");
		levelButton.setBounds(170, 550, 200, 50);
		add(levelButton);
		
		retryButton = new JButton("Retry");
		retryButton.setBounds(540, 550, 200, 50);
		add(retryButton);
		
		continueButton = new JButton("Continue");
		continueButton.setBounds(910, 550, 200, 50);
		add(continueButton);
	}
	
	/**
	 * Update the view based on the game status. 
	 */
	public void update(){
		scoreLabel.setText("Score: " + game.getCurrentLevel().getBoard().getCurrentScore());		
		// starLabel.setText("Star: " + game.getCurrentLevel().getStar());
		int star = game.getCurrentLevel().getStar();
		switch(star){
		case 1:
			starImg = star1Img;
			break;
		case 2:
			starImg = star2Img;
			break;
		case 3:
			starImg = star3Img;
			break;
		default:
			starImg = null;
			break;
		}
		if (!game.hasNextLevel()){
			continueButton.setEnabled(false);
		}
		
		if (game.getCurrentLevel().isLevelPassed()){
			resultImg = passImg;
			continueButton.setEnabled(true);
		}
		else{
			resultImg = failImg;
			continueButton.setEnabled(false);
		}
	}
	
	public JButton getRetryButton(){
		return retryButton;
	}
	
	public JButton getLevelButton(){
		return levelButton;
	}
	
	public JButton getContinueButton(){
		return continueButton;
	}
	
	public JLabel getScoreLabel(){
		return scoreLabel;
	}
	
	public JLabel getStarLabel(){
		return starLabel;
	}
	
	/**
	 * Override to include the image
	 */
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(resultImg, 490, 50, 300, 250, this);
		g.drawImage(starImg, 440, 300, 400, 150, this);
	}
}
