package com.example.asmduanmau.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmduanmau.Model.Sach;
import com.example.asmduanmau.Model.Top;
import com.example.asmduanmau.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    DatabaseHelper databaseHelper;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    public ThongKeDAO (Context context){
        this.context = context;
        databaseHelper = new DatabaseHelper(context);

    }

    public List<Sach> getTop10(){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlTop = "SELECT pm.maSach, sc.tenSach, COUNT(pm.maSach) FROM PHIEUMUON pm, SACH sc WHERE pm.maSach = sc.maSach GROUP BY pm.maSach, sc.tenSach ORDER BY COUNT(pm.maSach) DESC LIMIT 10";
        List<Sach> list = new ArrayList<Sach>();
        SachDAO dao = new SachDAO(context);
        Cursor c = db.rawQuery(sqlTop,null);
        if (c.getCount()!=0){
            c.moveToFirst();
            do {
                list.add(new Sach(c.getInt(0),c.getString(1),c.getInt(2)));
            }while (c.moveToNext());
        }
        return list;
    }
    public int getDoanhThu (String ngaybatdau, String ngayketthuc){
        ngaybatdau = ngaybatdau.replace("/", "");
        ngayketthuc = ngayketthuc.replace("/", "");
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tienThue) FROM PHIEUMUON WHERE substr(ngayMuon,7)||substr(ngayMuon,4,2)||substr(ngayMuon,1,2) BETWEEN ? AND ?", new String[]{ngaybatdau, ngayketthuc});

        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }else {
            return 0;
        }
    }
}
