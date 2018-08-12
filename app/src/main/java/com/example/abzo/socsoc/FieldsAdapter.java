package com.example.abzo.socsoc;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FieldsAdapter extends RecyclerView.Adapter<FieldsAdapter.FieldViewHolder> {

    private Context context;
    private int mNumberOfItems;
    private Cursor mCursor;

    public FieldsAdapter(int numberOfItems,Cursor cursor){

        mNumberOfItems = numberOfItems;
        mCursor = cursor;
        //also cursor to be checked
    }
    @NonNull
    @Override
    //it creates a view ,returns a view..inflates the view
    public FieldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_owner_manage_fields,parent,false);
        FieldViewHolder viewHolder = new FieldViewHolder(view); //holds the view we inflated in the view holder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FieldViewHolder holder, int position) {
        holder.bind(mCursor);

    }

    @Override
    public int getItemCount() {
        return mNumberOfItems;
    }

    class FieldViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView size;
        ImageView fieldPic;


        public FieldViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_manage);
            size = (TextView) itemView.findViewById(R.id.size_manage);
            fieldPic = (ImageView) itemView.findViewById(R.id.image_manage);
        }


        void bind(Cursor cursor) {
            if(cursor!= null && cursor.moveToFirst()) {

                size.setText(cursor.getString(cursor.getColumnIndex("size")));
                name.setText(cursor.getString(cursor.getColumnIndex("name")));
                Log.d("hello","adapter is cool");
            }else {
                Log.d("hello","adapter very sad");
            }
        }
    }
}
