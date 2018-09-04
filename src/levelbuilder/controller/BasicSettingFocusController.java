package levelbuilder.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import levelbuilder.view.BasicSettingView;

/**
 * The controller which makes the basic setting view focusable. 
 * @author Fuchen Chen
 *
 */
public class BasicSettingFocusController extends MouseAdapter{
	/** The basic setting view which needs to be focusable */
	protected  BasicSettingView boardView;
	
	/**
	 * Constructor
	 * @param b
	 * The basic setting view which needs to be focusable
	 */
	public BasicSettingFocusController(BasicSettingView b){
		this.boardView = b;
	}
	
	/**
	 * Upon mouse clicked, set the basic setting view focused.
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		boardView.requestFocusInWindow();
	}
}
