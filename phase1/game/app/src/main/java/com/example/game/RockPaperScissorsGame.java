package com.example.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;


public class RockPaperScissorsGame extends Game {

    //Game manager for running and the rock paper scissors game


    //Rectangles of buttons
    private Rect rockBtn = new Rect();
    private Rect paperBtn = new Rect();
    private Rect scissorsBtn = new Rect();

    //color of button
    private Paint btnColor = new Paint();


    RockPaperScissorsGame(int height, int width) {
        super(height, width);
    }

    public static void main(Object user) {

        // while loop {}
            //display

            // get the buttons and user choice


    }



    @Override
    void draw(Canvas canvas){


        rockBtn.set(10, super.height/2,
                super.width/5, super.height/10);
        paperBtn.set((super.width/2 - super.width/5/2), super.height/2,
                super.width/5, super.height/10);
        scissorsBtn.set((super.width - 10 - super.width/5), super.height/2,
                super.width/5, super.height/10);

        btnColor.setColor(Color.LTGRAY);
        canvas.drawRect(rockBtn, btnColor);
        canvas.drawRect(paperBtn, btnColor);
        canvas.drawRect(scissorsBtn, btnColor);
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


}
