package org.castelodelego.ludum28.entities;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.entities.shooters.BigShooter;
import org.castelodelego.ludum28.entities.shooters.FiveShooter;
import org.castelodelego.ludum28.entities.shooters.PeaShooter;
import org.castelodelego.ludum28.entities.shooters.Shooter;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Flyer {

	public static final int MODE_WIDE = 0;
	public static final int MODE_BEAM = 1;
	public static final int MODE_SHIELD = 2;
	public static final int MODE_SPEED = 3;

	// Screen Limits
	public static Vector2 limit_bl = new Vector2(10,10);
	public static Vector2 limit_tr = new Vector2(Constants.SCREEN_W - 64, Constants.SCREEN_H - 64);
	
	// Animations
	static Animation modeanim[] = null;
	static Animation roaranim[] = null;
	static Animation shield[] = null;
	
	// Roar
	static final float roar_interval = 1.5f;
	float roar_timer = 1.5f;
	float timer = 0;
	float invtimer = 0; // invulnerable timer after the shield
	float shieldrecharge = 0; // time for the shield to recharge
	
	boolean alive = true;
	int mode;
	
	// Movement variables
	public static Vector2 target;
	public boolean[] keyarray;
	
	public Player(int m) {
		super(new Vector2(), new Vector2(40,37));
		mode = m;
		
		setSpeed(400);
		Shooter pew = new PeaShooter();
		pew.setCoolDown(0.2f);		
		setGun(pew);
		
		offsetx = 11;
		offsety = 8;
		
		shootoffsetx = 35;
		shootoffsety = 43;
		
		switch (mode)
		{
		case MODE_WIDE: // Wide shooting mode shoots 5 shots at a time;
			setGun(new FiveShooter());
			break;
		case MODE_SPEED:
			setSpeed(600);
			break;
		case MODE_SHIELD:
			setHitpoints(3);
			break;
		case MODE_BEAM:
			setGun(new BigShooter());
			shootoffsetx = 20;
			shootoffsety = 34;
			break;
		}
		
		if (modeanim == null)
		{
			modeanim = new Animation[4];
			roaranim = new Animation[4];
			shield = new Animation[2];
			for (int i = 0; i < 4; i++)
			{
				modeanim[i] = Globals.animman.get("DinoMode"+(i+1));
				roaranim[i] = Globals.animman.get("DinoRoar"+(i+1));
			}	
			shield[0] = Globals.animman.get("Shield1");
			shield[1] = Globals.animman.get("Shield2");
		}

	}
	
	public void update(float delta)
	{
		if (isAlive())
		{
			super.update(delta);		
			
			// Being awesome
			roar_timer += delta;
			timer += delta;
			
			if (roar_timer < roar_interval)
				setAnim(roaranim[mode]);
			else
				setAnim(modeanim[mode]); // no longer awesome
			
			if (mode == MODE_SHIELD)
			{
				shieldrecharge -= delta;
				invtimer -= delta;
				if (hitpoints < 3 && shieldrecharge <= 0)
				{
					hitpoints++;
					shieldrecharge = 7;
				}
			}
			
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
	
	public int getMode()
	{
		return mode;
	}
	

	
	
	//** Override functions from Flyer **//
	
	@Override
	boolean testRemoval() {
		return false;
	}

	@Override
	void updateHook(float delta) {
	}

	@Override
	void moveLogic(float delta)
	{
		// If I have a target, go to target
		if (target != null)
			targetApproach(delta);
		// Else if I have an input vector, go to input vector
		else if (keyarray != null)
			keyArrayMove(delta);
		
		bindPosition();
	}
	
	void targetApproach(float delta)
	{
		Vector2 move = target.cpy().sub(position.x+hitbox.x/2, position.y+hitbox.y/2);
		if (move.len2() > 100)
			move.clamp(speed, speed);
				
		position.x += move.x*delta;
		position.y += move.y*delta;
	}
	
	void keyArrayMove(float delta)
	{
		Vector2 move = new Vector2(0,0);
		float mov = speed*delta;
		
		move.y = (keyarray[0]?1:0) + (keyarray[1]?-1:0);
		move.x = (keyarray[2]?1:0) + (keyarray[3]?-1:0);
		
		move.nor();
		position.x += move.x*mov;
		position.y += move.y*mov;
	}
	
	void bindPosition()
	{
		if (position.x < limit_bl.x)
			position.x = limit_bl.x;
		if (position.x > limit_tr.x)
			position.x = limit_tr.x;
		if (position.y < limit_bl.y)
			position.y = limit_bl.y;
		if (position.y > limit_tr.y)
			position.y = limit_tr.y;
	}
	
	public void setKeyArray(boolean[] keys)
	{
		keyarray = keys;
	}
	
	@Override
	public void setTarget(Vector2 t)
	{
		target = t;
	}
	
	public void setDirection(Vector2 t)
	{
		setTarget(t.add(position.x+hitbox.x/2, position.y+hitbox.y/2));
	}
	
	
	
	
	@Override
	public void doCollision(Flyer f) {
		if (invtimer <= 0)
		{
			hitpoints -= 1;
			invtimer = 1;
			shieldrecharge = 7;
		}
	}

	
	@Override
	public void renderSprite()
	{
		if (anim!=null)
		{
			if (invtimer > 0)
				Globals.batch.setColor(1, 1, 1, Globals.dice.nextInt(5)/4f);
			Globals.batch.draw(anim.getKeyFrame(animcount), position.x-offsetx, position.y-offsety);
			if (hitpoints > 1)
				Globals.batch.draw(shield[hitpoints-2].getKeyFrame(animcount), position.x-offsetx, position.y-offsety);
			
			if (invtimer > 0)
				Globals.batch.setColor(1, 1, 1, 1);
		}
	}
	
	public void renderDebug(ShapeRenderer linedrawer)
	{
		super.renderDebug(linedrawer);
		if (target != null)
		{
			linedrawer.setColor(Color.RED);
			linedrawer.circle(target.x, target.y, 5);
		}
	}
	
}
