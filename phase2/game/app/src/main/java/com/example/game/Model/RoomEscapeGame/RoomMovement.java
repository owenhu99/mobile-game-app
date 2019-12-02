package com.example.game.Model.RoomEscapeGame;

import com.example.game.Model.RoomEscapeGame.Entities.*;

import java.util.ArrayList;
import java.util.Optional;

public class RoomMovement {
    final int UP = 1;
    final int DOWN = 2;
    final int RIGHT = 3;
    final int LEFT = 4;
    private Player player;
    private ArrayList<Entity> entities;
    private RoomEscape room;

    RoomMovement(ArrayList<Entity> entities, Player player, RoomEscape room){
        this.entities = entities;
        this.player = player;
        this.room = room;
    }
    public void entityMove(int direction, Moveable entity){
        if(direction == UP) {
            if (!collision(UP, 1, entity))
                entity.move(UP, 1);
        }
        else if(direction == DOWN) {
            if (!collision(DOWN, 1, entity))
                entity.move(DOWN, 1);
        }
        else if(direction == RIGHT) {
            if (!collision(RIGHT, 1, entity))
                entity.move(RIGHT, 1);
        }
        else if(direction == LEFT) {
            if (!collision(LEFT, 1, entity))
                entity.move(LEFT, 1);
        }
    }

    public boolean collision(int direction, int distance, Moveable mover) {
        System.out.println("called");
        Optional<Entity> entity = null;
        if(direction == UP){
            for(int i = 1; i <= distance; i++) {
                entity = getEntity(mover.getXPos(), mover.getYPos() - i);
                if(entity != null) {
                    if (mover.collideCheck(direction, entity.get()))
                        return true;
                }
            }
        }
        else if(direction == DOWN){
            for(int i = 1; i <= distance; i++) {
                entity = getEntity(mover.getXPos(), mover.getYPos() + i);
                if(entity != null) {
                    if (mover.collideCheck(direction, entity.get()))
                        return true;
                }
            }
        }
        else if(direction == RIGHT){
            for(int i = 1; i <= distance; i++) {
                entity = getEntity(mover.getXPos() + i, mover.getYPos());
                if(entity != null) {
                    if (mover.collideCheck(direction, entity.get()))
                        return true;
                }
            }
        }
        else if(direction == LEFT){
            for(int i = 1; i <= distance; i++) {
                entity = getEntity(mover.getXPos() - i, mover.getYPos());
                if(entity != null) {
                    if (mover.collideCheck(direction, entity.get()))
                        return true;
                }
            }
        }
        return false;
    }
    private Optional<Entity> getEntity(int xGrid, int yGrid){
        for(Entity entity: entities){
            if(entity.getXPos() == xGrid && entity.getYPos() == yGrid)
                return Optional.of(entity);
        }
        return null;
    }
}
