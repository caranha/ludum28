package org.castelodelego.ludum28.gamemodel;

import java.util.ArrayList;
import java.util.List;

import org.castelodelego.ludum28.Globals;
import com.badlogic.gdx.math.Vector2;

public class TimedEventFactory {

	
	/**
	 * Returns N UFOS spread randomly across the time duration described
	 */
	static public List<TimedEvent> getRandomUFO(float timeStart, float timeDuration, int n)
	{
		ArrayList<TimedEvent> l = new ArrayList<TimedEvent>();
		for(int i = 0; i < n; i++)
		{
			l.add(new TimedEvent(timeStart + Globals.dice.nextFloat()*timeDuration, EnemyFactory.getUfoEnemy(Globals.difficulty),new Vector2(810,100+Globals.dice.nextFloat()*300)));
		}
		return l;
	}

	/**
	 * Returns N UFOS spread randomly across the time duration described
	 */
	static public List<TimedEvent> getRandomZeppelin(float timeStart, float timeDuration, int n)
	{
		ArrayList<TimedEvent> l = new ArrayList<TimedEvent>();
		for(int i = 0; i < n; i++)
		{
			l.add(new TimedEvent(timeStart + Globals.dice.nextFloat()*timeDuration, EnemyFactory.getDirigibleEnemy(Globals.difficulty),new Vector2(810,50+Globals.dice.nextFloat()*400)));
		}
		return l;
	}
	
	/**
	 * Returns N UFOS spread randomly across the time duration described
	 */
	static public List<TimedEvent> getRandomTank(float timeStart, float timeDuration, int n)
	{
		ArrayList<TimedEvent> l = new ArrayList<TimedEvent>();
		for(int i = 0; i < n; i++)
		{
			l.add(new TimedEvent(timeStart + Globals.dice.nextFloat()*timeDuration, EnemyFactory.getTankEnemy(Globals.difficulty),new Vector2(810,Globals.dice.nextFloat()*100+15)));
		}
		return l;
	}
}
