package com.example.game.Games;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class RockPaperScissorsGame extends Game {

    //Game manager for running and the rock paper scissors game

    //Stores result of current round
    private String roundResult;

    //color and size of text
    private Paint btnColor = new Paint();
    private Paint textColor = new Paint();
    private Paint uiTextColor = new Paint();

    //Stores current action of player and bot
    private String userAction;
    private String botAction;

    RockPaperScissorsGame(int d) {
        super(d);
        createButtons();
        userAction = "";
        botAction = "";
        roundResult = "";
    }

    /**
     * Creates the button text to be displayed when the game starts
     */
    private void createButtons() {

        btnColor.setColor(Color.GRAY);
        textColor.setColor(Color.RED);
        textColor.setTextSize(50);
        textColor.setTextAlign(Paint.Align.CENTER);

        uiTextColor.setColor(Color.WHITE);
        uiTextColor.setTextSize(50);

    }

    /**
     * Displays the rectangles and text representing the choices, and results of the game
     */
    @Override
    void draw(Canvas canvas) {

        System.out.println(canvas == null);

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
    void updateDifficulty() { }

    /**
     * Getting the user's choice by recording where the screen was tapped and running the game, end
     * the game after.
     */
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

    /**
     * Ends the game and returns required to update User's statistics
     *
     * @return A int representing the results of this game
     */
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
