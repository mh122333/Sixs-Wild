package game.controller;

import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ShowModeSelectViewController class handles displaying the mode select view after the
 * user selects "GO" in the splash screen.
 * @author Maximillian Hoerhold
 *
 */
public class ShowModeSelectViewController implements ActionListener{
	
	/** The ApplicationPanel which contains the button which is pressed to display the modeSelectView.*/
	protected ApplicationPanel appPanel;
	
	/** ShowModeSelectViewController Constructor*/
	public ShowModeSelectViewController(ApplicationPanel appPanel){
		this.appPanel = appPanel;
	}
	
	/** Displays the ModeSelectView when the given ActionEvent is performed. */
	public void actionPerformed(ActionEvent arg0) {
		appPanel.showModeSelctView();
	}

}
