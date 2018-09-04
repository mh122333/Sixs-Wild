package game.controller;

import game.entity.Game;
import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ShowAccListViewController class handles showing the list of the player's accomplishments
 * when he selects to do so from the mode select screen by selecting the accomplishments button.
 * @author Maximillian Hoerhold
 *
 */
public class ShowAccListViewController implements ActionListener{
	
	/** The Game object which contains all entity objects which are required to play the game. */
	protected Game game;
	/** The ApplicationPanel which contains the "Accomplishments" button. */
	protected ApplicationPanel appPanel;
	
	/** ShowAccListViewController Constructor */
	public ShowAccListViewController(Game g, ApplicationPanel appPanel){
		this.game = g;
		this.appPanel = appPanel;
	}

	/** Updates the AcclistView to display all available accomplishments and then sets it to be
	 * visible when the given ActionEvent is performed.
	 */
	public void actionPerformed(ActionEvent arg0) {
		appPanel.getAccListView().update();
		appPanel.showAccListView();
	}

}