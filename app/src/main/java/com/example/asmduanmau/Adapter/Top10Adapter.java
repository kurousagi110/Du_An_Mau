package com.example.asmduanmau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmduanmau.Model.Sach;
import com.example.asmduanmau.Model.Top;
import com.example.asmduanmau.R;

import java.util.ArrayList;
import java.util.List;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder>{
    private Context context;
    private List<Sach> list;

    public Top10Adapter(Context context, List<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top10,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaSachTop10.setText("Mã Sách "+ list.get(position).getMaSach());
        holder.txtTenSachTop10.setText("Tên Sách: "+list.get(position).getTenSach());
        holder.txtSoLuongTop10.setText("Số lượng: "+list.get(position).getSoLuongDaMuon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaSachTop10,txtTenSachTop10,txtSoLuongTop10;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtMaSachTop10 = itemView.findViewById(R.id.txtMaSachTop10);
            txtTenSachTop10 = itemView.findViewById(R.id.txtTenSachTop10);
            txtSoLuongTop10 = itemView.findViewById(R.id.txtSoLuongTop10);
        }
    }
}
