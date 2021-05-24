package com.example.pnlib.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pnlib.Model.DanhMuc;
import com.example.pnlib.R;

import java.util.ArrayList;

public class AdapterRecycleDanhMuc extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<DanhMuc> iconGioiThieus=new ArrayList<>();
    public AdapterRecycleDanhMuc(Context context, ArrayList<DanhMuc> iconGioiThieus){
        this.context=context;
        this.iconGioiThieus=iconGioiThieus;
    }
    public class DanhMucViewHolder extends RecyclerView.ViewHolder{
        public ImageView img_icon;
        public TextView ten_icon;
        public DanhMucViewHolder(@NonNull View itemView) {
            super(itemView);
            img_icon=(ImageView)itemView.findViewById(R.id.iConDanhMuc);
            ten_icon=(TextView)itemView.findViewById(R.id.TenDanhMuc);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.danh_muc,parent,false);
        return new DanhMucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DanhMuc danhMuc=iconGioiThieus.get(position);
        ((DanhMucViewHolder)holder).img_icon.setImageResource(danhMuc.getImg());
        ((DanhMucViewHolder)holder).ten_icon.setText(danhMuc.getTen());
    }

    @Override
    public int getItemCount() {
        return iconGioiThieus.size();
    }
}
