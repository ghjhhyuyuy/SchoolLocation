package com.example.ydx.findding_application_test1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ghjhh on 2018/1/4.
 */
//未用
public class DBAdapter extends SQLiteOpenHelper {
    public static final String LATITUDE = " latitude";
    public static final String LONTITUDE = " lontitude";
    private final static int VERSION = 1;
    private final static String DATABASE_NAME = "data.db";
    private final static String NAME ="location";
    public DBAdapter (Context context) {
  this(context, DATABASE_NAME, null, VERSION);
    }
    public DBAdapter (Context context, String name, SQLiteDatabase.CursorFactory factory,
  int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE " +
                NAME + "("+
                DBAdapter.LATITUDE+ " varchar,"+
                DBAdapter.LONTITUDE+ " varchar"+

                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
  db.execSQL("DROP TABLE IF EXISTS[launcher]");
  } else {
  return;
  }
  onCreate(db);

    }
}
