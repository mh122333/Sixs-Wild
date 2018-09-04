package levelbuilder.controller;

import game.entity.Game;
import game.view.PlayView;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JFrame;

import rwManager.SaveLBManager;
import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Controller for preview button
 * @author CFC
 *
 */
public class PreviewButtonController extends ButtonController{
	/** 
	 * Constructor
	 */
	public PreviewButtonController(Model model, ApplicationPanel appPanel) {
		super(model, appPanel);
	}

	/**
	 * Save the level and create a preview frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame preview = new JFrame();
		
		// Save the current level
		SaveLBManager saveManager = new SaveLBManager(model);
		try {
			saveManager.saveData();
			System.out.println("Successfully save level" + model.getLevel().getValue());
		} catch (IOException ie) {
			System.out.println("Fail to save level");
		}
		
		Game g = new Game();
		g.setCurrentLevel(model.getLevel().getValue());
		g.getCurrentLevel().initBoard();
		
		PlayView playView = new PlayView(g);
		
		// Set the correct level to PlayView
		playView.getBoardView().setUninitialized();		
		playView.update();
		
		preview.add(playView);
		preview.pack();
		preview.setVisible(true);
	}

}
