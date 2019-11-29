package com.example.game.Games.RunnerGame.RunnerGameEntities;

import android.graphics.Canvas;
import android.graphics.Color;


public class Player extends RunnerGameEntity {

    public Player(int d){
        // find out if int x and y are acceptable, pretty sure they are.
        super(d);
        this.speed = 50;
        this.x = (int)(Math.round(0.5 * x_board));
        this.y = (int)(Math.round(0.8 * y_board));
        this.speed = (int)Math.round(0.1 * x_board);
        this.x_dim = (int)Math.round(0.05 * x_board);
        this.y_dim = (int)Math.round(0.05 * x_board);
        this.entity_color.setColor(Color.BLUE);
    }

    @Override
    public void draw(Canvas canvas) {
        //figure out bitmap deal
        canvas.drawRect(x-x_dim, y-y_dim, x+x_dim, y+y_dim, this.entity_color);
        System.out.println("player draw at " + x + " " + y);
    }

    @Override
    public void move() {
        y++;
        System.out.println("player at " + x + " " + y);

    }

    public void move(int direction){
        switch (direction){
            case (1):
                this.x -= speed;
                break;
            case (2):
                this.x += speed;
                break;
            case(3):
                this.y += speed;
                break;
            case(4):
                this.y -= speed;
                break;
        }
        //detect if out of bounds, move to max if so.
    }
}
