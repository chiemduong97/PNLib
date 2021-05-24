package com.example.pnlib.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Adapter.AdapterSach;
import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.Model.NXB;
import com.example.pnlib.Model.Sach;
import com.example.pnlib.Model.TacGia;
import com.example.pnlib.R;
import com.example.pnlib.SQLite.LoaiSachDAO;
import com.example.pnlib.SQLite.NXBDAO;
import com.example.pnlib.SQLite.SachDAO;
import com.example.pnlib.SQLite.TacGiaDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class FragmentSach extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Sach> saches=new ArrayList<>();
    SachDAO sachDAO;
    LoaiSachDAO loaiSachDAO;
    TacGiaDAO tacGiaDAO;
    NXBDAO nXBDAO;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sach, null);
        recyclerView=view.findViewById(R.id.recyclerSach);
        FloatingActionButton floatingActionButton=view.findViewById(R.id.ThemSach);
        sachDAO=new SachDAO(getContext());
        saches=sachDAO.getAllSach();
        AdapterSach adapterSach=new AdapterSach(getContext(),saches,recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterSach);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemSach();
            }
        });
        return view;
    }
    public void CapNhatSach(){
        saches= sachDAO.getAllSach();
        AdapterSach adapterSach=new AdapterSach(getContext(),saches,recyclerView);
        recyclerView.setAdapter(adapterSach);
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
    public void ThemSach(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_them_sach,null);
        builder.setView(view);
        EditText ThemTenSach=view.findViewById(R.id.etThemTenSach);
        EditText ThemGiaThue=view.findViewById(R.id.etThemGiaThue);

        Spinner spinnerTG=view.findViewById(R.id.spinnerThemTG);
        Spinner spinnerNXB=view.findViewById(R.id.spinnerThemNXB);
        Spinner spinnerLoai=view.findViewById(R.id.spinnerThemLoaiSach);

        tacGiaDAO=new TacGiaDAO(getContext());
        ArrayList<TacGia> tacGias=tacGiaDAO.getAllTacGia();
        ArrayList<String> tenTGs=new ArrayList<>();
        for(int i=0;i<tacGias.size();i++){
            tenTGs.add(tacGias.get(i).getTenTG());
        }
        ArrayAdapter<String> adapterTG=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,tenTGs);
        spinnerTG.setAdapter(adapterTG);

        nXBDAO=new NXBDAO(getContext());
        ArrayList<NXB> nXBs=nXBDAO.getAllNXB();
        ArrayList<String> tenNXBs=new ArrayList<>();
        for(int i=0;i<nXBs.size();i++){
            tenNXBs.add(nXBs.get(i).getTenNXB());
        }
        ArrayAdapter<String> adapterNXB=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,tenNXBs);
        spinnerNXB.setAdapter(adapterNXB);

        loaiSachDAO=new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> loaiSaches=loaiSachDAO.getAllLoaiSach();
        ArrayList<String> tenLoaiSaches=new ArrayList<>();
        for(int i=0;i<loaiSaches.size();i++){
            tenLoaiSaches.add(loaiSaches.get(i).getTenLoaiSach());
        }

        ArrayAdapter<String> adapterLoai=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,tenLoaiSaches);
        spinnerLoai.setAdapter(adapterLoai);



        TextInputLayout textInputLayout=view.findViewById(R.id.textInputThemTenSach);
        TextInputLayout textInputLayout1=view.findViewById(R.id.textInputThemGiaThue);
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
                for(int i=0;i<tacGias.size();i++){
                    if(spinnerTG.getSelectedItemPosition()==i){
                        vitriTG=tacGias.get(i).getMaTG();
                        break;
                    }
                }
                for(int i=0;i<nXBs.size();i++){
                    if(spinnerNXB.getSelectedItemPosition()==i){
                        vitriNXB=nXBs.get(i).getMaNXB();
                        break;
                    }
                }
                for(int i=0;i<loaiSaches.size();i++){
                    if(spinnerLoai.getSelectedItemPosition()==i){
                        vitriLoai=loaiSaches.get(i).getMaLoaiSach();
                        break;
                    }
                }

                try{
                    if (ThemTenSach.getText().toString().equals("")||ThemGiaThue.getText().toString().equals("")||vitriTG==0||vitriNXB==0||vitriLoai==0){
                        Toast.makeText(getContext(),"Không được để trống, thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
//                    else if(CheckTenSach(ThemTenSach.getText().toString())){
//                        Toast.makeText(getContext(),"Trùng tên sách, thêm thất bại",Toast.LENGTH_SHORT).show();
//
//                    }
                    else{
                        sachDAO=new SachDAO(getContext());
                        Sach x=new Sach(0,ThemTenSach.getText().toString(),vitriTG,vitriNXB,vitriLoai,Double.parseDouble(ThemGiaThue.getText().toString()));
                        sachDAO.ThemSach(x);
                        CapNhatSach();
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
