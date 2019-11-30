package com.example.game.Games.RunnerGame.RunnerGameEntities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import java.util.Random;

public class Coin extends RunnerGameEntity {
    private static Bitmap coinBMP;

    Coin(int difficulty){
        super(difficulty);
        this.speed = (int)Math.round(0.1 * RunnerGameEntity.xBoard);
        this.xDim = (int)Math.round(0.05 * RunnerGameEntity.xBoard);
        this.yDim = (int)Math.round(0.05 * RunnerGameEntity.xBoard);
        this.y = 0;
        Random d = new Random();
        this.x = d.nextInt(RunnerGameEntity.xBoard);
        this.entityColor.setColor(Color.YELLOW);
        this.onContact = "Coin";
    }

    @Override
    public void draw(Canvas canvas) {
        RectF rectF = new RectF(x-xDim,y-yDim,x+xDim,y+yDim);
        //canvas.drawRect(x- xDim, y- yDim, x+ xDim, y+ yDim, this.entityColor);
        canvas.drawBitmap(coinBMP,null, rectF, null);

        System.out.println("coin draw at " + x + " " + y);
    }

    @Override
    public void move() {
        y += speed;
        System.out.println("coin move at " + x + " " + y);
    }

    public static void setCoinBMP(Bitmap bmp){ Coin.coinBMP = bmp; }
}
