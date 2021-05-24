package com.example.pnlib.Fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Adapter.AdapterThanhVien;
import com.example.pnlib.Model.ThanhVien;
import com.example.pnlib.R;
import com.example.pnlib.SQLite.ThanhVienDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FragmentThanhVien extends Fragment {
    RecyclerView recyclerView;
    ArrayList<ThanhVien> thanhViens=new ArrayList<>();
    ThanhVienDAO thanhVienDAO;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thanh_vien, null);
        recyclerView=view.findViewById(R.id.recyclerTV);
        thanhVienDAO=new ThanhVienDAO(getContext());
        thanhViens=thanhVienDAO.getAllThanhVien();
        FloatingActionButton floatingActionButton=view.findViewById(R.id.ThemTV);


        AdapterThanhVien adapterThanhVien=new AdapterThanhVien(getContext(),thanhViens,recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterThanhVien);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemThanhVien();
            }
        });
        return view;
    }
    public void CapNhatThanhVien(){
        thanhViens= thanhVienDAO.getAllThanhVien();
        AdapterThanhVien adapterThanhVien=new AdapterThanhVien(getContext(),thanhViens,recyclerView);
        recyclerView.setAdapter(adapterThanhVien);
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
    public void ThemThanhVien(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_them_thanh_vien,null);
        builder.setView(view);
        EditText ThemTenThanhVien=view.findViewById(R.id.etThemTenTV);
        EditText ThemNgaySinh=view.findViewById(R.id.etThemNgaySinh);
        EditText ThemDiaChi=view.findViewById(R.id.etThemDiaChi);
        ImageView ivThemNgaySinh=view.findViewById(R.id.btnThemNgaySinh);
        TextInputLayout textInputLayout=view.findViewById(R.id.textInputThemTenTV);
        TextInputLayout textInputLayout1=view.findViewById(R.id.textInputThemNgaySinh);
        TextInputLayout textInputLayout2=view.findViewById(R.id.textInputThemDiaChi);
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
        ivThemNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                        ThemNgaySinh.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },2000,1,1);
                datePickerDialog.show();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    if (ThemTenThanhVien.getText().toString().equals("")){
                        Toast.makeText(getContext(),"Không được để trống, thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                    else if(CheckTenThanhVien(ThemTenThanhVien.getText().toString())){
                        Toast.makeText(getContext(),"Đã có thành viên này, thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        thanhVienDAO=new ThanhVienDAO(getContext());
                        thanhVienDAO.ThemThanhVien(new ThanhVien(0,ThemTenThanhVien.getText().toString(),ThemNgaySinh.getText().toString(),ThemDiaChi.getText().toString()));
                        CapNhatThanhVien();
                        Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
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
}
