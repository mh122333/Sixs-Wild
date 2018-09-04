package game.controller;

import game.entity.*;
import game.view.ApplicationPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import levelbuilder.model.Position;

/** The BoardController class handles processing user input to generate and apply moves to
 * the board and update the board state.
 * @author Maximillian Hoerhold
 *
 */
public class BoardController extends MouseAdapter{
	
	/** The Game object which has all entity objects required to run the game. */
	protected Game game;
	/** The ApplicationPanel in which the game is being played. */
	protected ApplicationPanel appPanel;
	/** The types of moves which the user has available to them. */
	protected Move[] moves = new Move[3];
	/** The move which the user is in the process of making and stores the users actions. */
	protected int currentMove;
	
	/** BoardController Constructor */
	public BoardController(Game g, ApplicationPanel appPanel){
		this.game = g;
		
		this.appPanel = appPanel;
		
		this.moves[Board.NormalMove] = new NormalMove(game);
		
		this.moves[Board.RemoveMove] = new RemoveMove(game);
		
		this.moves[Board.SwapMove] = new SwapMove(game);
		
	}
	
	/** When the mouse is pressed it adds the cell which the mouse is over and 
	 * adds it to the CurrentMove and updates the view. */
	public void mousePressed(MouseEvent me){
		this.currentMove = game.getCurrentLevel().getBoard().getCurrentMove();
		
		// Get the cell position of the mouse press
		Position p = appPanel.getPlayView().getBoardView().getClickPosition(me.getX(), me.getY());
		
	
		// Set the cell to be selected and update the view
		if (moves[currentMove].add(p)){
			// Update view
			appPanel.getPlayView().getBoardView().update();
		}
	}
	
	/** When the mouse is dragged if it passes over a cell which is not included in CurrentMove
	 * it is added to CurrentMove.
	 */
	public void mouseDragged(MouseEvent me){
		this.currentMove = game.getCurrentLevel().getBoard().getCurrentMove();
		
		Position p = appPanel.getPlayView().getBoardView().getClickPosition(me.getX(), me.getY());
		// If cell is not in the list, set selected and add it
		if (moves[currentMove].add(p)){
			appPanel.getPlayView().getBoardView().update();
		}		
	}
	
	/** When the mouse is released the move is applied to the board and the board is updated accordingly. */
	public void mouseReleased(MouseEvent me){
		this.currentMove = game.getCurrentLevel().getBoard().getCurrentMove();
		
		moves[currentMove].doMove();
		moves[currentMove].clear();
		appPanel.getPlayView().getBoardView().update();
		
		// After release, reset the move to normal move
		game.getCurrentLevel().getBoard().setCurrentMove(Board.NormalMove);
		this.currentMove = game.getCurrentLevel().getBoard().getCurrentMove();
		
		// Update the entire play view
		if (!game.getCurrentLevel().isLevelEnd()){
			appPanel.getPlayView().update();
		}
		else{
			if (game.getCurrentLevel().isLevelPassed()){
				game.addAcc();	
				game.setLevelPlayable(game.getCurrentLevel().getLevel() + 1);
			}
			appPanel.getPlayView().getComboView().stopTimer();
			appPanel.getResultView().update();
			appPanel.showResultView();
			
		}
	}
}
