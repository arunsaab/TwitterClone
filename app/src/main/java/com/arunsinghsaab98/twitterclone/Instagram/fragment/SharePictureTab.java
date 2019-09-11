package com.arunsinghsaab98.twitterclone.Instagram.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arunsinghsaab98.twitterclone.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;

public class SharePictureTab extends Fragment {

    ImageView imageView;
    Button btnShareImg;
    EditText txtDiscription;
    Bitmap recievedImageBitmap;

    public SharePictureTab() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share_picture_tab, container, false);

        imageView = view.findViewById(R.id.chooseImg);
        txtDiscription = view.findViewById(R.id.txtDiscription);
        btnShareImg = view.findViewById(R.id.shareImg);

        btnShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (recievedImageBitmap != null)
                {

                    if (txtDiscription.getText().toString().equals(""))
                    {
                        Toast.makeText(getContext(), "Error: yOu must add some description", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        recievedImageBitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                        byte[] bytes = byteArrayOutputStream.toByteArray();
                        ParseFile parseFile = new ParseFile("img.png",bytes);
                        ParseObject parseObject = new ParseObject("Photo");
                        parseObject.put("picture",parseFile);
                        parseObject.put("username", ParseUser.getCurrentUser().getUsername());
                        parseObject.put("img_des",txtDiscription.getText().toString());

                        final ProgressDialog dialog = new ProgressDialog(getContext());
                        dialog.setMessage("Loading...");
                        dialog.show();

                        parseObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null)
                                {
                                    Toast.makeText(getContext(), "Done ", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(getContext(), "Unknown Error:  ", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }
                        });

                    }

                }else
                {
                    Toast.makeText(getContext(), "Error: yOu must select an image", Toast.LENGTH_SHORT).show();
                }

            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23 &&
                        ActivityCompat.checkSelfPermission(getContext(),
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)

                {
                    requestPermissions(new String[] { Manifest.permission.READ_EXTERNAL_STORAGE},1000);
                }
                else {
                    getChosenImage();
                }



            }
        });

        return view;

    }

    private void getChosenImage() {

//        Toast.makeText(getContext(), "Now we can access the Image", Toast.LENGTH_SHORT).show();

        //snippet part 1 to access image from galary
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,2000);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1000)
        {
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getChosenImage();
            }
        }
    }
//snippet part 2 to access image from galary
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2000)
        {

            if (resultCode == Activity.RESULT_OK)
            {
                try {

                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn,null,null,null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);

//                           close cursor so that it will not eat the resorces
                            cursor.close();

                     recievedImageBitmap = BitmapFactory.decodeFile(picturePath);
                    imageView.setImageBitmap(recievedImageBitmap);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }

    }
}
