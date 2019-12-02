package com.example.game.Games;

import android.content.Context;
import android.content.Intent;

import android.graphics.Canvas;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.game.Activities.GameActivity;
import com.example.game.Activities.GameResultsActivity;

import com.example.game.Users.User;


public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    public Game game;
    String gameType;
    //Canvas dimensions
    int width;
    int height;

    //Threads
    private GameTimer gameTimer;
    int secondsPlayed;

    User playerOne;
    User playerTwo;
    boolean firstTurn;


    GameFactory gameFactory;

    private Context context;

    public GameView(Context context, String game, User playerOne, User playerTwo) {
        super(context);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.firstTurn = true;
        getHolder().addCallback(this);
        setFocusable(true);
        this.gameType = game;
        gameFactory = new GameFactory();

        this.context = context;

    }

    void checkGameEnded(){
        if(game.gameEnded) {
            if(firstTurn) {
                playerOne.updateLastPoints(game.getPoints());
                playerOne.updatePlayTime(game.secondsPlayed);
                this.game = gameFactory.createGame(gameType, playerTwo.getSkin(),  width, height);
                this.game.setContext(context);
                firstTurn = false;
            }
            else{
                gameTimer.setPlaying(false);
                playerTwo.updateLastPoints(game.getPoints());
                playerTwo.updatePlayTime(game.secondsPlayed);
                Intent intent = new Intent(context, GameResultsActivity.class);
                intent.putExtra("user1", playerOne.getUserName());
                intent.putExtra("user2", playerTwo.getUserName());
                getContext().startActivity(intent);

            }
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
        game.draw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
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


        this.game = gameFactory.createGame(gameType, playerOne.getSkin(), width, height);
        this.game.setContext(context);

    }

    void updateSecondsPlayed(){
        this.secondsPlayed++;
        this.game.secondsPlayed++;
    }

}
