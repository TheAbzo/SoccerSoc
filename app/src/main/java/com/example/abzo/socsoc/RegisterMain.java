package com.example.abzo.socsoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegisterMain extends AppCompatActivity implements View.OnClickListener{

    //buttons
    Button nextbtn;
    Button backBtn;
    RadioButton playerRadio;
    RadioButton ownerRadio;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);
        getSupportActionBar().hide();

        //referencing
        nextbtn = (Button) findViewById(R.id.next_btn);
        backBtn = (Button) findViewById(R.id.go_back_btn);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        playerRadio = (RadioButton) findViewById(R.id.player_radio_button);
        ownerRadio = (RadioButton) findViewById(R.id.owner_radio_button);


        nextbtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_btn: {
                //check for radio button clicked

                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    // no radio buttons are checked
                    //do a toast
                }
                else {
                    // one of the radio buttons is checked
                    if(playerRadio.isChecked()) {
                        // is checked
                        //intent
                        Log.d("mytag", "player");

                        Intent intent = new Intent(RegisterMain.this, RegisterPlayer.class);
                        startActivity(intent);
                        break;
                    }
                    else {
                        // not checked
                        Log.d("mytag", "owner");
                        Intent intent = new Intent(RegisterMain.this, RegisterOwner.class);
                        startActivity(intent);
                        break;
                    }

                }
                //TODO handle radio button scenarios// one of the radio buttons is checked
            }
            case R.id.go_back_btn: {

                Intent intent = new Intent(RegisterMain.this, MainActivity.class);
                startActivity(intent);
                break;
            }


        }

    }
}
