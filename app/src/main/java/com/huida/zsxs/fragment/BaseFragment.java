package com.huida.zsxs.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2017/6/8.
 */

public abstract class BaseFragment extends Fragment {

    protected View view;
    public Activity mActivity;

    protected BaseFragment(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        BaseinitView();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
    }

    private void BaseinitView() {
        view = View.inflate(mActivity, getLayouId(), null);
    }


    protected void initView() {

    }

    protected abstract int getLayouId();
}
