package game.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class of the accomplishment list
 * @author Yo Karita
 *
 */
public class AccListMemento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4475580472802793947L;

	protected ArrayList<Acc> accList = new ArrayList<Acc>();

	/**
	 * Constructor for AccListMemento class.
	 * @param list the list of accomplishments
	 *
	 */
	public AccListMemento(ArrayList<Acc> list) {
		for (Acc a : list) {
			accList.add(a.copy());
		}
	}

	/**
	 * Get copy of an array list of accomplishment.
	 * @return ArrayList<Acc></Acc> the copy of the list of accomplishments
	 *
	 */
	public ArrayList<Acc> getCopy(){
		ArrayList<Acc> list = new ArrayList<Acc>();
		for (Acc a : accList) {
			list.add(a.copy());
		}
		
		return list;
	}
}
