package levelbuilder.test;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import junit.framework.TestCase;
import levelbuilder.controller.BoardNavigationController;
import levelbuilder.controller.DetailNavigationController;
import levelbuilder.controller.EditLevelController;
import levelbuilder.controller.BoardViewController;
import levelbuilder.controller.NewLevelController;
import levelbuilder.controller.RedoController;
import levelbuilder.controller.TextFieldController;
import levelbuilder.controller.ToSplashScreenController;
import levelbuilder.controller.UndoController;
import levelbuilder.model.Model;
import levelbuilder.model.Position;
import levelbuilder.move.ModifyShape;
import levelbuilder.move.ModifySixEnd;
import levelbuilder.move.ModifySixStart;
import levelbuilder.view.Application;

/**
 * Test for the level builder
 * @author Zhaochen Ding
 *
 */
public class LevelbuilderTest extends TestCase {
	Model model;
	Application app;
	
	/**
	 * Set up the game 
	 */
	protected void setUp(){
		model = new Model();
		app = new Application(model);
		app.setVisible(true);
	}
	
	/**
	 * Dispose the game 
	 */
	protected void tearDown(){
		app.dispose();
	}
	
	public void testModifying(){
		Position p1 = new Position(5, 5);
		ModifyShape ms = new ModifyShape(p1, model);
		assertTrue(ms.execute());
		Position p2 = new Position(6, 6);
		ModifyShape ms1 = new ModifyShape(p2, model);
		assertTrue(ms1.execute());
		ModifySixStart mss = new ModifySixStart(p1, model);
		ModifySixEnd mse = new ModifySixEnd(p2, model);
		assertTrue(mss.execute());
		assertTrue(mss.undo());
		assertTrue(mse.execute());
		assertTrue(mse.undo());
		assertTrue(ms.undo());
		assertTrue(ms1.undo());
		
	}
	
	public void testNewLevelView(){
		ActionEvent ae1 = new ActionEvent(app.getAppPanel().getSplashScreenView().getNewLevelButton(),ActionEvent.ACTION_PERFORMED, "Into New Level");
		NewLevelController nlc = new NewLevelController(model, app.getAppPanel());		
		nlc.actionPerformed(ae1);		
		assertTrue(app.getAppPanel().getBasicSettingView().isShowing());
		assertFalse(app.getAppPanel().getDetailSettingView().isShowing());
		assertFalse(app.getAppPanel().getBoardSettingView().isShowing());
		assertTrue(app.getAppPanel().getBasicSettingView().getLevelTextField().isShowing());
		assertTrue(app.getAppPanel().getBasicSettingView().getTypeTextField().isShowing());
		assertFalse(app.getAppPanel().getDetailSettingView().getMoveTF().getText().isEmpty());
		assertFalse(app.getAppPanel().getDetailSettingView().getTimeTF().isShowing());
		
		ActionEvent ae2 = new ActionEvent(app.getAppPanel().getNavigationView().getDetailButton(),ActionEvent.ACTION_PERFORMED, "Into detail setting");
		DetailNavigationController dnc = new DetailNavigationController(model, app.getAppPanel());
		dnc.actionPerformed(ae2);
		assertFalse(app.getAppPanel().getBasicSettingView().isShowing());
		assertTrue(app.getAppPanel().getDetailSettingView().isShowing());
		assertFalse(app.getAppPanel().getBoardSettingView().isShowing());
		
		ActionEvent ae3 = new ActionEvent(app.getAppPanel().getNavigationView().getBoardButton(),ActionEvent.ACTION_PERFORMED, "Into detail setting");
		BoardNavigationController bnc = new BoardNavigationController(model, app.getAppPanel());
		bnc.actionPerformed(ae3);
		assertFalse(app.getAppPanel().getBasicSettingView().isShowing());
		assertFalse(app.getAppPanel().getDetailSettingView().isShowing());
		assertTrue(app.getAppPanel().getBoardSettingView().isShowing());
		
		ActionEvent ae4 = new ActionEvent(app.getAppPanel().getBasicSettingView().getBackButton(), ActionEvent.ACTION_PERFORMED, "Back to SplashScreen");
		ToSplashScreenController tc = new ToSplashScreenController(model, app.getAppPanel());
		tc.actionPerformed(ae4);
		
		assertTrue(app.getAppPanel().getSplashScreenView().isShowing());
		
		TextFieldController tfc = new TextFieldController(model, model.getType(), 
                app.getAppPanel().getBasicSettingView().getTypeTextField(),  app.getAppPanel());
		app.getAppPanel().getBasicSettingView().getTypeTextField().setText("4");
		tfc.focusLost(new FocusEvent(app.getAppPanel().getBasicSettingView().getTypeTextField(), FocusEvent.FOCUS_LOST));
		bnc.actionPerformed(ae3);
		
		assertTrue(app.getAppPanel().getBoardSettingView().getSixStartButton().isShowing());
		assertTrue(app.getAppPanel().getBoardSettingView().getSixEndButton().isShowing());
	}
	
	public void testEditLevelView(){
		ActionEvent ae = new ActionEvent(app.getAppPanel().getSplashScreenView().getEditLevelButton(),ActionEvent.ACTION_PERFORMED, "Into New Level");
		EditLevelController ec = new EditLevelController(model, app.getAppPanel());		
		ec.actionPerformed(ae);
		assertTrue(app.getAppPanel().getBasicSettingView().isShowing());
		assertFalse(app.getAppPanel().getDetailSettingView().isShowing());
		assertTrue(app.getAppPanel().getBasicSettingView().getLevelList().isShowing());
		assertTrue(app.getAppPanel().getBasicSettingView().getTypeTextField().isShowing());
		assertFalse(app.getAppPanel().getDetailSettingView().getMoveTF().getText().isEmpty());
		assertFalse(app.getAppPanel().getDetailSettingView().getTimeTF().isShowing());
	}
	
	public void testBoard(){
		MouseEvent me = new MouseEvent(app.getAppPanel().getBoardSettingView().getAllBoardView(),MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				5, 5, 0, false);
		BoardViewController bc = new BoardViewController(model, app.getAppPanel());
		
		bc.mousePressed(me);
		
		assertFalse(model.getShape()[0][0]);
	}
	
	public void testFrequency(){
		TextFieldController tfc = new TextFieldController(model, model.getFrequency().getNumFrequency(1), 
				                                          app.getAppPanel().getDetailSettingView().getOneTF(),  app.getAppPanel());
		app.getAppPanel().getDetailSettingView().getOneTF().setText("10");
		tfc.focusLost(new FocusEvent(app.getAppPanel().getDetailSettingView().getOneTF(), FocusEvent.FOCUS_LOST));
		assertEquals("10",app.getAppPanel().getDetailSettingView().getOneTF().getText());
		
		UndoController undoC = new UndoController(model, app.getAppPanel());
		undoC.process();
		assertEquals("0",app.getAppPanel().getDetailSettingView().getOneTF().getText());
		
		RedoController redoC = new RedoController(model, app.getAppPanel());
		redoC.process();
		assertEquals("10",app.getAppPanel().getDetailSettingView().getOneTF().getText());
		
		app.getAppPanel().getDetailSettingView().getOneTF().setText("1000");
		tfc.focusLost(new FocusEvent(app.getAppPanel().getDetailSettingView().getOneTF(), FocusEvent.FOCUS_LOST));
		assertEquals("10",app.getAppPanel().getDetailSettingView().getOneTF().getText());
		
	}
}