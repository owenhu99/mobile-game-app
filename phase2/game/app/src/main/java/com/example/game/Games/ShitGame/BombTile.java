package com.example.game.Games.ShitGame;

import android.graphics.Color;
import android.graphics.Paint;

// use when drawing cool shit hopefully
//import android.graphics.drawable.AdaptiveIconDrawable;

/**
 * A tile in a game of BadMineSweeper.
 */
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

        bombColor.setColor(Color.RED);
        notBombColor.setColor(Color.GREEN);
        hidden.setColor(Color.GRAY);
    }
    @Override
    public String toString(){
        if(isBomb)
            return "T";
        else
            return "F";
    }

    /**
     * set this tile as a bomb
     */
    void setIsBomb(){ isBomb = true;}

    /**
     * set this tile as revealed: displayed
     */
    void setDisplayed(){ displayed = true;}

    /**
     * check if this is a bomb
     * @return isBomb
     */
    boolean checkBomb(){ return isBomb; }


    /**
     * Return the color of this bombtile.
     * @return color of bomb.
     */
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
