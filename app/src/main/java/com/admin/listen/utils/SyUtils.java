package com.admin.listen.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/1/24 0:21
 **/
public class SyUtils {

    /**
     * 获取系统屏幕象素
     *
     * @param context
     * @return
     */
    public static int[] getScreen(Context context) {
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        return new int[]{width, height};
    }

    /**
     * 打开关闭相互切换
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 自动关闭软键盘
     *
     * @param activity
     */
    public static void closeKeybord(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }


    public static String time_HHmm() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * 控制显示方向的，横屏，横屏颠倒
     *
     * @param activity
     * @param requestedOrientation
     * @return
     */
    public static int displayDirection(Activity activity, int requestedOrientation) {
        if (activity.getRequestedOrientation() != requestedOrientation)
            switch (requestedOrientation) {
                case SCREEN_ORIENTATION_REVERSE_LANDSCAPE:
                    //横屏翻转
                    activity.setRequestedOrientation(SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                    break;
                case SCREEN_ORIENTATION_REVERSE_PORTRAIT:
                    //竖屏翻转
                    activity.setRequestedOrientation(SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                    break;
                case SCREEN_ORIENTATION_LANDSCAPE:
                    //横屏
                    activity.setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);
                    break;
                case SCREEN_ORIENTATION_PORTRAIT:
                    //竖屏
                    activity.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
                    break;
            }
        return requestedOrientation;
    }



}
