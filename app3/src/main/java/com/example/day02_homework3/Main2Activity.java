package com.example.day02_homework3;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day02_homework3.adaper.FragmentAdaper;
import com.example.day02_homework3.fragment.JieShaoFragment;
import com.example.day02_homework3.fragment.KeChengFragment;
import com.example.day02_homework3.fragment.ZhuanFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {
    @BindView(R.id.teacher_tab)
    TabLayout mTeacherTab;
    @BindView(R.id.tea_img)
    ImageView mTeaImg;
    @BindView(R.id.tea_name)
    TextView mTeaName;
    @BindView(R.id.tea_title)
    TextView mTeaTitle;
    @BindView(R.id.tea_vp)
    ViewPager mTeaVp;
    private ArrayList<Fragment> fragments;
    private FragmentAdaper adaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        initRec();
        initTab();
    }

    private void initTab() {
        fragments = new ArrayList<>();
        fragments.add(new JieShaoFragment());
        fragments.add(new KeChengFragment());
        fragments.add(new ZhuanFragment());
        adaper = new FragmentAdaper(getSupportFragmentManager(), fragments);
         mTeaVp.setAdapter(adaper);
         mTeacherTab.setupWithViewPager(mTeaVp);
         mTeacherTab.getTabAt(0).setText("介绍");
         mTeacherTab.getTabAt(1).setText("课程");
         mTeacherTab.getTabAt(2).setText("专题");
    }

    private void initRec() {
        String img = getIntent().getStringExtra("img");
        String name = getIntent().getStringExtra("name");
        String title = getIntent().getStringExtra("title");
        Glide.with(this).load(img).apply(new RequestOptions().circleCrop()).into(mTeaImg);
        mTeaName.setText(name);
        mTeaTitle.setText(title);

    }
}
