package game.view;

import game.entity.Game;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Font;

/**
 * Level select view which contains the level list and buttons
 * @author Weijia Tao
 *
 */
public class LevelSelectView extends JPanel{
	/** Game related to this view */
	protected Game game;
	
	/** Level label */
	protected JLabel levelsLabel;
	
	/** Back Button */
	protected JButton backButton;
	
	/** Previous Button */
	protected JButton prevButton;
	
	/** Next Button */
	protected JButton nextButton;
	
	/** Level list view */
	protected LevelListView levels;  // by default window builder declared this as JPanel even though it is LevelListView, either way should work though.
	
	/**
	 * Create the view 
	 * @param g
	 * Game related to this view
	 */
	public LevelSelectView(Game g) {
		this.game = g;
		setPreferredSize(new Dimension(1280, 720));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
		
		levelsLabel = new JLabel("Levels");
		levelsLabel.setFont(new Font("Arial", Font.BOLD, 16));
		levelsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		levelsLabel.setBounds(0, 0, 200, 50);
		add(levelsLabel);
		
		backButton = new JButton("Back");
		backButton.setBounds(1080, 0, 200, 50);
		add(backButton);
		
		prevButton = new JButton("Prev");
		prevButton.setBounds(0, 670, 200, 50);
		add(prevButton);
		
		nextButton = new JButton("Next");
		nextButton.setBounds(1080, 670, 200, 50);
		add(nextButton);
		
		levels = new LevelListView(game);
		levels.setBounds(140, 110, 1000, 500);
		add(levels);
	}
	
	public JButton getBackButton() {
		return this.backButton;
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
		updatePreNextButton();
		this.levels.update();
	}
	
	/**
	 * Disable or enable the previous and next button based on the page of the list.
	 */
	private void updatePreNextButton(){
		prevButton.setEnabled(true);
		nextButton.setEnabled(true);
		
		if (levels.isFirstPage()){
			prevButton.setEnabled(false);
		}
		
		if (levels.isLastPage()){
			nextButton.setEnabled(false);
		}
		

	}
	
	public LevelListView getLevelListView(){
		return this.levels;
	}
	
}
