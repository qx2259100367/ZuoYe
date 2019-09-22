package com.example.day02_homework2.presenter;


import com.example.day02_homework2.DatasBean;
import com.example.day02_homework2.model.ProModel;
import com.example.day02_homework2.view.ProView;

import java.util.List;

public class ProPresenter implements ProModel.MyCallBask {
    private ProView proView;
    private ProModel proModel;

    public ProPresenter(ProView proView) {
        this.proView = proView;
        this.proModel = new ProModel();
    }

    public void loadDatas() {
        proModel.loadDatas(this);
    }

    @Override
    public void onSuccess(List<DatasBean> datas) {
           proView.onSuccess(datas);
    }

    @Override
    public void onFailed(String str) {
          proView.showToast(str);
    }
}
