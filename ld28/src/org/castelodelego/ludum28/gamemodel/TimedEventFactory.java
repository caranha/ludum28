package org.castelodelego.ludum28.gamemodel;

import java.util.ArrayList;

import org.castelodelego.ludum28.entities.Flyer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class TimedEventFactory {
	
	
	static public void addBirdLine(ArrayList<TimedEvent> list, float time, float altitude, int n, int difficulty)
	{
		Flyer[] fl;
		Vector2[] vl;
		for(int i = 0; i < n; i++)
		{
			fl = new Flyer[1];
			fl[0] = EnemyFactory.getBasicEnemy(difficulty);

			vl = new Vector2[1];
			vl[0] = new Vector2(810,altitude);
			
			list.add(new TimedEvent(time+i*0.5f, fl, vl));
		}
	}
	
	static public void addZepWall(ArrayList<TimedEvent> list, float time, int difficulty)
	{
		Flyer[] fl = new Flyer[3];
		Vector2[] vl = new Vector2[3];
		
		for (int i = 0; i < fl.length; i++)
		{
			fl[i] = EnemyFactory.getDirigibleEnemy(difficulty);

			vl[i] = new Vector2(810,100+40*i);
			list.add(new TimedEvent(time, fl, vl));
		}
	}

}
