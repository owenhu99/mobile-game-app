package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
    }

    public void submit(View view) {
        EditText firstName = findViewById(R.id.editText1);
        EditText lastName = findViewById(R.id.editText2);
        EditText userName = findViewById(R.id.editText3);
        User user = new User(userName.getText().toString(), firstName.getText().toString(), lastName.getText().toString());
        UserHelper.add(user);

        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public void edit(View view) {
        User user = new User("owenhu", "owen", "hu");
        user.setTotalGames(5);
        UserHelper.update(user);
    }

    public void delete(View view) {
        UserHelper.deleteSaveFile();
    }
}
