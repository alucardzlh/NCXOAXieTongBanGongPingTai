package com.OA.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class HyxxMainActivity extends BaseActivity implements OnClickListener{
private View mhysq,hysq,hysh,hytz;
private View iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hygl_main);
		initView();
		initDate();
	}
	
	private void initDate() {
		// TODO 自动生成的方法存根
	}

	private void initView() {
		// TODO 自动生成的方法存根
		 mhysq = findViewById(R.id.ly_mhysq);
		 hysq = findViewById(R.id.ly_hysq);
		 hysh = findViewById(R.id.ly_hysh);
	//	 hyqd = findViewById(R.id.ly_hyqd);
		 hytz = findViewById(R.id.ly_hytz);
		 
		 iv =  findViewById(R.id.imageView3);
		
		 mhysq.setOnClickListener(this);
		 hysq.setOnClickListener(this);
		 hysh.setOnClickListener(this);
	//	 hyqd.setOnClickListener(this);
		 hytz.setOnClickListener(this);
		 iv.setOnClickListener(this);
		 
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
		case R.id.ly_mhysq:
			Intent intent=new Intent(this,Hyxx_Wdhysq_MainActivity.class);
			startActivity(intent);
			break;
		case R.id.ly_hysq:
			Intent intent1=new Intent(this,Hyxx_hyssq_MainActivity.class);
			startActivity(intent1);
			break;
		case R.id.ly_hysh:
			Intent intent2=new Intent(this,Hyxx_hyssh_MainActivity.class);
			startActivity(intent2);
			break;
		case R.id.ly_hyqd:
		//	Intent intent3=new Intent(this,Hyxx_mysq_Activity.class);
		//	startActivity(intent3);
			break;
		case R.id.ly_hytz:
			Intent intent4=new Intent(this,Hyxx_Wdhytz_MainActivity.class);
			startActivity(intent4);
			break;
		case R.id.imageView3:
			HyxxMainActivity.this.finish();
			break;

		default:
			break;
		}
	}


}
