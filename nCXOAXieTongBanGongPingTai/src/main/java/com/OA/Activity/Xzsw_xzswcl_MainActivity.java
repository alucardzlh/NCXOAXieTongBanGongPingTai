package com.OA.Activity;

import java.util.ArrayList;
import java.util.List;

import com.OA.Adapter.RecApply_Xzswcl_Adapter;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.RecApply_xzswcl_Bean;
import com.OA.Service.RecApplylistCLManager;
import com.OA.View.DatePickDialog;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class Xzsw_xzswcl_MainActivity extends BaseActivity implements OnClickListener{
	

	InfoFile_ infoFile_;
	LinearLayout back;
	EditText edt_bt,edt_wh,edt_ngdw;
	TextView tv_1,tv_2;
	Button btn_search;
	LinearLayout ll1,ll2;
	ListView lv_base;
	List<RecApply_xzswcl_Bean> list = new ArrayList<RecApply_xzswcl_Bean>();
	
	ProgressBar pbMore;
	TextView tvMore;
	private int indexPager = 1;// 当前页数
	private boolean isMore = true;
	private int lastItem;// 最后一项的下标
	private int Count = 0;// 已加载的项数
	boolean haveAddFoot = false;
	boolean isSearchModeList = false;
	//搜索关键词
	String title;
	String wenHao;
	String laiWenCompany;
	String sendStartTime;
	String sendEndTime;
	
	RecApply_Xzswcl_Adapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xzsw_xzswcl_main);
		initViewAndListener();
		myMeeting.getRecCheckList(Xzsw_xzswcl_MainActivity.this, 1,infoFile_.infoUsername().get(), 
				infoFile_.infoUserType().get(), 10, indexPager,"", "","","","");
	}
	
	private void initViewAndListener() {
		// TODO 自动生成的方法存根
		infoFile_ = new InfoFile_(this);
		back = (LinearLayout) findViewById(R.id.back);
		edt_bt = (EditText) findViewById(R.id.edt_bt);
		edt_wh = (EditText) findViewById(R.id.edt_wh);
		edt_ngdw = (EditText) findViewById(R.id.edt_ngdw);
		tv_1 = (TextView) findViewById(R.id.tv_1);
		tv_2 = (TextView) findViewById(R.id.tv_2);
		ll1 = (LinearLayout) findViewById(R.id.ll1);
		ll2 = (LinearLayout) findViewById(R.id.ll2);
		btn_search = (Button) findViewById(R.id.btn_search);
		lv_base = (ListView) findViewById(R.id.lv_base);
		lv_base.setOnScrollListener(new ScrollListener());
		back.setOnClickListener(this);
		btn_search.setOnClickListener(this);
		ll1.setOnClickListener(this);
		ll2.setOnClickListener(this);
	}
	private void addFoot() {
		haveAddFoot = true;
		LayoutInflater inflater = LayoutInflater.from(this);
		LinearLayout llMore = (LinearLayout) inflater.inflate(
				R.layout.more_data_bar, null);
		pbMore = (ProgressBar) llMore.findViewById(R.id.pbMore);
		tvMore = (TextView) llMore.findViewById(R.id.tvMore);
		lv_base.addFooterView(llMore);
	}
