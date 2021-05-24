package com.example.pnlib.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Adapter.AdapterPhieuMuon;
import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.R;

import java.util.ArrayList;

public class FragmentPhieuMuon extends Fragment {
    RecyclerView recyclerView;
    ArrayList<PhieuMuon> phieuMuons=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_phieu_muon,null);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerPhieuMuon);
        phieuMuons.add(new PhieuMuon());
        phieuMuons.add(new PhieuMuon());
        phieuMuons.add(new PhieuMuon());
        phieuMuons.add(new PhieuMuon());
        phieuMuons.add(new PhieuMuon());
        phieuMuons.add(new PhieuMuon());

        AdapterPhieuMuon adapterPhieuMuon=new AdapterPhieuMuon(getContext(),phieuMuons,recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterPhieuMuon);
        return view;
    }
}
