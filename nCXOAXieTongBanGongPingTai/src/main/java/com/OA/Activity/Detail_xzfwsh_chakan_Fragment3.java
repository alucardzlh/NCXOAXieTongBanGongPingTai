package com.OA.Activity;


import java.util.ArrayList;
import java.util.List;

import com.OA.Adapter.Xzsw_Bdxx_ListTwo_Adapter;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.Xzsw_Detail_Bdxxtwo_Bean;
import com.OA.Service.GetSWBrowsePeopleListManager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class Detail_xzfwsh_chakan_Fragment3 extends Fragment{
	private View view;
	InfoFile_ infoFile_;
	private Xzsw_Bdxx_ListTwo_Adapter adapter;
	private List<Xzsw_Detail_Bdxxtwo_Bean> list;
	private ListView lv_base;
	int PageSize, PageNow;
	String Status;
	Context context;
	ProgressBar pbMore;
	TextView tvMore;
	private int indexPager = 1;// 当前页数
	private boolean isMore = true;
	private int lastItem;// 最后一项的下标
	private int Count = 0;// 已加载的项数
	boolean haveAddFoot = false;
	boolean isSearchModeList = false;
	private int WFID;
	boolean havaGotDatas = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			view = inflater.inflate(R.layout.xzfw_wdxzfw_fragment_3, null);
			infoFile_ = new InfoFile_(this.getActivity());
			initViewAndListener();
			WFID  = infoFile_.WFID().get();
			addFoot();
			if(!havaGotDatas){
				getBrowsePeopleList.getBrowsePeopleList(getActivity(), 1,10,indexPager,Integer.valueOf(WFID),Status);
			}
			else if(adapter != null){
				lv_base.setAdapter(adapter);
				tishiInfo(Constants.COUNT_OF_LIST_XZSW_DETAIL_BDXX_BEAN, Count);
			}
		return view;
	}
	private void initViewAndListener() {
		infoFile_ = new InfoFile_(getActivity());
		lv_base = (ListView) view.findViewById(R.id.lv_base);
		lv_base.setOnScrollListener(new ScrollListener());
		}
	
	private void addFoot() {
		haveAddFoot = true;
		LayoutInflater inflater = LayoutInflater.from(getActivity());
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
						getBrowsePeopleList.getBrowsePeopleList(getActivity(), 1,
								10, nextpage,Integer.valueOf(WFID),Status);
																
						}
				}
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}
	}
	
GetSWBrowsePeopleListManager getBrowsePeopleList=new GetSWBrowsePeopleListManager() {
		

		@Override
		protected void handlerLoginInfo(Context context, HandleResult handleResult,
				int paramInt) {
			loadfinish = true;
			havaGotDatas = true;
			if(handleResult.getiSuccess() != null && handleResult.getiSuccess().equals("success")){
				Count += Constants.COUNT_OF_LIST_XZSW_DETAIL_BDXX_BEAN;
				if (lv_base.getAdapter() == null) {
					list = Constants.listSWTWO_Detail_bdxx_bean;
					if(list.size() == 0){
						return;
					}
					adapter = new Xzsw_Bdxx_ListTwo_Adapter(getActivity(),list);
					lv_base.setAdapter(adapter);
				}
				else{
					list.addAll(Constants.listSWTWO_Detail_bdxx_bean);
					adapter.notifyDataSetChanged();
				}
			}else if(handleResult.getiSuccess() != null && handleResult.getiSuccess().equals("success_0")){
				Count = 0;
				if(!haveAddFoot || tvMore == null){
				//	addFoot();
				}
				list = new ArrayList<Xzsw_Detail_Bdxxtwo_Bean>();
				adapter = new Xzsw_Bdxx_ListTwo_Adapter(getActivity(), list);
				lv_base.setAdapter(adapter);
			}
			tishiInfo(Constants.COUNT_OF_LIST_XZSW_DETAIL_BDXX_BEAN, Count);
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
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			getActivity().finish();
		}
		return false;
	}

	static Detail_xzfwsh_chakan_Fragment3 newInstance(String s) {
		Detail_xzfwsh_chakan_Fragment3 newFragment = new Detail_xzfwsh_chakan_Fragment3();
		return newFragment;
	}

}
