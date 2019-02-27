package com.example.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class MoneyDBHelper extends SQLiteOpenHelper {
    private final static int _DBVersion = 2;
    private final static String _DBName = "Note.db";
    private final static String _TableName = "MONEY";
    private final String SQL = "CREATE TABLE  " + _TableName + "( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "DATE TEXT, " +
            "ITEM TEXT," +
            "CONTENT TEXT," +
            "PRICE INTEGER," +
            "TYPE INTEGER" +
            ");";

    public MoneyDBHelper(Context context) {
        super(context, _DBName, null, _DBVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL = "DROP TABLE IF EXISTS " + _TableName;
        db.execSQL(SQL);
        onCreate(db);
    }

    public long insert(money_list moneyList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DATE", moneyList.getDate());
        values.put("ITEM", moneyList.getItem());
        values.put("CONTENT", moneyList.getContent());
        values.put("PRICE", moneyList.getPrice());
        values.put("TYPE", moneyList.getType());
        long rowid = db.insert(_TableName, null, values);
        return rowid;
    }

    public List<money_list> getMonth() {
        SQLiteDatabase db = getReadableDatabase();
        String [] colums = {_TableName,"id","DATE","ITEM","CONTENT","PRICE","TYPE"};
        Cursor cursor = db.query(_TableName,colums,null,null,null,null,null);

        List<money_list> money_lists = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            String item = cursor.getString(2);
            String content = cursor.getString(3);
            int price = cursor.getInt(4);
            int type = cursor.getInt(5);
            money_list money =new money_list(date,item,content,price,type);
            money_lists.add(money);
        }
        cursor.close();
        return money_lists;
    }

}
