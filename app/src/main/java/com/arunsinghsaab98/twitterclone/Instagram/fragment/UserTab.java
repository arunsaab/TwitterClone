package com.arunsinghsaab98.twitterclone.Instagram.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arunsinghsaab98.twitterclone.Instagram.UserPost;
import com.arunsinghsaab98.twitterclone.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;


public class UserTab extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView ;
    private ArrayList<String> arrayList;
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

        listView.setOnItemLongClickListener(UserTab.this);
        listView.setOnItemClickListener(UserTab.this);


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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getContext(), UserPost.class);
        intent.putExtra("username",arrayList.get(position));
        startActivity(intent);


    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereEqualTo("username",arrayList.get(position));
        parseQuery.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user != null && e == null)
                {
//                    Toast.makeText(getContext(), user.get("favourite")+" ", Toast.LENGTH_SHORT).show();
                   final PrettyDialog prettyDialog = new PrettyDialog(getContext());

                     prettyDialog.setTitle(user.get("username").toString())
                    .setMessage(user.get("favourite").toString())
                    .setIcon(R.drawable.person)
                    .addButton("Ok", R.color.pdlg_color_white, R.color.pdlg_color_green,
                            new PrettyDialogCallback() {
                                @Override
                                public void onClick() {
                                 prettyDialog.dismiss();
                                }
                            })
                    .show();



                }
                else
                {
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        return true;
    }
}


