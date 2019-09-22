package com.example.day02_homework2.model;


import com.example.day02_homework2.ApiServer;
import com.example.day02_homework2.DatasBean;
import com.example.day02_homework2.UserBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProModel {
    public void loadDatas(final MyCallBask myCallBask){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.ProUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<UserBean> observable = apiServer.getDatas();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        List<DatasBean> beans = userBean.getDatas();
                        myCallBask.onSuccess(beans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        myCallBask.onFailed("请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface MyCallBask{
         void onSuccess(List<DatasBean> datas);
         void onFailed(String str);
    }
}
