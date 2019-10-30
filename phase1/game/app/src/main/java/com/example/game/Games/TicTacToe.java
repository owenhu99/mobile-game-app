package com.example.game.Games;

import android.graphics.Canvas;
import android.graphics.Color;
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
    private final int boxLineGap = 20;
    private final int statsDisplayGap = 300;
    private int boxWidth;
    private int boxHeight;

    Paint gridPaint;
    Paint oPaint;
    Paint xPaint;
    Paint paintText;


    //Creates a TicTacToe game.
    TicTacToe(int d){
        super(d);
        if(difficulty == 1)
            grid = new int[3][3];
        else
            grid = new int[4][4];
        currentPlayer = 1;
        gridPaint = new Paint();
        oPaint = new Paint();
        gridPaint.setColor(Color.WHITE);
        oPaint.setColor(Color.BLUE);
        oPaint.setStyle(Paint.Style.STROKE);
        oPaint.setStrokeWidth(20);
        xPaint = new Paint(oPaint);
        xPaint.setColor(Color.RED);
    }

    //plays the move of the current player given the place they placed their mark.
    private void playMove(int x, int y){
        if(grid[x][y] == 0) {
            grid[x][y] = currentPlayer;
            switchPlayer();
            if(checkWin(1)||checkWin(2)||checkFull())
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
        System.out.println(canvas == null);
        printTextBoard();
        drawGrid(canvas);
        for(int x = 0; x < grid.length; x++){
            for (int y = 0; y < grid.length; y++){
                if(grid[x][y]!= 0)
                    drawMark(canvas, grid[x][y], x, y);
            }
        }

    }

    private void printTextBoard(){
        for(int x = 0; x < grid.length; x++){
            for (int y = 0; y < grid.length; y++){
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    private void drawGrid(Canvas canvas){
        canvas.drawLine(0,300,width,300,gridPaint);
        canvas.drawLine(0,height,width,height,gridPaint);
        canvas.drawLine(0,300,0,height,gridPaint);
        canvas.drawLine(width,300,width,height,gridPaint);


        for(int i = 0; i < difficulty + 1; i++){
            //vertical grid line
            float left = boxWidth * (i+1);
            float right = left + lineThickness;
            float top = statsDisplayGap;
            float bottom = height;
            canvas.drawRect(left,top,right,bottom,gridPaint);

            //horizontal grid line
            float left2 = 0;
            float right2 = width;
            float top2 = boxHeight * (i+1) +statsDisplayGap;
            float bottom2 = top2 + lineThickness;

            canvas.drawRect(left2,top2,right2,bottom2,gridPaint);

        }
    }

    private void drawMark(Canvas canvas, int player, int x, int y){
        if (player == 1){
            float xCircle = (boxWidth * x) + boxWidth/2;
            float yCircle = statsDisplayGap+ (boxHeight * y) + boxHeight/2;
            canvas.drawCircle(xCircle,yCircle,Math.min(boxWidth,boxHeight)/2-boxLineGap*2,oPaint);
        }
        else if (player == 2){
            float startX = (boxWidth * x) + boxLineGap;
            float startY = statsDisplayGap+ (boxHeight * y) + boxLineGap;
            float endX = (boxWidth * (x+1)) - boxLineGap;
            float endY = statsDisplayGap+ (boxHeight * (y+1)) - boxLineGap;
            canvas.drawLine(startX, startY, endX, endY, xPaint);

            startX = (boxWidth * (x+1)) - boxLineGap;
            startY = statsDisplayGap+ (boxHeight * y) + boxLineGap;
            endX = (boxWidth * x) + boxLineGap;
            endY = statsDisplayGap+(boxHeight * (y+1)) - boxLineGap;
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
    void receiveInput(int x, int y){
        System.out.println("W,H:"+boxWidth +" "+ boxHeight);
        int xBox = -1;
        int yBox = -1;
        for(int i =1; i<=difficulty +2;i++){
            if(x<boxWidth*i){
                xBox = i-1;
                break;
            }
        }
        for(int i =1; i<=difficulty +2;i++){
            if(statsDisplayGap<y&&y<boxHeight*i+statsDisplayGap){
                yBox = i-1;
                break;
            }
        }
        if(!gameEnded&& xBox != -1 &&yBox != -1)
            playMove(xBox,yBox);
        if(currentPlayer == 2 &&!gameEnded)
            botPlay();
    }

    @Override
    int endGame(){
        System.out.println("GAME END");
        gameEnded = true;
        if(checkWin(1))
            return 1;
        else if(checkWin(2))
            return -1;
        else
            return 0;
    }
    @Override
    void reset(){
        if(difficulty == 1)
            grid = new int[3][3];
        else
            grid = new int[4][4];
        gameEnded = false;
        currentPlayer = 1;
    }

    void setBoxDimension(){
        System.out.println("width, height:"+width+" "+height);

        if(difficulty == 1){
            boxHeight = (height-statsDisplayGap - lineThickness) / 3;
            boxWidth = (width - lineThickness) / 3;
        }
        else if(difficulty == 2){
            boxHeight = (height-statsDisplayGap - lineThickness) / 4;
            boxWidth = (width - lineThickness) / 4;
        }

    }
}
