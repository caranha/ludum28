package org.castelodelego.ludum28.gamemodel;

import org.castelodelego.ludum28.entities.Flyer;

import com.badlogic.gdx.math.Vector2;

public class TimedEvent implements Comparable<TimedEvent> {

	float t;
	Flyer e;
	Vector2 p;
	
	public TimedEvent(float time, Flyer enemy, Vector2 position)
	{
		t = time;
		e = enemy;
		p = position;
	}

	public Flyer getFlyer()
	{
		return e;
	}
	
	public Vector2 getPosition()
	{
		return p;
	}
	
	public float getTime()
	{
		return t;
	}
	
	
	@Override
	public int compareTo(TimedEvent o) {
		if (this.equals(o))
			return 0;
		
		if (t > o.t)
			return 1;
		else
			return -1;
	}
	
}
