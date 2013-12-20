package org.castelodelego.ludum28.entities;


import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.Globals;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;


public class Prop extends Flyer {

	float maxlife;
	float life;
	boolean fade;
	
	public Prop(Vector2 pos, float lifetime, boolean fadeflag) {
		super(pos, new Vector2(60,60));
		
		team = 2;
		fade = fadeflag;
		maxlife = lifetime;
		life = lifetime;
		
		// TODO Auto-generated constructor stub
	}

	public void setFade(boolean fadeflag)
	{
		fade = fadeflag;
	}
	
	@Override
	boolean testRemoval() {
		if (life <= 0)
			return true;
		
		if ((position.x-hitbox.x > Constants.SCREEN_W ||
				position.y-hitbox.y > Constants.SCREEN_H ||
				position.x+hitbox.x < 0 ||
				position.y+hitbox.y < 0))
			return true;

		return false;
	}

	@Override
	void updateHook(float delta) {
		life -= delta;

	}

	@Override
	public void doCollision(Flyer f) {
	}

	public void renderSprite()
	{
		if (fade)
			Globals.batch.setColor(1, 1, 1, Math.max(0, life)/maxlife);
		if (anim!=null)
			Globals.batch.draw(anim.getKeyFrame(animcount), position.x-offsetx, position.y-offsety);
		if (fade)
			Globals.batch.setColor(Color.WHITE);
	}
	
}
