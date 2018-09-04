package levelbuilder.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

/**
 * Navigation view for navigation between different setting views
 * and undo/redo functions
 * @author Weijia Tao
 *
 */
public class NavigationView extends JPanel{
	/** Basic setting view button */
	protected JButton basicButton;
	
	/** Detail setting view button*/
	protected JButton detailButton;
	
	/** Board setting view button*/
	protected JButton boardButton;
	
	/** Undo button*/
	protected JButton undoButton;
	
	/** Redo button*/
	protected JButton redoButton;
	
	/**
	 * Create all the buttons
	 */
	public NavigationView() {
		setPreferredSize(new Dimension(450, 50));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
		
		basicButton = new JButton("Basic");
		basicButton.setBounds(0, 0, 150, 25);
		add(basicButton);
		
		detailButton = new JButton("Detail");
		detailButton.setBounds(150, 0, 150, 25);
		add(detailButton);
		
		boardButton = new JButton("Board");
		boardButton.setBounds(300, 0, 150, 25);
		add(boardButton);
		
		undoButton = new JButton("undo");
		undoButton.setBounds(0, 25, 225, 25);
		add(undoButton);
		
		redoButton = new JButton("redo");
		redoButton.setBounds(225, 25, 225, 25);
		add(redoButton);
	}
	
	public JButton getBasicButton(){
		return basicButton;
	}
	
	public JButton getDetailButton(){
		return detailButton;
	}
	
	public JButton getBoardButton(){
		return boardButton;
	}
	
	public JButton getUndoButton(){
		return undoButton;
	}
	
	public JButton getRedoButton(){
		return redoButton;
	}
}
