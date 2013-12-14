package org.castelodelego.ludum28.gamemodel;

import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.entities.BasicEnemy;
import org.castelodelego.ludum28.screens.GameScreen;

import com.badlogic.gdx.math.Vector2;

public class RandomTimeline implements StageTimeline {

	float CD_Timer = 0.7f;
	float cooldown = 0;
	float totaltime = 0;
	
	@Override
	public void reset(int difficulty) {
		cooldown = 0;
	}

	@Override
	public void update(float delta) {
		cooldown += delta;
		if (cooldown > CD_Timer)
		{
			Vector2 pos = new Vector2(Constants.SCREEN_W,Globals.dice.nextFloat()*Constants.SCREEN_H/2+100);
			
			BasicEnemy mook = new BasicEnemy(pos, new Vector2(10,10));
			mook.goUp(Globals.dice.nextBoolean());
			
			((GameScreen) Ludum28.gameScreen).addFlyer(mook);
			cooldown -= CD_Timer;
		}
	}

	@Override
	public boolean testWin() {
		return totaltime > 40;
	}
	
	public void setSpeed(float s)
	{
		CD_Timer = s;
	}

}
