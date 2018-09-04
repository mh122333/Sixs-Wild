package game.view;

import game.entity.Acc;
import game.entity.Game;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * Accomplishment table view using JLabels
 * @author Weijia Tao
 *
 */
public class AccLabelView extends JPanel{
	/** Game related to this view */
	protected Game game;
	
	/** Existing accomplishment list */
	protected ArrayList<Acc> accList;
	
	/** The current initial level for accomplishment  */
	protected int initAcc;
	
	/** The total number of accomplishments  */
	protected int numAcc;
	
	/** JLabel for the table */
	protected JLabel[][] data = new JLabel[4][6]; // 4 columns 6 rows
	
	/**
	 * Create the view and initialize all the labels
	 * @param g
	 * Game related to this view
	 */
	public AccLabelView(Game g){
    	this.game = g;
    	this.initAcc = 1;
    	
		setPreferredSize(new Dimension(1000, 500));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setOpaque(false);
		setLayout(null);
		
		JLabel levelTitle = new JLabel("Level");
		levelTitle.setFont(new Font("Arial", Font.BOLD, 16));
		levelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		levelTitle.setBounds(100, 75, 200, 50);
		add(levelTitle);
		
		JLabel scoreTitle = new JLabel("Score");
		scoreTitle.setFont(new Font("Arial", Font.BOLD, 16));
		scoreTitle.setHorizontalAlignment(SwingConstants.CENTER);
		scoreTitle.setBounds(300, 75, 200, 50);
		add(scoreTitle);
		
		JLabel staTitle = new JLabel("Star");
		staTitle.setFont(new Font("Arial", Font.BOLD, 16));
		staTitle.setHorizontalAlignment(SwingConstants.CENTER);
		staTitle.setBounds(500, 75, 200, 50);
		add(staTitle);
		
		JLabel dateTitle = new JLabel("Date");
		dateTitle.setFont(new Font("Arial", Font.BOLD, 16));
		dateTitle.setHorizontalAlignment(SwingConstants.CENTER);
		dateTitle.setBounds(700, 75, 200, 50);
		add(dateTitle);
		
		for (int col = 0; col < 4; col ++){
			for (int row = 0; row < 6; row ++){
				data[col][row] = new JLabel();
				data[col][row].setFont(new Font("Arial", Font.PLAIN, 16));
				data[col][row].setHorizontalAlignment(SwingConstants.CENTER);
				data[col][row].setBounds(100+col*200, 125+row*50, 200, 50);
				add(data[col][row]);
			}
		}
		
		update();
	}
	
	public int getInitAcc(){
		return this.initAcc;
	}
	
	/**
	 * Update the table based on the game. 
	 */
	public void update(){
		// Update the acc list
    	this.accList = game.getAccList();
      	this.numAcc = accList.size();
		// Level
		for (int row = 0; row < 6; row ++){
			if (initAcc+row <= numAcc){
				data[0][row].setText("" + accList.get(initAcc+row-1).getLevel());
			}
			else{
				data[0][row].setText("");
			}
		}
		// Score
		for (int row = 0; row < 6; row ++){
			if (initAcc+row <= numAcc){
				data[1][row].setText("" + accList.get(initAcc+row-1).getScore());
			}
			else{
				data[1][row].setText("");
			}
		}
		// Star
		for (int row = 0; row < 6; row ++){
			if (initAcc+row <= numAcc){
				data[2][row].setText("" + accList.get(initAcc+row-1).getStars());
			}
			else{
				data[2][row].setText("");
			}
		}
		// Date
		for (int row = 0; row < 6; row ++){
			if (initAcc+row <= numAcc){
				data[3][row].setText("" + accList.get(initAcc+row-1).getDate());
			}
			else{
				data[3][row].setText("");
			}
		}
	}
	
	/**
	 * Determine if it is the last page. 
	 * @return
	 * True if last page, False if not.
	 */
	public boolean isLastPage(){
		return initAcc + 6 > numAcc;
	}
	
	/**
	 * Determine if it is the first page. 
	 * @return
	 * True if first page, False if not.
	 */
	public boolean isFirstPage(){
		return initAcc == 1;
	}
	
	/**
	 * Increment the initial level of accomplishment
	 */
	public void incrementInitAcc(){	
		if (!isLastPage()){
			initAcc += 6;
		}
	}
	
	/**
	 * Decrement the initial level of accomplishment
	 */
	public void decrementInitAcc(){
		if (isFirstPage()){
			initAcc = 1;
		}
		else{
			initAcc -= 6;
		}
	}
}
