package com.example.game;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        ArrayList<String> users = getUsers();
        TextView[] userList = new TextView[users.size()];
        if (userList.length != 0) {
            for (int i = 0; i < userList.length; i++) {
                userList[i] = new TextView(this);
                userList[i].setText(users.get(i));
                userList[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(userList[i]);
            }
        }
    }

    protected ArrayList<String> getUsers() {
        ArrayList<String> users = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(getResources().getString(R.string.savefile)));
                CSVParser cp = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())
        ) {
            for (CSVRecord csvRecord : cp) {
                String username = csvRecord.get("User Name");
                users.add(username);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}
