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

    private int score;

    private MemoryTimer timer;


    //For drawing
    private final int lineThickness = 5;
    private final int boxLineGap = 20;
    private final int uiOffset = 300;
    private int boxWidth;
    private int boxHeight;
    private Paint gridPaint = new Paint();
    private Paint textPaint = new Paint();

    private String state;

    private Button startBtn;


    public MemoryGame(int width, int height) {
        super(width, height);
        this.targets = 5;
        this.state = "memorize";
        this.cleared = 0;
        this.remaining = 0;
        this.score = 0;

        createGrid();
        startBtn = new Button("Start", height / 12, width / 5,
                width/2 + 300, 100);

        timer = new MemoryTimer(this);
        timer.start();

        gridPaint.setColor(Color.WHITE);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(60);
    }

    /**
     *
     */
    private void createGrid() {

        grid = new MemoryTile[gridDimensions][gridDimensions];

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++) {
                MemoryTile a = new MemoryTile();
                grid[i][j] = a;
            }
        }


        cleared = 0;
        remaining = 0;

        setTargets();
        setTileDimension();

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

        drawStats(canvas);

        switch (state) {
            case "memorize":
                drawButton(startBtn, canvas);

                break;
            case "select":


                break;
        }
    }

    private void drawButton(Button btn, Canvas canvas) {
        canvas.drawRect(btn.getXLoc(), btn.getYLoc(),
                btn.getXLoc() + btn.getWidth(), btn.getYLoc() + btn.getHeight(),
                textPaint);
        canvas.drawText(btn.getText(), btn.getXLoc() + (btn.getWidth() / 2), btn.getYLoc() +
                (btn.getHeight() / 2) + btn.getTextPaint().getTextSize() / 2, btn.getTextPaint());
    }

    private void drawStats(Canvas canvas){
        canvas.drawText("Time Left:" + timer.getCurrentTime() ,50, 50, textPaint);
        canvas.drawText("Remaining: " + remaining,50, 150, textPaint);
        canvas.drawText("Score: " + score,50, 250, textPaint);

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

        drawTargets(canvas);
    }

    private void drawTargets(Canvas canvas){
        float startX;
        float startY;
        float endX;
        float endY;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                startX = (boxWidth * i) + boxLineGap;
                startY = uiOffset + (boxHeight * j) + boxLineGap;
                endX = (boxWidth * (i + 1)) - boxLineGap;
                endY = uiOffset + (boxHeight * (j + 1)) - boxLineGap;
                canvas.drawRect(startX, startY, endX, endY, grid[i][j].getColor(state));
            }
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

                int xTile = -1;
                int yTile = -1;

                for (int i = 0; i < grid.length; i++) {
                    if (x < boxWidth * (i + 1)) {
                        xTile = i;
                        for (int j = 0; j < grid[i].length; j++) {
                            if (uiOffset < y && y < boxHeight * (j + 1) + uiOffset) {
                                yTile = j;
                                break;
                            }
                        }
                        break;
                    }
                }
                if (!grid[xTile][yTile].getDisplayed()){
                    revealTile(xTile, yTile);
                }

                System.out.println("received input");

                break;
        }
    }

    /**
     * @param x
     * @param y
     */
    private void revealTile(int x, int y) {

        grid[x][y].setDisplayed();
        cleared++;

        if (grid[x][y].checkTarget()) {
            score++;
            remaining--;

            if (remaining == 0) {
                endRound();
            }
        } else {
            score--;
        }
    }

    private void endRound() {
        if (cleared == targets){
            score = score + 3;
        }

        reset();
    }

    public void endGame(){
        super.endGame(score);
    }

    private void reset() {
        state = "memorize";
        createGrid();
    }
}
