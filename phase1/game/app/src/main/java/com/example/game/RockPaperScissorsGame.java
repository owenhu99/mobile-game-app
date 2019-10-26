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

    //Stores result of current round
    private String roundResult;

    //Rectangles of buttons
    private Rect rockBtn = new Rect();
    private Rect paperBtn = new Rect();
    private Rect scissorsBtn = new Rect();

    //color of button
    private Paint btnColor = new Paint();


    RockPaperScissorsGame(int height, int width) {
        super(height, width);
        createButtons();
    }

    public static void main(Object user) {

        // while loop {}
        //display

        // get the buttons and user choice


    }


    private void createButtons() {
        int btnHeight = super.height / 10;
        int btnWidth = super.width / 5;

        rockBtn.set(10, super.height / 2,
                10 + btnWidth, super.height / 2 + btnHeight);
        paperBtn.set((super.width / 2 - btnWidth / 2), super.height / 2,
                super.width / 2 + btnWidth / 2, super.height / 2 + btnHeight);
        scissorsBtn.set((super.width - 10 - btnWidth), super.height / 2,
                super.width - 10, super.height / 2 + btnHeight);

        btnColor.setColor(Color.LTGRAY);
    }

    @Override
    void draw(Canvas canvas) {


        canvas.drawRect(rockBtn, btnColor);
        canvas.drawRect(paperBtn, btnColor);
        canvas.drawRect(scissorsBtn, btnColor);
    }

    @Override
    void updateDifficulty() {


    }

    @Override
    void receiveInput(int x, int y) {
        RockPaperScissors currRound;

        if ((x >= rockBtn.left && x <= rockBtn.right) &&
                (y >= rockBtn.top && y <= rockBtn.bottom)) {
            currRound = new RockPaperScissors("Rock");
            currRound.setBotChoice();
            roundResult = currRound.findResult();
        } else if ((x >= paperBtn.left && x <= paperBtn.right) &&
                (y >= paperBtn.top && y <= paperBtn.bottom)) {
            currRound = new RockPaperScissors("Paper");
            currRound.setBotChoice();
            roundResult = currRound.findResult();
        } else if ((x >= scissorsBtn.left && x <= scissorsBtn.right) &&
                (y >= scissorsBtn.top && y <= scissorsBtn.bottom)) {
            currRound = new RockPaperScissors("Scissors");
            currRound.setBotChoice();
            roundResult = currRound.findResult();
        }
    }

    @Override
    int endGame() {
        return 0;
    }


}
