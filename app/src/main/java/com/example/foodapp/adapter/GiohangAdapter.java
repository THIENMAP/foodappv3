package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.model.Giohang;
import com.example.foodapp.model.Sanpham;

import java.text.DecimalFormat;
import java.util.List;

public class GiohangAdapter extends RecyclerView.Adapter<GiohangAdapter.MyViewHolder> {
    Context context;
    List<Giohang> giohangList;

    public GiohangAdapter(Context context, List<Giohang> giohangList) {
        this.context = context;
        this.giohangList = giohangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.giohangitem1,parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Giohang giohang = giohangList.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giasp.setText(decimalFormat.format(Double.parseDouble(Long.toString(giohang.getGia())))+"đ");
        //String txt12 = ""+giohang.getHinhanh()+"-"+giohang.getTensp()+"-"+giohang.getSoluong()+"-"+giohang.getGia();
        Glide.with(context).load(giohang.getHinhanh()).into(holder.anhsanpham);
        holder.tensp.setText(giohang.getTensp());
        holder.soluongsp.setText(giohang.getSoluong());

    }

    @Override
    public int getItemCount() {
        return giohangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tensp,giasp,soluongsp;
        ImageView anhsanpham,addsl,removesl;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            removesl = itemView.findViewById(R.id.removesl);
            addsl = itemView.findViewById(R.id.addsl);
            tensp = itemView.findViewById(R.id.tenspgiohang);
            giasp = itemView.findViewById(R.id.giaspgiohang);
            soluongsp = itemView.findViewById(R.id.slspgiohang);
            anhsanpham = itemView.findViewById(R.id.item_giohang);
        }
    }
}
