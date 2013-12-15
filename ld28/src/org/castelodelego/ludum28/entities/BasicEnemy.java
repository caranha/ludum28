package org.castelodelego.ludum28.entities;


import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.screens.GameScreen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

/**
 * The basic enemy just moves up and down, randomly.
 * @author caranha
 *
 */
public class BasicEnemy extends Flyer 
{
	// Default Variables
	static final Sound death_default = Globals.manager.get("sfx/explosion1.ogg", Sound.class);
	static final Animation anim_default = Globals.animman.get("SimpleEnemy");
	static final Animation deathanim_default = Globals.animman.get("sfx/explosion32");
	
	/* Behavior variables */
	private float xdir = -80;		
	private float maxY = 100;
	private float ydir = 0;
	private float ydelta = 1;
	
	float shaketimer;
	Sound death = death_default;
	
	public BasicEnemy(Vector2 pos) 
	{
		super(pos, new Vector2(28,28));
		setTeam(Flyer.T_ENEMY);
		setSpeed(100);
		offsetx = 2;
		offsety = 4;
		
		setAnim(anim_default);
	}
	
	public void setAmplitude(float max)
	{
		maxY = max;
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
			
			((GameScreen) Ludum28.gameScreen).addFlyer(explosion);
			return true;
		}
		
		return false;
	}

	@Override
	void artificialIntelligence(float delta) {
		shaketimer -= delta;
		ydir += ydelta;
		if (ydir > maxY)
			ydelta = ydelta*-1;
		if (ydir < maxY*-1)
			ydelta = ydelta*-1;		
		setDirection(new Vector2(xdir,ydir));
	}

	@Override
	public void doCollision(Flyer f) {
		shaketimer = 0.5f;
		if (f instanceof Player)
			hitpoints -= 10000;
		else	
			hitpoints -= 1;
	}

}
