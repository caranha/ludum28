package org.castelodelego.ludum28.gamemodel;

import org.castelodelego.ludum28.entities.Flyer;

import com.badlogic.gdx.math.Vector2;

public class TimedEvent implements Comparable<TimedEvent> {

	public float t;
	public Flyer[] e;
	public Vector2[] p;
	
	public TimedEvent(float time, Flyer[] enemy, Vector2[] position)
	{
		t = time;
		e = enemy;
		p = position;
	}

	@Override
	public int compareTo(TimedEvent o) {
		return (int) (t - o.t);
	}
	
}
