package org.castelodelego.ludum28.gamemodel;

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
}
