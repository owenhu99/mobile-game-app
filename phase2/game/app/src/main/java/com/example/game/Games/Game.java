package com.example.game.Games;

import android.content.Context;
import android.graphics.Canvas;


/**
 * A class that represents a game.
 * */
public abstract class Game{
    protected int height;
    protected int width;
    boolean gameEnded;
    int points;
    int secondsPlayed;

    protected Context context;


    public Game(int width, int height){
        this.height = height;
        this.width = width;

        // this is how you will create your own bitmaps.
        // Bitmap bmp = BitmapFactory(context.getResources(), R.drawable.[file name should pop up])
    }

    //Display the game to the screen.
    protected abstract void draw(Canvas canvas);

    //Get the x and y position on the screen where the user pressed.
    protected abstract void receiveInput(int x, int y);

    //End the game, return and integer representing a win(1), loss(-1) or tie(0)
    public void endGame(int points){
        gameEnded = true;
        this.points = points;
    }
    int getPoints(){
        return points;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
