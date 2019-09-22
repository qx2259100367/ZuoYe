package com.example.day02_homework1.view;



import com.example.day02_homework1.Beans.FuliBean;

import java.util.List;

public interface FuliView {
     void onSuccess(List<FuliBean.ResultsBean> resultsBeans);
     void onFailed(String str);
}
