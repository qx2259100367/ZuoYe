package com.example.day02_homework3.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.day02_homework3.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 裘翔 on 2019/9/22.
 */

public class KeChengFragment extends Fragment {
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.video)
    VideoView mVideo;
    private View view;
    private Unbinder unbinder;
    String uri="https://yunxue-bucket.oss-cn-shanghai.aliyuncs.com/classfile/0/从技术走向管理/" +
            "001.从技术到管理_第1节_从技术到管理的内外部因素.mp4\n";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kecheng_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                MediaController controller = new MediaController(getContext());
                mVideo.setMediaController(controller);
                controller.setAnchorView(mVideo);
                mVideo.setVideoURI(Uri.parse(uri));
                mVideo.start();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
