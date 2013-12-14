package org.castelodelego.ludum28.entities;

import com.badlogic.gdx.math.Vector2;
import org.castelodelego.ludum28.Constants;

/**
 * This is a normal flyer that dies when it goes off the screen
 * @author caranha
 *
 */
public class Laser extends Flyer {

	int damage = 1;
	
	public Laser(Vector2 pos, Vector2 htb) {
		super(pos, htb);
		speed = 300;
	}
	
	public void update(float delta)
	{
		super.update(delta);
		
		// Tests if the shot is outside the screen, and set it for removal if it is
		if	((position.x-hitbox.x > Constants.SCREEN_W ||
				position.y-hitbox.y > Constants.SCREEN_H ||
				position.x+hitbox.x < 0 ||
				position.y+hitbox.y < 0))
			setRemove(true);
	}

	public void setDamage(int d)
	{
		damage = d;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	/**
	 * Lasers do not collide with other Lasers
	 */
	@Override
	public void doCollision(Flyer f)
	{
		if (!(f instanceof Laser))
			remove = true;
	}
}
