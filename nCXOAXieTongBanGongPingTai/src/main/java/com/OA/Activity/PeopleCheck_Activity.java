package com.OA.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.DeptIDAndValue_Bean;
import com.OA.Entity.HandleResult;
import com.OA.Entity.IDAndValue_Bean;
import com.OA.Entity.LoginOA;
import com.OA.Service.PeopleCheckManager;
import com.OA.View.MyPopupWindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PeopleCheck_Activity extends BaseActivity implements
		OnClickListener, OnItemClickListener {
	InfoFile_ infoFile_;
	LinearLayout ll_bumen, ll_jiaose, ll_zhiwu, ll_gongzuozu,ll_left1,ll_left2,ll_right1,ll_right2;
	TextView tv_bumen, tv_jiaose, tv_zhiwu, tv_gongzuozu;
	EditText tv_xingming, tv_yonghuming;
	ListView lv_base1, lv_base2;
	Button btn_left1, btn_left2, btn_right1, btn_right2, btn_sousuo, btn_sure,
			btn_cancel;

	int bumenID, jiaoseID, zhiwuID, gongzuozuID;
	String yonghuming, xingming;

	List<LoginOA> list_left = new ArrayList<LoginOA>();

	List<LoginOA> list_right = new ArrayList<LoginOA>();

	MyAdapter left_adapter, right_adapter;

	int left_selected, right_selected;

	boolean if_left_selected = false, if_right_selected = false;

	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.peoplecheck_main);
		intent = getIntent();
		initViewAndListener();
		checkmanager.getListDatas(this, 1, infoFile_.infoUsername().get(),
				Integer.valueOf(infoFile_.DeptID().get()).intValue());
	}

	private void initViewAndListener() {
		infoFile_ = new InfoFile_(this);
		ll_bumen = (LinearLayout) findViewById(R.id.ll_bumen2);
		ll_jiaose = (LinearLayout) findViewById(R.id.ll_jiaose2);
		ll_zhiwu = (LinearLayout) findViewById(R.id.ll_zhiwu2);
		ll_gongzuozu = (LinearLayout) findViewById(R.id.ll_gongzuozu2);
		ll_left1= (LinearLayout) findViewById(R.id.ll_left1);
		ll_left2= (LinearLayout) findViewById(R.id.ll_left2);
		ll_right1= (LinearLayout) findViewById(R.id.ll_right1);
		ll_right2= (LinearLayout) findViewById(R.id.ll_right2);
		tv_bumen = (TextView) findViewById(R.id.tv_bumen);
		tv_jiaose = (TextView) findViewById(R.id.tv_jiaose);
		tv_zhiwu = (TextView) findViewById(R.id.tv_zhiwu);
		tv_gongzuozu = (TextView) findViewById(R.id.tv_gongzuozu);
		tv_yonghuming = (EditText) findViewById(R.id.edt_yonghuming);
		tv_xingming = (EditText) findViewById(R.id.edt_xingming);
		btn_sousuo = (Button) findViewById(R.id.btn_sousuo);
		btn_left1 = (Button) findViewById(R.id.btn_left1);
		btn_left2 = (Button) findViewById(R.id.btn_left2);
		btn_right1 = (Button) findViewById(R.id.btn_right1);
		btn_right2 = (Button) findViewById(R.id.btn_right2);
		btn_sure = (Button) findViewById(R.id.btn_sure);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		lv_base1 = (ListView) findViewById(R.id.lv_base1);
		lv_base2 = (ListView) findViewById(R.id.lv_base2);
		lv_base1.setOnItemClickListener(this);
		lv_base2.setOnItemClickListener(this);
		ll_bumen.setOnClickListener(this);
		ll_jiaose.setOnClickListener(this);
		ll_zhiwu.setOnClickListener(this);
		ll_gongzuozu.setOnClickListener(this);
		btn_sousuo.setOnClickListener(this);
		btn_left1.setOnClickListener(this);
		btn_left2.setOnClickListener(this);
		btn_right1.setOnClickListener(this);
		btn_right2.setOnClickListener(this);
		btn_sure.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
		ll_left1.setOnClickListener(this);
		ll_left2.setOnClickListener(this);
		ll_right1.setOnClickListener(this);
		ll_right2.setOnClickListener(this);
		initValueAndID();
	}

	private void initValueAndID() {
		tv_bumen.setText("所有部门");
		tv_jiaose.setText("所有角色");
		tv_zhiwu.setText("所有职务");
		tv_gongzuozu.setText("所有工作组");
		bumenID = 0;
		jiaoseID = 0;
		zhiwuID = 0;
		gongzuozuID = 0;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}

	private PeopleCheckManager checkmanager = new PeopleCheckManager() {

		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			switch (paramInt) {
			case 1:

				break;
			case 2:
				if (handleResult.getiSuccess().equals("success")) {
					list_left = Constants.list_left;
					left_adapter = new MyAdapter(PeopleCheck_Activity.this,
							list_left);
					lv_base1.setAdapter(left_adapter);
					if (right_adapter == null) {
						right_adapter = new MyAdapter(
								PeopleCheck_Activity.this, list_right);
						lv_base2.setAdapter(right_adapter);
					}
				}
				break;

			default:
				break;
			}
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_bumen2:
			ShowMyPopOfDept(Constants.list_bumen, ll_bumen, tv_bumen, 1);
			break;
		case R.id.ll_jiaose2:
			ShowMyPop(Constants.list_jiaose, ll_jiaose, tv_jiaose, 2);
			break;
		case R.id.ll_zhiwu2:
			ShowMyPop(Constants.list_zhiwu, ll_zhiwu, tv_zhiwu, 3);
			break;
		case R.id.ll_gongzuozu2:
			ShowMyPop(Constants.list_gongzuozu, ll_gongzuozu, tv_gongzuozu, 4);
			break;
		case R.id.btn_sousuo:
			if_left_selected = false;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("strUserCode", tv_yonghuming.getText().toString());
			map.put("strUserName", tv_xingming.getText().toString());
			map.put("strDeptID", bumenID + "");
			map.put("strRoleID", jiaoseID + "");
			map.put("strPostID", zhiwuID + "");
			map.put("strGroupID", gongzuozuID + "");
			map.put("strMyDeptID", infoFile_.DeptID().get());
			checkmanager.SelectDeptPerson(PeopleCheck_Activity.this, 2, map);
			break;
		case R.id.btn_left1:
		case R.id.ll_left1:
			if (if_left_selected) {
				for(int i=0;i<list_right.size();i++){
					if(list_right.get(i).getUserName().equals(list_left.get(left_selected).getUserName())){
						Toast.makeText(PeopleCheck_Activity.this, "该人员已添加！", Toast.LENGTH_SHORT).show();
						return;
					}
				}
				list_right.add(list_left.get(left_selected));
				if (right_adapter == null) {
					right_adapter = new MyAdapter(PeopleCheck_Activity.this,
							list_right);
					lv_base2.setAdapter(right_adapter);
				} else {
					right_adapter.notifyDataSetChanged();
					lv_base1.setSelected(false);
				}
			}
			break;
		case R.id.btn_left2:
		case R.id.ll_left2:
			list_right = new ArrayList<LoginOA>();
			for(int i=0;i<list_left.size();i++){
				list_right.add(list_left.get(i));
			}
			right_adapter = new MyAdapter(PeopleCheck_Activity.this, list_right);
			lv_base2.setAdapter(right_adapter);
			break;
		case R.id.btn_right2:
		case R.id.ll_right2:
			if (if_right_selected) {
				list_right.remove(right_selected);
				right_adapter = new MyAdapter(PeopleCheck_Activity.this,
						list_right);
				lv_base2.setAdapter(right_adapter);
				if_right_selected = false;
			}
			break;
		case R.id.btn_right1:
		case R.id.ll_right1:
			list_right = new ArrayList<LoginOA>();
			right_adapter = new MyAdapter(PeopleCheck_Activity.this, list_right);
			lv_base2.setAdapter(right_adapter);
			break;
		case R.id.btn_sure:
			Constants.list_right = list_right;
			intent.putExtra("select_name", true);
			PeopleCheck_Activity.this.setResult(Activity.RESULT_OK, intent);
			PeopleCheck_Activity.this.finish();
			break;
		case R.id.btn_cancel:
			PeopleCheck_Activity.this.finish();
			break;
		default:
			break;
		}
	}

	private void ShowMyPop(final List<IDAndValue_Bean> list, View ll,
			final TextView tv, final int ID) {
		new MyPopupWindow(PeopleCheck_Activity.this, list, ll,
				MyPopupWindow.BOTTOM, "") {
			@Override
			protected void doNext(int position) {
				tv.setText((CharSequence) list.get(position).getValue());
				switch (ID) {
				case 2:
					jiaoseID = list.get(position).getID();
					break;
				case 3:
					zhiwuID = list.get(position).getID();
					break;
				case 4:
					initValueAndID();
					tv.setText((CharSequence) list.get(position).getValue());
					gongzuozuID = list.get(position).getID();
					break;
				default:
					break;
				}
				// FlowID = Integer.valueOf((String)
				// list_liucheng.get(position).get("ID")).intValue();
			}
		};
	}

	private void ShowMyPopOfDept(final List<DeptIDAndValue_Bean> list, View ll,
			final TextView tv, final int ID) {
		new MyPopupWindow(PeopleCheck_Activity.this, list, ll,
				MyPopupWindow.BOTTOM, 1, 1) {
			@Override
			protected void doNext(int position) {
				tv.setText((CharSequence) list.get(position).getDeptName());
				switch (ID) {
				case 1:
					initValueAndID();
					tv.setText((CharSequence) list.get(position).getDeptName());
					bumenID = Integer.valueOf(list.get(position).getDeptID())
							.intValue();
					break;
				default:
					break;
				}
				// FlowID = Integer.valueOf((String)
				// list_liucheng.get(position).get("ID")).intValue();
			}
		};
	}

	public class MyAdapter extends BaseAdapter {
		List<LoginOA> list;
		LayoutInflater inflate;
		Context context;

		public MyAdapter(Context context, List<LoginOA> list) {
			this.list = list;
			this.context = context;
			this.inflate = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflate.inflate(R.layout.checkpeople_list_item,
						null);
				holder.tv = (TextView) convertView.findViewById(R.id.tv);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv.setText(list.get(position).getUserName());
			return convertView;
		}

		class ViewHolder {
			TextView tv;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (parent.getId()) {
		case R.id.lv_base1:
			if_left_selected = true;
			left_selected = position;
			break;
		case R.id.lv_base2:
			if_right_selected = true;
			right_selected = position;
			break;
		default:
			break;
		}
	}
}
