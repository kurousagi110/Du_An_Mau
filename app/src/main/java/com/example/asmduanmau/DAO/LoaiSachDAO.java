package com.example.asmduanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asmduanmau.Model.LoaiSach;
import com.example.asmduanmau.Model.Sach;
import com.example.asmduanmau.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    DatabaseHelper databaseHelper;
    public LoaiSachDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }
    public boolean insert(String tenLoai) {
        try {
            SQLiteDatabase database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("tenLoai",tenLoai);
            long check = database.insert("LOAISACH", null, values);
            if (check == -1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            Log.d("LOAISACH insert error: ", e.getMessage());
            return false;
        }
    }

    public boolean updateLoaiSach (int a, String tenLoaiSach){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", tenLoaiSach);
        long check = sqLiteDatabase.update("LOAISACH", contentValues, "maLoai = ?", new String[]{String.valueOf(a)});
        if (check == -1){
            return false;
        }
        return true;
    }

    public int xoaLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH WHERE maLoai = ?",new String[]{String.valueOf(id)});
        if(cursor.getCount()!=0){
            return -1;
        }
        long check = sqLiteDatabase.delete("LOAISACH","maLoai = ?", new String[]{String.valueOf(id)});
        if (check ==-1){
            return 0;
        }
        return 1;
    }

   public List<LoaiSach> getDSLoaiSach(){
        List<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM LOAISACH",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
   }
}
