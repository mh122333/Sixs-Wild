package rwManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import levelbuilder.model.Model;

/**
 * Load level manager is in charge of loading the levels from the text files.
 * @author Zhaochen Ding
 *
 */
public class LoadLBManager {
	protected Path loadFilePath;
	  
	protected Model model;
	
	protected int level;
	
	/**
	 * Construct the manger by using a model
	 * @param m
	 * Model to be modified
	 */
	public LoadLBManager(Model m){
		this.model = m;
	}
	
	/**
	 * Update the file path based on the integer level given
	 * @param level
	 * Level number
	 */
	private void updateFilePath(int level){
		this.loadFilePath = Paths.get(SaveLBManager.SAVEFILE_FOLDER + "level" + level + SaveLBManager.SAVEFILE_SUFFIX);
	}
	
	/**
	 * Load the specific level into model
	 * @param level
	 * Level number
	 * @throws IOException
	 * Fail to read the level files
	 */
	public void loadData(int level) throws IOException{
		updateFilePath(level);
		
		Scanner scanner =  new Scanner(loadFilePath, SaveLBManager.ENCODING.name());
		try{
			while (scanner.hasNextLine()){
				
				String att = scanAttribute(scanner.nextLine());
				
				if (att != null){
					// Find an attribute
					switch(att){
					case SaveLBManager.LEVEL:
						model.getLevel().setValue(scanner.nextInt());
						break;
					case SaveLBManager.TYPE:
						model.getType().setValue(scanner.nextInt());
						break;
					case SaveLBManager.LIMIT:
						if (model.getType().getValue() == Model.Lightning){
							model.getTime().setValue(scanner.nextInt());
							model.getMove().setValue(model.getMove().getMin());
						}
						else{
							model.getMove().setValue(scanner.nextInt());
							model.getTime().setValue(model.getTime().getMin());
						}
						break;
					case SaveLBManager.GOALSCORE:
						model.getGoalScore().setValue(scanner.nextInt());
						break;
					case SaveLBManager.SHAPE:
						for (int row = 0; row < model.getShape().length; row ++){
							boolean[] r = scanRow(scanner.nextLine());
							
							setRow(model.getShape(), row, r);
						}
						break;					
					case SaveLBManager.ONEFREQUENCY:
						model.getFrequency().getNumFrequency(1).setValue(scanner.nextInt());
						break;
					case SaveLBManager.TWOFREQUENCY:
						model.getFrequency().getNumFrequency(2).setValue(scanner.nextInt());
						break;					
					case SaveLBManager.THREEFREQUENCY:
						model.getFrequency().getNumFrequency(3).setValue(scanner.nextInt());
						break;
					case SaveLBManager.FOURFREQUENCY:
						model.getFrequency().getNumFrequency(4).setValue(scanner.nextInt());
						break;
					case SaveLBManager.FIVEFREQUENCY:
						model.getFrequency().getNumFrequency(5).setValue(scanner.nextInt());
						break;
					case SaveLBManager.SIXFREQUENCY:
						model.getFrequency().getNumFrequency(6).setValue(scanner.nextInt());
						break;
					case SaveLBManager.XTWOFREQUENCY:
						model.getFrequency().getX2Frequency().setValue(scanner.nextInt());
						break;					
					case SaveLBManager.XTHREEFREQUENCY:
						model.getFrequency().getX3Frequency().setValue(scanner.nextInt());
						break;
					case SaveLBManager.RESET:
						model.getSpecialMoveBank().getReset().setValue(scanner.nextInt());
						break;
					case SaveLBManager.REMOVE:
						model.getSpecialMoveBank().getRemove().setValue(scanner.nextInt());
						break;
					case SaveLBManager.SWAP:
						model.getSpecialMoveBank().getSwap().setValue(scanner.nextInt());
						break;
					case SaveLBManager.SIXSTARTPOSITION:
						for (int row = 0; row < model.getShape().length; row ++){
							boolean[] r = scanRow(scanner.nextLine());
							
							setRow(model.getSixStartPos(), row, r);
						}
						break;
					case SaveLBManager.SIXENDPOSITION:
						for (int row = 0; row < model.getShape().length; row ++){
							boolean[] r = scanRow(scanner.nextLine());
							
							setRow(model.getSixEndPos(), row, r);
						}
						break;
					default:
						break;
					}
				}
			}
		}
		finally {
			if(scanner != null){
				scanner.close();
			}
		}
	}
	
	public Model getModel(){
		return this.model;
	}
	
	/**
	 * Find the attribute in [*] format in a given line
	 * @param line
	 * Line to conduct search
	 * @return
	 * Attribute name as a string
	 */
	private String scanAttribute(String line){
		String att = "";
		
		Scanner scanner = new Scanner(line);
		
		try {
			if (scanner.hasNext()){
				// Look for [...]
				att = scanner.findInLine("\\[(.*?)\\]");
			}
		}
		finally{
			if(scanner != null){
				scanner.close();
			}
		}

		  
		return att;
	}
	
	/**
	 * Scan a line and set it to a boolean array
	 * @param line
	 * Line to conduct scan
	 * @return
	 * An array with the proper true and false
	 */
	private boolean[] scanRow(String line){
		boolean[] row = new boolean[9]; 
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter(",");
		try {
			for (int i = 0; i < row.length; i ++){
				if (scanner.nextInt() == 1){
					row[i] = true;
				}
				else{
					row[i] = false;
				}
			}
		}
		finally{
			if(scanner != null){
				scanner.close();
			}
		}
		
		return row;
	}
	
	/**
	 * Given a boolean array, set it to the row of a 2D boolean array
	 * @param board
	 * 2D boolean array to be changed
	 * @param rowNum
	 * Row number to be set
	 * @param row
	 * Row data to load into the 2D array
	 */
	private void setRow(boolean[][] board, int rowNum, boolean[] row){
		for (int i = 0; i < board.length; i ++){
			board[i][rowNum] = row[i];
		}	
	}

	/**
	 * Find all the existing levels 
	 * @return
	 * An array list of existing levels (sorted from small to large)
	 */
	public ArrayList<Integer> getEditableLevels(){
		ArrayList<Integer> levellist = new ArrayList<Integer>();
		String fileName;
		
		File saveFolder = new File(SaveLBManager.SAVEFILE_FOLDER);
		File[] listOfFiles = saveFolder.listFiles(); 
		
		for (int i = 0; i < listOfFiles.length; i ++){
			if (listOfFiles[i].isFile()){
				fileName = listOfFiles[i].getName();
				if (fileName.endsWith(SaveLBManager.SAVEFILE_SUFFIX)){
					// Output the level as integer
					Scanner scanner = new Scanner(fileName);
					scanner.useDelimiter("level");
					try{
						String l = scanner.findInLine("\\d+");
						if (l != null){
							levellist.add(Integer.valueOf(l));
						}
					}
					finally{
						if(scanner != null){
							scanner.close();
						}
					}	
				}
			}
		}
		
		Collections.sort(levellist);
		System.out.println("Successfully Loaded Level" + levellist);
		return levellist;
	}
	
	/**
	 * Find the maximum existing level
	 * @return
	 * Maximum level number
	 */
	public int getMaxLevel(){
		int max = 1;
		ArrayList<Integer> list = getEditableLevels();
		for (Integer i: list){
			max = Math.max(max, i);
		}
		
		return max;
	}
}
