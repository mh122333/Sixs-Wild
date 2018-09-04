package game.entity;

import levelbuilder.model.Position;

/**
 * The class of swap move
 * @author Yo Karita
 *
 */
public class SwapMove extends Move{
	/**
	 * Constructor for SwapMove class.
	 * @param g game
	 *
	 */
	public SwapMove(Game g) {
		super(g);
	}

	/**
	 * Method to evaluate if the cells selected are valid cells for swap move. Checks if the 2 cells are connected not 6
	 * @param p position of the cell to add to the move
	 * @return boolean if the cells are valid to do swap move
	 */
	@Override
	public boolean add(Position p) {
		// Determine is within 9*9
		if (0<= p.getX() && p.getX()< 9 && 0 <= p.getY() && p.getY() < 9){
			if (game.getCurrentLevel().getBoard().getCell(p).getValid() && game.getCurrentLevel().getBoard().getCell(p).getSquare().getNumber() != 6){
				if (cellPositions.size() == 0){
					// add it and set selected
					cellPositions.add(p);
					game.getCurrentLevel().getBoard().setCellSelected(p, true);
					return true;
				}
				// Only allow add two
				else if (cellPositions.size() == 1){
					// Do not allow swap 6
					if (!cellPositions.contains(p) && connectValid(p)){
						cellPositions.add(p);
						game.getCurrentLevel().getBoard().setCellSelected(p, true);
						return true;
					}
				}
			}
		}
		
		return false;
	}
	/**
	 * Method to swap two cells
	 *
	 * @return boolean if the cells are swapped successfully
	 */
	@Override
	public boolean doMove() {
		// If no cell or more than 2 cell are selected, do nothing
		if (cellPositions.size() != 2 || game.getCurrentLevel().getBoard().getSwapMove() == 0){
			return false;
		}
		
		Position p1 = cellPositions.get(0);
		Position p2 = cellPositions.get(1);
		Square s1 = new Square(game.getCurrentLevel().getBoard().getCell(p1).getSquare().getNumber(),
							   game.getCurrentLevel().getBoard().getCell(p1).getSquare().getBonus());
		Square s2 = new Square(game.getCurrentLevel().getBoard().getCell(p2).getSquare().getNumber(),
				   			   game.getCurrentLevel().getBoard().getCell(p2).getSquare().getBonus());
		
		game.getCurrentLevel().getBoard().getCell(p1).getSquare().setSquare(s2);
		game.getCurrentLevel().getBoard().getCell(p2).getSquare().setSquare(s1);
		
		game.getCurrentLevel().getBoard().decrementSwapMove();
		game.getCurrentLevel().getBoard().decrementMove();
		return true;
	}

}
