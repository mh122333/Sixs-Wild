package game.entity;

import levelbuilder.model.Frequency;
import levelbuilder.model.Position;
import levelbuilder.model.SpecialMoveBank;

import java.util.Arrays;
/**
 * The class of a board for elimination
 * @author Yo Karita
 *
 */
public class EliminationBoard extends Board{
    protected int moveCnt;
    protected boolean[][] eliminatedCells;  //2D array. true: eliminated, false: not eliminated yet

    /**
     * Constructor for EliminationBoard class.
     * @param shape the shape of the board.
     * @param reset number of times reset moves allowed.
     * @param remove number of times remove moves allowed.
     * @param swap number of times swap moves allowed
     */
    public EliminationBoard(boolean[][] shape, int moveCnt_, int reset, int remove, int swap){
        super(shape, reset, remove, swap);

        eliminatedCells = new boolean[9][9];
        
        for (int row = 0; row < 9; row ++){
        	for (int col = 0; col < 9; col ++){
        		eliminatedCells[row][col] = false;
        	}
        }
        
        this.moveCnt = moveCnt_;
    }

    /**
     * Get eliminatedCells
     * @return boolean[][] eliminatedCells
     *
     */
    public boolean[][] getEliminatedCells(){
    	return this.eliminatedCells;
    }

    /**
     * Get moveCnt
     * @return int moveCnt
     *
     */
    public int getLimitLeft(){
    	return this.moveCnt;
    }

    /**
     * Reset the move limit to l
     * @param l the number of the new moveCnt
     *
     */
    public void resetLimit(int l){
    	this.moveCnt = l;
    }

    /**
     * Decrease the moveCnt by 1
     *
     * @return boolean if the count was decreased successfully
     *
     */
    public boolean decrementMove(){
    	if (this.moveCnt > 0){
    		this.moveCnt --;
    		return true;
    	}
    	
    	return false;
    }

    /**
     * Get moveCnt
     * @return int moveCnt
     *
     */
    public int getMoveLeft(){
    	return this.moveCnt; 
    }
}
