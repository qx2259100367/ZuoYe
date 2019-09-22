package com.example.day02_homework1.presenter;



import com.example.day02_homework1.Beans.FuliBean;
import com.example.day02_homework1.model.FuliModel;
import com.example.day02_homework1.view.FuliView;

import java.util.List;



public class FuliPresenter implements FuliModel.MyCallBask {
    private FuliView fuliView;
    private FuliModel fuliModel;
    public FuliPresenter(FuliView fuliView) {
       this.fuliView=fuliView;
       this.fuliModel=new FuliModel();
    }

    public void loadDatas() {
      fuliModel.loadDatas(this);
    }

    @Override
    public void onSuccess(List<FuliBean.ResultsBean> beans) {
         fuliView.onSuccess(beans);
    }

    @Override
    public void onFailed(String str) {
        fuliView.onFailed(str);
    }
}
