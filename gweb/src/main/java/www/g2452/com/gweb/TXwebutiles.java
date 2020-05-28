package www.g2452.com.gweb;

import android.content.Context;

import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.net.URL;

/**
 * 作者：G
 * 时间： 2019/5/7 0007 16:31
 * 作用：
 */
public class TXwebutiles {

    private static URL mIntentUrl;
   //初始化web
    public static void initweb(String mHomeUrl, WebView webView, Context context) {
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
        webSetting.setAppCachePath(context.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(context.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(context.getDir("geolocation", 0)
                .getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        webView.setWebViewClient(new WebViewClient() {
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
        });

        webView.setWebChromeClient(new WebChromeClient() {
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
        });
        if (mIntentUrl == null) {
            webView.loadUrl(mHomeUrl);
        } else {
            webView.loadUrl(mIntentUrl.toString());
        }
        CookieSyncManager.createInstance(context);
        CookieSyncManager.getInstance().sync();

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



    }



}
