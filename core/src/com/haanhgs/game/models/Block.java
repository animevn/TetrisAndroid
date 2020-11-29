package com.haanhgs.game.models;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Block {

    private Color color;
    private int column;
    private int row;
    private Sprite sprite;

    public Block(int column, int row, Color color){
        this.color = color;
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Color getColor() {
        return color;
    }

}
