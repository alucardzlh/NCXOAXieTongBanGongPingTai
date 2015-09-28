package com.OA.Activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.Detail_Bdxx_Bean;
import com.OA.Entity.FirstNextStepDatas;
import com.OA.Entity.HandleResult;
import com.OA.Entity.ISEnd_Bean;
import com.OA.Service.GetFlowFormElementsManager;
import com.OA.Util.StringUtil;
import com.OA.View.MyPopupWindow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FirstNextStepActivity extends BaseActivity implements
		OnClickListener {
	ImageView iv_close;
	TextView tv_next, tv_buzhou;
	LinearLayout ll_next;
	FirstNextStepDatas datas;
	CheckBox cb_send;
	Button btn_sure;
	InfoFile_ infoFile;
	String stepName="",checkName="",checkCode="";
	String checkedName = "",checkedCode = "",NextStepID = "";
	
	private List<Detail_Bdxx_Bean> list;
	
	int typeOfLast = 0;//1000行政发文审核，2000撰稿，3000行政收文审核，4000登记
	
	List<String> list_name = new ArrayList<String>();
	List<String> list_code = new ArrayList<String>();
	
	String json_bdxx = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstnextstep);
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		stepName = bundle.getString("stepName");
		checkName = bundle.getString("checkName");
		checkCode = bundle.getString("checkCode");
		NextStepID= bundle.getString("NextStepID");
		checkCode = checkCode.replace("|", ",");
		checkName = checkName.replace("|", ",");
		list_name = StringUtil.stringToNormalList(checkName);
		list_code = StringUtil.stringToNormalList(checkCode);
		initView();
	//	getFlowFormElements.GetApplyNextStepInfo(this, 1000, infoFile.FlowID().get(), infoFile.FlowStepID().get(), infoFile.infoUsername().get());
	}

	private void initView() {
		infoFile = new InfoFile_(this);
		typeOfLast = infoFile.TypeOfLast().get();
		iv_close = (ImageView) findViewById(R.id.iv_guanbi);
		tv_next = (TextView) findViewById(R.id.tv_next);
		tv_buzhou = (TextView) findViewById(R.id.tv_buzhou);
		ll_next = (LinearLayout) findViewById(R.id.ll_next);
		cb_send = (CheckBox) findViewById(R.id.cb_send);
		btn_sure = (Button) findViewById(R.id.btn_sure);
		iv_close.setOnClickListener(this);
		ll_next.setOnClickListener(this);
		btn_sure.setOnClickListener(this);
		tv_buzhou.setText(stepName);
		tv_next.setText("处理人员");
		list = Constants.list_Detail_bdxx_bean;
		Type listType = new TypeToken<ArrayList<Detail_Bdxx_Bean>>(){}.getType();  
        Gson gson = new Gson();  
		json_bdxx = gson.toJson(list, listType);
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
		case R.id.iv_guanbi:
			this.finish();
			break;
		case R.id.ll_next:
			if(list_name != null && list_name.size()>0){
				new MyPopupWindow(FirstNextStepActivity.this, list_name, ll_next,
						MyPopupWindow.BOTTOM) {
					@Override
					protected void doNext(int position) {
						tv_next.setText((CharSequence) list_name.get(position));
						checkedName = list_name.get(position);
						checkedCode = list_code.get(position);
					}
				};
			}
			break;
		case R.id.btn_sure:
			if(checkedName.equals("") && list_name != null && list_name.size()>0){
				Toast.makeText(FirstNextStepActivity.this, "请选择处理人员！", Toast.LENGTH_SHORT).show();
				return;
			}
			switch (typeOfLast) {
			case 1000://行政发文审核
			case 3000://行政收文审核
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("FlowID", infoFile.FlowID().get());//审核列表中获取到
				map.put("WFID", infoFile.WFID().get());//审核列表中获取到
				map.put("StepID", NextStepID);				//GetCheckNextStepInfo接口或者GetApplyNextStepInfo接口返回值获取到
				map.put("WFStepID", infoFile.WFStepID().get());		//审核列表中获取到
				map.put("ISPass", Constants.passOrNot);			
				map.put("PreStepNode", infoFile.CurNode().get());//审核列表中获取到
			//	map.put("Title", infoFile.Title().get());		//审核列表中获取到
				map.put("CheckUserCode", checkedCode);			//此activity处理人员下拉框选择
				map.put("BrowseCode", Constants.userCode);						//未知
				map.put("BrowseName", Constants.userName);						//未知
				map.put("ApplyUserCode", infoFile.infoUsername().get());	//当前登录人账号
				map.put("ApplyUserName", infoFile.infoUsername2().get());	//当前登录人名称
			//	map.put("EndTime", infoFile.endTime().get());				//审核列表中获取到
				map.put("ISSendMessage", cb_send.isChecked()?1:0);	//此activity选择
			//	map.put("ISFiliingDocument",1);//未知
				map.put("ArcStepID", infoFile.ArcStepID().get());//公文ID
				map.put("Memo", Constants.ShenHeYiJian);						//未知
				map.put("KindID", Constants.shouwenOrfawen);
				map.put("Json", json_bdxx);
				getFlowFormElements.SaveSendCheckStepData(this, 1000,  map);
				break;
			case 2000://行政发文撰稿
			case 4000://行政收文登记
				Map<String,Object> map1 = new HashMap<String, Object>();
				map1.put("FlowID", infoFile.FlowID().get());//审核列表中获取到
				map1.put("StepID", NextStepID);				//GetCheckNextStepInfo接口或者GetApplyNextStepInfo接口返回值获取到
				map1.put("Title", Constants.zg_biaoti);		//审核列表中获取到
				map1.put("CheckUserCode", checkedCode);			//此activity处理人员下拉框选择
				map1.put("BrowseCode", Constants.userCode);						//未知
				map1.put("BrowseName", Constants.userName);						//未知
				map1.put("ApplyUserCode", infoFile.infoUsername().get());	//当前登录人账号
				map1.put("ApplyUserName", infoFile.infoUsername2().get());	//当前登录人名称
				map1.put("EndTime", Constants.endTime);				//审核列表中获取到
				map1.put("ISSendMessage", Constants.IfSendMsg);	//初始选择
				map1.put("ISFiliingDocument",Constants.IfguiDang);//初始选择
				map1.put("ISNextSendMessage", cb_send.isChecked()?1:0);	//此activity选择
				map1.put("ArcStepID", infoFile.ArcStepID().get());//公文ID
				map1.put("KindID", Constants.shouwenOrfawen);
				map1.put("Json", json_bdxx);
				getFlowFormElements.SaveSendApplyStepData(this, 2000,  map1);
				break;
			default:
				break;
			}
			
			break;
		default:
			break;
		}

	}

	GetFlowFormElementsManager getFlowFormElements = new GetFlowFormElementsManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			switch (paramInt) {
			case 1000:
				if (handleResult.getiSuccess() != null
						&& handleResult.getiSuccess().equals("success")) {
					getFlowFormElements.ISEnd(FirstNextStepActivity.this,
							3000, NextStepID, Constants.passOrNot+"", infoFile.FlowID().get());

				}
				break;

			case 2000:
				if (handleResult.getiSuccess() != null
						&& handleResult.getiSuccess().equals("success")) {
					Intent intent = new Intent();
					intent.setAction("checkSuccess");
					FirstNextStepActivity.this.sendBroadcast(intent);
					FirstNextStepActivity.this.finish();
				}
				break;
			case 3000:
				if(handleResult.getiSuccess() != null
						&& handleResult.getiSuccess().equals("success")){
					ISEnd_Bean bean = handleResult.getBean_isEnd();
					if(bean.getISEnd().equals("1") && !bean.getJoinFLowID().equals("0")){
						infoFile.edit().FlowID().put(Integer.valueOf(bean.getJoinFLowID()).intValue()).apply();
						if(Constants.shouwenOrfawen == 2000){
							context.startActivity(new Intent(FirstNextStepActivity.this,Detail_Bdxx_MainActivity.class));
						}else if(Constants.shouwenOrfawen == 4000){
							context.startActivity(new Intent(FirstNextStepActivity.this,Xzsw_Detail_BDxx_MainActivity.class));
						}
						FirstNextStepActivity.this.finish();
					}else{
						Intent intent = new Intent();
						intent.setAction("checkSuccess");
						FirstNextStepActivity.this.sendBroadcast(intent);
						FirstNextStepActivity.this.finish();
					}
				}
				break;
			default:
				break;
			}

		}
	};
}
