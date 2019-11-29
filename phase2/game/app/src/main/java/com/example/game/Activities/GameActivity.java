package com.example.game.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.game.Games.GameView;
import com.example.game.R;
import com.example.game.Users.DatabaseHelper;
import com.example.game.Users.User;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    int difficulty = 1;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        dbHelper = new DatabaseHelper(this);
        ((TextView) findViewById(R.id.textView)).setText(getUser().printStats());
    }

    protected User getUser() {
        String username = getIntent().getExtras().getString("username");
        User currentUser = new User(username, this);
        Cursor data = dbHelper.getUserData(username);
        data.moveToFirst();
        int currency = data.getInt(data.getColumnIndex("currency"));
        double playtime = data.getDouble(data.getColumnIndex("playtime"));
        int points = data.getInt(data.getColumnIndex("points"));
        String skin = data.getString(data.getColumnIndex("skin"));
        String[] inventoryList = data.getString(data.getColumnIndex("inventory")).split(",");
        ArrayList<String> inventory = new ArrayList<>();
        for (String str : inventoryList) {
            inventory.add(str);
        }
        currentUser.loadStats(playtime, currency, points, skin, inventory);
        return currentUser;
    }

    public void TicTacToeStart(View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this, "Room", getUser()));
    }

    public void RockPaperScissorStart(View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this,"Room", getUser()));
    }

    public void BombStart(View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this,"Runner", getUser()));
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
