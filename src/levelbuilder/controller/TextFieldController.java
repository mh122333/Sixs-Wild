package levelbuilder.controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import levelbuilder.redo.RedoManager;
import levelbuilder.undo.*;
import levelbuilder.view.ApplicationPanel;
import levelbuilder.move.*;
import levelbuilder.model.Model;
import levelbuilder.model.Value;

/**
 * Controller for all text field. Active when focus lost from the text field. 
 * @author CFC
 *
 */
public class TextFieldController implements FocusListener{
	/** Model to be modified */
	protected Model m;
	
	/** Value related to the text field */
	protected Value value;
	
	/** text field */
	protected JTextField tf;
	
	/** High level application panel */
	protected ApplicationPanel appPanel;
	
	/**
	 * Constructor
	 * @param m
	 * Model to be modified
	 * @param v
	 * Value related to the text field
	 * @param tf
	 * text field
	 * @param appPanel
	 * High level application panel
	 */
	public TextFieldController(Model m, Value v, JTextField tf, ApplicationPanel appPanel){
		this.m = m;
		this.value = v;
		this.tf = tf;
		this.appPanel = appPanel;
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	/**
	 * When focus lost, try to modify the value and record the move. 
	 * If fail to modify the value, leave the original value
	 */
	@Override
	public void focusLost(FocusEvent fe) {
		// Try to get the integer value in textfield
		try{
			int v = Integer.valueOf(tf.getText());
			// No move
			if (v == value.getValue()){
				return;
			}
			IMove move = new ModifyValue(m, tf, appPanel, value, v);
			
			// request move
			if (move.execute()) {
				UndoManager.instance().recordMove(move);
				RedoManager.instance().removeAllMove();
			}	
			else {
				tf.setText("" + value.getValue());
			}
		} catch (Exception e){
			// if not number leave the original value
			tf.setText("" + value.getValue());
		}
	}
	
}
