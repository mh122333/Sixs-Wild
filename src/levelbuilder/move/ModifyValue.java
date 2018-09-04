package levelbuilder.move;

import javax.swing.JTextField;

import levelbuilder.model.*;
import levelbuilder.view.ApplicationPanel;
import levelbuilder.view.DetailSettingView;

/**
 * Modify the model value
 * @author Fuchen Chen
 *
 */
public class ModifyValue implements IMove{
	/** Model to be modified */
	protected Model m;
	
	/** Textfield that is related to such value */
	protected JTextField tf;
	
	/** The high level application panel */
	protected ApplicationPanel appPanel;
	
	/** Value to be modified */
	protected Value value;
	
	/** The original value of the Value */
	protected int oldValue;
	
	/** The new value */
	protected int newValue;
	
	/**
	 * Constructor for the class
	 * @param m
	 * Model to be modified
	 * @param tf
	 * Value related Textfield
	 * @param appPanel
	 * High level application panel
	 * @param old
	 * Value to be modified
	 * @param n
	 * New value
	 */
	public ModifyValue(Model m, JTextField tf, ApplicationPanel appPanel, Value old, int n){
		this.m =  m;
		this.tf = tf;
		this.appPanel = appPanel;
		this.value = old;
		this.oldValue = value.getValue();
		this.newValue = n;
	}
	
	/**
	 * Modify the value. If not valid, undo immediately
	 * @return
	 * True if modification is successful, False if not.
	 */
	public boolean execute(){
		boolean pass = true;
		// Set it first
		pass = pass && value.setValue(newValue);
		// If not valid, return false;
		if (!valid()){
			undo();
			pass = false;
		}
		
		return pass;		
	}
	
	/**
	 * Undo the modification
	 */
	public boolean undo(){
		value.setValue(oldValue);
		return true;
	}
	
	/**
	 * Method that verify if the modification is legal.
	 * For number and bonus frequency, make sure the sum is not over 100  
	 * @return
	 * True if valid, False if not.
	 */
	private boolean valid(){
		// If related to frequency, alternate the max value
		String type = appPanel.getDetailSettingView().getTFType(tf);
		int sumNumF = m.getFrequency().getNumFrequencySum();
		int sumBonusF =  m.getFrequency().getBonusFrequencySum();
		
		switch(type){
			case DetailSettingView.ONE_TF:
			case DetailSettingView.TWO_TF:
			case DetailSettingView.THREE_TF:
			case DetailSettingView.FOUR_TF:
			case DetailSettingView.FIVE_TF:
			case DetailSettingView.SIX_TF:
				if (sumNumF > 100){
					return false;
				}
				break;
			case DetailSettingView.XTWO_TF:
			case DetailSettingView.XTHREE_TF:
				if (sumBonusF > 100){
					return false;
				}
				break;
		}
		
		return true;
	}
}
