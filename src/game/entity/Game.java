package game.entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import rwManager.LoadLBManager;
import rwManager.SaveLBManager;
import levelbuilder.model.Model;

/**
 * The class which entire information about the game app.
 * @author Yo Karita
 *
 */

public class Game {
	protected ArrayList<Level> levels;
	
	protected ArrayList<Acc> accList;
	
	protected int currentLevel;
	
	protected final String ACC_SAVEFILE = "acc.save";

	/**
	 * Constructor for Game class.
	 *
	 */
	public Game(){
		levels = new ArrayList<Level>();
		accList = new ArrayList<Acc>();
		loadLevels();
		loadAccList();
	}

	/**
	 * Get accList
	 * @return ArrayList<Acc> accList
	 *
	 */
	public ArrayList<Acc> getAccList(){
		return this.accList;
	}
	
	public void addAcc(Level l){
		Acc accomplishment = new Acc(l);
		if (!accList.contains(accomplishment)){
			accList.add(accomplishment);
			System.out.println(accomplishment.getName() + " accomplishment added");
		}
		else{
			accList.get(accList.indexOf(accomplishment)).override(accomplishment);
			// accList.set(accList.indexOf(accomplishment), accomplishment);
			System.out.println(accomplishment.getName() + " accomplishment updated");
		}
		
		saveAccList();
	}
	
	public void addAcc(){
		addAcc(getCurrentLevel());
	}
	
	public void saveAccList(){
		AccListMemento memento = new AccListMemento(accList);
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(SaveLBManager.SAVEFILE_FOLDER + ACC_SAVEFILE));
			oos.writeObject(memento);
			System.out.println("Accomplishment saved");
		} catch (Exception e) {
			System.err.println("Unable to save accomplishments:" + e.getMessage());
		}
		
		if (oos != null) {
			try { oos.close(); } catch (IOException ioe) { } 
		}
	}
	
	public void loadAccList(){
		 ObjectInputStream ois = null;
		 try {
			 ois = new ObjectInputStream (new FileInputStream(SaveLBManager.SAVEFILE_FOLDER + ACC_SAVEFILE));
			 AccListMemento memento = (AccListMemento) ois.readObject();
			 ois.close();
			 accList = memento.getCopy();
		 } catch (Exception e) {
			 // unable to perform restore
			 System.out.println("No accomplishments found");
		 }
		 
		 // close down safely
		 if (ois != null) {
			 try { ois.close(); } catch (IOException ioe) { }
		 }
		 
		 // Set playable
		 for (Acc a: accList){
			 setLevelPlayable(a.getLevel());
		 }
		 
		 int levelCnt = 1;
		 while(getLevelPlayable(levelCnt)){
			 levelCnt ++;
		 }
		 setLevelPlayable(levelCnt);
	}
	
	// Load levels and sort them from 1 - Inf
	private void loadLevels(){
		Model m = new Model();
		int type;
		LoadLBManager loadMgr = new LoadLBManager(m);
		
		ArrayList<Integer> availableLevels = loadMgr.getEditableLevels();
		
		for (Integer i: availableLevels){
			try{
				loadMgr.loadData(i);
			} catch (Exception e){
				System.out.println("Unable to load the level");
			}
			
			type = loadMgr.getModel().getType().getValue();
			
			Model m_temp = loadMgr.getModel();
			
			switch(type){
			case Model.Puzzle:
				levels.add(new PuzzleLevel(m_temp, false));
				break;
			case Model.Lightning:
				levels.add(new LightningLevel(m_temp, false));
				break;
			case Model.Elimination:
				levels.add(new EliminationLevel(m_temp, false));
				break;
			case Model.Release:
				levels.add(new ReleaseLevel(m_temp, false));
				break;
			default:
				break;
			}

		}
		
		Collections.sort(levels, new LevelComp());
		String str = "Level Order: ";
		for(Level l: levels){
			str = str + l.getLevel() + " ";
		}
		System.out.println(str);
		
		if (levels.get(levels.size()-1).getLevel() != levels.size()){
			System.out.println("Missing level");
		}
		
	}

	/**
	 * Get levels
	 * @return ArrayList<level></level> levels
	 *
	 */
	public ArrayList<Level> getLevels(){
		return this.levels;
	}

	/**
	 * Get number of levels
	 * @return int number of levels
	 *
	 */
	public int getNumLevel(){
		return levels.size();
	}

	/**
	 * Get a list of levels
	 * @return ArrayList<Integet></Integet> a list of levels
	 *
	 */
	public ArrayList<Integer> getLevelList(){
		ArrayList<Integer> levellist = new ArrayList<Integer>();
		for (Level l: levels){
			levellist.add(l.getLevel());
		}
		
		return levellist;
	}

	/**
	 * Set the level playable
	 * @param l the level
	 * @return If the change was made successfully
	 *
	 */
	public boolean setLevelPlayable(int l){
		if (getLevel(l) != null){
			getLevel(l).setPlayable(true);
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Checks if the level is playable
	 * @param l the level to be checked
	 * @return boolean if the level is playable
	 *
	 */
	public boolean getLevelPlayable(int l){
		if (getLevel(l) != null){
			return getLevel(l).isPlayable();
		}
		else{
			return false;
		}
	}

	/**
	 * Get the level
	 * @param l the position of the level
	 * @return Level A level
	 *
	 */
	public Level getLevel(int l){
		if (l <= levels.size()){
			if (levels.get(l-1).getLevel() == l){
				return levels.get(l-1);
			}
			return null;
		}
		else{
			return null;
		}
	}

	/**
	 * Get currentLevel
	 * @return Level currentLevel
	 *
	 */
	public Level getCurrentLevel(){
		return getLevel(currentLevel);
	}

	/**
	 * Change the current level to be l
	 * @param l which level will be the current level
	 * @return boolean if the current level was changed successfully
	 *
	 */
	public boolean setCurrentLevel(int l){
		if (getLevelList().contains(l)){
			this.currentLevel = l;
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Checks if there is a next level
	 * @return boolean returns true if there is a next level
	 *
	 */
	public boolean hasNextLevel(){
		return getLevelList().contains(currentLevel + 1);
	}
	
	
	
}
