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
import com.huida.zsxs.utils.StaticValue;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by lenovo on 2017/6/24.
 */

public class HomeGridViewAdapter extends BaseAdapter {

    private HomeListViewBean bean;
    private Activity mActivity;
    private List<HomeListViewBean.CourseBean> course;

    public HomeGridViewAdapter(HomeListViewBean bean, Activity activity) {
        this.bean = bean;
        this.mActivity = activity;
        course = bean.getCourse();

        Log.d(TAG, "HomeGridViewAdapter: " + bean);
    }


    @Override
    public int getCount() {
        return course.size();
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

        Log.d(TAG,position+ "getView: "+course.size());

        Handler handler;
        LinearLayout linearLayout;
        HomeListViewBean.CourseBean courseBean = course.get(position);

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

        int width = mActivity.getResources().getDisplayMetrics().widthPixels/2;
        int v = (int) StaticValue.get(mActivity, width);
        ImageOptions options = new ImageOptions.Builder()
                .setSize(200,200)
                .build();
        x.image().bind(handler.imageView,courseBean.getImg(),options);

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
