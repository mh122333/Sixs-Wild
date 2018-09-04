package levelbuilder.redo;

import java.util.*;
import levelbuilder.move.*;

/**
 * Redo Manager which records all the moves that has been undone. 
 * @author Fuchen Chen
 *
 */
public class RedoManager {
	static RedoManager instance = null;
	
	/** Not visible outside of this package. */
	RedoManager() {
		
	}
	
	/** Redo stack. Moves are pushed onto here as they are redoable. */
	Stack<IMove> redoStack = new Stack<IMove>();
	
	public static RedoManager instance() {
		if (instance == null) {
			instance = new RedoManager();
		}
		
		return instance;
	}

	/**
	 * Record the move which can be redone in the future.
	 * 
	 * @param move
	 */
	public void recordMove(IMove move) {
		redoStack.add(move);
	}
	
	/**
	 * Prepare for redo by getting last move.
	 */
	public IMove removeLastMove() {
		if (redoStack.isEmpty()) { return null; }
		return redoStack.pop();
	}
	
	public void removeAllMove() {
		redoStack.removeAllElements();
	}

}
