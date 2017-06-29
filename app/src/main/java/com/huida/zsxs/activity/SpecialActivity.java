package com.huida.zsxs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.huida.zsxs.R;
import com.huida.zsxs.bean.TopSlidesBean;
import com.huida.zsxs.view.BackView;

/**
 * Created by lenovo on 2017/6/26.
 */

public class SpecialActivity extends Activity {

    private BackView special_back;
    private ListView special_lv;
    private TopSlidesBean.SlidesBean bean;

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

        String pic = intent.getStringExtra("pic");
        String title = intent.getStringExtra("title");
        String picURL = intent.getStringExtra("picURL");

        special_back.setTitle(title);
    }

    private void initEvent() {
        special_back.setActivity(this);
    }

}
