package levelbuilder.controller;

import java.awt.event.ActionEvent;

import levelbuilder.model.Model;
import levelbuilder.view.ApplicationPanel;

/**
 * Controller for the board shape button
 * @author Fuchen Chen
 *
 */
public class BoardShapeButtonController extends ButtonController{
	/**
	 * Constructor
	 */
	public BoardShapeButtonController(Model m, ApplicationPanel appPanel){
		super(m, appPanel);
	}
	
	/**
	 * Upon action performed, change the model edit status to shape
	 */
	public void actionPerformed(ActionEvent e){
		model.setEditStatus(Model.ShapeEdit);
	}
}
