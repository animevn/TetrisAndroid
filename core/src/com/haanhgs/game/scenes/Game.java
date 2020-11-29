package com.haanhgs.game.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class Game {
    private Stack<Scene>scenes;

    public Game(){
        scenes = new Stack<Scene>();
    }

    public void set(Scene scene){
        if (scenes.isEmpty()){
            scenes.push(scene);
        }else {
            scenes.pop();
            scenes.push(scene);
        }
    }

    public void update(float delta){
        scenes.peek().update(delta);
    }

    public void render(SpriteBatch spriteBatch){
        scenes.peek().render(spriteBatch);
    }
}
