package com.example.game.Games.MemoryGame;

public class MemoryTimer extends Thread {

    private int absoluteTime;
    private int lastTime;

    private MemoryFacade memory;

    public MemoryTimer(MemoryFacade memory){
        this.memory = memory;
        absoluteTime = 20;
        lastTime = 20;
    }

    @Override
    public void run(){
        while(true) {
            try {
                absoluteTime --;
                if(absoluteTime == 0){
                    memory.endGame();
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
