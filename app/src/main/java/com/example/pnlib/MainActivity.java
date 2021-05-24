package com.example.pnlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pnlib.Fragment.FragmentHome;
import com.example.pnlib.Fragment.FragmentPhieuMuon;
import com.example.pnlib.Fragment.FragmentQuanLySach;
import com.example.pnlib.Fragment.FragmentThanhVien;
import com.example.pnlib.Fragment.FragmentThongKe;
import com.example.pnlib.Fragment.FragmentThongKeDoanhThu;
import com.example.pnlib.Fragment.FragmentUser;
import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.Model.TacGia;
import com.example.pnlib.Model.User;
import com.example.pnlib.SQLite.LoaiSachDAO;
import com.example.pnlib.SQLite.TacGiaDAO;
import com.example.pnlib.SQLite.UserDAO;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Toolbar toolbar;
    UserDAO userDAO;
    ArrayList<User> users=new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        toolbar =  findViewById(R.id.toolbar);
        userDAO=new UserDAO(this);
        users=userDAO.getAllUser();

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,new FragmentHome()).commit();
        toolbar.setTitle("HOME");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                drawerLayout.closeDrawers();

                if (item.getItemId() == R.id.home) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout,new FragmentHome()).commit();
                    toolbar.setTitle("HOME");
                }
                else if (item.getItemId() == R.id.quanlyphieumuon) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout,new FragmentPhieuMuon()).commit();
                    toolbar.setTitle("QUẢN LÝ PHIẾU MƯỢN");
                }
                else if (item.getItemId() == R.id.quanlysach) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout,new FragmentQuanLySach()).commit();
                    toolbar.setTitle("QUẢN LÝ SÁCH");
                }
                else if (item.getItemId() == R.id.quanlythanhvien) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout,new FragmentThanhVien()).commit();
                    toolbar.setTitle("THÀNH VIÊN");
                }
                else if (item.getItemId() == R.id.quanlyuser) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout,new FragmentUser()).commit();
                    toolbar.setTitle("QUẢN LÝ TÀI KHOẢN");
                }
                else if (item.getItemId() == R.id.thongke) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout,new FragmentThongKe()).commit();
                    toolbar.setTitle("THỐNG KÊ");
                }
                else if (item.getItemId() == R.id.thoat){
                    Snackbar.make(findViewById(R.id.root),"Xác nhận thoát",5000)
                            .setActionTextColor(Color.RED)
                            .setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finishAffinity();
                                    System.exit(0);
                                }
                            })
                            .show();
                }
                return true;
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

}