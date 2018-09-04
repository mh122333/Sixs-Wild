package levelbuilder.move;

import levelbuilder.model.Model;
import levelbuilder.model.Position;

/**
 * Modify the six end position of the level using a position
 * @author Fuchen Chen
 *
 */
public class ModifySixEnd implements IMove{
	/** Position used as six end position */
	protected Position p;
	
	/** Model to be modified */
	protected Model m;
	
	/**
	 * Constructor
	 * @param p
	 * The position of the six end to be modified
	 * @param m
	 * The model to be modified
	 */
	public ModifySixEnd(Position p, Model m){
		this.p = p;
		this.m = m;
	}
	
	/**
	 * Modify the six end position
	 */
	@Override
	public boolean execute() {
		// Toggle the square
		return updateSixEnd(p);
	}

	/**
	 * Undo the modification
	 */
	@Override
	public boolean undo() {
		// Toggle the square
		return updateSixEnd(p);
	}
	
	/**
	 * Given a position, the method toggle the six end position only 
	 * when the position is not set as shape nor six start position. 
	 * If the six end position at current position is active, set it to inactive. Vice versa. 
	 * @param p
	 *  The position of the six end position to be modified
	 * @return
	 * True if set six end position successfully, False if not. 
	 */
	private boolean updateSixEnd(Position p){
		// Only change when the cell is not set as start pos and shape
		if (!m.getSixStartPos()[p.getX()][p.getY()] && !m.getShape()[p.getX()][p.getY()]){
			// If already set as end position, unset it and shape
			if (m.getSixEndPos()[p.getX()][p.getY()]){
				m.getSixEndPos()[p.getX()][p.getY()] = false;
			}
			// If not set, set it and set the shape
			else{
				m.getSixEndPos()[p.getX()][p.getY()] = true;
			}
			
			return true;
		}
		
		return false;
	}

}
