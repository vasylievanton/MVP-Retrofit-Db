package com.task.vasilyevanton.task;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.task.vasilyevanton.task.pojos.PeopleData;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usersManager";
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_AVATAR_URL = "avatar_url";
    private static final String KEY_URL = "url";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_AVATAR_URL + " TEXT,"
                + KEY_URL + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        onCreate(db);
    }

    public void addPeoples(ArrayList<PeopleData> users) {
        deleteAll();
        SQLiteDatabase db = this.getWritableDatabase();
        for (PeopleData data: users) {
            ContentValues values = new ContentValues();
            values.put(KEY_AVATAR_URL, data.getAvatarUrl());
            values.put(KEY_URL, data.getHtmlUrl());
            db.insert(TABLE_USERS, null, values);
        }
        db.close();
    }

    public ArrayList<PeopleData> getPeoples() {
        ArrayList<PeopleData> contactList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                PeopleData contact = new PeopleData();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setAvatarUrl(cursor.getString(1));
                contact.setHtmlUrl(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }


    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, null, null);
        db.close();
    }

    public int getPeopleCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}

