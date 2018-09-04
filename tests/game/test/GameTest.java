package game.test;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.xml.ws.handler.MessageContext.Scope;

import game.controller.*;
import game.entity.Cell;
import game.entity.Game;
import game.entity.Move;
import game.entity.PuzzleBoard;
import game.entity.RemoveMove;
import game.entity.ResetMove;
import game.entity.Square;
import game.entity.SwapMove;
import game.view.Application;
import game.view.ApplicationPanel;
import junit.framework.TestCase;
import levelbuilder.model.Frequency;
import levelbuilder.model.Position;

/**
 * Test for the game
 * @author Zhaochen Ding
 *
 */
public class GameTest extends TestCase {
	Game game;
	Application app;

	/**
	 * Set up the game 
	 */
	protected void setUp(){
		app = new Application();
		game = app.getGame();
		app.setVisible(true);
		
	}
	
	/**
	 * Dispose the game 
	 */
	protected void tearDown(){
		app.dispose();
	}
	
	public void testMove(){
		game.setCurrentLevel(1);
		Position p = new Position(3,2);
		Cell c = new Cell(p, true);
		System.out.println("Num level" + game.getLevels().size());
		assertTrue(game.getLevel(1).getBoard().getCell(p).getValid());
		game.getLevel(1).initBoard();
		game.getLevel(1).getShape();
		assertTrue(game.hasNextLevel());
		Square a = c.getSquare();
		RemoveMove rm = new RemoveMove(game);
		assertTrue(rm.add(p));
		assertTrue(rm.doMove());
		SwapMove sm = new SwapMove(game);
		assertTrue(sm.add(p));
		assertTrue(sm.add(new Position(2,2)));
		assertTrue(sm.doMove());
		ResetMove rm1 = new ResetMove (game);
		assertTrue(rm1.doMove());
		assertEquals(game.getCurrentLevel().getBoard().getLimitLeft(), 7);
		
		
	}
	public void testReleaseMove(){
		game.setCurrentLevel(4);
		Position p = new Position(3,2);
		assertTrue(game.getLevel(4).getBoard().getCell(p).getValid());
		game.getLevel(4).initBoard();
		RemoveMove rm = new RemoveMove(game);
		assertTrue(rm.add(p));
		assertTrue(rm.doMove());
		SwapMove sm = new SwapMove(game);
		assertTrue(sm.add(p));
		assertTrue(sm.add(new Position(2,2)));
		assertTrue(sm.doMove());
		ResetMove rm1 = new ResetMove (game);
		assertTrue(rm1.doMove());	
	}
	public void testLightningMove(){
		game.setCurrentLevel(2);
		Position p = new Position (2,3);
		game.getLevel(2).initBoard();
		RemoveMove rm = new RemoveMove(game);
		assertTrue(rm.add(p));
		assertTrue(rm.doMove());
		SwapMove sm = new SwapMove(game);
		assertTrue(sm.add(p));
		assertTrue(sm.add(new Position(2,2)));
		assertTrue(sm.doMove());
		ResetMove rm1 = new ResetMove (game);
		assertTrue(rm1.doMove());
		assertEquals(game.getCurrentLevel().getBoard().getLimitLeft(), 30);
		
		
	}
	public void testController(){
		ApplicationPanel ap  = app.getAppPanel();
		
		/* SplashScreen when First enter */
		assertTrue(ap.getSplashScreenView().getGoButton().isVisible());
		
		ShowLevelSelectViewController sc = new ShowLevelSelectViewController(ap);
		ShowPlayViewController spc = new ShowPlayViewController(game, 1, ap);
		ShowAccListViewController sav = new ShowAccListViewController(game, ap);
		ShowModeSelectViewController smc = new ShowModeSelectViewController(ap);
	 	AccListViewNextController anc = new AccListViewNextController(ap);
		// AccListViewPrevController apc = new AccListViewPrevController(ap);
		BoardController bc = new BoardController(game, ap);
		// ContinueButtonController cbc = new ContinueButtonController(game, ap);
		LevelSelectViewNextController lsnc = new LevelSelectViewNextController(ap);
		LevelSelectViewPrevController lspc = new LevelSelectViewPrevController(ap);
	
		
		/*Splash Screen to Mode Selection */
		ActionEvent ae = new ActionEvent(ap.getSplashScreenView().getGoButton(), ActionEvent.ACTION_PERFORMED, "SplashScreen to ModeSelection");
		smc.actionPerformed(ae);
		assertTrue(ap.getModeSelectView().getLevelButton().isEnabled());
		assertTrue(ap.getModeSelectView().isVisible());
		
		
		/* Mode Selection to Acclist */
		ActionEvent ae0 = new ActionEvent(ap.getModeSelectView().getAccButton(), ActionEvent.ACTION_PERFORMED, "Mode Selection to Acclist");
		sav.actionPerformed(ae0);
		assertTrue(ap.getAccListView().isVisible());
		
		
		/* Acclist Next Prev Button */
		ActionEvent ae1 = new ActionEvent(ap.getAccListView().getNextButton(), ActionEvent.ACTION_PERFORMED, "Next Button in ACClist");
		assertFalse(ap.getAccListView().getPrevButton().isEnabled());
		anc.actionPerformed(ae1);
		assertTrue(ap.getAccListView().getPrevButton().isEnabled());
		assertFalse(ap.getAccListView().getNextButton().isEnabled());
			
		/* ModeSelect View to LevelSelectView */
		ActionEvent ae3 = new ActionEvent(ap.getAccListView().getBackButton(), ActionEvent.ACTION_PERFORMED, "Back to mode selection");
		smc.actionPerformed(ae3);
		assertTrue(ap.getModeSelectView().getLevelButton().isEnabled());
		assertFalse(ap.getLevelSelectView().isShowing());
		
		/* Level Page Selection */
		ActionEvent ae6 = new ActionEvent(ap.getLevelSelectView(), ActionEvent.ACTION_PERFORMED, "Next page or Prev page");
		lsnc.actionPerformed(ae6);
		assertTrue(ap.getLevelSelectView().getNextButton().isVisible());
		assertTrue(ap.getLevelSelectView().getPrevButton().isEnabled());
		
		lspc.actionPerformed(ae6);
		assertFalse(ap.getLevelSelectView().getPrevButton().isEnabled());
		
		/* Into Level List View */
		ActionEvent ae4 = new ActionEvent(ap.getModeSelectView().getLevelButton(), ActionEvent.ACTION_PERFORMED, "Into Level");
		sc.actionPerformed(ae4);
		assertTrue(ap.getLevelSelectView().getLevelListView().getLevel1Button().isEnabled());
		
		/* Enter Level 1 */
		ActionEvent ae5 = new ActionEvent(ap.getLevelSelectView().getLevelListView().getLevel1Button(), ActionEvent.ACTION_PERFORMED, "Enter Level 1");
		spc.actionPerformed(ae5); 
		assertTrue(ap.getPlayView().getResetButton().isEnabled());
		assertEquals(ap.getPlayView().getProgressBarView().getCurrentWidth(), 0);
		assertTrue(ap.getPlayView().getProgressBarView().isVisible());
		assertTrue(ap.getPlayView().getResetButton().isVisible());
		assertTrue(ap.getPlayView().getRemoveButton().isVisible());
		assertTrue(ap.getPlayView().getSwapButton().isVisible());
		assertTrue(ap.getPlayView().getBoardView().getSquareView()[0][0].getValid());
		
		/* Select top left cell and deselect it*/
		MouseEvent me1 = new MouseEvent(ap.getPlayView().getBoardView(),MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				0, 0, 0, false);
		bc.mousePressed(me1);
		assertTrue(ap.getPlayView().getBoardView().getSquareView()[0][0].getSelected());
		MouseEvent me2 = new MouseEvent(ap.getPlayView().getBoardView(),MouseEvent.MOUSE_RELEASED, 
				System.currentTimeMillis(), 0, 
				0, 0, 0, false);
		bc.mouseReleased(me2);
		assertFalse(ap.getPlayView().getBoardView().getSquareView()[0][0].getSelected());
	}	
}