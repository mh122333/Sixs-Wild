package timer;

import levelbuilder.model.Model;
import game.entity.Game;
import game.view.ApplicationPanel;

/**
 * Class which is able to start the timer for the lightning mode
 * @author Fuchen Chen
 *
 */
public class LightningTimerStart {
	protected Game game;
	
	protected ApplicationPanel appPanel;
	
	public LightningTimerStart(Game g, ApplicationPanel appPanel){
		this.game = g;
		this.appPanel = appPanel;
	}
	
	public void start(){
		if (game.getCurrentLevel().getType() == Model.Lightning){
			LightningTimer t = LightningTimer.getInstance();
			
			LightningTimerTask task = new LightningTimerTask(game, appPanel);
			
			t.schedule(task, 1000);
		}
	}
}
