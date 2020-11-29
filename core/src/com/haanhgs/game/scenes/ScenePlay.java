package com.haanhgs.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.haanhgs.game.models.Block;
import com.haanhgs.game.models.GameListener;
import com.haanhgs.game.models.Repo;
import com.haanhgs.game.models.Shape;

public class ScenePlay extends Scene implements GameListener, GestureDetector.GestureListener {

    private Repo repo;
    private Texture bgTexture;
    private Texture gbTexture;
    private Sprite background;
    private Sprite gameboard;
    private float timer = 0;
    private float timePerMove;
    private Vector2 panPoint;
    private BitmapFont font;
    private Texture spriteTexture;


    public ScenePlay(Game game) {
        super(game);
        GestureDetector gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);
        panPoint = new Vector2(0, 0);

        bgTexture = new Texture("background.png");
        background = new Sprite(bgTexture);
        background.setSize(width, height);
        gbTexture = new Texture("gameboard.png");
        gameboard = new Sprite(gbTexture);
        gameboard.setSize(dimen * 10, dimen * 20);
        gameboard.setPosition(point.x, point.y);

        repo = new Repo(this);
        repo.beginGame();
        font = new BitmapFont(Gdx.files.internal("test.fnt"), false);
        float density = Gdx.graphics.getDensity();
        font.getData().setScale(density * 0.6f);
    }

    //run on computer
    private void keyControl(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            repo.rotateCurrentShape();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            repo.shiftCurrentShapeLeft();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            repo.shiftCurrentShapeRight();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            repo.dropCurrentShape();
        }
    }


    @Override
    public void update(float delta) {
        keyControl();
        timer += delta;
        if (timer > timePerMove){
            repo.lowerCurrentShape();
            timer = 0;
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();

        background.draw(spriteBatch);
        gameboard.draw(spriteBatch);

        renderShape(repo.getCurrentShape(), spriteBatch);
        renderShape(repo.getNextShape(), spriteBatch);

        for (Block block:repo.getCells()){
            if (block != null){
                block.getSprite().setPosition(colPoint(block.getColumn()),
                        rowPoint(block.getRow()));
                block.getSprite().draw(spriteBatch);
            }
        }


        font.draw(spriteBatch, "SCORES", colPoint(11), rowPoint(15));
        font.draw(spriteBatch, String.valueOf(repo.getScore()), colPoint(12), rowPoint(13));

        font.draw(spriteBatch, "LEVEL", colPoint(11), rowPoint(11));
        font.draw(spriteBatch, String.valueOf(repo.getLevel()), colPoint(12), rowPoint(9));

        spriteBatch.end();
    }

    private void renderShape(Shape shape, SpriteBatch spriteBatch){
        if (shape != null){
            for (Block block:shape.getBlocks()){
                if (block.getSprite() == null){
                    spriteTexture = new Texture(block.getColor().getDetail());
                    block.setSprite(new Sprite(spriteTexture));
                }
                block.getSprite().setSize(dimen, dimen);
                block.getSprite().setPosition(colPoint(block.getColumn()),
                        rowPoint(block.getRow()));
                block.getSprite().draw(spriteBatch);
            }
        }
    }

    private float colPoint(int column){
        return point.x + dimen * column;
    }

    private float rowPoint(int row){
        return point.y + dimen * row;
    }


    private float getTimePerMove(int level){
        switch (level){
            case 1:
                return  0.5f - (level - 1) * 0.025f;
            case 2:
                return  0.5f - (level - 1) * 0.025f;
            case 3:
                return  0.5f - (level - 1) * 0.025f;
            case 4:
                return  0.5f - (level - 1) * 0.025f;
            case 5:
                return  0.5f - (level - 1) * 0.025f;
            case 6:
                return  0.5f - (level - 1) * 0.025f;
            case 7:
                return  0.5f - (level - 1) * 0.025f;
            case 8:
                return  0.5f - (level - 1) * 0.025f;
            case 9:
                return  0.5f - (level - 1) * 0.025f;
            case 10:
                return  0.5f - (level - 1) * 0.025f;
            case 11:
                return  0.5f - (level - 1) * 0.025f;
            case 12:
                return  0.5f - (level - 1) * 0.025f;
            case 13:
                return  0.5f - (level - 1) * 0.025f;
            case 14:
                return  0.5f - (level - 1) * 0.025f;
            case 15:
                return  0.5f - (level - 1) * 0.025f;
            case 16:
                return  0.5f - (level - 1) * 0.025f;
            case 17:
                return  0.5f - (level - 1) * 0.025f;
            case 18:
                return  0.09f;
            case 19:
                return  0.08f;
            case 20:
                return  0.07f;
            case 21:
                return  0.06f;
            case 22:
                return  0.05f;
            case 23:
                return  0.045f;
            case 24:
                return  0.04f;
            case 25:
                return  0.035f;
            default:
                return  0.03f;

        }
    }


    @Override
    public void dispose() {
        bgTexture.dispose();
        gbTexture.dispose();
        spriteTexture.dispose();
    }

    @Override
    public void gameStart(Repo repo) {
        timePerMove = getTimePerMove(repo.getLevel());
    }

    @Override
    public void gameShapeLand(Repo repo) {

        repo.filledRows();

        repo.createShapePair();
    }

    @Override
    public void gameEnd(Repo repo) {
        repo.beginGame();
    }

    @Override
    public void gameShapeDrop(Repo repo) {

    }

    @Override
    public void gameLevelUp(Repo repo) {
        timePerMove = getTimePerMove(repo.getLevel());
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        repo.rotateCurrentShape();
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (Math.abs(velocityX) < Math.abs(velocityY)){
            if (velocityY > 0){
                repo.dropCurrentShape();
            }
        }
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        if (Math.abs(deltaY) < Math.abs(deltaX)){
           if (Math.abs(x - panPoint.x) > dimen * 0.8){
               if (deltaX > 0){
                   repo.shiftCurrentShapeRight();
                   panPoint.x = x;
               } else if (deltaX < 0){
                   repo.shiftCurrentShapeLeft();
                   panPoint.x = x;
               }
           }
        }

        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
                         Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
