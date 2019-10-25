package com.example.game;

/**
 * A class that represents a game of rock, paper, scissors
 * */
public class RockPaperScissors extends Game {



    /**
     *  Creates a TicTacToe game.
     */
    RockPaperScissors(){

    }


    private void randomBot(){
        int action = (int)(Math.random() * 3) + 1;
        switch (action){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    private void smartBot(){

    }


    private void checkWin(){

    }


    @Override
    void display(){}

    @Override
    void updateDifficulty(){
    }

    @Override
    void receiveInput(int x, int y){
    }

    @Override
    int endGame(){return 0;}
}
