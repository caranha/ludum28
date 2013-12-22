package org.castelodelego.ludum28.gamemodel;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.entities.UFOEnemy;
import org.castelodelego.ludum28.entities.DirigibleEnemy;
import org.castelodelego.ludum28.entities.TankEnemy;
import org.castelodelego.ludum28.entities.shooters.Shooter;
import org.castelodelego.ludum28.entities.shooters.TankShooter;

import com.badlogic.gdx.math.Vector2;

public class EnemyFactory {

	
	public static UFOEnemy getUfoEnemy(int difficulty)
	{
		UFOEnemy mook = new UFOEnemy(new Vector2(0,0));
		mook.setScore(difficulty+1);
		mook.setHitpoints(difficulty+1);
		mook.goUp(Globals.dice.nextBoolean());
		mook.setXSpeed(2);
		return mook;
	}	
	
	public static TankEnemy getTankEnemy(int difficulty)
	{		
		TankEnemy mook = new TankEnemy(new Vector2(0,0));
		mook.setScore(5*(difficulty+1));
		mook.setHitpoints(5*(difficulty+1));
		mook.setSpeed(60);

		Shooter gun = new TankShooter();
		gun.setCoolDown(1.5f - 0.2f*difficulty);
		mook.setGun(gun);
		return mook;
	}
	
	public static DirigibleEnemy getDirigibleEnemy(int difficulty)
	{
		DirigibleEnemy mook = new DirigibleEnemy(new Vector2(0,0));
		mook.setScore(2*(difficulty+1));
		mook.setHitpoints(3+difficulty*3);
		mook.setSpeed(120+difficulty*20);
		mook.setYSpeed(0.1f+0.05f*difficulty);
		return mook;
	}
	
}
