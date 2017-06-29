package com.huida.zsxs.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.huida.zsxs.R;

/**
 * Created by lenovo on 2017/6/27.
 */

public class BackView extends View {
    private Activity activity;
    private TextView viewById;

    public BackView(Context context) {
        super(context);
        initView();
    }

    public BackView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BackView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View inflate = View.inflate(getContext(), R.layout.view_back, null);

        viewById = (TextView) inflate.findViewById(R.id.special_back);

        viewById.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    public void setTitle(String text) {
        viewById.setText(text);
    }


}
