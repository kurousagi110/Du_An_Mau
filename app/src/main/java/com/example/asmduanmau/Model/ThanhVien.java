package com.example.asmduanmau.Model;

import java.util.Date;

public class ThanhVien {
    private Integer MaTV;
    private String HoTen;
    private String NamSinh;


    public ThanhVien(Integer maTV, String hoTen, String namSinh) {
        MaTV = maTV;
        HoTen = hoTen;
        NamSinh = namSinh;
    }

    public ThanhVien() {
    }

    public Integer getMaTV() {
        return MaTV;
    }

    public void setMaTV(Integer maTV) {
        MaTV = maTV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }
}
