package levelbuilder.controller;

import java.awt.event.ActionEvent;
import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Navigation Controller to show the board setting view. 
 * @author Fuchen Chen
 *
 */
public class BoardNavigationController extends ButtonController{
	/**
	 * Constructor
	 */
	public BoardNavigationController(Model model, ApplicationPanel appPanel){
		super(model, appPanel);
	}

	/**
	 * Override to show board setting view
	 */
	@Override
	public void actionPerformed(ActionEvent ae){
		update();
	}
	
	/**
	 * The method to update the board setting view. 
	 * If level type is release, also show the release model button. 
	 */
	private void update(){
		if (model.getType().getValue() == Model.Release){
			appPanel.getBoardSettingView().showReleaseModeButtons();
		}
		else{
			appPanel.getBoardSettingView().removeReleaseModeButtons();
		}
		
		model.setEditStatus(Model.ShapeEdit);
		appPanel.showBoardView();
	}
}

