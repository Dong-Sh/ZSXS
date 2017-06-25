package com.huida.zsxs.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.huida.zsxs.bean.HomeListViewBean;

/**
 * Created by lenovo on 2017/6/24.
 */

public class HomeListViewAdapter extends BaseAdapter {

    private HomeListViewBean bean;

    public HomeListViewAdapter(HomeListViewBean bean) {
        this.bean = bean;
    }

    public HomeListViewBean getBean() {
        return bean;
    }

    public void setBean(HomeListViewBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getCourse().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


}
