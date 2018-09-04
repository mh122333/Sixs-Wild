package levelbuilder.view;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;

import levelbuilder.controller.LevelListController;
import levelbuilder.controller.TextFieldController;
import levelbuilder.controller.UndoController;
import levelbuilder.model.*;

/**
 * Basic setting view contains the type and level modification view. 
 * Implements ISettingView and has refresh method. 
 * @author Weijia Tao
 *
 */
public class BasicSettingView extends JPanel implements ISettingView{
	/** Model to be modified */
	protected Model model;
	
	/** Level label */
	protected JLabel levelLabel;
	
	/** Type label */
	protected JLabel typeLabel;
	
	/** Back button */
	protected JButton backButton;
	
	/** Save button */
	protected JButton saveButton;
	
	/** Type text field */
	protected JTextField typeTextField;
	
	/** Level text field for new level*/
	protected JTextField levelTextField;
	
	/** Level combo box for edit level */
	protected JComboBox<Integer> levelList;
	
	/** Current level modification view */
	protected JComponent currentLevelSelect;
	
	/**
	 * Creates all the components
	 * @param m
	 * Model to be modified
	 */
	public BasicSettingView(Model m) {
		this.model = m;
		setPreferredSize(new Dimension(450, 400));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
		
		levelLabel = new JLabel("Level");
		levelLabel.setBounds(170, 127, 30, 15);
		add(levelLabel);
		
		typeLabel = new JLabel("Type");
		typeLabel.setBounds(170, 174, 30, 18);
		add(typeLabel);
		
		levelTextField = new JTextField();
		levelTextField.setBounds(203, 124, 66, 21);
		levelTextField .setColumns(10);

		levelList = new JComboBox<Integer>();
		levelList.setBounds(203, 124, 66, 21);
		
		typeTextField = new JTextField();
		typeTextField.setBounds(203, 173, 66, 21);
		add(typeTextField);
		typeTextField.setColumns(10);
		
		backButton = new JButton("Back");
		backButton.setBounds(0, 377, 93, 23);
		add(backButton);
		
		saveButton = new JButton("Save");
		saveButton.setBounds(357, 377, 93, 23);
		add(saveButton);
		
		JTextArea typeExp = new JTextArea();
		typeExp.setText("Type: \r\n1 = Puzzle\r\n2 = Lightning\r\n3 = Elimination\r\n4 = Release");
		typeExp.setBounds(170, 216, 120, 104);
		add(typeExp);
	}
	
	/**
	 * Set the level text field to be visible
	 */
	public void showLevelTextField(){
		if (currentLevelSelect != null){
			remove(currentLevelSelect);
		}
		currentLevelSelect = levelTextField;	
		add(currentLevelSelect);
		revalidate();
		repaint();
	}
	
	
	/**
	 * Set the level combo box to be visible with given list of levels available
	 */
	public void showLevelList(ArrayList<Integer> list){
		if (currentLevelSelect != null){
			remove(currentLevelSelect);
		}
		levelList.removeAllItems();
		for (int i: list){
			levelList.addItem(i);
		}
		currentLevelSelect = levelList;	
		add(levelList);
		revalidate();
		repaint();
	}
	
	public JTextField getLevelTextField(){
		return levelTextField;
	}
	
	public JComboBox<Integer> getLevelList(){
		return levelList;
	}

	public JTextField getTypeTextField(){
		return typeTextField;
	}
	
	public JButton getSaveButton(){
		return saveButton;
	}
	
	public JButton getBackButton(){
		return backButton;
	}
	
	/**
	 * Refresh the view based on model
	 */
	public void refresh(){
		getLevelTextField().setText("" + model.getLevel().getValue());
		getTypeTextField().setText("" + model.getType().getValue());
	}
}
