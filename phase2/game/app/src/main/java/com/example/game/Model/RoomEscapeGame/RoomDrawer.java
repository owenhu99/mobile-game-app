package com.example.game.Model.RoomEscapeGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.game.Model.RoomEscapeGame.Entities.Entity;
import com.example.game.Model.RoomEscapeGame.Entities.Player;
import com.example.game.R;

import java.util.ArrayList;

public class RoomDrawer {
    private ArrayList<Entity> entities;
    private Player player;
    private int drawWidth;
    private int drawHeight;
    private final int dimension = 100;
    private final int offsetX = 50;
    private RoomEscape room;
    private Boolean notSet;
    Paint visibleArea;
    Paint darkArea;
    Paint controlButton;
    Paint textPaint;
    Bitmap pepeBMP;
    RectF pepe;


    public RoomDrawer(int drawWidth, int drawHeight, RoomEscape room){
        this.entities = room.manager.getEntities();
        this.player = room.manager.getPlayer();
        this.room = room;
        this.drawWidth = drawWidth;
        this.drawHeight = drawHeight;
        notSet = true;
        visibleArea = new Paint();
        darkArea = new Paint();
        controlButton = new Paint();
        textPaint = new Paint();
        visibleArea.setColor(Color.LTGRAY);
        darkArea.setColor(Color.BLACK);
        controlButton.setColor(Color.WHITE);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(30);
    }
    void controlsDrawer(Canvas canvas){
        controlButton.setColor(Color.WHITE);
        canvas.drawRect(drawWidth/2 - 50, drawHeight+25,
                drawWidth/2 + 50, drawHeight+125, controlButton);
        canvas.drawRect(drawWidth/2 - 50, drawHeight+125,
                drawWidth/2 + 50, drawHeight+225, controlButton);
        canvas.drawRect(drawWidth/2 - 150, drawHeight+125,
                drawWidth/2 - 50, drawHeight+225, controlButton);
        canvas.drawRect(drawWidth/2 + 50, drawHeight+125,
                drawWidth/2 + 150, drawHeight+225, controlButton);
        controlButton.setColor(Color.BLACK);
        controlButton.setStrokeWidth(10);
        //up arrow
        canvas.drawLine(drawWidth/2, drawHeight+115,
                drawWidth/2, drawHeight+30, controlButton);
        canvas.drawLine(drawWidth/2 -30, drawHeight+75,
                drawWidth/2, drawHeight+35, controlButton);
        canvas.drawLine(drawWidth/2 +30, drawHeight+75,
                drawWidth/2, drawHeight+35, controlButton);
        //down arrow
        canvas.drawLine(drawWidth/2, drawHeight+135,
                drawWidth/2, drawHeight+215, controlButton);
        canvas.drawLine(drawWidth/2 -30, drawHeight+175,
                drawWidth/2, drawHeight+210, controlButton);
        canvas.drawLine(drawWidth/2 +30, drawHeight+175,
                drawWidth/2, drawHeight+210, controlButton);
        //right arrow
        canvas.drawLine(drawWidth/2 + 140, drawHeight+175,
                drawWidth/2 + 60, drawHeight+175, controlButton);
        canvas.drawLine(drawWidth/2 +100, drawHeight+135,
                drawWidth/2+ 140, drawHeight+175, controlButton);
        canvas.drawLine(drawWidth/2 +100, drawHeight+215,
                drawWidth/2+ 140, drawHeight+175, controlButton);
        //left arrow
        canvas.drawLine(drawWidth/2-140, drawHeight+175,
                drawWidth/2 - 60, drawHeight+175, controlButton);
        canvas.drawLine(drawWidth/2 -100, drawHeight+135,
                drawWidth/2-140, drawHeight+175, controlButton);
        canvas.drawLine(drawWidth/2 -100, drawHeight+215,
                drawWidth/2-140, drawHeight+175, controlButton);
    }

    void drawInfo(Canvas canvas){
        canvas.drawText(room.countDownTimer.getCurrentTime() + " time left.",0,12, 100, drawHeight + 200, textPaint);
        canvas.drawText(room.getManager().getPoints() + " points accumulated",0,20,drawWidth/2 + 200, drawHeight + 100, textPaint);
        canvas.drawText(room.getManager().getRoomsEscaped() + " rooms escaped.",0,15,drawWidth/2 + 200, drawHeight + 200, textPaint);

    }

    void drawRoom(Canvas canvas){
        if(notSet){
            pepeBMP = BitmapFactory.decodeResource(room.getContext().getResources(), R.drawable.pepefeelsgoodman);
            pepe = new RectF((player.getXPos() - 1) * dimension + offsetX, (player.getYPos() - 1) * dimension,
                    (player.getXPos() + 2) * dimension + offsetX, (player.getYPos() + 2) * dimension);
            notSet = false;
        }
        controlsDrawer(canvas);
        drawInfo(canvas);
        if(!player.getVisibility()) {
            canvas.drawRect(offsetX, 0, drawWidth+offsetX, drawHeight, darkArea);

            if(room.getCurrentSkin() == "pepe"){
                canvas.drawBitmap(pepeBMP,null, pepe, null);
            }
            else {
                canvas.drawRect((player.getXPos() - 1) * dimension + offsetX, (player.getYPos() - 1) * dimension,
                        (player.getXPos() + 2) * dimension + offsetX, (player.getYPos() + 2) * dimension,
                        visibleArea);
            }
            for(Entity entity: entities) {
                if(entity.getXPos() >= player.getXPos()-1 &&
                        entity.getXPos() <= player.getXPos()+1) {
                    if (entity.getYPos() >= player.getYPos() - 1 &&
                            entity.getYPos() <= player.getYPos() + 1)
                        entity.draw(canvas);
                }
            }
        }
        else{
            canvas.drawRect(0, 0, drawWidth, drawHeight, visibleArea);
            for(Entity entity: entities) {
                entity.draw(canvas);
            }
        }
    }
    void setPlayer(Player player){
        this.player = player;
    }
}
