package com.example.foodapp.model;

public class Giohang {
    int idgiohang;
    int idsp;
    String sosanphammua;
    String idkhanhhang;
    String tensp;
    long gia;
    String urlanh;

    public Giohang(){
    }

    public int getIdgiohang() {
        return idgiohang;
    }

    public void setIdgiohang(int idgiohang) {
        this.idgiohang = idgiohang;
    }

    public String getIdkhanhhang() {
        return idkhanhhang;
    }

    public void setIdkhanhhang(String idkhanhhang) {
        this.idkhanhhang = idkhanhhang;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getSoluong() {
        return sosanphammua;
    }

    public void setSoluong(String soluong) {
        this.sosanphammua = soluong;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getHinhanh() {

        return urlanh;
    }

    public void setHinhanh(String hinhanh) {
        this.urlanh = hinhanh;
    }
}
