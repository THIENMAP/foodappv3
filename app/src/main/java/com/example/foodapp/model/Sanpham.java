package com.example.foodapp.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    int id;
    int id_danhmuc;
    String tensp;
    String gia;
    String mota;
    String urlanh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_danhmuc() {
        return id_danhmuc;
    }

    public void setId_danhmuc(int id_danhmuc) {
        this.id_danhmuc = id_danhmuc;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getUrlanh() {
        return urlanh;
    }

    public void setUrlanh(String urlanh) {
        this.urlanh = urlanh;
    }
}
