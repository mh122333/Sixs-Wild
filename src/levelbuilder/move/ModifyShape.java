package levelbuilder.move;

import levelbuilder.model.Model;
import levelbuilder.model.Position;

/**
 * Modify the shape of the level using a position
 * @author Fuchen Chen
 *
 */
public class ModifyShape implements IMove{
	/** Position used to modify the shape */
	protected Position p;
	
	/** Model to be modified */
	protected Model m;
	
	/**
	 * Constructor
	 * @param p
	 * The position of the shape to be modified
	 * @param m
	 * The model to be modified
	 */
	public ModifyShape(Position p, Model m){
		this.p = p;
		this.m = m;
	}
	/**
	 * Modify the shape
	 */
	@Override
	public boolean execute() {
		// Toggle the square
		return updateShape(p);
	}

	/**
	 * Undo the modification
	 */
	@Override
	public boolean undo() {
		// Toggle the square
		return updateShape(p);
	}
	
	/**
	 * Given a position, the method toggle the shape only 
	 * when the position is not set as six start nor six end. 
	 * If the shape at current position is active, set it to inactive. Vice versa. 
	 * @param p
	 *  The position of the shape to be modified
	 * @return
	 * True if set shape successfully, False if not. 
	 */
	private boolean updateShape(Position p){
		// only change when the cell is not end or start pos
		if (!m.getSixEndPos()[p.getX()][p.getY()] && !m.getSixStartPos()[p.getX()][p.getY()]){
			if (m.getShape()[p.getX()][p.getY()]){
				m.getShape()[p.getX()][p.getY()] = false;
			}
			else{
				m.getShape()[p.getX()][p.getY()] = true;
			}
			
			return true;
		}
		
		return false;
	}

}
