package com.example.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * A class that represents a game of TicTacToe.
 * */
public class TicTacToe extends Game{
    // The grid of X's and O's. 0 = nothing, 1 = X, 2 = 0
    private int[][] grid;
    // The current player whose turn it is.
    private int currentPlayer;

    private final int lineThickness = 5;
    private final int markThickness = 20;
    private int boxWidth;
    private int boxHeight;

    Paint gridPaint;
    Paint oPaint;
    Paint xPaint;

    //Creates a TicTacToe game.
    TicTacToe(int height, int width){
        super( height,  width);
        grid = new int[3][3];
        currentPlayer = 1;
        gridPaint = new Paint();
        oPaint = new Paint();
        xPaint = new Paint();
    }

    //plays the move of the current player given the place they placed their mark.
    private void playMove(int x, int y){
        if(grid[x][y] == 0) {
            grid[x][y] = currentPlayer;
            switchPlayer();
            if(checkWin(1)&&!checkFull())
                botPlay();
            else
                endGame();

        }
    }

    //plays the bot's move
    private void botPlay(){
        int randX = (int)(Math.random()*grid.length);
        int randY = (int)(Math.random()*grid.length);
        boolean done = false;
        while(!done){
            if(grid[randX][randY] == 0) {
                playMove(randX, randY);
                done = true;
            }
            else{
                randX = (int)(Math.random()*grid.length);
                randY = (int)(Math.random()*grid.length);
            }
        }

    }

    //Switches the current player
    private void switchPlayer(){
        if(currentPlayer == 1)
            currentPlayer = 2;
        else
            currentPlayer = 1;
    }

    //Checks if the <player> has won.
    private boolean checkWin(int player){
        for(int x = 0; x < grid.length; x++){
            for (int y = 0; y < grid.length; y++){
                if(grid[x][y]!= player)
                    break;
                else if(y == grid.length - 1)
                    return true;
            }
        }
        for(int y = 0; y < grid.length; y++){
            for (int x = 0; x < grid.length; x++){
                if(grid[x][y]!= player)
                    break;
                else if(x == grid.length - 1)
                    return true;
            }
        }
        for(int x = 0; x < grid.length; x++){
            if(grid[x][grid.length - 1 - x]!= player)
                break;
            else if(x == grid.length - 1)
                return true;
        }
        for(int x = 0; x < grid.length; x++){
            if(grid[x][x]!= player)
                break;
            else if(x == grid.length - 1)
                return true;
        }
        return false;
    }

    private boolean checkFull(){
        for(int x = 0; x < grid.length; x++){
            for (int y = 0; y < grid.length; y++){
                if(grid[x][y]== 0)
                    return false;
            }
        }
        return true;
    }

    @Override
    void draw(Canvas canvas){
        drawGrid(canvas);
        for(int x = 0; x < grid.length; x++){
            for (int y = 0; y < grid.length; y++){
                if(grid[x][y]!= 0)
                    drawMark(canvas, grid[x][y], x, y);
            }
        }

    }

    private void drawGrid(Canvas canvas){
        for(int i = 0; i < difficulty + 1; i++){
            //vertical grid line
            float left = boxWidth * (i+1);
            float right = left + lineThickness;
            float top = 0;
            float bottom = height;

            canvas.drawRect(left,top,right,bottom,gridPaint);

            //horizontal grid line
            float left2 = 0;
            float right2 = width;
            float top2 = boxHeight * (i+1);
            float bottom2 = top2 + lineThickness;

            canvas.drawRect(left2,top2,right2,bottom2,gridPaint);
        }
    }

    private void drawMark(Canvas canvas, int player, int x, int y){
        if (player == 1){
            float cx = (boxWidth * x) + boxWidth/2;
            float cy = (boxHeight * x) + boxHeight/2;
            canvas.drawCircle(cx,cy,Math.min(boxWidth,boxHeight)/2-markThickness*2,oPaint);
        }
        else if (player == 2){
            float startX = (boxWidth * x) + markThickness;
            float startY = (boxHeight * y) + markThickness;
            float endX = startX + boxWidth - markThickness * 2;
            float endY = startY + boxHeight - markThickness;
            canvas.drawLine(startX, startY, endX, endY, xPaint);

            startX = (boxWidth * (x+1)) + markThickness;
            startY = (boxHeight * y) + markThickness;
            endX = startX + boxWidth - markThickness * 2;
            endY = startY + boxHeight - markThickness;
            canvas.drawLine(startX, startY, endX, endY, xPaint);
        }
    }

    @Override
    void updateDifficulty(){
        if(difficulty == 1)
            grid = new int[3][3];
        else if(difficulty == 2)
            grid = new int[4][4];
    }

    @Override
    void receiveInput(int x, int y){}

    @Override
    int endGame(){
        if(checkWin(1))
            return 1;
        else if(checkWin(2))
            return 2;
        else
            return 0;
    }

    private void setBoxDimension(){
        if(difficulty == 1){
            boxHeight = (width - lineThickness) / 3;
            boxWidth = (width - lineThickness) / 3;
        }
        else if(difficulty == 2){
            boxHeight = (width - lineThickness) / 4;
            boxWidth = (width - lineThickness) / 4;
        }
    }
}
