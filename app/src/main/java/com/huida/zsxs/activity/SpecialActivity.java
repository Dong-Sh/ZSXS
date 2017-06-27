package com.huida.zsxs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.huida.zsxs.R;
import com.huida.zsxs.bean.TopSlidesBean;

/**
 * Created by lenovo on 2017/6/26.
 */

public class SpecialActivity extends Activity {

    private TextView special_back;
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
        special_back = (TextView) findViewById(R.id.special_back);
        special_lv = (ListView) findViewById(R.id.special_lv);
    }

    private void initData() {
        Intent intent = getIntent();

        String pic = intent.getStringExtra("pic");
        String title = intent.getStringExtra("title");
        String picURL = intent.getStringExtra("picURL");


        special_back.setText(title);
    }

    private void initEvent() {
        special_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

}
