package com.example.pnlib.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pnlib.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class FragmentThongKeDoanhThu extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public FragmentThongKeDoanhThu(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_doanh_thu, null);
        BarChart barChart=view.findViewById(R.id.barCharDoanhThu);
        TextView namThongKe=view.findViewById(R.id.namThongKe);
        namThongKe.setText("2021");
        ArrayList doanhthu = new ArrayList<>();
        doanhthu.add(new BarEntry(1,20000));
        doanhthu.add(new BarEntry(2,50000));
        doanhthu.add(new BarEntry(3,30000));
        doanhthu.add(new BarEntry(4,25000));
        BarDataSet barDataSet=new BarDataSet(doanhthu,"Top 10 sách");
        barDataSet.setColor(Color.RED);
        XAxis xAxis = barChart.getXAxis();
        ArrayList<String> month=new ArrayList<>();
        month.add("");
        month.add("Mã 1");
        month.add("Mã 2");
        month.add("Mã 3");
        month.add("Mã 4");
        BarData barData=new BarData(barDataSet);
        barChart.setData(barData);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(month));
        barChart.getAxisLeft().setAxisMinimum(0);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);

        xAxis.setCenterAxisLabels(false);
        xAxis.setGranularityEnabled(true);
        barChart.getXAxis().setAxisMinimum(0.3f);
        barData.setBarWidth(0.3f);
        barChart.getDescription().setEnabled(false);
        barChart.animateY(1000);
        return view;
    }
}
