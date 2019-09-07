package com.arunsinghsaab98.twitterclone;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import com.parse.ParseInstallation;
import com.parse.SaveCallback;


public class SignUp extends AppCompatActivity {

    private EditText name,pass;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        btnSave = findViewById(R.id.btnSave);

        ParseInstallation.getCurrentInstallation().saveInBackground();

    }

    public void saveInfo(View v)
    {
        ParseObject user = new ParseObject("user");
        user.put("name",name.getText().toString());
        user.put("password",pass.getText().toString().trim());
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null)
                {
                    Toast.makeText(SignUp.this, "info saved successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
