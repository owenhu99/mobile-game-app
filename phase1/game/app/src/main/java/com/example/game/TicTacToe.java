package com.example.game;

import android.content.Context;
import android.graphics.Canvas;

/**
 * A class that represents a game of TicTacToe.
 * */
public class TicTacToe extends Game{
    // The grid of X's and O's. 0 = nothing, 1 = X, 2 = 0
    private int[][] grid;
    // The current player whose turn it is.
    private int currentPlayer;



    //Creates a TicTacToe game.
    TicTacToe(Context context){
        super(context);
        grid = new int[3][3];
        currentPlayer = 1;
    }

    //plays the move of the current player given the place they placed their mark.
    private void playMove(int x, int y){
        if(grid[x][y] == 0) {
            grid[x][y] = currentPlayer;
            switchPlayer();
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

    @Override
    void display(Canvas canvas){
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
    int endGame(){return 0;}
}
