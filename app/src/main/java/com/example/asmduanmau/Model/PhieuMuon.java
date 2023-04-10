package com.example.asmduanmau.Model;

import java.util.Date;

public class PhieuMuon {
    private Integer MaPM;
    private String MaTT;
    private Integer MaTV;
    private Integer MaSach;
    private Integer TienThue;
    private String NgayMuon;
    private Integer TraSach;
    private String tenTV;
    private String tenTT;
    private String tenSach;

    public PhieuMuon() {
    }
    // pm.maPM, pm.maTV, tv.hoTen, pm.maTT, tt.hoTen, pm.masach, sc.tenSach, pm.ngayMuon, pm.traSach, pm.tienThue
    public PhieuMuon(Integer maPM, Integer maTV, String tenTV, String maTT, String tenTT, Integer maSach, String tenSach, String ngayMuon, Integer traSach, Integer tienThue) {
        this.MaPM = maPM;
        this.MaTT = maTT;
        this.MaTV = maTV;
        this.MaSach = maSach;
        this.TienThue = tienThue;
        this.NgayMuon = ngayMuon;
        this.TraSach = traSach;
        this.tenTV = tenTV;
        this.tenTT = tenTT;
        this.tenSach = tenSach;
    }

    public PhieuMuon(Integer maTV, String maTT, Integer maSach, String ngayMuon, Integer traSach, Integer tienThue) {
        MaTT = maTT;
        MaTV = maTV;
        MaSach = maSach;
        TienThue = tienThue;
        NgayMuon = ngayMuon;
        TraSach = traSach;
    }

    public Integer getMaPM() {
        return MaPM;
    }

    public void setMaPM(Integer maPM) {
        MaPM = maPM;
    }

    public String getMaTT() {
        return MaTT;
    }

    public void setMaTT(String maTT) {
        MaTT = maTT;
    }

    public Integer getMaTV() {
        return MaTV;
    }

    public void setMaTV(Integer maTV) {
        MaTV = maTV;
    }

    public Integer getMaSach() {
        return MaSach;
    }

    public void setMaSach(Integer maSach) {
        MaSach = maSach;
    }

    public Integer getTienThue() {
        return TienThue;
    }

    public void setTienThue(Integer tienThue) {
        TienThue = tienThue;
    }

    public String getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        NgayMuon = ngayMuon;
    }

    public Integer getTraSach() {
        return TraSach;
    }

    public void setTraSach(Integer traSach) {
        TraSach = traSach;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
