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

	@Override
	public void renderSprite() {
		// TODO Auto-generated method stub
		
	}
}
