package game.entity;

import java.util.Comparator;

/**
 * The class of LevelComp which has a function to compare levels.
 * @author Yo Karita
 *
 */
public class LevelComp implements Comparator<Level>{
	/**
	 * Compare 2 levels and tells you which one is higher
	 *@param l1 the first level
	 *@param l2 another level to be compared with l1
	 *@return int if the l1 is bigger, then it'll return 1, else it returns -1
	 */
	@Override
	public int compare(Level l1, Level l2) {
		if (l1.getLevel() > l2.getLevel()){
			return 1;
		}
		else{
			return -1;
		}
	}
}
