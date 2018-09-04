package rwManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import levelbuilder.model.Model;

/**
 * Save level manager is in charge of saving the levels to the text files in a specific format.
 * @author Zhaochen Ding
 *
 */
public class SaveLBManager {
	  protected Path saveFilePath;
	  
	  protected Model model;
	  
	  
	  public final static Charset ENCODING = StandardCharsets.UTF_8;  	  
	  public final static String SAVEFILE_FOLDER= "save" + File.separator;
	  public final static String SAVEFILE_SUFFIX= ".txt";
	  
	  protected final static String LEVEL = "[Level]";
	   
	  protected final static String TYPE = "[Type]";
	  
	  protected final static String LIMIT = "[Limit]";
	  
	  protected final static String GOALSCORE = "[GoalScore]";
	  
	  protected final static String SHAPE = "[Shape]";
	  
	  protected final static String ONEFREQUENCY = "[OneFrequency]";
	  
	  protected final static String TWOFREQUENCY = "[TwoFrequency]";
	  
	  protected final static String THREEFREQUENCY = "[ThreeFrequency]";
	  
	  protected final static String FOURFREQUENCY = "[FourFrequency]";
	  
	  protected final static String FIVEFREQUENCY = "[FiveFrequency]";
	  
	  protected final static String SIXFREQUENCY = "[SixFrequency]";
	  
	  protected final static String XTWOFREQUENCY = "[XTwoFrequency]";
	  
	  protected final static String XTHREEFREQUENCY = "[XThreeFrequency]";
	  
	  protected final static String RESET = "[Reset]";
	  
	  protected final static String REMOVE = "[Remove]";
	  
	  protected final static String SWAP = "[Swap]";
	  
	  protected final static String SIXSTARTPOSITION = "[SixStartPosition]";
	  
	  protected final static String SIXENDPOSITION = "[SixEndPosition]";
	  
	  /**
	   * Construct by using a model
	   * @param m
	   * Model to be saved
	   */
	  public SaveLBManager(Model m){
		  this.model = m;
	  }
	  
	  /**
	   * Save the model data into a text file in a specific format
	   * @throws IOException
	   */
	  public void saveData() throws IOException{
		  // Create an arrayList to store all the info
		  List<String> data = new ArrayList<String>();
		  
		  // level
		  data.add(LEVEL);
		  data.add("" + model.getLevel().getValue());
		  
		  // type
		  data.add(TYPE);
		  data.add("" + model.getType().getValue());
		  
		  // limit
		  data.add(LIMIT);
		  if (model.getType().getValue() == Model.Lightning){		  
			  data.add("" + model.getTime().getValue());
		  }
		  else{
			  data.add("" + model.getMove().getValue());
		  }

		  // goalScore
		  data.add(GOALSCORE);
		  data.add("" + model.getGoalScore().getValue());

		  // shape
		  data.add(SHAPE);
		  for (int i = 0; i < 9; i++){
			  data.add(rowToString(columnToRow(model.getShape(), i)));
		  }
		  
		  // Frequency
		  data.add(ONEFREQUENCY);
		  data.add("" + model.getFrequency().getNumFrequency(1).getValue());
		  
		  data.add(TWOFREQUENCY);
		  data.add("" + model.getFrequency().getNumFrequency(2).getValue());
		  
		  data.add(THREEFREQUENCY);
		  data.add("" + model.getFrequency().getNumFrequency(3).getValue());
		  
		  data.add(FOURFREQUENCY);
		  data.add("" + model.getFrequency().getNumFrequency(4).getValue());
		  
		  data.add(FIVEFREQUENCY);
		  data.add("" + model.getFrequency().getNumFrequency(5).getValue());
		  
		  data.add(SIXFREQUENCY);
		  data.add("" + model.getFrequency().getNumFrequency(6).getValue());
		  
		  data.add(XTWOFREQUENCY);
		  data.add("" + model.getFrequency().getX2Frequency().getValue());
		  
		  data.add(XTHREEFREQUENCY);
		  data.add("" + model.getFrequency().getX3Frequency().getValue());
		  
		  // spcialMove
		  data.add(RESET);
		  data.add("" + model.getSpecialMoveBank().getReset().getValue());
		  
		  data.add(REMOVE);
		  data.add("" + model.getSpecialMoveBank().getRemove().getValue());
		  
		  data.add(SWAP);
		  data.add("" + model.getSpecialMoveBank().getSwap().getValue());
		  
		  // six start pos
		  data.add(SIXSTARTPOSITION);
		  for (int i = 0; i < 9; i++){
			  data.add(rowToString(columnToRow(model.getSixStartPos(), i)));
		  }
		  
		  // six end pos
		  data.add(SIXENDPOSITION);
		  for (int i = 0; i < 9; i++){
			  data.add(rowToString(columnToRow(model.getSixEndPos(), i)));
		  }
		  
		  // create save file name base on level
		  this.saveFilePath = Paths.get(SAVEFILE_FOLDER + "level" + model.getLevel().getValue() + SAVEFILE_SUFFIX);
		  // this.saveFilePath = Paths.get(SAVEFILE_FOLDER + "test.txt");
		  Files.write(saveFilePath, data, ENCODING);
	  }
	  
	  /**
	   * Convert a row of boolean into string. True as 1, False as 0.
	   * @param row
	   * Row to convert
	   * @return
	   * String with 1s and 0s separated by ","
	   */
	  private String rowToString(boolean[] row){
		  String s = null;
		  
		  if (row[0]){
			  s = "1,";
		  }
		  else{
			  s = "0,";
		  }
		  
		  for (int i = 1; i < row.length; i++){
			  if (row[i]){
				  s = s + "1,";
			  }
			  else{
				  s = s + "0,";
			  }
		  }
		  
		  return s;
		  
	  }
	  
	  /**
	   * Change a column of a 2D array to a row
	   * @param shape
	   * 2D array
	   * @param col
	   * Column number
	   * @return
	   * 1D array
	   */
	  private boolean[] columnToRow(boolean[][] shape, int col){
		  boolean[] newRow = new boolean[shape.length]; 
		  
		  for (int i = 0; i < shape.length; i ++){
			  	newRow[i] = shape[i][col];
		  }
		  
		  return newRow;
	  }
	  
}
