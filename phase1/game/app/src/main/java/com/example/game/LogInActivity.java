package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        fillUsers();
    }

    private void fillUsers() {
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.users_menu);
        ArrayList<User> users = getUsers();
        if (users.size() == 0) {
            return;
        }
        TextView user1 = new TextView(this);
        user1.setText(users.get(0).getUserName());
        user1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(user1);
    }

    protected ArrayList<User> getUsers() {
        Object user;
        ArrayList<User> users = new ArrayList<User>();
        String fileName = getResources().getString(R.string.savefile);

        try {
            FileInputStream fis = openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                while ((user = ois.readObject()) != null) {
                    users.add((User) user);
                }
            } finally {
                ois.close();
                fis.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
}
