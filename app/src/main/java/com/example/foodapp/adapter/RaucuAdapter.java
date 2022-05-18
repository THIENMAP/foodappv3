package com.example.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Interface.ItemClickListener;
import com.example.foodapp.R;
import com.example.foodapp.activity.ChitietActivity;
import com.example.foodapp.model.Sanpham;

import java.text.DecimalFormat;
import java.util.List;

public class RaucuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Sanpham> array;
    private static final int VIEW_TYPE_DATA =0;
    private static final int VIEW_TYPE_LOADING =1;

    public RaucuAdapter(Context context, List<Sanpham> array) {
        this.context = context;
        this.array = array;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item1;
        if(viewType == VIEW_TYPE_DATA){
            item1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemraucu,parent,false);
            return new MyViewHolder(item1);
        }else {
            item1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load,parent,false);
            return new LoadingViewHolder(item1);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            Sanpham sanpham1 = array.get(position);
            myViewHolder.txttenraucu.setText(sanpham1.getTensp());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            myViewHolder.txtgiaraucu.setText(decimalFormat.format(Double.parseDouble(sanpham1.getGia()))+"đ");
            Glide.with(context).load(sanpham1.getUrlanh()).into(myViewHolder.imganhraucu);
            myViewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if(!isLongClick){
                        Intent intent = new Intent(context, ChitietActivity.class);
                        intent.putExtra("chitiet",sanpham1);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }else {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return array.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_DATA;
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressbarrc);
        }
    }



    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txttenraucu,txtgiaraucu;
        ImageView imganhraucu;
        private ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtgiaraucu = itemView.findViewById(R.id.item_giaraucu);
            txttenraucu = itemView.findViewById(R.id.item_tenraucu);
            imganhraucu = itemView.findViewById(R.id.item_imageraucu);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }
    }
}
