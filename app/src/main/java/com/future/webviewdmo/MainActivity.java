package com.future.webviewdmo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements WebViewJsInterface.StrCallBack {

    WebView webView;
    TextView textView;
    EditText editText;
    Button button;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setWebViewAttributes();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();
                webView.loadUrl("javaScript:if(window.remotd){" +
                        "window.remotd('" + str + "')" +
                        "}");
            }
        });

    }

    private void setWebViewAttributes() {
        WebSettings webSettings = webView.getSettings();
        //允许加载JavaScript
        webSettings.setJavaScriptEnabled(true);
        //允许Chrome调试
        webView.setWebContentsDebuggingEnabled(true);
        //给WebView添加js接口
        webView.addJavascriptInterface(new WebViewJsInterface(this), "webviewcall");
        webView.loadUrl("file:///android_asset/index.html");

    }

    private void initView() {
        webView = findViewById(R.id.webview);
        textView = findViewById(R.id.tv);
        editText = findViewById(R.id.et);
        button = findViewById(R.id.bt);
    }

    @Override
    public void doCall(final String str) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(str);
            }
        });
    }
}
