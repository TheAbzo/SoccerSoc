package com.example.abzo.socsoc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OwnerAddField extends Fragment implements View.OnClickListener {

    EditText name;
    EditText size;
    EditText price;
    EditText availableDays;
    EditText availableTime;

    String username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.activity_owner_add_field,container,false);
        Button addBtn = (Button) rootView.findViewById(R.id.add_field_btn);

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view_owner);
        View headerView = navigationView.getHeaderView(0);

        TextView userName = (TextView) headerView.findViewById(R.id.nav_owner_username);
        username = userName.getText().toString();

        addBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_field_btn:{

                name = (EditText) getActivity().findViewById(R.id.custom_name_in_reg);
                size = (EditText) getActivity().findViewById(R.id.textView_size_in_reg_custom);
                price = (EditText)  getActivity().findViewById(R.id.textView_price_in_custom_reg);
                availableDays = (EditText)  getActivity().findViewById(R.id.textView_available_day_in_custom_reg);
                availableTime = (EditText)  getActivity().findViewById(R.id.textView_available_time_in_custom_reg);

                if (name.getText() == null || size.getText() == null || price.getText() == null
                        || availableTime.getText() == null || availableDays.getText() == null)
                {
                    Toast.makeText(getActivity(), "Please fill all the fields ", Toast.LENGTH_SHORT).show();

                }

                DataBaseHelper db = new DataBaseHelper(getActivity());
              Boolean isSuccess =  db.fieldCreateUpdate(name.getText().toString(),price.getText().toString(),size.getText().toString(),
                       availableDays.getText().toString(),availableTime.getText().toString(),username,true);

               //show if success then go back to manage fields
                if(isSuccess)
                    Toast.makeText(getActivity(), "new Field is added Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Please Try again later", Toast.LENGTH_SHORT).show();

                //open the other fragment
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.content_owner, new OwnerManageFields());
                transaction.commit();



            }
        }
    }
}
