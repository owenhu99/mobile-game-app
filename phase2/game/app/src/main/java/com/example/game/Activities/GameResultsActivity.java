package com.example.game.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.Users.DatabaseHelper;
import com.example.game.Users.User;


import java.util.ArrayList;

public class GameResultsActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    User user1;
    User user2;
    int userPoints1;
    int userPoints2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        dbHelper = new DatabaseHelper(this);
        ArrayList<String> userNameList = getIntent().getExtras().getStringArrayList("userList");
        user1 = getUser(userNameList.get(0));
        user2 = getUser(userNameList.get(1));

        ((TextView) findViewById(R.id.PlayerOneName)).setText(userNameList.get(0));
        ((TextView) findViewById(R.id.PlayerTwoName)).setText(userNameList.get(1));
        userPoints1 = user1.getLastPoints();
        userPoints2 = user2.getLastPoints();
        ((TextView) findViewById(R.id.PlayerOneScore)).setText(userPoints1);
        ((TextView) findViewById(R.id.PlayerTwoScore)).setText(userPoints2);
        if(userPoints1> userPoints2){
            ((TextView) findViewById(R.id.GameWinner)).setText("The winner is: " + userNameList.get(0));
            user1.updateWins();
        }
        else if (userPoints1< userPoints2) {
            ((TextView) findViewById(R.id.GameWinner)).setText("The winner is: " + userNameList.get(1));
            user2.updateWins();
        }
        else
            ((TextView) findViewById(R.id.GameWinner)).setText("The game ended in a tie!");
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

    public void ReturnToGameSelect(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }


}
