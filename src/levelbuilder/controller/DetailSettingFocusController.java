package levelbuilder.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import levelbuilder.view.DetailSettingView;

/**
 * The controller which makes the detail setting view focusable. 
 * @author Fuchen Chen
 *
 */
public class DetailSettingFocusController extends MouseAdapter{
	/** The detail setting view which needs to be focusable */
	protected  DetailSettingView detailView;
	
	/**
	 * Constructor
	 * @param b
	 * The detail setting view which needs to be focusable
	 */
	public DetailSettingFocusController(DetailSettingView d){
		this.detailView = d;
	}
	
	/**
	 * Upon mouse clicked, set the detail setting view focused.
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		//System.out.println("clicked");
		detailView.requestFocusInWindow();
	}
}
