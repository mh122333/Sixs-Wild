package game.entity;
        import levelbuilder.model.*;
import levelbuilder.model.Model.*;
import levelbuilder.model.SpecialMoveBank;
/**
 * The level class for puzzle
 * @author Yo Karita
 *
 */
public class PuzzleLevel extends Level{
	protected int totalMove;

	/**
	 * Constructor for PuzzleLevel class.
	 * @param model the model
	 * @param playable_ if the level is playable
	 */
    public PuzzleLevel(Model model, boolean playable_){
    	super(model, playable_);
    	this.totalMove = model.getMove().getValue();
        this.board = new PuzzleBoard(model.getShape(), model.getMove().getValue(), 
        						     model.getSpecialMoveBank().getReset().getValue(), 
        						     model.getSpecialMoveBank().getRemove().getValue(), 
        						     model.getSpecialMoveBank().getSwap().getValue());
    }
	/**
	 * Get the type of the level
	 * @return int returns the puzzle type
	 */
    public int getType(){
    	return Model.Puzzle;
    }

	/**
	 * InitializeBoard function for the puzzle level (generates all squares)
	 * @return boolean if the board is initialized successfully
	 */
	public boolean initBoard(){
		this.board.resetSpecialMoveLeft(specialMoveBank);
		this.board.resetLimit(totalMove);
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