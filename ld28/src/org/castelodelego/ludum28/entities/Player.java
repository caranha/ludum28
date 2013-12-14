package org.castelodelego.ludum28.entities;

import org.castelodelego.ludum28.Globals;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

public class Player extends Flyer {

	public static final int MODE_WIDE = 0;
	public static final int MODE_BEAM = 1;
	public static final int MODE_SHIELD = 2;
	public static final int MODE_SPEED = 3;
	
	static final float roar_interval = 1.5f;
	float roar_timer = 0;
	
	boolean alive = true;
	
	public Player(Vector2 pos, int mode) {
		super(pos, new Vector2(20,20));
		
		setSpeed(200);
		Shooter pew = new PeaShooter();
		pew.setCoolDown(0.2f);		
		setGun(pew);
	}
	
	public void update(float delta)
	{
		if (isAlive())
		{
			super.update(delta);		
			roar_timer += delta;
		}
		
		if (hitpoints <= 0)
		{
			alive = false;
			roar_timer = 0;
		}
	}
	
	public void doRoar()
	{
		if (roar_timer > roar_interval)
		{
			(Globals.manager.get("sfx/roar.ogg", Sound.class)).play();
			roar_timer = 0;
		}
	}
	
	/**
	 * Indicates whether the player is alive or not
	 * ** TODO: I might want change this to "Get State" later, to deal with death animations
	 * @return
	 */
	public boolean isAlive()
	{
		return alive;
	}
	

	@Override
	boolean testRemoval() {
		return false;
	}

	@Override
	void artificialIntelligence(float delta) {
	}

	@Override
	public void doCollision(Flyer f) {
		hitpoints -= 1;
	}

	@Override
	public void renderSprite() {
	}

}