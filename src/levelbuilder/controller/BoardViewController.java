package levelbuilder.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import levelbuilder.model.Model;
import levelbuilder.model.Position;
import levelbuilder.move.*;
import levelbuilder.redo.RedoManager;
import levelbuilder.undo.UndoManager;
import levelbuilder.view.ApplicationPanel;

/**
 * Controller for modifying the shape, six end and start positions.
 * @author Fuchen Chen
 *
 */
public class BoardViewController extends MouseAdapter{
	/** Model to be modified */
	protected Model model;
	
	/** High level application panel */
	protected ApplicationPanel appPanel;
	
	/**
	 * Constructor
	 * @param m
	 * Model to be modified
	 * @param appPanel
	 * High level application panel
	 */
	public BoardViewController(Model m, ApplicationPanel appPanel){
		this.model = m;
		this.appPanel = appPanel;
	}
	
	/**
	 * Upon mouse pressed, depending on the edit status, modify the model and update view.
	 */
	@Override
	public void mousePressed(MouseEvent me) {
		// Get the position of the cell clicked		
		Position p = appPanel.getBoardSettingView().getAllBoardView().clickOnCell(me.getPoint().x, me.getPoint().y);
		
		int status = model.getEditStatus();
		switch(status){
		case Model.ShapeEdit:
			IMove shapeMove = new ModifyShape(p, model);
			
			// request move
			if (shapeMove.execute()) {
				UndoManager.instance().recordMove(shapeMove);
				RedoManager.instance().removeAllMove();
			}	
			else {
				// do nothing
			}
			break;
		case Model.SixStartEdit:
			IMove sixStartMove = new ModifySixStart(p, model);
			
			// request move
			if (sixStartMove.execute()) {
				UndoManager.instance().recordMove(sixStartMove);
				RedoManager.instance().removeAllMove();
			}	
			else {
				// do nothing
			}
			break;
		case Model.SixEndEdit:
			IMove sixEndMove = new ModifySixEnd(p, model);
			
			// request move
			if (sixEndMove.execute()) {
				UndoManager.instance().recordMove(sixEndMove);
				RedoManager.instance().removeAllMove();
			}	
			else {
				// do nothing
			}
			break;
		default:
			break;
		}

		
		// Update GUI
		appPanel.getBoardSettingView().getAllBoardView().updateBoard();
	}
}
