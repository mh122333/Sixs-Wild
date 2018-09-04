package game.view;

import game.controller.*;
import game.entity.Game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * High level application panel for all the views.
 * @author Weijia Tao
 *
 */
public class ApplicationPanel extends JPanel{
	/** Game related to this view */
	protected Game game;
	
	/** Splash screen view */
	protected SplashScreenView splashScreenView;
	
	/** Mode select view (Play/Accomplishment) */
	protected ModeSelectView modeSelectView;
	
	/** Level selection view */
	protected LevelSelectView levelSelectView;
	
	/** Accomplishments view */
	protected AccListView accListView;
	
	/** Actual gaming view */
	protected PlayView playView;
	
	/** Result view after game is finished */
	protected ResultView resultView;
	
	/** Card layout is used */
	protected CardLayout layout;
	
	protected final String MODESELECT_PANEL = "Mode Select View";
	protected final String LEVELSELCT_PANEL = "Level Select View";
	protected final String ACCLIST_PANEL = "ACC List View";
	protected final String PLAY_PANEL = "Play View";
	protected final String RESULT_PANEL = "Result View";
	protected final String SPLASHSCREEN_PANEL = "Splash Screen View";
	
	/**
	 * Create the application panel, add all controllers
	 * @param g
	 * Game related to this view
	 */
	public ApplicationPanel(Game g){
		this.game = g;
		
		setPreferredSize(new Dimension(1280, 720));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setBackground(Color.WHITE);
		
		layout = new CardLayout();
		setLayout(layout);
		
		// add all views
		splashScreenView = new SplashScreenView();
		add(splashScreenView, SPLASHSCREEN_PANEL);
		
		modeSelectView = new ModeSelectView();
		add(modeSelectView, MODESELECT_PANEL);
		
		levelSelectView = new LevelSelectView(game);
		add(levelSelectView, LEVELSELCT_PANEL);
		
		accListView = new AccListView(game);
		add(accListView, ACCLIST_PANEL);
		
		playView = new PlayView(game);
		add(playView, PLAY_PANEL);
		
		resultView = new ResultView(game);
		add(resultView, RESULT_PANEL);
		
		layout.show(this, SPLASHSCREEN_PANEL);
		
		// add controllers
		splashScreenView.getGoButton().addActionListener(new ShowModeSelectViewController(this));
		modeSelectView.getLevelButton().addActionListener(new ShowLevelSelectViewController(this));
		modeSelectView.getAccButton().addActionListener(new ShowAccListViewController(game, this));
		
		levelSelectView.getLevelListView().getLevel1Button().addActionListener(new ShowPlayViewController(game,1,this));
		levelSelectView.getLevelListView().getLevel2Button().addActionListener(new ShowPlayViewController(game,2,this));
		levelSelectView.getLevelListView().getLevel3Button().addActionListener(new ShowPlayViewController(game,3,this));
		levelSelectView.getLevelListView().getLevel4Button().addActionListener(new ShowPlayViewController(game,4,this));
		levelSelectView.getLevelListView().getLevel5Button().addActionListener(new ShowPlayViewController(game,5,this));
		levelSelectView.getLevelListView().getLevel6Button().addActionListener(new ShowPlayViewController(game,6,this));
		
		levelSelectView.getBackButton().addActionListener(new ShowModeSelectViewController(this));
		levelSelectView.getNextButton().addActionListener(new LevelSelectViewNextController(this));
		levelSelectView.getPrevButton().addActionListener(new LevelSelectViewPrevController(this));
		
		// Play Controller
		playView.getBackButton().addActionListener(new ShowLevelSelectViewController(this));
		BoardController bc = new BoardController(game, this);
		playView.getBoardView().addMouseMotionListener(bc);
		playView.getBoardView().addMouseListener(bc);
		playView.getRemoveButton().addActionListener(new RemoveButtonController(game, this));
		playView.getSwapButton().addActionListener(new SwapButtonController(game, this));
		playView.getResetButton().addActionListener(new ResetButtonController(game, this));
		
		// Result Controller
		resultView.getLevelButton().addActionListener(new ShowLevelSelectViewController(this));
		resultView.getRetryButton().addActionListener(new RetryButtonController(game,this));
		resultView.getContinueButton().addActionListener(new ContinueButtonController(game, this));
		
		// Accomplishment view
		accListView.getBackButton().addActionListener(new ShowModeSelectViewController(this));
		accListView.getNextButton().addActionListener(new AccListViewNextController(this));
		accListView.getPrevButton().addActionListener(new AccListViewPrevController(this));
	}
	
	public void showModeSelctView(){
		layout.show(this, MODESELECT_PANEL);
	}
	
	public void showLevelSelctView(){
		layout.show(this, LEVELSELCT_PANEL);
	}
	
	public void showAccListView(){
		layout.show(this, ACCLIST_PANEL);
	}
	
	public void showPlayView(){
		layout.show(this, PLAY_PANEL);
	}
	
	public void showResultView(){
		layout.show(this, RESULT_PANEL);
	}
	
	public LevelSelectView getLevelSelectView(){
		return levelSelectView;
	}
	
	public PlayView getPlayView(){
		return playView;
	}
	
	public ResultView getResultView(){
		return resultView;
	}
	
	public AccListView getAccListView(){
		return accListView;
	}
	public ModeSelectView getModeSelectView(){
		return modeSelectView;
	}
	public SplashScreenView getSplashScreenView(){
		return splashScreenView;
	}
}
