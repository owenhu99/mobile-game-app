package com.example.game.Games.RoomEscapeGame.Entities;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.Games.RoomEscapeGame.RoomEscape;

public class Player extends Entity implements Moveable{
    Paint playerPaint;
    private boolean hasKey;
    private boolean visibility;
    private RoomEscape room;

    public Player(int xGrid, int yGrid, RoomEscape room) {
        super(xGrid, yGrid);
        this.room = room;
        playerPaint = new Paint();
        playerPaint.setColor(Color.BLUE);
        setPaint(playerPaint);
        hasKey = false;
        visibility = true;
    }

    public void move(int direction, int distance) {
        if(direction == UP)
            yGrid -= distance;
        else if(direction == DOWN)
            yGrid += distance;
        else if(direction == RIGHT)
            xGrid += distance;
        else if(direction == LEFT)
            xGrid -= distance;
    }
    public boolean getHasKey(){
        return hasKey;
    }
    public void setHasKey(boolean status){
        hasKey = status;
    }
    public boolean getVisibility(){
        return visibility;
    }
    public void setVisibility(boolean visibility){
        this.visibility = visibility;
    }

    public boolean collideCheck(int direction, Entity entity) {
        if (entity instanceof Key) {
            room.remover(entity);
            setHasKey(true);
        } else if (entity instanceof Door) {
            if (getHasKey())
                room.getManager().playerEscaped();
            else
                return true;
        } else if (entity instanceof Enemy) {
            room.getManager().caughtByEnemy();
            return true;
        } else if (entity instanceof Box) {
            room.getMovement().entityMove(direction,(Box)entity);
            return true;
        } else
            return true;

        return false;
    }

}
