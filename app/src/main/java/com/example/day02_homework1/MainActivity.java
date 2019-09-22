package com.example.day02_homework1;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.day02_homework1.Beans.FuliBean;
import com.example.day02_homework1.adaper.FuliAdaper;
import com.example.day02_homework1.presenter.FuliPresenter;
import com.example.day02_homework1.view.FuliView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FuliView {

    @BindView(R.id.MyRec)
    RecyclerView mMyRec;
    @BindView(R.id.MyVp)
    ViewPager mMyVp;
    private FuliPresenter fuliPresenter;
    private ArrayList<FuliBean.ResultsBean> list;
    private FuliAdaper adaper;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fuliPresenter = new FuliPresenter(this);
        initRec();
    }

    private void initRec() {
        mMyRec.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        // mMyRec.addItemDecoration(new DividerItemDecoration(this,LinearLayout.VERTICAL));
        fuliPresenter.loadDatas();
        list = new ArrayList<>();
        adaper = new FuliAdaper(list, this);
        mMyRec.setAdapter(adaper);
        adaper.setA(new FuliAdaper.A() {
            @Override
            public void setonLongClick(View v, int position) {
                mMyRec.setVisibility(View.GONE);
                mMyVp.setVisibility(View.VISIBLE);
                initViewPage(position);
            }
        });
    }

    private void initViewPage(int position) {
        final ArrayList<FuliBean.ResultsBean> fulilist = adaper.list;
        PagerAdapter adapter = new PagerAdapter(){
            @Override
            public int getCount() {
                return fulilist.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.img_item, null);
                ImageView imgs = view.findViewById(R.id.img);
                TextView tvs = view.findViewById(R.id.tv);
                Glide.with(MainActivity.this).load(fulilist.get(position).getUrl()).into(imgs);
                tvs.setText((position+1)+"/"+fulilist.size());
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        };
        mMyVp.setAdapter(adapter);
        mMyVp.setCurrentItem(position);
    }

    @Override
    public void onSuccess(List<FuliBean.ResultsBean> resultsBeans) {
        list.addAll(resultsBeans);
        adaper.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
