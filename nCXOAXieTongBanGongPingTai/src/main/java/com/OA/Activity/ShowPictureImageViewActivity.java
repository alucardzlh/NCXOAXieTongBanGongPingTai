package com.OA.Activity;

import java.util.List;

import com.OA.Util.PriUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class ShowPictureImageViewActivity extends Activity{
	private String URL;
	ImageView iv;
	Bitmap bitmap = null;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pictureshow);
		iv = (ImageView)findViewById(R.id.imageview);
		URL = getIntent().getStringExtra("url");
		new ImgShowThread().start();// 开启线程异步获取图片

	}
	private class ImgShowThread extends Thread {
		@Override
		public void run() {
			super.run();
			bitmap = PriUtil.getBitmap("http://117.40.244.134:8081/images/personCert/20140804113063.jpg");// 获取图片资源
			Message message = handler.obtainMessage(0x123);
			message.obj = bitmap;
			handler.sendMessage(message);
		}
	}
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				if(bitmap != null){
					iv.setTag(bitmap);
					iv.setImageBitmap(bitmap);
					 iv.setBackground(new BitmapDrawable(bitmap));
				 }
			}
		};
	};
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			this.finish();
		}
		return false;
	}
}
