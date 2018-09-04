package levelbuilder.model;

/**
 * Value is an object which contains the minimum, maximum, and actual value.
 * @author Fuchen Chen
 *
 */
public class Value {
	/** Minimum value */
	protected int min;
	
	/** Actual value */
	protected int value;
	
	/** Maximum value */
	protected int max;
	
	/**
	 * Construct with min, current and max value
	 * @param min
	 * @param value
	 * @param max
	 */
	public Value(int min, int value, int max){
		this.min = min;
		this.max = max;
		this.value = value;
	}
	
	/**
	 * Construct with only min and max value and set the minimum value as actual value
	 * @param min
	 * @param max
	 */
	public Value (int min, int max) {
		this (min, min, max);
	}
	
	/**
	 * Get the actual value
	 * @return
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * Get the minimum value
	 * @return
	 */
	public int getMin(){
		return this.min;
	}
	
//	/**
//	 * Set the minimum value and change the actual value to minimum value
//	 * @param v
//	 * the minimum value desired
//	 */
//	public void setMin(int v){
//		this.min = v;
//		this.value = this.min;
//	}
	
	/**
	 * Set the actual value to the minimum value
	 */
	public void setMin(){
		this.value = this.min;
	}
	
	/**
	 * Get the maximum value
	 * @return
	 */
	public int getMax(){
		return this.max;
	}
	
	/**
	 * Determine if a value is within range
	 * @param v
	 * @return
	 * True if within range, False if not. 
	 */
	private boolean withinRange(int v){
		return v >= min && v <= max;
	}
	
	/**
	 * Set the actual value to a given value
	 * @param v
	 * Given integer value
	 * @return
	 * True if set successfully, False if failed. 
	 */
	public boolean setValue(int v){
		if(withinRange(v)){
			this.value = v;
			return true;
		}
		
		return false;
	}
}
