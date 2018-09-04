package levelbuilder.controller;

import java.awt.event.ActionEvent;

import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Controller for the undo button
 * @author 
 *
 */
public class UndoButtonController extends ButtonController{
	/**
	 * Constructor
	 */
	public UndoButtonController(Model m, ApplicationPanel app) {
		super(m, app);
	}

	/**
	 * When action performed, undo the move. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		new UndoController(model, appPanel).process();
	}
}
