package com.example.game.Games;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

//A thread to continuously update the time played.
public class GameTimer extends Thread{
    private GameView gameView;
    private boolean isPlaying;
    private final SurfaceHolder surfaceHolder;

    GameTimer(SurfaceHolder holder, GameView view){
        this.gameView = view;
        this.surfaceHolder = holder;
    }

    @Override
    public void run(){
        Canvas canvas;
        int counter = 0;
        while(isPlaying) {
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                System.out.println(canvas == null);
                synchronized (surfaceHolder) {
                    if(counter == 10) {
                        this.gameView.secondsPlayed++;
                        counter = 0;
                    }
                    this.gameView.draw(canvas);
                    this.gameView.checkGameEnded();
                    counter ++;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            try{
                sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
}
