package com.example.pnlib;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.Model.NXB;
import com.example.pnlib.Model.Sach;
import com.example.pnlib.Model.TacGia;
import com.example.pnlib.Model.ThanhVien;
import com.example.pnlib.Model.User;
import com.example.pnlib.SQLite.LoaiSachDAO;
import com.example.pnlib.SQLite.NXBDAO;
import com.example.pnlib.SQLite.SachDAO;
import com.example.pnlib.SQLite.TacGiaDAO;
import com.example.pnlib.SQLite.ThanhVienDAO;
import com.example.pnlib.SQLite.UserDAO;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LoaiSachMau();
        TacGiaMau();
        NXBMau();
        SachMau();
        ThanhVienMau();


        UserDAO userDAO=new UserDAO(this);
        ArrayList<User> users=userDAO.getAllUser();
        if(users.size()==0)
            userDAO.ThemUser(new User(1,"Đỗ Chiếm Dương", "chiemduong.dp.cntt@gmail.com","chiemduong97","emtenduong97",R.drawable.avt1));
        CountDownTimer countDownTimer=new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }.start();

    }
    public void LoaiSachMau(){
        LoaiSachDAO loaiSachDAO=new LoaiSachDAO(this);
        ArrayList<LoaiSach> loaiSaches=loaiSachDAO.getAllLoaiSach();
        if(loaiSaches.size()==0){
            loaiSachDAO.ThemLoaiSach("Kinh tế");
            loaiSachDAO.ThemLoaiSach("Ngoại ngữ");
            loaiSachDAO.ThemLoaiSach("Công nghệ thông tin");
            loaiSachDAO.ThemLoaiSach("Ẩm thực");
            loaiSachDAO.ThemLoaiSach("Sức khỏe");
            loaiSachDAO.ThemLoaiSach("Chính trị");
            loaiSachDAO.ThemLoaiSach("Toán học");
        }
    }
    public void TacGiaMau(){
        TacGiaDAO tacGiaDAO=new TacGiaDAO(this);
        ArrayList<TacGia> tacGiaes=tacGiaDAO.getAllTacGia();
        if(tacGiaes.size()==0){
            tacGiaDAO.ThemTacGia("Tô Hoài");
            tacGiaDAO.ThemTacGia("Ngô Tất Tố");
            tacGiaDAO.ThemTacGia("Phạm Huy Hoàng");
            tacGiaDAO.ThemTacGia("Nguyễn Hữu Hưng");
            tacGiaDAO.ThemTacGia("Lê Trường Thông");
            tacGiaDAO.ThemTacGia("Dương Thăng Long");
            tacGiaDAO.ThemTacGia("Nguyễn Văn Ất");
        }
    }
    public void NXBMau(){
        NXBDAO nXBDAO=new NXBDAO(this);
        ArrayList<NXB> nXBs=nXBDAO.getAllNXB();
        if(nXBs.size()==0){
            nXBDAO.ThemNXB("Kim Đồng");
            nXBDAO.ThemNXB("DHQG TPHCM");
            nXBDAO.ThemNXB("Trẻ");
            nXBDAO.ThemNXB("Tổng hợp TPHCM");
            nXBDAO.ThemNXB("Giáo dục");
            nXBDAO.ThemNXB("Hội nhà văn");
            nXBDAO.ThemNXB("Thông tin và truyền thông");
        }
    }
    public void SachMau(){
        SachDAO sachDAO=new SachDAO(this);
        ArrayList<Sach> saches=sachDAO.getAllSach();
        if(saches.size()==0){
            sachDAO.ThemSach(new Sach(1,"Buôn bán",1,1,1,10000.0));
            sachDAO.ThemSach(new Sach(2,"Tiếng Anh",2,2,2,10000.0));
            sachDAO.ThemSach(new Sach(3,"Photoshop",3,3,3,10000.0));
            sachDAO.ThemSach(new Sach(4,"Nấu ăn",4,4,4,10000.0));
            sachDAO.ThemSach(new Sach(5,"Thể dục",5,5,5,10000.0));
            sachDAO.ThemSach(new Sach(6,"Chính trị",6,6,6,10000.0));
            sachDAO.ThemSach(new Sach(7,"Giải tích",7,7,7,10000.0));
        }
    }
    public void ThanhVienMau(){
        ThanhVienDAO thanhVienDAO=new ThanhVienDAO(this);
        ArrayList<ThanhVien> thanhViens=thanhVienDAO.getAllThanhVien();
        if(thanhViens.size()==0){
            thanhVienDAO.ThemThanhVien(new ThanhVien(1,"Đỗ Chiếm Dương","05/09/1997","Quận 9"));
            thanhVienDAO.ThemThanhVien(new ThanhVien(2,"Nguyễn Mạnh Đức","20/09/2000","Gò Vấp"));
            thanhVienDAO.ThemThanhVien(new ThanhVien(3,"Lê Cao Anh Kiệt","07/11/1998","Tân Bình"));
            thanhVienDAO.ThemThanhVien(new ThanhVien(4,"Hoàng Anh Tú","01/07/1997","Tân Phú"));
            thanhVienDAO.ThemThanhVien(new ThanhVien(5,"Đặng Hoàng Huy","21/04/1993","Bình Thạnh"));
            thanhVienDAO.ThemThanhVien(new ThanhVien(6,"Phan Hồng Quân","05/11/1997","Phú Nhuận"));
            thanhVienDAO.ThemThanhVien(new ThanhVien(7,"Trần Phước Sanh","15/02/1995","Quận 1"));
        }
    }
}
