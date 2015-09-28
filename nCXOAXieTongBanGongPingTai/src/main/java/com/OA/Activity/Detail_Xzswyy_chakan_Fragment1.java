package com.OA.Activity;

import java.util.List;

import com.OA.Adapter.Xzsw_Bdxx_List_Adapter;
import com.OA.Adapter.Xzswcl_Bdxx_List_Adapter;
import com.OA.Adapter.Xzswyy_Bdxx_List_Adapter;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.Xzswcl_Detail_Bdxx_Bean;
import com.OA.Entity.Xzswyy_Detail_Bdxx_Bean;
import com.OA.Service.GetShouWenFormElementsManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Detail_Xzswyy_chakan_Fragment1 extends Fragment{
	private View view;
	InfoFile_ infoFile_;
	private String FlowID,WFID;
	private Xzswyy_Bdxx_List_Adapter adapter;
	private List<Xzswyy_Detail_Bdxx_Bean> list;
	private ListView lv_base;
	Context context;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			view = inflater.inflate(R.layout.xzsw_xzswdy_fragment_1, null);
			initViewAndListener();
			if (lv_base.getAdapter() == null) {
				list = Constants.list_Detail_yybdxx_bean;
				if(list.size() != 0){
					adapter = new Xzswyy_Bdxx_List_Adapter(getActivity(),list);
					lv_base.setAdapter(adapter);
				}
			}
		return view;
	}
	
	private void initViewAndListener() {
		infoFile_ = new InfoFile_(getActivity());
		lv_base = (ListView) view.findViewById(R.id.lv_base);
		
		}
	
	static Detail_Xzswyy_chakan_Fragment1 newInstance(String s){
		Detail_Xzswyy_chakan_Fragment1 newFragment = new Detail_Xzswyy_chakan_Fragment1();
		return newFragment;
		
	}
}
