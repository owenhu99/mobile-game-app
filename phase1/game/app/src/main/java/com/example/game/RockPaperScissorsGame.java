package com.example.game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;


public class RockPaperScissorsGame extends Game {

    //Game manager for running and the rock paper scissors game

    RockPaperScissorsGame(Context context){
        super(context);
    }



    @Override
    void display(Canvas canvas){


    }

    @Override
    void updateDifficulty(){

    }

    @Override
    void receiveInput(int x, int y){
        if (x > 1 && x < 2  && y > 6 && y < 7){
            new RockPaperScissors("Rock");
        } else if (x > 4 && x < 5 && y > 6 && y < 7){
            new RockPaperScissors("Paper");
        } else if (x > 7 && x < 8 && y > 6 && y < 7) {
            new RockPaperScissors("Scissors");
        }
    }

    @Override
    int endGame(){return 0;}

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int x = (int)event.getX();
        int y = (int)event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }

        receiveInput(x, y);

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        display(canvas);
    }

}
