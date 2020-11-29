package com.haanhgs.game.models;

import java.util.Random;

public enum ShapeType {
    SquareShape(0), LineShape(1), TShape(2), LShape(3), JShape(4), ZShape(5), SShape(6);

    private int code;

    ShapeType(int code){
        this.code = code;
    }

    private static ShapeType getShapeType(int code){
        for (ShapeType shapeType:ShapeType.values()){
            if (shapeType.code == code){
                return shapeType;
            }
        }
        return null;
    }

    public static ShapeType random(){
        Random random = new Random();
        return getShapeType(random.nextInt(7));
    }
}
