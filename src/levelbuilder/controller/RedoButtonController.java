package levelbuilder.controller;

import java.awt.event.ActionEvent;

import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Controller for the redo button
 * @author Fuchen Chen
 *
 */
public class RedoButtonController extends ButtonController{
	
	/**
	 * Constructor
	 */
	public RedoButtonController(Model m, ApplicationPanel app) {
		super(m, app);
	}

	/**
	 * Upon action performed, redo the last move.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		new RedoController(model, appPanel).process();
	}
}
