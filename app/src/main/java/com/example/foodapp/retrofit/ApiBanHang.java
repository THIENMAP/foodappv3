package com.example.foodapp.retrofit;

import com.example.foodapp.model.LoaispModel;
import com.example.foodapp.model.Sanpham;
import com.example.foodapp.model.SanphamModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiBanHang {
    @GET("androi_getloaisp.php")
    Observable<LoaispModel> androi_getloaiSp();

    @GET("androi_getsanpham.php")
    Observable<SanphamModel> androi_getsanpham();

    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<SanphamModel> getSanPhamRC(
            @Field("page") int page,
            @Field("loai") int loai
    );
}
