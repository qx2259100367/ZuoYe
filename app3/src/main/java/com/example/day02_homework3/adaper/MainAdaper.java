package com.example.day02_homework3.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day02_homework3.R;
import com.example.day02_homework3.bean.MainBean;

import java.util.ArrayList;

/**
 * Created by 裘翔 on 2019/9/22.
 */

public class MainAdaper extends RecyclerView.Adapter<MainAdaper.ViewHolder>{
    private Context context;
    private ArrayList<MainBean.BodyBean.ResultBean> list;

    public MainAdaper(Context context, ArrayList<MainBean.BodyBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MainAdaper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.main_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainAdaper.ViewHolder holder, final int position) {
         holder.main_indent.setText(list.get(position).getTitle());
         holder.main_name.setText(list.get(position).getTeacherName());
        Glide.with(context).load(list.get(position).getTeacherPic()).apply(new RequestOptions().circleCrop()).into(holder.main_img);
         holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View v) {
                 a.setonLongIten(v,position);
                 return false;
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView main_img;
        TextView main_name;
        TextView main_indent;
        public ViewHolder(View itemView) {
            super(itemView);
            main_img=itemView.findViewById(R.id.main_img);
            main_name=itemView.findViewById(R.id.main_name);
            main_indent=itemView.findViewById(R.id.main_indent);
        }
    }
    public A a;

    public void setA(A a) {
        this.a = a;
    }
    public interface A{

        void setonLongIten(View v, int position);
    }
}
