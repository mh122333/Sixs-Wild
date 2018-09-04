package levelbuilder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Abstract class for button controller
 * @author Fuchen Chen
 *
 */
public abstract class ButtonController implements ActionListener{
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
	public ButtonController(Model model, ApplicationPanel appPanel){
		this.model = model;
		this.appPanel = appPanel;
	}
	
	/**
	 * Button is required to react to action performed event. 
	 */
	public abstract void actionPerformed(ActionEvent e);
}
