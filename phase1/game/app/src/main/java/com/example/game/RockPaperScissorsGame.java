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
    //private Rect rockBtn = new Rect();
    //private Rect paperBtn = new Rect();
    //private Rect scissorsBtn = new Rect();

    //color of button and text
    private Paint btnColor = new Paint();
    private Paint textColor = new Paint();
    private Paint uiTextColor = new Paint();


    private int roundNum;

    private String userAction;
    private String botAction;

    RockPaperScissorsGame(int d) {
        super(d);
        createButtons();
        roundNum = 1;
        userAction = "";
        botAction = "";
        roundResult = "";
    }

    public static void main(Object user) {

        // while loop {}
        //display

        // get the buttons and user choice


    }


    private void createButtons() {
        int btnHeight = height / 10;
        int btnWidth = width / 5;

        /*
        rockBtn.set(10, height / 2,
                10 + btnWidth, height / 2 + btnHeight);
        paperBtn.set((width / 2 - btnWidth / 2), height / 2,
                width / 2 + btnWidth / 2, height / 2 + btnHeight);
        scissorsBtn.set((width - 10 - btnWidth), height / 2,
                width - 10, height / 2 + btnHeight);*/

        btnColor.setColor(Color.GRAY);
        textColor.setColor(Color.RED);
        textColor.setTextSize(50);
        textColor.setTextAlign(Paint.Align.CENTER);

        uiTextColor.setColor(Color.WHITE);
        uiTextColor.setTextSize(50);

    }

    @Override
    void draw(Canvas canvas) {

        System.out.println(canvas == null);

        //canvas.drawRect(rockBtn, btnColor);
        //canvas.drawRect(paperBtn, btnColor);
        //canvas.drawRect(scissorsBtn, btnColor);
        int btnHeight = height / 12;
        int btnWidth = width / 5;

        canvas.drawRect(20, height / 2,
                20 + btnWidth, height / 2 + btnHeight, btnColor);
        canvas.drawRect((width / 2) - (btnWidth / 2), height / 2,
                (width / 2) + (btnWidth / 2), height / 2 + btnHeight, btnColor);
        canvas.drawRect(width - 20 - btnWidth, height / 2,
                width - 20, height / 2 + btnHeight, btnColor);

        canvas.drawText("Rock", 20 + (btnWidth / 2), height / 2 + (btnHeight / 2)
                + textColor.getTextSize() / 2, textColor);
        canvas.drawText("Paper", (width / 2), height / 2 + (btnHeight / 2)
                + textColor.getTextSize() / 2, textColor);
        canvas.drawText("Scissors", width - 20 - (btnWidth / 2), height / 2 +
                (btnHeight / 2) + textColor.getTextSize() / 2, textColor);


        canvas.drawText("Your Move: " + userAction, 20, height / 3, uiTextColor);
        canvas.drawText("Bot's Move: " + botAction, 20, height / 3 + 100, uiTextColor);
        canvas.drawText("Winner: " + roundResult, 20, height / 3 + 200, uiTextColor);
    }

    @Override
    void updateDifficulty() {


    }

    @Override
    void receiveInput(int x, int y) {
        int btnHeight = height / 12;
        int btnWidth = width / 5;

        RockPaperScissors currRound;

        if ((x >= 20 && x <= 20 + btnWidth) &&
                (y >= height / 2 && y <= height / 2 + btnHeight)) {
            userAction = "Rock";
            currRound = new RockPaperScissors("Rock");
            currRound.setBotChoice();
            botAction = currRound.getBotChoice();
            roundResult = currRound.findResult();
        } else if ((x >= (width / 2) - (btnWidth / 2) && x <= (width / 2) + (btnWidth / 2)) &&
                (y >= height / 2 && y <= height / 2 + btnHeight)) {

            userAction = "Paper";
            currRound = new RockPaperScissors("Paper");
            currRound.setBotChoice();
            botAction = currRound.getBotChoice();
            roundResult = currRound.findResult();
        } else if ((x >= width - 20 - btnWidth && x <= width - 20) &&
                (y >= height / 2 && y <= height / 2 + btnHeight)) {

            userAction = "Scissors";
            currRound = new RockPaperScissors("Scissors");
            currRound.setBotChoice();
            botAction = currRound.getBotChoice();
            roundResult = currRound.findResult();
        }

        endGame();
    }

    @Override
    int endGame() {
        gameEnded = true;

        if (roundResult.equals("bot")) {
            return -1;
        } else if (roundResult.equals("user")) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    void reset() {
        gameEnded = false;

    }

}
