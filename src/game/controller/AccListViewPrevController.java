package game.controller;

import game.view.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The AccListViewPrevController class handles updating the view when the "Prev" button is pressed
 * on the accomplishment view.
 * 
 * @author Maximillian Hoerhold
 *
 */
public class AccListViewPrevController implements ActionListener{
	
	/**The ApplicationPanel which contains the "Prev" button. */
	protected ApplicationPanel appPanel;
	
	/** AccListViewPrevController Constructor */
	public AccListViewPrevController(ApplicationPanel appPanel){
		this.appPanel = appPanel;
	}
	
	/** Decrements the AcomplishmentView displayed and updates the display when 
	 * the given ActionEvent is performed. 
	 */
	public void actionPerformed(ActionEvent arg0) {
		appPanel.getAccListView().getAccLabelView().decrementInitAcc();
		appPanel.getAccListView().update();
	}
}
