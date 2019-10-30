package com.example.game.Games;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * A class that represents a game.
 * */
public abstract class Game{
    //represents the difficulty of the game. 1 = Easy, 2 = Hard
    int difficulty;

    int height;
    int width;

    boolean gameEnded = false;


    public Game(int d){
        this.difficulty = d;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        updateDifficulty();
    }
    public void setWidthHeight(int width, int height){
        this.height = height;
        this.width = width;
    }

    //Display the game to the screen.
    abstract void draw(Canvas canvas);
    //update the difficulty
    abstract void updateDifficulty();
    //Get the x and y position on the screen where the user pressed.
    abstract void receiveInput(int x, int y);
    //End the game, return and integer representing a win(1), loss(-1) or tie(0)
    abstract int endGame();
    abstract void reset();
}
