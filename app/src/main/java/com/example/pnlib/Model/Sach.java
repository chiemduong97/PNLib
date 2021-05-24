package com.example.pnlib.Model;

public class Sach {
    private int maSach;
    private String tenSach;
    private int maTG;
    private int maNXB;
    private int maLoai;
    private Double giaThue;
    public Sach(){};

    public Sach(int maSach, String tenSach, int maTG, int maNXB, int maLoai, Double giaThue) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maTG = maTG;
        this.maNXB = maNXB;
        this.maLoai = maLoai;
        this.giaThue = giaThue;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getMaTG() {
        return maTG;
    }

    public void setMaTG(int maTG) {
        this.maTG = maTG;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public Double getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(Double giaThue) {
        this.giaThue = giaThue;
    }
}
