package org.castelodelego.ludum28.entities;


import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

/**
 * The UFO enemy moves up and down following a sine wave
 * @author caranha
 *
 */
public class UFOEnemy extends Flyer 
{
	// Default Variables
	static final Sound death_default = Globals.manager.get("sfx/explosion1.ogg", Sound.class);
	static final Animation anim_default = Globals.animman.get("SimpleEnemy");
	static final Animation deathanim_default = Globals.animman.get("sfx/explosion32");
	
	/* Behavior variables */
	
	private float ysign = 1;
	private float ytimer = 0;
	private float xdir = 1;
	
	float shaketimer;
	Sound death = death_default;
	
	public UFOEnemy(Vector2 pos) 
	{
		super(pos, new Vector2(28,28));
		setTeam(Flyer.T_ENEMY);
		setSpeed(160);
		offsetx = 2;
		offsety = 4;
		
		setAnim(anim_default);
		direction.x = xdir*-1;
		direction.y = 0;
	}
	
	public void setXSpeed(float x)
	{
		xdir = x;
	}
	
	public void goUp(boolean t)
	{
		if (t)
			ysign = 1;
		else
			ysign = -1;
	}
	
	@Override
	public void renderSprite()
	{
		float shakeoffset = 0;

		if (shaketimer > 0)
			shakeoffset = Globals.dice.nextInt(3)-2;
		if (anim!=null)
			Globals.batch.draw(anim.getKeyFrame(animcount), position.x-offsetx+shakeoffset, position.y-offsety+shakeoffset);
	}

	@Override
	boolean testRemoval() {
		// Enemy left the screen to the left side
		if (position.x+hitbox.x < 0)
			return true;
		if (hitpoints <= 0)
		{
			death.play(0.7f);
			Prop explosion = new Prop(position, 1.5f, true);
			explosion.setAnim(deathanim_default);
			explosion.setDirection(direction);
			explosion.setSpeed(speed/2);
			
			Ludum28.gameScreen.addFlyer(explosion);
			return true;
		}
		
		return false;
	}

	@Override
	/**
	 * The UFO Enemy follows a sine wave.
	 * It can go slower or faster depending on the amplitude of X;
	 */
	void updateHook(float delta) {
		ytimer += delta;
		shaketimer -= delta;

		direction.x = xdir*-1;
		direction.y = (float) (Math.sin(ytimer*2)*ysign);		
	}

	@Override
	public void doCollision(Flyer f) {
		shaketimer = 0.5f;
		if (f instanceof Player && f.getHitpoints() > 0)
			hitpoints -= 10000;
		else	
			hitpoints -= 1;
	}

}
