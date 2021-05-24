package com.example.pnlib.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Adapter.AdapterRecycleDanhMuc;
import com.example.pnlib.Model.DanhMuc;
import com.example.pnlib.R;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    RecyclerView recyclerView;
    ArrayList<DanhMuc> danhMucs=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home,null);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerDanhMuc);
        danhMucs.add(new DanhMuc(R.drawable.user,"Quản lý user"));
        danhMucs.add(new DanhMuc(R.drawable.phieumuon,"Phiếu mượn"));
        danhMucs.add(new DanhMuc(R.drawable.sach,"Sách"));
        danhMucs.add(new DanhMuc(R.drawable.loaisach,"Loại sách"));
        danhMucs.add(new DanhMuc(R.drawable.tacgia,"Tác giả"));
        danhMucs.add(new DanhMuc(R.drawable.nhaxuatban,"Nhà xuất bản"));
        danhMucs.add(new DanhMuc(R.drawable.top10,"Top 10 sách"));
        danhMucs.add(new DanhMuc(R.drawable.doanhthu,"Tổng chi"));
        AdapterRecycleDanhMuc adapterRecycleGioiThieu=new AdapterRecycleDanhMuc(getContext(),danhMucs);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),
                2,
                GridLayoutManager.HORIZONTAL,
                false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterRecycleGioiThieu);
        return view;
    }
}
