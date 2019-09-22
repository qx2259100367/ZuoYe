package com.example.day02_homework2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.day02_homework2.Adaper.RecAdaper;
import com.example.day02_homework2.presenter.ProPresenter;
import com.example.day02_homework2.view.ProView;
import com.example.xts.greendaodemo.db.DatasBeanDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ProView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.MyRec)
    RecyclerView mMyRec;
    private ProPresenter proPresenter;
    private DatasBeanDao beanDao;
    private ArrayList<DatasBean> list;
    private RecAdaper recAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        proPresenter = new ProPresenter(this);
        beanDao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
        initRec();
    }

    private void initRec() {
        mMyRec.setLayoutManager(new LinearLayoutManager(this));
        mMyRec.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        proPresenter.loadDatas();
        list = new ArrayList<>();
        recAdaper = new RecAdaper(list, this);
        mMyRec.setAdapter(recAdaper);
    }

    @Override
    public void onSuccess(List<DatasBean> datasBeans) {
        list.addAll(datasBeans);
        recAdaper.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
