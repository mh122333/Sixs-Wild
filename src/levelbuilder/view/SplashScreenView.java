package levelbuilder.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

/**
 * Splash screen view (welcome view)
 * @author Weijia Tao
 *
 */
public class SplashScreenView extends JPanel{
	/** New level button */
	protected JButton newLevelButton;
	
	/** Edit level button */
	protected JButton editLevelButton;
	
	/** Background image */
	protected Image background;
	
	/**
	 * Initialize all the views. 
	 */
	public SplashScreenView() {
		setPreferredSize(new Dimension(450, 450));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
		
		try{
			background = ImageIO.read(new File("images"+File.separator+"levelbuilder-welcome.png"));
		} catch (IOException e){
			System.out.println("No image found");
		}
		
		newLevelButton = new JButton("New Level");
		newLevelButton.setBounds(175, 300, 100, 30);
		add(newLevelButton);
		
		editLevelButton = new JButton("Edit Level");
		editLevelButton.setBounds(175, 370, 100, 30);
		add(editLevelButton);
	}
	
	/**
	 * Override to include the background picture.
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}
	
	public JButton getNewLevelButton(){
		return newLevelButton;
	}
	
	public JButton getEditLevelButton(){
		return editLevelButton;
	}
	
}
