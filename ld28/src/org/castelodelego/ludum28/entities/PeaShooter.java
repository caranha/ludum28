package org.castelodelego.ludum28.entities;

import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.screens.GameScreen;

import com.badlogic.gdx.math.Vector2;

/**
 * Very simple shooter, that shoots a small laser forwards
 * @author caranha
 */
public class PeaShooter implements Shooter {

	static float CDTIMER = 0.5f;
	float cooldown = 0;
	
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
			
			Flyer pew = new Flyer(pos.cpy(),500,new Vector2(10,2));
			pew.setDirection(d);
			pew.setTeam(team);
			
			((GameScreen) Ludum28.gameScreen).addFlyer(pew);
			
			
			cooldown -= CDTIMER;
		}		
	}

}
