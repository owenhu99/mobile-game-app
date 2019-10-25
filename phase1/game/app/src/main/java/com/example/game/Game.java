package com.example.game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * A class that represents a game.
 * */
public abstract class Game extends View {
    //represents the difficulty of the game. 1 = Easy, 2 = Hard
    int difficulty;
    int secondsPlayed;
    Thread timer;
    boolean paused;

    //Canvas dimensions
    int width;
    int height;

    public Game(Context context){
        super(context);
        timer = new GameTimer(this);
        timer.start();
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        updateDifficulty();
    }

    //Display the game to the screen.
    abstract void display(Canvas canvas);
    //update the difficulty
    abstract void updateDifficulty();
    //Get the x and y position on the screen where the user pressed.
    abstract void receiveInput(int x, int y);
    //End the game, return and integer representing a win(1), loss(-1) or tie(0)
    abstract int endGame();

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) (event.getX());
            int y = (int) (event.getY());
            receiveInput(x, y);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        display(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
