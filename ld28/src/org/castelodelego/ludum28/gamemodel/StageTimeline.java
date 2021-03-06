package org.castelodelego.ludum28.gamemodel;

import org.castelodelego.ludum28.parallax.ParallaxBackground;

import com.badlogic.gdx.audio.Music;

public interface StageTimeline {

	/**
	 * Resets the timeline
	 */
	public void reset();
	
	/**
	 * Updates the timeline();
	 * @param delta
	 */
	public void update(float delta);	
	
	/**
	 * Returns True if the player has won the level (a certain amount of time has passed, or the boss has died)
	 * @return
	 */
	public boolean testWin();
	
	public ParallaxBackground getParallax();
	public Music getStartMusic();
	
}
