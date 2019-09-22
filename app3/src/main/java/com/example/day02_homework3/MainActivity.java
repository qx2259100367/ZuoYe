package com.example.day02_homework3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.day02_homework3.adaper.MainAdaper;
import com.example.day02_homework3.bean.MainBean;
import com.example.day02_homework3.presenter.MainPresenter;
import com.example.day02_homework3.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.myRec)
    RecyclerView mMyRec;
    private MainPresenter mainPresenter;
    private ArrayList<MainBean.BodyBean.ResultBean> beans;
    private MainAdaper mainAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this);
        initToolbar();
        initRec();
    }

    private void initRec(){
        mMyRec.setLayoutManager(new LinearLayoutManager(this));
        mMyRec.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        beans = new ArrayList<>();
        mainPresenter.loadDatas();
        mainAdaper = new MainAdaper(this, beans);
        mMyRec.setAdapter(mainAdaper);
        mainAdaper.setA(new MainAdaper.A() {
            @Override
            public void setonLongIten(View v, int position) {
                MainBean.BodyBean.ResultBean resultBean = beans.get(position);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("img",resultBean.getTeacherPic());
                intent.putExtra("name",resultBean.getTeacherName());
                intent.putExtra("title",resultBean.getTitle());
                startActivity(intent);
            }
        });
    }

    private void initToolbar(){
        mToolbar.setTitle("名师推荐");
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onSuccess(List<MainBean.BodyBean.ResultBean> resultBeans) {
       beans.addAll(resultBeans);
       mainAdaper.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
