package com.example.abzo.socsoc;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PlayerHomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView playerProfilePic;
    TextView playerUsername;
    TextView playerEmail;
    DataBaseHelper db;
    String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);//TODO rename nav_view to nav_view_player
        View headerView = navigationView.getHeaderView(0);

        ImageView playerImage = (ImageView)headerView.findViewById(R.id.player_nav_pic);
        TextView playerUsername = (TextView) headerView.findViewById(R.id.player_nav_username);
        TextView PlayerEmail = (TextView) headerView.findViewById(R.id.player_nav_email);

        //database stuff
       //this.deleteDatabase("socData");
        String username;
        db = new DataBaseHelper(this);
       // Cursor idCursor = db.getDataFromName("abz");

        Intent comingIntent = getIntent();

        if (comingIntent.hasExtra("USERNAME")) {

            username = comingIntent.getStringExtra("USERNAME");

            Cursor idCursor = db.getDataFromUserName(username,"player");

            if(idCursor!= null && idCursor.moveToFirst()){

                playerUsername.setText(idCursor.getString(idCursor.getColumnIndex("username")) );
                PlayerEmail.setText(idCursor.getString(idCursor.getColumnIndex("email")));


                if(comingIntent.hasExtra("FILEPATH"))
                {

                    //saving image and changing its name
                    filePath = comingIntent.getStringExtra("FILEPATH");
                    String filename = "pippo.png";

                    try {
                        File f = new File(filePath, filename);
                        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                        playerImage.setImageBitmap(b);

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

                }else{
                    try {
                        Bitmap b = BitmapFactory.decodeStream(new FileInputStream("/data/user/0/com.example.abzo.socsoc/app_imageDir/"+username+".png"));
                        playerImage.setImageBitmap(b);

                        Log.d("Image","second load succcess");

                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                        Log.d("Image","second load failed");


                    }
                }

            } else {

                Log.d("cursorID","Null");
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.player_home_page, menu);
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

        if (id == R.id.nav_fields) {

            getSupportFragmentManager().beginTransaction().replace(R.id.player_content, new PlayerFields()).commit();
        } else if (id == R.id.nav_edit_profile_player) {

            getSupportFragmentManager().beginTransaction().replace(R.id.player_content, new PlayerEditProfile()).commit();

        } else if (id == R.id.nav_team) {

            getSupportFragmentManager().beginTransaction().replace(R.id.player_content, new PlayerTeams()).commit();

        } else if (id == R.id.nav_logout_player) {

            Intent intent = new Intent(PlayerHomePage.this, MainActivity.class);
             startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
