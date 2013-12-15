package org.castelodelego.ludum28.entities.shooters;

import com.badlogic.gdx.math.Vector2;

/**
 * This is the interface for methods that implement the creation of shoot-type flyers
 * @author caranha
 *
 */
public interface Shooter {

	public void Shoot(Vector2 pos, Vector2 dir, int team, float delta);	
	public void setCoolDown(float time);
	
}
