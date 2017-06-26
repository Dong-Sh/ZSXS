package com.huida.zsxs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by lenovo on 2017/6/26.
 */

public class HomtRecommendGridView extends GridView {
    public HomtRecommendGridView(Context context) {
        super(context);
    }

    public HomtRecommendGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomtRecommendGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE    >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
