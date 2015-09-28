package com.OA.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TzggMainActivity extends BaseActivity implements OnClickListener{
	
	LinearLayout back;
	LinearLayout ll_qc,ll_sh,ll_ll;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tzgl_main);
		initViewAndListener();
	}
	
	private void initViewAndListener() {
		back = (LinearLayout) findViewById(R.id.back);
		ll_qc = (LinearLayout) findViewById(R.id.tzqc_ly);
		ll_sh = (LinearLayout) findViewById(R.id.tzsh_ly);
		ll_ll = (LinearLayout) findViewById(R.id.tzll_ly);
		back.setOnClickListener(this);
		ll_qc.setOnClickListener(this);
		ll_sh.setOnClickListener(this);
		ll_ll.setOnClickListener(this);
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
		case R.id.tzqc_ly:
			startActivity(new Intent(TzggMainActivity.this, Tzgg_qc_MainActivity.class));
			break;
		case R.id.tzsh_ly:
			startActivity(new Intent(TzggMainActivity.this, Tzgg_sh_MainActivity.class));
			break;
		case R.id.tzll_ly:
			startActivity(new Intent(TzggMainActivity.this, Tzgg_ll_MainActivity.class));
			break;
		case R.id.back:
			TzggMainActivity.this.finish();
			break;
		default:
			break;
		}
	}
}
