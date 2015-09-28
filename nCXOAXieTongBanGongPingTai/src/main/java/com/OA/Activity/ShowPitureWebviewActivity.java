package com.OA.Activity;


import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.Toast;

public class ShowPitureWebviewActivity extends Activity{
	private WebView webView;

	private  String URL = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pictureshow);
		URL = getIntent().getStringExtra("url");
		webView = (WebView) this.findViewById(R.id.webview);
		WebSettings webSettings = webView.getSettings();
		// 设置WebView属性，能够执行Javascript脚本
		webSettings.setJavaScriptEnabled(true);
		// 设置可以访问文件
		webSettings.setAllowFileAccess(true);
		// 设置支持缩放
		webSettings.setBuiltInZoomControls(true);
		//webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		//webView.clearCache(true);
		// 加载需要显示的网页
		 webView.loadUrl(URL);

		webSettings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);

		// webView.loadDataWithBaseURL(null, "",
		// "text/html", "utf-8", null);
		// 设置Web视图
		webView.setWebViewClient(new webViewClient());
	}

	@Override
	// 设置回退
	// 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			this.finish();
		}
		return false;
	}

	// Web视图
	private class webViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
}
	
