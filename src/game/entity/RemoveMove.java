package game.entity;

import levelbuilder.model.Position;

/**
 * The class of swap move
 * @author Yo Karita
 *
 */
public class RemoveMove extends Move{
	/**
	 * Constructor for RemoveMove class.
	 * @param g game
	 *
	 */
	public RemoveMove(Game g) {
		super(g);
	}
	/**
	 * Method to evaluate if the cells selected are valid cells for remove move. checks if the cell is not 6
	 * @param p position of the cell to add to the move
	 * @return boolean if the cells are valid to do removemove
	 */
	@Override
	public boolean add(Position p ) {
		// Determine is within 9*9
		if (0<= p.getX() && p.getX()< 9 && 0 <= p.getY() && p.getY() < 9){
			if (game.getCurrentLevel().getBoard().getCell(p).getValid() && game.getCurrentLevel().getBoard().getCell(p).getSquare().getNumber() != 6){
				// Only allow add one cell to remove
				if (cellPositions.size() == 0){
					// add it and set selected
					cellPositions.add(p);
					game.getCurrentLevel().getBoard().setCellSelected(p, true);
					return true;
				}
			}
		}
		
		return false;
	}
	/**
	 * Method to remove one cell
	 *
	 * @return boolean if the cells is removed successfully
	 */
	@Override
	public boolean doMove() {
		// If no cell or more than 1 cell are selected, do nothing
		// If no remove left, do nothing
		if (cellPositions.size() != 1 || game.getCurrentLevel().getBoard().getRemoveMove() == 0){
			return false;
		}
		
		removeSingleSquare(cellPositions.get(0));
		checkSixToRelease();
		game.getCurrentLevel().getBoard().decrementRemoveMove();
		game.getCurrentLevel().getBoard().decrementMove();
		return true;
		
	}

}
