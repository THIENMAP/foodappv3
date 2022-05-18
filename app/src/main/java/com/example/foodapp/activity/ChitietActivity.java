package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.model.Sanpham;

import java.text.DecimalFormat;

public class ChitietActivity extends AppCompatActivity {
    TextView tensp,giasp,mota;
    Button btnchitietthem;
    ImageView imgchitietsp;
    Spinner spinner;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);
        initView();
        ActionToolBar();
        initData();
    }

    private void initData() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("chitiet");
        tensp.setText(sanpham.getTensp());
        giasp.setText(sanpham.getGia());
        mota.setText(sanpham.getMota());
        Glide.with(getApplicationContext()).load(sanpham.getUrlanh()).into(imgchitietsp);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        giasp.setText(decimalFormat.format(Double.parseDouble(sanpham.getGia()))+"đ");
        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
        spinner.setAdapter(adapterspin);
    }

    private void initView() {
        giasp = findViewById(R.id.item_giachitiet);
        tensp = findViewById(R.id.item_tenchitiet);
        mota = findViewById(R.id.item_motachitiet);
        imgchitietsp = findViewById(R.id.img_chitiet);
        btnchitietthem = findViewById(R.id.btngiohang);
        spinner = findViewById(R.id.spinner_chitiet);
        toolbar = findViewById(R.id.toolbarchitiet);

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
}