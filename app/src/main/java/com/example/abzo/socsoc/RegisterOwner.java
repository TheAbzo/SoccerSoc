package com.example.abzo.socsoc;

import android.content.Context;
import android.content.ContextWrapper;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class RegisterOwner extends AppCompatActivity  implements View.OnClickListener{

    static final int REQUEST_IMAGE_GET = 1;
    private ImageView image;
    Uri fullPhotoUri;

    Button nextBtn;
    Button backBtn;
    Button uploud;

    EditText numberOfFields;
    EditText name;
    EditText username;
    EditText email;
    EditText address;
    EditText telephone;
    String filename;
    String picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner);
       // getSupportActionBar().hide();

        //referncing
        nextBtn = (Button) findViewById(R.id.next_btn);
        backBtn = (Button) findViewById(R.id.go_back_btn_to_registermain);
        uploud = (Button) findViewById(R.id.button_uploud_pic);
        image = (ImageView) findViewById(R.id.user_picture_register);

        numberOfFields = (EditText)findViewById(R.id.owner_numberOfFields_in);
        name = (EditText) findViewById(R.id.owner_name_in);
        username = (EditText) findViewById(R.id.owner_userName_in);
        email = (EditText) findViewById(R.id.owner_email_in);
        address = (EditText) findViewById(R.id.owner_address_in);
        telephone = (EditText) findViewById(R.id.owner_number_in);


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
                //saving image into file system

                filename = "pippo.png";

                try {
                    //FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    ContextWrapper cw = new ContextWrapper(getApplicationContext());

                    //creating image directory
                    // path to /data/data/yourapp/app_data/imageDir
                    File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

                    // Create imageDir
                    File myPath = new File(directory,filename);

                    FileOutputStream out = new FileOutputStream(myPath);

                    theImage.compress(Bitmap.CompressFormat.PNG, 90, out);
                    out.flush();
                    out.close();
                    Log.d("Image","saved success");

                    picture =  directory.getAbsolutePath();
                    Log.d("pathis",picture);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Image","saved failed");

                }


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

                //check for fields
                //checking for data
                if((name.getText() == null)||(numberOfFields.getText() == null)||(email.getText() == null)||
                        (username.getText() == null)||(address.getText() == null)||
                        (picture == null)||(telephone.getText() == null))
                {
                    Toast.makeText(this, "Please fill all the fields including image", Toast.LENGTH_SHORT).show();
                    break;
                }
                //saving fields in an array

                String[] array = new String[]{name.getText().toString(), numberOfFields.getText().toString()
                        , email.getText().toString(),username.getText().toString(), address.getText().toString(),telephone.getText().toString()};

                Intent intent = new Intent(RegisterOwner.this, RegisterOwner2.class);
                Bundle bundle = new Bundle();
                bundle.putStringArray("MyArray", array);
                intent.putExtras(bundle);
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
