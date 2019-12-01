package com.example.game.Games;

import com.example.game.Games.MemoryGame.MemoryGame;
import com.example.game.Games.RoomEscapeGame.RoomEscape;
import com.example.game.Games.RunnerGame.RunnerGame;

public class GameFactory {

    public Game createGame(String name, int width, int height){
        if(name == "Room"){
            return new RoomEscape(width, height);
        } else if (name == "Runner"){
            return new RunnerGame(width, height);
        } else if (name == "Memory"){
            return new MemoryGame(width, height);
        }
        return null;
    }
}
