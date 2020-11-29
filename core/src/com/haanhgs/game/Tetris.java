package com.haanhgs.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haanhgs.game.scenes.Game;
import com.haanhgs.game.scenes.SceneMenu;
import com.haanhgs.game.scenes.ScenePlay;

public class Tetris extends ApplicationAdapter {

	private Game game;
	private SpriteBatch spriteBatch;
	private Music music;

	@Override
	public void create () {
	    game = new Game();
	    spriteBatch = new SpriteBatch();
	    game.set(new ScenePlay(game));
	    music = Gdx.audio.newMusic(Gdx.files.internal("theme.ogg"));
	    music.setLooping(true);
	    music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.update(Gdx.graphics.getDeltaTime());
        game.render(spriteBatch);
	}
	
	@Override
	public void dispose () {
	}
}
