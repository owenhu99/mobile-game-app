package com.example.game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_settings);

        b = findViewById(R.id.changeLang);
        b.setOnClickListener(this);
    }
    private void showLanguages(){
        String[] langs = {"English", "Français"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Language");
        builder.setSingleChoiceItems(langs, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    setLocale("en");
                }
                else{
                    setLocale("fr");
                }
            }
        });
        AlertDialog fMapTypeDialog = builder.create();
        fMapTypeDialog.show();
    }

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        config.locale = locale;
        SharedPreferences.Editor editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    private void loadLocale(){
        SharedPreferences editor = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = editor.getString("My_Lang", "");
        setLocale(language);
    }

    @Override
    public void onClick(View view) {
        showLanguages();
    }
}
