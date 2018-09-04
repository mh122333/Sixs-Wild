package game.entity;
import levelbuilder.model.Frequency;
import levelbuilder.model.Position;
import levelbuilder.model.SpecialMoveBank;

/**
 * The class of a board for Release
 * @author Yo Karita
 *
 */
public class ReleaseBoard extends Board{
    protected int moveCnt;
    protected int totalSixToRelease;
    protected int releaseCounter;

    /**
     * Constructor for ReleaseBoard class.
     * @param shape the shape of the board.
     * @param reset number of times reset moves allowed.
     * @param remove number of times remove moves allowed.
     * @param swap number of times swap moves allowed
     */
    public ReleaseBoard(boolean[][] shape, int moveCnt_, int reset, int remove, int swap){
    	super(shape, reset, remove, swap);
    	
        this.releaseCounter = 0;

        this.moveCnt = moveCnt_;
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
    
    @Override
	public boolean resetSquares(Frequency frequency){
		boolean returnValue;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j ++){
				if(cells[i][j].getValid() && !cells[i][j].getEndSix() && cells[i][j].getSquare().getNumber() !=6){
					returnValue = cells[i][j].generateSquares(frequency);
					if(!returnValue) return false;
				}

			}
		}
		return true;
	}
    

    @Override
    // Override to include 6 start and end position
    public boolean set6(boolean[][] sixStartPos, boolean[][] sixEndPos, Frequency frequency){
    	totalSixToRelease = 0;
    	releaseCounter = 0;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(sixStartPos[i][j]){
                	// Set cell
                	cells[i][j].generateSquares(frequency);
                	cells[i][j].setValid(true);
                	cells[i][j].getSquare().setNumber(6);
                	cells[i][j].getSquare().setBonus(1);
                	totalSixToRelease++;
                }
            }
        }
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(sixEndPos[i][j]){
                	// Set cell
                	cells[i][j].setValid(false);
                    cells[i][j].setEndSix(true);
                }
            }
        }
        return true;
    }
    
    public int getSixReleased(){
    	return this.releaseCounter;
    }
    
    public boolean incrementSixReleased(){
    	if (getSixToRelease() > 0){
    		this.releaseCounter ++;
    		return true;
    	}
    	
    	return false;
    }
    /**
     * Get number of 6 tiles need to be released
     * @return int number of 6 tiles need to be released
     *
     */
    public int getSixToRelease(){
    	return this.totalSixToRelease - this.releaseCounter;
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
     * Get moveCnt
     * @return int moveCnt
     *
     */
    public int getMoveLeft(){
    	return this.moveCnt; 
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
