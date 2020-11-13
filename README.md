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
    //androidx 用户
     implementation 'com.github.G452:GTXweb:0.0.9'
     
3、最后Sync Now搞定     


二、使用步骤
1、在Application中初始化

       QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }
            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);

2、布局内

     <com.tencent.smtt.sdk.WebView
        android:id="@+id/webView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
        
        
Activity或Fragment中

 3、 找到控件
 
     webView = (WebView) findViewById(R.id.webView);
   
 4、  初始化webView
 
    TXwebutiles.initweb(mUrl, webView, this);


三、新增富文本控件 RechTextView

        
