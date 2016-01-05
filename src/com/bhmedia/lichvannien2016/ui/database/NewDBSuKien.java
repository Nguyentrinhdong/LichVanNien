package com.bhmedia.lichvannien2016.ui.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 3/9/2015.
 */
public class NewDBSuKien extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "sukien.db";
    public static final String TABLE_NAME = "tblsukien";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DAY_BEGIN = "day_begin";
    public static final String COLUMN_MONTH_BEGIN = "month_begin";
    public static final String COLUMN_YEAR_BEGIN = "year_begin";
    public static final String COLUMN_DAY_END = "day_end";
    public static final String COLUMN_MONTH_END = "month_end";
    public static final String COLUMN_YEAR_END = "year_end";
    public static final String COLUMN_TIME = "time";
    Context context;
    public SQLiteDatabase sqLiteDatabase;

    public NewDBSuKien(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    public void openDatabase(){
        sqLiteDatabase = this.getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table tblsukien " +
                        "(id integer primary key, name text, day_begin integer, month_begin integer, year_begin integer, day_end integer, month_end integer, year_end integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS tblsukien");
        onCreate(db);
    }


    public boolean checkSuKienByTime(int day, int month, int year) {
        sqLiteDatabase = getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from tblsukien where " + COLUMN_DAY_BEGIN + "<=" + day +
                " AND " + COLUMN_DAY_END + ">=" + day + " AND " + COLUMN_MONTH_BEGIN + "<=" + month +
                " AND " + COLUMN_MONTH_END + ">=" + month + " AND " + COLUMN_YEAR_BEGIN + "<=" + year +
                " AND " + COLUMN_YEAR_END + ">=" + year, null);
        
        
        if (res.moveToFirst()) {
            res.close();
            return true;
        } else {
            res.close();
            return false;
        }

    }

   public void closeDatabase(){
       if(sqLiteDatabase != null)
           sqLiteDatabase.close();
   }
}
