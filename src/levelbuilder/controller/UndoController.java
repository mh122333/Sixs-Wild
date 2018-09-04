package levelbuilder.controller;

import levelbuilder.model.Model;
import levelbuilder.move.IMove;
import levelbuilder.redo.RedoManager;
import levelbuilder.undo.UndoManager;
import levelbuilder.view.ApplicationPanel;

/**
 * Undo controller that is able to undo the move in the list.
 * @author Fuchen Chen
 *
 */
public class UndoController {
	/** Model to be modified */
	Model model;
	
	/** High level application panel */
	ApplicationPanel appPanel;

	/**
	 * Constructor
	 */
	public UndoController(Model m, ApplicationPanel appPanel) {
		this.model = m;
		this.appPanel = appPanel;
	}

	/**
	 * Undo the last move
	 * @return
	 * True if undo successfully, False if not
	 */
	public boolean process() {
		UndoManager mgr = UndoManager.instance();
		
		// see if there is anything that can be undone
		IMove m = mgr.removeLastMove();
		if (m == null) { return false; }
		
		// now complete the request, if possible, and update GUI and model
		if (!m.undo()) {
			return false;
		}
		
		RedoManager.instance().recordMove(m);
		
		// refresh GUI with new information
		appPanel.getBasicSettingView().refresh();
		appPanel.getDetailSettingView().refresh();
		appPanel.getBoardSettingView().refresh();
		return true;
	}
}