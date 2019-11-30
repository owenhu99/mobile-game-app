package com.example.game.Games.RunnerGame.RunnerGameEntities;

/**
 * move in one direction and then another for a specified amount of time.
 */
public class Wigglers extends Enemies {
    private int counter = 0;


    Wigglers(int d) {
        super(d);
    }



    @Override
    public void move() {
        x += speed;
        y += (int)speed/2;
        counter++;
        if(counter % 5 == 0){
            x = -x;
        }
    }
}
