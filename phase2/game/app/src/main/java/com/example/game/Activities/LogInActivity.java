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

import com.example.game.Users.DatabaseHelper;
import com.example.game.R;
import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        dbHelper = new DatabaseHelper(this);
        fillUsers();
    }

    private void fillUsers() {
        LinearLayout linearLayout = findViewById(R.id.users_menu);

        ArrayList<String> users = new ArrayList<>();
        Cursor data = dbHelper.getData();
        while (data.moveToNext()) {
            users.add(data.getString(1));
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
                Button buttonDelete = new Button(userDisplayList[i].getContext());
                buttonDelete.setText("DELETE");
                buttonDelete.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent reload = new Intent(LogInActivity.this, LogInActivity.class);
                        dbHelper.deleteUser(currentUser);
                        startActivity(reload);
                    }
                });
                userDisplayList[i].addView(buttonDelete);

                // Button to start as the corresponding user
                Button buttonStart = new Button(userDisplayList[i].getContext());
                buttonStart.setText("START");
                buttonStart.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(LogInActivity.this, GameActivity.class);
                        intent.putExtra("username", currentUser);
                        startActivity(intent);
                    }
                });
                userDisplayList[i].addView(buttonStart);

                userDisplayList[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(userDisplayList[i]);
            }
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
