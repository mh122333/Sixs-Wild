package game.view;

import game.entity.EliminationLevel;
import game.entity.Game;
import game.entity.Level;
import game.entity.LightningLevel;
import game.entity.PuzzleLevel;
import game.entity.ReleaseBoard;
import game.entity.ReleaseLevel;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.SwingConstants;

import levelbuilder.model.Model;

/**
 * The actual game view which displays all the game status and board. 
 * @author Weijia Tao
 *
 */
public class PlayView extends JPanel{
	/** Game related to this view */
	protected Game game;
	
	protected JLabel levelLabel;
	protected JLabel scoreLabel;
	protected JLabel goalLabel;
	protected JLabel limitLabel;
	
	protected JButton backButton;
	protected JButton resetButton;
	protected JButton removeButton;
	protected JButton swapButton;
	
	/** Progress bar */
	protected ProgressBarView progressBarView;
	
	/** Board */
	protected BoardView boardView;
	
	/** Combo */
	protected ComboView comboView;
	
	/**
	 * Initialize the play view 
	 * @param g
	 *  Game related to this view
	 */
	public PlayView(Game g) {		
		this.game = g;
		
		setPreferredSize(new Dimension(1280, 720));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
		
		backButton = new JButton("Back");
		backButton.setBounds(1080, 0, 200, 50);
		add(backButton);
		
		levelLabel = new JLabel();
		levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		levelLabel.setBounds(0, 0, 150, 50);
		
		scoreLabel = new JLabel("Score");
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setBounds(329, 0, 150, 50);
		
		goalLabel = new JLabel("Goal");
		goalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		goalLabel.setBounds(650, 0, 150, 50);
		
		limitLabel = new JLabel("Move");
		limitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		limitLabel.setBounds(1000, 600, 200, 100);
		
		resetButton = new JButton("Reset");
		resetButton.setBounds(1000, 200, 100, 100);
		
		removeButton = new JButton("Remove");
		removeButton.setBounds(1000, 310, 100, 100);
		
		swapButton = new JButton("Swap");
		swapButton.setBounds(1000, 420, 100, 100);
	
		progressBarView = new ProgressBarView(game);
		progressBarView.setBounds(265, 691, progressBarView.getWidth(), progressBarView.getHeight());
		
		boardView = new BoardView(game);
		boardView.setBounds(300, 50, 630, 630);
		
		comboView = new ComboView(game);
		comboView.setBounds(0, 160, 300, 400);
		
		add(scoreLabel);
		add(levelLabel);
		add(goalLabel);
		add(limitLabel);
		add(resetButton);
		add(removeButton);
		add(swapButton);
		add(progressBarView);
		add(boardView);
		add(comboView);
	}
	
	/**
	 * Update the play view based on the game status
	 */
	public void update(){
		Level level = game.getCurrentLevel();
		
		boardView.update();
		
		progressBarView.update();
		
		comboView.update();
			
		levelLabel.setText("Level: " + level.getLevel());
		
		scoreLabel.setText("Score: " + level.getBoard().getCurrentScore());
		
		resetButton.setText("Reset: " + level.getBoard().getResetMove());
		
		removeButton.setText("Remove: " + level.getBoard().getRemoveMove());
		
		swapButton.setText("Swap: " + level.getBoard().getSwapMove());
		
		int type = level.getType();
		switch(type){
		case Model.Puzzle:
			goalLabel.setText("Reach: " + level.getGoalScore());
			limitLabel.setText("Move: " + level.getBoard().getLimitLeft());
			break;
		case Model.Lightning:
			goalLabel.setText("Reach: " + level.getGoalScore());
			limitLabel.setText("Time: " + level.getBoard().getLimitLeft());
			break;
		case Model.Elimination:
			goalLabel.setText("Mark all");
			limitLabel.setText("Move: " + level.getBoard().getLimitLeft());
			break;
		case Model.Release:
			goalLabel.setText("6s left: " + ((ReleaseBoard) level.getBoard()).getSixToRelease());
			limitLabel.setText("Move: " + level.getBoard().getLimitLeft());
			break;
		default:
			break;
		}
	}
	
	public JButton getBackButton(){
		return backButton;
	}
	
	public JButton getResetButton(){
		return resetButton;
	}
	
	public JButton getRemoveButton(){
		return removeButton;
	}
	
	public JButton getSwapButton(){
		return swapButton;
	}
	
	public ProgressBarView getProgressBarView(){
		return progressBarView;
	}
	
	public BoardView getBoardView(){
		return boardView;
	}
	
	
	public ComboView getComboView(){
		return comboView;
	}

}
