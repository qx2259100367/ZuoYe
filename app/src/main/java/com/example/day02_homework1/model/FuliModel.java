package com.example.day02_homework1.model;

import com.example.day02_homework1.ApiServer;
import com.example.day02_homework1.Beans.FuliBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 裘翔 on 2019/8/21.
 */

public class FuliModel {
    public void loadDatas(final MyCallBask myCallBask){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.FuliUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<FuliBean> observable = apiServer.getDatas();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuliBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FuliBean fuliBean) {
                        List<FuliBean.ResultsBean> results = fuliBean.getResults();
                        myCallBask.onSuccess(results);
                    }

                    @Override
                    public void onError(Throwable e) {
                         myCallBask.onFailed(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface MyCallBask{
         void onSuccess(List<FuliBean.ResultsBean> beans);
         void onFailed(String str);
    }
}
