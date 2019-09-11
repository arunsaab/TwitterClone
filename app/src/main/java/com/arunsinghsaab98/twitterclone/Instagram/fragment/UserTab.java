package com.arunsinghsaab98.twitterclone.Instagram.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arunsinghsaab98.twitterclone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class UserTab extends Fragment {

    private ListView listView ;
    private ArrayList arrayList;
    private ArrayAdapter arrayAdapter;
    private TextView textView;

    public UserTab() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_tab, container, false);

        listView = view.findViewById(R.id.listView);
        arrayList = new ArrayList();
        arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);
        textView = view.findViewById(R.id.txtMsg);


        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
//to not show current user
        parseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());

    parseQuery.findInBackground(new FindCallback<ParseUser>() {
        @Override
        public void done(List<ParseUser> users, ParseException e) {

            if (e == null && users.size() >0)
            {
                for(ParseUser user : users)
                {
                    arrayList.add(user.getUsername());
                }
                listView.setAdapter(arrayAdapter);
                textView.animate().alpha(0).setDuration(2000);
                listView.setVisibility(View.VISIBLE);

            }
            else
            {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    });


        return view;
    }


}
