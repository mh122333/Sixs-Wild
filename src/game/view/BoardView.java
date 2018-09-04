package game.view;

import game.entity.Game;
import game.entity.Level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import levelbuilder.model.Position;

/**
 * Board view during the game play. 
 * @author Weijia Tao
 *
 */
public class BoardView extends JPanel{
	/** Game related to this view */
	protected Game game;
	
	/** Square images for 1-6 */
	protected Image[] squareImages = new Image[6];
	
	/** X2 bonus image*/
	protected Image X2Image;
	
	/** X3 bonus image*/
	protected Image X3Image;
	
	/** Image when cell is selected */
	protected Image selectedImage;
	
	/** Image when cell is eliminated */
	protected Image eliminatedImage;
	
	/** Image for six end position cell */
	protected Image sixEndImage;
	
	protected SquareView[][] squareViews = new SquareView[9][9];
	
	/** Keep track whether the board needs to be initialized */
	protected boolean initialized;
	
	/** Off screen graphics for the entire board */
	protected Graphics offscreenGraphics;
	
	/** Off screen iamge for the entire board */
	protected Image offscreenImage;
	
	/** Actual graphics for the entire board */
	protected Graphics actualGraphics;
	
	// Size of the board view and image size
	protected final int width = 630;
	
	protected final int height = 630;
	
	protected final int squareSize = 70;
	
	/**
	 * Initialized the board view 
	 * @param g
	 * Game related to this view
	 */
	public BoardView(Game g){
		this.game = g;
		this.initialized = false;
		
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
		
		loadImages();	
	}
	
	/**
	 * Requieres the board to be initialized again
	 */
	public void setUninitialized(){
		this.initialized = false;
	}
	
	/**
	 * Update the entire board view
	 */
	public void update(){
		// System.out.println(level.getLevel());
		updateAllSquares(game.getCurrentLevel());
		redraw();
		repaint();
	}
	
	/**
	 * Update all the squares on the board based on the given level
	 * @param level
	 * Level related to the board
	 */
	private void updateAllSquares(Level level){
		if (!initialized){
			
		// Initialize all cells 
		for (int x = 0; x < 9; x++){
			for (int y = 0; y < 9; y++){
				// If valid cell then create squareView
				if(level.getBoard().getCells()[x][y].getValid()){
					squareViews[x][y] = new SquareView(new Position(x+1,y+1), squareSize, 
							                           true,
							                           level.getBoard().getCells()[x][y].getSquare().getNumber(),
							                           level.getBoard().getCells()[x][y].getSquare().getBonus());
					
					// Set eliminated status
					if (level.getBoard().getEliminatedCells() != null){
						squareViews[x][y].setEliminated(false);
					}
					else{
						squareViews[x][y].setEliminated(true);
					}
				}
				else{
					// Set squareView as not valid
					squareViews[x][y] = new SquareView(new Position(x+1,y+1), squareSize, 
	                           false,
	                           0,
	                           0);
					// Set eliminated
					squareViews[x][y].setEliminated(true);
					// Set six end position
					squareViews[x][y].setEndSix(level.getBoard().getCells()[x][y].getEndSix());
				}
			}
		}
		
		this.initialized = true;
		
		}
		else{
			// update
			for (int x = 0; x < 9; x++){
				for (int y = 0; y < 9; y++){
					if (squareViews[x][y].getValid()){
						squareViews[x][y].setNumber(level.getBoard().getCells()[x][y].getSquare().getNumber());
						squareViews[x][y].setBonus(level.getBoard().getCells()[x][y].getSquare().getBonus());
						squareViews[x][y].setSelected(level.getBoard().getCells()[x][y].getSelected());
						
						if (level.getBoard().getEliminatedCells() != null){
							squareViews[x][y].setEliminated(level.getBoard().getEliminatedCells()[x][y]);
						}
					}
					else{
						// update validness for six end position square
						squareViews[x][y].setValid(level.getBoard().getCells()[x][y].getValid());
						squareViews[x][y].setEndSix(level.getBoard().getCells()[x][y].getEndSix());
						// if is six end position, update the number to be shown
						if (squareViews[x][y].getValid()){
							squareViews[x][y].setNumber(level.getBoard().getCells()[x][y].getSquare().getNumber());
							squareViews[x][y].setBonus(level.getBoard().getCells()[x][y].getSquare().getBonus());
						}
					}
				}
			}
		}
	}
	
