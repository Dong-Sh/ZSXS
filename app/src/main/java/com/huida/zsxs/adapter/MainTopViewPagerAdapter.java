package com.huida.zsxs.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.huida.zsxs.R;
import com.huida.zsxs.bean.TopSlidesBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by lenovo on 2017/6/25.
 */

public class MainTopViewPagerAdapter extends PagerAdapter {


    private Activity mActivity;
    private List<TopSlidesBean.SlidesBean> slidesBeanList;
    ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public MainTopViewPagerAdapter(List<TopSlidesBean.SlidesBean> slidesBeanList, Activity mActivity) {
        this.slidesBeanList = slidesBeanList;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return 10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= slidesBeanList.size();
        ImageView im = new ImageView(mActivity);
        im.setBackgroundResource(R.mipmap.guodu_icon);

        im.setLayoutParams(params);

        if (position < 0) {
            position = slidesBeanList.size() + position;
        }

        TopSlidesBean.SlidesBean slidesBean = slidesBeanList.get(position);

        x.image().bind(im, slidesBean.getPic());


        container.addView(im);
        return im;


    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}