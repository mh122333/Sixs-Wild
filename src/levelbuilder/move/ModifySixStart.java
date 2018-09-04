package levelbuilder.move;

import levelbuilder.model.Model;
import levelbuilder.model.Position;

/**
 * Modify the six start position of the level using a position
 * @author Fuchen Chen
 *
 */
public class ModifySixStart implements IMove{
	/** Position used as six start position */
	protected Position p;
	
	/** Model to be modified */
	protected Model m;
	
	/**
	 * Constructor
	 * @param p
	 * The position of the six start to be modified
	 * @param m
	 * The model to be modified
	 */
	public ModifySixStart(Position p, Model m){
		this.p = p;
		this.m = m;
	}
	
	/**
	 * Modify the six start position
	 */
	@Override
	public boolean execute() {
		// Toggle the square
		return updateSixStart(p);
	}
	
	/**
	 * Undo the modification
	 */
	@Override
	public boolean undo() {
		// Toggle the square
		updateSixStart(p);
		return true;
	}
	
	/**
	 * Given a position, the method toggle the six start position only 
	 * when the position is not set as shape nor six end position. 
	 * If the six start position at current position is active, set it to inactive. Vice versa. 
	 * @param p
	 *  The position of the six start position to be modified
	 * @return
	 * True if set six start position successfully, False if not. 
	 */
	private boolean updateSixStart(Position p){
		// Only change when the cell is not set as end pos and shape
		if (!m.getSixEndPos()[p.getX()][p.getY()] && !m.getShape()[p.getX()][p.getY()]){
			// If already set as start position, unset it and shape
			if (m.getSixStartPos()[p.getX()][p.getY()]){
				m.getSixStartPos()[p.getX()][p.getY()] = false;
			}
			// If not set, set it and set the shape
			else{
				m.getSixStartPos()[p.getX()][p.getY()] = true;
			}
			
			return true;
		}
		
		return false;
	}

}
