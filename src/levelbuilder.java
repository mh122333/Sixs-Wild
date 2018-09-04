import javax.swing.JFrame;

import levelbuilder.model.*;
import levelbuilder.view.Application;

/** Class to simply launch the GUI. */
public class levelbuilder {
	
	/** Launch GUI by installing window controller on exit. */ 
	public static void main (String args[]) {
		Model model = new Model();
		
		final Application app = new Application(model);
		
		app.setVisible(true);
	}
}