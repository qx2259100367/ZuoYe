package com.example.day02_homework1;

import com.example.day02_homework1.Beans.FuliBean;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ApiServer {
    String FuliUrl = "http://gank.io/api/";

    @GET("data/%E7%A6%8F%E5%88%A9/20/1")
    Observable<FuliBean> getDatas();
}
