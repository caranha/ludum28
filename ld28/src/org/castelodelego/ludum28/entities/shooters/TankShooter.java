package org.castelodelego.ludum28.entities.shooters;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.entities.Flyer;
import org.castelodelego.ludum28.entities.Laser;
import org.castelodelego.ludum28.screens.GameScreen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

public class TankShooter implements Shooter {

	static float y[] = { 0.0f, 0.8f, 1.2f};
	float CDTIMER = 0.8f;
	float cooldown = 0;
	
	static Sound pewpew = Globals.manager.get("sfx/cannon.ogg", Sound.class);
	
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
			
			
			for (int i = 0; i < 3; i++)
			{
				Laser pew = new Laser(pos.cpy(),new Vector2(5,5));
				pew.setSpeed(150);
				pew.setAnim(Globals.animman.get("Bullet"));				
				pew.setDirection(new Vector2(x, y[i]));
				pew.setTeam(team);
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
