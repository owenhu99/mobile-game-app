package com.example.game.Users;

public interface Observable {
    void register(Observer o);

    void unregister(Observer o);

    void notifyObserver();
}
