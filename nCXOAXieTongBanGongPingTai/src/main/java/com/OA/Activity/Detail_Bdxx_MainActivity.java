package com.OA.Activity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.OA.Activity.Detail_xzfwsh_chakantabs_MainActivity.receive;
import com.OA.Adapter.Bdxx_List_Adapter;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.Detail_Bdxx_Bean;
import com.OA.Entity.FirstNextStepDatas;
import com.OA.Entity.HandleResult;
import com.OA.Service.GetFlowFormElementsManager;
import com.OA.Util.FileUtil;
import com.OA.Util.IAppOfficeUtil;
import com.OA.Interface.MyCallBack;
import com.OA.Util.StringUtil;
import com.OA.View.DatePickDialog;
import com.OA.View.FormFillView;
import com.kinggrid.iappoffice.IAppOffice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail_Bdxx_MainActivity extends BaseActivity implements
		OnClickListener {

	InfoFile_ infoFile_;
	private String FlowID;
	private LinearLayout back;
	private Bdxx_List_Adapter adapter;
	private List<Detail_Bdxx_Bean> list;
	ListView lv_base;
	private LinearLayout bdfoot, ll_form,view_checkPeople;
	Button btn_fs, btn_qczw;
	String zw_path = "";// 正文保存路径
	IAppOffice iAppOffice = null;
	// StringBuffer str_json = new StringBuffer();//此json为了保存表单中的所有数据
	int Tildes = 0;// 页面上选择文种文号值
	int ArcStepID = 0;// 保存正文后接口返回的ID，再次修改和提交都需要此参数
	boolean hasQCZW = false;// 起草正文标志位
	String str_zw_content = "";// 正文转化成的Base64String
	FirstNextStepDatas datas;

	EditText edt_cxry;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xzfw_xzfwzg_bdbt);
		initViewAndListener();
		Intent intent = this.getIntent();
		FlowID = infoFile_.FlowID().get() + "";
		// str_json.append("[");
		// getFlowFormElements.getFlowFormElements(Detail_xzfwbd_MainActivity.this,
		// 1,"admin", "af8a15d2c3085fb3d818179b13a7eb29",46, 0);
		getFlowFormElements.getFlowFormElements(Detail_Bdxx_MainActivity.this,
				1, infoFile_.infoUsername().get(), infoFile_.infoUserType()
						.get(), Integer.valueOf(FlowID), 0,-1);
	}

	private void initViewAndListener() {
		infoFile_ = new InfoFile_(this);
		Constants.tildes = 0;
		zw_path = Environment.getExternalStorageDirectory() + "/OADownFile/"
				+ infoFile_.infoUsername().get() + "/";

		iAppOffice = IAppOfficeUtil.getInstance(this);
		ll_form = (LinearLayout) findViewById(R.id.ll_form);
		back = (LinearLayout) findViewById(R.id.imageView3);
		back.setOnClickListener(this);
		bdtou = (LinearLayout) LayoutInflater.from(this).inflate(
				R.layout.bdxx_item_bdtou, null);
		bdfoot = (LinearLayout) LayoutInflater.from(this).inflate(
				R.layout.bdxx_item_bdfoot, null);
		view_checkPeople = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.bdxx_item_checkpeople, null);
		ll_form.addView(bdtou);
		// lv_base = (ListView) findViewById(R.id.lv_base);
		// lv_base.addHeaderView(bdtou);
		// lv_base.addFooterView(bdfoot);
		tv_1 = (TextView) findViewById(R.id.tv_1);
		edt_tgbt = (TextView) findViewById(R.id.edt_tgbt);
		edt_tgbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 final EditText inputServer = new EditText(Detail_Bdxx_MainActivity.this);
				 inputServer.setText(edt_tgbt.getText().toString());
			        AlertDialog.Builder builder = new AlertDialog.Builder(Detail_Bdxx_MainActivity.this);
			        builder.setTitle("公文标题").setIcon(null).setView(inputServer)
			                .setNegativeButton("取消", null);
			        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			            public void onClick(DialogInterface dialog, int which) {
							edt_tgbt.setText(inputServer.getText().toString());
			             }
			        });
			        builder.show();				
			}
		});
		cb_sfgd = (CheckBox) findViewById(R.id.cb_sfgd);
		cb_send = (CheckBox) findViewById(R.id.cb_send);
		lll = (LinearLayout) findViewById(R.id.ll1);
		// btn_fs = (Button) findViewById(R.id.btn_fs);
		// btn_qczw = (Button) findViewById(R.id.btn_qczw);
		// btn_fs.setOnClickListener(this);
		// btn_qczw.setOnClickListener(this);
		lll.setOnClickListener(this);
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/OADownFile/");
		if (!file.exists()) {
			file.mkdir();
		}
		File file1 = new File(zw_path);
		if (!file1.exists()) {
			file1.mkdir();
		}
		initFilePath();
		if (iAppOffice == null) {
			// btn_qczw.setClickable(false);
			Toast.makeText(this, "请正确安装wps应用！", Toast.LENGTH_SHORT).show();
		} else {
			// btn_qczw.setClickable(true);
		}
		infoFile_.edit().ArcStepID().put(0).apply();// 初始化公文ID
		Constants.shouwenOrfawen = 2000;
	}

	GetFlowFormElementsManager getFlowFormElements = new GetFlowFormElementsManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess() != null
					&& handleResult.getiSuccess().equals("success")) {
				switch (paramInt) {
				case 1:// 获取表单接口
					list = Constants.list_Detail_bdxx_bean;
					if (list.size() == 0) {
						return;
					}
					FormFillView fillView = new FormFillView(context, list,
							callBack);
					ll_form.addView(fillView.getView());
					if(handleResult.isSelPeople()){
						ll_form.addView(view_checkPeople);
						LinearLayout ll_checkpeople = (LinearLayout) findViewById(R.id.ll_checkpeople);
						edt_cxry = (EditText) findViewById(R.id.edt_xzry);
						ll_checkpeople.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								Detail_Bdxx_MainActivity.this.startActivityForResult(new Intent(Detail_Bdxx_MainActivity.this,
										PeopleCheck_Activity.class), 3000);
							}
						});
					}
					
					ll_form.addView(bdfoot);
					btn_fs = (Button) findViewById(R.id.btn_fs);
					btn_qczw = (Button) findViewById(R.id.btn_qczw);
					btn_fs.setOnClickListener(Detail_Bdxx_MainActivity.this);
					btn_qczw.setOnClickListener(Detail_Bdxx_MainActivity.this);
					break;
				case 2:// 加载正文范文
					FileUtil.writeBase64StringToFile(handleResult.getContent(),
							zw_path);
					hasQCZW = true;
					if (iAppOffice != null) {
						IAppOfficeUtil.OpenOfficeFile(iAppOffice, zw_path);
					}
					break;
				case 3:// 保存正文成功，开始提交步骤第一步
					ArcStepID = handleResult.getArcStepID();
					infoFile_.edit().ArcStepID().put(ArcStepID).apply();
					getFlowFormElements.GetApplyNextStepInfo(
							Detail_Bdxx_MainActivity.this, 4,
							Integer.valueOf(FlowID).intValue(), -1, infoFile_
									.infoUsername().get());
					break;
				case 4:// 开始提交步骤第二步
					datas = handleResult.getDatas();
					infoFile_.edit().TypeOfLast().put(2000).apply();
					if (!datas.getFlowAction().equals("BranchSel")) {
						Intent intent = new Intent();
						intent.setClass(Detail_Bdxx_MainActivity.this,
								FirstNextStepActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("stepName", datas.getFlowDescript());
						bundle.putString("checkCode", datas.getCheckCode());
						bundle.putString("checkName", datas.getCheckName());
						bundle.putString("NextStepID", datas.getNextStepID());
						intent.putExtras(bundle);
						startActivity(intent);
						// Detail_xzfwsh_chakantabs_MainActivity.this.finish();
					} else {
						Intent intent = new Intent();
						intent.setClass(Detail_Bdxx_MainActivity.this,
								SecondNextStepActivity.class);
						infoFile_
								.edit()
								.FlowStepID()
								.put(Integer.valueOf(datas.getNextStepID())
										.intValue()).apply();
						startActivity(intent);
						// Detail_xzfwsh_chakantabs_MainActivity.this.finish();
					}
					break;
				default:
					break;
				}
			}
		}
	};
	private LinearLayout bdtou;
	private LinearLayout lll;
	private TextView tv_1;
	private TextView edt_tgbt;
	CheckBox cb_sfgd,cb_send;

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
			Detail_Bdxx_MainActivity.this.finish();
			break;
		case R.id.ll1:
			DatePickDialog.showDateCheckDialog(this, tv_1, true);
			break;
		case R.id.btn_fs:
			if (!hasQCZW) {
				Toast.makeText(Detail_Bdxx_MainActivity.this, "请先起草正文！",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if(edt_tgbt.getText().toString().trim().equals("")){
				Toast.makeText(Detail_Bdxx_MainActivity.this, "请填写公文标题！",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if(tv_1.getText().toString().trim().equals("")){
				Toast.makeText(Detail_Bdxx_MainActivity.this, "请填写截止日期！",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if(cb_send.isChecked()){
				Constants.IfSendMsg = 1;
			}else{
				Constants.IfSendMsg = 0;
			}
			if(cb_sfgd.isChecked()){
				Constants.IfguiDang = 1;
			}else{
				Constants.IfguiDang = 0;
			}
			Constants.endTime = tv_1.getText().toString();
			Constants.zg_biaoti = edt_tgbt.getText().toString();
			Constants.userName = cxry_username.toString();
			Constants.userCode = cxry_usercode.toString();
			for(int i=0;i<list.size();i++){
				if(list.get(i).getElementBlank().equals("0") && list.get(i).getElementValue().equals("")){
					Toast.makeText(Detail_Bdxx_MainActivity.this, list.get(i).getElementName()+"不能为空！！",
						Toast.LENGTH_SHORT).show();
					return;
				}
			}
			/*
			 * 此处暂时缺少判断条件，哪些不能为空
			 */
			File file = new File(zw_path);
			byte[] bytes = null;
			try {
				bytes = FileUtil.getBytesFromFile(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			str_zw_content = Base64.encodeToString(bytes, Base64.DEFAULT);
			getFlowFormElements.SaveArchiveData(Detail_Bdxx_MainActivity.this,
					3, 0, 0, 0, infoFile_.infoUsername().get(), infoFile_
							.infoUserType().get(), str_zw_content);
			break;
		case R.id.btn_qczw:
			if (hasQCZW) {
				IAppOfficeUtil.OpenOfficeFile(iAppOffice, zw_path);
			} else if (list.size() != 0) {
				/*List<String> list_value = null;
				List<String> list_name = null;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getElementName().equals("机关代字号")) {
						list_value = StringUtil.stringToNormalList(list.get(i)
								.getOptionValue());
						list_name = StringUtil.stringToNormalList(list.get(i)
								.getOptionsText());
						if(list_name != null && list_value != null){
							for (int j = 0; j < list_name.size(); j++) {
								if (list.get(i).getElementValue()
										.equals(list_name.get(j))) {
									Tildes = Integer.valueOf(list_value.get(j))
											.intValue();
								}
							}
								}
						
					}
				}*/
				getFlowFormElements.LoadArchiveData(
						Detail_Bdxx_MainActivity.this, 2, Constants.tildes, ArcStepID, 0,
						0);
			}
			break;
		default:
			break;
		}

	}

	@Override
	protected void onDestroy() {
		IAppOfficeUtil.exit(iAppOffice);
		super.onDestroy();
	}

	private void initFilePath() {

		File file = new File(zw_path);
		if (!file.exists()) {
			file.mkdir();
		}
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日HH时mm分ss秒");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		zw_path = zw_path + infoFile_.infoUsername().get() + str + ".doc";

	}

	MyCallBack callBack = new MyCallBack() {

		@Override
		public void callback(String id, String type, String value, int position) {
			if(list.get(position).getElementType().equals("MultipleFileUpload")
					||list.get(position).getElementType().equals("FileUpload")){
				if(list.get(position).getElementValue().equals("")){
					list.get(position).setElementValue(value);
				}else{
					String str = list.get(position).getElementValue();
					list.get(position).setElementValue(str+","+value);
				}
			}else{
				list.get(position).setElementValue(value);
			}
			
			Constants.list_Detail_bdxx_bean = list;
		}
	};
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
