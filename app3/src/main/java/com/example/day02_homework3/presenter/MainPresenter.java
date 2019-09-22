package com.example.day02_homework3.presenter;
import com.example.day02_homework3.bean.MainBean;
import com.example.day02_homework3.model.MainModel;
import com.example.day02_homework3.view.MainView;

import java.util.List;

/**
 * Created by 裘翔 on 2019/9/22.
 */

public class MainPresenter implements MainModel.MyCallBask {
    private MainView mainView;
    private MainModel mainModel;
    public MainPresenter(MainView mainView){
        this.mainView=mainView;
        this.mainModel=new MainModel();
    }

    public void loadDatas() {
       mainModel.loadDatas(this);
    }

    @Override
    public void onSuccess(List<MainBean.BodyBean.ResultBean> results) {
          mainView.onSuccess(results);
    }

    @Override
    public void onFailed(String str) {
          mainView.onFailed(str);
    }
}
