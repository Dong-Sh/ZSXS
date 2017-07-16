package com.huida.zsxs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.huida.zsxs.R;
import com.huida.zsxs.adapter.SpecialListViewAdapter;
import com.huida.zsxs.bean.SpecialBean;
import com.huida.zsxs.bean.TopSlidesBean;
import com.huida.zsxs.utils.StaticValue;
import com.huida.zsxs.view.BackView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by lenovo on 2017/6/26.
 */

public class SpecialActivity extends Activity {

    private static final String TAG = "Special";
    private BackView special_back;
    private ListView special_lv;
    private TopSlidesBean.SlidesBean bean;
    private SpecialListViewAdapter lvAdapter;
    private String pic;
    private String title;
    private String types;
    private String page;
    private String picURL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sepcial);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        special_back = (BackView) findViewById(R.id.sepcial_back);
        special_lv = (ListView) findViewById(R.id.special_lv);
    }

    private void initData() {
        Intent intent = getIntent();

        pic = intent.getStringExtra("pic");
        title = intent.getStringExtra("title");
        types = intent.getStringExtra("Types");
        page = intent.getStringExtra("Page");
        picURL = intent.getStringExtra("picURL");


        Log.d(TAG, "initData1: " + types);

        getHttpData();


        special_back.setTitle(title);



    }

    private void initEvent() {
        special_back.setActivity(this);
    }

    public void getHttpData() {
        if(!TextUtils.isEmpty(pic)){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            x.image().bind(imageView, pic,new ImageOptions.Builder().setSize(0,0).build());
            special_lv.addHeaderView(imageView);
        }

        RequestParams entity = new RequestParams(StaticValue.Address);

        entity.addQueryStringParameter("Action", "getZTShow");
        entity.addQueryStringParameter("ztid", picURL);


        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String str = result.replaceAll("\t", "").replaceAll("\n", "");

                SpecialBean specialBean = new Gson().fromJson(str, SpecialBean.class);

                Log.d(TAG, "SpecialonSuccess: " + specialBean);

                special_back.setTitle(specialBean.getZTtitle());
                lvAdapter = new SpecialListViewAdapter(specialBean, SpecialActivity.this);
                special_lv.setAdapter(lvAdapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "onError: " + ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

}
