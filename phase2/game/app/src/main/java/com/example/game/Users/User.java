package com.example.game.Users;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

public class User implements Observable {
    private String userName;
    private double playTime;
    private int currency;
    private int points;
    private String skin;
    private ArrayList<String> inventory = new ArrayList<>();
    private ArrayList<Observer> observers = new ArrayList<>();
    private DatabaseHelper dbHelper;

    public User(String userName, DatabaseHelper dbHelper) {
        this.userName = userName;
        this.playTime = 0;
        this.currency = 0;
        this.points = 0;
        this.currency = 0;
        this.skin = "default";
        this.dbHelper = dbHelper;
        observers.add(dbHelper);
    }

    public void register(Observer o) {
        observers.add(o);
    }

    public void unregister(Observer o) {
        observers.remove(o);
    }

    public void notifyObserver() {
        for (Observer o : observers) {
            o.update(userName, currency, playTime, points, skin, String.join(",", inventory));
        }
    }

    /**
     * Updates all stats when a game finishes
     */
    public void updateStats(int wins, double time) {
        this.points += wins;
        this.currency += wins * 10;
        this.playTime += time;
        notifyObserver();
    }

    public void loadStats(double playTime, int currency, int points, String skin, ArrayList<String> inventory) {
        this.playTime = playTime;
        this.currency = currency;
        this.points = points;
        this.skin = skin;
        this.inventory = inventory;
    }

    public void setSkin(String skin) {
        this.skin = skin;
        notifyObserver();
    }

    public String getSkin() {
        return skin;
    }

    public void addToInventory(String skin) {
        this.inventory.add(skin);
    }

    public void removeFromInventory(String skin) {
        this.inventory.remove(skin);
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setCurrency(int currency) {
        this.currency += currency;
        notifyObserver();
    }

    public void setPlayTime(double time) {
        this.playTime += time;
        notifyObserver();
    }

    public void setPoints(int points) {
        this.points += points;
        notifyObserver();
    }

    /**
     * Print the current stats for displaying at the game menu
     */
    public String printStats() {
        return "Current User: " + userName + "\nPlaytime: " + playTime + "\nPoints: " + points + "\nCurrency: " + currency + "\nSkin: " + skin;
    }
}
