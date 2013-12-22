package org.castelodelego.ludum28.gamemodel;

import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.entities.Flyer;
import org.castelodelego.ludum28.parallax.ParallaxBackground;
import org.castelodelego.ludum28.parallax.ParallaxLayer;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;


/**
 * Generates enemies "randomly" for a stage
 * 
 * Rules: 
 * - Number of enemies per wave is fixed (20+10*wave)
 * - Enemies at the screen at the same time is fixed. (5+2*wave)
 * - Each enemy has its own probability of showing up. (0.6, 0.3, 0.1)
 * - Every 4 waves, things get harder:
 *   - Cooldown reduces
 *   - enemies give more points
 *   - enemies have more speed
 * 
 * @author caranha
 *
 */
public class RandomTimeline implements StageTimeline {

	int wave;
	
	
	int totalenemies;
	int maxenemies;
	int basedifficulty;
	float speedmodifier;
	int scorebonus;

	float spawnMin;
	float spawnDelta;
	float spawntimer;

	float tankchance;
	float dirigiblechance;
	
	int currentenemies = 0;
	
	@Override
	public void reset() {

		wave = Globals.wave;
		
		spawnMin = 0.6f - 0.05f*wave;
		spawnDelta = 0.6f - 0.01f*wave;
		
		tankchance = (float) Math.min(0.4, 0.1f+0.05f*wave);
		dirigiblechance = (float) Math.min(0.65, 0.3f+0.02f*wave);
		
		spawntimer = 0;
		currentenemies = 0;
		
		totalenemies = 20 + 20*wave;
		maxenemies = 8+4*wave;
		
		
		speedmodifier = 1+0.1f*wave;
		scorebonus = wave/2;
	}

	@Override
	public void update(float delta) {
		spawntimer -= delta;
		
		if ((spawntimer < 0) &&
			(currentenemies < totalenemies))
		{
			if (Globals.dice.nextFloat() < tankchance && Ludum28.gameScreen.getTotalEnemies() < maxenemies)
			{
				Vector2 pos = new Vector2(Constants.SCREEN_W+20,Globals.dice.nextFloat()*100+15);
				Flyer mook = EnemyFactory.getTankEnemy(Globals.difficulty);
				mook.setPosition(pos);
				mook.setSpeed(mook.getSpeed()*speedmodifier);
				mook.setScore(mook.getScore()+scorebonus);
				Ludum28.gameScreen.addFlyer(mook);
				currentenemies += 1;
			}
			if (Globals.dice.nextFloat() < dirigiblechance  && Ludum28.gameScreen.getTotalEnemies() < maxenemies)
			{
				Vector2 pos = new Vector2(Constants.SCREEN_W+20,(Globals.dice.nextFloat()*Constants.SCREEN_H*2f/3f)+150);
				Flyer mook = EnemyFactory.getDirigibleEnemy(Globals.difficulty);
				mook.setPosition(pos);
				mook.setSpeed(mook.getSpeed()*speedmodifier);
				mook.setScore(mook.getScore()+scorebonus);
				Ludum28.gameScreen.addFlyer(mook);
				currentenemies += 1;
			}
			if (Ludum28.gameScreen.getTotalEnemies() < maxenemies)
			{
				Vector2 pos = new Vector2(Constants.SCREEN_W,(Globals.dice.nextFloat()*Constants.SCREEN_H/3f)+150);
				Flyer mook = EnemyFactory.getUfoEnemy(Globals.difficulty);
				mook.setPosition(pos);
				mook.setSpeed(mook.getSpeed()*speedmodifier);
				mook.setScore(mook.getScore()+scorebonus);
				Ludum28.gameScreen.addFlyer(mook);
				currentenemies +=1;
			}

			spawntimer = (float) (spawnMin + Globals.dice.nextDouble()*spawnDelta);
		}
		
		
	}

	@Override
	public boolean testWin() {
		return (!(currentenemies < totalenemies));
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
