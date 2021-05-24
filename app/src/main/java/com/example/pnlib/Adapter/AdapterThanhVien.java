package com.example.pnlib.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Model.ThanhVien;
import com.example.pnlib.Model.ThanhVien;
import com.example.pnlib.R;
import com.example.pnlib.SQLite.ThanhVienDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AdapterThanhVien extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<ThanhVien>  thanhViens;
    RecyclerView recyclerView;
    ThanhVienDAO thanhVienDAO;
    public AdapterThanhVien(Context context, ArrayList<ThanhVien> thanhViens, RecyclerView recyclerView){
        this.context=context;
        this.thanhViens=thanhViens;
        this.recyclerView=recyclerView;
    }

    public class ThanhVienViewHolder extends RecyclerView.ViewHolder{
        public TextView ma_TV;
        public TextView ten_TV;
        public TextView ngaysinh;
        public TextView diachi;
        public ImageView sua_TV;
        public ImageView xoa_TV;

        public ThanhVienViewHolder(@NonNull View itemView) {
            super(itemView);
            ma_TV=(TextView)itemView.findViewById(R.id.tv_layout_TV_Ma);
            ten_TV=(TextView)itemView.findViewById(R.id.tv_layout_TV_Ten);
            ngaysinh=(TextView)itemView.findViewById(R.id.tv_layout_TV_NgaySinh);
            diachi=(TextView)itemView.findViewById(R.id.tv_layout_TV_DiaChi);
            sua_TV=(ImageView)itemView.findViewById(R.id.btnSuaTV);
            xoa_TV=(ImageView)itemView.findViewById(R.id.btnXoaTV);

        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.thanhvien,parent,false);
        return new ThanhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ThanhVien thanhVien=thanhViens.get(position);
        ((ThanhVienViewHolder)holder).ma_TV.setText("Mã thành viên: "+thanhVien.getMaTV());
        ((ThanhVienViewHolder)holder).ten_TV.setText("Tên thành viên: "+thanhVien.getTenTV());
        ((ThanhVienViewHolder)holder).ngaysinh.setText("Ngày sinh: "+thanhVien.getNgaySinh());
        ((ThanhVienViewHolder)holder).diachi.setText("Địa chỉ: "+thanhVien.getDiaChi());


        ((ThanhVienViewHolder)holder).xoa_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XoaThanhVien(position);
            }
        });
        ((ThanhVienViewHolder)holder).sua_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaThanhVien(position);
            }
        });
    }
    public void CapNhatThanhVien(){
        thanhViens= thanhVienDAO.getAllThanhVien();
        AdapterThanhVien adapterThanhVien=new AdapterThanhVien(context,thanhViens,recyclerView);
        recyclerView.setAdapter(adapterThanhVien);
    }

    public void XoaThanhVien(int position){
        for(int i=0;i<thanhViens.size();i++){
            try {
                if(position==i){
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_xac_nhan,null);
                    TextView xacnhan=view.findViewById(R.id.tvXacNhan);
                    xacnhan.setText("XÁC NHẬN XÓA THÀNH VIÊN");
                    builder.setView(view);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            thanhVienDAO=new ThanhVienDAO(context);
                            thanhVienDAO.XoaThanhVien(thanhViens.get(position).getMaTV());
                            CapNhatThanhVien();
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
    public boolean CheckTenThanhVien(String tenThanhVien){
        boolean check=false;
        for(int i=0;i<thanhViens.size();i++){
            if(tenThanhVien.equalsIgnoreCase(thanhViens.get(i).getTenTV())){
                check=true;
                break;
            }
        }
        return  check;
    }
    public void SuaThanhVien(int position){
        for(int i=0;i<thanhViens.size();i++){
            try{
                if(position==i){
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_sua_thanh_vien,null);
                    builder.setView(view);
                    EditText SuaTenThanhVien=view.findViewById(R.id.etSuaTenTV);
                    SuaTenThanhVien.setText(thanhViens.get(position).getTenTV());
                    EditText SuaNgaySinh=view.findViewById(R.id.etSuaNgaySinh);
                    SuaNgaySinh.setText(thanhViens.get(position).getNgaySinh());
                    EditText SuaDiaChi=view.findViewById(R.id.etSuaDiaChi);
                    SuaDiaChi.setText(thanhViens.get(position).getDiaChi());
                    TextView MaThanhVien=view.findViewById(R.id.tvMaTV);
                    ImageView ivSuaNgaySinh=view.findViewById(R.id.btnSuaNgaySinh);
                    MaThanhVien.setText("Mã loại sách: "+thanhViens.get(position).getMaTV());
                    TextInputLayout textInputLayout=view.findViewById(R.id.textInputSuaTenTV);
                    TextInputLayout textInputLayout1=view.findViewById(R.id.textInputSuaNgaySinh);
                    TextInputLayout textInputLayout2=view.findViewById(R.id.textInputSuaDiaChi);
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
                    textInputLayout2.getEditText().addTextChangedListener(new TextWatcher() {
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
                    ivSuaNgaySinh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Calendar calendar=Calendar.getInstance();
                            DatePickerDialog datePickerDialog=new DatePickerDialog(context,new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    calendar.set(year,month,dayOfMonth);
                                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                                    SuaNgaySinh.setText(simpleDateFormat.format(calendar.getTime()));
                                }
                            },2000,1,1);
                            datePickerDialog.show();
                        }
                    });
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try{
                                if (SuaTenThanhVien.getText().toString().equals("")){
                                    Toast.makeText(context,"Không được để trống, sửa thất bại",Toast.LENGTH_SHORT).show();
                                }
                                else if(CheckTenThanhVien(SuaTenThanhVien.getText().toString())){
                                    Toast.makeText(context,"Đã có thành viên này, sửa thất bại",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    thanhVienDAO=new ThanhVienDAO(context);
                                    thanhVienDAO.SuaThanhVien(new ThanhVien(thanhViens.get(position).getMaTV(),SuaTenThanhVien.getText().toString(),SuaNgaySinh.getText().toString(),SuaDiaChi.getText().toString()));
                                    CapNhatThanhVien();
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
        return thanhViens.size();
    }
}
