package com.huida.zsxs.fragment;

import android.app.Activity;
import android.view.View;

import com.huida.zsxs.R;
import com.huida.zsxs.activity.MainActivity;

/**
 * Created by lenovo on 2017/6/8.
 */

public class MyClassFragment extends BaseFragment {

    public MyClassFragment(Activity mActivity) {
        super(mActivity);
        //guty

    }

    protected int getLayouId() {
        return R.layout.fragment_mycass;
    }

}
