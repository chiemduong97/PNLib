package com.example.pnlib.Model;

public class ThanhVien {
    private int maTV;
    private String tenTV;
    private String ngaySinh;
    private String diaChi;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String tenTV, String ngaySinh, String diaChi) {
        this.maTV = maTV;
        this.tenTV = tenTV;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
