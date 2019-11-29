package com.example.game.Games.RunnerGame.RunnerGameEntities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

public class Coin extends RunnerGameEntity {

    public Coin(int difficulty){
        super(difficulty);
        this.speed = (int)Math.round(0.1 * RunnerGameEntity.x_board);
        this.x_dim = (int)Math.round(0.05 * RunnerGameEntity.x_board);
        this.y_dim = (int)Math.round(0.05 * RunnerGameEntity.x_board);
        this.y = 0;
        Random d = new Random();
        this.x = d.nextInt(RunnerGameEntity.x_board);
        this.entity_color.setColor(Color.YELLOW);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(x-x_dim, y-y_dim, x+x_dim, y+y_dim, this.entity_color);

        System.out.println("coin draw at " + x + " " + y);
    }

    @Override
    public void move() {
        y += speed;
        System.out.println("coin move at " + x + " " + y);
    }
}
