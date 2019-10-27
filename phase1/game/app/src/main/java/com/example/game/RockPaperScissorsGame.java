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

    //color of button and text
    private Paint btnColor = new Paint();
    private Paint textColor = new Paint();


    RockPaperScissorsGame(int d) {
        super(d);
        createButtons();
    }

    public static void main(Object user) {

        // while loop {}
        //display

        // get the buttons and user choice


    }


    private void createButtons() {
        int btnHeight = height / 10;
        int btnWidth = width / 5;

        rockBtn.set(10, height / 2,
                10 + btnWidth, height / 2 + btnHeight);
        paperBtn.set((width / 2 - btnWidth / 2), height / 2,
                width / 2 + btnWidth / 2, height / 2 + btnHeight);
        scissorsBtn.set((width - 10 - btnWidth), height / 2,
                width - 10, height / 2 + btnHeight);

        btnColor.setColor(Color.GRAY);
        textColor.setColor(Color.RED);
        textColor.setTextSize(50);
        textColor.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    void draw(Canvas canvas) {

        System.out.println(canvas == null);

        //canvas.drawRect(rockBtn, btnColor);
        //canvas.drawRect(paperBtn, btnColor);
        //canvas.drawRect(scissorsBtn, btnColor);
        int btnHeight = height / 12;
        int btnWidth = width / 5;

        canvas.drawRect(10, height / 2,
                10 + btnWidth, height / 2 + btnHeight, btnColor);
        canvas.drawRect((width / 2) - (btnWidth / 2), height / 2,
                (width / 2) + (btnWidth / 2), height / 2 + btnHeight, btnColor);
        canvas.drawRect(width - 10 - btnWidth, height / 2,
                width - 10, height / 2 + btnHeight, btnColor);

        canvas.drawText("Rock", 10 + (btnWidth / 2), height / 2 + (btnHeight / 2)
                + textColor.getTextSize() / 2, textColor);
        canvas.drawText("Paper", (width / 2), height / 2 + (btnHeight / 2)
                + textColor.getTextSize() / 2, textColor);
        canvas.drawText("Scissors", width - 10 - (btnWidth / 2), height / 2 +
                (btnHeight / 2) + textColor.getTextSize() / 2, textColor);


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

    @Override
    void reset() {
    }

    ;
}
