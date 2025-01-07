package com.gaurav.dbdemo1.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.gaurav.dbdemo1.model.Contact;
import com.gaurav.dbdemo1.params.Params;

public class MyDbHandler extends SQLiteOpenHelper {
    private static final String TAG = "MyDbHandler";

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEYS_ID + " INTEGER PRIMARY KEY, "
                + Params.KEYS_NAME + " TEXT, "
                + Params.KEY_PHONE + " TEXT)";
        Log.d(TAG, "Query has been created: " + Create);
        db.execSQL(Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Params.TABLE_NAME);
        onCreate(db);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(Params.KEYS_NAME, contact.getName());
            values.put(Params.KEY_PHONE, contact.getPhone_number());

            db.insert(Params.TABLE_NAME, null, values);
            Log.d(TAG, "Successfully inserted");
        } catch (Exception e) {
            Log.e(TAG, "Error inserting contact: " + e.getMessage());
        } finally {
            db.close();
        }
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase(); // this is used to read the data from the database

        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst()) {         // the cursor moves to the first row of the table
            do {
                Contact contact = new Contact(); // creating a contact object
                contact.setId(Integer.parseInt(cursor.getString(0))); // setting the id of the contact & .parseInt is used to convert string to int
                contact.setName(cursor.getString(1)); // setting the name of the contact
                contact.setPhone_number(cursor.getString(2)); //setting the phone number of the contact
                contactList.add(contact);   //adding the contact to the list
            } while (cursor.moveToNext()); // as its a do while loop is it is used to move to the next row of the table
        }

        cursor.close();
        db.close();

        return contactList;
    }

    public int updateContacts(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEYS_ID,contact.getId());
        values.put(Params.KEYS_NAME,contact.getName());
        values.put(Params.KEY_PHONE,contact.getPhone_number());

        //Update code now
        return db.update(Params.TABLE_NAME,values,Params.KEYS_ID + "=?",
                new String[]{String.valueOf(contact.getId())});


    }

//    Deleting ContactBY Id , its a step 1` to make the function and perform the action in main activity
    public void DeleteContactById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEYS_ID + "=?",
                new String[] {String.valueOf(id)});
        db.close();
    }

//    Deleting the Contact By Pasing Conatct
    public void DeleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEYS_ID + "=?",
                new String[] {String.valueOf(contact.getId())});
        db.close(); // db queries to close !
    }

    public int getCount(){
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select,null);
        return cursor.getCount();
    }
}





























