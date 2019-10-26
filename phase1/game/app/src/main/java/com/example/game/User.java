package com.example.game;

public class User {
  private String userName;
  private String firstName;
  private String lastName;
  private int lives;
  private double averageTime;
  private double totalTime;

  public User(String userName, String firstName, String lastName) {
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.lives = 3;
    this.averageTime = 0;
    this.totalTime = 0;
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

  public void setAverageTime(double averageTime) {
    this.averageTime = averageTime;
  }

  public double getAverageTime() {
    return averageTime;
  }

  public void setLives(int lives) {
    this.lives = lives;
  }

  public int getLives() {
    return lives;
  }

  public void setTotalTime(double totalTime) {
    this.totalTime = totalTime;
  }

  public double getTotalTime() {
    return totalTime;
  }

  public void clearStats() {
    this.lives = 3;
    this.averageTime = 0;
    this.totalTime = 0;
  }
}
