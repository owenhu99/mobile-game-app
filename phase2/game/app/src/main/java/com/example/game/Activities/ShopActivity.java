package com.example.game.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.game.R;
import com.example.game.Users.DatabaseHelper;
import com.example.game.Users.User;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {


    DatabaseHelper dbHelper;
    User currentUser;
    final int SKIN_1_COST = 10;
    final int SKIN_2_COST = 25;
    final int SKIN_3_COST = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        dbHelper = new DatabaseHelper(this);
        String userName = getIntent().getExtras().getString("username");

        //currentUser = getUser(userName);
        //ArrayList<String> userInv = currentUser.getInventory();
        ((TextView) findViewById(R.id.textViewShop)).setText(userName);

        Button buy1 = (Button) findViewById(R.id.buy1);
        Button buy2 = (Button) findViewById(R.id.buy2);
        Button buy3 = (Button) findViewById(R.id.buy3);

        buy1.setOnClickListener(this);
        buy2.setOnClickListener(this);
        buy3.setOnClickListener(this);


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


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.buy1:
            case R.id.buy2:
            case R.id.buy3:
        }

    }


  //public void buyPack_1() {
   // int gold = this.currentUser.getCurrency();
   // if (gold >= 10) {
    //  currentUser.setCurrency(gold - 10);
     // currentUser.addToInventory("1");
    //}
    //    }


    public void buyPack_2(View view){}

    public void buyPack_3(View view){}

    public void buy(){


    }




}
