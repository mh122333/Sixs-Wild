package game.view;

import game.controller.ShowPlayViewController;
import game.entity.EliminationLevel;
import game.entity.Game;
import game.entity.Level;
import game.entity.LightningLevel;
import game.entity.PuzzleLevel;
import game.entity.ReleaseLevel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Font;

import levelbuilder.model.Model;

/**
 * Level lists view for level selections.
 * @author Weijia Tao
 *
 */
public class LevelListView extends JPanel{
	/** Game related to this view */
	protected Game game;
	
	/** Six buttons for level selection */
	protected JButton[] levelButton = new JButton[6];
	
	/** The current initial level */
	protected int initLevel;
	
	/** The total number of levels  */
	protected int numLevel;
	
	/** Image for the puzzle level type */
	protected ImageIcon puzzleIcon;
	
	/** Image for the lightning level type */
	protected ImageIcon lightningIcon;
	
	/** Image for the release level type */
	protected ImageIcon releaseIcon;
	
	/** Image for the elimination level type */
	protected ImageIcon eliminationIcon;
	
	/** Image for the NA level */
	protected ImageIcon naIcon;
	
	/**
	 * Load all the images and create all the buttons
	 * @param g
	 * Game related to this view
	 */
	public LevelListView(Game g) {
		this.game = g;
		this.numLevel = game.getNumLevel();
				
		setPreferredSize(new Dimension(1000, 500));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
		
		try{
			puzzleIcon = new ImageIcon(ImageIO.read(new File("images"+File.separator+"puzzle-type.png")));
			lightningIcon = new ImageIcon(ImageIO.read(new File("images"+File.separator+"lightning-type.png")));
			eliminationIcon = new ImageIcon(ImageIO.read(new File("images"+File.separator+"elimination-type.png")));
			releaseIcon = new ImageIcon(ImageIO.read(new File("images"+File.separator+"release-type.png")));
			naIcon = new ImageIcon(ImageIO.read(new File("images"+File.separator+"na-type.png")));
		} catch (IOException e){
			System.out.println("No image found");
		}
		
		initLevel = 1;
		
		levelButton[0] = new JButton();
		levelButton[0].setBounds(100, 52, 200, 200);
		add(levelButton[0]);
		
		levelButton[1] = new JButton();
		levelButton[1].setBounds(400, 52, 200, 200);
		add(levelButton[1]);
		
		levelButton[2] = new JButton();
		levelButton[2].setBounds(700, 52, 200, 200);
		add(levelButton[2]);
		
		levelButton[3] = new JButton();
		levelButton[3].setBounds(100, 258, 200, 200);
		add(levelButton[3]);
		
		levelButton[4] = new JButton();
		levelButton[4].setBounds(400, 258, 200, 200);
		add(levelButton[4]);
		
		levelButton[5] = new JButton();
		levelButton[5].setBounds(700, 262, 200, 200);
		add(levelButton[5]);
		
		for (int i = 0; i < 6; i ++){
			levelButton[i].setFont(new Font("Arial", Font.BOLD, 24));
			levelButton[i].setContentAreaFilled(false);
			levelButton[i].setFocusPainted(false);
			levelButton[i].setHorizontalTextPosition(SwingConstants.CENTER);
			levelButton[i].setVerticalTextPosition(SwingConstants.BOTTOM);
		}
		
		update();
	}
		
	public JButton getLevel1Button() {
		return this.levelButton[0];
	}
	
	public JButton getLevel2Button() {
		return this.levelButton[1];
	}
	
	public JButton getLevel3Button() {
		return this.levelButton[2];
	}
	
	public JButton getLevel4Button() {
		return this.levelButton[3];
	}
	
	public JButton getLevel5Button() {
		return this.levelButton[4];
	}
	
	public JButton getLevel6Button() {
		return this.levelButton[5];
	}
	
	public int getInitLevel(){
		return this.initLevel;
	}
	
	/**
	 * Update the buttons based on the game
	 */
	public void update(){
		for (int i = 0; i < 6; i ++){
			levelButton[i].setText("Level" + (initLevel + i));
		}
		
		for (int i = 0; i < 6; i ++){
			// Change Button Pic according to the level type
			if (game.getLevelPlayable(initLevel+i)){
				levelButton[i].setEnabled(true);
				int type = game.getLevel(initLevel+i).getType();
				switch(type){
				case Model.Puzzle:
					levelButton[i].setIcon(puzzleIcon);
					break;
				case Model.Lightning:
					levelButton[i].setIcon(lightningIcon);
					break;
				case Model.Elimination:
					levelButton[i].setIcon(eliminationIcon);
					break;
				case Model.Release:
					levelButton[i].setIcon(releaseIcon);
					break;
				default:
					break;
				}
			}
			else{
				levelButton[i].setEnabled(false);
				levelButton[i].setIcon(naIcon);
			}
		}
		
	}
	
	/**
	 * Determine if it is the last page. 
	 * @return
	 * True if last page, False if not.
	 */
	public boolean isLastPage(){
		return initLevel + 6 > numLevel;
	}
	
	/**
	 * Determine if it is the first page. 
	 * @return
	 * True if first page, False if not.
	 */
	public boolean isFirstPage(){
		return initLevel == 1;
	}
	
	/**
	 * Increment the initial level
	 */
	public void incrementInitLevel(){	
		if ((initLevel + 6) <= numLevel){
			initLevel += 6;
		}
	}
	
	/**
	 * Decrement the initial level
	 */
	public void decrementInitLevel(){
		if (initLevel <=6){
			initLevel = 1;
		}
		else{
			initLevel -= 6;
		}
	}
}
