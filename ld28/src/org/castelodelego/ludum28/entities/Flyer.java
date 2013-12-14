package org.castelodelego.ludum28.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

// FIXME: Should probably replace the hitbox with a rectangle, so that the position vector indicates the CENTER of the rectangle

/**
 * This is a generic flying entity with a position, a direction and a speed.
 * It can be extended to create enemies, players, etc.
 * 
 * @author caranha
 *
 */
public class Flyer {

	Vector2 position; // current position
	Vector2 direction; // which way the flyer wants to go (relative to the flyer)
	
	float speed = 100; // speed in cm/second
	Vector2 hitbox; // the X and Y size of the hitbox for this Flyer
	
	boolean remove; // This flyer is no longer used and can be removed
	int hitpoints = 1; // the number of hitpoints that this flyer has
	
	Shooter gun; // The function used to create shots
	
	// Team indicates if this flyer is a player or an enemy
	public static final int T_PLAYER = 0;
	public static final int T_ENEMY = 1;
	int team; 
	
	public Flyer(Vector2 pos, Vector2 htb)
	{
		position = pos;
		direction = new Vector2(0,0);
		hitbox = htb;
		remove = false;
		team = T_PLAYER;
	}
	
	/**
	 * Updates the position of the flyer
	 * @param delta
	 */
	public void update(float delta)
	{
		float mov = speed*delta; // maximum movement this tick
		direction.nor();		
		position.x += direction.x*mov;
		position.y += direction.y*mov;
		
		if (gun != null)
		{
			gun.Shoot(position, direction, team, delta);
		}
		
		if (hitpoints <= 0)
			setRemove(true);
		
	}

	/**
	 * Tests if this flier collides with a hitbox
	 * @param pos Position of the hitbox (lower left)
	 * @param box Size of the hitbox (width, height)
	 * @return
	 */
	public boolean testCollision(Vector2 pos, Vector2 box)
	{
		if (position.x+hitbox.x < pos.x || 
			position.y+hitbox.y < pos.y ||
			position.x > pos.x+box.x ||
			position.y > pos.y+box.y)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Is called when this flyer is hit by a projectile. The projectile that did the collision is passed as a parameter (for damage calculation, etc)
	 * @param value
	 */
	public void doCollision(Flyer f)
	{
		Gdx.app.log("Collision", "My hitpoints: "+hitpoints);
		hitpoints -= 1;
	}
	
	/**
	 * Render debug information for this Flyer
	 * @param linedrawer
	 */
	public void renderDebug(ShapeRenderer linedrawer)
	{
		linedrawer.setColor(Color.RED);
		linedrawer.rect(position.x, position.y, hitbox.x, hitbox.y);
		
		linedrawer.setColor(Color.GREEN);
		linedrawer.line(position.x, position.y, position.x+direction.x, position.y+direction.y);
	}
	
	/** 
	 * Renders the sprite for this Flyer
	 */
	public void renderSprite()
	{
		
	}
	
	
	// SETTERS
	
	/**
	 * Does not copy the vector pos
	 * @param dir
	 */
	public void setPosition(Vector2 pos)
	{
		position.set(pos);
	}
	
	public void setSpeed(float s)
	{
		speed = s;
	}

	/**
	 * Does not copy the vector dir
	 * @param dir
	 */
	public void setDirection(Vector2 dir)
	{
		direction.set(dir);
	}
	
	public void setTarget(Vector2 target)
	{
		direction.set(target.x - position.x, target.y - position.y);
	}
	
	public void setTeam(int t)
	{
		team = t;
	}

	public void setGun(Shooter g)
	{
		gun = g;
	}
	
	public void setRemove(Boolean b)
	{
		remove = b;
	}
	
	// GETTERS
	
	public Vector2 getDirection()
	{
		return direction;
	}

	public Vector2 getPosition()
	{
		return position;
	}
	
	public Vector2 getHitbox()
	{
		return hitbox;
	}
	
	/**
	 * Indicates whether we don't need this flyer anymore (because it was destroyed/etc)
	 * @return
	 */
	public boolean getRemove()
	{
		return remove;
	}

	public int getTeam()
	{
		return team;
	}
}
