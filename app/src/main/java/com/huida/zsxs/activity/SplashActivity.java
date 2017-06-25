package com.huida.zsxs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.huida.zsxs.R;

/**
 * Created by lenovo on 2017/6/8.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initData();
        initEvent();
    }

    private void initData() {

    }

    private void initEvent() {
        //执行某个事件后进入MainActivity
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);

        finish();
    }
}
