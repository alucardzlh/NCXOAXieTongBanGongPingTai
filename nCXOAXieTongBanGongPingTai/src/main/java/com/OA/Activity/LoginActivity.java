package com.OA.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.OA.Service.LoginOAManager;
import com.OA.Util.StringUtil;
import com.OA.Data.DataHelper;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.LoginOA;
import com.OA.Entity.MyMeeting_hyssh_Bean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.umeng.update.UmengUpdateAgent;

/***
 * 
 * @author rjh 登录activity
 * 
 */
@NoTitle
@EActivity(R.layout.layout_login_main)
public class LoginActivity extends Activity {
	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	EditText edt_login_1, edt_login_2;
	@ViewById
	CheckBox cb_login;
	@ViewById
	Button btn_login;
	@ViewById
	TextView tv_version;
	protected static ActivityApp app;

	@AfterViews
	void initView() {
		btn_login.setOnClickListener(onClickListener);
		initLogin();
	}

	private void initLogin() {
		if (infoFile_.isAutoLogin().get()) {
			edt_login_1.setText(infoFile_.infoUsername().get());
			edt_login_2.setText(infoFile_.infoPassword().get());
			cb_login.setChecked(true);
		}
	}

	private LoginOAManager mLoginManager = new LoginOAManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess().equals("success")) {
				if (handleResult.getLogin_result().equals("success")) {
					LoginOA bean = new LoginOA();
					bean = handleResult.getBean_login();
					infoFile_
							.edit()
							.infoUsername2()
							.put(bean.getUserName() != null ? bean
									.getUserName() : "0").apply();
					infoFile_
							.edit()
							.AdminID()
							.put(bean.getAdminID() != null ? bean.getAdminID()
									: "0").apply();
					infoFile_
							.edit()
							.RoleID()
							.put(bean.getRoleID() != null ? bean.getRoleID()
									: "0").apply();
					infoFile_
							.edit()
							.WorkID()
							.put(bean.getWorkID() != null ? bean.getWorkID()
									: "0").apply();
					infoFile_
							.edit()
							.DeptID()
							.put(bean.getDeptID() != null ? bean.getDeptID()
									: "0").apply();
					infoFile_.edit().infoUsername()
							.put(edt_login_1.getText().toString()).apply();
					if (cb_login.isChecked()) {
						infoFile_.edit().isAutoLogin().put(true).apply();

						infoFile_.edit().infoPassword()
								.put(edt_login_2.getText().toString()).apply();
					} else {
						infoFile_.edit().isAutoLogin().put(false).apply();
					}
					infoFile_
							.edit()
							.infoUserType()
							.put(StringUtil.md5("DCOA"
									+ edt_login_1.getText().toString()))
							.apply();
					startActivity(new Intent(LoginActivity.this,
							MainActivity.class));
					LoginActivity.this.finish();
					Toast.makeText(LoginActivity.this, "登陆成功！",
							Toast.LENGTH_SHORT).show();
				}
			}
		}
	};

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_login:
				// startActivity(new Intent(LoginActivity.this,
				// MainActivity.class));
				// LoginActivity.this.finish();
				if (StringUtil.isBlank(edt_login_1.getText().toString())
						|| StringUtil.isBlank(edt_login_2.getText().toString())) {
					Toast.makeText(LoginActivity.this, "用户名和密码不能为空！",
							Toast.LENGTH_LONG).show();
					return;
				} else {

					mLoginManager.getLoginOAManager(LoginActivity.this, 1,
							edt_login_1.getText().toString(), edt_login_2
									.getText().toString());
				}

				break;

			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UmengUpdateAgent.update(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click();
		}
		return false;
	}

	private boolean isExit = false;

	/**
	 * 双击退出
	 */
	public void exitBy2Click() {
		if (!isExit) {
			isExit = true; // 准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

			new Thread(new Runnable() {

				@Override
				public void run() {
					SystemClock.sleep(2000);
					isExit = false; // 取消退出
				}
			}).start();
		} else {
			this.finish();
		}
	}
}
