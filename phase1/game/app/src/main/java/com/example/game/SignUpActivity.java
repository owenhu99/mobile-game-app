package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


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
        String filePath = getResources().getString(R.string.savefile);
        if (!new File(filePath).exists()) {
            try (FileWriter fw = new FileWriter(filePath);
                 CSVPrinter cp = new CSVPrinter(fw, CSVFormat.DEFAULT.withHeader("First Name", "Last Name", "User Name"))) {
                cp.printRecord(firstName.getText().toString(), lastName.getText().toString(), userName.getText().toString());
                cp.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileWriter fw = new FileWriter(filePath, true);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(firstName.getText().toString() + "," + lastName.getText().toString() + "," + userName.getText().toString() + "\n");
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }
}
