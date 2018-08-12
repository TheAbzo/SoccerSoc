package com.example.abzo.socsoc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerFields extends Fragment implements View.OnClickListener {

    Button btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_player_fields,container,false);

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view); //todo change it to nav_view_player
        View headerView = navigationView.getHeaderView(0);

        TextView userName = (TextView) headerView.findViewById(R.id.player_nav_username);
        String username = userName.getText().toString();

        ImageView playerPic = (ImageView) rootView.findViewById(R.id.player_profile_pic_in_fields);

        try {
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream("/data/user/0/com.example.abzo.socsoc/app_imageDir/"+username+".png"));
            playerPic.setImageBitmap(b);
            Log.d("Image","second load succcess");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            Log.d("Image","second load failed");
        }


        btn = (Button) rootView.findViewById(R.id.btn_search_field);
        btn.setOnClickListener(this);
        return rootView;


    }

    @Override
    public void onClick(View view) {
        // implements your things
        switch (view.getId()) {
            case R.id.btn_search_field:{

                //open the other fragment
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.player_content, new PlayerFieldsSecondFrag());
                transaction.commit();

            }
        }

    }

}
