package com.example.asmduanmau.Model;

public class LoaiSach {
    private Integer MaLoaiSach;
    private String TenLoaiSach;

    public LoaiSach() {
    }

    public LoaiSach(Integer maLoaiSach, String tenLoaiSach) {
        MaLoaiSach = maLoaiSach;
        TenLoaiSach = tenLoaiSach;
    }

    public LoaiSach(String tenLoaiSach) {
        TenLoaiSach = tenLoaiSach;
    }

    public Integer getMaLoaiSach() {
        return MaLoaiSach;
    }

    public void setMaLoaiSach(Integer maLoaiSach) {
        MaLoaiSach = maLoaiSach;
    }

    public String getTenLoaiSach() {
        return TenLoaiSach;
    }

    public void setTenLoaiSach(String tenLoaiSach) {
        TenLoaiSach = tenLoaiSach;
    }
}
