package com.driver.shinekaye.olddriver;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shine Kaye on 2016/3/28.
 */

/**
 * Save the Settings 该类用于存储密码数据
 */

public class SharedPreUtil {

    public  SharedPreferences sp;

    public SharedPreUtil(Context context) {
        sp = context.getSharedPreferences("SharedPreUtil", Context.MODE_PRIVATE);
    }

    public void putPassword(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getPassword(String key) {
        return sp.getString(key, "");

    }

    public boolean getChanged(String key) {
        return sp.contains(key);
    }
}
