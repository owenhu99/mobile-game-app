package com.example.game.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.Users.DatabaseHelper;

import java.util.ArrayList;

public class ChoosePlayersActivity extends AppCompatActivity {

    static int playerNum = 1;
    DatabaseHelper dbHelper;
    static String[] playerKey = new String[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_players);
        dbHelper = new DatabaseHelper(this);
        Button b = findViewById(R.id.start_game);
        checkStartGame(b);
        fillUsers();

    }

    private void fillUsers() {
        LinearLayout linearLayout = findViewById(R.id.users_menu);

        ArrayList<String> users = new ArrayList<>();
        Cursor data = dbHelper.getData();
        while (data.moveToNext()) {
            users.add(data.getString(1));
            //TODO GET THE KEY FROM EACH USER SO THAT WE CAN SEND IT TO THE GAME?
        }
        final LinearLayout[] userDisplayList = new LinearLayout[users.size()];
        if (userDisplayList.length == 0) {
            toastMessage("No users found.");
        } else {
            for (int i = 0; i < userDisplayList.length; i++) {
                userDisplayList[i] = new LinearLayout(this);
                userDisplayList[i].setOrientation(LinearLayout.HORIZONTAL);
                final String currentUser = users.get(i);

                // TextView of user name
                TextView textUsername = new TextView(userDisplayList[i].getContext());
                textUsername.setText(currentUser);
                userDisplayList[i].addView(textUsername);

                // Button to delete the corresponding user
                Button buttonPlayer = new Button(userDisplayList[i].getContext());
                buttonPlayer.setText("Choose as Player " + playerNum);
                buttonPlayer.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        playerNum = 2;
                        Intent reload = new Intent(ChoosePlayersActivity.this, ChoosePlayersActivity.class);
                        startActivity(reload);
                    }
                });
                userDisplayList[i].addView(buttonPlayer);

                userDisplayList[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(userDisplayList[i]);
            }
        }
    }
    private void checkStartGame(Button b){
        if (playerNum == 2){
            b.setEnabled(true);
        }
        else{
            b.setEnabled(false);
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
