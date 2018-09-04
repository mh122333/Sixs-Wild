package timer;

import game.entity.Game;
import game.entity.LightningBoard;
import game.view.ApplicationPanel;

import java.util.TimerTask;

/**
 * Timer task for the lighting timer
 * @author Fuchen Chen
 *
 */
public class LightningTimerTask extends TimerTask{
	protected Game game;
	
	protected ApplicationPanel appPanel;
	
	public LightningTimerTask(Game g, ApplicationPanel appPanel){
		super();
		this.game = g;
		this.appPanel = appPanel;
	}
	
	/**
	 * If there is remaining time, update the view and model.
	 * If not, cancel the timer and update the view and model
	 */
	@Override
	public void run() {
		if (!((LightningBoard) game.getCurrentLevel().getBoard()).decrementTime()){
			LightningTimer t = LightningTimer.getInstance();
			t.cancel();
			
			if (game.getCurrentLevel().isLevelPassed()){
				game.setLevelPlayable(game.getCurrentLevel().getLevel() + 1);
			}
			
			game.addAcc();	
			appPanel.getPlayView().getComboView().stopTimer();
			appPanel.getResultView().update();
			appPanel.showResultView();	
			return;
		}
		
		appPanel.getPlayView().update();
	}
}
