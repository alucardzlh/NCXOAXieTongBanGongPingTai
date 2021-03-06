package com.OA.Activity;


import java.util.List;

import com.OA.Adapter.Bdxx_List_Adapter;
import com.OA.Adapter.Wdxzfw_Bdxx_List_Adapter;
import com.OA.Adapter.Xzfw_wdxzfw_Bdxx_List_Adapter;
import com.OA.Adapter.Xzsw_Bdxx_List_Adapter;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.Detail_Bdxx_Bean;
import com.OA.Entity.HandleResult;
import com.OA.Service.GetFlowFormElementsManager;
import com.OA.Interface.MyCallBack;
import com.OA.View.FormFillView;
import com.OA.View.FormFillViewOnlyForShow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Detail_Xzfwyy_chakan_Fragment1 extends Fragment{
	private View view;
	InfoFile_ infoFile_;
	private String FlowID,WFID;
	//private Xzfw_wdxzfw_Bdxx_List_Adapter adapter;
	private List<Detail_Bdxx_Bean> list;
	//private ListView lv_base;
	private LinearLayout ll_form;
	Context context;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			view = inflater.inflate(R.layout.xzfw_wdxzfw_fragment_1, null);
			initViewAndListener();
			context = this.getActivity();
			list = Constants.list_Detail_bdxx_bean;
			if (list.size() != 0) {
				FormFillViewOnlyForShow fillView = new FormFillViewOnlyForShow(context, list,
						callBack);
				ll_form.addView(fillView.getView());
			}
		return view;
	}
	
	
	
	private void initViewAndListener() {
		infoFile_ = new InfoFile_(getActivity());
		ll_form = (LinearLayout) view.findViewById(R.id.ll_form);
		
		}
	static Detail_Xzfwyy_chakan_Fragment1 newInstance(String s) {
		Detail_Xzfwyy_chakan_Fragment1 newFragment = new Detail_Xzfwyy_chakan_Fragment1();
		return newFragment;
	}
	MyCallBack callBack = new MyCallBack() {

		@Override
		public void callback(String id, String type, String value, int position) {
			list.get(position).setElementValue(value);
			Constants.list_Detail_bdxx_bean = list;
		}
	};
}
