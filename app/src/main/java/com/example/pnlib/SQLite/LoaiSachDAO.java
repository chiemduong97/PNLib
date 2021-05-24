package com.example.pnlib.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDAO {
    private SQLiteDatabase db;
    MyDatabase myDatabase;
    public LoaiSachDAO(Context context){myDatabase=new MyDatabase(context);}

    public void ThemLoaiSach(String tenLoaiSach){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("ten_loai",tenLoaiSach);
        db.insert("LoaiSach",null,values);
    }
    public void XoaLoaiSach(int id_loai){
        db=myDatabase.getWritableDatabase();
        String x[]=new String[]{id_loai+""};
        db.delete("LoaiSach","id_loai=?",x);
    }

    public void SuaLoaiSach(LoaiSach loaiSach){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        String x[]=new String[]{loaiSach.getMaLoaiSach()+""};
        values.put("ten_loai",loaiSach.getTenLoaiSach());
        db.update("LoaiSach",values,"id_loai=?",x);
    }

    public ArrayList<LoaiSach> getAllLoaiSach(){
        db=myDatabase.getReadableDatabase();
        ArrayList<LoaiSach> loaiSaches = new ArrayList<>();
        Cursor c = db.rawQuery("select * from LoaiSach",null);
        if(c.moveToFirst())
        {
            do{
                LoaiSach x=new LoaiSach();
                x.setMaLoaiSach(c.getInt(0));
                x.setTenLoaiSach(c.getString(1));
                loaiSaches.add(x);
            }while (c.moveToNext());
        }
        return loaiSaches;
    }

   
}
