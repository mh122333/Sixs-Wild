package levelbuilder.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import rwManager.LoadLBManager;
import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Controller for the level list in edit mode
 * @author Fuchen Chen
 *
 */
public class LevelListController implements ItemListener{	
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
	public LevelListController(Model model, ApplicationPanel appPanel){
		this.model = model;
		this.appPanel = appPanel;
	}
	
	/**
	 * When the item is selected, load the corresponding level.
	 */
	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			Integer level = (Integer) event.getItem();
			
			// Load the level
			LoadLBManager lmg = new LoadLBManager(model);
			try {
				lmg.loadData(level);
				appPanel.refresh();
			} catch (IOException e) {
				System.out.println("Unable to load the selected level");
			}
		}
	}

}
