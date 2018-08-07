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
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterOwner extends AppCompatActivity  implements View.OnClickListener{

    static final int REQUEST_IMAGE_GET = 1;
    private ImageView image;
    Uri fullPhotoUri;

    Button nextBtn;
    Button backBtn;
    Button uploud;
    EditText numberOfFields;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner);
       // getSupportActionBar().hide();

        //referncing
        nextBtn = (Button) findViewById(R.id.next_btn);
        backBtn = (Button) findViewById(R.id.go_back_btn_to_registermain);
        uploud = (Button) findViewById(R.id.button_uploud_pic);
        numberOfFields = (EditText)findViewById(R.id.textView_numberOfFields_in);
        image = (ImageView) findViewById(R.id.user_picture_register);


        nextBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        uploud.setOnClickListener(this);



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
            case R.id.next_btn: {

                if(numberOfFields.getText().toString().equals(""))
                {
                    Toast.makeText(this, "Please specify number of fields", Toast.LENGTH_SHORT).show();
                    break;
                }
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
