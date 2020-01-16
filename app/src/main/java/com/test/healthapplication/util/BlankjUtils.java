package com.test.healthapplication.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import java.util.LinkedList;


public final class BlankjUtils {

    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    private static LinkedList<Activity> sActivityList = new LinkedList<>();


    private BlankjUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Init utils.
     * <p>Init it in the class of Application.</p>
     *
     * @param context
     *         context
     */
    public static void init(@NonNull final Context context) {
        BlankjUtils.sApplication = (Application) context.getApplicationContext();
    }

    /**
     * Return the context of Application object.
     *
     * @return the context of Application object
     */
    public static Application getApp() {
        if (sApplication != null) return sApplication;
        throw new NullPointerException("u should init first");
    }
}
