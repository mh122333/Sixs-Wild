package game.entity;

import levelbuilder.model.Frequency;
import levelbuilder.model.Model;
import levelbuilder.model.SpecialMoveBank;

/**
 * The class with attributes which variables don't change during the game.
 * @author Yo Karita
 *
 */
public abstract class Level {
	protected boolean playable;

	protected int level;

	protected int goalScore;

	protected Frequency frequency;
	
	protected SpecialMoveBank specialMoveBank;
	
	protected boolean[][] shape = new boolean[9][9];

	protected Board board;
	/**
	 * Constructor for Level class.
	 * @param model the model
	 * @param playable_ if the level is playable
	 */
	public Level(Model model, boolean playable_){
        this.playable = playable_;
        this.level = model.getLevel().getValue();
        this.goalScore = model.getGoalScore().getValue();
        
        this.frequency = new Frequency();
        for (int i = 0; i < 6; i ++){
            this.frequency.getNumFrequency(i+1).setValue(model.getFrequency().getNumFrequency(i+1).getValue());
        }
        this.frequency.getX2Frequency().setValue(model.getFrequency().getX2Frequency().getValue());
        this.frequency.getX3Frequency().setValue(model.getFrequency().getX3Frequency().getValue());
        
        this.specialMoveBank = new SpecialMoveBank();
        this.specialMoveBank.getRemove().setValue(model.getSpecialMoveBank().getRemove().getValue());
        this.specialMoveBank.getReset().setValue(model.getSpecialMoveBank().getReset().getValue());
        this.specialMoveBank.getSwap().setValue(model.getSpecialMoveBank().getSwap().getValue());
        
        // Copy the shape
        for (int x = 0; x < 9; x ++){
        	for (int y = 0; y < 9; y ++){
                this.shape[x][y] = model.getShape()[x][y];
        	}
        }
	}

	/**
	 *
	 * The default method for initial
	 * @return boolean if the
	 */
	public abstract boolean initBoard();

	/**
	 * Get SixEndPos
	 * @return boolean[][] sixEndPos. or null
	 *
	 */
	public abstract boolean[][] getSixEndPos();

	/**
	 * Check if the level is passed
	 * @return boolean if the level is passed
	 */
	public abstract boolean isLevelPassed();

	/**
	 * Get the type of the level
	 * @return int the level type
	 */
	public abstract int getType();

	/**
	 * Checks if the level is end
	 * @return boolean if the level is end
	 */
    public boolean isLevelEnd(){
    	return this.board.getLimitLeft() == 0;
    }

	/**
	 * Checks if the level is end
	 * @return boolean if the level is end
	 */
	public boolean isPlayable() {
		return playable;
	}

	/**
	 * Set the playable true or false
	 * @param b true is playable. false is not
	 */
	public void setPlayable(boolean b) {
		this.playable = b;
	}
	/**
	 * Get the level
	 *
	 * @return int level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Get the goal score of the level
	 *
	 * @return int goalScore
	 */
	public int getGoalScore() {
		return goalScore;
	}

	/**
	 * Get the frequency
	 *
	 * @return Frequency frequency
	 */
	public Frequency getFrequency() {
		return frequency;
	}
	/**
	 * Get the board
	 *
	 * @return Board board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Get the shape
	 *
	 * @return boolean[][] shape
	 */
	public boolean[][] getShape(){
		return this.shape;
	}

	/**
	 * Reset squares
	 *
	 * @return boolean if the squares are reset
	 */
	public boolean resetSquares(){
		return this.board.resetSquares(frequency);
	}

	/**
	 * Determine how many stars the player gets
	 *
	 * @return int number of stars gotten
	 */
	public int getStar(){
		int currentScore = this.board.getCurrentScore();
		
		if (0 <= currentScore && currentScore < goalScore){
			return 0;
		}
		else if (goalScore <= currentScore && currentScore < 3*goalScore/2){
			return 1;
		}
		else if (3*goalScore/2 <= currentScore && currentScore < 2*goalScore){
			return 2;
		}
		else{
			return 3;
		}
	}
}
