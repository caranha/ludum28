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
public class TankEnemy extends Flyer 
{
	// Default Variables
	static final Sound death_default = Globals.manager.get("sfx/explosion1.ogg", Sound.class);
	static final Animation anim_default = Globals.animman.get("Tank");
	static final Animation deathanim_default = Globals.animman.get("TankTrash");
	
	/* Behavior variables */
	private float switchtimer = 3;
	private float timer = 0;
	private boolean stop = false;
	
	float shaketimer;
	Sound death = death_default;
	
	public TankEnemy(Vector2 pos) 
	{
		super(pos, new Vector2(80,40));
		setTeam(Flyer.T_ENEMY);
		setSpeed(100);
		offsetx = 2;
		offsety = 4;
		
		shootoffsetx = 25;
		shootoffsety = 15;
		
		setAnim(anim_default);
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
			Prop explosion = new Prop(position, 15, true);
			explosion.setAnim(deathanim_default);
			explosion.setDirection(new Vector2(-1,0));
			explosion.setSpeed(200);
			
			((GameScreen) Ludum28.gameScreen).addFlyer(explosion);
			return true;
		}
		
		return false;
	}

	@Override
	void artificialIntelligence(float delta) {
		shaketimer -= delta;
		timer -= delta;
		
		if (timer <= 0)
		{
			if (stop)
			{
				direction.set(-1,0);
				timer = switchtimer*(1+Globals.dice.nextFloat());
				stop = false;
			}
			else
			{
				direction.set(0,0);
				timer = switchtimer*(1+Globals.dice.nextFloat());
				stop = true;
			}
		}
		
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
