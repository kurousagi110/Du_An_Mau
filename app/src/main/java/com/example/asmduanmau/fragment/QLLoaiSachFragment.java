package com.example.asmduanmau.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmduanmau.Adapter.LoaiSachAdapter;
import com.example.asmduanmau.DAO.LoaiSachDAO;
import com.example.asmduanmau.Model.LoaiSach;
import com.example.asmduanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;

public class QLLoaiSachFragment extends Fragment {
    LoaiSachDAO loaiSachDAO;
    List<LoaiSach> list;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlloaisach,container,false);

        recyclerView = view.findViewById(R.id.recycleQLLoaiSach);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatThemLoaiSach);
        LoadData();


        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themloaisach,null);
        EditText edtThemLoaiSach = view.findViewById(R.id.edtThemLoaiSach);

        builder.setView(view);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenLoai = edtThemLoaiSach.getText().toString();
                if (loaiSachDAO.insert(tenLoai)){
                    LoadData();
                }else {
                    Toast.makeText(getContext(), "Thêm loại sách thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void LoadData(){
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);

        loaiSachDAO = new LoaiSachDAO(getContext());
        list = loaiSachDAO.getDSLoaiSach();

        LoaiSachAdapter loaiSachAdapter = new LoaiSachAdapter(getContext(),list);
        recyclerView.setAdapter(loaiSachAdapter);
    }
}
