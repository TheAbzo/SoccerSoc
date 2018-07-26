package com.example.abzo.socsoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    Button registerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        registerBtn = (Button) findViewById(R.id.button_sign_up);//referncing
        registerBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign_up: {

                //intent
                Intent intent = new Intent(MainActivity.this, RegisterMain.class);
                startActivity(intent);
                break;
            }


        }

    }
}
