package com.example.game.Games.RunnerGame.RunnerGameEntities;

public class LongBoi extends Enemies {


    LongBoi(int d){
        super(d);
        this.y = 0;

        this.xDim = (int)(Math.round(0.01*xBoard));
        this.yDim = (int)(Math.round(0.3*yBoard));
    }

    @Override
    public void move() {
        y--;
    }
}
