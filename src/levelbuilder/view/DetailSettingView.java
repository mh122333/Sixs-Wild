package levelbuilder.view;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;

import levelbuilder.controller.TextFieldController;
import levelbuilder.model.*;

/**
 * Detail setting view for all the settings of the model excep level and type
 * Implements ISettingView and has refresh method. 
 * @author Weijia Tao
 *
 */
public class DetailSettingView extends JPanel implements ISettingView{
	/** Model to be modified */
	protected Model model;
	
	// Text fields
	protected JTextField moveTF;
	protected JTextField timeTF;
	protected JTextField scoreTF;
	protected JTextField oneTF;
	protected JTextField twoTF;
	protected JTextField threeTF;
	protected JTextField fourTF;
	protected JTextField fiveTF;
	protected JTextField sixTF;
	protected JTextField xTwoTF;
	protected JTextField xThreeTF;
	protected JTextField resetTF;
	protected JTextField removeTF;
	protected JTextField swapTF;
	
	// Labels
	protected JLabel moveL;
	protected JLabel timeL;
	protected JLabel scoreL;
	protected JLabel numFreqL;
	protected JLabel bonusFreqL;
	protected JLabel specialMoveL;
	protected JLabel oneL;
	protected JLabel twoL;
	protected JLabel threeL;
	protected JLabel fourL;
	protected JLabel fiveL;
	protected JLabel sixL;
	protected JLabel xTwoL;
	protected JLabel xThreeL;
	protected JLabel resetL;
	protected JLabel removeL;
	protected JLabel swapL;
	
	/** Save button */
	protected JButton saveButton;
	
	public static final String ONE_TF = "one";
	public static final String TWO_TF = "two";
	public static final String THREE_TF = "three";
	public static final String FOUR_TF = "four";
	public static final String FIVE_TF = "five";
	public static final String SIX_TF = "six";
	public static final String XTWO_TF = "xtwo";
	public static final String XTHREE_TF = "xthree";
	public static final String OHTER_TF = "other";
	
	/**
	 * Initialize all the components
	 * @param m
	 * Model to be modified
	 */
	public DetailSettingView(Model m) {
		this.model = m;
		
		setPreferredSize(new Dimension(450, 400));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
		
		moveL = new JLabel("Move");
		moveL.setBounds(100, 80, 30, 15);
		add(moveL);
		
		scoreL = new JLabel("Goal Score");
		scoreL.setBounds(100, 110, 80, 15);
		add(scoreL);
		
		numFreqL = new JLabel("Number Frequency (Sum = 100%)");
		numFreqL.setBounds(100, 140, 240, 15);
		add(numFreqL);
		
		bonusFreqL = new JLabel("Bonus Frequency (Sum <= 100%)");
		bonusFreqL.setBounds(100, 200, 271, 15);
		add(bonusFreqL);
		
		specialMoveL = new JLabel("Special Move");
		specialMoveL.setBounds(100, 260, 100, 15);
		add(specialMoveL);
		
		moveTF = new JTextField();
		moveTF.setBounds(135, 75, 66, 21);
		add(moveTF);
		moveTF.setColumns(10);
		
		timeTF = new JTextField();
		timeTF.setBounds(240, 75, 66, 21);
		add(timeTF);
		timeTF.setColumns(10);
		
		scoreTF = new JTextField();
		scoreTF.setBounds(170, 105, 66, 21);
		add(scoreTF);
		scoreTF.setColumns(10);
		
		// Number frewquency
		oneL = new JLabel("1");
		oneL.setBounds(100, 170, 10, 15);
		add(oneL);
		
		oneTF = new JTextField();
		oneTF.setBounds(110, 165, 20, 21);
		add(oneTF);
		oneTF.setColumns(2);
		
		twoTF = new JTextField();
		twoTF.setColumns(2);
		twoTF.setBounds(150, 165, 20, 21);
		add(twoTF);
		
		twoL = new JLabel("2");
		twoL.setBounds(140, 170, 10, 15);
		add(twoL);
		
		threeTF = new JTextField();
		threeTF.setColumns(2);
		threeTF.setBounds(190, 165, 20, 21);
		add(threeTF);
		
		threeL = new JLabel("3");
		threeL.setBounds(180, 170, 10, 15);
		add(threeL);
		
		fourTF = new JTextField();
		fourTF.setColumns(2);
		fourTF.setBounds(230, 165, 20, 21);
		add(fourTF);
		
		fourL = new JLabel("4");
		fourL.setBounds(220, 170, 10, 15);
		add(fourL);
		
		fiveTF = new JTextField();
		fiveTF.setColumns(2);
		fiveTF.setBounds(270, 165, 20, 21);
		add(fiveTF);
		
		fiveL = new JLabel("5");
		fiveL.setBounds(260, 170, 10, 15);
		add(fiveL);
		
		sixTF = new JTextField();
		sixTF.setColumns(2);
		sixTF.setBounds(310, 165, 20, 21);
		add(sixTF);
		
		sixL = new JLabel("6");
		sixL.setBounds(300, 170, 10, 15);
		add(sixL);
		
		// Bonus Frequency
		xTwoL = new JLabel("x2");
		xTwoL.setBounds(100, 230, 15, 15);
		add(xTwoL);
		
		xTwoTF = new JTextField();
		xTwoTF.setColumns(2);
		xTwoTF.setBounds(120, 225, 20, 21);
		add(xTwoTF);
		
		xThreeTF = new JTextField();
		xThreeTF.setColumns(2);
		xThreeTF.setBounds(190, 225, 20, 21);
		add(xThreeTF);
		
		xThreeL = new JLabel("x3");
		xThreeL.setBounds(170, 230, 15, 15);
		add(xThreeL);
		
		// Special Move
		resetL = new JLabel("Reset");
		resetL.setBounds(100, 290, 40, 15);
		add(resetL);
		
		resetTF = new JTextField();
		resetTF.setColumns(2);
		resetTF.setBounds(140, 285, 20, 21);
		add(resetTF);
		
		removeL = new JLabel("Remove");
		removeL.setBounds(180, 290, 50, 15);
		add(removeL);
		
		removeTF = new JTextField();
		removeTF.setColumns(2);
		removeTF.setBounds(235, 285, 20, 21);
		add(removeTF);
		
		swapL = new JLabel("Swap");
		swapL.setBounds(270, 290, 40, 15);
		add(swapL);
		
		swapTF = new JTextField();
		swapTF.setColumns(2);
		swapTF.setBounds(305, 285, 20, 21);
		add(swapTF);
		
		// Save Button
		saveButton = new JButton("Save");
		saveButton.setBounds(357, 377, 93, 23);
		add(saveButton);
		
		timeL = new JLabel("Time");
		timeL.setBounds(210, 80, 54, 15);
		add(timeL);
	}
	
