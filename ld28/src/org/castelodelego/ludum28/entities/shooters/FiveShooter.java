package org.castelodelego.ludum28.entities.shooters;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.entities.Flyer;
import org.castelodelego.ludum28.entities.Laser;
import org.castelodelego.ludum28.screens.GameScreen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

public class FiveShooter implements Shooter {

	static float y[] = { 0.5f, 0.25f, 0f, -0.25f, -0.5f};
	float CDTIMER = 0.5f;
	float cooldown = 0;
	
	static Sound pewpew = Globals.manager.get("sfx/pew.ogg", Sound.class);
	
	@Override
	public void Shoot(Vector2 pos, Vector2 dir, int team, float delta) {
		cooldown += delta;
		if (cooldown > CDTIMER)
		{
			float x;
			if (team == Flyer.T_PLAYER)
				x = 1;
			else
				x = -1;
			
			
			for (int i = 0; i < 5; i++)
			{
				Laser pew = new Laser(pos.cpy(),new Vector2(10,2));
				pew.setSpeed(500);
				pew.setDirection(new Vector2(x, y[i]));
				pew.setTeam(team);

				pew.setAnim(Globals.animman.get("PlayerLaser"));
				pew.setAnimOffset(1, 1);
				
				((GameScreen) Ludum28.gameScreen).addFlyer(pew);
			}
			
			pewpew.play(0.25f);
			
			cooldown -= CDTIMER;
		}
		
	}

	@Override
	public void setCoolDown(float time) {
		CDTIMER = time;
	}

}
