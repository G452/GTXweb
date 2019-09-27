# GTXweb
腾讯TBS浏览服务二次封装

1、布局内

     <com.tencent.smtt.sdk.WebView
        android:id="@+id/webView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
        
        
Activity或Fragment中

 2、 找到控件
 
     webView = (WebView) findViewById(R.id.webView);
   
 3、  初始化webView
 
    TXwebutiles.initweb(mUrl, webView, this);

        
