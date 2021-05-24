package com.example.pnlib.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Model.User;

import java.util.ArrayList;

public class UserDAO {
    private SQLiteDatabase db;
    MyDatabase myDatabase;
    public UserDAO(Context context){myDatabase=new MyDatabase(context);}

    public void ThemUser(User user){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("ten_user",user.getHoTen());
        values.put("email",user.getEmail());
        values.put("avatar",user.getAvatar());
        values.put("user_name",user.getUserName());
        values.put("password",user.getPassWord());
        db.insert("User",null,values);
    }
    public void XoaUser(int id_user){
        db=myDatabase.getWritableDatabase();
        String x[]=new String[]{id_user+""};
        db.delete("User","id_user=?",x);
    }

    public void SuaUser(User user){
        db=myDatabase.getWritableDatabase();
        ContentValues values=new ContentValues();
        String x[]=new String[]{user.getMaUser()+""};
        values.put("ten_user",user.getHoTen());
        values.put("email",user.getEmail());
        values.put("avatar",user.getAvatar());
        values.put("user_name",user.getUserName());
        values.put("password",user.getPassWord());
        db.update("User",values,"id_user=?",x);
    }

    public ArrayList<User> getAllUser(){
        db=myDatabase.getReadableDatabase();
        ArrayList<User> users = new ArrayList<>();
        Cursor c = db.rawQuery("select * from User",null);
        if(c.moveToFirst())
        {
            do{
                User x=new User();
                x.setMaUser(c.getInt(0));
                x.setHoTen(c.getString(1));
                x.setEmail(c.getString(2));
                x.setAvatar(c.getInt(3));
                x.setUserName(c.getString(4));
                x.setPassWord(c.getString(5));
                users.add(x);
            }while (c.moveToNext());
        }
        return users;
    }


}
