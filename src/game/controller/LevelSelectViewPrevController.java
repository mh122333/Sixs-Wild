package game.controller;

import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LevelSelectViewPrevController class handles updating the view when the "Prev" button is pressed
 * on the Level Select View.
 * 
 * @author Maximillian Hoerhold
 *
 */
public class LevelSelectViewPrevController implements ActionListener{
	
	/** The ApplicationPannel which contains the "Prev" button. */
	protected ApplicationPanel appPanel;
	
	/** LevelSelectViewPrevController Constructor */
	public LevelSelectViewPrevController(ApplicationPanel appPanel){
		this.appPanel = appPanel;
	}
	
	/** Decrements the LevelSelectView displayed and updates the
	 *  display when the given ActionEvent is performed. */
	public void actionPerformed(ActionEvent arg0) {
		appPanel.getLevelSelectView().getLevelListView().decrementInitLevel();
		appPanel.getLevelSelectView().update();
	}

}
