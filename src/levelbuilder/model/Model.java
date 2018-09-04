package levelbuilder.model;

/**
 * Model for the level builder. It has all the data required to descirbe a level of Sixes Wild. 
 * It includes all different modes. 
 * @author Fuchen Chen
 */
public class Model {
	/** Level */
	protected Value level; 
	
	/** Type: 
	 * puzzle, lightning, elimination, or release
	 */
	protected Value type;
	
	/** Move for puzzle, elimination, or release mode */
	protected Value move;
	
	/** Time for lightning mode  */
	protected Value time;
	
	/** Goal Score */
	protected Value goalScore;
	
	/** 2D Array of boolean to represent the shape of the board. 
	 * True is active and False is inactive. 
	 */
	protected boolean[][] shape;
	
	/** Frequency */ 
	protected Frequency frequency;
	
	/** Special Move */
	protected SpecialMoveBank specialMoveBank;
	
	/** 2D Array of boolean to represent the six start positions of the board */
	protected boolean[][] sixStartPos;
	
	/** 2D Array of boolean to represent the six end positions of the board */
	protected boolean[][] sixEndPos;
	
	/** Edit status of the current model*/
	protected int editStatus;
	
	// Constants for game type
	public static final int Puzzle = 1;
	
	public static final int Lightning = 2;
	
	public static final int Elimination =  3;
	
	public static final int Release = 4;
	
	// Constants for edit status
	public static final int ShapeEdit = 1;
	
	public static final int SixStartEdit = 2;
	
	public static final int SixEndEdit = 3;
	
	/**
	 * Initialize the model 
	 */
	public Model(){
		// at least 1 level and at most 100 levels
		this.level = new Value(1,100);
		
		// Only four types
		this.type = new Value(1, 4);
		
		// max 99 moves
		this.move = new Value(1, 99);
		
		// max 5 minutes
		this.time = new Value(1, 180);
		
		// At lease 0 and at most max of Integer
		this.goalScore = new Value(0, Integer.MAX_VALUE);
		
		// Frequency
		this.frequency = new Frequency();
		
		// Special Move
		this.specialMoveBank = new SpecialMoveBank();
		
		setEditStatus(ShapeEdit);
		
		initShape();
		
		initSixStartPos();
		
		initSixEndPos();
	}
	
	/**
	 * Reset the model to initial state
	 */
	public void reset(){
		level.setMin();
		type.setMin();
		move.setMin();
		time.setMin();
		goalScore.setMin();
		frequency.reset();
		specialMoveBank.reset();
		setEditStatus(ShapeEdit);
		initShape();
		initSixStartPos();
		initSixEndPos();
	}
	
	/**
	 * Initialize the shape to be all active
	 */
	private void initShape(){
		this.shape = new boolean[9][9];
		for (int col = 0; col < 9; col ++){
			for (int row = 0; row < 9; row ++){
				shape[col][row] = true;
			}
		}
	}
	
	/**
	 * Initialize the six start positions to be all not set 
	 */
	private void initSixStartPos(){
		this.sixStartPos = new boolean[9][9];
		for (int col = 0; col < 9; col ++){
			for (int row = 0; row < 9; row ++){
				sixStartPos[col][row] = false;
			}
		}
	}
	
	/**
	 * Initialize the six end positions to be all not set 
	 */
	private void initSixEndPos(){
		this.sixEndPos = new boolean[9][9];
		for (int col = 0; col < 9; col ++){
			for (int row = 0; row < 9; row ++){
				sixEndPos[col][row] = false;
			}
		}
	}
	
	/**
	 * Get the current level of the model
	 * @return
	 */
	public Value getLevel(){
		return level;
	}
	
	/**
	 * Get the current type of the model
	 * @return
	 */
	public Value getType(){
		return type;
	}
	
	/**
	 * Get the current move limit of the model
	 * @return
	 */
	public Value getMove(){
		return move;
	}
	
	/**
	 * Get the current time limit of the model
	 * @return
	 */
	public Value getTime(){
		return time;
	}
	
	/**
	 * Get the current goal score of the model
	 * @return
	 */
	public Value getGoalScore(){
		return goalScore;
	}
	
	/**
	 * Get the current shape of the model
	 * @return
	 */
	public boolean[][] getShape(){
		return shape;
	}
	
	/**
	 * Get the current frequency of the model
	 * @return
	 */
	public Frequency getFrequency(){
		return frequency;
	}
	
	/**
	 * Get the current special move limit of the model
	 * @return
	 */
	public SpecialMoveBank getSpecialMoveBank(){
		return specialMoveBank;
	}
	
	/**
	 * Get the current six start positions of the model
	 * @return
	 */
	public boolean[][] getSixStartPos(){
		return sixStartPos;
	}
	
	/**
	 * Get the current six end positions of the model
	 * @return
	 */
	public boolean[][] getSixEndPos(){
		return sixEndPos;
	}
	
	/**
	 * Get the current edit status of the model
	 * @return
	 */
	public int getEditStatus(){
		return this.editStatus;
	}
	
	/**
	 * Set the edit status
	 * @param status
	 * one of shape, six start, or six end. 
	 * @return
	 * True if set successfully, False if not
	 */
	public boolean setEditStatus(int status){
		if(status > 3 || status < 1){
			return false;
		}
		else {
			this.editStatus = status;
			return true;
		}
	}
}
