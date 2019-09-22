package com.example.day02_homework3.view;

import com.example.day02_homework3.bean.MainBean;

import java.util.List;

/**
 * Created by 裘翔 on 2019/9/22.
 */

public interface MainView {
    void onSuccess(List<MainBean.BodyBean.ResultBean> resultBeans);
    void onFailed(String str);
}
