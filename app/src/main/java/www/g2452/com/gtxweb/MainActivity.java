package www.g2452.com.gtxweb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.tencent.smtt.sdk.WebView;

import www.g2452.com.gweb.TXwebutiles;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String mUrl = "https://blog.csdn.net/qq_43143981";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);
        TXwebutiles.initweb(mUrl, webView, this);
    }

    //保存页面信息
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null || webView == null || intent.getData() == null){
            return;
        }
        finish();
        webView.loadUrl(intent.getData().toString());
    }

   //销毁webview
    @Override
    protected void onDestroy() {
        //一定要调用WebView的销毁
        if (null != webView) {
            webView.destroy();
        }
        super.onDestroy();
    }

    //拦截返回键
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
