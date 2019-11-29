package com.example.game.Games.RunnerGame.RunnerGameEntities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

public class Coin extends RunnerGameEntity {

    public Coin(){
        super();
        this.speed = (int)Math.round(0.1 * RunnerGameEntity.x_board);
        this.x_dim = (int)Math.round(0.05 * RunnerGameEntity.x_board);
        this.y_dim = (int)Math.round(0.05 * RunnerGameEntity.x_board);
        this.y = 0;
        this.entity_color = new Paint(Color.YELLOW);
        Random d = new Random();
        this.x = d.nextInt(RunnerGameEntity.x_board);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint mlem = new Paint();
        mlem.setColor(Color.YELLOW);
        Rect rect = new Rect(x-x_dim, y-y_dim, 2*x_dim, 2*y_dim);
        canvas.drawRect(rect, mlem);
        System.out.println("coindims\nxdim: " + x_dim + "\n ydim: " + y_dim);
        System.out.println("coin at " + x +", " + y);
    }

    @Override
    public void move() {
        y--;
    }
}
