package com.sample.galleryImage.utilis;


import android.util.Log;

import androidx.annotation.NonNull;


import com.sample.galleryImage.BuildConfig;

public class AppLog {

    /*
     * Log state will print based on BuildConfig.LOG (This value declared in build.gradle - buildConfigField "boolean", "LOG", "true")
     * true - it prints (App running in Developer Mode (buildConfigField "boolean", "LOG", "true")
     * false - it wont print log (App Runing in Release mode (buildConfigField "boolean", "LOG", "false")
     */

    public static final String EXCEPTION = "VideoCapture_Exception";
    public static final String PREVIEW = "VideoCapture_Preview";
    public static final String RECORDER = "VideoCapture_VideoRecorder";
    public static final String CAMERA = "VideoCapture_CameraWrapper";


    public static void i(String tag, String inputString) {
        if (BuildConfig.LOG)
            Log.i(tag, getStackTraceMsg() + ": " + inputString);
        tag = null;
        inputString = null;
    }

    public static void e(String tag, String inputString) {
        if (BuildConfig.LOG)
            Log.e(tag, getStackTraceMsg() + ": " + inputString);
        tag = null;
        inputString = null;
    }

    public static void d(String tag, String inputString) {
        if (BuildConfig.LOG)
            Log.d(tag, getStackTraceMsg() + ": " + inputString);
        tag = null;
        inputString = null;
    }

    public static void v(String tag, String inputString) {
        if (BuildConfig.LOG)
            Log.v(tag, getStackTraceMsg() + ": " + inputString);
        tag = null;
        inputString = null;
    }

    public static void w(String tag, String inputString) {
        if (BuildConfig.LOG)
            Log.w(tag, getStackTraceMsg() + ": " + inputString);
        tag = null;
        inputString = null;
    }

    /**
     * Location code location can be used only inside class
     */
    @NonNull
    private static String getStackTraceMsg() {
        String fileInfo = "";
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements != null && stackTraceElements.length > 4) {
            StackTraceElement stackTrace = stackTraceElements[4];
            fileInfo = stackTrace.getFileName() + "(" + stackTrace.getLineNumber() + ") " + stackTrace.getMethodName();
            stackTrace = null;
        }
        stackTraceElements = null;
        return fileInfo;
    }

}
