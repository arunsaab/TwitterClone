package com.arunsinghsaab98.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import com.parse.ParseInstallation;
import com.parse.SaveCallback;

import java.util.List;


public class SignUp extends AppCompatActivity {

    private EditText name,pass;
//    private Button btnSave;
//    private TextView txtGetData;
    String allInfo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
//        btnSave = findViewById(R.id.btnSave);
//        txtGetData = findViewById(R.id.txtGetData);

//        txtGetData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("person");
//                parseQuery.getInBackground("dPUw7HDzTF", new GetCallback<ParseObject>() {
//                    @Override
//                    public void done(ParseObject object, ParseException e) {
//                        if (object != null && e == null)
//                        {
//                            txtGetData.setText(object.get("lname") +"");
//
//                        }
//                    }
//                });
//            }
//        });

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


    public void getall(View view) {
        ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("user");
        queryAll.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null)
                {
                    if (objects.size() > 0)
                    {
                        allInfo = "";
                        for (ParseObject getAll : objects)
                        {
                            allInfo = allInfo + getAll.get("name") + "\n" ;

                        }


                        Toast.makeText(SignUp.this, allInfo, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    public void openLogInSignUp(View view) {

        Intent intent = new Intent(SignUp.this,LogInSignUP.class);
        startActivity(intent);

    }
}
