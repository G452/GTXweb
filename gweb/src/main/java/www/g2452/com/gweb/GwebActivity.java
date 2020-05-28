package www.g2452.com.gweb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import java.net.URL;

public class GwebActivity extends AppCompatActivity {

    private WebView webView;
    private String mHomeUrl = "https://blog.csdn.net/qq_43143981";
    private URL mIntentUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gweb);
        webView = (WebView) findViewById(R.id.webView);
        initWeb();
//        webView.loadUrl("https://blog.csdn.net/qq_43143981");
    }
    private void initWeb() {
        WebSettings webSetting = webView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);

        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);

        if (mIntentUrl == null) {
            webView.loadUrl(mHomeUrl);
        } else {
            webView.loadUrl(mIntentUrl.toString());
        }
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }

    WebViewClient webViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            //此处不能像使用系统的WebView的写法一样，强行显示在WebView中，不调用系统浏览器
            //要参考demo中BrowserActivity中这里的写法，直接返回false，否则会出现网页中某得页面加载不出来的情况，别问我为什么知道，^_^，踩过坑。
        /*if (!TextUtils.isEmpty(url)) {
            webView.loadUrl(url);
        }
        return super.shouldOverrideUrlLoading(webView, url);*/
            return false;
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
        }
    };

    WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView webView, int i) {
            try {
//                progress.setProgress(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.onProgressChanged(webView, i);
        }

        @Override
        public void onReceivedTitle(WebView webView, String s) {
            super.onReceivedTitle(webView, s);
//            tvTitle.setText(s);
        }

        @Override
        public boolean onJsConfirm(WebView webView, String s, String s1, JsResult jsResult) {
            return super.onJsConfirm(webView, s, s1, jsResult);
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
//                case R.id.bt_back:
//                    if(webView != null && webView.canGoBack()){
//                        webView.goBack();
//                        return;
//                    }
//                    finish();
//                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null || webView == null || intent.getData() == null){
            return;
        }
        finish();
        webView.loadUrl(intent.getData().toString());
    }

    @Override
    protected void onDestroy() {
//        if (unbinder != null) {
//            unbinder.unbind();
//        }
        //一定要调用WebView的销毁
        if (null != webView) {
            webView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //监听返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //判断webView是否还有需要返回的页面
            if(webView != null && webView.canGoBack()){
                webView.goBack();
                return true;
            }else{
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
