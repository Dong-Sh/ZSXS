package com.huida.zsxs.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huida.zsxs.R;
import com.huida.zsxs.bean.HomeListViewBean;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by lenovo on 2017/6/24.
 */

public class HomeGridViewAdapter extends BaseAdapter {

    private HomeListViewBean bean;
    private Activity mActivity;

    public HomeGridViewAdapter(HomeListViewBean bean, Activity activity) {
        this.bean = bean;
        this.mActivity = activity;
    }

    public HomeListViewBean getBean() {
        return bean;
    }

    public void setBean(HomeListViewBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getCourse().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getCourse().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private ImageOptions options = new ImageOptions.Builder().build();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Handler handler;
        LinearLayout linearLayout;
        Log.d("Dongsh", "getView: ");
        HomeListViewBean.CourseBean courseBean = bean.getCourse().get(position);
        if (convertView == null) {
            convertView = linearLayout = (LinearLayout) View.inflate(mActivity, R.layout.home_gv_adapter_item, null);
            handler = new Handler();

            handler.imageView = (ImageView) linearLayout.getChildAt(0);


            /*handler.textViews[1] = (TextView) linearLayout.getChildAt(1);
            handler.textViews[2] = (TextView) linearLayout.getChildAt(2);

            linearLayout = (LinearLayout) linearLayout.getChildAt(3);

            handler.textViews[3] = (TextView) linearLayout.getChildAt(0);
            handler.textViews[4] = (TextView) linearLayout.getChildAt(1);*/

            convertView.setTag(handler);
        } else {
            handler = (Handler) convertView.getTag();
        }


        x.image().loadDrawable(courseBean.getImg(), options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                handler.imageView.setBackground(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        /*handler.textViews[1].setText(courseBean.getTitle());
        handler.textViews[2].setText(courseBean.getInfo());
        handler.textViews[3].setText(courseBean.getKeshi() + "课时");
        handler.textViews[4].setText(courseBean.getHot());*/

        return convertView;
    }

    class Handler {
        public ImageView imageView;
        public TextView[] textViews;


    }

}
