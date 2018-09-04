package game.entity;
import levelbuilder.model.*;
import levelbuilder.model.Model.*;
import levelbuilder.model.SpecialMoveBank;

/**
 * The class of a board for lightning
 * @author Yo Karita
 *
 */
public class LightningLevel extends Level{
	protected int totalTime;
	/**
	 * Constructor for LightningLevel class.
	 * @param model the model
	 * @param playable_ if the level is playable
	 */
    public LightningLevel(Model model, boolean playable_){
    	super(model, playable_);
    	this.totalTime = model.getTime().getValue();
        this.board = new LightningBoard(model.getShape(), model.getTime().getValue(),
        								model.getSpecialMoveBank().getReset().getValue(), 
        								model.getSpecialMoveBank().getRemove().getValue(), 
        								model.getSpecialMoveBank().getSwap().getValue());
    }
	/**
	 * Get the type of the level
	 * @return int returns the lighting type
	 */
    public int getType(){
    	return Model.Lightning;
    }
	/**
	 * InitializeBoard function for the lightning level (generates all squares),reset time instead of movecount
	 * @return boolean if the board is initialized successfully
	 */
	public boolean initBoard(){
		this.board.resetSpecialMoveLeft(specialMoveBank);
		this.board.resetLimit(totalTime);
		this.board.resetCurrentScore();
		return this.board.generateSquares(frequency);
	}
	/**
	 * Return null because there is no six end points
	 * @return null
	 */
    public boolean[][] getSixEndPos(){
    	return null;
    }
	/**
	 * Checks if the level is passed
	 * @return boolean checks if the current score is equal or greater than the goal score
	 */
    public boolean isLevelPassed(){    	
    	return this.goalScore <= this.board.getCurrentScore(); 	
    }
}