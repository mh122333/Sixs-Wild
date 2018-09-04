package levelbuilder.undo;

import java.util.*;
import levelbuilder.move.*;

/**
 * Undo Manger which records all the moves that have been executed
 * @author Fuchen Chen
 *
 */
public class UndoManager {
	static UndoManager instance = null;
	
	/** Not visible outside of this package. */
	UndoManager() {
		
	}
	
	/** Undo stack. Moves are pushed onto here as they are undo. */
	Stack<IMove> undoStack = new Stack<IMove>();
	
	public static UndoManager instance() {
		if (instance == null) {
			instance = new UndoManager();
		}
		
		return instance;
	}

	/**
	 * Record the move which can be undone in the future.
	 * 
	 * @param move
	 */
	public void recordMove(IMove move) {
		undoStack.add(move);
	}
	
	/**
	 * Prepare for undo by getting last move.
	 */
	public IMove removeLastMove() {
		if (undoStack.isEmpty()) { return null; }
		return undoStack.pop();
	}

}
