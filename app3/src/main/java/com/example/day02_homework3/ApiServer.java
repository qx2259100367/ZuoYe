package com.example.day02_homework3;

import com.example.day02_homework3.bean.MainBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 裘翔 on 2019/9/22.
 */

public interface ApiServer {
    String MainUrl="https://api.yunxuekeji.cn/yunxue_app_api/";
    @GET("content/getData/30/66597/1/10")
    Observable<MainBean> getMainData();
}
