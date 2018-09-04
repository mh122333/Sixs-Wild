package game.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;

/**
 * Splash screen for the game
 * @author Weijia Tao
 *
 */
public class SplashScreenView extends JPanel{
	/** Go button to enter the game */
	protected JButton goButton;
	
	/**
	 * Initialize the splash screen.
	 */
	public SplashScreenView() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(1280, 720));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setLayout(null);
		
		
		goButton = new JButton("GO");
		goButton.setFont(new Font("Arial", Font.PLAIN, 24));
		goButton.setBounds(540,500,200,100);
		add(goButton);
		
		JLabel SixesWildLabel = new JLabel("Sixes Wild");
		SixesWildLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SixesWildLabel.setFont(new Font("Arial", Font.PLAIN, 32));
		SixesWildLabel.setBounds(540, 197, 196, 55);
		add(SixesWildLabel);
		
		JTextArea TelestoInfo = new JTextArea();
		TelestoInfo.setEditable(false);
		TelestoInfo.setOpaque(false);
		TelestoInfo.setFont(new Font("Arial", Font.PLAIN, 18));
		TelestoInfo.setText("Telesto\r\nFuchen Chen\r\nZhaochen Ding\r\nMaximillian Hoerhold\r\nYo Karita\r\nWeijia Tao");
		TelestoInfo.setBounds(540, 290, 200, 200);
		add(TelestoInfo);
	}
	
	public JButton getGoButton(){
		return this.goButton;
	}
}
