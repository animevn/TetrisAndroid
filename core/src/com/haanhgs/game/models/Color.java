package com.haanhgs.game.models;

import java.util.Random;

public enum Color {

    Blue(0, "blue.png"), Orange(1, "orange.png"), Purple(2, "purple.png"), Red(3, "red.png"),
    Teal(4, "teal.png"), Yellow(5, "yellow.png");

    private String detail;
    private int code;

    Color(int code, String detail){
        this.detail = detail;
        this.code = code;
    }

    private static Color getColor(int code){
        for (Color color:Color.values()){
            if (color.code == code){
                return color;
            }
        }
        return null;
    }

    public static  Color random(){
        Random random = new Random();
        return getColor(random.nextInt(6));
    }

    public String getDetail() {
        return detail;
    }
}
