package com.huida.zsxs.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huida.zsxs.R;
import com.huida.zsxs.activity.SearchActivity;

/**
 * Created by lenovo on 2017/6/8.
 */

public class TitleView extends LinearLayout {

    private ImageView icon;
    private TextView title;
    private ImageView search;
    private ImageView right1;
    private TextView textView;

    public TitleView(Context context) {
        super(context);
        initView(context);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }


    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.title, this, true);

        icon = (ImageView) view.findViewById(R.id.title_iv_icon);
        title = (TextView) view.findViewById(R.id.title_tv_title);
        search = (ImageView) view.findViewById(R.id.title_ib_search);
        right1 = (ImageView) view.findViewById(R.id.title_ib_right1);
        textView = (TextView) view.findViewById(R.id.title_ib_right2);
        initListener();
    }

    private void initListener() {
        search.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SearchActivity.class);

                getContext().startActivity(intent);
            }
        });
    }

    public void setVisible(int... visible) {//传入五个控件是否显示
        icon.setVisibility(visible[0]);
        title.setVisibility(visible[1]);
        search.setVisibility(visible[2]);
        right1.setVisibility(visible[3]);
        textView.setVisibility(visible[4]);
    }

    public void setRight1Image(int resid) {//传入右边第一个图片
        right1.setBackgroundResource(resid);
    }

    public void setRight2Image(int resid) {//传入右边第二个图片
        textView.setBackgroundResource(resid);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setRight1Listener(OnClickListener listener) {
        right1.setOnClickListener(listener);
    }

    public void setRight2Listener(OnClickListener listener) {
        textView.setOnClickListener(listener);
    }
    public void setRight2LeftDrawable(){
        textView.setText("编辑");
        textView.setCompoundDrawables(getResources().getDrawable(R.mipmap.item_delete),null,null,null);
    }
    public void setRight2Clear(){
        textView.setText("取消");
        textView.setCompoundDrawables(null,null,null,null);
    }
}
