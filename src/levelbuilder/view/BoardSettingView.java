package levelbuilder.view;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import levelbuilder.model.*;

/**
 * Board setting view contains all components related to the baord shape, six end, and six start views. 
 * Implements ISettingView and has refresh method. 
 * @author Weijia Tao
 *
 */
public class BoardSettingView extends JPanel implements ISettingView{
	/** Model to be modified */
	protected Model model;
	
	/** Save button */
	protected JButton saveButton;
	
	/** Preview button */
	protected JButton previewButon;
	
	/** Set board shape button */
	protected JButton setBoardShapeButton;
	
	/** Set six start button */
	protected JButton set6StartButton;
	
	/** Set six end button */
	protected JButton set6EndButton;
	
	/** Board view for shape, six start and end modification */
	protected AllBoardView allBoardView;
	
	/**
	 * Create all the components
	 * @param m
	 * Model to be modified
	 */
	public BoardSettingView(Model m) {
		this.model = m;
		
		setPreferredSize(new Dimension(450, 400));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
		
		previewButon = new JButton("Preview");
		previewButon.setBounds(0, 377, 93, 23);
		add(previewButon);
		
		saveButton = new JButton("Save");
		saveButton.setBounds(357, 377, 93, 23);
		add(saveButton);
		
		set6StartButton = new JButton("6 Start Positions");
		set6StartButton.setBounds(150, 35, 150, 23);
		
		set6EndButton = new JButton("6 End Positions");
		set6EndButton.setBounds(300, 35, 150, 23);
		
		setBoardShapeButton = new JButton("Board Shape");
		setBoardShapeButton.setBounds(0, 35, 150, 23);
		
		// Initialize baordView	
		allBoardView = new AllBoardView(model);
		allBoardView.setBounds(90, 80, allBoardView.getWidth(), allBoardView.getHeight());
		add(allBoardView);
	}
	
	/**
	 * Set release mode modification buttons visible
	 */
	public boolean showReleaseModeButtons(){
		add(setBoardShapeButton);
		add(set6StartButton);
		add(set6EndButton);
		revalidate();
		repaint();
		return true;
	}
	
	/**
	 * Set release mode modification buttons invisible
	 */
	public boolean removeReleaseModeButtons(){
		remove(setBoardShapeButton);
		remove(set6StartButton);
		remove(set6EndButton);
		revalidate();
		repaint();
		return true;
	}
	
	public AllBoardView getAllBoardView(){
		return allBoardView;
	}
	
	public JButton getBoardShapeButton(){
		return setBoardShapeButton;
	}
	
	public JButton getSixStartButton(){
		return set6StartButton;
	}
	
	public JButton getSixEndButton(){
		return set6EndButton;
	}
	
	public JButton getSaveButton(){
		return saveButton;
	}
	
	public JButton getPreviewButton(){
		return previewButon;
	}
	
	/**
	 * Refresh the view based on the model
	 */
	public void refresh(){
		if (model.getType().getValue() != Model.Release){
			removeReleaseModeButtons();
		}
		else{
			showReleaseModeButtons();
		}
		allBoardView.updateBoard();
	};
}
