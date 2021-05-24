package com.example.pnlib.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.Model.NXB;
import com.example.pnlib.Model.Sach;
import com.example.pnlib.Model.TacGia;
import com.example.pnlib.R;
import com.example.pnlib.SQLite.LoaiSachDAO;
import com.example.pnlib.SQLite.NXBDAO;
import com.example.pnlib.SQLite.SachDAO;
import com.example.pnlib.SQLite.TacGiaDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AdapterSach extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Sach>  saches;
    RecyclerView recyclerView;
    SachDAO sachDAO;
    LoaiSachDAO loaiSachDAO;
    TacGiaDAO tacGiaDAO;
    NXBDAO nXBDAO;
    public AdapterSach(Context context, ArrayList<Sach> saches, RecyclerView recyclerView){
        this.context=context;
        this.saches=saches;
        this.recyclerView=recyclerView;
    }

    public class SachViewHolder extends RecyclerView.ViewHolder{
        public TextView ma_sach;
        public TextView ten_sach;
        public TextView tg;
        public TextView nxb;
        public TextView loai;
        public TextView gia_thue;
        public ImageView sua_sach;
        public ImageView xoa_sach;

        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            ma_sach=(TextView)itemView.findViewById(R.id.tv_layout_Sach_Ma);
            ten_sach=(TextView)itemView.findViewById(R.id.tv_layout_Sach_Ten);
            tg=(TextView)itemView.findViewById(R.id.tv_layout_Sach_TG);
            nxb=(TextView)itemView.findViewById(R.id.tv_layout_Sach_NXB);
            loai=(TextView)itemView.findViewById(R.id.tv_layout_Sach_LoaiSach);
            gia_thue=(TextView)itemView.findViewById(R.id.tv_layout_Sach_GiaThue);
            sua_sach=(ImageView)itemView.findViewById(R.id.btnSuaSach);
            xoa_sach=(ImageView)itemView.findViewById(R.id.btnXoaSach);

        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sach,parent,false);
        return new SachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Sach sach=saches.get(position);
        ((SachViewHolder)holder).ma_sach.setText("Mã sách: "+sach.getMaSach());
        ((SachViewHolder)holder).ten_sach.setText("Tên sách: "+sach.getTenSach());
        ((SachViewHolder)holder).tg.setText("Tác giả: "+TenTG(position));
        ((SachViewHolder)holder).nxb.setText("Nhà xuất bản: "+TenNXB(position));
        ((SachViewHolder)holder).loai.setText("Loại sách: "+TenSach(position));
        ((SachViewHolder)holder).gia_thue.setText("Giá thuê: "+sach.getGiaThue());
        ((SachViewHolder)holder).xoa_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XoaSach(position);
            }
        });
        ((SachViewHolder)holder).sua_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaSach(position);
            }
        });
    }
    public String TenSach(int position){
        String tenSach="";
        loaiSachDAO=new LoaiSachDAO(context);
        ArrayList<LoaiSach> loaiSaches=loaiSachDAO.getAllLoaiSach();
        for(int i=0;i<loaiSaches.size();i++){
            if(saches.get(position).getMaLoai()==loaiSaches.get(i).getMaLoaiSach()){
                tenSach=loaiSaches.get(i).getTenLoaiSach();
                break;
            }
        }
        return tenSach;
    }
    public String TenNXB(int position){
        String tenNXB="";
        nXBDAO=new NXBDAO(context);
        ArrayList<NXB> nXBs=nXBDAO.getAllNXB();
        for(int i=0;i<nXBs.size();i++){
            if(saches.get(position).getMaLoai()==nXBs.get(i).getMaNXB()){
                tenNXB=nXBs.get(i).getTenNXB();
                break;
            }
        }
        return tenNXB;
    }
    public String TenTG(int position){
        String tenTG="";
        tacGiaDAO=new TacGiaDAO(context);
        ArrayList<TacGia> tacGias=tacGiaDAO.getAllTacGia();
        for (int i=0;i<tacGias.size();i++){
            if(saches.get(position).getMaTG()==tacGias.get(i).getMaTG()){
                tenTG=tacGias.get(i).getTenTG();
                break;
            }
        }
        return tenTG;
    }
    public void CapNhatSach(){
        saches= sachDAO.getAllSach();
        AdapterSach adapterSach=new AdapterSach(context,saches,recyclerView);
        recyclerView.setAdapter(adapterSach);
    }
    public void XoaSach(int position){
        for(int i=0;i<saches.size();i++){
            try {
                if(position==i){
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_xac_nhan,null);
                    TextView xacnhan=view.findViewById(R.id.tvXacNhan);
                    xacnhan.setText("XÁC NHẬN XÓA SÁCH");
                    builder.setView(view);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sachDAO=new SachDAO(context);
                            sachDAO.XoaSach(saches.get(position).getMaSach());
                            CapNhatSach();
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
//    public boolean CheckTenSach(String tenSach){
//        boolean check=false;
//        for(int i=0;i<saches.size();i++){
//            if(tenSach.equalsIgnoreCase(saches.get(i).getTenSach())){
//                check=true;
//                break;
//            }
//        }
//        return  check;
//    }
    public void SuaSach(int position){
        for(int i=0;i<saches.size();i++){
            try{
                if(position==i){
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_sua_sach,null);
                    builder.setView(view);
                    EditText SuaTenSach=view.findViewById(R.id.etSuaTenSach);
                    SuaTenSach.setText(saches.get(position).getTenSach());
                    EditText SuaGiaThue=view.findViewById(R.id.etSuaGiaThue);
                    SuaGiaThue.setText(saches.get(position).getGiaThue()+"");
                    Spinner spinnerTG=view.findViewById(R.id.spinnerSuaTG);
                    Spinner spinnerNXB=view.findViewById(R.id.spinnerSuaNXB);
                    Spinner spinnerLoai=view.findViewById(R.id.spinnerSuaLoaiSach);
                    TextView MaSach=view.findViewById(R.id.tvMaSach);
                    MaSach.setText("Mã sách: "+saches.get(position).getMaSach());
                    tacGiaDAO=new TacGiaDAO(context);
                    ArrayList<TacGia> tacGias=tacGiaDAO.getAllTacGia();
                    ArrayList<String> tenTGs=new ArrayList<>();
                    for(int j=0;j<tacGias.size();j++){
                        tenTGs.add(tacGias.get(j).getTenTG());
                    }
                    ArrayAdapter<String> adapterTG=new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,tenTGs);
                    spinnerTG.setAdapter(adapterTG);

                    nXBDAO=new NXBDAO(context);
                    ArrayList<NXB> nXBs=nXBDAO.getAllNXB();
                    ArrayList<String> tenNXBs=new ArrayList<>();
                    for(int j=0;j<nXBs.size();j++){
                        tenNXBs.add(nXBs.get(j).getTenNXB());
                    }
                    ArrayAdapter<String> adapterNXB=new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,tenNXBs);
                    spinnerNXB.setAdapter(adapterNXB);

                    loaiSachDAO=new LoaiSachDAO(context);
                    ArrayList<LoaiSach> loaiSaches=loaiSachDAO.getAllLoaiSach();
                    ArrayList<String> tenLoaiSaches=new ArrayList<>();
                    for(int j=0;j<loaiSaches.size();j++){
                        tenLoaiSaches.add(loaiSaches.get(j).getTenLoaiSach());
                    }

                    ArrayAdapter<String> adapterLoai=new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,tenLoaiSaches);
                    spinnerLoai.setAdapter(adapterLoai);



                    TextInputLayout textInputLayout=view.findViewById(R.id.textInputSuaTenSach);
                    TextInputLayout textInputLayout1=view.findViewById(R.id.textInputSuaGiaThue);
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
                    textInputLayout1.getEditText().addTextChangedListener(new TextWatcher() {
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
                            int vitriTG = 0;
                            int vitriNXB=0;
                            int vitriLoai=0;
                            for(int k=0;k<tacGias.size();k++){
                                if(spinnerTG.getSelectedItemPosition()==k){
                                    vitriTG=tacGias.get(k).getMaTG();
                                    break;
                                }
                            }
                            for(int k=0;k<nXBs.size();k++){
                                if(spinnerNXB.getSelectedItemPosition()==k){
                                    vitriNXB=nXBs.get(k).getMaNXB();
                                    break;
                                }
                            }
                            for(int k=0;k<loaiSaches.size();k++){
                                if(spinnerLoai.getSelectedItemPosition()==k){
                                    vitriLoai=loaiSaches.get(k).getMaLoaiSach();
                                    break;
                                }
                            }

                            try{
                                if (SuaTenSach.getText().toString().equals("")||SuaGiaThue.getText().toString().equals("")||vitriTG==0||vitriNXB==0||vitriLoai==0){
                                    Toast.makeText(context,"Không được để trống, sửa thất bại",Toast.LENGTH_SHORT).show();
                                }
//                                else if(CheckTenSach(SuaTenSach.getText().toString())){
//                                    Toast.makeText(context,"Trùng tên sách, sửa thất bại",Toast.LENGTH_SHORT).show();
//
//                                }
                                else{
                                    sachDAO=new SachDAO(context);
                                    Sach x=new Sach(saches.get(position).getMaSach(),SuaTenSach.getText().toString(),vitriTG,vitriNXB,vitriLoai,Double.parseDouble(SuaGiaThue.getText().toString()));
                                    sachDAO.SuaSach(x);
                                    CapNhatSach();
                                    Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception ex){
                                Toast.makeText(context,"sửa thất bại",Toast.LENGTH_SHORT).show();
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
        return saches.size();
    }
}
