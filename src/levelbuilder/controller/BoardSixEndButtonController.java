package levelbuilder.controller;

import java.awt.event.ActionEvent;

import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Controller for the six end position button
 * @author Fuchen Chen
 *
 */
public class BoardSixEndButtonController extends ButtonController{
	/**
	 * Constructor
	 */
	public BoardSixEndButtonController(Model m, ApplicationPanel appPanel){
		super(m, appPanel);
	}
	
	/**
	 * Upon action performed, change the model edit status to six end position
	 */
	public void actionPerformed(ActionEvent e){
		// appPanel.getBoardSettingView().showBoardSixEndView();
		model.setEditStatus(Model.SixEndEdit);
	}
}