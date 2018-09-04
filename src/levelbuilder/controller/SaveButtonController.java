package levelbuilder.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import rwManager.SaveLBManager;
import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Controller for save button
 * @author Fuchen Chen
 *
 */
public class SaveButtonController extends ButtonController{
	/**
	 * Constructor
	 */
	public SaveButtonController(Model model, ApplicationPanel appPanel){
		super(model, appPanel);
	}
	
	/**
	 * Upon action performed, save the current model. 
	 */
	public void actionPerformed(ActionEvent e){
		if(model.getFrequency().getNumFrequencySum() != 100 ){
			JOptionPane.showMessageDialog(appPanel.getParent(), "Number frequency is not correctly set");
		}
		else{
			SaveLBManager saveManager = new SaveLBManager(model);
			try {
				saveManager.saveData();
			} catch (IOException ie) {
				System.out.println("Fail to save level");
			}
		}
	}
}
