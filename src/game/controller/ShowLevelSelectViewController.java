package game.controller;

import game.entity.LightningBoard;
import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import levelbuilder.model.Model;
import timer.LightningTimer;

/**
 * The ShowLevelSelectViewController class handles showing the list of available levels
 * when he selects to do so from the mode select screen by selecting the levels button.
 * @author Maximillian Hoerhold
 *
 */
public class ShowLevelSelectViewController implements ActionListener{
	
	/** The ApplicationPanel which contains the "Levels" button. */
	protected ApplicationPanel appPanel;
	
	/** ShowLevelSelectViewController Constructor */
	public ShowLevelSelectViewController(ApplicationPanel appPanel){
		this.appPanel = appPanel;
	}
	
	/** Updates the LevelSelectView to display all available Levels and then sets it to be
	 * visible when the given ActionEvent is performed.
	 */
	public void actionPerformed(ActionEvent arg0) {
		LightningTimer t = LightningTimer.getInstance();
		t.cancel();
		
		appPanel.getPlayView().getComboView().stopTimer();
		appPanel.getLevelSelectView().update();
		appPanel.showLevelSelctView();
	}

}
