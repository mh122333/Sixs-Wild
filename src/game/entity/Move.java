package game.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import levelbuilder.model.Position;

/**
 * The class of move.
 * @author Yo Karita
 *
 */
public abstract class Move {
	protected ArrayList<Position> cellPositions = new ArrayList<Position>();
	
	protected Game game;
	/**
	 * Constructor for Move class.
	 * @param g game
	 */
	public Move(Game g){
		this.game = g;
	}

	/**
	 * Abstract method to check if the cell is valid for a move
	 * @param p position of the cell to be added
	 * @return boolean if the cell is valid
	 */
	public abstract boolean add(Position p);

	/**
	 * A method to check if the cells are connected vertically or horizontally.
	 * @param p2 position of the other cell to be checked
	 * @return boolean if the cell is connected
	 */
	public boolean connectValid(Position p2){
		boolean validation = false;
		// If the first one, return true
		if (cellPositions.size() == 0){return true;}
		
		for (Position p1: cellPositions){
			if (p1.getX() == p2.getX() && (Math.abs(p2.getY() - p1.getY()) == 1)){
				validation = true;
			}
			else if (p1.getY() == p2.getY() && (Math.abs(p2.getX() - p1.getX()) == 1)){ 
				validation = true;
			}
			else {
			}
		}
		
		return validation;
	}

	/**
	 * A method to remove a single square.
	 * If the level is elimination, it mark the cell as eliminated.
	 * @param p position of the cell to be moved
	 * @return boolean if move was successful
	 */
	public boolean removeSingleSquare(Position p){
		boolean isElimination = (game.getCurrentLevel().getBoard().getEliminatedCells() != null);
//		boolean isRelease = (game.getCurrentLevel().getSixEndPos() != null);
		
		if (!cellPositions.contains(p)){return false;}
		
		// Set eliminated if elimination mode
		if (isElimination){
			game.getCurrentLevel().getBoard().getEliminatedCells()[p.getX()][p.getY()] = true;
		}
		
		// Record the cell above positions as a queue
		Queue<Position> cellsAbove = new LinkedList<Position>();
		cellsAbove.add(p);
		
		// Go up vertically to find out all valid cells
		for (int y = p.getY() - 1; y >= 0 ; y --){
			Position p_temp = new Position(p.getX(),y);
			if (game.getCurrentLevel().getBoard().getCell(p_temp).getValid() && !game.getCurrentLevel().getBoard().getCell(p_temp).getEndSix()){
				cellsAbove.add(p_temp);
			}
		}

		while(cellsAbove.size() > 1){
			Position cell1 = cellsAbove.remove();
			Position cell2 = cellsAbove.peek();			
			game.getCurrentLevel().getBoard().getCell(cell1).getSquare().setSquare(game.getCurrentLevel().getBoard().getCell(cell2).getSquare());
		}
		
		// Regenerate the last cell's number and bonus
		game.getCurrentLevel().getBoard().getCell(cellsAbove.remove()).generateSquares(game.getCurrentLevel().getFrequency());
			
		return true;
	}
	/**
	 * A method to check if the 6 tile is above the release point.
	 * If it is, then 6 moves to the release point and increment the 6 released
	 * @return boolean true if 6 reached the release point
	 */
	public boolean checkSixToRelease(){
		boolean isRelease = (game.getCurrentLevel().getSixEndPos() != null);
		boolean returnValue = false;
		if (isRelease){
	        for(int i = 0; i < 9; i++){
	            for(int j = 0; j < 9; j++){
	            	// If it is a six end position cell
	            	if (game.getCurrentLevel().getSixEndPos()[i][j] && !game.getCurrentLevel().getBoard().getCell(new Position(i, j)).getValid()){
	            		int y = j - 1;
	            		// Find the first valid one above
	            		while (!game.getCurrentLevel().getBoard().getCell(new Position(i, y)).getValid()){
	            			y --;
	            		}
	            		if (game.getCurrentLevel().getBoard().getCell(new Position(i, y)).getSquare().getNumber() == 6){
							Position p = new Position(i, y);
							cellPositions.add(p);
	            			removeSingleSquare(p);
	            			game.getCurrentLevel().getBoard().getCell(new Position(i, j)).setValid(true);
	            			game.getCurrentLevel().getBoard().getCell(new Position(i, j)).setSquare(new Square(6,1));
	            			((ReleaseBoard) game.getCurrentLevel().getBoard()).incrementSixReleased();
	            			returnValue = true;
	            		}
	            	}
	            }
	        }
		}
		
		return returnValue;
	}
	/**
	 * An abstract method to do actual move.
	 * @return boolean if the move was successful
	 */
	public abstract boolean doMove();

	/**
	 * An method to check if the sum is 6
	 * @return boolean if the sum is 6
	 */
	public boolean sumValid(){
		int sum = 0;
		for (Position p: cellPositions){
			sum += game.getCurrentLevel().getBoard().getCell(p).getSquare().getNumber();
		}
		
		if (sum == 6){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public void clear(){
		// Set unselected
		for (Position p: cellPositions){
			game.getCurrentLevel().getBoard().getCell(p).setSelected(false);
		}
		
		// Clear the list
		cellPositions.clear();
	}
	
}
