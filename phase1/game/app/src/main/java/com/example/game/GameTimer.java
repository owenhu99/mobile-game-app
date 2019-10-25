package com.example.game;

//A thread to continuously update the time played.
public class GameTimer extends Thread{
    private Game game;
    public GameTimer(Game game){
        this.game = game;
    }
    @Override
    public void run(){
        while(true) {
            try {
                if(!game.paused)
                    game.secondsPlayed ++;
                sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}
