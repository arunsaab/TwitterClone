package com.arunsinghsaab98.twitterclone.Instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arunsinghsaab98.twitterclone.R;
import com.arunsinghsaab98.twitterclone.SignUp;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class InstaLogin extends AppCompatActivity {

    EditText inlogEmail,inlogPass;
    Button InstaLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insta_login);

        inlogPass = findViewById(R.id.iLpass);
        inlogEmail = findViewById(R.id.iLemail);

        InstaLogIn = findViewById(R.id.ilogin);

        InstaLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(inlogEmail.getText().toString().trim(), inlogPass.getText().toString().trim(), new LogInCallback() {




                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(e == null )
                        {
                            Toast.makeText(getApplicationContext(),user.getUsername().toString()+"",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(InstaLogin.this,InstaSignUp.class);
                            startActivity(intent);
                        }else
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }
}
