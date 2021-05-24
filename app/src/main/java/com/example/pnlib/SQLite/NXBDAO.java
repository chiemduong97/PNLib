package com.example.pnlib.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Model.NXB;

import java.util.ArrayList;

public class NXBDAO {
    private SQLiteDatabase db;
    MyDatabase myDatabase;
    public NXBDAO(Context context){myDatabase=new MyDatabase(context);}

    public void ThemNXB(String tenNXB){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("ten_NXB",tenNXB);
        db.insert("NXB",null,values);
    }
    public void XoaNXB(int id_NXB){
        db=myDatabase.getWritableDatabase();
        String x[]=new String[]{id_NXB+""};
        db.delete("NXB","id_NXB=?",x);
    }

    public void SuaNXB(NXB nXB){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        String x[]=new String[]{nXB.getMaNXB()+""};
        values.put("id_NXB",nXB.getMaNXB());
        values.put("ten_NXB",nXB.getTenNXB());
        db.update("NXB",values,"id_NXB=?",x);
    }

    public ArrayList<NXB> getAllNXB(){
        db=myDatabase.getReadableDatabase();
        ArrayList<NXB> nXBs = new ArrayList<>();
        Cursor c = db.rawQuery("select * from NXB",null);
        if(c.moveToFirst())
        {
            do{
                NXB x=new NXB();
                x.setMaNXB(c.getInt(0));
                x.setTenNXB(c.getString(1));
                nXBs.add(x);
            }while (c.moveToNext());
        }
        return nXBs;
    }

   
}
