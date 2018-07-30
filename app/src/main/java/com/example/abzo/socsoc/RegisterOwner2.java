package com.example.abzo.socsoc;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RegisterOwner2 extends AppCompatActivity {

    private String numberOfFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner2);
     //   getSupportActionBar().hide();

        // Parent layout
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.layout);

        // Layout inflater
        LayoutInflater layoutInflater = getLayoutInflater();
        View view;

        //todo change available time and date to something other than textview
        Intent comingIntent = getIntent();

        if(comingIntent.hasExtra("FIELDS")){

            numberOfFields = comingIntent.getStringExtra("FIELDS");
            //loop
            Integer f = Integer.valueOf(numberOfFields);

            Log.d("MYTAGE", f.toString());

            for (int i = 0; i < f; i++) {

                // Add the text layout to the parent layout
                view = layoutInflater.inflate(R.layout.custom_owner_register_layout, parentLayout, false);

                // In order to get the view we have to use the new view with text_layout in it
                ConstraintLayout constraintLayout =  (ConstraintLayout) view.findViewById(R.id.child);

                 TextView textView = (TextView) view.findViewById(R.id.textView_to_be_filled);
                 textView.setText("Field # " + (i+1));

                // Add the text view to the parent layout
                parentLayout.addView(constraintLayout);
            }

        }
    }
}
