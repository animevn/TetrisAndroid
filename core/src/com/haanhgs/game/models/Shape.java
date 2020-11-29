package com.haanhgs.game.models;

import java.util.HashMap;

public class Shape {

    private Block[] blocks = new Block[4];
    private int column;
    private int row;
    private Angle angle;
    private ShapeType type;

    public Shape(int column, int row){
        this.column = column;
        this.row = row;
        Color color = Color.random();
        angle = Angle.random();
        type = ShapeType.random();

        for (int i = 0; i < blocks.length; i++){
            blocks[i] = new Block(column + blockDiff(type).get(angle)[i][0],
                    row + blockDiff(type).get(angle)[i][1], color);
        }
    }

    private void rotateShape(Angle angle){
        for (int i = 0; i < blocks.length; i++){
            blocks[i].setColumn(column + blockDiff(type).get(angle)[i][0]);
            blocks[i].setRow(row + blockDiff(type).get(angle)[i][1]);
        }
        this.angle = angle;
    }

    public void moveShapeTo(int column, int row){
        this.column = column;
        this.row = row;
        rotateShape(angle);
    }

    public void rotateClockwise(){
        rotateShape(Angle.rotate(angle, true));
    }

    public void rotateCounterClockwise(){
        rotateShape(Angle.rotate(angle, false));
    }

    private void shiftShape(int columns, int rows){
        this.column += columns;
        this.row += rows;
        rotateShape(angle);
    }

    public void shiftShapeDown(){
        shiftShape(0, -1);
    }

    public void shiftShapeUp(){
        shiftShape(0, 1);
    }

    public void shiftShapeLeft(){
        shiftShape(-1, 0);
    }

    public void shiftShapeRight(){
        shiftShape(1, 0);
    }

    private HashMap<Angle, int[][]> blockDiff(ShapeType type){

        switch (type){
            case LineShape:
                return new HashMap<Angle, int[][]>(){
                    {
                        put(Angle.One, new int[][]{{0, 0}, {0, -1}, {0, -2}, {0, -3}});
                        put(Angle.Two, new int[][]{{-1, 0}, {0, 0}, {1, 0}, {2, 0}});
                        put(Angle.Three, new int[][]{{0, 0}, {0, -1}, {0, -2}, {0, -3}});
                        put(Angle.Four, new int[][]{{-1, 0}, {0, 0}, {1, 0}, {2, 0}});
                    }
                };

            case SquareShape:
                return new HashMap<Angle, int[][]>(){
                    {
                        put(Angle.One, new int[][]{{0, 0}, {1, 0}, {1, -1}, {0, -1}});
                        put(Angle.Two, new int[][]{{0, 0}, {1, 0}, {1, -1}, {0, -1}});
                        put(Angle.Three, new int[][]{{0, 0}, {1, 0}, {1, -1}, {0, -1}});
                        put(Angle.Four, new int[][]{{0, 0}, {1, 0}, {1, -1}, {0, -1}});
                    }
                };

            case TShape:
                return new HashMap<Angle, int[][]>(){
                    {
                        put(Angle.One, new int[][]{{-1, 0}, {0, 0}, {1, 0}, {0, -1}});
                        put(Angle.Two, new int[][]{{0, 0}, {0, -1}, {0, -2}, {-1, -1}});
                        put(Angle.Three, new int[][]{{1, -1}, {0, -1}, {-1, -1}, {0, 0}});
                        put(Angle.Four, new int[][]{{0, -2}, {0, -1}, {0, 0}, {1, -1}});
                    }
                };

            case LShape:
                return new HashMap<Angle, int[][]>(){
                    {
                        put(Angle.One, new int[][]{{-1, 0}, {-1, -1}, {-1, -2}, {0, -2}});
                        put(Angle.Two, new int[][]{{1, 0}, {0, 0}, {-1, 0}, {-1, -1}});
                        put(Angle.Three, new int[][]{{0, -2}, {0, -1}, {0, 0}, {-1, 0}});
                        put(Angle.Four, new int[][]{{-1, -1}, {0, -1}, {1, -1}, {1, 0}});
                    }
                };

            case JShape:
                return new HashMap<Angle, int[][]>(){
                    {
                        put(Angle.One, new int[][]{{1, 0}, {1, -1}, {1, -2}, {0, -2}});
                        put(Angle.Two, new int[][]{{1, -1}, {0, -1}, {-1, -1}, {-1, 0}});
                        put(Angle.Three, new int[][]{{0, -2}, {0, -1}, {0, 0}, {1, 0}});
                        put(Angle.Four, new int[][]{{-1, 0}, {0, 0}, {1, 0}, {1, -1}});
                    }
                };

            case ZShape:
                return new HashMap<Angle, int[][]>(){
                    {
                        put(Angle.One, new int[][]{{1, 0}, {1, -1}, {0, -1}, {0, -2}});
                        put(Angle.Two, new int[][]{{1, -1}, {0, -1}, {0, 0}, {-1, 0}});
                        put(Angle.Three, new int[][]{{1, 0}, {1, -1}, {0, -1}, {0, -2}});
                        put(Angle.Four, new int[][]{{1, -1}, {0, -1}, {0, 0}, {-1, 0}});
                    }
                };

            case SShape:
                return new HashMap<Angle, int[][]>(){
                    {
                        put(Angle.One, new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {0, -2}});
                        put(Angle.Two, new int[][]{{1, 0}, {0, 0}, {0, -1}, {-1, -1}});
                        put(Angle.Three, new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {0, -2}});
                        put(Angle.Four, new int[][]{{1, 0}, {0, 0}, {0, -1}, {-1, -1}});
                    }
                };

