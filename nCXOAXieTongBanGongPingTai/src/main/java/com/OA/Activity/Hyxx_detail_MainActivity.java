package com.OA.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Entity.MyNoticeDetail_Bean;
import com.OA.Service.ChangeMtingTimeManager;
import com.OA.Service.Hyxx_Detail_ShManager;
import com.OA.Service.Tzgg_Detail_ShManager;
import com.OA.Util.CommUtil;
import com.OA.Util.StringUtil;
import com.OA.View.DatePickDialog;
import com.kinggrid.iappoffice.IAppOffice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Hyxx_detail_MainActivity extends BaseActivity implements
		OnClickListener {
	InfoFile_ infoFile_;
	IAppOffice iAppOffice = null;
	LinearLayout back;
	EditText edt_hyzl, edt_hys, edt_dz, edt_rnrs, edt_zcr, edt_jlr, edt_hyyt,
			edt_cxrs, edt_xks, edt_sqr, edt_cxry, edt_hynr, edt_bz;
	TextView fjll, title_2, tv_bccl, kssj, jssj;
	Button btn_tongguo, btn_butongguo, btn_guanbi, btn_xiugaishijian,
			btn_shangchuan, btn_save, btn_close, btn_close111, btn_addpeople;
	LinearLayout ll_btn_sh, ll_btn_wdhyssq, ll_btn_hyssq, ll_upload, ll1, ll2,
			ll_checkpeople, ll_xgcl, ll_btn_wdhytz;
	CheckBox cb_send;
	ImageButton bccl;
	MyMeetingDetail_Bean bean;
	int typeOfBottomButton = 0;// 1表示从我的会议申请列表详情按钮跳入此activity，2表示从会议室申请列表详情按钮跳入此activity，3表示从会议室审核列表详情按钮跳入此activity

	String uploadedFiles = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hyxx_detail_main);
		initViewAndListener();
	}

	private void initViewAndListener() {
		infoFile_ = new InfoFile_(this);
		Constants.FileIDs = "";
		Constants.list_files = null;
		// iAppOffice = new IAppOffice(this);
		// int a = iAppOffice.init();
		back = (LinearLayout) findViewById(R.id.back);
		edt_hyzl = (EditText) findViewById(R.id.edt_hyzl);
		edt_hys = (EditText) findViewById(R.id.edt_hys);
		edt_dz = (EditText) findViewById(R.id.edt_dz);
		edt_rnrs = (EditText) findViewById(R.id.edt_rnrs);
		kssj = (TextView) findViewById(R.id.kssj);
		jssj = (TextView) findViewById(R.id.jssj);
		edt_zcr = (EditText) findViewById(R.id.edt_zcr);
		edt_jlr = (EditText) findViewById(R.id.edt_jlr);
		edt_hyyt = (EditText) findViewById(R.id.edt_hyyt);
		edt_cxrs = (EditText) findViewById(R.id.edt_cxrs);
		edt_xks = (EditText) findViewById(R.id.edt_xks);
		edt_sqr = (EditText) findViewById(R.id.edt_sqr);
		edt_cxry = (EditText) findViewById(R.id.edt_cxry);
		edt_hynr = (EditText) findViewById(R.id.edt_hynr);
		edt_bz = (EditText) findViewById(R.id.edt_bz);
		fjll = (TextView) findViewById(R.id.fjll);
		title_2 = (TextView) findViewById(R.id.title_2);
		tv_bccl = (TextView) findViewById(R.id.tv_bccl);
		fjll.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		btn_tongguo = (Button) findViewById(R.id.btn_tongguo);
		btn_butongguo = (Button) findViewById(R.id.btn_butongguo);
		btn_guanbi = (Button) findViewById(R.id.btn_guanbi);
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_close = (Button) findViewById(R.id.btn_close);
		btn_close111 = (Button) findViewById(R.id.btn_close111);
		btn_addpeople = (Button) findViewById(R.id.btn_addpeople);
		btn_xiugaishijian = (Button) findViewById(R.id.btn_xiugaishijian);
		btn_shangchuan = (Button) findViewById(R.id.btn_shangchuan);
		ll_btn_sh = (LinearLayout) findViewById(R.id.ll_btn_sh);
		ll1 = (LinearLayout) findViewById(R.id.ll1);
		ll2 = (LinearLayout) findViewById(R.id.ll2);
		ll_btn_wdhyssq = (LinearLayout) findViewById(R.id.ll_btn_wdhyssq);
		ll_btn_wdhytz = (LinearLayout) findViewById(R.id.ll_btn_wdhytz);
		ll_btn_hyssq = (LinearLayout) findViewById(R.id.ll_btn_hyssq);
		ll_upload = (LinearLayout) findViewById(R.id.ll_upload);
		ll_checkpeople = (LinearLayout) findViewById(R.id.ll_checkpeople);
		ll_xgcl = (LinearLayout) findViewById(R.id.ll_xgcl);
		cb_send = (CheckBox) findViewById(R.id.cb_send);
		bccl = (ImageButton) findViewById(R.id.bccl);
	//	ib_checkpeople = (ImageButton) findViewById(R.id.bccl);
		back.setOnClickListener(this);

		initBottomButtonAndOthers();
	}

	private void initBottomButtonAndOthers() {
		typeOfBottomButton = infoFile_.typeOfHyxxBottomButton().get();
		switch (typeOfBottomButton) {
		case 0:
			Hyxx_detail_MainActivity.this.finish();
			break;
		case 1:
			title_2.setText("我的会议申请");
			tv_bccl.setText("补传材料：");
			ll_btn_wdhyssq.setVisibility(View.VISIBLE);
			ll_btn_wdhytz.setVisibility(View.GONE);
			ll_btn_sh.setVisibility(View.GONE);
			ll_btn_hyssq.setVisibility(View.GONE);
			ll_upload.setVisibility(View.VISIBLE);
			cb_send.setClickable(false);
			edt_hyzl.setEnabled(false);
			edt_hys.setEnabled(false);
			edt_dz.setEnabled(false);
			edt_rnrs.setEnabled(false);
			edt_zcr.setEnabled(false);
			edt_jlr.setEnabled(false);
			edt_hyyt.setEnabled(false);
			edt_cxrs.setEnabled(false);
			edt_xks.setEnabled(false);
			edt_sqr.setEnabled(false);
			edt_cxry.setEnabled(false);
			edt_hynr.setEnabled(false);
			edt_bz.setEnabled(false);
			ll1.setOnClickListener(Hyxx_detail_MainActivity.this);
			ll2.setOnClickListener(Hyxx_detail_MainActivity.this);
			fjll.setOnClickListener(Hyxx_detail_MainActivity.this);
			bccl.setOnClickListener(Hyxx_detail_MainActivity.this);
			btn_xiugaishijian.setOnClickListener(Hyxx_detail_MainActivity.this);
			btn_shangchuan.setOnClickListener(Hyxx_detail_MainActivity.this);
			hyxx_detail.getMeeting_Detail(this, 1, infoFile_.infoUsername()
					.get(), infoFile_.infoUserType().get(), infoFile_
					.RoomLogID().get());
			break;
		case 2:
			title_2.setText("会议室申请");
			tv_bccl.setText("相关材料：");
			ll_xgcl.setVisibility(View.GONE);
			ll_btn_wdhyssq.setVisibility(View.GONE);
			ll_btn_wdhytz.setVisibility(View.GONE);
			ll_btn_sh.setVisibility(View.GONE);
			ll_btn_hyssq.setVisibility(View.VISIBLE);
			ll_upload.setVisibility(View.VISIBLE);
			edt_hys.setText(infoFile_.nameOfMeeting().get());
			edt_hys.setEnabled(false);
			edt_dz.setText(infoFile_.addressOfMeeting().get());
			edt_dz.setEnabled(false);
			edt_rnrs.setText(infoFile_.rnrsOfMeeting().get());
			edt_rnrs.setEnabled(false);
			edt_sqr.setText(infoFile_.infoUsername2().get());
			edt_sqr.setEnabled(false);
			edt_cxry.setEnabled(false);
			// edt_hyzl.setText(infoFile_.typeOfMeeting().get() != null ?
			// infoFile_.typeOfMeeting().get() : "-");
			edt_hyzl.setEnabled(false);
			btn_save.setOnClickListener(Hyxx_detail_MainActivity.this);
			ll_checkpeople.setOnClickListener(Hyxx_detail_MainActivity.this);
	//		ib_checkpeople.setOnClickListener(Hyxx_detail_MainActivity.this);
			ll1.setOnClickListener(Hyxx_detail_MainActivity.this);
			ll2.setOnClickListener(Hyxx_detail_MainActivity.this);
			bccl.setOnClickListener(Hyxx_detail_MainActivity.this);
			btn_save.setOnClickListener(Hyxx_detail_MainActivity.this);
			btn_close.setOnClickListener(Hyxx_detail_MainActivity.this);
			// hyxx_detail.getMeeting_Detail(this, 1,
			// infoFile_.infoUsername().get(), infoFile_.infoUserType().get()
			// ,infoFile_.RoomLogID().get());
			break;
		case 3:
			title_2.setText("会议室审核");
			ll_btn_wdhyssq.setVisibility(View.GONE);
			ll_btn_wdhytz.setVisibility(View.GONE);
			ll_btn_sh.setVisibility(View.VISIBLE);
			ll_btn_hyssq.setVisibility(View.GONE);
			ll_upload.setVisibility(View.GONE);
			cb_send.setClickable(false);
			edt_hyzl.setEnabled(false);
			fjll.setOnClickListener(Hyxx_detail_MainActivity.this);
			edt_hys.setEnabled(false);
			edt_dz.setEnabled(false);
			edt_rnrs.setEnabled(false);
			edt_zcr.setEnabled(false);
			edt_jlr.setEnabled(false);
			edt_hyyt.setEnabled(false);
			edt_cxrs.setEnabled(false);
			edt_xks.setEnabled(false);
			edt_sqr.setEnabled(false);
			edt_cxry.setEnabled(false);
			edt_hynr.setEnabled(false);
			edt_bz.setEnabled(false);
			btn_butongguo.setOnClickListener(Hyxx_detail_MainActivity.this);
			btn_tongguo.setOnClickListener(Hyxx_detail_MainActivity.this);
			btn_guanbi.setOnClickListener(Hyxx_detail_MainActivity.this);
			hyxx_detail.getMeeting_Detail(this, 1, infoFile_.infoUsername()
					.get(), infoFile_.infoUserType().get(), infoFile_
					.RoomLogID().get());
			break;
		case 4:
			title_2.setText("我的会议通知");
			ll_btn_wdhyssq.setVisibility(View.GONE);
			ll_btn_wdhytz.setVisibility(View.VISIBLE);
			ll_btn_sh.setVisibility(View.GONE);
			ll_btn_hyssq.setVisibility(View.GONE);
			ll_upload.setVisibility(View.GONE);
			cb_send.setClickable(false);
			edt_hyzl.setEnabled(false);
			fjll.setOnClickListener(Hyxx_detail_MainActivity.this);
			edt_hys.setEnabled(false);
			edt_dz.setEnabled(false);
			edt_rnrs.setEnabled(false);
			edt_zcr.setEnabled(false);
			edt_jlr.setEnabled(false);
			edt_hyyt.setEnabled(false);
			edt_cxrs.setEnabled(false);
			edt_xks.setEnabled(false);
			edt_sqr.setEnabled(false);
			edt_cxry.setEnabled(false);
			edt_hynr.setEnabled(false);
			edt_bz.setEnabled(false);
			btn_addpeople.setOnClickListener(Hyxx_detail_MainActivity.this);
			btn_close111.setOnClickListener(Hyxx_detail_MainActivity.this);
			hyxx_detail.getMeeting_Detail(this, 1, infoFile_.infoUsername()
					.get(), infoFile_.infoUserType().get(), infoFile_
					.RoomLogID().get());
			break;
		default:
			break;
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_tongguo:

			hyxx_detail.VerifyMtRoom(Hyxx_detail_MainActivity.this, 2,
					infoFile_.infoUsername().get(), infoFile_.infoUserType()
							.get(), infoFile_.RoomLogID().get(), "true",
					infoFile_.StepDataID().get());
			break;
		case R.id.btn_butongguo:
			hyxx_detail.VerifyMtRoom(Hyxx_detail_MainActivity.this, 2,
					infoFile_.infoUsername().get(), infoFile_.infoUserType()
							.get(), infoFile_.RoomLogID().get(), "false",
					infoFile_.StepDataID().get());
			break;
		case R.id.btn_guanbi:
			Hyxx_detail_MainActivity.this.finish();
			break;

		case R.id.btn_save:
			if (checkIfBlank()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("SignCode", infoFile_.infoUserType().get());
				map.put("UserCode", infoFile_.infoUsername().get());
				map.put("Host", edt_zcr.getText().toString());
				map.put("Applicant", edt_sqr.getText().toString());
				map.put("InvitingCode", cxry_usercode.toString());
				map.put("InvitingName", cxry_username.toString());
				map.put("MeetingContent", edt_hynr.getText().toString());
				map.put("MeetingStart", kssj.getText().toString());
				map.put("MeetingEnd", jssj.getText().toString());
				map.put("MeetingTitle", edt_hyyt.getText().toString());
				map.put("Memo", edt_bz.getText().toString());
				map.put("Position", edt_dz.getText().toString());
				map.put("RecorderName", edt_jlr.getText().toString());
				map.put("RoomName", infoFile_.nameOfMeeting().get());
				if (cb_send.isChecked()) {
					map.put("SendType", "1");
				} else {
					map.put("SendType", "2");
				}
				map.put("FileIDs", uploadedFiles);
				map.put("RoomID", infoFile_.ROOMID().get());
				map.put("InvitingCount",
						Integer.valueOf(edt_cxrs.getText().toString())
								.intValue());
				map.put("MatCard", Integer
						.valueOf(edt_xks.getText().toString()).intValue());
				map.put("ContainPeople",
						Integer.valueOf(infoFile_.rnrsOfMeeting().get())
								.intValue());
				map.put("RoomLogID", infoFile_.RoomLogID().get());
				hyxx_detail.ApplyMtRoom(Hyxx_detail_MainActivity.this, 2000,
						map);
			}
			break;
		case R.id.btn_close:
			Hyxx_detail_MainActivity.this.finish();
			break;
		case R.id.btn_close111:
			Hyxx_detail_MainActivity.this.finish();
			break;
		case R.id.btn_addpeople:
			break;
		case R.id.btn_xiugaishijian:
			changeMting.ChangeMtingTime(this, 1, infoFile_.RoomLogID().get(),
					kssj.getText().toString(), jssj.getText().toString(),
					infoFile_.infoUsername().get(), infoFile_.infoUserType()
							.get());
			break;
		 case R.id.btn_shangchuan:
			 if(uploadedFiles.equals("")){
				 Toast.makeText(Hyxx_detail_MainActivity.this, "请上传附件!",
							Toast.LENGTH_SHORT).show();
				 return;
			 }
			 Map<String,Object> map = new HashMap<String, Object>();

			 map.put("fileIDs", uploadedFiles);
			 map.put("RoomLogID", infoFile_.RoomLogID().get());
			 hyxx_detail.SupplyFile(Hyxx_detail_MainActivity.this, 4444, map);
			 break;
		case R.id.fjll:
			if (bean != null && bean.getObj().getFileList() != null
					&& bean.getObj().getFileList().size() != 0) {
				Constants.list_files = bean.getObj().getFileList();
				startActivityForResult(new Intent(
						Hyxx_detail_MainActivity.this,
						UpLoadFile_Activity.class).addFlags(2000), 1000);
			} else {
				Toast.makeText(Hyxx_detail_MainActivity.this, "无附件!",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.back:
			Hyxx_detail_MainActivity.this.finish();
			break;
		case R.id.ll1:
			DatePickDialog.showDateCheckDialog(this, kssj, true);
			break;
		case R.id.ll2:
			DatePickDialog.showDateCheckDialog(this, jssj, true);
			break;
		case R.id.bccl:
			if (typeOfBottomButton == 1 || typeOfBottomButton == 2
					|| typeOfBottomButton == 4) {
				startActivityForResult(new Intent(
						Hyxx_detail_MainActivity.this,
						UpLoadFile_Activity.class).addFlags(1000), 2000);
			}
			break;
	//	case R.id.ib_checkpeople:
		case R.id.ll_checkpeople:
			startActivityForResult(new Intent(Hyxx_detail_MainActivity.this,
					PeopleCheck_Activity.class), 3000);
			// Toast.makeText(Hyxx_detail_MainActivity.this, "选择人员",
			// Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

	private boolean checkIfBlank() {
		// if(StringUtil.isBlank(edt_hyzl.getText().toString())){
		// Toast.makeText(this, "会议种类不能为空！", Toast.LENGTH_SHORT).show();
		// return false;
		// }
		if (StringUtil.isBlank(edt_hys.getText().toString())) {
			Toast.makeText(this, "会议室不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(edt_dz.getText().toString())) {
			Toast.makeText(this, "地址不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(edt_rnrs.getText().toString())) {
			Toast.makeText(this, "容纳人数不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(kssj.getText().toString())) {
			Toast.makeText(this, "开始时间不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(jssj.getText().toString())) {
			Toast.makeText(this, "结束时间不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(edt_zcr.getText().toString())) {
			Toast.makeText(this, "主持人不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(edt_jlr.getText().toString())) {
			Toast.makeText(this, "记录人不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(edt_hyyt.getText().toString())) {
			Toast.makeText(this, "会议议题不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(edt_cxrs.getText().toString())) {
			Toast.makeText(this, "出席人数不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(edt_cxrs.getText().toString());
		Matcher m1 = p.matcher(edt_xks.getText().toString());
		if (!m.matches()) {
			Toast.makeText(this, "出席人数请输入数字!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (!m1.matches()) {
			Toast.makeText(this, "席卡数请输入数字!", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(edt_xks.getText().toString())) {
			Toast.makeText(this, "席卡数不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(edt_sqr.getText().toString())) {
			Toast.makeText(this, "申请人不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(edt_cxry.getText().toString())) {
			Toast.makeText(this, "出席人员不能为空！", Toast.LENGTH_SHORT).show();
			// return false;
		}
		if (StringUtil.isBlank(edt_hynr.getText().toString())) {
			Toast.makeText(this, "会议内容不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(edt_bz.getText().toString())) {
			Toast.makeText(this, "备注不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private Hyxx_Detail_ShManager hyxx_detail = new Hyxx_Detail_ShManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			switch (paramInt) {
			case 1:// 会议室详情
				if (handleResult.getiSuccess().equals("success")) {
					bean = Constants.mymeetingdetail_bean;
					if (bean != null && bean.getStatus().equals("2000")) {
						initViewAndListenersAgain();
					} else if (bean != null && bean.getStatus().equals("0000")) {
						Toast.makeText(Hyxx_detail_MainActivity.this,
								"获取会议信息失败，安全验证未通过!", Toast.LENGTH_SHORT).show();
					} else if (bean != null && bean.getStatus().equals("5001")) {
						Toast.makeText(Hyxx_detail_MainActivity.this,
								"获取会议信息失败，公告数据不存在!", Toast.LENGTH_SHORT).show();
					}
				}

				break;
			case 2:// 会议室审核
				if (handleResult.getiSuccess().equals("success")) {
					Hyxx_detail_MainActivity.this.finish();
				}
				break;
			case 2000:// 会议室申请
				if (handleResult.getiSuccess().equals("success")) {
					Hyxx_detail_MainActivity.this.finish();
				}
				break;
			case 4444://补传材料
				if (handleResult.getiSuccess().equals("success")) {
					uploadedFiles = "";
				}
				break;
			default:
				break;
			}
		}
	};
	private ChangeMtingTimeManager changeMting = new ChangeMtingTimeManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess().equals("success")) {
				bean = Constants.mymeetingdetail_bean;
				if (bean != null && bean.getStatus().equals("2000")) {
					Toast.makeText(Hyxx_detail_MainActivity.this, "修改成功！",
							Toast.LENGTH_SHORT).show();
				} else if (bean != null && bean.getStatus().equals("5001")) {
					Toast.makeText(Hyxx_detail_MainActivity.this,
							"此会议未经审核，不能修改时间", Toast.LENGTH_SHORT).show();
				} else if (bean != null && bean.getStatus().equals("5002")) {
					Toast.makeText(Hyxx_detail_MainActivity.this, "修改失败！",
							Toast.LENGTH_SHORT).show();
				} else if (bean != null && bean.getStatus().equals("5011")) {
					Toast.makeText(Hyxx_detail_MainActivity.this, "开始时间大于结束时间",
							Toast.LENGTH_SHORT).show();
				} else if (bean != null && bean.getStatus().equals("5012")) {
					Toast.makeText(Hyxx_detail_MainActivity.this, "开始时间小于目前时间",
							Toast.LENGTH_SHORT).show();
				} else if (bean != null && bean.getStatus().equals("5013")) {
					Toast.makeText(Hyxx_detail_MainActivity.this,
							"此时间段会议室已有预约", Toast.LENGTH_SHORT).show();
				}

			}
		}
	};

	protected void initViewAndListenersAgain() {
		// edt_hyzl.setText((CharSequence) (bean.getObj().getKind()!= null
		// ?bean.getObj().getKind() :"-") );
		edt_hys.setText((CharSequence) (bean.getObj().getRoomName() != null ? bean
				.getObj().getRoomName() : "-"));
		edt_dz.setText((CharSequence) (bean.getObj().getPosition() != null ? bean
				.getObj().getPosition() : "-"));
		edt_rnrs.setText((CharSequence) (bean.getObj().getContainPeople() != null ? bean
				.getObj().getContainPeople() : "-"));
		kssj.setText((CharSequence) (bean.getObj().getMeetingStart() != null ? bean
				.getObj().getMeetingStart() : "-"));
		jssj.setText((CharSequence) (bean.getObj().getMeetingEnd() != null ? bean
				.getObj().getMeetingEnd() : "-"));
		edt_zcr.setText((CharSequence) (bean.getObj().getHost() != null ? bean
				.getObj().getHost() : "-"));
		edt_jlr.setText((CharSequence) (bean.getObj().getRecorderName() != null ? bean
				.getObj().getRecorderName() : "-"));
		edt_hyyt.setText((CharSequence) (bean.getObj().getMeetingTitle() != null ? bean
				.getObj().getMeetingTitle() : "-"));
		edt_cxrs.setText((CharSequence) (bean.getObj().getInvitingCount() != null ? bean
				.getObj().getInvitingCount() : "-"));
		edt_xks.setText((CharSequence) (bean.getObj().getMatCard() != null ? bean
				.getObj().getMatCard() : "-"));
		edt_sqr.setText((CharSequence) (bean.getObj().getApplicant() != null ? bean
				.getObj().getApplicant() : "-"));
		edt_cxry.setText((CharSequence) (bean.getObj().getInvitingName() != null ? bean
				.getObj().getInvitingName() : "-"));
		edt_hynr.setText((CharSequence) (bean.getObj().getMeetingContent() != null ? bean
				.getObj().getMeetingContent() : "-"));
		edt_bz.setText((CharSequence) (bean.getObj().getMemo() != null ? bean
				.getObj().getMemo() : "-"));
		if (bean.getObj().getSendType() != null
				&& bean.getObj().getSendType().equals("1")) {
			cb_send.setChecked(true);
			cb_send.setClickable(false);
		} else {
			cb_send.setChecked(false);
			cb_send.setClickable(false);
		}
		// edt_tgbt.setText(bean.getObj().getTitle() != null ?
		// bean.getObj().getTitle() : "-");
		// edt_tglx.setText(String.valueOf(bean.getObj().getTypeID()) != null ?
		// String.valueOf(bean.getObj().getTypeID()) : "-");
		// edt_ztc.setText(bean.getObj().getSubject() != null ?
		// bean.getObj().getSubject() : "-");
		// edt_zdllry.setText(bean.getObj().getBrowseName() != null ?
		// bean.getObj().getBrowseName() : "-");
		// fjll.setOnClickListener(Hyxx_detail_MainActivity.this);
		// zwll.setOnClickListener(Hyxx_detail_MainActivity.this);
		// if(infoFile_.statusOfNotice().get() != null &&
		// infoFile_.statusOfNotice().get().equals("1000")){
		// btn_tongguo.setOnClickListener(Hyxx_detail_MainActivity.this);
		// btn_butongguo.setOnClickListener(Hyxx_detail_MainActivity.this);
		// }else{
		// btn_tongguo.setVisibility(View.INVISIBLE);
		// btn_butongguo.setVisibility(View.INVISIBLE);
		// }
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
		if(requestCode == 2000 && resultCode == Activity.RESULT_OK){
			if(uploadedFiles.equals("")){
				uploadedFiles = Constants.FileIDs;
			}else
			uploadedFiles =uploadedFiles+","+Constants.FileIDs;
		}
	}
}
