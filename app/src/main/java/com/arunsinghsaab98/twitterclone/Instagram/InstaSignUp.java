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
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class InstaSignUp extends AppCompatActivity {

    private Button iSignUp,iLogIn;
    private EditText txtSuname,txtSpass,txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insta_sign_up);

        txtSuname = findViewById(R.id.iSuname);
        txtSpass = findViewById(R.id.iSpass);
        txtEmail = findViewById(R.id.isEmail);

        iSignUp = findViewById(R.id.isignUp);
        iLogIn = findViewById(R.id.ilogin);

        iLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstaSignUp.this,InstaLogin.class);
                startActivity(intent);
                finish();
            }
        });

        iSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser instaUser = new ParseUser();
                instaUser.setEmail(txtEmail.getText().toString().toLowerCase());
                instaUser.setUsername(txtSuname.getText().toString().trim());
                instaUser.setPassword(txtSpass.getText().toString().trim());



                instaUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null)
                        {
                            Toast.makeText(InstaSignUp.this, "you are signed up successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(InstaSignUp.this,InstaLogin.class);
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
        });


    }
}
