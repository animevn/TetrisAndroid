package com.haanhgs.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.ArrayList;
import java.util.List;

public class Repo {
    private static final int START_COLUMN = 4;
    private static final int START_ROW = 19;
    private static final int PREV_COLUMN = 12;
    private static final int PREV_ROW = 19;
    public static final int COLLUMS = 10;
    public static final int ROWS = 20;
    private static final int LEVEL_THRESHOLD = 10;

    private Shape currentShape;
    private Shape nextShape;
    private Block[] cells;
    private GameListener listener;
    private int score;
    private int level;
    private int totalRows;
    private Sound bomb;
    private Sound drop;

    public Repo(GameListener listener){
        cells = new Block[COLLUMS * ROWS];
        currentShape = null;
        nextShape = null;
        score = 0;
        level = 1;
        totalRows = 0;
        bomb = Gdx.audio.newSound(Gdx.files.internal("bomb.mp3"));
        drop = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
        this.listener = listener;
    }

    public void beginGame(){
        cells = new Block[COLLUMS * ROWS];
        if (currentShape == null){
            currentShape = new Shape(START_COLUMN, START_ROW);
        }
        if (nextShape == null){
            nextShape = new Shape(PREV_COLUMN, PREV_ROW);
        }
        listener.gameStart(this);
    }

    public void createShapePair(){
        currentShape = nextShape;
        nextShape = new Shape(PREV_COLUMN, PREV_ROW);
        currentShape.moveShapeTo(START_COLUMN, START_ROW);
        if (detectIllegal()){
            endGame();
        }
    }

    private void endGame(){
        listener.gameEnd(this);
    }

    private void landCurrentShape(){
        if (currentShape != null){
            for (Block block:currentShape.getBlocks()){
                cells[block.getRow() * COLLUMS + block.getColumn()] = block;
            }
            currentShape = null;
            listener.gameShapeLand(this);
        }
    }

    public void lowerCurrentShape(){
        if (currentShape != null){
            currentShape.shiftShapeDown();
            if (detectIllegal()){
                currentShape.shiftShapeUp();
                if (detectIllegal()){
                    endGame();
                }else {
                    landCurrentShape();
                }
            } else {
                if (detectTouch()){
                    landCurrentShape();
                }
            }
        }
    }

    public void dropCurrentShape(){
        if (currentShape != null){
            drop.play();
            while (!detectIllegal()){
                currentShape.shiftShapeDown();
            }
            currentShape.shiftShapeUp();
            lowerCurrentShape();
        }
        listener.gameShapeDrop(this);
    }

    public void rotateCurrentShape(){
        if (currentShape != null){
            currentShape.rotateClockwise();
            if (detectIllegal()){
                currentShape.rotateCounterClockwise();
            }
        }
    }

    public void shiftCurrentShapeLeft(){
        if (currentShape != null){
            currentShape.shiftShapeLeft();
            if (detectIllegal()){
                currentShape.shiftShapeRight();
            }
        }
    }

    public void shiftCurrentShapeRight(){
        if (currentShape != null){
            currentShape.shiftShapeRight();
            if (detectIllegal()){
                currentShape.shiftShapeLeft();
            }
        }
    }

    private boolean detectTouch(){
        if (currentShape != null){
            for (Block block:currentShape.blocksByAngle(currentShape.getAngle())){
                if  (block.getRow() == 0 ||
                    cells[(block.getRow() + 1) * COLLUMS + block.getColumn()] != null){

                    return true;
                }
            }
        }
        return false;
    }

    private boolean detectIllegal(){
        if (currentShape != null){
            for (Block block:currentShape.getBlocks()){
                if  (block.getColumn() < 0 || block.getColumn() >= COLLUMS
                    || block.getRow() < 0 || block.getRow() >= ROWS
                    || cells[block.getRow() * COLLUMS + block.getColumn()] != null){

                    return true;
                }
            }
        }
        return false;
    }


    public void filledRows(){
        List<List<Block>> remainBlocks = new ArrayList<>();

        for (int i = ROWS -1; i >= 0; i--){
            List<Block> filledRow = new ArrayList<>();
            for (int j = 0; j < COLLUMS; j++){
                if (cells[i * COLLUMS + j] != null){
                    filledRow.add(cells[i * COLLUMS + j]);
                }
            }

            if (filledRow.size() == COLLUMS){
                bomb.play();
                score += 10 * level;
                totalRows += 1;
                if (totalRows > level * LEVEL_THRESHOLD){
                    level += 1;
                    listener.gameLevelUp(this);
                }

                for (Block block:filledRow){
                    cells[block.getRow() * COLLUMS + block.getColumn()] = null;
                }

                if (remainBlocks.size() > 0){
                    for (int j = remainBlocks.size() - 1; j >= 0; j--){
                        for (Block block:remainBlocks.get(j)){
                            cells[(block.getRow() - 1)*COLLUMS + block.getColumn()] =
                                    cells[block.getRow()*COLLUMS + block.getColumn()];
                            cells[block.getRow()*COLLUMS + block.getColumn()] = null;
                            block.setRow(block.getRow() - 1);
                        }
                    }
                }
            }else if (filledRow.size() > 0){
                remainBlocks.add(filledRow);
            }
        }
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

    public Shape getNextShape() {
        return nextShape;
    }

    public Block[] getCells() {
        return cells;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }
}
