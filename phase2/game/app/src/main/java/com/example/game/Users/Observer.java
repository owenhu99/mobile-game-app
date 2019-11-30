package com.example.game.Users;

public interface Observer {
    void update(String username, int currency, double playtime, int points, int wins, String skin, String inventory);
}
