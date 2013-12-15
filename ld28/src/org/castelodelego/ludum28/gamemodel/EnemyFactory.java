package org.castelodelego.ludum28.gamemodel;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.entities.BasicEnemy;
import org.castelodelego.ludum28.entities.DirigibleEnemy;
import org.castelodelego.ludum28.entities.TankEnemy;
import org.castelodelego.ludum28.entities.shooters.Shooter;
import org.castelodelego.ludum28.entities.shooters.TankShooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class EnemyFactory {

	
	public static BasicEnemy getBasicEnemy(int difficulty)
	{
		BasicEnemy mook = new BasicEnemy(new Vector2(0,0));
		float mookspeed = 70+(difficulty*3);
		if (Globals.dice.nextFloat() < 0.4f)
			mookspeed += difficulty*5;
		if (Globals.dice.nextFloat() < 0.1f)
			mookspeed += (difficulty-3)*10;
		mook.goUp(Globals.dice.nextBoolean());			
		mook.setScore(difficulty+1);
		mook.setHitpoints((difficulty/3)+1);
		mook.setSpeed(mookspeed);
		mook.setAmplitude(Math.max(50, 130-(Globals.dice.nextFloat()*10*difficulty)));
		return mook;
	}
	
	public static TankEnemy getTankEnemy(int difficulty)
	{
		TankEnemy mook = new TankEnemy(new Vector2(0,0));
		float mookspeed = 60;
		mook.setScore(3*(difficulty+1));
		mook.setHitpoints(((int) (Math.ceil(Math.log10(difficulty+1)))+1)*5);
		Gdx.app.log("Tank Hitpoints", ""+mook.getHitpoints());		
		mook.setSpeed(mookspeed);
		Shooter gun = new TankShooter();
		gun.setCoolDown(Math.max(1.2f - 0.02f*difficulty,0.3f));
		mook.setGun(gun);
		return mook;
	}
	
	public static DirigibleEnemy getDirigibleEnemy(int difficulty)
	{
		DirigibleEnemy mook = new DirigibleEnemy(new Vector2(0,0));
		mook.setSpeed(140+Globals.dice.nextFloat()*difficulty*4);
		mook.setScore(2*(difficulty+1));
		mook.setHitpoints((int) Math.min(2+difficulty*2,10));
		Gdx.app.log("Dirigible Hitpoints", ""+mook.getHitpoints());	
		mook.setYSpeed(0.1f+0.01f*difficulty);
		return mook;
	}
	
}
