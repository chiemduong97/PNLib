package com.example.pnlib.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Adapter.AdapterLoaiSach;
import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.R;
import com.example.pnlib.SQLite.LoaiSachDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class FragmentLoaiSach extends Fragment {
    RecyclerView recyclerView;
    ArrayList<LoaiSach> loaiSaches=new ArrayList<>();
    LoaiSachDAO loaiSachDAO;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai_sach, null);
        recyclerView=view.findViewById(R.id.recyclerLoaiSach);
        loaiSachDAO=new LoaiSachDAO(getContext());
        loaiSaches=loaiSachDAO.getAllLoaiSach();
        FloatingActionButton floatingActionButton=view.findViewById(R.id.ThemLoaiSach);
        AdapterLoaiSach adapterLoaiSach=new AdapterLoaiSach(getContext(),loaiSaches,recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterLoaiSach);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemLoaiSach();
            }
        });
        return view;
    }
    public void CapNhatLoaiSach(){
        loaiSaches=loaiSachDAO.getAllLoaiSach();
        AdapterLoaiSach adapterLoaiSach=new AdapterLoaiSach(getContext(),loaiSaches,recyclerView);
        recyclerView.setAdapter(adapterLoaiSach);
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
    public void ThemLoaiSach(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_them_loai_sach,null);
        builder.setView(view);
        EditText ThemTenLoaiSach=view.findViewById(R.id.etThemTenLoaiSach);
        TextInputLayout textInputLayout=view.findViewById(R.id.textInputThemLoaiSach);
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
                    if (ThemTenLoaiSach.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"Không được để trống, thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                    else if(CheckTenLoaiSach(ThemTenLoaiSach.getText().toString())){
                        Toast.makeText(getContext(),"Đã có loại sách này, thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        loaiSachDAO=new LoaiSachDAO(getContext());
                        loaiSachDAO.ThemLoaiSach(ThemTenLoaiSach.getText().toString());
                        CapNhatLoaiSach();
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
