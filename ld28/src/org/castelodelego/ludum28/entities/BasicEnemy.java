package org.castelodelego.ludum28.entities;


import org.castelodelego.ludum28.Globals;

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
	
	public BasicEnemy(Vector2 pos) 
	{
		super(pos, new Vector2(28,28));
		setTeam(Flyer.T_ENEMY);
		setSpeed(100);
		offsetx = 2;
		offsety = 4;
		
		setAnim(Globals.animman.get("SimpleEnemy"));
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

	@Override
	boolean testRemoval() {
		// Enemy left the screen to the left side
		if (position.x+hitbox.x < 0)
			return true;
		if (hitpoints <= 0)
			return true;
		
		return false;
	}

	@Override
	void artificialIntelligence(float delta) {
		ydir += ydelta;
		if (ydir > maxY)
			ydelta = ydelta*-1;
		if (ydir < maxY*-1)
			ydelta = ydelta*-1;		
		setDirection(new Vector2(xdir,ydir));
	}

	@Override
	public void doCollision(Flyer f) {
		hitpoints -= 1;
	}

}
