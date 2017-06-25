package com.huida.zsxs.pager;

import android.app.Activity;
import android.view.View;

/**
 * Created by liling on 2017/6/25.
 */

public abstract  class BasePager {
    public  final Activity mActivity;
    public View  rootView;

    public BasePager(Activity mActivity) {
        this.mActivity = mActivity;
        rootView = initView();


    }
    //必须实现布局
    public abstract View initView();
    public  void  initData(){};
}
