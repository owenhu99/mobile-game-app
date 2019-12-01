package com.example.game.Games.MemoryGame;

public class MemoryTimer extends Thread {

    private int absoluteTime;
    private int lastTime;

    private MemoryGame memoryGame;

    public MemoryTimer(MemoryGame game){
        this.memoryGame = game;
        absoluteTime = 30;
        lastTime = 30;
    }

    @Override
    public void run(){
        while(true) {
            try {
                absoluteTime --;
                if(absoluteTime == 0){
                    memoryGame.endGame();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            try{
                sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    int getCurrentTime(){
        return absoluteTime;
    }
    int getLastTime(){
        return lastTime;
    }
    void updateLastTime(){
        lastTime = absoluteTime;
    }
}
