package com.haanhgs.game.models;

import com.badlogic.gdx.utils.Json;

import java.util.Random;

public enum Angle {

    One(0), Two(1), Three(2), Four(3);

    private int code;

    Angle(int code){
        this.code = code;
    }

    private static Angle getAngle(int code){
        for (Angle angle:Angle.values()){
            if (angle.code == code){
                return angle;
            }
        }
        return null;
    }

    public static Angle random(){
        Random random = new Random();
        return getAngle(random.nextInt(4));
    }

    public static Angle rotate(Angle angle, boolean clockwise){
        int newAngle = angle.code + (clockwise ? 1 : -1);
        if (newAngle > Angle.Four.code){
            newAngle = Angle.One.code;
        }else if (newAngle < 0){
            newAngle = Angle.Four.code;
        }
        return getAngle(newAngle);
    }
}
