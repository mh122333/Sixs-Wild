package game.entity;
import levelbuilder.model.Frequency;
import levelbuilder.model.Position;
import levelbuilder.model.SpecialMoveBank;
/**
 * The class of a board for Puzzle
 * @author Yo Karita
 *
 */
public class PuzzleBoard extends Board{
    protected int moveCnt;

    /**
     * Constructor for PuzzleBoard class.
     * @param shape the shape of the board.
     * @param reset number of times reset moves allowed.
     * @param remove number of times remove moves allowed.
     * @param swap number of times swap moves allowed
     */
    public PuzzleBoard(boolean[][] shape, int moveCnt_, int reset, int remove, int swap){
    	super(shape, reset, remove, swap);

        this.moveCnt = moveCnt_;
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
     * Set moveCnt to l
     * @param l new movecnt
     *
     */
    public void resetLimit(int l){
    	this.moveCnt = l;
    }

    /**
     * Decrement moveCnt by 1 if moveCnt is greater than 0
     * @return boolean if the decrement was successful
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

    /**
     * Get eliminatedCells
     * @return null
     *
     */
    public boolean[][] getEliminatedCells(){
    	return null;
    }
}
