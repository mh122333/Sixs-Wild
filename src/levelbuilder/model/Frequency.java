package levelbuilder.model;

/**
 * Frequency class that stores all the frequency related values
 * @author Fuchen Chen
 *
 */
public class Frequency {
	/** 
	 * Frequency of the numbers (1-6). 
	 * numFrequency[n-1] is probability for number n. 
	 * */
	protected Value[] numFrequency;
	
	/** 
	 * bonusFrequency[0] is probability for x2. 
	 * bonusFrequency[1] is probability for x3. 
	 */
	protected Value[] bonusFrequency;
	
	/** 
	 * Constructor for the frequency. 
	 * Initial values are all set to zero. 
	 * Min: 0, Max: 99.
	 * */
	public Frequency(){
		this.numFrequency = new Value[6];
		for (int i = 0; i < 6; i ++){
			numFrequency[i] = new Value(0, 99);
		}
		
		this.bonusFrequency = new Value[2];
		bonusFrequency[0] = new Value(0, 99);
		bonusFrequency[1] = new Value(0, 99);
	}
	
	/**
	 * Reset the frequency to their minimum value. 
	 */
	public void reset(){
		for (int i = 0; i < 6; i ++){
			numFrequency[i].setMin();
		}
		bonusFrequency[0].setMin();
		bonusFrequency[1].setMin();
	}
	
	/**	
	 * Get the frequecy of a specific number
	 * @param num
	 * the specific number from 1 to 6
	 * @return 
	 * value which contains min, max, and actual value
	 */
	public Value getNumFrequency(int num){
		return this.numFrequency[num-1];		
	}
	
	/**
	 * Get the value of x2 bonus frequency
	 * @return 
	 * value which contains min, max, and actual value
	 */
	public Value getX2Frequency(){
		return this.bonusFrequency[0];		
	}
	
	/**
	 * Get the value of x2 bonus frequency
	 * @return 
	 * value which contains min, max, and actual value
	 */
	public Value getX3Frequency(){
		return this.bonusFrequency[1];		
	}
	
	/**
	 * Get the sum of the number frequency
	 * @return
	 * the sum of the number frequency
	 */
	public int getNumFrequencySum(){
		int sum = 0;
		for (int i = 0; i < 6; i ++){
			sum += numFrequency[i].getValue();
		}
		
		return sum;
	}
	
	
	/**
	 * Get the sum of the bonus frequency
	 * @return
	 * the sum of the bonus frequency
	 */
	public int getBonusFrequencySum(){		
		return bonusFrequency[0].getValue() + bonusFrequency[1].getValue();
	}
}
