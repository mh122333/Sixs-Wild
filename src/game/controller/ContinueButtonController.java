package game.controller;

import game.entity.Game;
import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import timer.LightningTimerStart;


/** The ContinueButtonController class handles the "Continue" button located on the result screen
 * which is displayed upon completion of a level.
 * @author Maximillian Hoerhold
 *
 */
public class ContinueButtonController implements ActionListener{
	
	/** The Game which contains all entities needed to process the "Continue" button. */
	protected Game game;
	/** The ApplicationPanel which contains the "Continue" button.  */
	protected ApplicationPanel appPanel;
	
	/** ContinueButtonController Constructor */
	public ContinueButtonController(Game game, ApplicationPanel appPanel){
		this.game = game;
		this.appPanel = appPanel;
	}
	
	/** When the given ActionEvent is performed, currentLevel is incremented
	 *  and the new currentLevel is initialized. */
	public void actionPerformed(ActionEvent e) {
		// Figure out the correct level to load
		int l = game.getCurrentLevel().getLevel();
		game.setCurrentLevel(l+1);
		System.out.println("Retry level " + l);
		game.getCurrentLevel().initBoard();
		
		// Set the correct level to PlayView
		appPanel.getPlayView().getBoardView().setUninitialized();		
		appPanel.getPlayView().update();
		
		LightningTimerStart lts = new LightningTimerStart(game,appPanel);
		lts.start();
		
		appPanel.showPlayView();		
	}
}
