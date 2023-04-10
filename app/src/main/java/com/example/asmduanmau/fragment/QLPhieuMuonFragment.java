package com.example.asmduanmau.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmduanmau.Adapter.PhieuMuonAdapter;
import com.example.asmduanmau.DAO.PhieuMuonDAO;
import com.example.asmduanmau.DAO.SachDAO;
import com.example.asmduanmau.DAO.ThanhVienDAO;
import com.example.asmduanmau.Model.PhieuMuon;
import com.example.asmduanmau.Model.Sach;
import com.example.asmduanmau.Model.ThanhVien;
import com.example.asmduanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class QLPhieuMuonFragment extends Fragment {
    PhieuMuonDAO phieuMuonDAO;
    RecyclerView recyclerView;
    List<PhieuMuon> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlphieumuon, container, false);
        //ánh xạ
         recyclerView = view.findViewById(R.id.recycleQLPhieuMuon);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);
        //giao diện

        //data

        //adapter
        LoadData();


        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }
    private  void LoadData(){
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        PhieuMuonAdapter adapter  = new PhieuMuonAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_thempm,null);
        Spinner spnThanhVien = view.findViewById(R.id.spnThanhVien);
        Spinner spnSach = view.findViewById(R.id.spnSach);

        getDataThanhVien(spnThanhVien);
        getDataSach(spnSach);
        builder.setView(view);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //get mã tv
                HashMap<String,Object> hsTV = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int maTV = (int) hsTV.get("maTV");
                //get ma sach:
                HashMap<String,Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                int maSach = (int) hsSach.get("maSach");

                int tien = (int) hsSach.get("giaThue");

                themPhieuMuon(maTV,maSach,tien);
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
    private void getDataThanhVien(Spinner spnThanhVien){
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        List<ThanhVien> list = thanhVienDAO.getDSThanhVien();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (ThanhVien tv: list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("maTV",tv.getMaTV());
            hs.put("hoTen",tv.getHoTen());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter =new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoTen"},
                new int[]{android.R.id.text1});
        spnThanhVien.setAdapter(simpleAdapter);
    }
    private void getDataSach(Spinner spnSach){
        SachDAO sachDAO = new SachDAO(getContext());
        List<Sach> list = sachDAO.getDSSach();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Sach sach: list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("maSach",sach.getMaSach());
            hs.put("tenSach",sach.getTenSach());
            hs.put("giaThue",sach.getGiaThue());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter =new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenSach"},
                new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);
    }
    private void themPhieuMuon(int maTV, int maSach, int tien){
        //Lấy mã thủ thư
        SharedPreferences sharedPreferences  = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        String maTT = sharedPreferences.getString("maTT","");
        //lấy ngày hiện tại
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);
        PhieuMuon phieuMuon = new PhieuMuon(maTV, maTT,maSach, ngay, 0 ,tien);
        boolean kiemtra = phieuMuonDAO.insert(phieuMuon);
        if (kiemtra){
            LoadData();
            Toast.makeText(getContext(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getContext(), "Thêm phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
    }
}
