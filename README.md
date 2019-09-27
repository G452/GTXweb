# GTXweb
腾讯TBS浏览服务二次封装

一、集成步骤
1、项目的build中

     allprojects {
    repositories {
      ……
      ……
        maven { url 'https://jitpack.io' }
    }
    }
    
2、主model的build中    

     implementation 'com.github.G452:GTXweb:0.0.3'
     
3、最后Sync Now搞定     


二、使用步骤

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

        
