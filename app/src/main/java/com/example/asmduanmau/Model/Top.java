package com.example.asmduanmau.Model;

public class Top {
    private String tenSach;
    private Integer soLuong;

    public Top() {
    }

    public Top(String tenSach, Integer soLuong) {
        this.tenSach = tenSach;
        this.soLuong = soLuong;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
}
