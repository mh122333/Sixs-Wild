package game.controller;

import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LevelSelectViewNextController class handles updating the view when the "Next" button is pressed
 * on the Level Select view.
 * 
 * @author Maximillian Hoerhold
 *
 */
public class LevelSelectViewNextController implements ActionListener{
	/** The ApplicationPannel which contains the "Next" button. */
	protected ApplicationPanel appPanel;
	
	/** LevelSelectViewNextController Constructor */
	public LevelSelectViewNextController(ApplicationPanel appPanel){
		this.appPanel = appPanel;
	}
	
	/** Increments the LevelSelectView displayed and updates the
	 *  display when the given ActionEvent is performed. */
	public void actionPerformed(ActionEvent arg0) {
		appPanel.getLevelSelectView().getLevelListView().incrementInitLevel();
		appPanel.getLevelSelectView().update();
	}

}
