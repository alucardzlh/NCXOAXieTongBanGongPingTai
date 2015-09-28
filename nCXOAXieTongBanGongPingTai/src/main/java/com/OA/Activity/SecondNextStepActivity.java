package com.OA.Activity;

import java.util.ArrayList;
import java.util.List;

import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.FirstNextStepDatas;
import com.OA.Entity.HandleResult;
import com.OA.Entity.NextFlowStepsBean;
import com.OA.Service.GetFlowFormElementsManager;
import com.OA.View.MyPopupWindow;

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

public class SecondNextStepActivity extends BaseActivity implements OnClickListener{

	ImageView iv_close;
	TextView tv_next;
	LinearLayout ll_next;
	List<NextFlowStepsBean> list_bean_steps;
	Button btn_sure;
	InfoFile_ infoFile;
	
	List<String> list_name = new ArrayList<String>();//步骤名称列表
	List<String> list_code = new ArrayList<String>();//步骤ID列表,即FlowStepID列表调用第一步接口参数
	int checkedFlowStepID = 0;		//选中的步骤的FlowStepID，用于
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondnextstep);
		initView();
		getFlowFormElements.GetNextFlowSteps(this, 1000, infoFile.FlowID().get(), infoFile.FlowStepID().get(), infoFile.infoUsername().get());
	}
	
	private void initView() {
		infoFile = new InfoFile_(this);
		iv_close = (ImageView) findViewById(R.id.iv_guanbi);
		tv_next = (TextView) findViewById(R.id.tv_next);
		ll_next = (LinearLayout) findViewById(R.id.ll_next);
		btn_sure = (Button) findViewById(R.id.btn_sure);
		iv_close.setOnClickListener(this);
		ll_next.setOnClickListener(this);
		btn_sure.setOnClickListener(this);
		tv_next.setText("请选择！");
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
			new MyPopupWindow(SecondNextStepActivity.this, list_name, ll_next,
					MyPopupWindow.BOTTOM) {
				@Override
				protected void doNext(int position) {
					tv_next.setText((CharSequence) list_name.get(position));
					checkedFlowStepID = Integer.valueOf(list_code.get(position)).intValue();
				}
			};
			}
			break;
		case R.id.btn_sure:
			if(checkedFlowStepID == 0){
				Toast.makeText(SecondNextStepActivity.this, "请选择步骤！", Toast.LENGTH_SHORT).show();
				return;
			}
			getFlowFormElements.GetCheckNextStepInfo(this, 2000, infoFile.FlowID().get(),
					infoFile.WFID().get(),checkedFlowStepID, "", Constants.passOrNot+"",1,
					infoFile.infoUsername().get());
//			if (datas != null && datas.getFlowAction().equals("BranchSel")) {
//				getFlowFormElements.GetNextFlowSteps(this, 2000, 0, Integer
//						.valueOf(datas.getNextStepID()).intValue(), "");
//			}else if(datas != null && !datas.getFlowAction().equals("BranchSel") ){
//				
//			}
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
//					datas = handleResult.getDatas();
//					if (!datas.getFlowAction().equals("BranchSel")) {
//
//					}
					list_bean_steps = handleResult.getList_bean_steps();
					for(int i=0;i<list_bean_steps.size();i++){
						list_name.add(list_bean_steps.get(i).getFlowDescript());
						list_code.add(list_bean_steps.get(i).getFlowStepID());
					}
				}
				break;

			case 2000:
				if (handleResult.getiSuccess() != null
						&& handleResult.getiSuccess().equals("success")) {
					FirstNextStepDatas datas = handleResult.getDatas();
					if (!datas.getFlowAction().equals("BranchSel")) {
						Intent intent = new Intent();
						intent.setClass(
								SecondNextStepActivity.this,
								FirstNextStepActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("stepName", datas.getFlowDescript());
						bundle.putString("checkCode", datas.getCheckCode());
						bundle.putString("checkName", datas.getCheckName());
						bundle.putString("NextStepID", datas.getNextStepID());
						intent.putExtras(bundle);
						startActivity(intent);
						SecondNextStepActivity.this.finish();
						// Detail_xzfwsh_chakantabs_MainActivity.this.finish();
					} else {
						list_name.clear();
						list_code.clear();
						tv_next.setText("请选择！");
						checkedFlowStepID = 0;	
						infoFile.edit().FlowStepID().put(Integer.valueOf(datas.getNextStepID()).intValue()).apply();
						getFlowFormElements.GetNextFlowSteps(SecondNextStepActivity.this, 1000, infoFile.FlowID().get(), infoFile.FlowStepID().get(), infoFile.infoUsername().get());
					}
				}
				break;

			default:
				break;
			}

		}
	};

}
