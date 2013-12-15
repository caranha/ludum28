package org.castelodelego.ludum28.gamemodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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

public class AsteroidTimeline implements StageTimeline {
	
	ArrayList<TimedEvent> events;
	Iterator<TimedEvent> pointer;
	TimedEvent current = null;
	float timer;

	@Override
	public void reset(int difficulty) {
		timer = 0; 
		events = prepareEvents(difficulty);
		pointer = events.iterator();
		if (pointer.hasNext())		
			current = pointer.next();
	}

	@Override
	public void update(float delta) {
		
		timer += delta;
		
		if (current != null && timer >= current.t) // time to pull another event
		{
			for (int i = 0; i < current.e.length; i++)
			{
				current.e[i].setPosition(current.p[i]);
				Gdx.app.log("Speed", current.e[i].getSpeed()+"");
				((GameScreen) Ludum28.gameScreen).addFlyer(current.e[i]);
			}
			
			current = null;
			if (pointer.hasNext())		
				current = pointer.next();
		}
	}

	@Override
	public boolean testWin() {
		return !pointer.hasNext();
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
		return Globals.manager.get("music/play2.ogg",Music.class);
	}

	/**
	 * This huge function will contain all the enemies in the timeline
	 * FIXME: make it read the enemies from a file or something!
	 * @param difficulty
	 * @return
	 */
	ArrayList<TimedEvent> prepareEvents(int difficulty)
	{
		ArrayList<TimedEvent> ret = new ArrayList<TimedEvent>();

		TimedEventFactory.addBirdLine(ret, 6, 300, 4, difficulty);
		TimedEventFactory.addBirdLine(ret, 4, 100, 4, difficulty);
		
		TimedEventFactory.addZepWall(ret, 2, difficulty);
		
		
		Collections.sort(ret);
		return ret;
	}

	
	
}
