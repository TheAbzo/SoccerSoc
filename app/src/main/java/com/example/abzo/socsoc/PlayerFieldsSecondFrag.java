package com.example.abzo.socsoc;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayerFieldsSecondFrag extends Fragment {

    private FieldsAdapter fieldsAdapter;
    private RecyclerView fieldList;
    private Cursor cursorData;
    int number;
    DataBaseHelper db;
    String userName;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_player_fields_recycler,container,false);
        db = new DataBaseHelper(getActivity());

        //reference to the recycler view
        fieldList = (RecyclerView) rootView.findViewById(R.id.fields_player_recycler);

        //set layout manager
        fieldList.setLayoutManager(new LinearLayoutManager(getActivity()));

        //get data from database as cursor ,and get its count
        cursorData =  db.getAllFields();
        number = cursorData.getCount();
        Log.d("count boy","count it"+number);

        //create an adapter
        FieldsAdapter fieldsAdapter = new FieldsAdapter(number,cursorData);

        //set the adapter
        fieldList.setAdapter(fieldsAdapter);

        return rootView;
    }
}
