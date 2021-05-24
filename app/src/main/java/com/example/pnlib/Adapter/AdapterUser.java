package com.example.pnlib.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Model.User;
import com.example.pnlib.R;
import com.example.pnlib.SQLite.UserDAO;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<User>  users;
    RecyclerView recyclerView;
    UserDAO userDAO;
    public AdapterUser(Context context, ArrayList<User> users, RecyclerView recyclerView){
        this.context=context;
        this.users=users;
        this.recyclerView=recyclerView;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        public TextView ma_User;
        public TextView taikhoan;
        public ImageView xoa_User;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ma_User=(TextView)itemView.findViewById(R.id.tv_layout_User_Ma);
            taikhoan=(TextView)itemView.findViewById(R.id.tv_layout_User_TaiKhoan);
            xoa_User=(ImageView)itemView.findViewById(R.id.btnXoaUser);

        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user=users.get(position);
        ((UserViewHolder)holder).ma_User.setText("Mã user: " +user.getMaUser());
        ((UserViewHolder)holder).taikhoan.setText("Tên tài khoản: "+user.getUserName());
        
        ((UserViewHolder)holder).xoa_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XoaUser(position);
            }
        });
    }
    public void CapNhatUser(){
        users= userDAO.getAllUser();
        AdapterUser adapterUser=new AdapterUser(context,users,recyclerView);
        recyclerView.setAdapter(adapterUser);
    }

    public void XoaUser(int position){
        for(int i=0;i<users.size();i++){
            try {
                if(position==i){
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_xac_nhan,null);
                    TextView xacnhan=view.findViewById(R.id.tvXacNhan);
                    xacnhan.setText("XÁC NHẬN XÓA USER");
                    builder.setView(view);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userDAO=new UserDAO(context);
                            userDAO.XoaUser(users.get(position).getMaUser());
                            CapNhatUser();
                            Toast.makeText(context,"Xóa thành công!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();

                }
            }catch (Exception ex){
                Toast.makeText(context,"Xóa thất bại",Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
    @Override
    public int getItemCount() {
        return users.size();
    }
}