	public JTextField getMoveTF(){
		return this.moveTF;
	}
	
	public JTextField getTimeTF(){
		return this.timeTF;
	}
	
	public JTextField getScoreTF(){
		return this.scoreTF;
	}
	
	public JTextField getOneTF(){
		return this.oneTF;
	}
	
	public JTextField getTwoTF(){
		return this.twoTF;
	}
	
	public JTextField getThreeTF(){
		return this.threeTF;
	}
	
	public JTextField getFourTF(){
		return this.fourTF;
	}
	
	public JTextField getFiveTF(){
		return this.fiveTF;
	}
	
	public JTextField getSixTF(){
		return this.sixTF;
	}
	
	public JTextField getXTwoTF(){
		return this.xTwoTF;
	}
	
	public JTextField getXThreeTF(){
		return this.xThreeTF;
	}
	
	public JTextField getResetTF(){
		return this.resetTF;
	}
	
	public JTextField getRemoveTF(){
		return this.removeTF;
	}
	
	public JTextField getSwapTF(){
		return this.swapTF;
	}
	
	public JButton getSaveButton(){
		return saveButton;
	}
	
	/**
	 * Determine what setting is the given text field related
	 * @param tf
	 * A text field 
	 * @return
	 * A string which represents the setting this text field is related
	 */
	public String getTFType(JTextField tf){
		if (tf.equals(oneTF)){
			return ONE_TF;
		}
		else if (tf.equals(twoTF)){
			return TWO_TF;
		}
		else if (tf.equals(threeTF)){
			return THREE_TF;
		}
		else if (tf.equals(fourTF)){
			return FOUR_TF;
		}
		else if (tf.equals(fiveTF)){
			return FIVE_TF;
		}
		else if (tf.equals(sixTF)){
			return SIX_TF;
		}
		else if (tf.equals(xTwoTF)){
			return XTWO_TF;
		}
		else if (tf.equals(xThreeTF)){
			return XTHREE_TF;
		}
		else{
			return OHTER_TF;
		}
	}
	
	/**
	 * Refresh all the text field based on model
	 */
	public void refresh(){
		getMoveTF().setText("" + model.getMove().getValue());
		getTimeTF().setText("" + model.getTime().getValue());
		getScoreTF().setText("" + model.getGoalScore().getValue());
		getOneTF().setText("" + model.getFrequency().getNumFrequency(1).getValue());
		getTwoTF().setText("" + model.getFrequency().getNumFrequency(2).getValue());
		getThreeTF().setText("" + model.getFrequency().getNumFrequency(3).getValue());
		getFourTF().setText("" + model.getFrequency().getNumFrequency(4).getValue());
		getFiveTF().setText("" + model.getFrequency().getNumFrequency(5).getValue());
		getSixTF().setText("" + model.getFrequency().getNumFrequency(6).getValue());
		getXTwoTF().setText("" + model.getFrequency().getX2Frequency().getValue());
		getXThreeTF().setText("" + model.getFrequency().getX3Frequency().getValue());
		getResetTF().setText("" + model.getSpecialMoveBank().getReset().getValue());
		getRemoveTF().setText("" + model.getSpecialMoveBank().getRemove().getValue());
		getSwapTF().setText("" + model.getSpecialMoveBank().getSwap().getValue());
		
	}
}
