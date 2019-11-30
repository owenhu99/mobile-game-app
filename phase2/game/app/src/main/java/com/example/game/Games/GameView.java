package com.example.game.Games;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.game.R;
import com.example.game.Users.User;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    public Game game;
    String gameType;
    //Canvas dimensions
    int width;
    int height;

    //Thread
    private GameTimer gameTimer;
    int secondsPlayed;

    GameFactory gameFactory;

    User playerOne;
    User playerTwo;
    boolean firstTurn;


    private Context context;

    public GameView(Context context, String game, User playerOne, User playerTwo) {
        super(context);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        firstTurn = true;
        getHolder().addCallback(this);
        setFocusable(true);
        this.gameType = game;
        gameFactory = new GameFactory();

        this.context = context;
    }

    void checkGameEnded(){
        if(game.gameEnded) {
            if(firstTurn) {
                playerOne.setPoints(game.getPoints());
                this.game = gameFactory.createGame(gameType, width, height);
            }
            else{
                playerTwo.setPoints(game.getPoints());
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
        this.game = gameFactory.createGame(gameType, width, height);
        setBitmaps();
    }

    void setBitmaps(){
        Bitmap coinBMP = BitmapFactory.decodeResource(getResources(), R.drawable.goldenpepe);
        Bitmap friendlyBMP = BitmapFactory.decodeResource(getResources(), R.drawable.feelsgoodman);
        Bitmap enemyBMP = BitmapFactory.decodeResource(getResources(), R.drawable.feelsbadman);
        game.setCoinBMP(coinBMP);
        game.setEnemyBMP(enemyBMP);
        game.setFriendlyBMP(friendlyBMP);
    }
}
