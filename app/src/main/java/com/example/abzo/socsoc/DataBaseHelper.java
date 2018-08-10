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

    //owner table
    //TODO advertisment history
    private static final String OWNER_TABLE = "owner";
    private static final String OWNER_ID = "ID";
    private static final String OWNER_NAME = "name";
    private static final String OWNER_AGE = "age";
    private static final String OWNER_EMAIL = "email";
    private static final String OWNER_USERNAME = "username";
    private static final String OWNER_ADDRESS = "address";
    private static final String OWNER_FIELDS = "fields_number";

    //field table
    private static final String FIELD_TABLE = "field";
    private static final String FIELD_ID = "ID"; //unique and linked to a picture
    private static final String FIELD_NAME = "name";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_SIZE = "size";
    private static final String FIELD_DAYS = "days";
    private static final String FIELD_TIME = "time";
    private static final String FIELD_OWNER = "owner_id";


    //TODO change numeric values to integers
    //TODO add foriegn keys and fields table

    String CREATE_PLAYER = "CREATE TABLE "+ PLAYER_TABLE + "(" +COL0+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL1 +" TEXT,"+COL2 + " TEXT,"+COL3 +" TEXT,"+COL4 +" TEXT UNIQUE,"+COL5+" TEXT)";

    String CREATE_OWNER = "CREATE TABLE "+ OWNER_TABLE + "(" +OWNER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
            OWNER_NAME +" TEXT,"+OWNER_AGE + " TEXT,"+OWNER_EMAIL +" TEXT,"+OWNER_USERNAME +" TEXT UNIQUE,"
            + OWNER_ADDRESS+" TEXT," + OWNER_FIELDS+" TEXT)";

    String CREATE_FIELD = "CREATE TABLE "+ FIELD_TABLE + "(" +FIELD_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_NAME +" TEXT,"+FIELD_PRICE + " TEXT,"+FIELD_SIZE +" TEXT,"+FIELD_DAYS +" TEXT UNIQUE,"
            + FIELD_TIME+" TEXT,"+FIELD_OWNER+" INTEGER,"+" FOREIGN KEY ("+FIELD_OWNER+") REFERENCES "+OWNER_TABLE+" ("+OWNER_ID+"))";


    public DataBaseHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_OWNER);
        sqLiteDatabase.execSQL(CREATE_PLAYER);
        sqLiteDatabase.execSQL(CREATE_FIELD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    /**
     * updates ,creates based on username
     * @param   name
     * @param   age
     * @param   email
     * @param   username
     * @param   address
     * @param   create (1 for create ,0 for update)
     * @return true if  result success
     */
    public boolean playerCreateUpdate(String name, String age, String email, String username, String address, Boolean create) {

        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, age);
        contentValues.put(COL3, email);
        contentValues.put(COL4, username);
        contentValues.put(COL5, address);

        if(create == true)
            result = db.insert(PLAYER_TABLE, null, contentValues);
        else
            result = db.update(PLAYER_TABLE, contentValues, COL4 + "=" + username, null);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }




    /**
     * gets owner or player's username
     * @param  userName
     * @param  tableName
     * @return Cursor with user's data
     */
    public Cursor getDataFromUserName(String userName,String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + tableName + " WHERE " +COL4+" = '" +userName +"'";
        Cursor data = db.rawQuery(query, null);

        return data;
    }



    /**
     * updates ,creates based on username
     * @param   name
     * @param   age
     * @param   email
     * @param   username
     * @param   address
     * @param   fields
     * @param   create (1 for create ,0 for update)
     * @return true if  result success
     */
    public boolean ownerCreateUpdate(String name, String age, String email, String username, String address,String fields,Boolean create ) {

        long result;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OWNER_NAME, name);
        contentValues.put(OWNER_AGE, age);
        contentValues.put(OWNER_EMAIL, email);
        contentValues.put(OWNER_USERNAME, username);
        contentValues.put(OWNER_ADDRESS, address);
        contentValues.put(OWNER_FIELDS, fields);

        if(create == true)
            result = db.insert(OWNER_TABLE, null, contentValues);
        else
            result = db.update(OWNER_TABLE, contentValues, OWNER_USERNAME + " =" + username, null);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * gets owner id by owner username
     * @param  userName
     * @return Cursor with user's data
     */
    public Cursor getOwnerId(String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+OWNER_ID+" FROM " + OWNER_TABLE + " WHERE " +OWNER_USERNAME+" = '" +userName +"'";
        Cursor data = db.rawQuery(query, null);

        return data;
    }


    /**
     * updates ,creates based on ownerID with the field name
     * @param   name
     * @param   price
     * @param   size
     * @param   days
     * @param   time
     * @param   owner_id
     * @param   create (1 for create ,0 for update)
     * @return true if  result success
     */
    public boolean fieldCreateUpdate(String name, String price, String size, String days, String time,String owner_id,Boolean create ) {

        long result;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_PRICE, price);
        contentValues.put(FIELD_SIZE, size);
        contentValues.put(FIELD_DAYS, days);
        contentValues.put(FIELD_TIME, time);
        contentValues.put(FIELD_OWNER, owner_id);

        if(create == true)
            result = db.insert(FIELD_TABLE, null, contentValues);
        else
            result = db.update(FIELD_TABLE, contentValues, FIELD_OWNER + "=" + owner_id +" AND "+FIELD_NAME+"="+name, null);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * gets field by an id
     * @param  id
     * @return Cursor with user's data
     */
    public Cursor getFieldsByOwnerName(int  id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + FIELD_TABLE + " WHERE " +FIELD_OWNER+" = '" +id +"'";
        Cursor data = db.rawQuery(query, null);

        return data;
    }


    public Cursor getAllFields() {

        String query = "Select*FROM " + FIELD_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

}
