package com.haanhgs.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.haanhgs.game.models.Repo;

public abstract class Scene {

    public Game game;
    public OrthographicCamera cam;
    public float width;
    public float height;
    public float dimen;
    public Vector2 point;

    public Scene(Game game){
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
        width = Gdx.app.getGraphics().getWidth();
        height = Gdx.app.getGraphics().getHeight();
        dimen = (width - 30)/(Repo.COLLUMS + 5);
        point = new Vector2(10, height - dimen * Repo.ROWS - 20);
    }

    public abstract void update(float delta);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
}
