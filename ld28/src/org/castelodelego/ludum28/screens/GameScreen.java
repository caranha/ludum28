package org.castelodelego.ludum28.screens;

import java.util.Iterator;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.entities.BasicEnemy;
import org.castelodelego.ludum28.entities.Flyer;
import org.castelodelego.ludum28.entities.Player;
import org.castelodelego.ludum28.gamemodel.RandomTimeline;
import org.castelodelego.ludum28.gamemodel.StageTimeline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {

	ShapeRenderer linedrawer;
	
	Array<Flyer> friends;
	Array<Flyer> enemies;
	Flyer player;
	
	StageTimeline timeline;
	
	
	int roartimer = 0; // TODO: Move this to the Player Class;
	
	public GameScreen()
	{
		linedrawer = new ShapeRenderer();
		linedrawer.setProjectionMatrix(Globals.cam.combined);

		friends = new Array<Flyer>();
		enemies = new Array<Flyer>();		
	}

	public void reset()
	{
		setTestPlayer();
		timeline.reset();
		Gdx.input.setInputProcessor(Globals.gamecontroller);
	}
	
	public void setTimeline(StageTimeline t)
	{
		timeline = t;
	}
	
	/** 
	 * Debug function that sets a test player
	 * FIXME: Debug Function
	 */
	void setTestPlayer()
	{
		player = new Player(new Vector2(20,20), Player.MODE_WIDE); 
		
		addFlyer(new BasicEnemy(new Vector2(600,200),new Vector2(20,20)));
		RandomTimeline t = new RandomTimeline();
		t.setSpeed(0.2f);
		timeline = t;
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (player == null)
			setTestPlayer();
		
		/*** Updating the Game ***/
		tickGame(delta);
		
		/*** Rendering the Game ***/
		debugRender();
		roartimer -=delta;//debug
	}

	void tickGame(float delta)
	{
		Iterator<Flyer> it;
		Flyer aux;
		
		player.update(delta);
		
		// UPDATING FLYERS -- ALLIES AND ENEMIES
		it = friends.iterator();
		while (it.hasNext())
		{
			aux = it.next();
			if (aux.getRemove())
				it.remove();
			else
				aux.update(delta);
		}
		
		it = enemies.iterator();
		while (it.hasNext())
		{
			aux = it.next();
			if (aux.getRemove())
				it.remove();
			else
				aux.update(delta);
		}
		
		// TESTING COLLISIONS - Allies against all enemies, enemies against the player
		// TODO: Optimize this
		
		for (Flyer col1: enemies)
		{
			for (Flyer col2: friends)
			{
				if (col1.testCollision(col2.getPosition(), col2.getHitbox()))
				{
					col1.doCollision(col2);
					col2.doCollision(col1);
				}
			}
			
			if (col1.testCollision(player.getPosition(), player.getHitbox()))
			{
				player.doCollision(col1);
			}
			
		}
		
		
		
		if (timeline != null)
			timeline.update(delta);
		
		if (timeline.testWin())
		{
			// TODO: The player has won the stage
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
	public void doPlayerRoar()
	{
		((Player) player).doRoar();
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