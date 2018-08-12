package com.example.abzo.socsoc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OwnerEditProfile extends Fragment implements View.OnClickListener{

    ImageView ownerImage;
    EditText name;
    EditText email;
    EditText addressOfField;
    EditText telephoneNumber;

    String userNameOfOwner;
    Button editBtn;
    Cursor ownerData;
    DataBaseHelper db;
    int numberOfFields;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_owner_edit_profile,container,false);

        //referencing
        name = (EditText)rootView.findViewById(R.id.owner_name_in);
        email = (EditText)rootView.findViewById(R.id.owner_email_in);
        addressOfField = (EditText)rootView.findViewById(R.id.owner_address_in);
        telephoneNumber = (EditText)rootView.findViewById(R.id.owner_number_in);
        ownerImage = (ImageView)rootView.findViewById(R.id.user_picture_register_owner);

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view_owner);
        View headerView = navigationView.getHeaderView(0);


        TextView userName = (TextView) headerView.findViewById(R.id.nav_owner_username);
        userNameOfOwner = userName.getText().toString();

        //create database instance and get data from username
        db = new DataBaseHelper(getActivity());
        ownerData = db.getDataFromUserName(userName.getText().toString(),"owner"); //TODO make it not hardcoding
        Cursor fieldsOfOwner = db.getFieldsByOwnerName(userNameOfOwner);
        numberOfFields = fieldsOfOwner.getCount();
        //fill data  String size = cursorData.getString(cursorData.getColumnIndex("size"));
        if(ownerData!= null && ownerData.moveToFirst()) {

            name.setText(ownerData.getString(ownerData.getColumnIndex("name")));//TODO make it not hardcoding,owner_phone
            email.setText(ownerData.getString(ownerData.getColumnIndex("email")));//TODO make it not hardcoding,owner_phone
            addressOfField.setText(ownerData.getString(ownerData.getColumnIndex("address")));//TODO make it not hardcoding,owner_phone
            telephoneNumber.setText(ownerData.getString(ownerData.getColumnIndex("owner_phone")));//TODO make it not hardcoding,owner_phone
        }
        //call the function that sets the picture
        setImage(userName.getText().toString());
        editBtn = (Button)rootView.findViewById(R.id.btn_edit_owner);
        editBtn.setOnClickListener(this);

        return rootView;

    }

    public void setImage(String username){

        try {
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream("/data/user/0/com.example.abzo.socsoc/app_imageDir/"+username+".png"));
            ownerImage.setImageBitmap(b);
            Log.d("Image","second load succcess");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            Log.d("Image","second load failed");
        }
    }

    public void saveData(){
        if(name.getText().toString().equals("")|| email.getText().toString().equals("")//todo handle lag in toasts
                || addressOfField.getText().toString().equals("")|| telephoneNumber.getText().toString().equals("")){

            Toast.makeText(getActivity(), "please don't leave anything blank", Toast.LENGTH_SHORT).show();


        }else {

            db = new DataBaseHelper(getActivity());
            Boolean result = db.ownerCreateUpdate(name.getText().toString(),email.getText().toString(),userNameOfOwner,
                    addressOfField.getText().toString(),telephoneNumber.getText().toString(),String.valueOf(numberOfFields),false);

            if(result)
                Toast.makeText(getActivity(), "saved successful", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "saved unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_edit_owner:{
              //  / get prompts.xml view
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View promptsView = inflater.inflate(R.layout.prompt, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView.findViewById(R.id.editView_prompt);

                // set dialog message
                alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        //if answer is right , say its saved with toast and close the dialogue
                                        String pass = userInput.getText().toString();
                                        if(pass.contains("123"))
                                        {Toast.makeText(getActivity(), "correct password", Toast.LENGTH_SHORT).show();
                                            //call function save that has toast in it
                                            saveData();
                                            dialog.cancel();}
                                        else {

                                            Toast.makeText(getActivity(), "wrong password", Toast.LENGTH_SHORT).show();
                                            dialog.cancel();
                                        }
                                        //result.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        }

    }
}
