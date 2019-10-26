package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
    }

    public void submit(View view) {
        Intent intent = new Intent(this, LogInActivity.class);
        EditText firstName = (EditText) findViewById(R.id.editText1);
        EditText lastName = (EditText) findViewById(R.id.editText2);
        EditText userName = (EditText) findViewById(R.id.editText3);
        User user = new User(userName.getText().toString(), firstName.getText().toString(), lastName.getText().toString());

        String fileName = getResources().getString(R.string.savefile);
        try {
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(user);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        startActivity(intent);
    }
}
