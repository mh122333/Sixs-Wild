package game.view;

import game.entity.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Accomplishments list view which contains the table and buttons
 * @author Weijia Tao
 *
 */
public class AccListView extends JPanel{
	/** Game related to this view */
	protected Game game;
	
	/** Accomplishment table */
	protected AccLabelView accLabelView;
	
	/** Back Button */
	protected JButton backButton;
	
	/** Previous Button */
	protected JButton prevButton;
	
	/** Next Button */
	protected JButton nextButton;
	
	/** Background image */
	protected Image background;

	/**
	 * Create the view 
	 * @param g
	 * Game related to this view
	 */
    public AccListView(Game g) {
    	this.game = g;
    	
		setPreferredSize(new Dimension(1280, 720));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize()); 
		setBackground(Color.WHITE);
		setLayout(null);
		
		try{
			background = ImageIO.read(new File("images"+File.separator+"acc-background.png"));
		} catch (IOException e){
			System.out.println("No image found");
		}
		               
		JLabel lblAccomplishments = new JLabel("Accomplishments");
		lblAccomplishments.setFont(new Font("Arial", Font.BOLD, 16));
		lblAccomplishments.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccomplishments.setBounds(0, 0, 200, 50);
		add(lblAccomplishments);
		               
		backButton = new JButton("Back");
		backButton.setBounds(1080, 0, 200, 50);
		add(backButton);
		
		prevButton = new JButton("Prev");
		prevButton.setBounds(0, 670, 200, 50);
		add(prevButton);
		
		nextButton = new JButton("Next");
		nextButton.setBounds(1080, 670, 200, 50);
		add(nextButton);
		               
		accLabelView = new AccLabelView(game);
		accLabelView.setBounds(140, 110, 1000, 500);
		add(accLabelView);
		
		update();
	}
    
    public JButton getBackButton(){
    	return backButton;
    }
    
	public JButton getPrevButton() {
		return this.prevButton;
	}
	
	public JButton getNextButton() {
		return this.nextButton;
	}
	
	/**
	 * Update the entire view
	 */
	public void update(){
		this.accLabelView.update();
		updatePreNextButton();
	}
	
	/**
	 * Disable or enable the previous and next button based on the page of the table.
	 */
	private void updatePreNextButton(){
		prevButton.setEnabled(true);
		nextButton.setEnabled(true);
		
		if (accLabelView.isFirstPage()){
			prevButton.setEnabled(false);
		}
		
		if (accLabelView.isLastPage()){
			nextButton.setEnabled(false);
		}

	}
     
    public AccLabelView getAccLabelView(){
    	return accLabelView;
    }
    
    /**
     * Override to add the background picture.
     */
    @Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}
}