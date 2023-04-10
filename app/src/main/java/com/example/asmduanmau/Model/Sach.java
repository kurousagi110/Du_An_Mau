package com.example.asmduanmau.Model;

public class Sach {
    private Integer MaSach;
    private String TenSach;
    private Integer GiaThue;
    private Integer MaLoaiSach;
    private Integer soLuongDaMuon;
    private String tenloai;

    public Sach() {
    }

    public Sach(Integer maSach, String tenSach, Integer giaThue, Integer maLoaiSach) {
        MaSach = maSach;
        TenSach = tenSach;
        GiaThue = giaThue;
        MaLoaiSach = maLoaiSach;
    }

    public Sach(Integer maSach, String tenSach, Integer soLuongDaMuon) {
        MaSach = maSach;
        TenSach = tenSach;
        this.soLuongDaMuon = soLuongDaMuon;
    }
    public Sach(Integer maSach, String tenSach, Integer giaThue, Integer maLoai, String tenloai) {
        this.MaSach = maSach;
        this.TenSach = tenSach;
        this.GiaThue = giaThue;
        this.MaLoaiSach = maLoai;
        this.tenloai = tenloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public Integer getSoLuongDaMuon() {
        return soLuongDaMuon;
    }

    public void setSoLuongDaMuon(Integer soLuongDaMuon) {
        this.soLuongDaMuon = soLuongDaMuon;
    }

    public Integer getMaSach() {
        return MaSach;
    }

    public void setMaSach(Integer maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public Integer getGiaThue() {
        return GiaThue;
    }

    public void setGiaThue(Integer giaThue) {
        GiaThue = giaThue;
    }

    public Integer getMaLoaiSach() {
        return MaLoaiSach;
    }

    public void setMaLoaiSach(Integer maLoaiSach) {
        MaLoaiSach = maLoaiSach;
    }
}
