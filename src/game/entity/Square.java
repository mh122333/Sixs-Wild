package game.entity;
/**
 * The class of a Square. Squares changes during the game.
 * @author Yo Karita
 *
 */
public class Square {
	protected int number;
	
	protected int bonus;
	/**
	 * Constructor for Square class.
	 * @param number_ the shape of the board.
	 * @param bonus_ number of times reset moves allowed.
	 */
	public Square(int number_, int bonus_){
		this.number = number_;
		this.bonus = bonus_;
	}
	/**
	 * Set number to n
	 * @param n new number
	 *
	 */
	public void setNumber(int n){
		this.number = n;
	}

	/**
	 * Set bonus to n
	 * @param n new bonus value
	 *
	 */
	public void setBonus(int n){
		this.bonus = n;
	}

	/**
	 * Change the number and the square of
	 * @param s Square object which has the number and bonus to set
	 *
	 */
	public void setSquare(Square s){
		this.number = s.getNumber();
		this.bonus = s.getBonus();
	}
	/**
	 * Get number
	 * @return int number
	 *
	 */
	public int getNumber(){
		return this.number;
	}

	/**
	 * Get bonus
	 * @return int bonus
	 *
	 */
	public int getBonus(){
		return this.bonus;
	}
}
