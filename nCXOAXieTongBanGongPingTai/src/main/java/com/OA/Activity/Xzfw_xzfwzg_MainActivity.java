package com.OA.Activity;

import java.util.ArrayList;
import java.util.List;

import com.OA.Adapter.MyXzfw_xzfwzg_Adapter;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyXzfwzg_Bean;
import com.OA.Service.SendApplyListManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * 行政发文撰稿
 * 
 * @author Administrator
 * 
 */
public class Xzfw_xzfwzg_MainActivity extends BaseActivity implements
		OnClickListener{
	InfoFile_ infoFile_;
	private ListView lv_base;
	private MyXzfw_xzfwzg_Adapter adapter;
	private int m;
	List<MyXzfwzg_Bean> list = new ArrayList<MyXzfwzg_Bean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xzfw_xzfwzg_main);
		initViewAndListener();

//<<<<<<< .mine
//		getSend.GetSendApplyListManager(this, 1, infoFile_.infoUsername().get(), 
//				infoFile_.infoUserType().get(), 2000);
////		getSend.GetSendApplyListManager(this, 1, "admin",
////				"af8a15d2c3085fb3d818179b13a7eb29", 2000);
//=======
		getSend.GetSendApplyListManager(this, 1, infoFile_.infoUsername().get()!= null ? infoFile_.infoUsername().get():"aaa",
				infoFile_.infoUserType().get() != null ? infoFile_.infoUserType().get() :"aaa", Integer.valueOf(infoFile_.RoleID().get()!= null ? infoFile_.RoleID().get():"aaa").intValue(),2000);
	}

	private void initViewAndListener() {
		infoFile_ = new InfoFile_(this);
		lv_base = (ListView) findViewById(R.id.lv_base);
		back = (LinearLayout) findViewById(R.id.imageView3);
		back.setOnClickListener(this);
		lv_base.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int posit,
					long id) {
				Intent intent=new Intent();
				intent.setClass(Xzfw_xzfwzg_MainActivity.this, Detail_Bdxx_MainActivity.class);
				infoFile_.edit().FlowID().put(Integer.valueOf(list.get(posit).getFlowID()).intValue()).apply();
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}

	SendApplyListManager getSend = new SendApplyListManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess() != null
					&& handleResult.getiSuccess().equals("success")) {
				list = Constants.lis_xzfw_xzwfzg_bean;
				adapter = new MyXzfw_xzfwzg_Adapter(
						Xzfw_xzfwzg_MainActivity.this, list);
				lv_base.setAdapter(adapter);
			}

		}
	};
	private LinearLayout back;

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.imageView3:
			Xzfw_xzfwzg_MainActivity.this.finish();
			break;
		default:
			break;
		}
	}


}
