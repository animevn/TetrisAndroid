package com.haanhgs.game.scenes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SceneMenu extends Scene {

    private TextButton button;
    private TextButton.TextButtonStyle style;
    private BitmapFont font;

    public SceneMenu(Game game) {
        super(game);

        font = new BitmapFont();
        style = new TextButton.TextButtonStyle();
        style.font = font;
        button = new TextButton("Play", style);
        button.setPosition(width/2, height/2);
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        button.draw(spriteBatch, 1);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
