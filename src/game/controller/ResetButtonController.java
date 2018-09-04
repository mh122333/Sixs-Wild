package game.controller;

import game.entity.Board;
import game.entity.Game;
import game.entity.ResetMove;
import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
 * The ResetButtonController class handles setting the current move of a level to be a reset move
 * when the "Reset" button is pressed.
 * @author Maximillian Hoerhold
 *
 */
public class ResetButtonController implements ActionListener{
	
	/** The Game object which contains all entity objects which are required to play the game. */
	protected Game game;
	/** The ApplicationPanel which contains the "Reset" button. */
	protected ApplicationPanel appPanel;
	
	/** ResetButtonController Constructor*/
	public ResetButtonController(Game g, ApplicationPanel appPanel){
		this.game = g;
		this.appPanel = appPanel;
	}

	/** Sets the current Move to be a ResetMove when the given ActionEvent occurs and performs the ResetMove. */
	public void actionPerformed(ActionEvent e) {
		ResetMove m = new ResetMove(game);
		m.doMove();
		appPanel.getPlayView().update();
	}
}
