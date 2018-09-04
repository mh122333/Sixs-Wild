package levelbuilder.view;

import java.awt.Dimension;
import javax.swing.*;
import levelbuilder.model.*;
import levelbuilder.controller.*;

/**
 * High level application panel which contains all the view for the level builder
 * @author Weijia Tao
 *
 */
public class ApplicationPanel extends JPanel{
	/** Splash screen view */
	protected SplashScreenView splashView;
	
	/** Basic Setting view */
	protected BasicSettingView basicView;
	
	/** Board Setting view */
	protected BoardSettingView boardView;
	
	/** Detail Setting view */
	protected DetailSettingView detailView;
	
	/** Navigation view */
	protected NavigationView navigationView;
	
	/** Current visible view */
	protected JPanel currentView;
	
	/** Model related to these views */
	protected Model model;
	
	/**
	 * Create all the view and add all the controllers. 
	 * @param m
	 * Model related to this application panel
	 */
	public ApplicationPanel(Model m) {
		this.model = m;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(450, 450));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		
		/** Splash screen view */
		splashView = new SplashScreenView();
		
		/** Navigation Button View*/
		navigationView = new NavigationView();
		
		/** Basic Setting View*/
		basicView = new BasicSettingView(model);
		basicView.setFocusable(true);
		basicView.refresh();
		
		/** Detail Setting View*/
		detailView = new DetailSettingView(model);
		detailView.setFocusable(true);
		detailView.refresh();
		
		/** Board Setting View*/
		boardView = new BoardSettingView(model);
		boardView.setFocusable(true);
		
		// add splash screen button controller
		splashView.getNewLevelButton().addActionListener(new NewLevelController(model, this));
		splashView.getEditLevelButton().addActionListener(new EditLevelController(model, this));
		
		// add navigation buttons listener
		navigationView.getBasicButton().addActionListener(new BasicNavigationController(model, this));
		navigationView.getDetailButton().addActionListener(new DetailNavigationController(model, this));
		navigationView.getBoardButton().addActionListener(new BoardNavigationController(model, this));
		
		// add board view controller
		boardView.getAllBoardView().addMouseListener(new BoardViewController(model, this));
		
		// add board setting view button controller
		boardView.getBoardShapeButton().addActionListener(new BoardShapeButtonController(model, this));
		boardView.getSixStartButton().addActionListener(new BoardSixStartButtonController(model, this));
		boardView.getSixEndButton().addActionListener(new BoardSixEndButtonController(model, this));
		
		
		// add text fields controller
		addBasicViewTFActionListener();
		basicView.getLevelList().addItemListener(new LevelListController(model, this));
		basicView.getBackButton().addActionListener(new ToSplashScreenController(model, this));
		addDetailViewTFActionListener();
		
		// add focus controller
		basicView.addMouseListener(new BasicSettingFocusController(basicView));
		detailView.addMouseListener(new DetailSettingFocusController(detailView));
		
		// add undo controller
		navigationView.getUndoButton().addActionListener(new UndoButtonController(model, this));
		navigationView.getRedoButton().addActionListener(new RedoButtonController(model, this));
		
		// add save controller
		basicView.getSaveButton().addActionListener(new SaveButtonController(model, this));
		detailView.getSaveButton().addActionListener(new SaveButtonController(model, this));
		boardView.getSaveButton().addActionListener(new SaveButtonController(model, this));
		
		// add preview controller 
		boardView.getPreviewButton().addActionListener(new PreviewButtonController(model, this));
		
		// Initialize basic view first
		currentView = splashView;
		add(currentView);
	}
	
	public void addBasicViewTFActionListener(){
		basicView.getLevelTextField().addFocusListener(new TextFieldController(model,model.getLevel(),basicView.getLevelTextField(), this));
		basicView.getTypeTextField().addFocusListener(new TextFieldController(model,model.getType(),basicView.getTypeTextField(), this));
	}
	
	public void addDetailViewTFActionListener(){
		detailView.moveTF.addFocusListener(new TextFieldController(model,model.getMove(),detailView.moveTF, this));
		detailView.timeTF.addFocusListener(new TextFieldController(model,model.getTime(),detailView.timeTF, this));
		detailView.scoreTF.addFocusListener(new TextFieldController(model,model.getGoalScore(),detailView.scoreTF, this));
		
		detailView.oneTF.addFocusListener(new TextFieldController(model,model.getFrequency().getNumFrequency(1),detailView.oneTF, this));
		detailView.twoTF.addFocusListener(new TextFieldController(model,model.getFrequency().getNumFrequency(2),detailView.twoTF, this));
		detailView.threeTF.addFocusListener(new TextFieldController(model,model.getFrequency().getNumFrequency(3),detailView.threeTF, this));
		detailView.fourTF.addFocusListener(new TextFieldController(model,model.getFrequency().getNumFrequency(4),detailView.fourTF, this));
		detailView.fiveTF.addFocusListener(new TextFieldController(model,model.getFrequency().getNumFrequency(5),detailView.fiveTF, this));
		detailView.sixTF.addFocusListener(new TextFieldController(model,model.getFrequency().getNumFrequency(6),detailView.sixTF, this));
		
		detailView.xTwoTF.addFocusListener(new TextFieldController(model,model.getFrequency().getX2Frequency(),detailView.xTwoTF, this));
		detailView.xThreeTF.addFocusListener(new TextFieldController(model,model.getFrequency().getX3Frequency(),detailView.xThreeTF, this));
		
		detailView.resetTF.addFocusListener(new TextFieldController(model,model.getSpecialMoveBank().getReset(),detailView.resetTF, this));
		detailView.removeTF.addFocusListener(new TextFieldController(model,model.getSpecialMoveBank().getRemove(),detailView.removeTF, this));
		detailView.swapTF.addFocusListener(new TextFieldController(model,model.getSpecialMoveBank().getSwap(),detailView.swapTF, this));
		
	}
	
	/**
	 * Set the splash screen view visible
	 */
	public boolean showSplashScreenView(){
		remove(currentView);
		remove(navigationView);
		currentView = splashView;
		add(currentView);
		revalidate();
		repaint();
		return true;
	}
	
	
	/**
	 * Set the navigation view visible
	 */
	public boolean showNavigationView(){
		add(navigationView);
		revalidate();
		repaint();
		return true;
	}
	
	/**
	 * Set the basic setting view visible
	 */
	public boolean showBasicView(){
		remove(currentView);
		currentView = basicView;
		add(currentView);
		revalidate();
		repaint();
		return true;
	}
	
	
	/**
	 * Set the detail setting view visible
	 */
	public boolean showDetailView(){
		remove(currentView);
		currentView = detailView;
		add(currentView);
		revalidate();
		repaint();
		return true;
	}
	
	/**
	 * Set the board setting view visible
	 */
	public boolean showBoardView(){
		remove(currentView);
		currentView = boardView;
		add(currentView);
		revalidate();
		repaint();
		return true;
	}
	
	/**
	 * Refresh all the views based on model
	 */
	public void refresh(){
		basicView.refresh();
		detailView.refresh();
		boardView.refresh();
	}
	
	public NavigationView getNavigationView(){
		return navigationView;
	}
	
	public BoardSettingView getBoardSettingView(){
		return boardView;
	}
	
	public BasicSettingView getBasicSettingView(){
		return basicView;
	}
	
	public DetailSettingView getDetailSettingView(){
		return detailView;
	}
	
	public SplashScreenView getSplashScreenView(){
		return splashView;
	}
}
