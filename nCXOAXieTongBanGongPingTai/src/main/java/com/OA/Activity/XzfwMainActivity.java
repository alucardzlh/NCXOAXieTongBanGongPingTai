package com.OA.Activity;

import com.OA.Data.InfoFile_;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class XzfwMainActivity extends BaseActivity implements OnClickListener{

	private LinearLayout mxzfw,xzfwzg,xzfwyy,xzfwdy,xzfwsh,xzfwcs;
	private LinearLayout back;
	InfoFile_ infoFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xzfw_main);
		initViewAndListener();
	}
	
	private void initViewAndListener() {
		infoFile = new InfoFile_(this);
		back = (LinearLayout) findViewById(R.id.imageView3);
		mxzfw = (LinearLayout) findViewById(R.id.mxzfw_ly);
		xzfwzg = (LinearLayout) findViewById(R.id.xzfwzg_ly);
		xzfwyy = (LinearLayout) findViewById(R.id.xzfwyy_ly);
		xzfwdy = (LinearLayout) findViewById(R.id.xzfwdy_ly);
		xzfwsh = (LinearLayout) findViewById(R.id.xzfwsh_ly);
		xzfwcs= (LinearLayout) findViewById(R.id.xzgwcs_ly);
		mxzfw.setOnClickListener(this);
		xzfwsh.setOnClickListener(this);
		xzfwzg.setOnClickListener(this);
		xzfwyy.setOnClickListener(this);
		xzfwdy.setOnClickListener(this);
		xzfwcs.setOnClickListener(this);
		back.setOnClickListener(this);
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
		switch (v.getId()) {
		case R.id.imageView3:
			XzfwMainActivity.this.finish();
			break;
		case R.id.mxzfw_ly:
			startActivity(new Intent(XzfwMainActivity.this, Xzfw_Wdxzfw_MainActivity.class));
			break;
		case R.id.xzfwzg_ly:
			startActivity(new Intent(XzfwMainActivity.this, Xzfw_xzfwzg_MainActivity.class));
			break;
		case R.id.xzfwyy_ly:
			startActivity(new Intent(XzfwMainActivity.this, Xzfw_xzfwyy_MainActivity.class));
			break;
		case R.id.xzfwdy_ly:
			startActivity(new Intent(XzfwMainActivity.this, Xzfw_xzfwdy_MainActivity.class));
			break;
		case R.id.xzfwsh_ly:
			startActivity(new Intent(XzfwMainActivity.this, Xzfw_xzfwsh_MainActivity.class));
			break;
		case R.id.xzgwcs_ly:
			infoFile.edit().FlowID().put(46).apply();
			startActivity(new Intent(XzfwMainActivity.this, Detail_Bdxx_MainActivity.class));
			break;
		default:
			break;
		}
	}

}
