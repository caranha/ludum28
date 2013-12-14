package org.castelodelego.ludum28.gamemodel;

import org.castelodelego.ludum28.parallax.ParallaxBackground;

public interface StageTimeline {

	/**
	 * Resets the timeline
	 */
	public void reset(int difficulty);
	
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
	
}
