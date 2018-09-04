package game.view;

import levelbuilder.model.Position;

/**
 * Square view which contains the information of the cell in game
 * @author Weijia Tao
 *
 */
public class SquareView {
	// Position ranges from (1,1) to (9,9)
	protected Position pos;
	
	protected int size;
	
	protected boolean valid;
	
	protected int number;
	
	protected int bonus;
	
	protected boolean endSix;
	
	protected boolean selected;
	
	protected boolean eliminated;
	
	public SquareView(Position pos, int size, boolean valid, int number, int bonus){
		this.pos = pos;
		this.size = size;
		this.valid = valid;
		this.number = number;
		this.bonus = bonus;
		this.endSix = false;
	}
	
	public SquareView(boolean valid){
		this.valid = valid;
	}
	
	public Position getAbsPosition(){
		int x = (pos.getX()-1)*size;
		int y = (pos.getY()-1)*size;
		
		return new Position(x,y);
	}
	
	public boolean getValid(){
		return this.valid;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public int getBonus(){
		return this.bonus;
	}
	
	public void setNumber(int n){
		this.number = n;
	}
	
	public void setBonus(int n){
		this.bonus = n;
	}
	
	public void setEndSix(boolean b){
		this.endSix = b;
	}
	
	public boolean getEndSix(){
		return this.endSix;
	}
	
	public void setSelected(boolean b){
		this.selected = b;
	}
	
	public boolean getSelected(){
		return this.selected;
	}
	
	public void setEliminated(boolean b){
		this.eliminated = b;
	}
	
	public void setValid(boolean b){
		this.valid = b;
	}
	
	public boolean getEliminated(){
		return this.eliminated;
	}
}
