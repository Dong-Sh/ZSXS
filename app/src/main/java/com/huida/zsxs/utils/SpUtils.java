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
            if (!string.equals("")) {
                Log.d("Dongsh", "setString: |" + string.indexOf(value) + "|" + string + "|" + value);
                String[] split = string.substring(1).split(",");
                for (int i = 0; i < split.length; i++) {
                    if (split[i].equals(value)) {
                        return false;
                    }
                }
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
