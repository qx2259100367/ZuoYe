package com.example.day02_homework2.Adaper;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.day02_homework2.BaseApp;
import com.example.day02_homework2.DatasBean;
import com.example.day02_homework2.R;
import com.example.xts.greendaodemo.db.DatasBeanDao;
import java.util.List;

public class RecAdaper extends RecyclerView.Adapter<RecAdaper.ViewHolder>{
    private List<DatasBean> list;
    private Context context;
    private DatasBeanDao beanDao;
    private List<DatasBean> all;

    public RecAdaper(List<DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rec_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        beanDao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
        RequestOptions options = new RequestOptions()
                   .circleCrop();
        Glide.with(context).load(list.get(position).getThumbnail()).apply(options).into(holder.rec_img);
        holder.rec_name.setText(list.get(position).getAuthor());
        holder.rec_title.setText(list.get(position).getTitle());
        SharedPreferences db = context.getSharedPreferences("db", Context.MODE_PRIVATE);
        String type = db.getString(position + "", "关注");
        holder.btn.setText(type);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btn = holder.btn.getText().toString();
                if (btn.equals("关注")){
                     beanDao.insertOrReplace(list.get(position));
                     holder.btn.setText("取消");
                }else if (btn.equals("取消")){
                      beanDao.deleteInTx(list.get(position));
                      holder.btn.setText("关注");
                }
                String type2 = holder.btn.getText().toString();
                SharedPreferences db1 = context.getSharedPreferences("db", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = db1.edit();
                edit.putString(position+"",type2);
                edit.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rec_name;
        TextView rec_title;
        ImageView rec_img;
        Button btn;
        public ViewHolder(View itemView) {
            super(itemView);
            rec_img=itemView.findViewById(R.id.rec_img);
            rec_name=itemView.findViewById(R.id.rec_name);
            rec_title=itemView.findViewById(R.id.rec_title);
            btn=itemView.findViewById(R.id.btn);
        }
    }
}
