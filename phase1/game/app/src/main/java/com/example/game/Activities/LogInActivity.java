package com.example.game.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.Users.User;
import com.example.game.Users.UserHelper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        fillUsers();
    }

    private void fillUsers() {
        LinearLayout linearLayout = findViewById(R.id.users_menu);

        ArrayList<User> users = getUsers();
        final LinearLayout[] userList = new LinearLayout[users.size()];
        if (userList.length != 0) {
            for (int i = 0; i < userList.length; i++) {
                userList[i] = new LinearLayout(this);
                userList[i].setOrientation(LinearLayout.HORIZONTAL);
                final User currentUser = users.get(i);

                // TextView of user name
                TextView textUsername = new TextView(userList[i].getContext());
                textUsername.setText(users.get(i).getUserName());
                userList[i].addView(textUsername);

                // Button to delete the corresponding user
                Button buttonDelete = new Button(userList[i].getContext());
                buttonDelete.setText("DELETE");
                buttonDelete.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent reload = new Intent(LogInActivity.this, LogInActivity.class);
                        UserHelper.deleteUser(currentUser);
                        startActivity(reload);
                    }
                });
                userList[i].addView(buttonDelete);

                // Button to start as the corresponding user
                Button buttonStart = new Button(userList[i].getContext());
                buttonStart.setText("START");
                buttonStart.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(LogInActivity.this, GameActivity.class);
                        intent.putExtra("USER", currentUser);
                        startActivity(intent);
                    }
                });
                userList[i].addView(buttonStart);

                userList[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(userList[i]);
            }
        }
    }

    protected ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(getResources().getString(R.string.savefile)));
                CSVParser cp = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())
        ) {
            for (CSVRecord csvRecord : cp) {
                String username = csvRecord.get("User Name");
                String firstname = csvRecord.get("First Name");
                String lastname = csvRecord.get("Last Name");
                String stats = csvRecord.get("Stats");
                User user = new User(username, firstname, lastname);
                user.setStatsFromCSV(stats);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}
