package com.example.game.Games.RunnerGame.RunnerGameEntities;

import android.graphics.Canvas;

import com.example.game.Games.RunnerGame.RunnerGameEntities.RunnerGameEntity;

public class Wall extends Enemies {

    /**
     * Walls take up 40% of the screen. they either spawn on left side or right side, and are as tall
     * as the player character.
     * @param difficulty
     */
    Wall(int difficulty){
        super(difficulty);
        int w = d.nextInt(2);
        if(w == 0){
            this.x = xDim;
        }else {
            this.x = xBoard-xDim;
        }


    }

    @Override
    public void move() { this.y -= (int)speed/2; }


}
