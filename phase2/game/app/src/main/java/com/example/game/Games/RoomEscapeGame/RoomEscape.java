package com.example.game.Games.RoomEscapeGame;
import android.graphics.Canvas;

import com.example.game.Games.Game;
import com.example.game.Games.RoomEscapeGame.Entities.*;

import java.util.ArrayList;

public class RoomEscape extends Game {

    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> spawn = new ArrayList<>();
    private ArrayList<Entity> remove = new ArrayList<>();

    private int width;
    private int height;

    RoomManager manager;
    RoomDrawer drawer;
    RoomVisibility visibility;
    RoomMovement movement;
    EscapeCountDownTimer countDownTimer;
    StandingTimer standingTimer;

    private int controlSpaceOffset = 300;

    public RoomEscape(int width, int height) {
        super(width, height);
        this.width = width;
        this.height = height - controlSpaceOffset;
        this.manager = new RoomManager(entities, width, this.height, this);
        manager.populateRoomRandom();
        this.drawer = new RoomDrawer(width, this.height, this);
        this.standingTimer = new StandingTimer();
        this.visibility = new RoomVisibility(manager.getPlayer(), standingTimer);
        this.countDownTimer = new EscapeCountDownTimer(this);
        this.movement = new RoomMovement(entities, manager.getPlayer(), this);

        standingTimer.start();
        countDownTimer.start();
    }

    @Override
    protected void draw(Canvas canvas){
        for(Entity entity: remove)
            entities.remove(entity);
        for(Entity entity: spawn)
            entities.add(entity);
        visibility.checkVisibility();
        drawer.drawRoom(canvas);
        remove.clear();
        spawn.clear();
    }
    //Get the x and y position on the screen where the user pressed.
    protected void receiveInput(int x, int y){
        if(y > height+25 && y < height+125){
            if(x > width/2 - 50 && x < width/2 + 50){
                visibility.playerMoving();
                movement.entityMove(1, manager.getPlayer());
            }
        }
        else if(y > height+125 && y < height+225){
            if(x > width/2 - 50 && x < width/2 + 50) {
                visibility.playerMoving();
                movement.entityMove(2, manager.getPlayer());
            }
            else if(x > width/2 - 150 && x < width/2 - 50) {
                visibility.playerMoving();
                movement.entityMove(4, manager.getPlayer());
            }
            else if(x > width/2 + 50 && x < width/2 + 150) {
                visibility.playerMoving();
                movement.entityMove(3, manager.getPlayer());
            }
        }
    }
    //End the game, return and integer representing a win(1), loss(-1) or tie(0)
    protected int endGame(){
        return 0;
    }
    protected void reset(){
    }

    public RoomMovement getMovement(){
        return movement;
    }
    public RoomManager getManager(){
        return manager;
    }
    public RoomDrawer getDrawer(){return drawer;}

    public void spawner(Entity entity){
        spawn.add(entity);
    }
    public void remover(Entity entity){
        remove.add(entity);
    }
}


