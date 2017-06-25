package com.huida.zsxs.pager;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huida.zsxs.R;

/**
 * Created by liling on 2017/6/25.
 */

public class VideoPager extends BasePager{

    public VideoPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View  view=View.inflate(mActivity, R.layout.list_item_layout,null);
        ImageView iv_item_icon = (ImageView) view.findViewById(R.id.iv_item_icon);
        TextView tv_item_title = (TextView) view.findViewById(R.id.tv_item_title);
        TextView tv_item_sectitle = (TextView) view.findViewById(R.id.tv_item_Sectitle);
        TextView tv__item_jifen = (TextView) view.findViewById(R.id.tv__item_jifen);
        TextView tv__item_dianjiliang = (TextView) view.findViewById(R.id.tv_item_dianjiliang);

        return view;
    }
}
