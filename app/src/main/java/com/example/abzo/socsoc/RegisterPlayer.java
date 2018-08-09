package com.example.abzo.socsoc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterPlayer extends AppCompatActivity implements View.OnClickListener{


    static final int REQUEST_IMAGE_GET = 1;
    private ImageView image;
    Uri fullPhotoUri;
    DataBaseHelper db;

    Button nextBtn;
    Button backBtn;
    Button uploud;

    EditText age;
    EditText name;
    EditText username;
    EditText email;
    EditText address;
    Uri picture;
    EditText telephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_player);
     //   getSupportActionBar().hide();
        image = (ImageView) findViewById(R.id.user_picture_register);

        age = (EditText) findViewById(R.id.textView_age_in);
        name = (EditText) findViewById(R.id.textView_name_in);
        username = (EditText) findViewById(R.id.textView_userName_in);
        email = (EditText) findViewById(R.id.textView_email_in);
        address = (EditText) findViewById(R.id.textView_address_in);
        telephone = (EditText) findViewById(R.id.textView_number_in);

        backBtn = (Button) findViewById(R.id.go_back_btn_to_registermain);
        uploud = (Button) findViewById(R.id.button_uploud_pic);
        nextBtn = (Button)findViewById(R.id.next_btn_register_player);

        backBtn.setOnClickListener(this);
        uploud.setOnClickListener(this);
        nextBtn.setOnClickListener(this);



    }

    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            Bitmap thumbnail = data.getParcelableExtra("data");
            fullPhotoUri = data.getData();
            // Do work with photo saved at fullPhotoUri
            ImageView tv1;

            InputStream inputStream;
            try{
                inputStream = getContentResolver().openInputStream(fullPhotoUri);
                Bitmap theImage = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(theImage);
                picture = fullPhotoUri;


            }
            catch (FileNotFoundException e){
                e.printStackTrace();
                Toast.makeText(this, "unable to open image  ", Toast.LENGTH_SHORT).show();
            }


        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.go_back_btn_to_registermain: {

                Intent intent = new Intent(RegisterPlayer.this, RegisterMain.class);
                startActivity(intent);
                break;
            }

            case R.id.next_btn_register_player: {

                //saving into database

                //checking for data
                if((name.getText() == null)||(age.getText() == null)||(email.getText() == null)||(username.getText() == null)||(address.getText() == null)||
                       (picture == null))
                {
                    Toast.makeText(this, "Please fill all the fields including image", Toast.LENGTH_SHORT).show();
                    break;
                }
             //handle the exceptions
                db = new DataBaseHelper(this);

                Boolean savingProcess = db.saveDataPlayer(name.getText().toString(),age.getText().toString(),email.getText().toString(),
                        username.getText().toString(),address.getText().toString(),picture.toString());

                Log.d("Success?", savingProcess.toString());


                String userName = username.getText().toString();
                Intent intent = new Intent(RegisterPlayer.this, PlayerHomePage.class);
                intent.putExtra("USERNAME",userName);
                startActivity(intent);
                //send the URI
//                if(!fullPhotoUri.toString().equals("")){
//                    intent.putExtra("theURI",fullPhotoUri.toString());
//                }
                startActivity(intent);
                break;
            }

            case R.id.button_uploud_pic: {

                selectImage();
                break;
            }


        }

    }
    public void onPictureClick(View view) {
        selectImage();
    }
}
