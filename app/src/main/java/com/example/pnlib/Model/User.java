package com.example.pnlib.Model;

public class User {
    private int maUser;
    private String hoTen;
    private String email;
    private String userName;
    private String passWord;
    private int avatar;
    public User() {
    }

    public User(int maUser, String hoTen, String email, String userName, String passWord, int avatar) {
        this.maUser = maUser;
        this.hoTen = hoTen;
        this.email = email;
        this.userName = userName;
        this.passWord = passWord;
        this.avatar=avatar;
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
