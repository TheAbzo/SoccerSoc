package com.example.abzo.socsoc;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OwnerHomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DataBaseHelper db;
    String filePath = "/data/user/0/com.example.abzo.socsoc/app_imageDir/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_owner);
        View headerView = navigationView.getHeaderView(0);

        ImageView ownerImage = (ImageView)headerView.findViewById(R.id.nav_owner_profile_pic);
        TextView ownerUsername = (TextView) headerView.findViewById(R.id.nav_owner_username);
        TextView ownerEmail = (TextView) headerView.findViewById(R.id.nav_owner_email);
        /////filling data //todo put in a function
        String username;
        Intent comingIntent = getIntent();
        db = new DataBaseHelper(this);

        if(comingIntent.hasExtra("USERNAME")){

            username = comingIntent.getStringExtra("USERNAME");
            Cursor cursorData = db.getDataFromUserName(username,"owner");
            if(cursorData!= null && cursorData.moveToFirst()){

                ownerUsername.setText(cursorData.getString(cursorData.getColumnIndex("username")));
                ownerEmail.setText(cursorData.getString(cursorData.getColumnIndex("email")));
            }else {
                Log.d("ownerData","Null");
            }

            if(comingIntent.hasExtra("COMING_FROM_MAIN_ACTIVITY")){

                try {
                    Bitmap b = BitmapFactory.decodeStream(new FileInputStream("/data/user/0/com.example.abzo.socsoc/app_imageDir/"+username+".png"));
                    ownerImage.setImageBitmap(b);
                    Log.d("Image","second load succcess");
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                    Log.d("Image","second load failed");
                }
            }else {
                String filename = "pippo.png";

                try {
                    File f = new File(filePath, filename);
                    Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                    ownerImage.setImageBitmap(b);

                    String newName = username + ".png";
                    Log.d("newName",newName);
                    File newfile = new File(filePath,newName);
                    f.renameTo(newfile);
                    Log.d("Image","first load succcess");
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                    Log.d("Image","first load failed");
                }
            }
        }



        ////

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_advertise) {
            // Handle the advertisment action
            Log.d("hello","ads 1");

            getSupportFragmentManager().beginTransaction().replace(R.id.content_owner, new OwnerAdvertisment()).commit();
        } else if (id == R.id.nav_manage_fields) {
            Log.d("hello","managing 1");

            getSupportFragmentManager().beginTransaction().replace(R.id.content_owner, new OwnerManageFields()).commit();

        } else if (id == R.id.nav_edit_profile) {

            getSupportFragmentManager().beginTransaction().replace(R.id.content_owner, new OwnerEditProfile()).commit();

        } else if (id == R.id.nav_logout) {

            Intent intent = new Intent(OwnerHomePage.this, MainActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
