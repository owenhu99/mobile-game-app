package com.example.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    public Game game;
    //Canvas dimensions
    int width;
    int height;
    Canvas canvas;

    private GameTimer gameTimer;
    int secondsPlayed;

    public GameView(Context context, String game) {
        super(context);
        getHolder().addCallback(this);
        gameTimer = new GameTimer(getHolder(), this);
        if(game == "TTT")
            this.game = new TicTacToe(height, width);
        else if(game == "RPS")
            this.game = new RockPaperScissorsGame(height, width);
        setFocusable(true);

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Figure out the size of a letter.
        canvas = holder.lockCanvas();
        Paint paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        gameTimer.setPlaying(true);
        gameTimer.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                gameTimer.setPlaying(false);
                gameTimer.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            game.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) (event.getX());
            int y = (int) (event.getY());
            game.receiveInput(x, y);
        }
        draw(canvas);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
