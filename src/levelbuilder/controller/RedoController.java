package levelbuilder.controller;

import levelbuilder.model.Model;
import levelbuilder.move.IMove;
import levelbuilder.redo.RedoManager;
import levelbuilder.undo.UndoManager;
import levelbuilder.view.ApplicationPanel;

/**
 * Redo controller that is able to redo the move in the list.
 * @author Fuchen Chen
 *
 */
public class RedoController {
	/** Model to be modified */
	Model model;
	
	/** High level application panel */
	ApplicationPanel appPanel;
	
	/**
	 * Constructor
	 */
	public RedoController(Model m, ApplicationPanel appPanel) {
		this.model = m;
		this.appPanel = appPanel;
	}

	/**
	 * Redo the last move
	 * @return
	 * True if redo successfully, False if not
	 */
	public boolean process() {
		RedoManager rdmgr = RedoManager.instance();
		UndoManager udmgr = UndoManager.instance();
		
		// see if there is anything that can be redone
		IMove m = rdmgr.removeLastMove();
		if (m == null) { return false; }
		
		// now complete the request, if possible, and update GUI and model
		if (!m.execute()) {
			return false;
		}
		
		udmgr.recordMove(m);
		
		// refresh GUI with new information
		appPanel.getBasicSettingView().refresh();
		appPanel.getDetailSettingView().refresh();
		appPanel.getBoardSettingView().refresh();
		return true;
	}
}
