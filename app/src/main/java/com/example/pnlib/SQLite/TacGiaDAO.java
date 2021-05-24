package com.example.pnlib.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Model.TacGia;

import java.util.ArrayList;

public class TacGiaDAO {
    private SQLiteDatabase db;
    MyDatabase myDatabase;
    public TacGiaDAO(Context context){myDatabase=new MyDatabase(context);}

    public void ThemTacGia(String tenTG){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("ten_TG",tenTG);
        db.insert("TacGia",null,values);
    }
    public void XoaTacGia(int id_TG){
        db=myDatabase.getWritableDatabase();
        String x[]=new String[]{id_TG+""};
        db.delete("TacGia","id_TG=?",x);
    }

    public void SuaTacGia(TacGia tacGia){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        String x[]=new String[]{tacGia.getMaTG()+""};
        values.put("id_TG",tacGia.getMaTG());
        values.put("ten_TG",tacGia.getTenTG());
        db.update("TacGia",values,"id_TG=?",x);
    }

    public ArrayList<TacGia> getAllTacGia(){
        db=myDatabase.getReadableDatabase();
        ArrayList<TacGia> tacGias = new ArrayList<>();
        Cursor c = db.rawQuery("select * from TacGia",null);
        if(c.moveToFirst())
        {
            do{
                TacGia x=new TacGia();
                x.setMaTG(c.getInt(0));
                x.setTenTG(c.getString(1));
                tacGias.add(x);
            }while (c.moveToNext());
        }
        return tacGias;
    }

   
}
