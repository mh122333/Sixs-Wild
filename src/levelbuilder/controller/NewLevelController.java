package levelbuilder.controller;

import java.awt.event.ActionEvent;

import rwManager.LoadLBManager;
import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Controller for the new level button on the splash screen.
 * @author Fuchen Chen
 *
 */
public class NewLevelController extends ButtonController{
	
	/**
	 * Constructor
	 */
	public NewLevelController(Model model, ApplicationPanel appPanel) {
		super(model, appPanel);
	}
	
	/**
	 * Upon action performed, set the minimum level, show the level text field and update the view.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		LoadLBManager lmg = new LoadLBManager(model);
		model.getLevel().setValue(lmg.getMaxLevel() + 1);
		appPanel.getBasicSettingView().refresh();
		appPanel.showNavigationView();
		appPanel.showBasicView();
		appPanel.getBasicSettingView().showLevelTextField();
	}
}