package com.example.abzo.socsoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RegisterPlayer extends AppCompatActivity implements View.OnClickListener{

    Button nextBtn;
    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_player);
     //   getSupportActionBar().hide();
        backBtn = (Button) findViewById(R.id.go_back_btn_to_registermain);

        backBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.go_back_btn_to_registermain: {

                Intent intent = new Intent(RegisterPlayer.this, RegisterMain.class);
                startActivity(intent);
                break;
            }


        }

    }
}
