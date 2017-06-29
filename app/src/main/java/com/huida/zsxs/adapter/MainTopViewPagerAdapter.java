package com.huida.zsxs.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.huida.zsxs.R;
import com.huida.zsxs.activity.SpecialActivity;
import com.huida.zsxs.bean.TopSlidesBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by lenovo on 2017/6/25.
 */

public class MainTopViewPagerAdapter extends PagerAdapter {


    private Activity mActivity;
    private List<TopSlidesBean.SlidesBean> slidesBeanList;
    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

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
        im.setScaleType(ImageView.ScaleType.FIT_XY);
        if (position < 0) {
            position = slidesBeanList.size() + position;
        }

        final TopSlidesBean.SlidesBean slidesBean = slidesBeanList.get(position);

        x.image().bind(im, slidesBean.getPic());


        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidesBean.getPictype().equals("app")) {
                    Intent intent = new Intent(mActivity, SpecialActivity.class);

                    intent.putExtra("pic", slidesBean.getPic());
                    intent.putExtra("title", slidesBean.getTitle());
                    intent.putExtra("picURL", slidesBean.getPic());

                    mActivity.startActivity(intent);

                } else {

                }
            }
        });

        container.addView(im);
        return im;


    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}