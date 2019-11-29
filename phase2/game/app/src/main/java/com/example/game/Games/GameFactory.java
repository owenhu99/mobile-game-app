package com.example.game.Games;

import com.example.game.Games.RoomEscapeGame.RoomEscape;

public class GameFactory {
    public Game createGame(String name, int width, int height){
        if(name == "Room")
            return new RoomEscape(width, height);
        return null;
    }
}
