package com.example.game.Games.RoomEscapeGame.Entities;

import java.util.Optional;

public interface Moveable {
    void move(int direction, int distance);
    int getXPos();
    int getYPos();
    boolean collideCheck(int direction, Entity entity);
}
