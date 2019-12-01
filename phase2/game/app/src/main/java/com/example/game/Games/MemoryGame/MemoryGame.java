package com.example.game.Games.MemoryGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.Games.MemoryGame.Entities.Button;
import com.example.game.Games.MemoryGame.Entities.MemoryTile;

import com.example.game.Games.Game;

import java.util.Random;

public class MemoryGame {

    private MemoryTile grid[][];
    private int gridDimensions = 4;

    private int targets;
    private int remaining;
    private int cleared;
    private int score;

    private int width;
    private int height;
    private final int lineThickness = 5;
    private final int uiOffset = 300;
    private int boxWidth;
    private int boxHeight;

    private String state;


    public MemoryGame(int width, int height) {
        this.width = width;
        this.height = height;
        this.targets = 3;
        this.state = "memorize";
        this.cleared = 0;
        this.remaining = 0;
        this.score = 0;
    }

    public String getState() {
        return state;
    }

    public MemoryTile[][] getGrid(){
        return grid;
    }

    public int getBoxHeight(){
        return boxHeight;
    }

    public int getBoxWidth(){
        return boxWidth;
    }

    public int getScore(){
        return score;
    }

    public int getRemaining(){
        return remaining;
    }

    /**
     *
     */
    protected void createGrid() {

        if (targets < 8) {
            targets++;
        }

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
     * Getting the user's choice by recording where the screen was tapped and running the game, end
     * the game after.
     */
    protected void receiveInput(int x, int y, Button startBtn) {
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
            score = score + 1000;
            remaining--;

            if (remaining == 0) {
                endRound();
            }
        } else {
            if (score > 0) {
                score = score - 1000;
            }
        }
    }

    private void endRound() {
        if (cleared == targets){
            score = score + 3000;
        }

        reset();
    }

    private void reset() {
        state = "memorize";
        createGrid();
    }
}
