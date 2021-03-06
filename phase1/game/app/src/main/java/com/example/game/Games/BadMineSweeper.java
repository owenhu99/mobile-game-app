package com.example.game.Games;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;


/**
 * Child class of Game. An instance of a BadMineSweeper game.
 */
class BadMineSweeper extends Game {
    private BombTile[][] grid;
    private int mines;
    private int spaces_cleared;
    private int win_num;
    // future functionality of setting the grid.
    private int grid_size = 4;

    // drawings
    private final int lineThickness = 5;
    private final int boxLineGap = 20;
    private final int statsDisplayGap = 300;
    private int boxWidth;
    private int boxHeight;

    private Paint gridPaint = new Paint();

    BadMineSweeper(int d) {
        super(d);
        gridPaint.setColor(Color.WHITE);
        createNewBoard();
    }

    /**
     * create a new board of size grid_size, setting the mines randomly.
     */
    private void createNewBoard(){
        grid = new BombTile[grid_size][grid_size];
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++) {
                BombTile w = new BombTile();
                grid[i][j] = w;
            }
        updateDifficulty();
        setMines();
        spaces_cleared = 0;
    }

    /**
     * place mines in grid randomly.
     */
    private void setMines() {
        Random r = new Random();
        int a = r.nextInt(grid.length);
        int b = r.nextInt(grid[0].length);
        int count = 0;
        while (count < mines) {
            // check if bomb
            while (grid[a][b].checkBomb()) {
                a = r.nextInt(grid.length);
                b = r.nextInt(grid[0].length);
            }
            grid[a][b].setIsBomb();
            System.out.println("Set Bomb at " + a + ", " + b);
            count++;
        }
    }


    void setBoxDimension() {
        System.out.println("width, height:" + width + " " + height);
        boxHeight = (height - statsDisplayGap - lineThickness) / grid.length;
        boxWidth = (width - lineThickness) / grid[0].length;
    }


    //Display the game to the screen.
    @Override
    void draw(Canvas canvas) {
        System.out.println(canvas == null);
        drawGrid(canvas);
        // Figure out what to draw
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                drawMark(canvas, grid[x][y], x, y);
            }
        }
    }

    /**
     * draw a tile given the box it should be in.
     * @param canvas
     * @param tile
     * @param x
     * @param y
     */
    private void drawMark(Canvas canvas, BombTile tile, int x, int y) {

        float startX = (boxWidth * x) + boxLineGap;
        float startY = statsDisplayGap + (boxHeight * y) + boxLineGap;
        float endX = (boxWidth * (x + 1)) - boxLineGap;
        float endY = statsDisplayGap + (boxHeight * (y + 1)) - boxLineGap;
        canvas.drawRect(startX, startY, endX, endY, tile.getColor());

    }

    /**
     * draw the board in its current state.
     * @param canvas
     */
    private void drawGrid(Canvas canvas) {
        canvas.drawLine(0, 300, width, 300, gridPaint);
        canvas.drawLine(0, height, width, height, gridPaint);
        canvas.drawLine(0, 300, 0, height, gridPaint);
        canvas.drawLine(width, 300, width, height, gridPaint);

        for (int i = 0; i < grid.length; i++) {
            //vertical grid line
            float left = boxWidth * (i + 1);
            float right = left + lineThickness;
            float top = statsDisplayGap;
            float bottom = height;
            canvas.drawRect(left, top, right, bottom, gridPaint);

            //horizontal grid line
            float left2 = 0;
            float right2 = width;
            float top2 = boxHeight * (i + 1) + statsDisplayGap;
            float bottom2 = top2 + lineThickness;

            canvas.drawRect(left2, top2, right2, bottom2, gridPaint);

        }
    }

    /**
     * change the amount of mines in the board as well as the amount of mines needed to win based off of difficulty setting.
     */
    @Override
    void updateDifficulty() {
        if (difficulty == 1) {
            mines = 5;
            win_num = 6;
        } else if (difficulty == 2) {
            mines = 8;
            win_num = 4;
        }
    }

    /**
     * receive the x and y coordinates of where the user taps, and reveals the tile of the pressed
     *  coordinate.
     * @param x
     * @param y
     */
    @Override
    void receiveInput(int x, int y) {
        int xBox = -1;
        int yBox = -1;
        // grabbing array position
        for (int i = 0; i < grid.length; i++) {
            if (x < boxWidth * (i + 1)) {
                xBox = i;
                break;
            }
        }
        for (int i = 0; i < grid[0].length; i++) {
            if (statsDisplayGap < y && y < boxHeight * (i + 1) + statsDisplayGap) {
                yBox = i;
                break;
            }
        }

        // performing everything else
        grid[xBox][yBox].setDisplayed();
        if (grid[xBox][yBox].checkBomb()) {
            endGame();
        } else {
            spaces_cleared++;
        }

        System.out.println("received input");
    }

    //End the game, return and integer representing a win(1), loss(-1) or tie(0)
    @Override
    int endGame() {
        gameEnded = true;
        return Integer.compare(spaces_cleared, win_num);
    }


    /**
     * reset the game board, resetting the grid and resetting mines.
     */
    @Override
    void reset() {
        System.out.println("RESET");
        gameEnded = false;
        createNewBoard();
    }
}
