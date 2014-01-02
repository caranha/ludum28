package org.castelodelego.ludum28.gamemodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.parallax.ParallaxBackground;
import org.castelodelego.ludum28.parallax.ParallaxFactory;

import com.badlogic.gdx.audio.Music;

/** 
 * 
 * This timeline generates a number of enemies from events in the timedEventFactory
 * @author caranha
 *
 */
public class RandomEventTimeline implements StageTimeline {
	
	ArrayList<TimedEvent> eventlist;
	ParallaxBackground background;
	String music = "music/play2.ogg";
	
	Iterator<TimedEvent> pointer;
	TimedEvent current = null;
	float timer;

	
	@Override
	public void reset() {
		timer = 0; 
		eventlist = new ArrayList<TimedEvent>();

		for (int i = 0; i < Globals.wave+1; i++)
		{
			eventlist.addAll(TimedEventFactory.getRandomUFO(1+(i%4)*7, 4, 15));
			eventlist.addAll(TimedEventFactory.getRandomZeppelin(2+(i%4)*7, 4, 4));
			eventlist.addAll(TimedEventFactory.getRandomTank(1+(i%4)*7, 1, 2));
		}
		

		
		Collections.sort(eventlist);
		
		pointer = eventlist.iterator();
		if (pointer.hasNext())		
			current = pointer.next();
	}

	@Override
	public void update(float delta) {
		
		timer += delta;
		
		if (current != null && timer >= current.getTime()) // time to pull another event
		{
			current.getFlyer().setPosition(current.getPosition());
			Ludum28.gameScreen.addFlyer(current.getFlyer());
			if (pointer.hasNext())		
				current = pointer.next();
			else
				current = null;
		}
	}

	@Override
	public boolean testWin() {
		return (current == null);
	}

	public ParallaxBackground getParallax()
	{
		return ParallaxFactory.getDefaultBackground();
	}

	@Override
	public Music getStartMusic() {
		return Globals.manager.get(music,Music.class);
	}

	
}
