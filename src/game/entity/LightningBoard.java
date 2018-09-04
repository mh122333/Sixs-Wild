package game.entity;
import levelbuilder.model.Frequency;
import levelbuilder.model.Position;
import levelbuilder.model.SpecialMoveBank;

/**
 * The class of a board for Lightning
 * @author Yo Karita
 *
 */
public class LightningBoard extends Board{
    protected int timeCnt;

    /**
     * Constructor for LightningBoard class.
     * @param shape the shape of the board.
     * @param reset number of times reset moves allowed.
     * @param remove number of times remove moves allowed.
     * @param swap number of times swap moves allowed
     */
    public LightningBoard(boolean[][] shape, int timeCnt_, int reset, int remove, int swap){
    	super(shape, reset, remove, swap);

        this.timeCnt = timeCnt_;
    }

    /**
     * Set timeCnt to l
     * @param l new timecnt
     *
     */
    public void resetLimit(int l){
    	this.timeCnt = l;
    }

    /**
     * Get timeCnt
     * @return int timeCnt
     *
     */
    public int getLimitLeft(){
    	return this.timeCnt;
    }

    /**
     * Decrement timeCnt by 1
     * @return boolean if the decrement was successfull
     *
     */
    public boolean decrementTime(){
    	if (this.timeCnt > 0){
    		this.timeCnt --;
    		return true;
    	}
    	
    	return false;
    }
    /**
     * Decrement move but lightning has no movelimit so return false
     * @return boolean return false
     *
     */
    public boolean decrementMove(){
    	return false;
    }

    /**
     * Always returns 1
     * @return int 1
     *
     */
    public int getMoveLeft(){
    	return 1; 
    }

    /**
     * Get eliminated cells
     * @return null
     *
     */
    public boolean[][] getEliminatedCells(){
    	return null;
    }
}
