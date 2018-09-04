package levelbuilder.controller;

import java.awt.event.ActionEvent;


import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Navigation Controller to show the detail setting view. 
 * @author Fuchen Chen
 *
 */
public class DetailNavigationController extends ButtonController{
	/**
	 * Constructor
	 */
	public DetailNavigationController(Model model, ApplicationPanel appPanel){
		super(model, appPanel);
	}
	
	/**
	 * Override to show detail setting view
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		update();
	}
	
	/**
	 * The method to update the detail setting view. 
	 * If level type is lightning, enable the time text field. 
	 * Otherwise, enable the move text field. 
	 */
	private void update(){
		if (model.getType().getValue() == Model.Lightning){
			appPanel.getDetailSettingView().getTimeTF().setEnabled(true);
			appPanel.getDetailSettingView().getMoveTF().setEnabled(false);
		}
		else{
			appPanel.getDetailSettingView().getTimeTF().setEnabled(false);
			appPanel.getDetailSettingView().getMoveTF().setEnabled(true);
		}
		appPanel.showDetailView();
	}
}

