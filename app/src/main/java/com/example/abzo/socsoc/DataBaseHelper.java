package com.example.abzo.socsoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.content.ContentValues.TAG;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String databaseName = "socsocDB";
    //player table
    private static final String PLAYER_TABLE = "player";
    private static final String COL0 = "ID";
    private static final String COL1 = "name";
    private static final String COL2 = "age";
    private static final String COL3 = "email";
    private static final String COL4 = "username";
    private static final String COL5 = "address";
    private static final String COL6 = "picture";

    //owner table
    //TODO advertisment history
    private static final String OWNER_TABLE = "owner";
    private static final String OWNER_ID = "ID";
    private static final String OWNER_NAME = "name";
    private static final String OWNER_AGE = "age";
    private static final String OWNER_EMAIL = "email";
    private static final String OWNER_USERNAME = "username";
    private static final String OWNER_ADDRESS = "address";
    private static final String OWNER_PICTURE = "picture";
    private static final String OWNER_FIELDS = "fields";

    //TODO change numeric values to integers
    //TODO add foriegn keys and fields table

    String CREATE_PLAYER = "CREATE TABLE "+ PLAYER_TABLE + "(" +COL0+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL1 +" TEXT,"+COL2 + " TEXT,"+COL3 +" TEXT,"+COL4 +" TEXT UNIQUE,"+COL5+" TEXT,"+COL6+" TEXT )";

    String CREATE_OWNER = "CREATE TABLE "+ OWNER_TABLE + "(" +OWNER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
            OWNER_NAME +" TEXT,"+OWNER_AGE + " TEXT,"+OWNER_EMAIL +" TEXT,"+OWNER_USERNAME +" TEXT UNIQUE,"
            + OWNER_ADDRESS+" TEXT," + OWNER_FIELDS+" TEXT,"+OWNER_PICTURE+" TEXT )";


    public DataBaseHelper(Context context) {
        super(context, databaseName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_OWNER);
        sqLiteDatabase.execSQL(CREATE_PLAYER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean saveDataPlayer(String name, String age, String email, String username, String address, String picture) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, age);
        contentValues.put(COL3, email);
        contentValues.put(COL4, username);
        contentValues.put(COL5, address);
        contentValues.put(COL6, picture);

        long result = db.insert(PLAYER_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean saveDataOwner(String name, String age, String email, String username, String address, String picture,String fields ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OWNER_NAME, name);
        contentValues.put(OWNER_AGE, age);
        contentValues.put(OWNER_EMAIL, email);
        contentValues.put(OWNER_USERNAME, username);
        contentValues.put(OWNER_ADDRESS, address);
        contentValues.put(OWNER_PICTURE, picture);
        contentValues.put(OWNER_FIELDS, fields);

        long result = db.insert(OWNER_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }



    /**
     * Returns all the data from database
     * return all data from an id  return an id from username
     * @return
     */
    public Cursor getDataFromName(String userName,String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + tableName + " WHERE " +COL4+" = '" +userName +"'";
        Cursor data = db.rawQuery(query, null);

        return data;
    }


//    public Cursor loadHandler() {
//
//        String query = "Select*FROM " + PLAYER_TABLE;
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.rawQuery(query, null);
//
//
//        return cursor;
//    }

}
