package game.entity;

import levelbuilder.model.Position;
import levelbuilder.model.Frequency;
import java.util.Random;

/**
 * The class of a cell. Cells don't move during a game.
 * @author Yo Karita
 *
 */
public class Cell {
	protected Square square;
	
	protected Position position;
	
	protected boolean selected;
	
	protected boolean validCell;
	
	protected boolean endSix;

	/**
	 * Constructor for Board class.
	 * @param position_ the position of the cell
	 * @param validCell_ if the cell is valid or not
	 */
	public Cell(Position position_, boolean validCell_){
		this.square = null;
		this.position = position_;
		this.selected = false;
		this.validCell = validCell_;
		this.endSix = false;
	}

	/**
	 * Set validCell
	 * @param b if it's a valid cell
	 *
	 */
	public void setValid(boolean b){
		this.validCell = b;
	}

	/**
	 * Set endSix
	 * @param b if it's a end pos
	 *
	 */
	public void setEndSix(boolean b){
		this.endSix = b;
	}

	/**
	 * Set selected
	 * @param b if it is selected
	 *
	 */
	public void setSelected(boolean b){
		this.selected = b;
	}

	/**
	 * Checks if the cell is selected
	 * @return boolean true if it is selected
	 *
	 */
	public boolean getSelected(){
		return this.selected;
	}

	/**
	 * Checks if the cell is end pos of a 6 tile
	 * @return boolean true if it is end pos
	 *
	 */
	public boolean getEndSix(){
		return endSix;
	}

	/**
	 * Get square
	 * @return Square square
	 *
	 */
	public Square getSquare(){
		return this.square;
	}

	/**
	 * Get valid
	 * @return boolean valid
	 *
	 */
	public boolean getValid(){
		return this.validCell;
	}

	/**
	 * Set square
	 * @param s a square
	 *
	 */
	public void setSquare(Square s){
		this.square = s;
	}
	
//	protected boolean generateSquares(Frequency frequency){
//		int number = 0;
//		int bonus = 0;
//		
//		//for num
//		int sum = 0;
//		int[] frequencyCut = new int[6];
//		
//		// Calculate total value
//		for (int i = 0; i < 6; i ++){
//			sum += frequency.getNumFrequency(i+1).getValue();
//		}
//		
//		// Calculate each frequency cut
//		int totalFrequency = 0;
//		for (int i = 0; i < 6; i ++){
//			frequencyCut[i] = (int) (frequency.getNumFrequency(i+1).getValue()*100/sum) + totalFrequency;
//			totalFrequency = frequencyCut[i];
//			// System.out.println("Frequecy cut is: " + frequencyCut[i]);
//		}
//		
//		Random sampler = new Random();
//		int randomNumber = sampler.nextInt(totalFrequency);
//				
//		if (0 <= randomNumber && randomNumber < frequencyCut[0]){
//			number = 1;
//		}
//		else if (frequencyCut[0] <= randomNumber && randomNumber < frequencyCut[1]){
//			number = 2;
//		}
//		else if (frequencyCut[1] <= randomNumber && randomNumber < frequencyCut[2]){
//			number = 3;
//		}
//		else if (frequencyCut[2] <= randomNumber && randomNumber < frequencyCut[3]){
//			number = 4;
//		}
//		else if (frequencyCut[3] <= randomNumber && randomNumber < frequencyCut[4]){
//			number = 5;
//		}
//		else if (frequencyCut[4] <= randomNumber && randomNumber < frequencyCut[5]){
//			number = 6;
//		}
//		else{
//			System.out.println("Cannot calculate number for square");
//		}
//		//for Bonus
//		if (number != 6){
//			int bonusSum = 99 + frequency.getX2Frequency().getValue() + frequency.getX3Frequency().getValue();
//			int X1Frequency = (int) 99*100/bonusSum;
//			int X2Frequency = (int) frequency.getX2Frequency().getValue()*100/bonusSum+X1Frequency;
//			int X3Frequency = (int) frequency.getX3Frequency().getValue()*100/bonusSum+X2Frequency;
//			
//			Random sampler2 = new Random();
//			int randomNumber2 = sampler2.nextInt(X3Frequency);
//			
//			if (0 <= randomNumber2 && randomNumber2 < X1Frequency){
//				bonus = 1;
//			}
//			else if (X1Frequency <= randomNumber2 && randomNumber2 < X2Frequency){
//				bonus = 2;
//			}
//			else if (X2Frequency <= randomNumber2 && randomNumber2 < X3Frequency){
//				bonus = 3;
//			}
//			else{
//				System.out.println("Cannot calculate bonus for square");
//			}
//		}
//		else{
//			bonus = 1;
//		}
//		this.square= new Square(number, bonus);
//		return true;
//	}

