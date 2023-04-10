package com.example.asmduanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asmduanmau.Model.Sach;
import com.example.asmduanmau.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {

    DatabaseHelper databaseHelper;
    public SachDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);

    }
    public boolean insertSach (String tensach, int giatien, int maloai){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach", tensach);
        contentValues.put("giaThue", giatien);
        contentValues.put("maloai", maloai);
        long check = sqLiteDatabase.insert("SACH", null, contentValues);
        if (check == -1){
            return false;
        }
        return true;
    }
    public boolean updateSach (int masach, String tensach, int giathue, int maloai){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach", tensach);
        contentValues.put("giaThue", giathue);
        contentValues.put("maloai", maloai);
        long check = sqLiteDatabase.update("SACH", contentValues, "masach = ?", new String[]{String.valueOf(masach)});
        if (check == -1){
            return false;
        }
        return true;
    }


    public int deleteSach (int masach){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUMUON WHERE masach = ?", new String[]{String.valueOf(masach)});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = sqLiteDatabase.delete("SACH", "masach = ?", new String[]{String.valueOf(masach)});
        if (check == -1){
            return 0;
        }else {
            return 1;
        }
    }

    public List<Sach> getDSSach() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        List<Sach> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM SACH",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
            }while (cursor.moveToNext());

        }
        return list;
    }
}
