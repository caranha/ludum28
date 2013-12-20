package org.castelodelego.ludum28.entities.shooters;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.entities.Flyer;
import org.castelodelego.ludum28.entities.Laser;
import org.castelodelego.ludum28.screens.GameScreen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

/**
 * Very simple shooter, that shoots a small laser forwards
 * @author caranha
 */
public class BigShooter implements Shooter {

	float CDTIMER = 0.8f;
	float cooldown = 0;
	
	static Sound pewpew = Globals.manager.get("sfx/pew.ogg", Sound.class);
	
	@Override
	public void Shoot(Vector2 pos, Vector2 dir, int team, float delta) {
		cooldown += delta;
		if (cooldown > CDTIMER)
		{
			Vector2 d;
			if (team == Flyer.T_PLAYER)
				d = new Vector2(1,0);
			else
				d = new Vector2(-1,0);
			
			Flyer pew = new Laser(pos.cpy(),new Vector2(80,20));
			pew.setSpeed(250);
			pew.setHitpoints(20);
			pew.setDirection(d);
			pew.setTeam(team);
			
			pew.setAnim(Globals.animman.get("BigLaser"));
			pew.setAnimOffset(0, 0);
			
			((GameScreen) Ludum28.gameScreen).addFlyer(pew);
			pewpew.play(0.5f, 0.5f, 0);
			
			cooldown -= CDTIMER;
		}		
	}

	public void setCoolDown(float time)
	{
		CDTIMER = time;
	}
	
}
