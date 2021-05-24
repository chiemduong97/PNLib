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

import com.example.pnlib.Adapter.AdapterTacGia;
import com.example.pnlib.Model.TacGia;
import com.example.pnlib.R;
import com.example.pnlib.SQLite.TacGiaDAO;
import com.example.pnlib.SQLite.TacGiaDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class FragmentTacGia extends Fragment {
    RecyclerView recyclerView;
    ArrayList<TacGia> tacGiaes=new ArrayList<>();
    TacGiaDAO tacGiaDAO;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tac_gia, null);
        recyclerView=view.findViewById(R.id.recyclerTG);
        tacGiaDAO=new TacGiaDAO(getContext());
        tacGiaes=tacGiaDAO.getAllTacGia();
        FloatingActionButton floatingActionButton=view.findViewById(R.id.ThemTG);
       

        AdapterTacGia adapterTacGia=new AdapterTacGia(getContext(),tacGiaes,recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterTacGia);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemTG();
            }
        });
        return view;
    }
    public void CapNhatTacGia(){
        tacGiaes= tacGiaDAO.getAllTacGia();
        AdapterTacGia adapterTacGia=new AdapterTacGia(getContext(),tacGiaes,recyclerView);
        recyclerView.setAdapter(adapterTacGia);
    }
    public boolean CheckTenTacGia(String tenTacGia){
        boolean check=false;
        for(int i=0;i<tacGiaes.size();i++){
            if(tenTacGia.equalsIgnoreCase(tacGiaes.get(i).getTenTG())){
                check=true;
                break;
            }
        }
        return  check;
    }
    public void ThemTG(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_them_tac_gia,null);
        builder.setView(view);
        EditText ThemTenTacGia=view.findViewById(R.id.etThemTenTG);
        TextInputLayout textInputLayout=view.findViewById(R.id.textInputThemTG);
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
                    if (ThemTenTacGia.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"Không được để trống, thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                    else if(CheckTenTacGia(ThemTenTacGia.getText().toString())){
                        Toast.makeText(getContext(),"Đã có tác giả này, thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        tacGiaDAO=new TacGiaDAO(getContext());
                        tacGiaDAO.ThemTacGia(ThemTenTacGia.getText().toString());
                        CapNhatTacGia();
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
