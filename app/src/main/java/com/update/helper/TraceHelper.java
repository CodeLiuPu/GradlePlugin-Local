package com.update.helper;

import android.app.Activity;
import android.widget.Toast;

/**
 * @author : liupu
 * date   : 2019/5/10
 * desc   :
 */
public class TraceHelper {
    private final String TAG = "TraceHelper";

    /**
     * 当Activity执行了onCreate时触发
     *
     * @param activity
     */
    public static void onActivityCreate(Activity activity) {
        Toast.makeText(activity,
                activity.getClass().getName() + "call onCreate",
                Toast.LENGTH_LONG).show();
    }

    /**
     * 当Activity执行了onDestroy时触发
     *
     * @param activity
     */
    public static void onActivityDestroy(Activity activity) {
        Toast.makeText(activity,
                activity.getClass().getName() + "call onDestroy",
                Toast.LENGTH_LONG).show();
    }
}