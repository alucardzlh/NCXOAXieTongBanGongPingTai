package com.OA.Activity;

import com.OA.Activity.R;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Service.BuBaoJoinWorkerManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TjryDialog_Toast_MainAcitvity extends BaseActivity implements
		OnClickListener {

	private ImageView iv_guanbi;
	private EditText edt_name, edt_zhiwu, edt_dianhua;
	private CheckBox cb_send;
	private Button btn_tianjia;
	private MyMeetingDetail_Bean bean;
	private String JoinWorkerCodes; // 未知参数？？？？？
	private String JoinWorkerName; // 系统外人员
	private String UserPostName; // 职务
	private String UserMobile; // 电话
	private String JoinWorkerNames;// 系统内人员
	private Boolean isSendMsg; // 是否发送
	private int DeptID;
	InfoFile_ infoFile_;
	private EditText edt_cxry;
	private LinearLayout ll_checkpeople;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hyxx_wdhytz_xiangxi_tjrydialog);
		initView();

	}

	public int getRoomLogID() {
		int RoomLogID = Integer.valueOf(getIntent().getExtras().getString(
				"RoomLogID"));
		return RoomLogID;
	}

	private void initView() {
		infoFile_ = new InfoFile_(this);
		iv_guanbi = (ImageView) findViewById(R.id.iv_guanbi);
		edt_name = (EditText) findViewById(R.id.edt_name); // 姓名
		edt_zhiwu = (EditText) findViewById(R.id.edt_zhiwu); // 职务
		edt_dianhua = (EditText) findViewById(R.id.edt_dianhua); // 电话
		cb_send = (CheckBox) findViewById(R.id.cb_send); // 发送
		btn_tianjia = (Button) findViewById(R.id.btn_tianjia);
		ll_checkpeople = (LinearLayout) findViewById(R.id.ll_checkpeople);
		edt_cxry = (EditText) findViewById(R.id.edt_cxry);
		ll_checkpeople.setOnClickListener(this);
		iv_guanbi.setOnClickListener(this);
		btn_tianjia.setOnClickListener(this);
		isSendMsg = cb_send.isChecked();

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}

	BuBaoJoinWorkerManager bubaojosinworker = new BuBaoJoinWorkerManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess().equals("success")) {
				bean = Constants.mymeetingdetail_bean;
				if (bean != null && bean.getStatus().equals("2000")) {
					Toast.makeText(TjryDialog_Toast_MainAcitvity.this, "添加成功",
							Toast.LENGTH_SHORT).show();
					TjryDialog_Toast_MainAcitvity.this.finish();
				} else if (bean != null && bean.getStatus().equals("5100")) {
					Toast.makeText(TjryDialog_Toast_MainAcitvity.this, "此人已参加",
							Toast.LENGTH_SHORT).show();
				} else if (bean != null && bean.getStatus().equals("5000")) {
					Toast.makeText(TjryDialog_Toast_MainAcitvity.this, "失败",
							Toast.LENGTH_SHORT).show();
				} else if (bean != null && bean.getStatus().equals("0000")) {
					Toast.makeText(TjryDialog_Toast_MainAcitvity.this,
							"安全验证未通过!", Toast.LENGTH_SHORT).show();
				}
			}

		}
	};

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.iv_guanbi:
			this.finish();
			break;
		case R.id.btn_tianjia:
			JoinWorkerName = edt_name.getText().toString().trim();
			UserPostName = edt_zhiwu.getText().toString().trim();
			UserMobile = edt_dianhua.getText().toString().trim();
			JoinWorkerNames = cxry_username.toString();
			JoinWorkerCodes = cxry_usercode.toString();
			DeptID = Integer.valueOf(infoFile_.DeptID().get());

			if (TextUtils.isEmpty(JoinWorkerNames)) {
				Toast.makeText(this, "内容不能为空", Toast.LENGTH_LONG).show();
			} else {
				bubaojosinworker.getBuBaoJoinWorkerManager(this, 1,
						getRoomLogID(), DeptID, UserPostName, JoinWorkerName,
						JoinWorkerNames, JoinWorkerCodes, UserMobile, infoFile_
								.infoUsername().get(), isSendMsg, infoFile_
								.infoUserType().get());
			}

			break;
		case R.id.ll_checkpeople:
			startActivityForResult(new Intent(
					TjryDialog_Toast_MainAcitvity.this,
					PeopleCheck_Activity.class), 3000);

			break;
		default:
			break;
		}

	}

	StringBuffer cxry_usercode = new StringBuffer();
	StringBuffer cxry_username = new StringBuffer();

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 3000 && resultCode == Activity.RESULT_OK) {
			for (int i = 0; i < Constants.list_right.size(); i++) {
				if(edt_cxry.getText().toString().trim().contains(Constants.list_right.get(i).getUserName())){
					continue;
				}
				if (i == Constants.list_right.size() - 1) {
					if(edt_cxry.getText().toString()!= null && !edt_cxry.getText().toString().equals("")){
						cxry_username.append(","+Constants.list_right.get(i)
								.getUserName());
						cxry_usercode.append(","+Constants.list_right.get(i)
								.getUserCode());
					}else{
						cxry_username.append(Constants.list_right.get(i)
								.getUserName());
						cxry_usercode.append(Constants.list_right.get(i)
								.getUserCode());		
					}

				} else {
					if(edt_cxry.getText().toString()!= null && !edt_cxry.getText().toString().equals("")){
						cxry_username.append(","+Constants.list_right.get(i)
								.getUserName());
						cxry_usercode.append(","+Constants.list_right.get(i)
								.getUserCode());
					}else{
					cxry_username.append(Constants.list_right.get(i)
							.getUserName() + ",");
					cxry_usercode.append(Constants.list_right.get(i)
							.getUserCode() + ",");
					}
				}
			}
			edt_cxry.setText(cxry_username);
			edt_cxry.setEnabled(false);
		}
	}
}
