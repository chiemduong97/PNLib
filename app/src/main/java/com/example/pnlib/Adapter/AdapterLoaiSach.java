package com.example.pnlib.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.R;
import com.example.pnlib.SQLite.LoaiSachDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AdapterLoaiSach extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<LoaiSach>  loaiSaches;
    RecyclerView recyclerView;
    LoaiSachDAO loaiSachDAO;

    public AdapterLoaiSach(Context context, ArrayList<LoaiSach> loaiSaches, RecyclerView recyclerView){
        this.context=context;
        this.loaiSaches=loaiSaches;
        this.recyclerView=recyclerView;
    }

    public class LoaiSachViewHolder extends RecyclerView.ViewHolder{
        public TextView ma_LoaiSach;
        public TextView ten_LoaiSach;
        public ImageView sua_LoaiSach;
        public ImageView xoa_LoaiSach;

        public LoaiSachViewHolder(@NonNull View itemView) {
            super(itemView);
            ma_LoaiSach=(TextView)itemView.findViewById(R.id.tv_layout_LoaiSach_Ma);
            ten_LoaiSach=(TextView)itemView.findViewById(R.id.tv_layout_LoaiSach_Ten);
            sua_LoaiSach=(ImageView)itemView.findViewById(R.id.btnSuaLoaiSach);
            xoa_LoaiSach=(ImageView)itemView.findViewById(R.id.btnXoaLoaiSach);

        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.loaisach,parent,false);
        return new LoaiSachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LoaiSach loaiSach=loaiSaches.get(position);
        ((LoaiSachViewHolder)holder).ma_LoaiSach.setText("Mã loại sách: "+loaiSach.getMaLoaiSach());
        ((LoaiSachViewHolder)holder).ten_LoaiSach.setText("Tên loại sách: "+loaiSach.getTenLoaiSach());

        ((LoaiSachViewHolder)holder).xoa_LoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XoaLoaiSach(position);
            }
        });
        ((LoaiSachViewHolder)holder).sua_LoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaLoaiSach(position);
            }
        });
    }
    public void CapNhatLoaiSach(){
        loaiSaches= loaiSachDAO.getAllLoaiSach();
        AdapterLoaiSach adapterLoaiSach=new AdapterLoaiSach(context,loaiSaches,recyclerView);
        recyclerView.setAdapter(adapterLoaiSach);
    }

    public void XoaLoaiSach(int position){
        for(int i=0;i<loaiSaches.size();i++){
            try {
                if(position==i){
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_xac_nhan,null);
                    TextView xacnhan=view.findViewById(R.id.tvXacNhan);
                    xacnhan.setText("XÁC NHẬN XÓA LOẠI SÁCH");
                    builder.setView(view);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            loaiSachDAO=new LoaiSachDAO(context);
                            loaiSachDAO.XoaLoaiSach(loaiSaches.get(position).getMaLoaiSach());
                            CapNhatLoaiSach();
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
    public boolean CheckTenLoaiSach(String tenloaisach){
        boolean check=false;
        for(int i=0;i<loaiSaches.size();i++){
            if(tenloaisach.equalsIgnoreCase(loaiSaches.get(i).getTenLoaiSach())){
                check=true;
                break;
            }
        }
        return  check;
    }
    public void SuaLoaiSach(int position){
        for(int i=0;i<loaiSaches.size();i++){
            try{
                if(position==i){
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_sua_loai_sach,null);
                    builder.setView(view);
                    EditText SuaTenLoaiSach=view.findViewById(R.id.etSuaTenLoaiSach);
                    SuaTenLoaiSach.setText(loaiSaches.get(position).getTenLoaiSach());
                    TextView MaLoaiSach=view.findViewById(R.id.tvMaLoaiSach);
                    MaLoaiSach.setText("Mã loại sách: "+loaiSaches.get(position).getMaLoaiSach());
                    TextInputLayout textInputLayout=view.findViewById(R.id.textInputSuaLoaiSach);
                    textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if(s.length()==0){
                                textInputLayout.setError("Không được để trống");
                                textInputLayout.setErrorEnabled(true);
                            }
                            else
                                textInputLayout.setErrorEnabled(false);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try{
                                if (SuaTenLoaiSach.getText().toString().equals("")){
                                    Toast.makeText(context,"Không được để trống, sửa thất bại",Toast.LENGTH_SHORT).show();
                                }
                                else if(CheckTenLoaiSach(SuaTenLoaiSach.getText().toString())){
                                    Toast.makeText(context,"Đã có loại sách này, sửa thất bại",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    loaiSachDAO=new LoaiSachDAO(context);
                                    loaiSachDAO.SuaLoaiSach(new LoaiSach(loaiSaches.get(position).getMaLoaiSach(),SuaTenLoaiSach.getText().toString()));
                                    CapNhatLoaiSach();
                                    Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception ex){
                                Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
                            }

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
                Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
    @Override
    public int getItemCount() {
        return loaiSaches.size();
    }
}
