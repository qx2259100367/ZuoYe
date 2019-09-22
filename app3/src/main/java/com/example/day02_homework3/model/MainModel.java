package com.example.day02_homework3.model;

import com.example.day02_homework3.ApiServer;
import com.example.day02_homework3.bean.MainBean;
import com.example.day02_homework3.presenter.MainPresenter;

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
 * Created by 裘翔 on 2019/9/22.
 */

public class MainModel {

    private Retrofit retrofit;

    public void loadDatas(final MyCallBask myCallBask){
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.MainUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<MainBean> observable = apiServer.getMainData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MainBean mainBean) {
                        List<MainBean.BodyBean.ResultBean> result = mainBean.getBody().getResult();
                        myCallBask.onSuccess(result);
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
        void onSuccess(List<MainBean.BodyBean.ResultBean> results);
        void onFailed(String str);
    }
}
