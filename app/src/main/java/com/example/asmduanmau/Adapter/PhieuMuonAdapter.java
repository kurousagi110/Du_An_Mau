package com.example.asmduanmau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmduanmau.DAO.PhieuMuonDAO;
import com.example.asmduanmau.Model.PhieuMuon;
import com.example.asmduanmau.R;

import java.util.List;
import java.util.zip.Inflater;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.Viewholder>{
    private List<PhieuMuon> list;
    private Context context;

    public PhieuMuonAdapter(List<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.txtMaPM.setText("Mã PM: "+list.get(position).getMaPM());
        holder.txtMaTV.setText("Mã TV: "+list.get(position).getMaTV());
        holder.txtMaTT.setText("Mã TT: "+list.get(position).getMaTT());
        holder.txtMaSach.setText("Mã Sách: "+list.get(position).getMaSach());
        holder.txtNgayMuon.setText("Ngày: "+list.get(position).getNgayMuon());
        String TrangThai = "";
        if (list.get(position).getTraSach()==1){
            TrangThai = "Đã trả sách";
            holder.btnTraSach.setVisibility(View.GONE);
        }else {
            TrangThai = "Chưa trả sách";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }
        holder.txtTrangThai.setText("Trạng Thái: " +TrangThai);
        holder.txtTenTV.setText("Tên TV: "+list.get(position).getTenTV());
        holder.txtTenTT.setText("Tên TT: "+list.get(position).getTenTT());
        holder.txtTenSach.setText("Tên Sách: "+list.get(position).getTenSach());
        holder.txtTienThue.setText("Tiền Thuê: "+list.get(position).getTienThue());

        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(context);
                boolean kiemtra = phieuMuonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMaPM());
                if (kiemtra){
                    list.clear();
                    list=phieuMuonDAO.getData();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView txtMaPM, txtMaTV, txtMaTT, txtMaSach, txtNgayMuon, txtTrangThai, txtTenTV, txtTenTT, txtTenSach, txtTienThue;
        Button btnTraSach;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtMaPM = itemView.findViewById(R.id.txtMaPM);
            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtMaTT = itemView.findViewById(R.id.txtMaTT);
            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtNgayMuon = itemView.findViewById(R.id.txtNgayMuon);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            txtTenTV = itemView.findViewById(R.id.txtTenTV);
            txtTenTT = itemView.findViewById(R.id.txtTenTT);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtTienThue = itemView.findViewById(R.id.txtTienThue);
            btnTraSach = itemView.findViewById(R.id.btnTraSach);
        }
    }
}
