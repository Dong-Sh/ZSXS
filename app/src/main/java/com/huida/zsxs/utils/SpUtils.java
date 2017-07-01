package com.huida.zsxs.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * Created by lenovo on 2017/6/23.
 */

public class SpUtils {

    private static String SEARCH = "search";
    public static String USERID = "userid";
    private static SharedPreferences sharedPreferences;

    public static boolean setSearchString(Context context, String key, String value) {

        String string = getString(context, key);

        if (!value.equals("")) {
            Log.d("Dongsh", "setString: |" + string.indexOf(value) + "|" + string + "|" + value);
            if (string.indexOf(value) != -1) {
                return false;
            }
        } else {
            return false;
        }
        sharedPreferences = context.getSharedPreferences(SEARCH, Context.MODE_PRIVATE);

        Editor edit = sharedPreferences.edit();

        edit.putString(key, string + "," + value);

        edit.commit();

        return true;
    }

    public static boolean setString(Context context, String key, String value) {

        sharedPreferences = context.getSharedPreferences(SEARCH, Context.MODE_PRIVATE);

        Editor edit = sharedPreferences.edit();

        edit.putString(key, value);

        edit.commit();

        return true;
    }

    public static void clearSearchString(Context context, String key) {

        sharedPreferences = context.getSharedPreferences(SEARCH, Context.MODE_PRIVATE);

        Editor edit = sharedPreferences.edit();

        edit.putString(key, "");

        edit.commit();
    }


    public static String getString(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(SEARCH, Context.MODE_PRIVATE);

        return sharedPreferences.getString(key, "");
    }

}
