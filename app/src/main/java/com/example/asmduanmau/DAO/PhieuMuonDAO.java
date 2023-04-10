package com.example.asmduanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asmduanmau.Model.PhieuMuon;
import com.example.asmduanmau.Model.ThanhVien;
import com.example.asmduanmau.database.DatabaseHelper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhieuMuonDAO {
    DatabaseHelper databaseHelper;

    public PhieuMuonDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }
    public boolean insert(PhieuMuon obj) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("maTT", obj.getMaTT());
            values.put("maTV", obj.getMaTV());
            values.put("maSach", obj.getMaSach());
            values.put("tienThue", obj.getTienThue());
            values.put("ngayMuon", String.valueOf(obj.getNgayMuon()));
            values.put("traSach", obj.getTraSach());


            long check = database.insert("PHIEUMUON", null, values);
            if (check == -1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            Log.d("PHIEUMUON insert error: ", e.getMessage());
            return false;
        }
    }
    public boolean update(PhieuMuon obj) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("maTT", obj.getMaTT());
            values.put("maTV", obj.getMaTV());
            values.put("maSach", obj.getMaSach());
            values.put("tienThue", obj.getTienThue());
            values.put("ngayMuon", String.valueOf(obj.getNgayMuon()));
            values.put("traSach", obj.getTraSach());
            long check = database.update("PHIEUMUON", values, "maPM = ?", new String[]{String.valueOf(obj.getMaPM())});
            if (check == -1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            Log.d("PHIEUMUONDAO update error: ", e.getMessage());
            return false;
        }
    }


    public List<PhieuMuon> getData() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT pm.maPM, pm.maTV, tv.hoTen, pm.maTT, tt.hoTen, pm.masach, sc.tenSach, pm.ngayMuon, pm.traSach, pm.tienThue FROM PHIEUMUON pm,THANHVIEN tv, THUTHU tt, SACH sc WHERE pm.maTV = tv.maTV and pm.maTT = tt.maTT and pm.maSach = sc.maSach ORDER BY pm.maPM DESC",null);
        if (c.getCount()!=0){
            c.moveToFirst();
            do{
                list.add(new PhieuMuon(c.getInt(0),c.getInt(1),c.getString(2),c.getString(3),c.getString(4),c.getInt(5),c.getString(6),c.getString(7),c.getInt(8),c.getInt(9)));
            }while (c.moveToNext());
        }
        return list;
    }
    public boolean thayDoiTrangThai(int maPM){
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("traSach",1);
        long check =database.update("PHIEUMUON",values, "maPM= ?",new String[]{String.valueOf(maPM)});
        if (check==-1){
            return false;
        }else
            return true;
    }

    //"SELECT pm.maPM, pm.maTV, tv.hoTen, pm.maTT, tt.hoTen, pm.masach, sc.tenSach, pm.ngayMuon, pm.traSach, pm.tienThue FROM PHIEUMUON pm,THANHVIEN tv, THUTHU tt, SACH sc WHERE pm.maTV = tv.maTV and pm.maTT = tt.maTT and pm.maSach = sc.maSach";


//    public List<PhieuMuon> getAll() {
//        String sql = "SELECT pm.maPM, pm.maTV, tv.hoTen, pm.maTT, tt.hoTen, pm.masach, sc.tenSach, pm.ngayMuon, pm.traSach, pm.tienThue" +
//                "FROM PHIEUMUON pm,THANHVIEN tv, THUTHU tt, SACH sc " +
//                "WHERE pm.maTV = tv.maTV and pm.maTT = tt.maTT and pm.maSach = sc.maSach";
//        return getData(sql);
//    }
//
//    public PhieuMuon getID(String id) {
//        String sql = "SELECT * FROM PHIEUMUON WHERE maPM = ?";
//        List<PhieuMuon> list = getData(sql,id);
//        return list.get(0);
//    }
}
