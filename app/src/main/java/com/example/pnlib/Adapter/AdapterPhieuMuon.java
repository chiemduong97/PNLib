package com.example.pnlib.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AdapterPhieuMuon extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<PhieuMuon> phieuMuons;
    RecyclerView recyclerView;

    public AdapterPhieuMuon(Context context, ArrayList<PhieuMuon> phieuMuons, RecyclerView recyclerView){
        this.context=context;
        this.phieuMuons=phieuMuons;
        this.recyclerView=recyclerView;
    }

    public class PhieuMuonViewHolder extends RecyclerView.ViewHolder{
        public TextView ma_phieu_muon;
        public TextView user;
        public TextView tv;
        public TextView ten_sach;
        public TextView gia_thue;
        public TextView ngay_thue;
        public CheckBox trang_thai;
        public TextView ngay_tra;
        public ImageView sua_phieu_muon;
        public ImageView xoa_phieu_muon;

        public PhieuMuonViewHolder(@NonNull View itemView) {
            super(itemView);
            ma_phieu_muon=(TextView)itemView.findViewById(R.id.tv_layout_PhieuMuon_Ma);
            user=(TextView)itemView.findViewById(R.id.tv_layout_PhieuMuon_User);
            tv=(TextView)itemView.findViewById(R.id.tv_layout_PhieuMuon_TV);
            ten_sach=(TextView)itemView.findViewById(R.id.tv_layout_PhieuMuon_Sach);
            gia_thue=(TextView)itemView.findViewById(R.id.tv_layout_PhieuMuon_GiaThueSach);
            ngay_thue=(TextView)itemView.findViewById(R.id.tv_layout_PhieuMuon_NgayThue);
            trang_thai=(CheckBox) itemView.findViewById(R.id.tv_layout_PhieuMuon_TrangThai);
            ngay_tra=(TextView) itemView.findViewById(R.id.tv_layout_PhieuMuon_NgayTra);
            sua_phieu_muon=(ImageView) itemView.findViewById(R.id.btnSuaPhieuMuon);
            xoa_phieu_muon=(ImageView) itemView.findViewById(R.id.btnXoaPhieuMuon);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.phieumuon,parent,false);
        return new PhieuMuonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PhieuMuon phieuMuon=phieuMuons.get(position);
        ((PhieuMuonViewHolder)holder).ma_phieu_muon.setText("Mã phiếu mượn: 1");
        ((PhieuMuonViewHolder)holder).user.setText("Người tạo: chiemduong97");
        ((PhieuMuonViewHolder)holder).tv.setText("Người mượn: hoangan");
        ((PhieuMuonViewHolder)holder).ten_sach.setText("Tên sách: Photoshop");
        ((PhieuMuonViewHolder)holder).gia_thue.setText("Giá thuê: 20000");
        ((PhieuMuonViewHolder)holder).ngay_thue.setText("Ngày thu: 22/05/2021");
        ((PhieuMuonViewHolder)holder).trang_thai.setChecked(false);
        ((PhieuMuonViewHolder)holder).ngay_tra.setText("Ngày trả: null");
//        ((PhieuMuonViewHolder)holder).xoa_phieu_muon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                XoaphieuMuon(position);
//            }
//        });
//        ((PhieuMuonViewHolder)holder).sua_phieu_muon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SuaphieuMuon(position);
//            }
//        });
    }
