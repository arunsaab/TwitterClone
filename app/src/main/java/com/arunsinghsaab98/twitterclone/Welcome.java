package com.arunsinghsaab98.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.parse.ParseUser;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView textView = findViewById(R.id.textView);
        textView.setText("Welcome " + ParseUser.getCurrentUser().get("username")+"");
    }
}
