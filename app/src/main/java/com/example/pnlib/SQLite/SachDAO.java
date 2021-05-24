package com.example.pnlib.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Model.Sach;

import java.util.ArrayList;

public class SachDAO {
    private SQLiteDatabase db;
    MyDatabase myDatabase;
    public SachDAO(Context context){myDatabase=new MyDatabase(context);}

    public void ThemSach(Sach sach){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("ten_sach",sach.getTenSach());
        values.put("id_TG",sach.getMaTG());
        values.put("id_NXB",sach.getMaNXB());
        values.put("id_loai",sach.getMaLoai());
        values.put("gia_thue",sach.getGiaThue());
        db.insert("Sach",null,values);
    }
    public void XoaSach(int id_sach){
        db=myDatabase.getWritableDatabase();
        String x[]=new String[]{id_sach+""};
        db.delete("Sach","id_sach=?",x);
    }

    public void SuaSach(Sach sach){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        String x[]=new String[]{sach.getMaSach()+""};
        values.put("ten_sach",sach.getTenSach());
        values.put("id_TG",sach.getMaTG());
        values.put("id_NXB",sach.getMaNXB());
        values.put("id_loai",sach.getMaLoai());
        values.put("gia_thue",sach.getGiaThue());
        db.update("Sach",values,"id_sach=?",x);
    }

    public ArrayList<Sach> getAllSach(){
        db=myDatabase.getReadableDatabase();
        ArrayList<Sach> saches = new ArrayList<>();
        Cursor c = db.rawQuery("select * from Sach",null);
        if(c.moveToFirst())
        {
            do{
                Sach x=new Sach();
                x.setMaSach(c.getInt(0));
                x.setTenSach(c.getString(1));
                x.setMaTG(c.getInt(2));
                x.setMaNXB(c.getInt(3));
                x.setMaLoai(c.getInt(4));
                x.setGiaThue(c.getDouble(5));
                saches.add(x);
            }while (c.moveToNext());
        }
        return saches;
    }


}
