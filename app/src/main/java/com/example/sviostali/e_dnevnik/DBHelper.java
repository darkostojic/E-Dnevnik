package com.example.sviostali.e_dnevnik;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {


    public Context c;
    private static final String DATABASE_NAME = "EDnevnik.db";
    private static final int VERSION = 1;


    //Data for user table

    public static final String USER_TABLE = "user";
    public static final String USER_COL_1 = "id";
    public static final String USER_COL_2 = "login";
    public static final String USER_COL_3 = "password";
    public static final String USER_COL_4 = "avatar";
    public static final String USER_COL_5 = "professor";

    //Data for subject table

    public static final String SUBJECT_TABLE = "subject";
    public static final String SUBJECT_COL_1 = "id";
    public static final String SUBJECT_COL_2 = "subject_name";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //This function creates User table in database
        db.execSQL("CREATE TABLE " + USER_TABLE + " (" +
                "id integer primary key autoincrement not null, " +
                "login text not null," +
                "password text not null," +
                "avatar text not null," +
                "professor integer not null)");

        //This function creates Subject table in database

        db.execSQL("CREATE TABLE " + SUBJECT_TABLE + " (" +
                "id integer primary key autoincrement not null, " +
                "subject_name text not null)");

        addSubjectsToDB(db, c);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
    }


    //Function for inserting users into database

    public boolean insertUserData(String login, String password, String avatar, int professor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_2, login);
        contentValues.put(USER_COL_3, password);
        contentValues.put(USER_COL_4, avatar);
        contentValues.put(USER_COL_5, professor);

        try {
            db.insert(USER_TABLE, null, contentValues);
            db.close();
            Log.i("Return true", "Return true");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        /*if(result == -1){
            return false;
        } else {
            return true;
        }*/
    }

    //Function that returns all users from database

    public Cursor getUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor getDataNow = db.rawQuery("SELECT login" +
                " FROM " + USER_TABLE + " order by id asc", null);
        return getDataNow;
    }

    //Function for inserting subjects into database

    public boolean insertSubjects(String subject_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBJECT_COL_2, subject_name);

        long result = db.insert(SUBJECT_TABLE, null, contentValues);
        db.close();
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Function that returns all subjects from database

    public Cursor getSubjects(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor getDataNow = db.rawQuery("SELECT subject_name" +
                " FROM " + SUBJECT_TABLE + " order by id asc", null);
        return getDataNow;
    }

    public void addSubjectsToDB(SQLiteDatabase db, Context c){
        Resources res = c.getResources();
        String[] subjects = res.getStringArray(R.array.subjects);
        for(int i = 0; i<subjects.length;i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put(SUBJECT_COL_2, subjects[i]);

            long result = db.insert(SUBJECT_TABLE, null, contentValues);
        }

    }

}
