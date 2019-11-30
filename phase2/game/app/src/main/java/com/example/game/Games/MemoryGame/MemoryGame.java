package com.example.game.Games.MemoryGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.Games.MemoryGame.Entities.Button;
import com.example.game.Games.MemoryGame.Entities.MemoryTile;

import com.example.game.Games.Game;

import java.util.Random;

public class MemoryGame extends Game {

    private MemoryTile grid[][];
    private int gridDimensions = 4;

    private int targets;
    private int remaining;
    private int cleared;

    private int totalScore;
    private int roundScore;


    //For drawing
    private final int lineThickness = 5;
    private final int boxLineGap = 20;
    private final int uiOffset = height / 5;
    private int boxWidth;
    private int boxHeight;
    private Paint gridPaint = new Paint();

    private String state;

    private Button startBtn;


    public MemoryGame(int width, int height) {
        super(width, height);
        this.targets = 5;
        this.state = "memorize";

        createGrid();
        startBtn = new Button("Start", height / 12, width / 5,
                width / 2 - (width / 5 / 2), height - height / 12);

        gridPaint.setColor(Color.WHITE);
    }

    /**
     *
     */
    private void createGrid() {

        grid = new MemoryTile[gridDimensions][gridDimensions];

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++) {
                MemoryTile a = new MemoryTile();
                grid[i][j] = a;
            }
        setTargets();
        cleared = 0;
        remaining = 0;
    }

    /**
     *
     */
    private void setTargets() {

        Random rNum = new Random();

        int x = rNum.nextInt(grid.length);
        int y = rNum.nextInt(grid[0].length);

        while (remaining < targets) {
            while (grid[x][y].checkTarget()) {
                x = rNum.nextInt(grid.length);
                y = rNum.nextInt(grid[0].length);
            }

            grid[x][y].setAsTarget();
            remaining++;
        }

    }

    /**
     *
     */
    private void setTileDimension() {
        boxHeight = (height - uiOffset - lineThickness) / grid.length;
        boxWidth = (width - lineThickness) / grid[0].length;
    }

    /**
     * Displays the rectangles and text representing the choices, and results of the game
     */
    @Override
    protected void draw(Canvas canvas) {

        drawGrid(canvas);

        switch (state) {
            case "memorize":
                drawButton(startBtn, canvas);


                break;
            case "select":


                break;
        }
    }

    private void drawButton(Button btn, Canvas canvas) {

    }

    private void drawGrid(Canvas canvas) {
        canvas.drawLine(0, 300, width, 300, gridPaint);
        canvas.drawLine(0, height, width, height, gridPaint);
        canvas.drawLine(0, 300, 0, height, gridPaint);
        canvas.drawLine(width, 300, width, height, gridPaint);

        for (int i = 0; i < grid.length; i++) {
            //vertical grid line
            float left = boxWidth * (i + 1);
            float right = left + lineThickness;
            float top = uiOffset;
            float bottom = height;
            canvas.drawRect(left, top, right, bottom, gridPaint);

            //horizontal grid line
            float left2 = 0;
            float right2 = width;
            float top2 = boxHeight * (i + 1) + uiOffset;
            float bottom2 = top2 + lineThickness;

            canvas.drawRect(left2, top2, right2, bottom2, gridPaint);
        }

        switch (state) {
            case "memorize":



                break;
            case "select":


                break;
        }
    }

    /**
     * Getting the user's choice by recording where the screen was tapped and running the game, end
     * the game after.
     */
    @Override
    protected void receiveInput(int x, int y) {
        switch (state) {
            case "memorize":
                if ((x >= startBtn.getXLoc() && x <= startBtn.getXLoc() + startBtn.getWidth()) &&
                        (y >= startBtn.getYLoc() && y <= startBtn.getYLoc() + startBtn.getHeight())) {
                    state = "select";
                }

                break;
            case "select":
                int[] tile = tilePressed();
                int xTile = tile[0];
                int yTile = tile[1];

                if (!grid[x][y].getDisplayed()){
                    revealTile(xTile, yTile);
                }

                break;
        }
    }

    /**
     * @return
     */
    private int[] tilePressed() {
        int x = -1;
        int y = -1;
        int[] temp = new int[2];

        for (int i = 0; i < grid.length; i++) {
            if (x < boxWidth * (i + 1)) {
                x = i;

                for (int j = 0; j < grid[i].length; j++) {
                    if (uiOffset < y && y < boxHeight * (i + 1) + uiOffset) {
                        y = i;
                        break;
                    }
                }

                break;
            }
        }

        temp[0] = x;
        temp[1] = y;

        return temp;
    }

    /**
     * @param x
     * @param y
     */
    private void revealTile(int x, int y) {

        grid[x][y].setDisplayed();
        cleared++;

        if (grid[x][y].checkTarget()) {
            roundScore++;

            if (remaining == 0) {
                endRound();
            }
        } else {
            roundScore--;
        }
    }

    private void endRound() {
        if (cleared == targets){
            roundScore = roundScore * 2;
        }
        totalScore = totalScore + roundScore;

        super.endGame(totalScore);
    }

    private void reset() {
        state = "memorize";
        createGrid();
    }

    private void updateGame(int secondsPlayed) {

    }
}