	/**
	 * Load all images
	 */
	private void loadImages(){
		for (int i = 0; i < 6; i++)
		try{
			squareImages[i] = ImageIO.read(new File("images"+File.separator+"square_" + (i+1) + ".png"));
			sixEndImage = ImageIO.read(new File("images"+File.separator+"six-end-overlay.png"));
			selectedImage = ImageIO.read(new File("images"+File.separator+"square_selected.png"));
			eliminatedImage = ImageIO.read(new File("images"+File.separator+"square_eliminated.png"));
			X2Image = ImageIO.read(new File("images"+File.separator+"bonus_2.png"));
			X3Image = ImageIO.read(new File("images"+File.separator+"bonus_3.png"));
		} catch (IOException e){
			System.out.println("No image found");
		}
	}
	
	/** Make sure that image is created as needed. */
	void ensureImageAvailable(Graphics g) {
		if (offscreenImage == null) {
			offscreenImage = this.createImage(this.getWidth(), this.getHeight());
			offscreenGraphics = offscreenImage.getGraphics();
			actualGraphics = g;
			
			redraw();
		}
	}
	
	/**
	 * Redraw all the board view
	 */
	public void redraw() {
		// nothing to draw into? Must stop here.
		if (offscreenImage == null) return;
		
		// clear the image.
		offscreenGraphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		// Make sure offscreenImage is white
		offscreenGraphics.setColor(Color.WHITE);
		offscreenGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
		// Draw all squares
		for (int x = 0; x < 9; x++){
			for (int y = 0; y < 9; y++){
				if (squareViews[x][y].getValid()){
					offscreenGraphics.drawImage(squareImages[squareViews[x][y].getNumber()-1], squareViews[x][y].getAbsPosition().getX(), squareViews[x][y].getAbsPosition().getY(), this);
					
					int bonus = squareViews[x][y].getBonus();
					switch(bonus){
					case 2: 
						offscreenGraphics.drawImage(X2Image, squareViews[x][y].getAbsPosition().getX(), squareViews[x][y].getAbsPosition().getY(), this);
						break;
					case 3: 
						offscreenGraphics.drawImage(X3Image, squareViews[x][y].getAbsPosition().getX(), squareViews[x][y].getAbsPosition().getY(), this);
						break;		
					default:
						break;
					}
					
					// If is selected
					if (squareViews[x][y].getSelected()){
						// Overlay selected image
						offscreenGraphics.drawImage(selectedImage, squareViews[x][y].getAbsPosition().getX(), squareViews[x][y].getAbsPosition().getY(), this);
					}
					
					// If is six end position
					if (squareViews[x][y].getEndSix()){
						// Overlay six end image
						offscreenGraphics.drawImage(sixEndImage, squareViews[x][y].getAbsPosition().getX(), squareViews[x][y].getAbsPosition().getY(), this);
					}
					
					// If is not eliminated
					if (!squareViews[x][y].getEliminated()){
						offscreenGraphics.drawImage(eliminatedImage, squareViews[x][y].getAbsPosition().getX(), squareViews[x][y].getAbsPosition().getY(), this);
					}
				}
				else{
					// If is six end position
					if (squareViews[x][y].getEndSix()){
						// Overlay six end image
						offscreenGraphics.drawImage(sixEndImage, squareViews[x][y].getAbsPosition().getX(), squareViews[x][y].getAbsPosition().getY(), this);
					}
				}
			}
		}
	}
	
	/**
	 * Override to include the baord view image
	 */
	public void paint(Graphics g){
		ensureImageAvailable(g);
		g.drawImage(offscreenImage, 0, 0, getWidth(), getHeight(), this);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public SquareView[][] getSquareView(){
		return this.squareViews;
	}
	
	/**
	 * Find the relative position in the board
	 * @param x
	 * Horizontal absolute location 
	 * @param y
	 * Vertical absolute location 
	 * @return
	 * Relative position
	 */
	public Position getClickPosition(int x, int y){
		int col = x/squareSize;
		int row = y/squareSize;
		
		Position pos = new Position(col, row);
		return pos;
	}
	
}
