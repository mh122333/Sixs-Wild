package levelbuilder.move;
/**
 * Interface for modify class
 * @author Fuchen Chen
 *
 */
public interface IMove {
	/**
	 * Execute a move and return true on success
	 */
	public boolean execute();
	
	/**
	 * Undo move and return true on success
	 */
	public boolean undo();
}

