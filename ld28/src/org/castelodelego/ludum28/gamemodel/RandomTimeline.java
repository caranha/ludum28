package org.castelodelego.ludum28.gamemodel;

import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.entities.Flyer;
import org.castelodelego.ludum28.parallax.ParallaxBackground;
import org.castelodelego.ludum28.parallax.ParallaxLayer;
import org.castelodelego.ludum28.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class RandomTimeline implements StageTimeline {

	float BaseTimer = 1.3f;
	float CD_Timer;
	float cooldown = 0;
	float totaltime = 0;
	int difficulty;
	
	@Override
	public void reset(int diff) {
		cooldown = 0;
		totaltime = 0;
		difficulty = diff;
		Gdx.app.log("Difficulty", difficulty+"");
		
		CD_Timer = BaseTimer*(5f/(diff+5f));		
	}

	@Override
	public void update(float delta) {
		cooldown += delta;
		totaltime += delta
				;
		if (cooldown > CD_Timer && !testWin()) // create an enemy
		{
			Flyer mook;
			
			if (Globals.dice.nextDouble() < 0.9)
			{				
				Vector2 pos = new Vector2(Constants.SCREEN_W,Globals.dice.nextFloat()*Constants.SCREEN_H/2+100);
				mook = EnemyFactory.getBasicEnemy(difficulty);
				mook.setPosition(pos);
			}
			else
			{				
				Vector2 pos = new Vector2(Constants.SCREEN_W+20,Globals.dice.nextFloat()*100+15);
				mook = EnemyFactory.getTankEnemy(difficulty);
				mook.setPosition(pos);
			}	
			
			((GameScreen) Ludum28.gameScreen).addFlyer(mook);
			cooldown -= CD_Timer;
		}
		
		
	}

	@Override
	public boolean testWin() {
		return totaltime > 25+difficulty*2;
	}
	
	public void setSpeed(float s)
	{
		BaseTimer = s;
	}

	@Override
	public ParallaxBackground getParallax() {
		
		TextureAtlas atlas = Globals.manager.get("sprites/pack.atlas", TextureAtlas.class);
		ParallaxBackground test = new ParallaxBackground(new ParallaxLayer[]{
	            new ParallaxLayer(atlas.findRegion("parallax/Ground3"),new Vector2(0.1f,0),new Vector2(0, Constants.SCREEN_H)),
	            new ParallaxLayer(atlas.findRegion("parallax/Ground2"),new Vector2(0.5f,0),new Vector2(0, Constants.SCREEN_H)),
	            new ParallaxLayer(atlas.findRegion("parallax/Ground1"),new Vector2(1.0f,0),new Vector2(0, Constants.SCREEN_H))
	      }, 800, 480,new Vector2(200,0));
		return test;
	}

	@Override
	public Music getStartMusic() {
		return Globals.manager.get("music/play1.ogg",Music.class);
	}

}
