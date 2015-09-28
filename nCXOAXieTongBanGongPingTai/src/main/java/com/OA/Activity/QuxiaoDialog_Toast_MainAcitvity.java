package com.OA.Activity;

import com.OA.Activity.R;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Service.CancelmtingManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class QuxiaoDialog_Toast_MainAcitvity extends BaseActivity implements OnClickListener{
	private ImageView iv_guanbi;
	private Button btn_guanbi,btn_baocun;
	private TextView tv_hyyt;
	private String hyyt;
	private String TAG="QuxiaoDialog_Toast_MainAcitvity";
	private String reasonMsg;
	private Boolean isPhoneMsgSend;

	InfoFile_ infoFile_;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quxiaodialog);
		initView();
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		hyyt = bundle.getString("Title");
		initDate();
	}

	private void initDate() {
		tv_hyyt.setText(hyyt);
	
	}

	private void initView() {
		infoFile_ = new InfoFile_(this);
		iv_guanbi = (ImageView) findViewById(R.id.iv_guanbi);
		btn_baocun = (Button) findViewById(R.id.btn_baocun);
		btn_guanbi = (Button) findViewById(R.id.btn_guanbi);
		tv_hyyt = (TextView) findViewById(R.id.tv_hyyt);
		edt_quxiao = (EditText) findViewById(R.id.edt_quxiao);
		cb = (CheckBox) findViewById(R.id.cb_send);
//		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				isPhoneMsgSend=isChecked;
//			}
//		});
		iv_guanbi.setOnClickListener(this);
		btn_guanbi.setOnClickListener(this);
		btn_baocun.setOnClickListener(this);
	}
	CancelmtingManager cancelmting=new CancelmtingManager() {
		
		private MyMeetingDetail_Bean bean;

		@Override
		protected void handlerLoginInfo(Context context, HandleResult handleResult,
				int paramInt) {
			if(handleResult.getiSuccess().equals("success")){
				bean = Constants.mymeetingdetail_bean;
				if(bean != null && bean.getStatus().equals("2000")){
					Toast.makeText(QuxiaoDialog_Toast_MainAcitvity.this, "成功", Toast.LENGTH_SHORT).show();
					QuxiaoDialog_Toast_MainAcitvity.this.finish();
				}else if(bean != null && bean.getStatus().equals("0000")){
					Toast.makeText(QuxiaoDialog_Toast_MainAcitvity.this, "获取会议信息失败，安全验证未通过!", Toast.LENGTH_SHORT).show();
					QuxiaoDialog_Toast_MainAcitvity.this.finish();
//			}else if(bean != null && bean.getStatus().equals("5001")){
//				Toast.makeText(QuxiaoDialog_Toast_MainAcitvity.this, "获取会议信息失败，公告数据不存在!", Toast.LENGTH_SHORT).show();
				}
			}
		}
	};
	private CheckBox cb;
	private EditText edt_quxiao;
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_guanbi:
			this.finish();
			break;
		case R.id.btn_guanbi:
			this.finish();
			break;
		case R.id.btn_baocun:
			
			reasonMsg=edt_quxiao.getText().toString();
			cancelmting.CancelmtingManager(this,1,infoFile_.RoomLogID().get(), reasonMsg, cb.isChecked(),  infoFile_.infoUsername().get(), infoFile_.infoUserType().get()
					);
			
			break;

		default:
			break;
		}
	}
}
