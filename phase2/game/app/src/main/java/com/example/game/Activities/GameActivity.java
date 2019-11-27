package com.example.game.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.game.Games.GameView;
import com.example.game.R;
import com.example.game.Users.User;

public class GameActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    int difficulty = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ((TextView) findViewById(R.id.textView)).setText(getUser().printStats());
    }

    protected User getUser() {
        return getIntent().getParcelableExtra("USER");
    }

    public void TicTacToeStart(View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this, "TTT", difficulty, getUser()));
    }

    public void RockPaperScissorStart(View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this,"RPS", difficulty, getUser()));
    }

    public void BombStart(View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this,"BS", difficulty, getUser()));
    }

    public void shop(View view){
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
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
