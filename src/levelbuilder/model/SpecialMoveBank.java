package levelbuilder.model;

/**
 * Special moves are stored in this class. 
 * Reset, 
 * Remove, 
 * Swap. 
 * @author Fuchen Chen
 *
 */
public class SpecialMoveBank {
	/** Value for reset move */
	protected Value reset;
	
	/** Value for remove move */
	protected Value remove;
	
	/** Value for swap move */
	protected Value swap;
	
	/**
	 * Construct the special move with: 
	 * Initial Value is 0.
	 * min: 0, max: 100.
	 * */
	public SpecialMoveBank(){
		this.reset = new Value(0, 100);
		this.remove = new Value(0, 100);
		this.swap = new Value(0, 100);
	}
	
	/**
	 * Reset all the special moves to minimum value
	 */
	public void reset(){
		reset.setMin();
		remove.setMin();
		swap.setMin();
	}
	
	/**
	 * Get the value for reset move
	 * @return
	 */
	public Value getReset(){
		return this.reset;
	}
	
	/**
	 * Get the value for remove move
	 * @return
	 */
	public Value getRemove(){
		return this.remove;
	}
	
	/**
	 * Get the value for swap move
	 * @return
	 */
	public Value getSwap(){
		return this.swap;
	}
}
