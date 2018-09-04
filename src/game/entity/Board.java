package game.entity;

import levelbuilder.model.Frequency;
import levelbuilder.model.Position;
import levelbuilder.model.SpecialMoveBank;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The class with attributes which variables change during the game.
 * @author Yo Karita
 *
 */
public abstract class Board{
	protected int currentScore;
	
	protected Cell[][] cells = new Cell[9][9];
	
	protected int resetMove;
	
	protected int removeMove;
	
	protected int swapMove;
	
	protected int currentMove;
	
	public static final int NormalMove = 0;
	
	public static final int RemoveMove = 1;
	
	public static final int SwapMove = 2;

	/**
	 * Constructor for Board class.
	 * @param shape the shape of the board.
	 * @param reset number of times reset moves allowed.
	 * @param remove number of times remove moves allowed.
	 * @param swap number of times swap moves allowed
	 */
	public Board(boolean[][] shape, int reset, int remove, int swap){
		this.currentScore = 0;
		this.currentMove = NormalMove;
		this.resetMove = reset;
		this.removeMove = remove;
		this.swapMove = swap;
		
		// Create cells based on shape
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j ++){
                if(shape[i][j]){
                    cells[i][j] = new Cell(new Position(i,j), true);
                }
                else {
                    cells[i][j] = new Cell(new Position(i,j), false);
                }
            }
        }
	}
	/**
	 * The method to reset the limit of a move
	 * @param limit the new limit
	 *
	 */
	public abstract void resetLimit(int limit);

	/**
	 * The method to get the limit of a move or time
	 * @return int How many moves or time left
	 *
	 */
	public abstract int getLimitLeft();

	/**
	 * Decrement the normal move limit by 1
	 *@return boolean if the move was decreased successfully
	 *
	 */
	public abstract boolean decrementMove();

	/**
	 * Decrement the normal move limit by 1
	 *
	 *
	 */
	public abstract int getMoveLeft();

	/**
	 * Get currentMove
	 * @return int currentMove
	 *
	 */
	public int getCurrentMove(){
		return this.currentMove;
	}

	/**
	 * Set the current move
	 * @param i the number to be set
	 *
	 */
	public void setCurrentMove(int i){
		this.currentMove = i;
	}
	/**
	 * Reset the special move limits to the original
	 * @param s the sets of limit numbers
	 *
	 */
	public void resetSpecialMoveLeft(SpecialMoveBank s){
		this.resetMove = s.getReset().getValue();
		this.removeMove = s.getRemove().getValue();
		this.swapMove = s.getSwap().getValue();
	}

	/**
	 * Reset the current score to 0
	 *
	 *
	 */
	public void resetCurrentScore(){
		this.currentScore = 0;
	}
	
	// Return null if not eliminated mode
	/**
	 * Checks which tiles are eliminated
	 * @return boolean[][] true means the tile is eliminated, false means it's not. or null if it's not elimination
	 *
	 */
	public abstract boolean[][] getEliminatedCells();

	public boolean generateSquares(Frequency frequency){
		boolean returnValue;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j ++){
				if(cells[i][j].getValid()){
					returnValue = cells[i][j].generateSquares(frequency);
					if(!returnValue) return false;
				}

			}
		}
		return true;
	}

	/**
	 * The method to reset the squares
	 * @param frequency the frequency of the tiles of the game
	 * @return boolean if the squares are reset
	 *
	 */
	public boolean resetSquares(Frequency frequency){
		return generateSquares(frequency);
	}

	/**
	 * Get currentScore
	 * @return int currentScore
	 *
	 */
	public int getCurrentScore() {
		return currentScore;
	}

	/**
	 * Increase the current score by the move score
	 * @param moveScore the score made by the last move
	 *
	 */
	public void addScore(int moveScore){
		currentScore += moveScore;
	}

	/**
	 * Get cells
	 * @return Cell[][] cells
	 *
	 */
	public Cell[][] getCells() {
		return cells;
	}

	/**
	 * Get resetMove
	 * @return int resetMove
	 *
	 */
	public int getResetMove() {
		return this.resetMove;
	}

	/**
	 * Get removeMove
	 * @return int removeMove
	 *
	 */
	public int getRemoveMove() {
		return this.removeMove;
	}

	/**
	 * Get swapMove
	 * @return int swapMove
	 *
	 */
	public int getSwapMove() {
		return this.swapMove;
	}

	/**
	 * Set a cell selected or not
	 * @param p the position of the cell to be changed
	 * @param b true is selected, false is not
	 * @return Cell the cell located at p
	 *
	 */
	public boolean setCellSelected(Position p, boolean b){
		if (0<= p.getX() && p.getX()< 9 && 0 <= p.getY() && p.getY() < 9){
			cells[p.getX()][p.getY()].setSelected(b);
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * Get a cell
	 * @param p the position of the cell to be gotten
	 * @return Cell the cell located at p
	 *
	 */
	public Cell getCell(Position p){
		try{
			return cells[p.getX()][p.getY()];
		} catch (Exception e){
			System.out.println("Cell not found");
			return null;
		}
	}

	// In default, not six start and end is set
	/**
	 * Set a 6 tile
	 * @param sixStartPos the location of start position of a 6 tile
	 * @param sixEndPos the location of end position of the 6 tile
	 * @param frequency frequency of tiles of the game
	 * @return boolean if the 6 tile was placed successfully
	 *
	 */
	public boolean set6(boolean[][] sixStartPos, boolean[][] sixEndPos, Frequency frequency){
		return false;
	}

	/**
	 * Decrease the remove move limit by 1
	 * @return boolean if the remove move limit was decreased successfully
	 *
	 */
	public boolean decrementRemoveMove(){
		if (this.removeMove > 0){
			this.removeMove --;
			return true;
		}
		return false;
	}
	/**
	 * Decrease the swap move limit by 1
	 * @return boolean if the swap move limit was decreased successfully
	 *
	 */
	public boolean decrementSwapMove(){
		if (this.swapMove > 0){
			this.swapMove --;
			return true;
		}
		return false;
	}

	/**
	 * Decrease the reset move limit by 1
	 * @return boolean if the reset move limit was decreased successfully
	 *
	 */
	public boolean decrementResetMove(){
		if (this.resetMove > 0){
			this.resetMove --;
			return true;
		}
		return false;
	}

}