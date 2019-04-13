package com.example.hellocat.utils;

import android.util.Log;
import android.util.Printer;

public class LogUtil {

    private static final boolean isDebug = true;

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void alwaysShowD(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void alwaysShowI(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }

    public static void alwaysShowW(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void alwaysShowE(String tag, String msg) {
        Log.e(tag, msg);
    }
}

