package game.controller;

import game.entity.Game;
import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import levelbuilder.model.Model;
import timer.LightningTimer;
import timer.LightningTimerStart;
import timer.LightningTimerTask;

/**
 * The RetryButtonController class handles the "Retry" button which appears on result views
 * upon completion of a level.
 * @author Maximillian Hoerhold
 *
 */
public class RetryButtonController implements ActionListener{
	
	/** The Game object which contains all entity objects which are required to play the game. */
	protected Game game;
	/** The ApplicationPanel which contains the "Retry" button. */
	protected ApplicationPanel appPanel;
	
	/** RetryButtonController Constructor*/
	public RetryButtonController(Game game, ApplicationPanel appPanel){
		this.game = game;
		this.appPanel = appPanel;
	}

	/** Sets CurrentLevel to a new instance of the current level
	 *  when the given ActionEvent is performed and initializes it. */
	public void actionPerformed(ActionEvent e) {
		// Figure out the correct level to load
		int l = game.getCurrentLevel().getLevel();
		game.setCurrentLevel(l);
		System.out.println("Retry level " + l);
		game.getCurrentLevel().initBoard();
		
		appPanel.getPlayView().getBoardView().setUninitialized();		
		appPanel.getPlayView().update();
		
		LightningTimerStart lts = new LightningTimerStart(game,appPanel);
		lts.start();
		
		appPanel.showPlayView();		
	}
}
