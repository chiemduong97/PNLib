package com.example.pnlib.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    public MyDatabase(@Nullable Context context) {
        super(context, "PNLib", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_PhieuMuon= "create table PhieuMuon (" +
                "id_phieumuon INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "id_user INTEGER NOT NULL, " +
                "id_tv INTEGER NOT NULL, " +
                "id_sach INTEGER NOT NULL, " +
                "ngay_thue INTEGER NOT NULL, " +
                "thang_thue INTEGER NOT NULL, " +
                "nam_thue INTEGER NOT NULL, " +
                "trang_thai INTEGER NOT NULL, " +
                "ngay_tra TEXT)";
        db.execSQL(table_PhieuMuon);

        String table_Sach= "create table Sach (" +
                "id_sach INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "ten_sach TEXT NOT NULL, " +
                "id_TG INTEGER NOT NULL, " +
                "id_NXB INTEGER NOT NULL, " +
                "id_loai INTEGER NOT NULL, " +
                "gia_thue REAL NOT NULL)";
        db.execSQL(table_Sach);

        String table_LoaiSach= "create table LoaiSach (" +
                "id_loai INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "ten_loai TEXT NOT NULL)";
        db.execSQL(table_LoaiSach);

        String table_TacGia= "create table TacGia (" +
                "id_TG INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "ten_TG TEXT NOT NULL)";
        db.execSQL(table_TacGia);

        String table_NXB= "create table NXB (" +
                "id_NXB INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "ten_NXB TEXT NOT NULL)";
        db.execSQL(table_NXB);

        String table_ThanhVien= "create table ThanhVien (" +
                "id_TV INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "ten_TV TEXT NOT NULL, " +
                "ngay_sinh TEXT NOT NULL, " +
                "dia_chi TEXT NOT NULL)";
        db.execSQL(table_ThanhVien);

        String table_User= "create table User (" +
                "id_user INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "ten_user TEXT, " +
                "email TEXT, " +
                "avatar INTEGER, " +
                "user_name TEXT NOT NULL, " +
                "password TEXT NOT NULL)" ;
        db.execSQL(table_User);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable1="DROP TABLE IF EXISTS PhieuMuon";
        db.execSQL(dropTable1);
        String dropTable2="DROP TABLE IF EXISTS Sach";
        db.execSQL(dropTable2);
        String dropTable3="DROP TABLE IF EXISTS LoaiSach";
        db.execSQL(dropTable3);
        String dropTable4="DROP TABLE IF EXISTS TacGia";
        db.execSQL(dropTable4);
        String dropTable5="DROP TABLE IF EXISTS NXB";
        db.execSQL(dropTable5);
        String dropTable6="DROP TABLE IF EXISTS ThanhVien";
        db.execSQL(dropTable6);
        String dropTable7="DROP TABLE IF EXISTS User";
        db.execSQL(dropTable7);
        onCreate(db);
    }
}
