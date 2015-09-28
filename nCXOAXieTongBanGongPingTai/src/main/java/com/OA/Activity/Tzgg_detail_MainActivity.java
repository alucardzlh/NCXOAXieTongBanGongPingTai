package com.OA.Activity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.IDAndValue_Bean;
import com.OA.Entity.LoginOA;
import com.OA.Entity.MyNoticeDetail_Bean;
import com.OA.Service.Tzgg_Detail_ShManager;
import com.OA.Util.CommUtil;
import com.OA.Util.FileUtil;
import com.OA.Util.IAppOfficeUtil;
import com.OA.Util.StringUtil;
import com.OA.View.DatePickDialog;
import com.OA.View.MyPopupWindow;
import com.googlecode.androidannotations.annotations.OnActivityResult;
import com.kinggrid.iappoffice.IAppOffice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Tzgg_detail_MainActivity extends BaseActivity implements
		OnClickListener {
	InfoFile_ infoFile_;
	IAppOffice iAppOffice = null;
	LinearLayout back; // 返回按钮
	/*
	 * ImageButton ib_checkpeople; //选择人员按钮 EditText edt_tzbt, edt_ztc,
	 * edt_zdllry; //通告标题,主题词,指定浏览人员输入框 TextView fjll, zwll, tv_title, tv_jzsj,
	 * edt_tglx, edt_tglc; //附件浏览,正文浏览,截止时间，通告类型 //通告流程控件 Button btn_tongguo,
	 * btn_butongguo, btn_guanbi,btn_save,btn_close,btn_xiugai;
	 * //通告按钮，不通过按钮，关闭按钮 //保存按钮，关闭按钮 LinearLayout ll_btn_tzgg_save,
	 * ll_btn_tzgg_tongguo, ll_tzgg_tglc, //各线性布局，目的为方便设置显示隐藏 ImageView back; //
	 * 返回按钮
	 */
	// ImageButton ib_checkpeople; // 选择人员按钮
	EditText edt_tgbt, edt_ztc, edt_zdllry; // 通告标题,主题词,指定浏览人员输入框
	TextView fjll, zwll, tv_title, tv_jzsj, edt_tglx, edt_tglc; // 附件浏览,正文浏览,截止时间，通告类型
																// 通告流程控件
	Button btn_tongguo, btn_butongguo, btn_guanbi, btn_save, btn_close; // 通告按钮，不通过按钮，关闭按钮
																		// 保存按钮，关闭按钮
	LinearLayout ll_btn_tzgg_save, ll_btn_tzgg_tongguo,
			ll_tzgg_tglc, // 各线性布局，目的为方便设置显示隐藏
			ll_tzgg_tglc1, ll_tzgg_tglx1, ll_tzgg_tglx, ll_tzgg_jzsj,
			ll_checkpeople;
	MyNoticeDetail_Bean bean; // 通知公告详情bean

	List<IDAndValue_Bean> list_fenlei = null; // 通告类型数据列表
	List<IDAndValue_Bean> list_liucheng = null; // 通告流程数据列表

	int TypeID, FlowID;

	int fromWhere = 100;// 0表示从添加按钮按钮跳进此activity，1表示从浏览列表和审核列表中详情按钮跳入此activity
						// 2表示从修改按钮跳进此activity
	int initResult;

	String file_qc_path = "";// 保存待上传的word正文路径

	String path = "";
	String path_qc = "", path_zw = "", path_xg = "";
	
	String userName = "";//修改通知公告时，保存获取到的公告详情中的指定浏览人员名称
	String userCode = "";//修改通知公告时，保存获取到的公告详情中的指定浏览人员ID

	String uploadedFiles = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tzgg_detail_main);
		initViewAndListener();

	}

	private void initViewAndListener() {
		fromWhere = getIntent().getFlags();
		Constants.FileIDs = "";
		infoFile_ = new InfoFile_(this);
		path =  Environment.getExternalStorageDirectory()
				+ "/OADownFile/"+infoFile_.infoUsername().get()+"/";
		iAppOffice = IAppOfficeUtil.getInstance(this);
		back = (LinearLayout) findViewById(R.id.back);
		edt_tgbt = (EditText) findViewById(R.id.edt_tgbt);
		edt_ztc = (EditText) findViewById(R.id.edt_ztc);
		edt_tglx = (TextView) findViewById(R.id.edt_tglx);
		edt_tglc = (TextView) findViewById(R.id.edt_tglc);
		edt_zdllry = (EditText) findViewById(R.id.edt_zdllry);
		fjll = (TextView) findViewById(R.id.fjll);
		zwll = (TextView) findViewById(R.id.zwll);
		tv_jzsj = (TextView) findViewById(R.id.tv_jzsj);
		tv_title = (TextView) findViewById(R.id.tv_title);
		ll_btn_tzgg_save = (LinearLayout) findViewById(R.id.ll_btn_tzgg_save);
		ll_btn_tzgg_tongguo = (LinearLayout) findViewById(R.id.ll_btn_tzgg_tongguo);
		ll_tzgg_tglc = (LinearLayout) findViewById(R.id.ll_tzgg_tglc);
		ll_tzgg_tglx = (LinearLayout) findViewById(R.id.ll_tzgg_tglx);
		ll_tzgg_tglc1 = (LinearLayout) findViewById(R.id.ll_tzgg_tglc1);
		ll_tzgg_tglx1 = (LinearLayout) findViewById(R.id.ll_tzgg_tglx1);
		ll_tzgg_jzsj = (LinearLayout) findViewById(R.id.ll_tzgg_jzsj);
		ll_checkpeople = (LinearLayout) findViewById(R.id.ll_checkpeople);
		fjll.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		zwll.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		btn_tongguo = (Button) findViewById(R.id.btn_tongguo);
		btn_butongguo = (Button) findViewById(R.id.btn_butongguo);
		btn_guanbi = (Button) findViewById(R.id.btn_guanbi);
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_close = (Button) findViewById(R.id.btn_close);
		// ib_checkpeople = (ImageButton) findViewById(R.id.ib_checkpeople);
		back.setOnClickListener(this);
		btn_guanbi.setOnClickListener(this);
		tv_title.setText(Constants.title);
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/OADownFile/");
		if(!file.exists()){
			file.mkdir();
		}
		File file1 = new File(path);
		if(!file1.exists()){
			file1.mkdir();
		}
		HandleSomeViewsVisibility();
		if(iAppOffice == null){
			zwll.setTextColor(getResources().getColor(R.color.gray));
			zwll.setEnabled(false);
		}else{
			zwll.setEnabled(true);
		}
	}

	private void HandleSomeViewsVisibility() {
		switch (fromWhere) {
		case 0:// 起草通知公告
			initFilePath();
			ll_btn_tzgg_save.setVisibility(View.VISIBLE);
			ll_btn_tzgg_tongguo.setVisibility(View.GONE);
			ll_tzgg_jzsj.setVisibility(View.VISIBLE);
			ll_tzgg_jzsj.setOnClickListener(this);
			ll_tzgg_tglc1.setVisibility(View.VISIBLE);
			ll_tzgg_tglx1.setVisibility(View.VISIBLE);
			ll_tzgg_tglx1.setOnClickListener(this);
			ll_tzgg_tglc1.setOnClickListener(this);
			// ib_checkpeople.setOnClickListener(this);
			btn_save.setOnClickListener(this);
			btn_close.setOnClickListener(this);
			fjll.setOnClickListener(this);
			zwll.setOnClickListener(this);
			ll_checkpeople.setOnClickListener(this);
			edt_tgbt.setEnabled(true);
			edt_ztc.setEnabled(true);
			edt_zdllry.setEnabled(false);
			// btn_xiugai.setOnClickListener(this);
			fjll.setText("上传附件");
			zwll.setText("起草正文");
			tzgg_detail.GetNoticeTypeListAndFlowSetList(this, 1000, null);
			break;
		case 1: // 审核或者浏览通知公告
			ll_btn_tzgg_save.setVisibility(View.GONE);
			ll_btn_tzgg_tongguo.setVisibility(View.VISIBLE);
			ll_tzgg_jzsj.setVisibility(View.GONE);
			ll_tzgg_tglc1.setVisibility(View.GONE);
			ll_tzgg_tglx1.setVisibility(View.VISIBLE);
			edt_tgbt.setEnabled(false);
			edt_ztc.setEnabled(false);
			edt_zdllry.setEnabled(false);
			fjll.setText("附件浏览");
			zwll.setText("正文浏览");
			fjll.setOnClickListener(this);
			zwll.setOnClickListener(this);
			tzgg_detail.getNotice_Detail(this, 1, infoFile_.infoUsername()
					.get(), infoFile_.infoUserType().get(), infoFile_
					.NoticeID().get());
			break;
		case 2: // 修改数据//调接口
			ll_btn_tzgg_save.setVisibility(View.VISIBLE);
			ll_btn_tzgg_tongguo.setVisibility(View.GONE);
			ll_tzgg_jzsj.setVisibility(View.VISIBLE);
			ll_tzgg_jzsj.setOnClickListener(this);
			ll_tzgg_tglc1.setVisibility(View.GONE);
			ll_tzgg_tglx1.setVisibility(View.VISIBLE);
			// ib_checkpeople.setVisibility(View.VISIBLE);
			ll_tzgg_tglx1.setOnClickListener(this);
			ll_tzgg_tglc1.setOnClickListener(this);
			// ib_checkpeople.setOnClickListener(this);
			ll_checkpeople.setOnClickListener(this);
			fjll.setOnClickListener(this);
			zwll.setOnClickListener(this);
			edt_tgbt.setEnabled(true);
			edt_ztc.setEnabled(true);
			edt_zdllry.setEnabled(true);
			btn_save.setOnClickListener(this);
			fjll.setText("上传附件");
			zwll.setText("修改正文");
			tzgg_detail.GetNoticeTypeListAndFlowSetList(this, 1000, null);
			tzgg_detail.getNotice_Detail(this, 1, infoFile_.infoUsername()
					.get(), infoFile_.infoUserType().get(), infoFile_
					.NoticeID().get());
			btn_close.setOnClickListener(this);
			break;
		default:
			break;
		}
	}

	private void initFilePath() {

		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日HH时mm分ss秒");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		path_qc = path + infoFile_.infoUsername().get() + str + ".doc";

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}

	StringBuffer cxry_usercode = new StringBuffer();
	StringBuffer cxry_username = new StringBuffer();
	boolean hasAddUsers = false;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 3000 && resultCode == Activity.RESULT_OK) {
			
			if(fromWhere == 2 && !hasAddUsers){
				hasAddUsers = true;
				cxry_username.append(userName);
				cxry_usercode.append(userCode);
			}
			for (int i = 0; i < Constants.list_right.size(); i++) {
				if(edt_zdllry.getText().toString().trim().contains(Constants.list_right.get(i).getUserName())){
					continue;
				}
				if (i == Constants.list_right.size() - 1) {
					if (edt_zdllry.getText().toString() != null
							&& !edt_zdllry.getText().toString().equals("")) {
						cxry_username.append(","
								+ Constants.list_right.get(i).getUserName());
						cxry_usercode.append(","
								+ Constants.list_right.get(i).getUserCode());
					} else {
						cxry_username.append(Constants.list_right.get(i)
								.getUserName());
						cxry_usercode.append(Constants.list_right.get(i)
								.getUserCode());
					}
				} else {
					if (edt_zdllry.getText().toString() != null
							&& !edt_zdllry.getText().toString().equals("")) {
						cxry_username.append(","
								+ Constants.list_right.get(i).getUserName());
						cxry_usercode.append(","
								+ Constants.list_right.get(i).getUserCode());
					} else {
						cxry_username.append(Constants.list_right.get(i)
								.getUserName() + ",");
						cxry_usercode.append(Constants.list_right.get(i)
								.getUserCode() + ",");
					}
				}
			}
			edt_zdllry.setText(cxry_username);
			edt_zdllry.setEnabled(false);
		}
		if(requestCode == 2000 && resultCode == Activity.RESULT_OK){
			if(uploadedFiles.equals("")){
				uploadedFiles = Constants.FileIDs;
			}else
			uploadedFiles =uploadedFiles+","+Constants.FileIDs;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_tongguo:
			tzgg_detail.VerifyNotice(Tzgg_detail_MainActivity.this, 2,
					infoFile_.infoUsername().get(), infoFile_.infoUserType()
							.get(), infoFile_.NoticeID().get(), "true",
					infoFile_.flowid().get(), infoFile_.WFStepID().get());
			break;
		case R.id.btn_butongguo:
			tzgg_detail.VerifyNotice(Tzgg_detail_MainActivity.this, 2,
					infoFile_.infoUsername().get(), infoFile_.infoUserType()
							.get(), infoFile_.NoticeID().get(), "false",
					infoFile_.flowid().get(), infoFile_.WFStepID().get());
			break;
		case R.id.btn_guanbi:
			Tzgg_detail_MainActivity.this.finish();
			break;
		case R.id.fjll:
			switch (fromWhere) {
			case 0:
			case 2://上传
				startActivityForResult(new Intent(
						Tzgg_detail_MainActivity.this,
						UpLoadFile_Activity.class).addFlags(1000), 2000);
				break;
			case 1://下载
				if (bean != null && bean.getObj().getFileList() != null
						&& bean.getObj().getFileList().size() != 0) {
					Constants.list_files = bean.getObj().getFileList();
					startActivityForResult(new Intent(
							Tzgg_detail_MainActivity.this,
							UpLoadFile_Activity.class).addFlags(2000), 1000);
				} else {
					Toast.makeText(Tzgg_detail_MainActivity.this, "无附件!",
							Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
			}
			break;
		case R.id.zwll:

			switch (fromWhere) {
			case 0:// 起草

				File file_qc = new File(path_qc);
				if (!file_qc.exists()) {
					try {
						file_qc.createNewFile();
						if (iAppOffice != null) {
							// IAppOfficeUtil.InsertText(iAppOffice,
							// "tttttttttttttttt");
							// IAppOfficeUtil.setText(iAppOffice,
							// "ggggggggggggggg");
							IAppOfficeUtil.CreateAndOpenNewOffice(iAppOffice,
									path_qc);
							file_qc_path = path_qc;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					if (iAppOffice != null) {
						IAppOfficeUtil.OpenOfficeFile(iAppOffice, path_qc);
					}
				}
				break;
			case 1:// 审核浏览
				path_zw = path + infoFile_.infoUsername().get()
						+ bean.getObj().getNoticeID() + ".doc";
				FileUtil.writeBase64StringToFile(bean.getObj().getContent(),
						path_zw);
				if (iAppOffice != null) {
					IAppOfficeUtil.OpenOfficeFile(iAppOffice, path_zw);
				}
				break;
			case 2:// 修改
/*				path_xg = path + infoFile_.infoUsername().get()
						+ bean.getObj().getNoticeID() + ".doc";
				FileUtil.writeBase64StringToFile(bean.getObj().getContent(),
						path_xg);*/
				if (iAppOffice != null) {
					IAppOfficeUtil.OpenOfficeFile(iAppOffice, path_xg);
				}
	//			file_qc_path = path_xg;
				break;
			default:
				break;
			}
			break;
		case R.id.ll_tzgg_jzsj:
			DatePickDialog.showDateCheckDialog(this, tv_jzsj, true);
			break;
		case R.id.ll_tzgg_tglc1:
			if (list_liucheng.size() > 0) {

				new MyPopupWindow(Tzgg_detail_MainActivity.this, list_liucheng,
						ll_tzgg_tglc, MyPopupWindow.BOTTOM, "") {
					@Override
					protected void doNext(int position) {
						edt_tglc.setText((CharSequence) list_liucheng.get(
								position).getValue());
						FlowID = (int) list_liucheng.get(position).getID();
					}
				};
			}
			break;
		case R.id.ll_tzgg_tglx1:

			if (list_fenlei.size() > 0) {
				new MyPopupWindow(Tzgg_detail_MainActivity.this, list_fenlei,
						ll_tzgg_tglx, MyPopupWindow.BOTTOM, "") {
					@Override
					protected void doNext(int position) {
						edt_tglx.setText((CharSequence) list_fenlei.get(
								position).getValue());
						TypeID = (int) list_fenlei.get(position).getID();
					}
				};
			}
			break;
		case R.id.ll_checkpeople:
			startActivityForResult(new Intent(Tzgg_detail_MainActivity.this,
					PeopleCheck_Activity.class), 3000);
			break;
		case R.id.btn_save:
			if (checkIfBlankInEditttext()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("SignCode", infoFile_.infoUserType().get());
				map.put("UserCode", infoFile_.infoUsername().get());
				map.put("Title", edt_tgbt.getText().toString().trim());
				map.put("Subject", edt_ztc.getText().toString().trim());
				byte[] bytes = null;
				File file_content = new File(file_qc_path);
				if (file_content.exists()) {
					try {
						bytes = FileUtil.getBytesFromFile(file_content);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				String str;
				if (bytes != null) {
					str = Base64.encodeToString(bytes, Base64.DEFAULT);
				} else {
					str = "";
				}
				if(str.equals("")){
					Toast.makeText(Tzgg_detail_MainActivity.this, "请先起草正文！", Toast.LENGTH_SHORT).show();
					return;
				}
				map.put("Content", str);

				map.put("EndDate", tv_jzsj.getText().toString().trim());
				map.put("FileIDs", uploadedFiles);
				map.put("TypeID", TypeID);
				map.put("BrowseName", cxry_username.toString());
				map.put("BrowseCode", cxry_usercode.toString());
				if (fromWhere == 0) {
					map.put("NoticeID", fromWhere);
					map.put("FlowID", FlowID);
				} else if(fromWhere == 2){
					map.put("NoticeID",
							Integer.valueOf(infoFile_.NoticeID().get())
									.intValue());
					map.put("FlowID", 0);
				}
				tzgg_detail.SaveNotice(this, 2000, map);
			}
			break;
		case R.id.btn_close:
			Tzgg_detail_MainActivity.this.finish();
			break;
		case R.id.back:
			Tzgg_detail_MainActivity.this.finish();
			break;
		default:
			break;
		}
	}

	private boolean checkIfBlankInEditttext() {
		if (StringUtil.isBlank(edt_tgbt.getText().toString().trim())) {
			Toast.makeText(this, "通告标题不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(edt_ztc.getText().toString().trim())) {
			Toast.makeText(this, "主题词不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(tv_jzsj.getText().toString().trim())) {
			Toast.makeText(this, "截止时间不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (StringUtil.isBlank(edt_zdllry.getText().toString().trim())) {
			Toast.makeText(this, "指定浏览人员不能为空！", Toast.LENGTH_SHORT).show(); //
			return false;
		}
	/*	if (StringUtil.isBlank(edt_tglx.getText().toString().trim())) {
			Toast.makeText(this, "通告类型不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}*/
		if (StringUtil.isBlank(edt_tglc.getText().toString().trim())) {
			Toast.makeText(this, "通告流程不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private Tzgg_Detail_ShManager tzgg_detail = new Tzgg_Detail_ShManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			switch (paramInt) {
			case 1:// 获取通知公告详情
				if (handleResult.getiSuccess().equals("success")) {
					bean = Constants.mynoticedetail_bean;
					if (bean != null && bean.getStatus().equals("2000")) {
						initViewAndListenersAgain();
					} else if (bean != null && bean.getStatus().equals("0000")) {
						Toast.makeText(Tzgg_detail_MainActivity.this,
								"获取通告失败，安全验证未通过!", Toast.LENGTH_SHORT).show();
					} else if (bean != null && bean.getStatus().equals("5001")) {
						Toast.makeText(Tzgg_detail_MainActivity.this,
								"获取通告失败，公告数据不存在!", Toast.LENGTH_SHORT).show();
					}
					if(fromWhere == 2){//如果是修改通知公告，不论正文会不会改动，都需要先把正文下载下来，以便调用修改接口
						path_xg = path + infoFile_.infoUsername().get()
								+ bean.getObj().getNoticeID() + ".doc";
						FileUtil.writeBase64StringToFile(bean.getObj().getContent(),
								path_xg);
						file_qc_path = path_xg;
						TypeID = bean.getObj().getTypeID();
					}
				}
				break;
			case 2:// 通知公告审核
				if (handleResult.getiSuccess().equals("success")) {
					Tzgg_detail_MainActivity.this.finish();
				}
				break;
			case 1000:// 获取通告类型和通告流程下拉数据
				if (handleResult.getiSuccess().equals("success")) {
					list_fenlei = handleResult.getBean_fenlei().getList();
					list_liucheng = handleResult.getBean_liucheng().getList();
					if (list_fenlei.size() > 0 && list_liucheng.size() > 0) {
						edt_tglx.setText((CharSequence) list_fenlei.get(0)
								.getValue());
						edt_tglc.setText((CharSequence) list_liucheng.get(0)
								.getValue());
						FlowID = (int) list_liucheng.get(0).getID();
						if(fromWhere == 0){
							TypeID = (int) list_fenlei.get(0).getID();
						}
					}
				}
				break;
			case 2000:// 添加和修改通知公告
				if (handleResult.getiSuccess().equals("success")) {
					Tzgg_detail_MainActivity.this.finish();
				}
				break;
			default:
				break;
			}
		}
	};

	protected void initViewAndListenersAgain() {
		edt_tgbt.setText(bean.getObj().getTitle() != null ? bean.getObj()
				.getTitle() : "-");
		edt_tglx.setText(String.valueOf(bean.getObj().getTypeName()) != null ? String
				.valueOf(bean.getObj().getTypeName()) : "-");
		tv_jzsj.setText(String.valueOf(bean.getObj().getEndDate()) != null ? String
				.valueOf(bean.getObj().getEndDate()) : "-");
		edt_tglx.setText(String.valueOf(bean.getObj().getTypeName()) != null ? String
				.valueOf(bean.getObj().getTypeName()) : "-");
		edt_ztc.setText(bean.getObj().getSubject() != null ? bean.getObj()
				.getSubject() : "-");
		edt_zdllry.setText(bean.getObj().getBrowseName() != null ? bean
				.getObj().getBrowseName() : "-");
		userName = bean.getObj().getBrowseName();
		userCode = bean.getObj().getBrowseCode();
		fjll.setOnClickListener(Tzgg_detail_MainActivity.this);
		zwll.setOnClickListener(Tzgg_detail_MainActivity.this);
		if (infoFile_.statusOfNotice().get() != null
				&& infoFile_.statusOfNotice().get().equals("1000")) {
			btn_tongguo.setOnClickListener(Tzgg_detail_MainActivity.this);
			btn_butongguo.setOnClickListener(Tzgg_detail_MainActivity.this);
		} else {
			btn_tongguo.setVisibility(View.INVISIBLE);
			btn_butongguo.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(iAppOffice != null){
			IAppOfficeUtil.exit(iAppOffice);
		}
	}
}
