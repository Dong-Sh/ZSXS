package com.huida.zsxs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.huida.zsxs.R;

/**
 * Created by lenovo on 2017/6/8.
 */

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        initData();
        initEvent();
    }

    private void initEvent() {
        if(true){//是否第一次进入程序
            Intent intent = new Intent(this,SplashActivity.class);

            startActivity(intent);
        }else{
            Intent intent = new Intent(this,MainActivity.class);

            startActivity(intent);
        }

        finish();
    }

    private void initData() {

    }
}
