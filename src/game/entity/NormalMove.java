package game.entity;

import levelbuilder.model.Position;
/**
 * The class of normal move
 * @author Yo Karita
 *
 */
public class NormalMove extends Move{

	public NormalMove(Game g) {
		super(g);
	}
	/**
	 * Method to evlauate if the cells selected are valid cells for normal move.
	 * @param p position of the cell to add to the move
	 * @return boolean if the cells are valid to do normal move
	 */
	@Override
	public boolean add(Position p){
		// Determine is within 9*9
		if (0<= p.getX() && p.getX()< 9 && 0 <= p.getY() && p.getY() < 9){
			if (game.getCurrentLevel().getBoard().getCell(p).getValid() && game.getCurrentLevel().getBoard().getCell(p).getSquare().getNumber() != 6){
				if (cellPositions.size() == 0){
					// add it and set selected
					cellPositions.add(p);
					game.getCurrentLevel().getBoard().setCellSelected(p, true);
					return true;
				}
				else if (!cellPositions.contains(p) && connectValid(p)){
					cellPositions.add(p);
					game.getCurrentLevel().getBoard().setCellSelected(p, true);
					return true;
				}
			}
		}
		
		return false;
	}
	/**
	 * Method to do normal move
	 * (It doesn't decrease the move when the move is failed)
	 * @return boolean if the cells are moved successfully
	 */
	@Override
	public boolean doMove(){
		if (sumValid() && game.getCurrentLevel().getBoard().getMoveLeft() > 0){
			// Update score first before the position is changed
			game.getCurrentLevel().getBoard().addScore(calcScore());
			// remove the top and left most one first
			for (int x = 0; x < 9; x++){
				for (int y = 0; y < 9; y++){
					removeSingleSquare(new Position(x,y));
				}
			}
			// Check if six needs to be released
			checkSixToRelease();
			// Update the move left
			game.getCurrentLevel().getBoard().decrementMove();
			return true;
		}
		else{
			// Still update the move left
			// game.getCurrentLevel().getBoard().decrementMove();
			return false;
		}
	}

	/**
	 * Method to calculate the score of the move
	 *
	 * @return int the score made by the move
	 */
	public int calcScore(){
		int mvscr = 0;
		mvscr = 10*cellPositions.size();
		for(Position p : cellPositions){
			mvscr *= game.getCurrentLevel().getBoard().getCell(p).getSquare().getBonus();
		}
		
		// System.out.println("Score Plus: " + mvscr);
		return mvscr;
	}
}
