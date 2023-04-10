package com.example.asmduanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
        super(context, "DBHELPER", null, 9);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang thu thu
        String tblThuThu = "CREATE TABLE THUTHU (maTT text primary key , hoTen text not null, matKhau text not null)";
        db.execSQL(tblThuThu);
        db.execSQL("insert into THUTHU(maTT,hoTen, matKhau) values('user1','Pham Hoang Nam','123')");
        db.execSQL("insert into THUTHU(maTT,hoTen, matKhau) values('user2','Pham Van Ban','123')");
        //tao bang thanh vien
        String tblThanhVien = "CREATE TABLE THANHVIEN (maTV integer primary key autoincrement, hoTen text not null, namSinh integer not null)";
        db.execSQL(tblThanhVien);
        db.execSQL("insert into THANHVIEN(hoTen, namSinh) values('abc','123')");
        db.execSQL("insert into THANHVIEN(hoTen, namSinh) values('abcd','1235')");
        db.execSQL("insert into THANHVIEN(hoTen, namSinh) values('abce','1234')");
        db.execSQL("insert into THANHVIEN(hoTen, namSinh) values('abcf','1239')");
        //tao bang loai sach
        String tblLoaiSach = "CREATE TABLE LOAISACH (maLoai integer primary key autoincrement, tenLoai text not null)";
        db.execSQL(tblLoaiSach);

        //tao bang sach
        String tblSach = "CREATE TABLE SACH (maSach integer primary key autoincrement, tenSach text not null, giaThue integer not null, maLoai integer references LOAISACH(maLoai))";
        db.execSQL(tblSach);

        //tao bang phieu muon
        String tblPhieuMuon = "CREATE TABLE PHIEUMUON (maPM integer primary key autoincrement, maTT text references THUTHU(maTT), maTV integer references THANHVIEN(maTV), maSach integer references SACH(maSach), tienThue integer not null, ngayMuon text not null, traSach integer not null)";
        db.execSQL(tblPhieuMuon);

        db.execSQL("INSERT INTO LOAISACH(tenLoai) VALUES ( 'Thiếu nhi'),('Tình cảm'),( 'Giáo khoa')");
        db.execSQL("INSERT INTO SACH(tenSach, giaThue, maLoai) VALUES ( 'Hãy đợi đấy', 2500, 1), ( 'Thằng cuội', 1000, 1), ( 'Lập trình Android', 2000, 3)");
        db.execSQL("INSERT INTO THUTHU(maTT,hoTen, matKhau) VALUES ('thuthu01','Nguyễn Văn Anh','abc123'),('thuthu02','Trần Văn Hùng','123abc')");
        db.execSQL("INSERT INTO THANHVIEN(hoTen, namSinh) VALUES ('Cao Thu Trang','2000'),('Trần Mỹ Kim','2000')");
        //trả sách: 1: đã trả - 0: chưa trả
        db.execSQL("INSERT INTO PHIEUMUON(maTT, maTV, maSach, tienThue, ngayMuon, traSach) VALUES ('thuthu01',1, 1,2500, '19/03/2022', 1),('thuthu01',1, 3,2000, '19/03/2022', 0),('thuthu02',2, 1,2000, '19/03/2022', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            String dThuThu = "DROP TABLE IF EXISTS THUTHU";
            db.execSQL(dThuThu);
            String dThanhVien = "DROP TABLE IF EXISTS THANHVIEN";
            db.execSQL(dThanhVien);
            String dLoaiSach = "DROP TABLE IF EXISTS LOAISACH";
            db.execSQL(dLoaiSach);
            String dSach = "DROP TABLE IF EXISTS SACH";
            db.execSQL(dSach);
            String dPhieuMuon = "DROP TABLE IF EXISTS PHIEUMUON";
            db.execSQL(dPhieuMuon);

            onCreate(db);
        }
    }

}
