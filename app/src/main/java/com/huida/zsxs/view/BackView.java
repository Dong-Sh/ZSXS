package com.huida.zsxs.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huida.zsxs.R;

/**
 * Created by lenovo on 2017/6/27.
 */

public class BackView extends LinearLayout {
    private static final String TAG = "BackView";
    private Activity activity;
    private TextView viewById;

    public BackView(Context context) {
        super(context);
        initView(context);
    }

    public BackView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BackView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_back, this,true);

        viewById = (TextView) inflate.findViewById(R.id.special_back);

        viewById.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                activity.finish();
            }
        });

        Log.d(TAG, "initView: ");
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    public void setTitle(String text) {
        viewById.setText(text);
    }


}