//    public void CapNhatphieuMuon(){
//        loaiThuDAO=new LoaiThuDAO(context);
//        User user;
//        try{
//            user=((QuanLyThuChi)context).UserActive();
//        }
//        catch (Exception exception){
//            user=((MainActivity2)context).UserActive();
//        }
//        ArrayList<LoaiThu> loaiThus=loaiThuDAO.getAllLoaiThu(user.getId_user());
//        arrayList1=new ArrayList<>();
//        for(int i=0;i<loaiThus.size();i++){
//            arrayList1.add(loaiThus.get(i).getId_loaithu());
//        }
//        dsphieuMuon=phieuMuonDAO.getAllphieuMuon(arrayList1);
//        AdapterphieuMuon adapterphieuMuon=new AdapterphieuMuon(context,dsphieuMuon,recyclerView);
//        recyclerView.setAdapter(adapterphieuMuon);
//    }
//
//    public void XoaphieuMuon(int position){
//        for(int i=0;i<dsphieuMuon.size();i++){
//            try {
//                if(position==i){
//                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
//                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_xac_nhan,null);
//                    TextView xacnhan=view.findViewById(R.id.tvXacNhan);
//                    xacnhan.setText("XÁC NHẬN XÓA KHOẢNG THU");
//                    builder.setView(view);
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            phieuMuonDAO=new phieuMuonDAO(context);
//                            phieuMuonDAO.XoaphieuMuon(dsphieuMuon.get(position).getId_phieuMuon());
//                            CapNhatphieuMuon();
//                            Toast.makeText(context,"Xóa thành công!",Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//                    builder.create().show();
//
//                }
//            }catch (Exception ex){
//                Toast.makeText(context,"Xóa thất bại",Toast.LENGTH_SHORT).show();
//                break;
//            }
//
//        }
//    }
//    public void SuaphieuMuon(int position){
//        for(int i=0;i<dsphieuMuon.size();i++){
//            try {
//                if(position==i){
//                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
//                    View view= LayoutInflater.from(context).inflate(R.layout.dialog_sua_khoang_thu,null);
//                    builder.setView(view);
//                    TextView tvMaphieuMuon=view.findViewById(R.id.tvMaphieuMuon);
//                    EditText etSuaTenphieuMuon=view.findViewById(R.id.etSuaTenphieuMuon);
//                    EditText etSuaSoLuongphieuMuon=view.findViewById(R.id.etSuaSoLuongphieuMuon);
//                    EditText SuaNgayThu=view.findViewById(R.id.etSuaNgayThu);
//                    ImageView btnSuaNgayThu=view.findViewById(R.id.btnSuaNgayThu);
//
//                    loaiThuDAO=new LoaiThuDAO(context);
//                    User user;
//                    try{
//                        user=((QuanLyThuChi)context).UserActive();
//                    }
//                    catch (Exception exception){
//                        user=((MainActivity2)context).UserActive();
//                    }
//                    ArrayList<LoaiThu> loaiThus=loaiThuDAO.getAllLoaiThu(user.getId_user());
//                    ArrayList<String> arrayList=new ArrayList<String>();
//                    for(int j=0;j<loaiThus.size();j++){
//                        arrayList.add(loaiThus.get(j).getLoaithu_name());
//                    }
//                    Spinner spinnerSuaphieuMuon=view.findViewById(R.id.spinnerSuaphieuMuon);
//                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,arrayList);
//                    spinnerSuaphieuMuon.setAdapter(adapter);
//
//                    btnSuaNgayThu.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Calendar calendar=Calendar.getInstance();
//                            int ngay=calendar.get(Calendar.DATE);
//                            int thang=calendar.get(Calendar.MONTH);
//                            int nam=calendar.get(Calendar.YEAR);
//                            DatePickerDialog datePickerDialog=new DatePickerDialog(context,new DatePickerDialog.OnDateSetListener() {
//                                @Override
//                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                    calendar.set(year,month,dayOfMonth);
//                                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
//                                    SuaNgayThu.setText(simpleDateFormat.format(calendar.getTime()));
//                                    ngaythu=new date(dayOfMonth,month+1,year);
//                                }
//                            },nam,thang,ngay);
//                            datePickerDialog.show();
//                        }
//                    });
//
//                    tvMaphieuMuon.setText("Mã khoảng thu:"+dsphieuMuon.get(position).getId_phieuMuon());
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            int vitri = 0;
//                            for(int k=0;k<loaiThus.size();k++){
//                                if(spinnerSuaphieuMuon.getSelectedItemPosition()==k){
//                                    vitri=loaiThus.get(k).getId_loaithu();
//                                    break;
//                                }
//                            }
//                            try{
//                                if (etSuaTenphieuMuon.getText().toString().equals("")||etSuaSoLuongphieuMuon.getText().toString().equals("")||SuaNgayThu.getText().toString().equals("")||vitri==0){
//                                    Toast.makeText(context,"Không được để trống, sửa thất bại",Toast.LENGTH_SHORT).show();
//                                }
//                                else{
//                                    phieuMuonDAO=new phieuMuonDAO(context);
//                                    phieuMuonDAO.SuaphieuMuon(dsphieuMuon.get(position).getId_phieuMuon(),etSuaTenphieuMuon.getText().toString(),Double.parseDouble(etSuaSoLuongphieuMuon.getText().toString()),vitri,ngaythu);
//                                    CapNhatphieuMuon();
//                                    Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
//                                }
//                            }catch (Exception ex){
//                                Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//                    builder.create().show();
//                }
//            }catch (Exception ex){
//                Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
//                break;
//            }
//
//        }
//    }
    @Override
    public int getItemCount() {
        return phieuMuons.size();
    }
}
