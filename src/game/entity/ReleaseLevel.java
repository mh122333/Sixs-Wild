package game.entity;
import levelbuilder.model.*;
import levelbuilder.model.Model.*;
import levelbuilder.model.SpecialMoveBank;
/**
 * The level class for release
 * @author Yo Karita
 *
 */
public class ReleaseLevel extends Level{
	protected int totalMove;
    protected boolean[][] sixStartPos = new boolean[9][9];
    protected boolean[][] sixEndPos = new boolean[9][9];

    /**
     * Constructor for ReleaseLevel class.
     * @param model the model
     * @param playable_ if the level is playable
     */
    public ReleaseLevel(Model model, boolean playable_){
    	super(model, playable_);
    	this.totalMove = model.getMove().getValue();
    	
    	// Force the frequency for 6 to 0
    	this.frequency.getNumFrequency(6).setValue(0);
        
        // Copy the six start and end position
        for (int x = 0; x < 9; x ++){
        	for (int y = 0; y < 9; y ++){
        		this.sixStartPos[x][y] = model.getSixStartPos()[x][y];
        	}
        }
        for (int x = 0; x < 9; x ++){
        	for (int y = 0; y < 9; y ++){
        		this.sixEndPos[x][y] = model.getSixEndPos()[x][y];
        	}
        }
        
        this.board = new ReleaseBoard(model.getShape(),model.getMove().getValue(),
				  model.getSpecialMoveBank().getReset().getValue(), 
				  model.getSpecialMoveBank().getRemove().getValue(), 
				  model.getSpecialMoveBank().getSwap().getValue());
        
    }
    /**
     * InitializeBoard function for the release level (generates all squares), set 6 start and end points
     * @return boolean if the board is initialized successfully
     */
    public boolean initBoard(){
		this.board.resetSpecialMoveLeft(specialMoveBank);
		this.board.resetLimit(totalMove);
		this.board.resetCurrentScore();
        this.board.generateSquares(frequency);
        this.board.set6(sixStartPos, sixEndPos, frequency);
        return true;
    }
    /**
     * Get the type of the level
     * @return int returns the release type
     */
    public int getType(){
    	return Model.Release;
    }
    /**
     * Get sixEndPos
     * @return boolean sixEndPos
     */
    public boolean[][] getSixEndPos(){
    	return this.sixEndPos;
    }
    /**
     * Checks if the level is passed
     * @return boolean checks if the current score is equal or greater than the goal score
     */
    public boolean isLevelPassed(){    	
    	return this.goalScore <= this.board.getCurrentScore() && (((ReleaseBoard) this.board).getSixToRelease() == 0); 	
    }

}