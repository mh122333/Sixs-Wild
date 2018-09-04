package game.controller;

import game.entity.Board;
import game.entity.Game;
import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
 * The SwapButtonController class handles setting the current move of a level to be a swap move
 * when the "Swap" button is pressed.
 * @author Maximillian Hoerhold
 *
 */
public class SwapButtonController implements ActionListener{
	/** The Game object which contains all entity objects which are required to play the game. */
	protected Game game;
	/** The ApplicationPanel which contains the "Swap" button. */
	protected ApplicationPanel appPanel;
	
	/** SwapButtonController Constructor*/
	public SwapButtonController(Game g, ApplicationPanel appPanel){
		this.game = g;
		this.appPanel = appPanel;
	}
	
	/** Sets the current Move to be a SwapMove when the given ActionEvent occurs. */
	public void actionPerformed(ActionEvent e) {
		game.getCurrentLevel().getBoard().setCurrentMove(Board.SwapMove);
	}
}
