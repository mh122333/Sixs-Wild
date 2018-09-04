package game.entity;

/**
 * The class of reset move
 * @author Yo Karita
 *
 */
public class ResetMove {
	protected Game game;
	/**
	 * Constructor for ResetMove class.
	 * @param g game
	 *
	 */
	public ResetMove(Game g){
		this.game = g;
	}
	/**
	 * Use resetSquares() to reset the board. There is no new tiles. Just swapping.
	 *
	 * @return boolean if the cells are reset successfully
	 */
	public boolean doMove(){
		if (game.getCurrentLevel().getBoard().getResetMove() == 0){return false;}
		
		game.getCurrentLevel().resetSquares();
		game.getCurrentLevel().getBoard().decrementResetMove();
		game.getCurrentLevel().getBoard().decrementMove();
		return true;
	}
}
