package com.huida.zsxs.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huida.zsxs.R;
import com.huida.zsxs.bean.HomeListViewBean;

import org.xutils.x;

import static android.content.ContentValues.TAG;

/**
 * Created by lenovo on 2017/6/24.
 */

public class HomeGridViewAdapter extends BaseAdapter {

    private HomeListViewBean bean;
    private Activity mActivity;

    public HomeGridViewAdapter(HomeListViewBean bean, Activity activity) {
        this.bean = bean;
        this.mActivity = activity;

        Log.d(TAG, "HomeGridViewAdapter: " + bean);
    }


    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d(TAG,position+ "getView: "+bean.getCourse().size());

        Handler handler;
        LinearLayout linearLayout;
        HomeListViewBean.CourseBean courseBean = bean.getCourse().get(position);

        if (convertView == null) {
            convertView = linearLayout = (LinearLayout) View.inflate(mActivity, R.layout.home_gv_adapter_item, null);
            handler = new Handler();

            handler.imageView = (ImageView) linearLayout.getChildAt(0);
            handler.textViews[0] = (TextView) linearLayout.getChildAt(1);
            handler.textViews[1] = (TextView) linearLayout.getChildAt(2);

            linearLayout = (LinearLayout) linearLayout.getChildAt(3);

            handler.textViews[2] = (TextView) linearLayout.getChildAt(0);
            handler.textViews[3] = (TextView) linearLayout.getChildAt(1);


            convertView.setTag(handler);
        }else{
            handler = (Handler) convertView.getTag();

        }

        x.image().bind(handler.imageView,courseBean.getImg());

        handler.textViews[0].setText(courseBean.getTitle());
        handler.textViews[1].setText(courseBean.getInfo());
        handler.textViews[2].setText(courseBean.getKeshi() + "课时");
        handler.textViews[3].setText(courseBean.getHot()+"");



        return convertView;
    }

    class Handler {
        public ImageView imageView;
        public TextView[] textViews = new TextView[4];


    }

}
