package org.castelodelego.ludum28.entities;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

public class Player extends Flyer {

	public static final int MODE_WIDE = 0;
	public static final int MODE_BEAM = 1;
	public static final int MODE_SHIELD = 2;
	public static final int MODE_SPEED = 3;
	
	public static final int offsetx = 11;
	public static final int offsety = 8;
	
	public static Vector2 limit_bl = new Vector2(10,10);
	public static Vector2 limit_tr = new Vector2(Constants.SCREEN_W - 64, Constants.SCREEN_H - 64);
	
	
	static final float roar_interval = 1.5f;
	float roar_timer = 1.5f;
	float timer = 0;
	
	boolean alive = true;
	int mode;
	
	static Animation modeanim[] = null ;
	static Animation roaranim[] = null;
	
	
	public Player(int m) {
		super(new Vector2(0,0), new Vector2(46,37));
		mode = m;
		
		setSpeed(200);
		Shooter pew = new PeaShooter();
		pew.setCoolDown(0.2f);		
		setGun(pew);
		
		switch (mode)
		{
		case MODE_WIDE: // Wide shooting mode shoots 5 shots at a time;
			setGun(new FiveShooter());
			break;
		case MODE_SPEED:
			setSpeed(350);
		}
		
		if (modeanim == null)
		{
			Gdx.app.log("[Info]", "Loaded Player Animations");
			modeanim = new Animation[4];
			roaranim = new Animation[4];
			for (int i = 0; i < 4; i++)
			{
				// FIXME - Do all the dinossaur animations!
				modeanim[i] = Globals.animman.get("DinoMode"+(1));
				roaranim[i] = Globals.animman.get("DinoRoar"+(1));
			}	
		}

	}
	
	public void update(float delta)
	{
		if (isAlive())
		{
			super.update(delta);		
			if (position.x < limit_bl.x)
				position.x = limit_bl.x;
			if (position.x > limit_tr.x)
				position.x = limit_tr.x;
			if (position.y < limit_bl.y)
				position.y = limit_bl.y;
			if (position.y > limit_tr.y)
				position.y = limit_tr.y;
				
			roar_timer += delta;
			timer += delta;
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
	void artificialIntelligence(float delta) {
	}

	@Override
	public void doCollision(Flyer f) {
		hitpoints -= 1;
	}

	@Override
	public void renderSprite() {
		if (roar_timer < roar_interval)
			Globals.batch.draw(roaranim[mode].getKeyFrame(timer), position.x-offsetx, position.y-offsety);
		else	
			Globals.batch.draw(modeanim[mode].getKeyFrame(timer), position.x-offsetx, position.y-offsety);
	}

}
