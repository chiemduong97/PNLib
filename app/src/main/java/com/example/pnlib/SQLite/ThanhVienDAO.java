package com.example.pnlib.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDAO {
    private SQLiteDatabase db;
    MyDatabase myDatabase;
    public ThanhVienDAO(Context context){myDatabase=new MyDatabase(context);}

    public void ThemThanhVien(ThanhVien thanhVien){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("ten_TV",thanhVien.getTenTV());
        values.put("dia_chi",thanhVien.getDiaChi());
        values.put("ngay_sinh",thanhVien.getNgaySinh());
        db.insert("ThanhVien",null,values);
    }
    public void XoaThanhVien(int id_TV){
        db=myDatabase.getWritableDatabase();
        String x[]=new String[]{id_TV+""};
        db.delete("ThanhVien","id_TV=?",x);
    }

    public void SuaThanhVien(ThanhVien thanhVien){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        String x[]=new String[]{thanhVien.getMaTV()+""};
        values.put("ten_TV",thanhVien.getTenTV());
        values.put("dia_chi",thanhVien.getDiaChi());
        values.put("ngay_sinh",thanhVien.getNgaySinh());
        db.update("ThanhVien",values,"id_TV=?",x);
    }

    public ArrayList<ThanhVien> getAllThanhVien(){
        db=myDatabase.getReadableDatabase();
        ArrayList<ThanhVien> thanhViens = new ArrayList<>();
        Cursor c = db.rawQuery("select * from ThanhVien",null);
        if(c.moveToFirst())
        {
            do{
                ThanhVien x=new ThanhVien();
                x.setMaTV(c.getInt(0));
                x.setTenTV(c.getString(1));
                x.setNgaySinh(c.getString(2));
                x.setDiaChi(c.getString(3));
                thanhViens.add(x);
            }while (c.moveToNext());
        }
        return thanhViens;
    }


}
