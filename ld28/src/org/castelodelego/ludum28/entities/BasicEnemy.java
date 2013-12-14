package org.castelodelego.ludum28.entities;


import com.badlogic.gdx.math.Vector2;

/**
 * The basic enemy just moves up and down, randomly.
 * @author caranha
 *
 */
public class BasicEnemy extends Flyer 
{
	float xdir = -80;	
	
	float maxY = 100;
	float ydir = 0;
	float ydelta = 1;
	
	public BasicEnemy(Vector2 pos, Vector2 htb) 
	{
		super(pos, htb);
		setTeam(Flyer.T_ENEMY);
		setSpeed(100);
	}
	
	public void update(float delta)
	{
		super.update(delta);
		
		ydir += ydelta;
		if (ydir > maxY)
			ydelta = ydelta*-1;
		if (ydir < maxY*-1)
			ydelta = ydelta*-1;		
		setDirection(new Vector2(xdir,ydir));

		// Enemy is removed if he crosses the left border
		if (position.x+hitbox.x < 0)
			setRemove(true);
	}
	
	public void goUp(boolean t)
	{
		if (t)
		{
			ydelta = 1;
		}
		else
		{
			ydelta = -1;
		}
	}
}
