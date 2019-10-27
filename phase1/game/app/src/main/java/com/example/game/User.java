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
}
