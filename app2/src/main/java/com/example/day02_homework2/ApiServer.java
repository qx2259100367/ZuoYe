package com.example.day02_homework2;


import io.reactivex.Observable;
import retrofit2.http.GET;
public interface ApiServer {
    String ProUrl="http://static.owspace.com/";

    @GET("?c=api&a=getList&page_id=0")
    Observable<UserBean> getDatas();
}
