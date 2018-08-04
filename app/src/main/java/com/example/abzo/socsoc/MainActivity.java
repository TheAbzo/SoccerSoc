package com.example.abzo.socsoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    Button registerBtn,loginBtn;
    EditText password,login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getSupportActionBar().hide();


        login = (EditText)findViewById(R.id.username_field_in_login);
        registerBtn = (Button) findViewById(R.id.button_sign_up);//referncing
        registerBtn.setOnClickListener(this);

       /////////////////////// just to test the owner home page activity ////////////////
         loginBtn = (Button) findViewById(R.id.button_log_in);//referncing
        loginBtn.setOnClickListener(this);
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
            /////////////////////// just to test the owner home page activity ////////////////
            case R.id.button_log_in: {
                //intent
                String userName = login.getText().toString();

                Log.d("username",userName);
                if (userName.contains("player")){
                    Log.d("player", userName);

                    Intent intent = new Intent(MainActivity.this, PlayerHomePage.class);
                    startActivity(intent);
                    break;
                }else if(userName.contains("owner")){

                    Log.d("mytag", "player");

                    Intent intent = new Intent(MainActivity.this, OwnerHomePage.class);
                    startActivity(intent);
                    break;
                }else {
                    Toast.makeText(this, "please type 'player' or 'owner'", Toast.LENGTH_SHORT).show();
                }

            }


        }

    }
}
