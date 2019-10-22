package com.example.game;

/**
 * A class that represents a game.
 * */
public abstract class Game {
    //represents the difficulty of the game. 1 = Easy, 2 = Hard
    int difficulty;

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        updateDifficulty();
    }

    //Display the game to the screen.
    abstract void display();
    //update the difficulty
    abstract void updateDifficulty();
    //Get the x and y position on the screen where the user pressed.
    abstract void receiveInput(int x, int y);
    //End the game, return and integer representing a win(1), loss(-1) or tie(0)
    abstract int endGame();
}
