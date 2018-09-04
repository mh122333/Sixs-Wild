package game.controller;

import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The AccListViewNextController class handles updating the view when the "Next" button is pressed
 * on the accomplishment view.
 * 
 * @author Maximillian Hoerhold
 *
 */
public class AccListViewNextController implements ActionListener{
	
	/**The ApplicationPanel which contains the "Next" button. */
	protected ApplicationPanel appPanel;
	
	/** AccListViewNextController Constructor */
	public AccListViewNextController(ApplicationPanel appPanel){
		this.appPanel = appPanel;
	}

	/** Increments the AcomplishmentView displayed and updates the display when 
	 * the given ActionEvent is performed. 
	 */
	public void actionPerformed(ActionEvent arg0) {
		appPanel.getAccListView().getAccLabelView().incrementInitAcc();
		appPanel.getAccListView().update();
	}
}
