package com.example.abzo.socsoc;

import android.content.Intent;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RegisterOwner2 extends AppCompatActivity implements View.OnClickListener {

    private String numberOfFields;
    Button finishBtn;
    DataBaseHelper db;
    String arrayReceived[];
    List<ConstraintLayout> constraints = new ArrayList<>();

    EditText name;
    EditText size;
    EditText price;
    EditText availableDays;
    EditText availableTime;

    int ownerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner2);
        //   getSupportActionBar().hide();

        // Parent layout
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.layout);
        finishBtn = (Button) findViewById(R.id.finish_btn);

        // Layout inflater
        LayoutInflater layoutInflater = getLayoutInflater();
        View view;


        Bundle bundle = getIntent().getExtras();
        arrayReceived = bundle.getStringArray("MyArray");




       //TODO make a check first if the intent is available

            numberOfFields = arrayReceived[1];
            //loop
            Integer f = Integer.valueOf(numberOfFields);

            Log.d("MYTAGE", f.toString());

            for (int i = 0; i < f; i++) {

                // Add the text layout to the parent layout
                view = layoutInflater.inflate(R.layout.custom_owner_register_layout, parentLayout, false);

                // In order to get the view we have to use the new view with text_layout in it
                ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.child);

                TextView textView = (TextView) view.findViewById(R.id.textView_to_be_filled_custom_reg);
                textView.setText("Field # " + (i + 1));

                // Add the text view to the parent layout
                parentLayout.addView(constraintLayout);
                constraints.add(constraintLayout);
            }





        finishBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.finish_btn: {

                //        String[] array = new String[]{name.getText().toString(), numberOfFields.getText().toString()
                // email.getText().toString(),username.getText().toString(), address.getText().toString(),telephone.getText().toString()};
                db = new DataBaseHelper(this);
                Boolean savingProcess = db.ownerCreateUpdate(arrayReceived[0],arrayReceived[2],arrayReceived[3],arrayReceived[4],arrayReceived[5],arrayReceived[1],true);
                Log.d("Success owner?", savingProcess.toString()); //TODO handle unique username

                Log.d("Ssaassa", "value:"+ constraints.size());
                    for (int i = 0; i < constraints.size(); i++) {
                        name = (EditText) constraints.get(i).findViewById(R.id.custom_name_in_reg);
                        size = (EditText) constraints.get(i).findViewById(R.id.textView_size_in_reg_custom);
                        price = (EditText) constraints.get(i).findViewById(R.id.textView_price_in_custom_reg);
                        availableDays = (EditText) constraints.get(i).findViewById(R.id.textView_available_day_in_custom_reg);
                        availableTime = (EditText) constraints.get(i).findViewById(R.id.textView_available_time_in_custom_reg);

                        if (name.getText() == null || size.getText() == null || price.getText() == null
                                || availableTime.getText() == null || availableDays.getText() == null) {
                            Toast.makeText(this, "Please fill all the fields ", Toast.LENGTH_SHORT).show();

                        }
                       Boolean isSuccess =  db.fieldCreateUpdate(name.toString(), price.toString(), size.toString(), availableDays.toString(), availableTime.toString(),
                      arrayReceived[3],true  );
                       Log.d("is Field saved",isSuccess.toString());
                    }

                String userName = arrayReceived[3];
                Intent intent = new Intent(RegisterOwner2.this, OwnerHomePage.class);
                intent.putExtra("USERNAME",userName);
                startActivity(intent);
                break;

            }

        }
    }
}
