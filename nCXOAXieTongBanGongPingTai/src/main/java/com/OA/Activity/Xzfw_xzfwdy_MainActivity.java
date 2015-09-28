package com.OA.Activity;

import java.util.ArrayList;
import java.util.List;

import com.OA.Adapter.MyXzfw_Wdxzfw_Adapter;
import com.OA.Adapter.MyXzfw_Xzfwyy_Adapter;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.Meeting_hyssh_Bean;
import com.OA.Entity.MyXzfwyy_Bean;
import com.OA.Entity.Wdxzfw_Bean;
import com.OA.Service.MySendApplyListManager;
import com.OA.Service.SendBrowseListManager;
import com.OA.View.DatePickDialog;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 行政发文待阅
 * 
 * @author Administrator
 * 
 */
public class Xzfw_xzfwdy_MainActivity extends BaseActivity implements
		OnClickListener {
	InfoFile_ infoFile_;
	LinearLayout back;
	EditText edt_bt, edt_wh, edt_ngdw;
	
	
	TextView tv_1,tv_2;
	LinearLayout ll1,ll2;
	Button btn_search;
	ListView lv_base;
	List<MyXzfwyy_Bean> list = new ArrayList<MyXzfwyy_Bean>();
	
	ProgressBar pbMore;
	TextView tvMore;
	private int indexPager = 1;// 当前页数
	private boolean isMore = true;
	private int Count = 0;// 已加载的项数
	boolean haveAddFoot = false;
	boolean isSearchButtonClickedFirst = false;
	/**
	 * 搜索关键字
	 */
	String title;		
	String wenHao;
	String niGaoCompany;
	String sendStartTime;
	String sendEndTime;
	
	MyXzfw_Xzfwyy_Adapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xzfw_xzfwdy_main);
		initViewAndListener();
		endBrows.GetSendBrowseListManager(Xzfw_xzfwdy_MainActivity.this, 1,infoFile_.infoUsername().get(), infoFile_.infoUserType().get(), 
				10, indexPager, "100",title,wenHao, niGaoCompany, sendStartTime, sendEndTime);

	}
	
	private void initViewAndListener() {
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
						endBrows.GetSendBrowseListManager(Xzfw_xzfwdy_MainActivity.this, 1, infoFile_.infoUsername().get(),infoFile_.infoUserType().get(), 
									10, nextpage, "100",title,wenHao, niGaoCompany, sendStartTime, sendEndTime);
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
	
	private SendBrowseListManager endBrows = new SendBrowseListManager() {
		
		@Override
		protected void handlerLoginInfo(Context context, HandleResult handleResult,
				int paramInt) {
			loadfinish = true;
			if(handleResult.getiSuccess() != null && handleResult.getiSuccess().equals("success")){
				Count += Constants.COUNT_OF_LIST_MYXZFW_BEAN;
				if (lv_base.getAdapter() == null) {
					list = Constants.list_xzfwyy_bean;
					if(list.size() == 0){
						return;
					}
					adapter = new MyXzfw_Xzfwyy_Adapter(Xzfw_xzfwdy_MainActivity.this,list);
					addFoot();
					lv_base.setAdapter(adapter);
				}
				else{ 
					if(isSearchButtonClickedFirst){
						list = Constants.list_xzfwyy_bean;
							if(list.size()==0){
								return;
							}
							adapter = new MyXzfw_Xzfwyy_Adapter(Xzfw_xzfwdy_MainActivity.this,list);
							lv_base.setAdapter(adapter);
							isSearchButtonClickedFirst=false;
					}else{
						list.addAll(Constants.list_xzfwyy_bean);
						adapter.notifyDataSetChanged();
					}
				}
			}else if(handleResult.getiSuccess() != null && handleResult.getiSuccess().equals("success_0")){
				Count = 0;
				if(!haveAddFoot){
					addFoot();
				}
				list = new ArrayList<MyXzfwyy_Bean>();
				adapter = new MyXzfw_Xzfwyy_Adapter(Xzfw_xzfwdy_MainActivity.this,list);
				lv_base.setAdapter(adapter);
			}
			tishiInfo(Constants.COUNT_OF_LIST_MYXZFW_BEAN,Count);
		}
	};
	public void tishiInfo(int size1, int count1) {
		if (size1 < Constants.PAGE_SIZE_LARGE) {// 当后台加载的数据小于十条的时候
			isMore = false;// 表示没满
		} else {
			isMore = true;// 否则表示满了
		}
			
			tvMore.setText(size1< Constants.PAGE_SIZE_LARGE ? "共 " +count1+ " 条记录" : "更多记录...");
			pbMore.setVisibility(View.GONE);// 隐藏圆形进度条
	}
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.back:
			Xzfw_xzfwdy_MainActivity.this.finish();
			break;
		case R.id.btn_search:
			List<MyXzfwyy_Bean> list = new ArrayList<MyXzfwyy_Bean>();
			isSearchButtonClickedFirst=true;
			indexPager = 1;
			Count = 0;
			list.clear();
			title = edt_bt.getText().toString().trim();
			wenHao=edt_wh.getText().toString().trim();
			niGaoCompany=edt_ngdw.getText().toString().trim();
			sendStartTime = tv_1.getText().toString().trim();
			sendEndTime = tv_2.getText().toString().trim();
			endBrows.GetSendBrowseListManager(Xzfw_xzfwdy_MainActivity.this, 1,infoFile_.infoUsername().get(), infoFile_.infoUserType().get(), 
						10, indexPager, "100",title,wenHao, niGaoCompany, sendStartTime, sendEndTime);
			break;
		case R.id.ll1:
			DatePickDialog.showDateCheckDialog(this, tv_1, true);
			break;
		case R.id.ll2:
			DatePickDialog.showDateCheckDialog(this, tv_2, true);
		default:
			break;
		}
	}
}
