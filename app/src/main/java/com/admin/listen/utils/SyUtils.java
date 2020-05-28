package com.admin.listen.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.inputmethod.InputMethodManager;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    /**
     *  MD5加密
     * @param plainText
     * @return
     */
    public static String strToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

}
