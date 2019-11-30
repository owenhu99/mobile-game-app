package com.example.game.Games.RunnerGame.RunnerGameEntities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;


public class Player extends RunnerGameEntity {
    private static Bitmap playerBMP;

    public Player(int d){
        // find out if int x and y are acceptable, pretty sure they are.
        super(d);
        this.speed = (int)(Math.round(0.025 * xBoard));
        this.x = (int)(Math.round(0.5 * xBoard));
        this.y = (int)(Math.round(0.8 * yBoard));
        this.speed = (int)Math.round(0.1 * xBoard);
        // player occupies 5% of the board.
        this.xDim = (int)Math.round(0.025 * xBoard);
        this.yDim = (int)Math.round(0.025 * xBoard);
        this.entityColor.setColor(Color.BLUE);
    }

    @Override
    public void draw(Canvas canvas) {
        //figure out bitmap deal
        //canvas.drawRect(x- xDim, y- yDim, x+ xDim, y+ yDim, this.entityColor);
        //System.out.println("player draw at " + x + " " + y);

        RectF rectF = new RectF(x-xDim,y-yDim,x+xDim,y+yDim);
        //canvas.drawRect(x- xDim, y- yDim, x+ xDim, y+ yDim, this.entityColor);
        canvas.drawBitmap(playerBMP,null, rectF, null);
    }

    @Override
    public void move() { }

    public void move(int direction){
        switch (direction){
            case (1):
                this.x -= speed;
                if(x < 0){
                    x = xDim;
                }
                break;
            case (2):
                this.x += speed;
                if(x > xBoard){
                    x = xBoard-xDim;
                }
                break;
            case(3):
                this.y += speed;
                if(y > yBoard){
                    y = yBoard-yDim;
                }
                break;
            case(4):
                this.y -= speed;
                if(y < 0){
                    y = yDim;
                }
                break;
        }
        //detect if out of bounds, move to max if so.
    }
    public static void setPlayerBMP(Bitmap bmp){ Player.playerBMP = bmp; }
}
