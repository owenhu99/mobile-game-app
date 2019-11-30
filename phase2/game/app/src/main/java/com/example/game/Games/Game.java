package com.example.game.Games;

import android.graphics.Bitmap;
import android.graphics.Canvas;
/**
 * A class that represents a game.
 * */
public abstract class Game{
    protected int height;
    protected int width;
    boolean gameEnded = false;

    protected Bitmap enemyBMP;
    protected Bitmap friendlyBMP;
    protected Bitmap coinBMP;
    private int points;


    public Game(int width, int height){
        this.height = height;
        this.width = width;
    }

    //Display the game to the screen.
    protected abstract void draw(Canvas canvas);
    //Get the x and y position on the screen where the user pressed.
    protected abstract void receiveInput(int x, int y);
    //End the game, return and integer representing a win(1), loss(-1) or tie(0)
    protected void endGame(int points){
        gameEnded = true;
        this.points = points;
    }
    int getPoints(){return points;}


    public void setCoinBMP(Bitmap coinBMP) { this.coinBMP = coinBMP; }
    public void setEnemyBMP(Bitmap enemyBMP) { this.enemyBMP = enemyBMP; }
    public void setFriendlyBMP(Bitmap friendlyBMP) { this.friendlyBMP = friendlyBMP; }
}
