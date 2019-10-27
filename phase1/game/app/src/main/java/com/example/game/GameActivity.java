package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    int difficulty = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void TicTacToeStart(View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this, "TTT", difficulty));
    }

    public void RockPaperScissorStart(View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this,"RPS", difficulty));
    }

    public void BombStart(View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this,"BS", difficulty));
    }

    public void easyMode(View view) {
        difficulty = 1;
    }
    public void hardMode(View view) {
        difficulty = 2;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int frmt, int w, int h) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}

}
