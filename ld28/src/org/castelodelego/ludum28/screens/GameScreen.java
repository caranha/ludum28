package org.castelodelego.ludum28.screens;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.entities.Flyer;
import org.castelodelego.ludum28.entities.PeaShooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {

	ShapeRenderer linedrawer;
	
	Array<Flyer> friends;
	Array<Flyer> enemies;
	Flyer player;
	
	public GameScreen()
	{
		linedrawer = new ShapeRenderer();
		linedrawer.setProjectionMatrix(Globals.cam.combined);

		friends = new Array<Flyer>();
		enemies = new Array<Flyer>();		
	}

	/** 
	 * Debug function that sets a test player
	 */
	void setTestPlayer()
	{
		player = new Flyer(new Vector2(20,20), 200, new Vector2(10,10)); // FIXME: debug
		player.setGun(new PeaShooter());
		
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/** Debug input **/
		if (Gdx.input.isTouched())
		{
			Vector3 rawtouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
			Globals.cam.unproject(rawtouch);	
			setPlayerTarget(new Vector2(rawtouch.x,rawtouch.y));
		}
		
		if (player == null)
			setTestPlayer();
		
		/*** Updating the Game ***/
		tickGame(delta);
		
		/*** Rendering the Game ***/
		debugRender();
	}

	void tickGame(float delta)
	{
		player.update(delta);
		for (Flyer aux: friends)
		{
			aux.update(delta);
		}
		for (Flyer aux: enemies)
		{
			aux.update(delta);
		}
	}
	
	void debugRender()
	{
		linedrawer.begin(ShapeType.Line);
		player.renderDebug(linedrawer);
		for (Flyer aux:friends)
		{
			aux.renderDebug(linedrawer);
		}
		for (Flyer aux:enemies)
		{
			aux.renderDebug(linedrawer);
		}
		linedrawer.end();		
	}
	
	
	public void setPlayerDirection(Vector2 direction)
	{
		player.setDirection(direction);
	}
	
	public void setPlayerTarget(Vector2 target)
	{
		player.setTarget(target);
	}
	
	/**
	 * This is used to add a new Flyer to the Scene;
	 * @param f
	 */
	public void addFlyer(Flyer f)
	{
		if (f.getTeam()==Flyer.T_PLAYER)
			friends.add(f);
		if (f.getTeam()==Flyer.T_ENEMY)
			enemies.add(f);
	}
	
	public Flyer getPlayer()
	{
		return player;
	}
	
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	
}