private boolean loadfinish = true; // 指示数据是否加载完成
	
	private final class ScrollListener implements OnScrollListener {
		private int number = 10; // 每次获取多少条数据

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
		
			final int loadtotal = totalItemCount - 1;
			int lastItemid = lv_base.getLastVisiblePosition(); // 获取当前屏幕最后Item的ID
			if ((lastItemid + 1) == loadtotal) { // 达到数据的最后一条记录

				if (loadtotal > 0) {
					// 当前页
					int currentpage = loadtotal % number == 0 ? loadtotal
							/ number
							: loadtotal / number + 1;
					int nextpage = currentpage + 1; // 下一页
					if (loadfinish && isMore) {
						loadfinish = false;
					//	if(isSearchModeList){
							myMeeting.getRecCheckList(Xzsw_xzswcl_MainActivity.this, 1,infoFile_.infoUsername().get(), 
									infoFile_.infoUserType().get(), 10, nextpage,title , wenHao,laiWenCompany,sendStartTime,sendEndTime);
																
					//	}
					//	else{
					//		myMeeting.getRecBrowseList(Xzsw_xzswyy_MainActivity.this, 1,infoFile_.infoUsername().get(), 
					//				infoFile_.infoUserType().get(), 10, nextpage, "", "", "","","","");
					//	}					
						}
				}
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}
	
	private RecApplylistCLManager myMeeting = new RecApplylistCLManager() {
		
		@Override
		protected void handlerLoginInfo(Context context, HandleResult handleResult,
				int paramInt) {
			loadfinish = true;
			if(handleResult.getiSuccess() != null && handleResult.getiSuccess().equals("success")){
				Count += Constants.COUNT_OF_LIST_MyRECAPPLY_XZSWCL_BEAN;
				if (lv_base.getAdapter() == null) {
					list = Constants.list_recApply_xzswcl_bean;
					if(list.size() == 0){
						return;
					}
					adapter = new RecApply_Xzswcl_Adapter(Xzsw_xzswcl_MainActivity.this,list);
					addFoot();
					lv_base.setAdapter(adapter);
				}
				else if(isSearchModeList){
					list = Constants.list_recApply_xzswcl_bean;
					if(list.size() == 0){
						return;
					}
					adapter = new RecApply_Xzswcl_Adapter(Xzsw_xzswcl_MainActivity.this,list);
					lv_base.setAdapter(adapter);
					isSearchModeList = false;
				}
			else{
					list.addAll(Constants.list_recApply_xzswcl_bean);
					adapter.notifyDataSetChanged();
				}
				
			}else if(handleResult.getiSuccess() != null && handleResult.getiSuccess().equals("success_0")){
				Count = 0;
				if(!haveAddFoot){
					addFoot();
				}
				list = new ArrayList<RecApply_xzswcl_Bean>();
				adapter = new RecApply_Xzswcl_Adapter(Xzsw_xzswcl_MainActivity.this,list);
				lv_base.setAdapter(adapter);
			}
			tishiInfo(Constants.COUNT_OF_LIST_MyRECAPPLY_XZSWCL_BEAN,Count);
		}
	};
	
	public void tishiInfo(int size1, int count1) {
		if (size1 < Constants.PAGE_SIZE_LARGE) {// 当后台加载的数据小于十条的时候
			isMore = false;// 表示没满
		} else {
			isMore = true;// 否则表示满了
		}
		tvMore.setText(size1 < Constants.PAGE_SIZE_LARGE ? "共 " + count1
				+ " 条记录" : "更多记录...");
		pbMore.setVisibility(View.GONE);// 隐藏圆形进度条
	}
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.back:
			Xzsw_xzswcl_MainActivity.this.finish();
			break;
		case R.id.btn_search:
			indexPager = 1;
			Count = 0;
			list.clear();
			isSearchModeList = true;
			title = edt_bt.getText().toString().trim();
			wenHao = edt_wh.getText().toString().trim();
			laiWenCompany = edt_ngdw.getText().toString().trim();
			sendStartTime = tv_1.getText().toString().trim();
			sendEndTime = tv_2.getText().toString().trim();
			myMeeting.getRecCheckList(Xzsw_xzswcl_MainActivity.this, 1,infoFile_.infoUsername().get(), 
					infoFile_.infoUserType().get(), 10, indexPager,title , wenHao,laiWenCompany,sendStartTime,sendEndTime);
			break;
		case R.id.ll1:
			DatePickDialog.showDateCheckDialog(this, tv_1, true);
			break;
		case R.id.ll2:
			DatePickDialog.showDateCheckDialog(this, tv_2, true);
			break;
		default:
			break;
		}
	}

}
