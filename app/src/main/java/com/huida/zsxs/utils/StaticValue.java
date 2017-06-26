package com.huida.zsxs.utils;

import android.content.Context;

/**
 * Created by lenovo on 2017/6/8.
 */

public class StaticValue {
    public static String Address = "http://api.chinaplat.com/getval_2017";

    public static double get(Context context,double value){
        return context.getResources().getDisplayMetrics().density*value;
    }
}
