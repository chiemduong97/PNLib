package com.example.pnlib.Model;

public class TacGia {
    private int maTG;
    private String tenTG;

    public TacGia() {
    }

    public TacGia(int maTG, String tenTG) {
        this.maTG = maTG;
        this.tenTG = tenTG;
    }

    public int getMaTG() {
        return maTG;
    }

    public void setMaTG(int maTG) {
        this.maTG = maTG;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }
}
