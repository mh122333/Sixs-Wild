package levelbuilder.view;

import javax.swing.JFrame;
import levelbuilder.model.*;

import java.awt.BorderLayout;

import java.awt.Color;

/**
 * High level application class for the level builder
 * @author Weijia Tao
 *
 */
public class Application extends JFrame {
	/** Model related to the application */
	protected Model model;
	
	/** High level application panel */
	protected ApplicationPanel applicationPanel;
	
	/**
	 * Constructor
	 * @param m
	 * Model to be related to this application
	 */
	public Application(Model m) {
		this.model = m;
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		applicationPanel = new ApplicationPanel(model);
		getContentPane().add(applicationPanel, BorderLayout.CENTER);
		pack();
	}
	
	/**
	 * Get the application panel of this application 
	 * @return
	 * High level application panel 
	 */
	public ApplicationPanel getAppPanel(){
		return this.applicationPanel;
	}
		
}