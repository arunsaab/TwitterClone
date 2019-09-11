package com.arunsinghsaab98.twitterclone.Instagram.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arunsinghsaab98.twitterclone.Instagram.TabAdapter;
import com.arunsinghsaab98.twitterclone.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ProfileTab extends Fragment  {

    EditText favSports;
    Button btnUpdate;


    public ProfileTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        favSports = view.findViewById(R.id.edtFavSports);
        btnUpdate = view.findViewById(R.id.btnUpdate);

       final ParseUser parseUser = ParseUser.getCurrentUser();

       if (parseUser.get("favourite") == null) {
           favSports.setText("");
       }
       else
       {
           favSports.setText(parseUser.get("favourite").toString());
       }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("favourite",favSports.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null)
                        {
                            Toast.makeText(getContext(), "data Saved", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "error hai: " +e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


        return view;
    }


}
