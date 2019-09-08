package com.arunsinghsaab98.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LogInSignUP extends AppCompatActivity {

    private EditText txtLoginUser,txtLoginPass,txtSignUPUser,txtSignUPPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_sign_up);


        txtLoginUser = findViewById(R.id.txtLogin);
        txtLoginPass = findViewById(R.id.loginPass);
        txtSignUPUser = findViewById(R.id.edtSignUpUserName);
        txtSignUPPass = findViewById(R.id.edtSignUpPassword);

    }

    public void SignUp(View view) {

        ParseUser appUser = new ParseUser();
        appUser.setUsername(txtSignUPUser.getText().toString().trim());
        appUser.setPassword(txtSignUPPass.getText().toString().trim());

        appUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null)
                {
                    Toast.makeText(getApplicationContext(),"signed up successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LogInSignUP.this,Welcome.class);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void loginUser(View view) {

        ParseUser.logInInBackground(txtLoginUser.getText().toString().trim(), txtLoginPass.getText().toString().trim(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null && user != null)
                {
                    Toast.makeText(getApplicationContext(),"logged in successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LogInSignUP.this,Welcome.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}
