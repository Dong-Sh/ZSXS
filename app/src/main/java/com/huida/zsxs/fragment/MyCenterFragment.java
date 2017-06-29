package com.huida.zsxs.fragment;

import android.app.Activity;
import android.content.Intent;

import com.huida.zsxs.R;
import com.huida.zsxs.activity.LoginActivity;

/**
 * Created by lenovo on 2017/6/8.
 */

public class MyCenterFragment extends BaseFragment {

    public MyCenterFragment(Activity mActivity) {
        super(mActivity);
    }

    @Override
    protected int getLayouId() {
        return R.layout.fragment_center;
    }

    protected void initView() {
        Intent intent = new Intent(mActivity, LoginActivity.class);
//        startActivity(intent);
    }
}
