package org.castelodelego.ludum28.entities;

import com.badlogic.gdx.math.Vector2;
import org.castelodelego.ludum28.Constants;

/**
 * This is a normal flyer that dies when it goes off the screen
 * @author caranha
 *
 */
public class Laser extends Flyer {
	
	public Laser(Vector2 pos, Vector2 htb) {
		super(pos, htb);
		speed = 300;
	}
	

	/**
	 * Lasers do not collide with other Lasers
	 */
	@Override
	public void doCollision(Flyer f)
	{
		// Does not interact with other lasers
		if (!(f instanceof Laser))
			hitpoints -= 1;
	}

	@Override
	boolean testRemoval() {
		if (hitpoints <= 0)
			return true;
		
		if ((position.x-hitbox.x > Constants.SCREEN_W ||
				position.y-hitbox.y > Constants.SCREEN_H ||
				position.x+hitbox.x < 0 ||
				position.y+hitbox.y < 0))
			return true;
		
		return false;
	}

	@Override
	void updateHook(float delta) {
		// Simple lasers just go straight ahead
	}

}
