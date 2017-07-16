package com.huida.zsxs.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huida.zsxs.R;
import com.huida.zsxs.bean.SpecialBean;

import org.xutils.x;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by lenovo on 2017/7/16.
 */

public class SpecialListViewAdapter extends BaseAdapter {
    private Context context;
    private SpecialBean bean;

    public SpecialListViewAdapter(SpecialBean bean, Context context) {
        this.context = context;
        this.bean = bean;
    }

    public void setLists(List<SpecialBean.CourseBean> lists) {
        bean.setCourse(lists);
    }

    @Override
    public int getCount() {
        return bean.getCourse().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Handler handler;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.special_lv_item, null);
            handler = new Handler();
            handler.icon = (ImageView) convertView.findViewById(R.id.special_icon);
            handler.title = (TextView) convertView.findViewById(R.id.special_title);
            handler.time = (TextView) convertView.findViewById(R.id.special_time);
            handler.jifen = (TextView) convertView.findViewById(R.id.special_jifen);
            handler.hot = (TextView) convertView.findViewById(R.id.special_hot);

            handler.icon.setBackgroundResource(R.mipmap.duanwang_icon);
            convertView.setTag(handler);


        }else{
            handler = (Handler) convertView.getTag();
        }
        SpecialBean.CourseBean bean = this.bean.getCourse().get(position);
        Log.d(TAG, "getView: "+bean);

        x.image().bind(handler.icon,bean.getImg());

        handler.title.setText(bean.getTitle());
        handler.time.setText(bean.getKeshi()+"课时");
        handler.hot.setText(bean.getHot()+"");
        handler.jifen.setText(bean.getMoney()+"积分");

        return convertView;
    }

    class Handler {
        ImageView icon;
        TextView title;
        TextView time;
        TextView jifen;
        TextView hot;
    }
}