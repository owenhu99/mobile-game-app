package com.example.game.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game.Games.GameView;
import com.example.game.R;
import com.example.game.Users.DatabaseHelper;
import com.example.game.Users.User;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    int difficulty = 1;
    DatabaseHelper dbHelper;
    User user1;
    User user2;
    View gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        dbHelper = new DatabaseHelper(this);
        ArrayList<String> userNameList = getIntent().getExtras().getStringArrayList("userList");
        user1 = getUser(userNameList.get(0));
        user2 = getUser(userNameList.get(1));
        ((TextView) findViewById(R.id.textView)).setText(user1.printStats());
    }

    protected User getUser(String username) {
        User currentUser = new User(username, dbHelper);
        Cursor data = dbHelper.getUserData(username);
        if (data.getCount() > 0) {
            data.moveToFirst();
            int currency = data.getInt(data.getColumnIndex("currency"));
            double playtime = data.getDouble(data.getColumnIndex("playtime"));
            int points = data.getInt(data.getColumnIndex("points"));
            int wins = data.getInt(data.getColumnIndex("wins"));
            String skin = data.getString(data.getColumnIndex("skin"));
            String inventory = data.getString(data.getColumnIndex("inventory"));
            ArrayList<String> inventoryList = new ArrayList<>();
            if (inventory != null) {
                for (String str : inventory.split(",")) {
                    inventoryList.add(str);
                }
            }
            currentUser.loadStats(playtime, currency, points, wins, skin, inventoryList);
        }
        return currentUser;
    }

    //>??????????
    public void deleteView(){
        Intent intent = new Intent(this, GameResultsActivity.class);
        startActivity(intent);

    }

    public void RoomEscapeStart(View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gameView = new GameView(this,this, "Room", user1, user2);
        setContentView(gameView);
    }

    public void MemoryStart(View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gameView = new GameView(this,this, "Memory", user1, user2);
        setContentView(gameView);
    }

    public void RunnerStart(View view) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gameView = new GameView(this,this, "Runner", user1, user2);
        setContentView(gameView);
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

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
