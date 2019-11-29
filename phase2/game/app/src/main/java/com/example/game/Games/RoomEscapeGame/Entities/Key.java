package com.example.game.Games.RoomEscapeGame.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Key extends Entity {
    Paint keyPaint;

    public Key(int xGrid, int yGrid) {
        super(xGrid, yGrid);
        keyPaint = new Paint();
        keyPaint.setColor(Color.YELLOW);
        setPaint(keyPaint);
    }

    public void collected(){

    }
}
