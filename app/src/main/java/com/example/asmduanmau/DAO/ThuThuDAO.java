package com.example.asmduanmau.DAO;

import android.annotation.SuppressLint;
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

public class ThuThuDAO {
    DatabaseHelper databaseHelper;
    public ThuThuDAO(Context context) {
         databaseHelper = new DatabaseHelper(context);
    }

    public boolean insert(ThuThu obj) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("MaTT", obj.getMaTT());
            values.put("hoTen", obj.getHoTen());
            values.put("matKhau", obj.getMatKhau());
            long check = database.insert("THUTHU", null, values);
            if (check == -1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {

            return false;
        }
    }

    public boolean update(String username, String oldPass, String newPass) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM THUTHU WHERE maTT = ? and matKhau = ?",new String[]{username,oldPass});
        if (cursor.getCount()>0){
            ContentValues values = new ContentValues();
            values.put("matKhau", newPass);
            long check = database.update("THUTHU",values,"maTT = ?",new String[]{username});
            if (check == -1){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        try {
            long check = database.delete("THUTHU", "maTT = ?", new String[]{id});
            if (check == -1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {

            return false;
        }
    }

    private List<ThuThu> getData(String sql, String... selectionArgs) {
        List<ThuThu> list = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor c = database.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThuThu obj = new ThuThu();

            String maTT = (c.getString(c.getColumnIndexOrThrow("maTT")));
            obj.setMaTT(maTT);
            String hoTen = c.getString(c.getColumnIndexOrThrow("hoTen"));
            obj.setHoTen(hoTen);
            String matKhau = c.getString(c.getColumnIndexOrThrow("matKhau"));
            obj.setMatKhau(matKhau);
            list.add(obj);
        }
        return list;
    }

    public List<ThuThu> getAll() {
        String sql = "SELECT * FROM THUTHU";
        return getData(sql);
    }

    public ThuThu getID(String id) {
        String sql = "SELECT * FROM THANHVIEN WHERE maTT = ?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }
    public boolean login(String maTT, String matKhau){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        String query = "select * from THUTHU where maTT = ? and matKhau= ?";
        Cursor cursor = database.rawQuery(query, new String[]{ maTT, matKhau});
        if (cursor.getCount()!=0){
            return true;
        }else
            return false;
    }
}