	/**
	 * Generates a square
	 * @param frequency  the probabilities of tiles
	 * @return boolean if the square is generated successfully
	 */
	protected boolean generateSquares(Frequency frequency){
		int number = 0;
		int bonus = 0;
		
		//for num
		int sum = 0;
		int[] frequencyCut = new int[6];
		
		// Calculate total value
		for (int i = 0; i < 6; i ++){
			sum += frequency.getNumFrequency(i+1).getValue();
		}
		
		if (sum != 100){
			System.out.println("Number frequency setting not correct, change to default frequency");
			frequency.getNumFrequency(1).setValue(20);
			frequency.getNumFrequency(2).setValue(20);
			frequency.getNumFrequency(3).setValue(20);
			frequency.getNumFrequency(4).setValue(20);
			frequency.getNumFrequency(5).setValue(20);
			frequency.getNumFrequency(6).setValue(0);
		}
		
		// Calculate each frequency cut
		int totalFrequency = 0;
		for (int i = 0; i < 6; i ++){
			frequencyCut[i] = (int) (frequency.getNumFrequency(i+1).getValue()) + totalFrequency;
			totalFrequency = frequencyCut[i];
			// System.out.println("Frequecy cut is: " + frequencyCut[i]);
		}
		
		Random sampler = new Random();
		int randomNumber = sampler.nextInt(totalFrequency);
				
		if (0 <= randomNumber && randomNumber < frequencyCut[0]){
			number = 1;
		}
		else if (frequencyCut[0] <= randomNumber && randomNumber < frequencyCut[1]){
			number = 2;
		}
		else if (frequencyCut[1] <= randomNumber && randomNumber < frequencyCut[2]){
			number = 3;
		}
		else if (frequencyCut[2] <= randomNumber && randomNumber < frequencyCut[3]){
			number = 4;
		}
		else if (frequencyCut[3] <= randomNumber && randomNumber < frequencyCut[4]){
			number = 5;
		}
		else if (frequencyCut[4] <= randomNumber && randomNumber < frequencyCut[5]){
			number = 6;
		}
		else{
			System.out.println("Cannot calculate number for square");
		}
		//for Bonus
		if (number != 6){
			int bonusSum = frequency.getX2Frequency().getValue() + frequency.getX3Frequency().getValue();
			if (bonusSum > 100){
				System.out.println("Bonus frequency setting not correct, change to default frequency");
				frequency.getX2Frequency().setValue(30);
				frequency.getX3Frequency().setValue(30);
			}
			int X1Frequency = (int) 100-bonusSum;
			int X2Frequency = (int) frequency.getX2Frequency().getValue()+X1Frequency;
			int X3Frequency = (int) frequency.getX3Frequency().getValue()+X2Frequency;
			
			Random sampler2 = new Random();
			int randomNumber2 = sampler2.nextInt(X3Frequency);
			
			if (0 <= randomNumber2 && randomNumber2 < X1Frequency){
				bonus = 1;
			}
			else if (X1Frequency <= randomNumber2 && randomNumber2 < X2Frequency){
				bonus = 2;
			}
			else if (X2Frequency <= randomNumber2 && randomNumber2 < X3Frequency){
				bonus = 3;
			}
			else{
				System.out.println("Cannot calculate bonus for square");
			}
		}
		else{
			bonus = 1;
		}
		this.square= new Square(number, bonus);
		return true;
	}

}