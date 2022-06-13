package com.example.tascoo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbManager extends SQLiteOpenHelper {

    private static final String dbname = "Tascoo.db";


    public DbManager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table Users(Name String, Email String primary key, password TEXT)";
        System.out.println(query);
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);

    }
    public Boolean InstertUserData( String Name, String Email , String Password){
        SQLiteDatabase tascoo = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",Name );
        contentValues.put("Email",Email );
        contentValues.put("Password",Password );
        long results = tascoo.insert("Users" , null, contentValues);
if(results == -1 ) return false;
else
    return true;
    }

    public Boolean checkEmail (String Email) {

        SQLiteDatabase tascoo = this.getWritableDatabase();
        Cursor C = tascoo.rawQuery("Select * from Users where Email = ?", new String[] {Email});
        if (C.getCount()>0)
            return true;
        else
            return false;


    }
    public Boolean checkEmailpassword ( String Email,String passwrod){

        SQLiteDatabase tascoo=this.getWritableDatabase();
        Cursor C = tascoo.rawQuery("Select * from Users where Email  = ? and password = ?", new String[] {Email, passwrod});
        if (C.getCount()>0)
            return true;
        else
            return false;

    }

}
