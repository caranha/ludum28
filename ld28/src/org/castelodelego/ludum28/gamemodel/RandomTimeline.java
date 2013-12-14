package org.castelodelego.ludum28.gamemodel;

import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.entities.BasicEnemy;
import org.castelodelego.ludum28.parallax.ParallaxBackground;
import org.castelodelego.ludum28.parallax.ParallaxLayer;
import org.castelodelego.ludum28.screens.GameScreen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class RandomTimeline implements StageTimeline {

	float CD_Timer = 0.7f;
	float cooldown = 0;
	float totaltime = 0;
	
	@Override
	public void reset(int difficulty) {
		cooldown = 0;
		totaltime = 0;
	}

	@Override
	public void update(float delta) {
		cooldown += delta;
		totaltime += delta;
		if (cooldown > CD_Timer)
		{
			Vector2 pos = new Vector2(Constants.SCREEN_W,Globals.dice.nextFloat()*Constants.SCREEN_H/2+100);
			
			BasicEnemy mook = new BasicEnemy(pos);
			mook.goUp(Globals.dice.nextBoolean());
			mook.setScore(3);
			
			((GameScreen) Ludum28.gameScreen).addFlyer(mook);
			cooldown -= CD_Timer;
		}
	}

	@Override
	public boolean testWin() {
		return totaltime > 20;
	}
	
	public void setSpeed(float s)
	{
		CD_Timer = s;
	}

	@Override
	public ParallaxBackground getParallax() {
		
		TextureAtlas atlas = Globals.manager.get("sprites/pack.atlas", TextureAtlas.class);
		ParallaxBackground test = new ParallaxBackground(new ParallaxLayer[]{
	            new ParallaxLayer(atlas.findRegion("parallax/Ground3"),new Vector2(0.1f,0),new Vector2(0, Constants.SCREEN_H)),
	            new ParallaxLayer(atlas.findRegion("parallax/Ground2"),new Vector2(0.5f,0),new Vector2(0, Constants.SCREEN_H)),
	            new ParallaxLayer(atlas.findRegion("parallax/Ground1"),new Vector2(1.0f,0),new Vector2(0, Constants.SCREEN_H))
	      }, 800, 480,new Vector2(200,0));
		// TODO Auto-generated method stub
		return test;
	}

}
