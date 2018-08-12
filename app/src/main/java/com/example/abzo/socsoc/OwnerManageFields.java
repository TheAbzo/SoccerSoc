package com.example.abzo.socsoc;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class OwnerManageFields extends Fragment implements View.OnClickListener{

    private FieldsAdapter fieldsAdapter;
    private RecyclerView fieldList;
    private Cursor cursorData;
    int number;
    DataBaseHelper db;
    String userName;
    FloatingActionButton addBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_owner_manage_fields,container,false);
        addBtn = rootView.findViewById(R.id.floating_btn_manage_fields);
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view_owner);
        View headerView = navigationView.getHeaderView(0);

        db = new DataBaseHelper(getActivity());

        TextView ownerUsername = (TextView) headerView.findViewById(R.id.nav_owner_username);

        userName = ownerUsername.getText().toString();
        Log.d("Hello","username is"+userName);

        //reference to the recycler view
        fieldList = (RecyclerView) rootView.findViewById(R.id.recycler_of_manage_fields);

        //set layout manager
        fieldList.setLayoutManager(new LinearLayoutManager(getActivity()));

        //get data from database as cursor ,and get its count
        cursorData =  db.getFieldsByOwnerName(userName);
        number = cursorData.getCount();
        if(cursorData.moveToFirst())
        { String size = cursorData.getString(cursorData.getColumnIndex("size"));

        Log.d("hello","Size is"+size);}
        //create an adapter
        FieldsAdapter fieldsAdapter = new FieldsAdapter(number,cursorData);

        //set the adapter
        fieldList.setAdapter(fieldsAdapter);


       // fieldList.setHasFixedSize(true);

        Log.d("Hello","app should start"+number);
        addBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.floating_btn_manage_fields:{

                //open the other fragment
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.content_owner, new OwnerAddField());
                transaction.commit();

            }
        }
    }
}
