package game.controller;

import game.entity.Game;
import game.entity.LightningBoard;
import game.entity.NormalMove;
import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import timer.LightningTimer;
import timer.LightningTimerStart;
import timer.LightningTimerTask;
import levelbuilder.model.Model;

/** the ShowPlayViewController class processes the level that the user selects and initializes the proper
 * level play view so that the user can play it.
 * @author Maximillian Hoerhold
 *
 */
public class ShowPlayViewController implements ActionListener{
	
	/** The Game object which contains all entity objects required to play the game. */
	protected Game game;
	/** The number of the level that the user selected. */
	protected int buttonNum;
	/** The ApplicationPanel which has the list of available levels from which the user chose. */
	protected ApplicationPanel appPanel;
	
	/** ShowPlayViewController Constructor */
	public ShowPlayViewController(Game game, int buttonNum, ApplicationPanel appPanel){
		this.game = game;
		this.buttonNum = buttonNum;
		this.appPanel = appPanel;
	}

	/** Figures out which level to initialize and then displays the proper playView when the given
	 * ActionEvent is performed.
	 */
	public void actionPerformed(ActionEvent e) {
		// Figure out the correct level to load
		int l = appPanel.getLevelSelectView().getLevelListView().getInitLevel() + buttonNum - 1;
		game.setCurrentLevel(l);
		System.out.println("Playing level " + l);
		game.getCurrentLevel().initBoard();
		
		if (game.getCurrentLevel().getType() == Model.Release){
			NormalMove m = new NormalMove(game);
			m.checkSixToRelease();
		}
		
		LightningTimerStart lts = new LightningTimerStart(game,appPanel);
		lts.start();
		
		// Set the correct level to PlayView
		appPanel.getPlayView().getBoardView().setUninitialized();		
		appPanel.getPlayView().update();
		appPanel.showPlayView();		
	}

}
