package org.castelodelego.ludum28;

import com.badlogic.gdx.audio.Music;

public class SoundServer {

	Music current = null;
	Music next = null;
	
	boolean fadeout = true;
	float fadetotal = 0;
	float fadetimer = 0;
	
	/** 
	 * Update the volume status of the song
	 * @param delta
	 */
	public void update(float delta)
	{
		// Fade-in, Fade-out
		if (current != null && fadetotal != 0) // I am fading something
		{
			fadetimer = Math.max(0, fadetimer - delta);
			
			if (fadetimer <= 0)
			{
				if (fadeout)
					playnow(next);
				else
					fadetotal = 0;
			}
			else // fadeout not finished yet, keep changing the volume
			{
				if (fadeout)
					current.setVolume(fadetimer/fadetotal);
				else
					current.setVolume(1-(fadetimer/fadetotal));
			}

		}
		
	}
	
	/**
	 * Stop current song and start playing a new one immediately
	 * @param song
	 */
	public void playnow(Music song)
	{
		current = song;
		fadeout = false;
		fadetotal = 1;
		fadetimer = 1;
		next = null;
		if (current != null)
		{
			current.play();
			current.setLooping(true);
			current.setVolume(0);
		}
	}
	
	/**
	 * Stop all songs
	 */
	public void stop()
	{
		fadetotal = 1;
		fadetimer = 1;
		fadeout = true;
		next = null;
	}
	
	/**
	 * Fades out current song, and start a new one when that is over
	 * @param song
	 */
	public void playnext(Music song)
	{
		if (current == null)
			playnow(song);
		else
		{
			next = song;
			fadeout = true;
			fadetotal = 1;
			fadetimer = 1;
		}
	}
	
}
