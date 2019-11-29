package com.example.game.Games.RunnerGame.RunnerGameEntities;

import android.graphics.Canvas;

import com.example.game.Games.RunnerGame.RunnerGameEntities.RunnerGameEntity;

public class Wall extends RunnerGameEntity {

    Wall(int difficulty){
        this.speed = difficulty;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void move() {
        this.y -= speed;
    }


}
