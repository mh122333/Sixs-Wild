package game.controller;

import game.entity.Board;
import game.entity.Game;
import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
 * The RemoveButtonController class handles setting the current move of a level to be a remove move
 * when the "Remove" button is pressed.
 * @author Maximillian Hoerhold
 *
 */
public class RemoveButtonController implements ActionListener {
	
	/** The Game object which contains all entity objects which are required to play the game. */
	protected Game game;
	/** The ApplicationPanel which contains the "Remove" button. */
	protected ApplicationPanel appPanel;
	
	/** RemoveButtonController Constructor*/
	public RemoveButtonController(Game g, ApplicationPanel appPanel){
		this.game = g;
		this.appPanel = appPanel;
	}

	/** Sets the current Move to be a RemoveMove when the given ActionEvent occurs. */
	public void actionPerformed(ActionEvent e) {
		game.getCurrentLevel().getBoard().setCurrentMove(Board.RemoveMove);
	}
}
