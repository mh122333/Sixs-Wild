package game.view;

import game.entity.Game;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

/**
 * Application class for all the classes related to the game
 * @author Weijia Tao
 *
 */
public class Application extends JFrame {
	/** High level application panel which includes all the views */
	protected ApplicationPanel applicationPanel;
	
	/** High level game class which includes all the models */
	protected Game game;
	
	/**
	 * Create the application.
	 */
	public Application() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		game = new Game();
		
		applicationPanel = new ApplicationPanel(game);
		getContentPane().add(applicationPanel, BorderLayout.CENTER);
		pack();
	}
	
	public ApplicationPanel getAppPanel(){
		return this.applicationPanel;
	}
	
	public Game getGame(){
		return this.game;
	}
}