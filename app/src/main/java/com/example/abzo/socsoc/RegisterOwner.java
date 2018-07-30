package com.example.abzo.socsoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterOwner extends AppCompatActivity  implements View.OnClickListener{

    Button nextBtn;
    Button backBtn;
    EditText numberOfFields;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner);
        getSupportActionBar().hide();

        //referncing
        nextBtn = (Button) findViewById(R.id.next_btn);
        backBtn = (Button) findViewById(R.id.go_back_btn_to_registermain);
        numberOfFields = (EditText)findViewById(R.id.textView_numberOfFields_in);

        nextBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_btn: {

                String fields = numberOfFields.getText().toString();

                Log.d("mytag", fields);

                //create an intent and sends #of fields in it
                Intent intent = new Intent(RegisterOwner.this, RegisterOwner2.class);
                intent.putExtra("FIELDS",fields);

                Log.d("intentStarted", fields);

                startActivity(intent);
                break;
            }
            case R.id.go_back_btn_to_registermain: {

                Intent intent = new Intent(RegisterOwner.this, RegisterMain.class);
                startActivity(intent);
                break;
            }


        }

    }
}
