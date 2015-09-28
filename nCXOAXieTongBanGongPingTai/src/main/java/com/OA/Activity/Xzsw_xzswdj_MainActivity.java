package com.OA.Activity;

import java.util.ArrayList;
import java.util.List;

import com.OA.Adapter.MyXzsw_xzswdj_Adapter;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyXzswdj_Bean;
import com.OA.Service.GetApplyListManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 行政收文登记
 * @author Administrator
 *
 */
public class Xzsw_xzswdj_MainActivity extends BaseActivity implements OnClickListener{

	InfoFile_ infoFile_;
	private ListView lv_base;
	private MyXzsw_xzswdj_Adapter adapter;
	private LinearLayout back;
	List<MyXzswdj_Bean> list = new ArrayList<MyXzswdj_Bean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xzsw_xzswdj_main);
		initViewAndListener();
		getSend.getApplyListManager(this, 1, infoFile_.infoUsername().get()!= null ? infoFile_.infoUsername().get():"aaa",
				infoFile_.infoUserType().get() != null ? infoFile_.infoUserType().get() :"aaa", Integer.valueOf(infoFile_.RoleID().get()!= null ? infoFile_.RoleID().get():"aaa").intValue(),4000);
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
				intent.setClass(Xzsw_xzswdj_MainActivity.this, Xzsw_Detail_BDxx_MainActivity.class);
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

	GetApplyListManager getSend = new GetApplyListManager() {

		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess() != null
					&& handleResult.getiSuccess().equals("success")) {
				list = Constants.lis_xzsw_xzswdj_bean;
				adapter = new MyXzsw_xzswdj_Adapter(
						Xzsw_xzswdj_MainActivity.this, list);
				lv_base.setAdapter(adapter);
			}

		}
	};
	@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.imageView3:
				Xzsw_xzswdj_MainActivity.this.finish();
				break;
			default:
				break;
			}
		}
}
