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

import com.example.pnlib.Adapter.AdapterUser;
import com.example.pnlib.Model.User;
import com.example.pnlib.R;
import com.example.pnlib.SQLite.UserDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class FragmentUser extends Fragment {
    RecyclerView recyclerView;
    ArrayList<User> users=new ArrayList<>();
    UserDAO userDAO;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        recyclerView=view.findViewById(R.id.recyclerUser);
        userDAO=new UserDAO(getContext());
        users=userDAO.getAllUser();
        FloatingActionButton floatingActionButton=view.findViewById(R.id.ThemUser);




        AdapterUser adapterUser=new AdapterUser(getContext(),users,recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterUser);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemUser();
            }
        });
        return view;
    }
    public void CapNhatUser(){
        users= userDAO.getAllUser();
        AdapterUser adapterUser=new AdapterUser(getContext(),users,recyclerView);
        recyclerView.setAdapter(adapterUser);
    }
    public boolean CheckTenUser(String tenUser){
        boolean check=false;
        for(int i=0;i<users.size();i++){
            if(tenUser.equalsIgnoreCase(users.get(i).getUserName())){
                check=true;
                break;
            }
        }
        return  check;
    }
    public void ThemUser(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_them_user,null);
        builder.setView(view);
        EditText ThemUser=view.findViewById(R.id.etThemUser);
        TextInputLayout textInputLayout=view.findViewById(R.id.textInputThemUser);
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
                    if (ThemUser.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"Không được để trống, thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                    else if(CheckTenUser(ThemUser.getText().toString())){
                        Toast.makeText(getContext(),"Đã có user này, thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        userDAO=new UserDAO(getContext());
                        userDAO.ThemUser(new User(0,null,null,ThemUser.getText().toString(),"abc123",R.drawable.user));
                        CapNhatUser();
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
