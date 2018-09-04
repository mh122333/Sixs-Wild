package levelbuilder.controller;

import java.awt.event.ActionEvent;

import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Controller for the six start position button
 * @author Fuchen Chen
 *
 */
public class BoardSixStartButtonController extends ButtonController{
	/**
	 * Constructor
	 */
	public BoardSixStartButtonController(Model m, ApplicationPanel appPanel){
		super(m, appPanel);
	}
	
	/**
	 * Upon action performed, change the model edit status to six start position
	 */
	public void actionPerformed(ActionEvent e){
		// appPanel.getBoardSettingView().showBoardSixStartView();
		model.setEditStatus(Model.SixStartEdit);
	}
}
