package www.g2452.com.gweb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * 作者：G
 * ClassName：RechTextView
 * 时间：2020/8/19  2:09 PM
 * 概述：富文本加载器
 */
public class RechTextView extends RelativeLayout {

    private WebView webView;

    public RechTextView(Context context) {
        super(context);
        initView(context);
    }

    public RechTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public RechTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    /**
     * 添加富文本
     */

    public void addRechText(String html) {
        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }

    public WebView getWebView() {
        if (webView == null) {
            return null;
        }
        return webView;
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void initView(Context context) {
        View inflate = View.inflate(context, R.layout.rechtext_layout, null);
        webView = inflate.findViewById(R.id.rech_view);
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(false);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        //支持自动适配
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(false);  //支持放大缩小
        settings.setBuiltInZoomControls(false); //显示缩放按钮
        settings.setBlockNetworkImage(true);// 把图片加载放在最后来加载渲染
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setSaveFormData(true);
        settings.setGeolocationEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);/// 支持通过JS打开新窗口
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置不让其跳转浏览器
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
// 添加客户端支持
        webView.setWebChromeClient(new WebChromeClient());
//不加这个图片显示不出来
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        webView.getSettings().setBlockNetworkImage(false);

        //允许cookie 不然有的网站无法登陆
        CookieManager mCookieManager = CookieManager.getInstance();
        mCookieManager.setAcceptCookie(true);
        mCookieManager.setAcceptThirdPartyCookies(webView, true);
        addView(inflate);
    }


}