            default:
                return new HashMap<Angle, int[][]>(){
                    {
                        put(Angle.One, new int[][]{{0, 0}, {1, 0}, {1, -1}, {0, -1}});
                        put(Angle.Two, new int[][]{{0, 0}, {1, 0}, {1, -1}, {0, -1}});
                        put(Angle.Three, new int[][]{{0, 0}, {1, 0}, {1, -1}, {0, -1}});
                        put(Angle.Four, new int[][]{{0, 0}, {1, 0}, {1, -1}, {0, -1}});
                    }
                };
        }
    }

    private HashMap<Angle, Block[]> bottomBlock(ShapeType type){
        switch (type){
            default:
                return new HashMap<Angle, Block[]>(){
                    {
                        put(Angle.One, new Block[]{blocks[2], blocks[3]});
                        put(Angle.Two, new Block[]{blocks[2], blocks[3]});
                        put(Angle.Three, new Block[]{blocks[2], blocks[3]});
                        put(Angle.Four, new Block[]{blocks[2], blocks[3]});
                    }
                };

            case SShape:
                return new HashMap<Angle, Block[]>(){
                    {
                        put(Angle.One, new Block[]{blocks[1], blocks[3]});
                        put(Angle.Two, new Block[]{blocks[0], blocks[2], blocks[3]});
                        put(Angle.Three, new Block[]{blocks[1], blocks[3]});
                        put(Angle.Four, new Block[]{blocks[0], blocks[2], blocks[3]});
                    }
                };
            case ZShape:
                return new HashMap<Angle, Block[]>(){
                    {
                        put(Angle.One, new Block[]{blocks[1], blocks[3]});
                        put(Angle.Two, new Block[]{blocks[0], blocks[1], blocks[3]});
                        put(Angle.Three, new Block[]{blocks[1], blocks[3]});
                        put(Angle.Four, new Block[]{blocks[0], blocks[1], blocks[3]});
                    }
                };

            case JShape:
                return new HashMap<Angle, Block[]>(){
                    {
                        put(Angle.One, new Block[]{blocks[2], blocks[3]});
                        put(Angle.Two, new Block[]{blocks[0], blocks[1], blocks[2]});
                        put(Angle.Three, new Block[]{blocks[0], blocks[3]});
                        put(Angle.Four, new Block[]{blocks[0], blocks[1], blocks[2]});
                    }
                };

            case LShape:
                return new HashMap<Angle, Block[]>(){
                    {
                        put(Angle.One, new Block[]{blocks[2], blocks[3]});
                        put(Angle.Two, new Block[]{blocks[0], blocks[1], blocks[3]});
                        put(Angle.Three, new Block[]{blocks[0], blocks[3]});
                        put(Angle.Four, new Block[]{blocks[0], blocks[1], blocks[2]});
                    }
                };

            case TShape:
                return new HashMap<Angle, Block[]>(){
                    {
                        put(Angle.One, new Block[]{blocks[0], blocks[2], blocks[3]});
                        put(Angle.Two, new Block[]{blocks[2], blocks[3]});
                        put(Angle.Three, new Block[]{blocks[0], blocks[1], blocks[2]});
                        put(Angle.Four, new Block[]{blocks[0], blocks[3]});
                    }
                };

            case SquareShape:
                return new HashMap<Angle, Block[]>(){
                    {
                        put(Angle.One, new Block[]{blocks[2], blocks[3]});
                        put(Angle.Two, new Block[]{blocks[2], blocks[3]});
                        put(Angle.Three, new Block[]{blocks[2], blocks[3]});
                        put(Angle.Four, new Block[]{blocks[2], blocks[3]});
                    }
                };

            case LineShape:
                return new HashMap<Angle, Block[]>(){
                    {
                        put(Angle.One, new Block[]{blocks[3]});
                        put(Angle.Two, new Block[]{blocks[0], blocks[1], blocks[2], blocks[3]});
                        put(Angle.Three, new Block[]{blocks[3]});
                        put(Angle.Four, new Block[]{blocks[0], blocks[1], blocks[2], blocks[3]});
                    }
                };
        }
    }

    public Block[] blocksByAngle(Angle angle){
        return bottomBlock(type).get(angle);
    }


    public Block[] getBlocks() {
        return blocks;
    }

    public Angle getAngle() {
        return angle;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
