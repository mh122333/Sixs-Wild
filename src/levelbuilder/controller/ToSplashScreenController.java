package levelbuilder.controller;

import java.awt.event.ActionEvent;

import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Controller for the back button that is linked to the splash screen
 * @author Fuchen Chen
 *
 */
public class ToSplashScreenController extends ButtonController{

	/**
	 * Constructor
	 */
	public ToSplashScreenController(Model model, ApplicationPanel appPanel) {
		super(model, appPanel);
	}

	/**
	 * Upon action performed, reset the model and view.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		model.reset();
		appPanel.refresh();
		appPanel.showSplashScreenView();
	}

}
