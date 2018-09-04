package levelbuilder.controller;

import java.awt.event.ActionEvent;

import rwManager.LoadLBManager;
import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Controller for the edit level button on the splash screen.
 * @author Fuchen Chen
 *
 */
public class EditLevelController extends ButtonController{

	/**
	 * Constructor
	 */
	public EditLevelController(Model model, ApplicationPanel appPanel) {
		super(model, appPanel);
	}

	/**
	 * Upon action performed, load the existing editable levels and update the view.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		appPanel.showNavigationView();
		appPanel.showBasicView();
		
		LoadLBManager lmg = new LoadLBManager(model);
		appPanel.getBasicSettingView().showLevelList(lmg.getEditableLevels());
	}
}