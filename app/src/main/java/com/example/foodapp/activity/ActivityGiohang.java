package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.adapter.GiohangAdapter;
import com.example.foodapp.adapter.SpAdapter;
import com.example.foodapp.model.Giohang;
import com.example.foodapp.model.GiohangModel;
import com.example.foodapp.retrofit.ApiBanHang;
import com.example.foodapp.retrofit.RetrofitClient;
import com.example.foodapp.utils.Utils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ActivityGiohang extends AppCompatActivity {
    ImageView addicon,removeicon;
    TextView giohangtrong,tonggiohang;
    Toolbar toolbar;
    RecyclerView recyclerViewgiohang;
    ApiBanHang apiBanHang;
    List<Giohang> giohangList;
    GiohangAdapter giohangAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        initView();
        ActionToolBar();
        initControl();
        getData();

    }

    private void getData() {
        String txtidkhach =Integer.toString(Utils.usercuren.getId());
        compositeDisposable.add(apiBanHang.getgiohang(txtidkhach)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        giohangModel -> {
                            if(giohangModel.isSuccess()){
                                giohangList = giohangModel.getResult();
                                //khoi tao adapter
                                giohangAdapter = new GiohangAdapter(getApplicationContext(),giohangList);
                                recyclerViewgiohang.setAdapter(giohangAdapter);
                            }
                        },
                        throwable -> {
                            giohangtrong.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(),"không get dc gio hang"+throwable.getMessage(),Toast.LENGTH_LONG).show();
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

    private void initControl() {
        recyclerViewgiohang.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewgiohang.setLayoutManager(layoutManager);


    }

    private void initView() {

        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        giohangtrong = findViewById(R.id.giohangtrong);
        toolbar = findViewById(R.id.toolbargiohang);

        tonggiohang = findViewById(R.id.txttonggiohang);
        recyclerViewgiohang = findViewById(R.id.recyclerviewgiohang);


    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}