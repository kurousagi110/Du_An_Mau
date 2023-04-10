package com.example.asmduanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asmduanmau.Model.ThanhVien;
import com.example.asmduanmau.Model.ThuThu;
import com.example.asmduanmau.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    DatabaseHelper databaseHelper;

    public ThanhVienDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);

    }

    //them thanh vien
    public boolean insertTV(String hoten, String namsinh) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten", hoten);
        contentValues.put("namSinh", namsinh);
        long check = sqLiteDatabase.insert("THANHVIEN", null, contentValues);
        if (check == -1) {
            return false;
        }
        return true;
    }

    // sua thanh vien
    public boolean updateTV(int matv, String hoten, String namsinh) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten", hoten);
        contentValues.put("namSinh", namsinh);
        long check = sqLiteDatabase.update("THANHVIEN", contentValues, "matv = ?", new String[]{String.valueOf(matv)});
        if (check == -1) {
            return false;
        }
        return true;
    }

    // xoa thanh vien
    public int deleteTV(int matv) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUMUON WHERE matv = ?", new String[]{String.valueOf(matv)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("THANHVIEN", "matv = ?", new String[]{String.valueOf(matv)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }

    public ArrayList<ThanhVien> getDSThanhVien() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        ArrayList<ThanhVien> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM THANHVIEN", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());

        }
        return list;
    }

}
