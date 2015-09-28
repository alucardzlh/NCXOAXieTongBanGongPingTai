package com.OA.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class XzswMainActivity extends BaseActivity implements OnClickListener{
	LinearLayout  ll1,ll2,ll3,ll4,ll5;
	LinearLayout imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xzsw_main);
		initViewAndListener();
		
	}
	
	private void initViewAndListener() {
		// TODO 自动生成的方法存根
		ll1 = (LinearLayout) findViewById(R.id.mxzsw_ly);
		ll1.setOnClickListener(this);
		ll2 = (LinearLayout) findViewById(R.id.xzswyy_ly);
		ll2.setOnClickListener(this);
		ll3 = (LinearLayout) findViewById(R.id.xzswdy_ly);
		ll3.setOnClickListener(this);
		ll4 = (LinearLayout) findViewById(R.id.xzswcl_ly);
		ll4.setOnClickListener(this);
		ll5 = (LinearLayout) findViewById(R.id.xzswdj_ly);
		ll5.setOnClickListener(this);
		imageView = (LinearLayout) findViewById(R.id.imageView3);
		imageView.setOnClickListener(this);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.mxzsw_ly:
			startActivity(new Intent(XzswMainActivity.this, Xzsw_Wdxzsw_MainActivity.class));
			break;
		case R.id.xzswyy_ly:
			startActivity(new Intent(XzswMainActivity.this,Xzsw_xzswyy_MainActivity.class));
			break;
		case R.id.xzswdy_ly:
			startActivity(new Intent(XzswMainActivity.this, Xzsw_xzswdy_mainActivity.class));
			break;
		case R.id.xzswcl_ly:
			startActivity(new Intent(XzswMainActivity.this, Xzsw_xzswcl_MainActivity.class));
			break;
		case R.id.xzswdj_ly:
			startActivity(new Intent(XzswMainActivity.this,Xzsw_xzswdj_MainActivity.class));
			break;
		case R.id.imageView3:
			XzswMainActivity.this.finish();
			break;
		default:
			break;
		}
	}
}
