package com.example.asmduanmau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmduanmau.DAO.LoaiSachDAO;
import com.example.asmduanmau.Model.LoaiSach;
import com.example.asmduanmau.Model.PhieuMuon;
import com.example.asmduanmau.R;

import java.util.List;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{
    private Context context;
    private List<LoaiSach> list;
    private LoaiSachDAO loaiSachDAO;


    public LoaiSachAdapter( Context context,List<LoaiSach> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaLoaiSach.setText("Mã loại sách:"+list.get(position).getMaLoaiSach());
        holder.txtTenLoaiSach.setText("Tên loại sách:"+list.get(position).getTenLoaiSach());
        holder.ivXoaLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiSachDAO = new LoaiSachDAO(context);
                int check = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getMaLoaiSach());
                switch (check){
                    case 1:
                        reloadData();
                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xoá do có sách thuộc thể loại này", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xoá không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        holder.ivSuaLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSua(holder.getAdapterPosition(),list.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivXoaLoaiSach,ivSuaLoaiSach;
        TextView txtMaLoaiSach, txtTenLoaiSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoaiSach = itemView.findViewById(R.id.txtMaLoaiSach);
            txtTenLoaiSach = itemView.findViewById(R.id.txtTenLoaiSach);
            ivXoaLoaiSach = itemView.findViewById(R.id.ivXoaLoaiSach);
            ivSuaLoaiSach = itemView.findViewById(R.id.ivSuaLoaiSach);
        }
    }
    private void showDialogSua(int i, LoaiSach loai){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_sualoaisach, null);
        EditText edtSuaLoaiSach = view.findViewById(R.id.edtSuaLoaiSach);
        edtSuaLoaiSach.setText(""+list.get(i).getTenLoaiSach());
        builder.setView(view);

        builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenLoaiSach = edtSuaLoaiSach.getText().toString();

                int a = list.get(i).getMaLoaiSach();
                Log.d("abc", (a + tenLoaiSach));
                boolean check = loaiSachDAO.updateLoaiSach(list.get(i).getMaLoaiSach(),tenLoaiSach);
                if (check){
                    Toast.makeText(context, "update ok", Toast.LENGTH_SHORT).show();
                    reloadData();
                }else {
                    Toast.makeText(context, "update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "huy", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void reloadData(){
        list.clear();
        list = loaiSachDAO.getDSLoaiSach();
        notifyDataSetChanged();
    }
}
