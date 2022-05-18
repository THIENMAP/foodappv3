package com.example.foodapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.foodapp.R;
import com.example.foodapp.adapter.Loaispadapter;
import com.example.foodapp.adapter.RaucuAdapter;
import com.example.foodapp.adapter.SpAdapter;
import com.example.foodapp.model.Loaisp;
import com.example.foodapp.model.Sanpham;
import com.example.foodapp.retrofit.ApiBanHang;
import com.example.foodapp.retrofit.RetrofitClient;
import com.example.foodapp.utils.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RaucuActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewraucu;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page=1;
    int loai;
    RaucuAdapter raucuAdapter;
    List<Sanpham> sanphamList;
    GridLayoutManager layoutManager;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    boolean isLoading = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raucu);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        loai = getIntent().getIntExtra("loai",2);

        AnhXa();
        ActionToolBar();
        getData(page);
        addEventLoad();

    }

    private void addEventLoad() {
        recyclerViewraucu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isLoading == false){
                    if(layoutManager.findLastCompletelyVisibleItemPosition() == sanphamList.size()-1){
                        isLoading = true;
                        loadmore();
                    }
                }
            }
        });
    }

    private void loadmore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                sanphamList.add(null);
                raucuAdapter.notifyItemInserted(sanphamList.size()-1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sanphamList.remove(sanphamList.size()-1);
                raucuAdapter.notifyItemRemoved(sanphamList.size());
                page = page +1;
                getData(page);
                raucuAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        },2000);
    }

    private void getData(int page) {
        compositeDisposable.add(apiBanHang.getSanPhamRC(page,loai)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                SanphamModel -> {
                    if(SanphamModel.isSuccess()){
                        if(raucuAdapter == null){
                            sanphamList = SanphamModel.getResult();
                            //khoi tao adapter
                            raucuAdapter = new RaucuAdapter(getApplicationContext(),sanphamList);
                            recyclerViewraucu.setAdapter(raucuAdapter);
                        }else {
                            int vitri = sanphamList.size()-1;
                            int soluongadd = SanphamModel.getResult().size();
                            for(int i = 0;i<soluongadd;i++){
                                sanphamList.add(SanphamModel.getResult().get(i));
                            }
                            raucuAdapter.notifyItemRangeInserted(vitri,soluongadd);
                        }

                    }else {
                        Toast.makeText(getApplicationContext(),"Hết dữ liệu",Toast.LENGTH_LONG).show();
                        isLoading=true;
                    }
                },
                throwable -> {
                    Toast.makeText(getApplicationContext(),"không kết nối được sever"+throwable.getMessage(),Toast.LENGTH_LONG).show();
                }
        ));
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarraucu);
        recyclerViewraucu = findViewById(R.id.recyclerviewraucu);
        layoutManager = new GridLayoutManager(this,2);
        recyclerViewraucu.setLayoutManager(layoutManager);
        recyclerViewraucu.setHasFixedSize(true);
        sanphamList = new ArrayList<>();
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}