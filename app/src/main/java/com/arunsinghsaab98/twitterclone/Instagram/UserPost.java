package com.arunsinghsaab98.twitterclone.Instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arunsinghsaab98.twitterclone.R;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class UserPost extends AppCompatActivity {

    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post);

        linearLayout = findViewById(R.id.linearLayout);

        Intent receivedIntentObject = getIntent();
        final String receivedUserName = receivedIntentObject.getStringExtra("username");
        Toast.makeText(this, receivedUserName, Toast.LENGTH_SHORT).show();


        setTitle(receivedUserName + "'s posts");

        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Photo");
//        to get photos related to specific user (selected user)
        parseQuery.whereEqualTo("username",receivedUserName);
        parseQuery.orderByDescending("createdAt");

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size() > 0 && e == null)
                {
                    for (ParseObject post : objects)
                    {
                        TextView postDes = new TextView(UserPost.this);
                        postDes.setText(post.get("img_des")+ "");
                        ParseFile postPicture = (ParseFile) post.get("picture");
                        postPicture.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if (data != null && e == null)
                                {
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data,0
                                            ,data.length);
//                                    dynamically implementing imageview
                                    ImageView postImageView = new ImageView(UserPost.this);
                                    LinearLayout.LayoutParams img_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                                    img_params.setMargins(5,5,5,5);
                                    postImageView.setLayoutParams(img_params);
                                    postImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                    postImageView.setImageBitmap(bitmap);

                                    LinearLayout.LayoutParams des_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                                    des_params.setMargins(5,5,5,5);
                                    postDes.setLayoutParams(des_params);
                                    postDes.setGravity(Gravity.CENTER);
                                    postDes.setBackgroundColor(Color.BLUE);
                                    postDes.setTextColor(Color.WHITE);
                                    postDes.setTextSize(30f);

                                    linearLayout.addView(postImageView);
                                    linearLayout.addView(postDes);

                                }
                            }
                        });

                    }
                }
                else
                {
                    Toast.makeText(UserPost.this, receivedUserName+" doesn't have any post", Toast.LENGTH_SHORT).show();
                    finish();

                }
           dialog.dismiss();

            }

        });




    }
}
