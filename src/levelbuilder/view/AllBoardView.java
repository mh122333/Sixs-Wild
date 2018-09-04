package levelbuilder.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import levelbuilder.model.Model;
import levelbuilder.model.Position;

/**
 * Board view that contains shape, six start and six end board views.
 * @author Weijia Tao
 *
 */
public class AllBoardView extends JPanel{
	/** Image for the empty cell */
	protected Image cellImg;
	
	/** Image for the cell when marked as shape */
	protected Image cellShapeImg;
	
	/** Image for the cell when marked as six start position */
	protected Image sixStartImg;
	
	/** Image for the cell when marked as six end position */
	protected Image sixEndImg;
	
	/** Current image for the cell */
	protected Image currentCellImg;
	
	/** Off screen graphics for the image */
	protected Graphics offscreenGraphics;
	
	/** Off screen image */
	protected Image offscreenImage;
	
	/** Actual graphics */
	Graphics actualGraphics;
	
	/** Model to be modified */
	protected Model model;
	
	// Size of the board view and image offset
	protected final int width = 270;
	
	protected final int height = 270;
	
	protected final int cellSize = 30;
	
	/**
	 * Constructor 
	 * @param m
	 * Model to be modified
	 */
	public AllBoardView(Model m){
		this.model = m;
		
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		setLayout(null);
				
		try{
			cellImg = ImageIO.read(new File("images" + File.separator + "cell.png"));
			cellShapeImg = ImageIO.read(new File("images"+File.separator+"cell-over.png"));
			sixEndImg = ImageIO.read(new File("images"+File.separator+"cell-end.png"));
			sixStartImg = ImageIO.read(new File("images"+File.separator+"cell-start.png"));
		} catch (IOException e){
			System.out.println("No image found");
		}
		
		// Set initial cell image
		currentCellImg = cellImg;
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
	 * Redraw board view
	 */
	public void redraw(){
		// nothing to draw into? Must stop here.
		if (offscreenImage == null) return;
		
		// clear the image.
		offscreenGraphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		// Draw all cells
		updateBoard();
	}
		
	
	/**
	 * Redraw a single cell view in the board
	 * @param x
	 * horizontal location of the cell
	 * @param y
	 * vertical location of the cell
	 */
	public void redraw(int x, int y) {
		// nothing to draw into? Must stop here.
		if (offscreenImage == null) return;
		
		// clear the cell place
		offscreenGraphics.clearRect(getAbsPosition(x, y).getX(), getAbsPosition(x, y).getY(), cellSize, cellSize);
		
		// Draw the cell
		offscreenGraphics.drawImage(currentCellImg, getAbsPosition(x, y).getX(), getAbsPosition(x, y).getY(), this);
	}
	
	/**
	 * Paint method for the JPanel
	 */
	@Override
	public void paint(Graphics g){
		ensureImageAvailable(g);
		g.drawImage(offscreenImage, 0, 0, getWidth(), getHeight(), this);
	}
	
	/**
	 * Override the default width method
	 */
	@Override
	public int getWidth(){
		return width;
	}
	
	/**
	 * Override the default height method
	 */
	@Override
	public int getHeight(){
		return height;
	}
	
	/**
	 * Given x, y absolute position, returns the relative position in the board
	 * @param x
	 * horizontal absolute position
	 * @param y
	 * vertical absolute position
	 * @return
	 * Relative position on the board
	 */
	public Position clickOnCell(int x, int y){
		int col = x/30;
		int row = y/30;
		
		Position pos = new Position(col, row);
		return pos;
	}
	
	/**
	 * Update the board view based on the model
	 */
	public void updateBoard(){
		// check all the cells
		for (int col = 0; col < 9; col ++){
			for (int row = 0; row < 9; row ++){
				// check startPos
				if(model.getSixStartPos()[col][row]){
					currentCellImg = sixStartImg;
					redraw(col, row);
				}
				// check endPos
				else if(model.getSixEndPos()[col][row]){
					currentCellImg = sixEndImg;
					redraw(col, row);
				}
				// check shape
				else if (model.getShape()[col][row]){
					currentCellImg = cellShapeImg;
					redraw(col, row);
				}
				// if all not set, just show the blank cell
				else{
					currentCellImg = cellImg;
					redraw(col, row);
				}
			}
		}
		
		repaint();
	}
	
	/**
	 * Given the relative position of a cell, calculate the absolute position on the board view
	 * @param x
	 * Horizontal relative position
	 * @param y
	 * Vertical relative position
	 * @return
	 * Absolute position on the board view
	 */
	private Position getAbsPosition(int x, int y){
		int abs_x = x*cellSize;
		int abs_y = y*cellSize;
		
		return new Position(abs_x,abs_y);
	}
	
}
