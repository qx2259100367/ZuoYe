package com.example.day02_homework2.view;


import com.example.day02_homework2.DatasBean;

import java.util.List;

/**
 * Created by 裘翔 on 2019/8/22.
 */

public interface ProView {
    void onSuccess(List<DatasBean> datasBeans);
    void showToast(String str);
}
