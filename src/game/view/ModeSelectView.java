package game.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;

/**
 * Mode selection view for play or accomplishments
 * @author Weijia Tao
 *
 */
public class ModeSelectView extends JPanel{
	/** Play button */
	protected JButton levelButton;
	
	/** Accomplishment button */
	protected JButton accButton;
	
	/**
	 * Initialize the view
	 */
	public ModeSelectView() {
		setPreferredSize(new Dimension(1280, 720));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
		
		levelButton = new JButton("Levels");
		levelButton.setBounds(540, 210, 200, 100);
		add(levelButton);
		
		accButton = new JButton("Accomplishments");
		accButton.setBounds(540, 410, 200, 100);
		add(accButton);
		
	}
	
	public JButton getLevelButton(){
		return this.levelButton;
	}
	public JButton getAccButton(){
		return this.accButton;
			
	}	
}			
		