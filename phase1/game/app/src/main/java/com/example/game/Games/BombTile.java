package com.example.game.Games;

import android.graphics.Color;
import android.graphics.Paint;

// use when drawing cool shit hopefully
//import android.graphics.drawable.AdaptiveIconDrawable;

public class BombTile {
    private boolean displayed = false;
    private boolean isBomb = false;

    private Paint bombColor;
    private Paint notBombColor;
    private Paint hidden;

    // Look to add personalized bomb pictures
    // private AdaptiveIconDrawable bomb =

    BombTile(){
        // initialize these values later, or change to a different graphic.
        bombColor = new Paint();
        notBombColor = new Paint();
        hidden = new Paint();

        bombColor.setColor(Color.BLUE);
        notBombColor.setColor(Color.RED);
        hidden.setColor(Color.GRAY);
    }
    @Override
    public String toString(){
        if(isBomb)
            return "T";
        else
            return "F";
    }

    void setIsBomb(){ isBomb = true;}
    void setDisplayed(){ displayed = true;}
    boolean checkBomb(){ return isBomb; }

    Paint getColor(){
        if(displayed){
            if (isBomb){
                return bombColor;
            }
            else{
                return notBombColor;
            }
        }
        else{
            return hidden;
        }
    }

}
