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

import com.example.pnlib.Model.NXB;
import com.example.pnlib.Model.NXB;
import com.example.pnlib.R;
import com.example.pnlib.SQLite.NXBDAO;
import com.example.pnlib.SQLite.NXBDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AdapterNXB extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<NXB>  nXBs;
    RecyclerView recyclerView;
    NXBDAO nXBDAO;
    public AdapterNXB(Context context, ArrayList<NXB> nXBs, RecyclerView recyclerView){
        this.context=context;
        this.nXBs=nXBs;
        this.recyclerView=recyclerView;
    }

    public class NXBViewHolder extends RecyclerView.ViewHolder{
        public TextView ma_NXB;
        public TextView ten_NXB;
        public ImageView sua_NXB;
        public ImageView xoa_NXB;

        public NXBViewHolder(@NonNull View itemView) {
            super(itemView);
            ma_NXB=(TextView)itemView.findViewById(R.id.tv_layout_NXB_Ma);
            ten_NXB=(TextView)itemView.findViewById(R.id.tv_layout_NXB_Ten);
            sua_NXB=(ImageView)itemView.findViewById(R.id.btnSuaNXB);
            xoa_NXB=(ImageView)itemView.findViewById(R.id.btnXoaNXB);

        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.nxb,parent,false);
        return new NXBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NXB nXB=nXBs.get(position);
        ((NXBViewHolder)holder).ma_NXB.setText("Mã NXB: "+nXB.getMaNXB());
        ((NXBViewHolder)holder).ten_NXB.setText("Tên NXB: "+nXB.getTenNXB());

        ((NXBViewHolder)holder).xoa_NXB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XoaNXB(position);
            }
        });
        ((NXBViewHolder)holder).sua_NXB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaNXB(position);
            }
        });
    }
    public void CapNhatNXB(){
        nXBs= nXBDAO.getAllNXB();
        AdapterNXB adapterNXB=new AdapterNXB(context,nXBs,recyclerView);
        recyclerView.setAdapter(adapterNXB);
    }

    public void XoaNXB(int position){
        for(int i=0;i<nXBs.size();i++){
            try {
                if(position==i){
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_xac_nhan,null);
                    TextView xacnhan=view.findViewById(R.id.tvXacNhan);
                    xacnhan.setText("XÁC NHẬN XÓA NHÀ XUẤT BẢN");
                    builder.setView(view);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            nXBDAO=new NXBDAO(context);
                            nXBDAO.XoaNXB(nXBs.get(position).getMaNXB());
                            CapNhatNXB();
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
    public boolean CheckTenNXB(String tenNXB){
        boolean check=false;
        for(int i=0;i<nXBs.size();i++){
            if(tenNXB.equalsIgnoreCase(nXBs.get(i).getTenNXB())){
                check=true;
                break;
            }
        }
        return  check;
    }
    public void SuaNXB(int position){
        for(int i=0;i<nXBs.size();i++){
            try{
                if(position==i){
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_sua_nxb,null);
                    builder.setView(view);
                    EditText SuaTenNXB=view.findViewById(R.id.etSuaTenNXB);
                    SuaTenNXB.setText(nXBs.get(position).getTenNXB());
                    TextView MaNXB=view.findViewById(R.id.tvMaNXB);
                    MaNXB.setText("Mã nhà xuất bản: "+nXBs.get(position).getMaNXB());
                    TextInputLayout textInputLayout=view.findViewById(R.id.textInputSuaNXB);
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
                                if (SuaTenNXB.getText().toString().equals("")){
                                    Toast.makeText(context,"Không được để trống, sửa thất bại",Toast.LENGTH_SHORT).show();
                                }
                                else if(CheckTenNXB(SuaTenNXB.getText().toString())){
                                    Toast.makeText(context,"Đã có tác giả này, sửa thất bại",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    nXBDAO=new NXBDAO(context);
                                    nXBDAO.SuaNXB(new NXB(nXBs.get(position).getMaNXB(),SuaTenNXB.getText().toString()));
                                    CapNhatNXB();
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
        return nXBs.size();
    }
}
