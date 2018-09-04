import game.view.Application;

/** Class to simply launch the GUI. */
public class game {
	/** Launch GUI by installing window controller on exit. */ 
	public static void main (String args[]) {
		
		final Application app = new Application();
		
		app.setVisible(true);
	}
}
