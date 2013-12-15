package org.castelodelego.ludum28.gamemodel;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.entities.BasicEnemy;

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
	
}
