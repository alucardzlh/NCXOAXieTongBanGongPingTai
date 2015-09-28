package com.OA.Activity;

import java.util.ArrayList;
import java.util.List;

import com.OA.Adapter.MyMetting_Hyssq_Adapter;
import com.OA.Adapter.MyReceApply_Wdxzsw_Adapter;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.Meeting_hyssq_Bean;
import com.OA.Entity.RecApply_xzsw_bean;
import com.OA.Service.Meeting_hyssq_ListManager;
import com.OA.Service.MyRecApplyListManager;
import com.OA.View.DatePickDialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

public class Xzsw_Wdxzsw_MainActivity extends BaseActivity implements OnClickListener{
	InfoFile_ infoFile_;
	LinearLayout back;
	EditText edt_bt, edt_lwdw,edt_wzwh;
	TextView tv_1,tv_2;
	Button btn_search;
	ListView lv_base;
	LinearLayout ll1,ll2;
	List<RecApply_xzsw_bean> list = new ArrayList<RecApply_xzsw_bean>();
	
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
	String title = "";		
	String wenHao = "";
	String niGaoCompany = "";
	String sendStartTime = "";
	String sendEndTime = "";

	
	MyReceApply_Wdxzsw_Adapter adapter;
	private String TAG="not";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xzsw_myxzsw_main);
		initViewAndListener();
		myNotice.getGetMyRecApplyList(this, 1, infoFile_.infoUsername().get(), infoFile_.infoUserType().get(),10,  indexPager
				,"","","","","","");
	}
	

	
	
	private void initViewAndListener() {
		// TODO 自动生成的方法存根
		infoFile_ = new InfoFile_(this);
		btn_search = (Button) findViewById(R.id.btn_search);
		back = (LinearLayout) findViewById(R.id.back);
		edt_bt = (EditText) findViewById(R.id.edt_bt);
		//edt_wh= (EditText) findViewById(R.id.edt_wzwh);
		edt_lwdw = (EditText) findViewById(R.id.edt_lwdw);
		edt_wzwh = (EditText) findViewById(R.id.edt_wzwh);
		ll1 = (LinearLayout) findViewById(R.id.ll1);
		ll2 = (LinearLayout) findViewById(R.id.ll2);
		tv_1 = (TextView) findViewById(R.id.tv_1);
		tv_2 = (TextView) findViewById(R.id.tv_2);
		
		lv_base = (ListView) findViewById(R.id.lv_base);
		lv_base.setOnScrollListener(new ScrollListener());
		back .setOnClickListener(this);
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
							myNotice.getGetMyRecApplyList(Xzsw_Wdxzsw_MainActivity.this, 1,infoFile_.infoUsername().get(), infoFile_.infoUserType().get()
									, 10, indexPager,"",title,niGaoCompany, wenHao, sendStartTime, sendEndTime);
						}
				}
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}
	
private MyRecApplyListManager myNotice = new MyRecApplyListManager() {
		
		@Override
		protected void handlerLoginInfo(Context context, HandleResult handleResult,
				int paramInt) {
			loadfinish = true;
			if(handleResult.getiSuccess() != null && handleResult.getiSuccess().equals("success")){
				Count += Constants.COUNT_OF_LIST_RECAPPLY_XZSW_BEAN;
				if (lv_base.getAdapter() == null) {
					list = Constants.list_recApply_xzsw_bean;
					if(list.size() == 0){
						return;
					}
					adapter = new MyReceApply_Wdxzsw_Adapter(Xzsw_Wdxzsw_MainActivity.this,list);
					addFoot();
					lv_base.setAdapter(adapter);
				}
				else{
					if(isSearchButtonClickedFirst){
						list = Constants.list_recApply_xzsw_bean;
						if(list.size()==0){
							return;
						}
						adapter = new MyReceApply_Wdxzsw_Adapter(Xzsw_Wdxzsw_MainActivity.this,list);
						lv_base.setAdapter(adapter);
						isSearchButtonClickedFirst=false;
					}else{
						list.addAll(Constants.list_recApply_xzsw_bean);
						adapter.notifyDataSetChanged();
					}
				}
			}else if(handleResult.getiSuccess() != null && handleResult.getiSuccess().equals("success_0")){
				Count = 0;
				if(!haveAddFoot){
					addFoot();
				}
				list = new ArrayList<RecApply_xzsw_bean>();
				adapter = new MyReceApply_Wdxzsw_Adapter(Xzsw_Wdxzsw_MainActivity.this,list);
				lv_base.setAdapter(adapter);
			}
			tishiInfo(Constants.COUNT_OF_LIST_RECAPPLY_XZSW_BEAN,Count);
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
		case R.id.btn_search:
			isSearchButtonClickedFirst = true;
			indexPager = 1;
			Count = 0;
			list.clear();
				title = edt_bt.getText().toString().trim();
				wenHao = edt_wzwh.getText().toString().trim();
				niGaoCompany = edt_lwdw.getText().toString().trim();

				sendStartTime = tv_1.getText().toString().trim();
				sendEndTime = tv_2.getText().toString().trim();
				myNotice.getGetMyRecApplyList(Xzsw_Wdxzsw_MainActivity.this, 1,infoFile_.infoUsername().get(), infoFile_.infoUserType().get() 
//<<<<<<< .mine
						, 10, indexPager, "",title, wenHao, niGaoCompany, sendStartTime, sendEndTime);
//=======
//						, 10, indexPager, "",title,niGaoCompany, wenHao, sendStartTime, sendEndTime);
//>>>>>>> .r152
		
			break;
		case R.id.back:
		Xzsw_Wdxzsw_MainActivity.this .finish();
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
