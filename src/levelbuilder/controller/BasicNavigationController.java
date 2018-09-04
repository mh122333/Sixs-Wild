package levelbuilder.controller;

import java.awt.event.ActionEvent;

import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Navigation Controller to show the basic setting view. 
 * @author Fuchen Chen
 *
 */
public class BasicNavigationController extends ButtonController{
	/**
	 * Constructor
	 */
	public BasicNavigationController(Model model, ApplicationPanel appPanel){
		super(model, appPanel);
	}
	
	/**
	 * Override to show basic setting view
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		appPanel.showBasicView();
	}
}
