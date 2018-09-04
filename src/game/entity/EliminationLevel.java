package game.entity;
import levelbuilder.model.Frequency;
import levelbuilder.model.*;
import levelbuilder.model.Model.*;
import levelbuilder.model.SpecialMoveBank;

/**
 * The level class for elimination
 * @author Yo Karita
 *
 */
public class EliminationLevel extends Level{
	protected int totalMove;

	/**
	 * Constructor for EliminationLevel class.
	 * @param model the model
	 * @param playable_ if the level is playable
	 */
    public EliminationLevel(Model model, boolean playable_){
    	super(model, playable_);
    	this.totalMove = model.getMove().getValue();
        this.board = new EliminationBoard(model.getShape(),model.getMove().getValue(), 
        								  model.getSpecialMoveBank().getReset().getValue(), 
        								  model.getSpecialMoveBank().getRemove().getValue(), 
        								  model.getSpecialMoveBank().getSwap().getValue());
    }

	/**
	 * Get the type of the level
	 * @return int returns the elimination type
	 */
    public int getType(){
    	return Model.Elimination;
    }
    
	public boolean initBoard(){
		this.board.resetSpecialMoveLeft(specialMoveBank);
		this.board.resetLimit(totalMove);
		this.board.resetCurrentScore();
		
        for (int row = 0; row < 9; row ++){
        	for (int col = 0; col < 9; col ++){
        		this.board.getEliminatedCells()[row][col] = false;
        	}
        }
		
		return this.board.generateSquares(frequency);
	}

	/**
	 * Get the sixEndPos
	 * @return null there is no six end points
	 */
    public boolean[][] getSixEndPos(){
    	return null;
    }

	/**
	 * Checks if the level is passed
	 * @return boolean if the level is passed
	 */
    public boolean isLevelPassed(){
    	boolean[][] cellsEliminated = this.board.getEliminatedCells();
    	boolean pass = true;
    	
    	if(cellsEliminated == null){return false;}
    	
    	for(int x = 0; x < 9; x ++){
    		for(int y = 0; y < 9; y ++){
    			if (shape[x][y]){
    				pass = pass && cellsEliminated[x][y];
    			}
    		}
    	}
    	
    	pass = pass &&  this.goalScore <= this.board.getCurrentScore();
    	
    	return pass;
    	
    }

}