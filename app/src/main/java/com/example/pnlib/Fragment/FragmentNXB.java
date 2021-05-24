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

import com.example.pnlib.Adapter.AdapterNXB;
import com.example.pnlib.Adapter.AdapterNXB;
import com.example.pnlib.Model.NXB;
import com.example.pnlib.R;
import com.example.pnlib.SQLite.NXBDAO;
import com.example.pnlib.SQLite.NXBDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class FragmentNXB extends Fragment {
    RecyclerView recyclerView;
    ArrayList<NXB> nXBs=new ArrayList<>();
    NXBDAO nXBDAO;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nxb, null);
        recyclerView=view.findViewById(R.id.recyclerNXB);
        nXBDAO=new NXBDAO(getContext());
        nXBs=nXBDAO.getAllNXB();
        FloatingActionButton floatingActionButton=view.findViewById(R.id.ThemNXB);


        AdapterNXB adapterNXB=new AdapterNXB(getContext(),nXBs,recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterNXB);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemNXB();
            }
        });
        return view;
    }
    public void CapNhatNXB(){
        nXBs= nXBDAO.getAllNXB();
        AdapterNXB adapterNXB=new AdapterNXB(getContext(),nXBs,recyclerView);
        recyclerView.setAdapter(adapterNXB);
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
    public void ThemNXB(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_them_nxb,null);
        builder.setView(view);
        EditText ThemTenNXB=view.findViewById(R.id.etThemTenNXB);
        TextInputLayout textInputLayout=view.findViewById(R.id.textInputThemNXB);
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
                    if (ThemTenNXB.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"Không được để trống, thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                    else if(CheckTenNXB(ThemTenNXB.getText().toString())){
                        Toast.makeText(getContext(),"Đã có nhà xuất bản này, thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        nXBDAO=new NXBDAO(getContext());
                        nXBDAO.ThemNXB(ThemTenNXB.getText().toString());
                        CapNhatNXB();
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
