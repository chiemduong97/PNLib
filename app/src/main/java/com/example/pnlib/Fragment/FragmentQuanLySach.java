package com.example.pnlib.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.pnlib.R;
import com.google.android.material.tabs.TabLayout;

public class FragmentQuanLySach extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 4 ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x =  inflater.inflate(R.layout.fragment_quan_ly_sach,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabsquanlysach);

        viewPager = (ViewPager) x.findViewById(R.id.viewpagerquanlysach);
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.ic_loaisach);
                tabLayout.getTabAt(1).setIcon(R.drawable.ic_sach);
                tabLayout.getTabAt(2).setIcon(R.drawable.ic_tacgia);
                tabLayout.getTabAt(3).setIcon(R.drawable.ic_nxb);
            }
        });
        return x;
    }
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new FragmentLoaiSach();
                case 1 : return new FragmentSach();
                case 2 : return new FragmentTacGia();
                case 3 : return new FragmentNXB();
            }
            return null;
        }
        @Override
        public int getCount() {
            return int_items;
        }
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Loại";
                case 1 :
                    return "Sách";
                case 2 :
                    return "Tác giả";
                case 3 :
                    return "NXB";
            }
            return null;
        }


    }
}
