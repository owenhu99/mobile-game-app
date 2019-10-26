package com.example.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

//A thread to continuously update the time played.
public class GameTimer extends Thread{
    private GameView gameView;
    private boolean isPlaying;
    private SurfaceHolder surfaceHolder;
    Canvas canvas;

    public GameTimer(SurfaceHolder holder, GameView view){
        this.gameView = view;
        this.surfaceHolder = holder;
    }

    @Override
    public void run(){
        while(isPlaying) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                gameView.secondsPlayed ++;
                gameView.draw(canvas);
                sleep(1000);
            } catch (Exception e) {
            }
        }
    }
    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
}
