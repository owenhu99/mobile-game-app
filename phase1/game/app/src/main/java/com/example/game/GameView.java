package com.example.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    public Game game;
    String gameType;
    //Canvas dimensions
    int width;
    int height;

    private GameTimer gameTimer;
    int secondsPlayed;

    int numWins = 0;
    int numLoses = 0;
    int numTies = 0;

    Paint textPaint;

    int difficulty;

    public GameView(Context context, String game, int d) {
        super(context);
        difficulty = d;
        getHolder().addCallback(this);
        setFocusable(true);
        this.gameType = game;
        if(gameType == "TTT")
            this.game = new TicTacToe(d);
        else if(gameType == "RPS")
            this.game = new RockPaperScissorsGame(d);
        else if(gameType == "BS")
            this.game = new BadMineSweeper(d);

        textPaint = new Paint();
        textPaint.setTextSize(36);
        textPaint.setColor(Color.GREEN);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    void checkGameEnded(){
        if(game.gameEnded) {
            int results = game.endGame();
            if(results == 1)
                numWins++;
            else if(results == 0)
                numTies ++;
            else if(results == -1)
                numLoses ++;
            if(gameType == "TTT")
                this.game.reset();
            else if(gameType == "RPS")
                this.game.reset();
            else if(gameType == "BS")
                this.game.reset();
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Figure out the size of a letter.
        gameTimer = new GameTimer(holder, this);
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
        canvas.drawText("wins: "+ numWins,50,50, textPaint);
        canvas.drawText("tie: "+ numTies,50,100, textPaint);
        canvas.drawText("loses: "+ numLoses,50,150, textPaint);
        canvas.drawText("Play Time: "+ secondsPlayed,800,50, textPaint);

        if (canvas != null) {
            game.draw(canvas);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        System.out.println("detected touch");
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) (event.getX());
            int y = (int) (event.getY());
            System.out.println(x+" "+y);
            game.receiveInput(x, y);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, height);
        game.setWidthHeight(width, height);
        if(game instanceof TicTacToe)
            ((TicTacToe)game).setBoxDimension();
        else if(game instanceof BadMineSweeper)
            ((BadMineSweeper)game).setBoxDimension();


    }
}
