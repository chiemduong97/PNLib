package com.example.pnlib.Model;

public class DanhMuc {
    private int img;
    private String ten;
    public DanhMuc(){

    }
    public DanhMuc(int img, String ten){
        this.img=img;
        this.ten=ten;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
