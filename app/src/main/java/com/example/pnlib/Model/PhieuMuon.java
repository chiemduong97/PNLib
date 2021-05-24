package com.example.pnlib.Model;

public class PhieuMuon {
    private int maPM;
    private int maUser;
    private int maTV;
    private int maSach;
    private date ngayThue;
    private int trangThai;
    private String ngayTra;
    public PhieuMuon(){};

    public PhieuMuon(int maPM, int maUser, int maTV, int maSach, date ngayThue, int trangThai, String ngayTra) {
        this.maPM = maPM;
        this.maUser = maUser;
        this.maTV = maTV;
        this.maSach = maSach;
        this.ngayThue = ngayThue;
        this.trangThai = trangThai;
        this.ngayTra = ngayTra;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public date getNgayThue() {
        return ngayThue;
    }
    public String _getNgayThue(){
        return ngayThue.getNgay()+"/"+ngayThue.getThang()+"/"+ngayThue.getNam();
    }

    public void setNgayThue(date ngayThue) {
        this.ngayThue = ngayThue;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }
}
