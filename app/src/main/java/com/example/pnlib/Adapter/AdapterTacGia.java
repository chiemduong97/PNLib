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

import com.example.pnlib.Model.TacGia;
import com.example.pnlib.R;
import com.example.pnlib.SQLite.TacGiaDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AdapterTacGia extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<TacGia>  tacGiaes;
    RecyclerView recyclerView;
    TacGiaDAO tacGiaDAO;
    public AdapterTacGia(Context context, ArrayList<TacGia> tacGiaes, RecyclerView recyclerView){
        this.context=context;
        this.tacGiaes=tacGiaes;
        this.recyclerView=recyclerView;
    }

    public class TacGiaViewHolder extends RecyclerView.ViewHolder{
        public TextView ma_TacGia;
        public TextView ten_TacGia;
        public ImageView sua_TacGia;
        public ImageView xoa_TacGia;

        public TacGiaViewHolder(@NonNull View itemView) {
            super(itemView);
            ma_TacGia=(TextView)itemView.findViewById(R.id.tv_layout_TG_Ma);
            ten_TacGia=(TextView)itemView.findViewById(R.id.tv_layout_TG_Ten);
            sua_TacGia=(ImageView)itemView.findViewById(R.id.btnSuaTG);
            xoa_TacGia=(ImageView)itemView.findViewById(R.id.btnXoaTG);

        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.tacgia,parent,false);
        return new TacGiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TacGia tacGia=tacGiaes.get(position);
        ((TacGiaViewHolder)holder).ma_TacGia.setText("Mã tác giả: "+tacGia.getMaTG());
        ((TacGiaViewHolder)holder).ten_TacGia.setText("Tên tác giả: "+tacGia.getTenTG());
        
        ((TacGiaViewHolder)holder).xoa_TacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XoaTacGia(position);
            }
        });
        ((TacGiaViewHolder)holder).sua_TacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaTacGia(position);
            }
        });
    }
    public void CapNhatTacGia(){
        tacGiaes= tacGiaDAO.getAllTacGia();
        AdapterTacGia adapterTacGia=new AdapterTacGia(context,tacGiaes,recyclerView);
        recyclerView.setAdapter(adapterTacGia);
    }

    public void XoaTacGia(int position){
        for(int i=0;i<tacGiaes.size();i++){
            try {
                if(position==i){
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_xac_nhan,null);
                    TextView xacnhan=view.findViewById(R.id.tvXacNhan);
                    xacnhan.setText("XÁC NHẬN XÓA TÁC GIẢ");
                    builder.setView(view);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tacGiaDAO=new TacGiaDAO(context);
                            tacGiaDAO.XoaTacGia(tacGiaes.get(position).getMaTG());
                            CapNhatTacGia();
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
    public void SuaTacGia(int position){
        for(int i=0;i<tacGiaes.size();i++){
            try{
                if(position==i){
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_sua_tac_gia,null);
                    builder.setView(view);
                    EditText SuaTenTacGia=view.findViewById(R.id.etSuaTenTG);
                    SuaTenTacGia.setText(tacGiaes.get(position).getTenTG());
                    TextView MaTacGia=view.findViewById(R.id.tvMaTG);
                    MaTacGia.setText("Mã tác giả: "+tacGiaes.get(position).getMaTG());
                    TextInputLayout textInputLayout=view.findViewById(R.id.textInputSuaTG);
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
                                if (SuaTenTacGia.getText().toString().equals("")){
                                    Toast.makeText(context,"Không được để trống, sửa thất bại",Toast.LENGTH_SHORT).show();
                                }
                                else if(CheckTenTacGia(SuaTenTacGia.getText().toString())){
                                    Toast.makeText(context,"Đã có tác giả này, sửa thất bại",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    tacGiaDAO=new TacGiaDAO(context);
                                    tacGiaDAO.SuaTacGia(new TacGia(tacGiaes.get(position).getMaTG(),SuaTenTacGia.getText().toString()));
                                    CapNhatTacGia();
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
        return tacGiaes.size();
    }
}
