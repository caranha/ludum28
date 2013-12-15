package org.castelodelego.ludum28.entities;


import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.screens.GameScreen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

/**
 * 
 * The dirigible enemy will try to move towards the player. Slowly.
 * 
 * @author caranha
 *
 */
public class DirigibleEnemy extends Flyer 
{
	// Default Variables
	static final Sound death_default = Globals.manager.get("sfx/falling.ogg", Sound.class);
	static final Animation anim_default = Globals.animman.get("Dirigible");
	static final Animation deathanim_default = Globals.animman.get("DirigibleDeath");
	
	/* Behavior variables */
	private float yspeed = 0.2f;
	
	float shaketimer;
	Sound death = death_default;
	
	public DirigibleEnemy(Vector2 pos) 
	{
		super(pos, new Vector2(80,30));
		setTeam(Flyer.T_ENEMY);
		setSpeed(100);
		offsetx = 2;
		offsety = 8;
		
		setAnim(anim_default);
		setDirection(new Vector2(-1,0));
	}	
	
	@Override
	public void renderSprite()
	{
		float shakeoffset = 0;

		if (shaketimer > 0)
			shakeoffset = Globals.dice.nextInt(3)-2;
		if (anim!=null)
			Globals.batch.draw(anim.getKeyFrame(animcount), position.x-offsetx+shakeoffset, position.y-offsety+shakeoffset);
	}

	@Override
	boolean testRemoval() {
		// Enemy left the screen to the left side
		if (position.x+hitbox.x < 0)
			return true;
		if (hitpoints <= 0)
		{
			death.play(0.7f);
			Prop explosion = new Prop(position, 15, false);
			explosion.setAnim(deathanim_default);
			explosion.setDirection(new Vector2(-1,-1.2f));
			explosion.setSpeed(200);
			
			((GameScreen) Ludum28.gameScreen).addFlyer(explosion);
			return true;
		}
		
		return false;
	}

	@Override
	/**
	 * The Dirigible tries to, slowly, move towards the player.
	 */
	void artificialIntelligence(float delta) {
		shaketimer -= delta;
		
		float py = ((GameScreen) Ludum28.gameScreen).getPlayer().getPosition().y;
		
		if (py > position.y)
			direction.y = yspeed;
		if (py < position.y)
			direction.y = -yspeed;
		
	}

	@Override
	public void doCollision(Flyer f) {
		shaketimer = 0.5f;
		if (f instanceof Player)
			hitpoints -= 10000;
		else	
			hitpoints -= 1;
	}
	
	public void setYSpeed(float y)
	{
		yspeed = y;
	}

}
