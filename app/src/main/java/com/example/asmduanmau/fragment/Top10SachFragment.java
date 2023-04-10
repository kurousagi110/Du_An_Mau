package com.example.asmduanmau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmduanmau.Adapter.Top10Adapter;
import com.example.asmduanmau.DAO.ThongKeDAO;
import com.example.asmduanmau.Model.Sach;
import com.example.asmduanmau.R;

import java.util.ArrayList;
import java.util.List;

public class Top10SachFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke_top10,container,false);
        //ánh xạ recycle
        RecyclerView recyclerViewTop10 = view.findViewById(R.id.recycleTop10);
        //add dữ liệu vào list
        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        List<Sach> list = thongKeDAO.getTop10();
        //đổ dữ liệu
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewTop10.setLayoutManager(linearLayoutManager);
        Top10Adapter top10Adapter = new Top10Adapter(getContext(),list);
        recyclerViewTop10.setAdapter(top10Adapter);
        return view;
    }
}
