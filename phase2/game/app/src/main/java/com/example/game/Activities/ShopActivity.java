package com.example.game.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


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

    currentUser = getUser(userName);
    ArrayList<String> userInv = currentUser.getInventory();

    //testing for me
    currentUser.setCurrency(100);

    updateDisplay();

    // creates buttons and sets listeners
    Button button1 = (Button) findViewById(R.id.shop1);
    Button button2 = (Button) findViewById(R.id.shop2);
    Button button3 = (Button) findViewById(R.id.shop3);

    button1.setOnClickListener(this);
    button2.setOnClickListener(this);
    button3.setOnClickListener(this);

    prepareButtons(userInv, button1, button2, button3 );


  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.shop1:
          Button button = findViewById(R.id.shop1);
          String skin = "1";
          if (button.getText().equals("Equip")) {
              equipSkin(skin);
          } else{
              buyPack_1(v, button);
          }
      case R.id.shop2:
          Button button2 = findViewById(R.id.shop2);
          String skin2 = "2";
          if (button2.getText().equals("Equip")) {
              equipSkin(skin2);
        } else{
          buyPack_2(v, button2);
              }
      case R.id.shop3:
          Button button3 = findViewById(R.id.shop3);
          String skin3 = "3";

        if (button3.getText().equals("Equip")) {

            equipSkin(skin3);
            }

        else{
            buyPack_3(v, button3);
        }
    }
  }


  public void buyPack_1(View view, Button button) {
    buy(SKIN_1_COST, "1", button);
  }

  public void buyPack_2(View view, Button button) {
    buy(SKIN_2_COST, "2", button);
  }

  public void buyPack_3(View view, Button button) {
    buy(SKIN_3_COST, "3", button);
  }


  public void buy(int cost, String skin, Button button) {
    int gold = this.currentUser.getCurrency();
    if (gold >= cost) {
        currentUser.setCurrency(gold - cost);
        currentUser.addToInventory(skin);

        //change the button text to Equip now
        button.setText("Equip");

        updateDisplay();
    }
    else{
        Toast.makeText(this, "You don't enough Gold!", Toast.LENGTH_SHORT).show();
    }
  }

  public void equipSkin(String skin){
      currentUser.setSkin(skin);
      String text = "Equiped " + skin + " Skin Pack!";
      Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
      updateDisplay();
  }


  private void prepareButtons(ArrayList<String> userInv, Button button1, Button button2,
                              Button button3){

      if (userInv.contains("1")){
          button1.setText("Equip");
      }
      else{
          String textCost = "Buy: " + SKIN_1_COST;
          button1.setText(textCost);
      }

      if (userInv.contains("2")){
          button2.setText("Equip");
      }
      else{
          String textCost = "Buy: " + SKIN_2_COST;
          button2.setText(textCost);
      }

      if (userInv.contains("3")){
          button3.setText("Equip");
      }
      else{
          String textCost = "Buy: " + SKIN_3_COST;
          button3.setText(textCost);
      }
  }



  private  void updateDisplay(){
      // gets and displays current users currency
      String textCurrency = "Gold: " + currentUser.getCurrency();


      // gets and displays current users skin
      String textEquiped = "\nEquiped Skin: " + currentUser.getSkin();

      String message = textCurrency +  textEquiped;


      ((TextView) findViewById(R.id.textViewShop)).setText(message);
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

}