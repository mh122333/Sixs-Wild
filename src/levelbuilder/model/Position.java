package levelbuilder.model;

/**
 * Position which has x, y location. 
 * @author Fuchen Chen
 *
 */
public class Position {
	/** x, horizontal location */
	protected int x; 
	
	/** y, vertical location */
	protected int y;
	
	/**
	 * Create a position using x, y location.
	 * @param x
	 * @param y
	 */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the x location of this position
	 * @return
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Get the y location of this position
	 * @return
	 */
	public int getY(){
		return this.y;
	}
	
	/**
	 * Override the default equals method such that if x and y are same, then two positions are same. 
	 * @return
	 */
	@Override
	public boolean equals(Object o){		
		if (o instanceof Position){
			Position p = (Position) o;
			return this.x == p.getX() && this.y == p.getY();
		}
		else{
			return false;
		}
	}
}
