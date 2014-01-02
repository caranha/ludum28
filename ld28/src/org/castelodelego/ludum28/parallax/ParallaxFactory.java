package org.castelodelego.ludum28.parallax;

import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.Globals;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class ParallaxFactory {

	
	   /**
	    * Returns the default "savannah" parallax background
	    * @return
	    */
	   public static ParallaxBackground getDefaultBackground()
	   {
		   TextureAtlas atlas = Globals.manager.get("sprites/pack.atlas", TextureAtlas.class);
			ParallaxBackground test = new ParallaxBackground(new ParallaxLayer[]{
					new ParallaxLayer(atlas.findRegion("parallax/Ground3"),new Vector2(0.1f,0),new Vector2(0, Constants.SCREEN_H)),
					new ParallaxLayer(atlas.findRegion("parallax/Ground2"),new Vector2(0.5f,0),new Vector2(0, Constants.SCREEN_H)),
					new ParallaxLayer(atlas.findRegion("parallax/Ground1"),new Vector2(1.0f,0),new Vector2(0, Constants.SCREEN_H))
			}, 800, 480,new Vector2(200,0));
			return test;
	   }
}
