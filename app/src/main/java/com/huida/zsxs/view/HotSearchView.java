package com.huida.zsxs.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huida.zsxs.R;

import java.util.Arrays;

/**
 * Created by lenovo on 2017/6/23.
 */

public class HotSearchView extends LinearLayout {

    private static final String TAG = "HotSearchView";

    public HotSearchView(Context context) {
        super(context);
        initView();
    }

    public HotSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    public HotSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    int width = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();

    public void setValues(String[] values,OnClickListener listener) {
        Log.d(TAG, "setValues: "+ Arrays.toString(values));
        this.removeAllViews();
        float widths = 0;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(HORIZONTAL);
        for (int i = 0; i < values.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setTextColor(Color.BLACK);
            textView.setText(values[i]);
            textView.setBackgroundResource(R.drawable.search_tv_shape);
textView.setTextSize(12);
            textView.setLayoutParams(params);
            textView.setPadding(50,25,50,25);
            textView.setTextColor(Color.parseColor("#7d7f81"));
            textView.measure(0, 0);

            textView.setOnClickListener(listener);
            widths += textView.getMeasuredWidth()*1.70;
            Log.d(TAG, "setValues: "+widths+"|"+width + values[i]);
            linearLayout.addView(textView);
            if (widths >= width) {
                this.addView(linearLayout);
                Log.d(TAG, "setValues: widths");
                linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(HORIZONTAL);
                widths = 0;
            }
        }
        this.addView(linearLayout);
    }

    private void initView() {
        params.leftMargin = 50;
        params.bottomMargin = 25;
    }



}
