package com.example.game;

public class User {
  private String userName;
  private String firstName;
  private String lastName;
  private double totalTime;
  private int totalWins;
  private int gold;


  public User(String userName, String firstName, String lastName) {
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.totalTime = 0;
    this.totalWins = 0;
    this.gold = 0;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setTotalTime(double totalTime) {
    this.totalTime = totalTime;
  }

  public double getTotalTime() {
    return totalTime;
  }

  public void setTotalWins(int wins){this.totalWins = wins;}

  public int getTotalWins(){return totalWins;}

  public void setGold(int gold){this.gold = gold;}

  public int getGold(){return gold;}

  public void clearStats() {
    this.totalTime = 0;
    this.totalWins = 0;
    this.gold = 0;
  }

  public void update() {
    UserUpdateHelper.update(this);
  }

  // increases the user's total wins and increases gold equal to 10 gold per win, increase total time played

  public void updateUserStats(int wins, double time) {
    this.totalWins += wins;
    this.gold += wins * 10;
    this.totalTime += time;
  }

  // decreases the user's gold when they buy something
  public void decreaseGold(int gold) {this.gold -= gold;}

}
