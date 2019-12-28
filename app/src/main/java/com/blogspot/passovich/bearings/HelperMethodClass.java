package com.blogspot.passovich.bearings;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.io.IOException;

public class HelperMethodClass {
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private Cursor cursor;
    private static String TAG ="myLogs";

    void onCreateDBAPK(Context context, String nameDB){
        Log.d(TAG,"onCreateDB");
        dbHelper = new DBHelper(context, nameDB);
        try {dbHelper.createDataBase();}
        catch(IOException ioe){throw new Error("Unable to create DataBase");}
        try{dbHelper.openDataBase();}
        catch (SQLException sqle){throw sqle;}
        dbHelper.close();
    }
    boolean onViewDataFromFieldTable (Context context, String nameDB, String nameTableDB, int tekString,Ring ring,String selection){
        ////////-------открываем БД для считывания данных из таблицы---------///////
        dbHelper = new DBHelper(context,nameDB);
        try {database = dbHelper.getWritableDatabase(); Log.d(TAG,"writeDB");}
        catch (SQLiteException e){database = dbHelper.getReadableDatabase(); Log.d(TAG,"readDB");}
        ///////------читаем данные из поля таблицы-------///////
        cursor=database.query(nameTableDB,null,selection,null,null,null,null);
        if(cursor.moveToPosition(tekString)){
            ring.setName(cursor.getString(cursor.getColumnIndex("Name")));
            ring.setD (cursor.getString(cursor.getColumnIndex("d" )));
            ring.setD2(cursor.getString(cursor.getColumnIndex("d2")));
            ring.setD3(cursor.getString(cursor.getColumnIndex("d3")));
            ring.setS (cursor.getString(cursor.getColumnIndex("s" )));
        }
        else{return false;}
        cursor.close();dbHelper.close();database.close();
        return true;
    }
}
