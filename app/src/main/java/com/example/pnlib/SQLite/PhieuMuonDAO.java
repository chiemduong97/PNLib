package com.example.pnlib.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.Model.date;

import java.util.ArrayList;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    MyDatabase myDatabase;
    public PhieuMuonDAO(Context context){myDatabase=new MyDatabase(context);}

    public void ThemPhieuMuon(PhieuMuon phieuMuon){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id_user",phieuMuon.getMaUser());
        values.put("id_tv",phieuMuon.getMaTV());
        values.put("id_sach",phieuMuon.getMaSach());
        values.put("ngay_thue",phieuMuon.getNgayThue().getNgay());
        values.put("thang_thue",phieuMuon.getNgayThue().getThang());
        values.put("nam_thue",phieuMuon.getNgayThue().getNam());
        values.put("trang_thai",phieuMuon.getTrangThai());
        values.put("ngay_tra",phieuMuon.getNgayTra());
        db.insert("PhieuMuon",null,values);
    }
    public void XoaPhieuMuon(int id_phieumuon){
        db=myDatabase.getWritableDatabase();
        String x[]=new String[]{id_phieumuon+""};
        db.delete("PhieuMuon","id_phieumuon=?",x);
    }

    public void SuaPhieuMuon(PhieuMuon phieuMuon){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        String x[]=new String[]{phieuMuon.getMaPM()+""};
        values.put("id_user",phieuMuon.getMaUser());
        values.put("id_tv",phieuMuon.getMaTV());
        values.put("id_sach",phieuMuon.getMaSach());
        values.put("ngay_thue",phieuMuon.getNgayThue().getNgay());
        values.put("thang_thue",phieuMuon.getNgayThue().getThang());
        values.put("nam_thue",phieuMuon.getNgayThue().getNam());
        values.put("trang_thai",phieuMuon.getTrangThai());
        values.put("ngay_tra",phieuMuon.getNgayTra());
        db.update("PhieuMuon",values,"id_phieumuon=?",x);
    }

    public ArrayList<PhieuMuon> getAllPhieuMuon(){
        db=myDatabase.getReadableDatabase();
        ArrayList<PhieuMuon> phieuMuons = new ArrayList<>();
        Cursor c = db.rawQuery("select * from PhieuMuon",null);
        if(c.moveToFirst())
        {
            do{
                PhieuMuon x=new PhieuMuon();
                x.setMaPM(c.getInt(0));
                x.setMaUser(c.getInt(1));
                x.setMaTV(c.getInt(2));
                x.setMaSach(c.getInt(3));
                x.setNgayThue(new date(c.getInt(4),c.getInt(5),c.getInt(6)));
                x.setTrangThai(c.getInt(7));
                x.setNgayTra(c.getString(8));
                phieuMuons.add(x);
            }while (c.moveToNext());
        }
        return phieuMuons;
    }

    public ArrayList<PhieuMuon> getAllPhieuMuon(int id_user){
        db=myDatabase.getReadableDatabase();
        ArrayList<PhieuMuon> phieuMuons = new ArrayList<>();
        String a[]=new String[]{id_user+""};
        Cursor c = db.rawQuery("select * from PhieuMuon WHERE id_user=?",a);
        if(c.moveToFirst())
        {
            do{
                PhieuMuon x=new PhieuMuon();
                x.setMaPM(c.getInt(0));
                x.setMaUser(c.getInt(1));
                x.setMaTV(c.getInt(2));
                x.setMaSach(c.getInt(3));
                x.setNgayThue(new date(c.getInt(4),c.getInt(5),c.getInt(6)));
                x.setTrangThai(c.getInt(7));
                x.setNgayTra(c.getString(8));
                phieuMuons.add(x);
            }while (c.moveToNext());
        }
        return phieuMuons;
    }
}
