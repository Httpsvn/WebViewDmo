package com.future.webviewdmo;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by 24260 on 2018/5/8.
 */

public class WebViewJsInterface {
    StrCallBack strCallBack;

  public   interface StrCallBack {
        void doCall(String str);
    }

    public WebViewJsInterface(StrCallBack strCall) {
        this.strCallBack = strCall;
    }

    private static final String TAG = "WebViewJsInterface";

    //一定要加这个注解
    // 不在主线程执行
    @JavascriptInterface
    public void setValue(String value) {
        Log.e(TAG, "value" + value);
        if (strCallBack != null) {
            strCallBack.doCall(value);
        }
    }

}
