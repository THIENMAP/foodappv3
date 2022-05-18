package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.model.Loaisp;

import java.util.List;

public class Loaispadapter extends BaseAdapter {
    List<Loaisp> array;
    Context context;

    public Loaispadapter(Context context,List<Loaisp> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder{
        TextView texttensp;
        ImageView anhloaisp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.itemloaisanpham,null);
            viewHolder.texttensp = view.findViewById(R.id.item_tenloaisp);
            viewHolder.anhloaisp = view.findViewById(R.id.item_imageloaisp);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();

        }
        viewHolder.texttensp.setText(array.get(i).getTendanhmuc());
        Glide.with(context).load(array.get(i).getHinhloaisp()).into(viewHolder.anhloaisp);
        return view;
    }
}
