package com.OA.Activity;

import java.util.List;

import org.apache.poi.ss.formula.functions.Count;
import org.ksoap2.serialization.SoapObject;

import com.OA.Adapter.Xzsw_Bdxx_Listone_Adapter;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.Xzsw_Detail_Bdxxfour_Bean;
import com.OA.Entity.Xzsw_Detail_Bdxxone_Bean;
import com.OA.Entity.Xzsw_Detail_Bdxxthree_Bean;
import com.OA.Entity.Xzsw_detai_Bdxxowo_Bean;
import com.OA.Service.GetBrowsePeopleListManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail_Xzswcl_chakan_Fragment2 extends Fragment {
	private View view;
	InfoFile_ infoFile_;
	private Xzsw_Bdxx_Listone_Adapter adapter;
	private ListView lv_base;
	private Xzsw_Detail_Bdxxone_Bean reult;
	private Xzsw_Detail_Bdxxthree_Bean reult1;
	private Xzsw_Detail_Bdxxfour_Bean reult2;
	Context context;
	private String WFID;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.xzsw_xzswcl_fragment_2, null);
		initViewAndListener();
		Intent intent = getActivity().getIntent();
		Bundle bundle = intent.getExtras();
		if(Constants.kllry == null || Constants.wllry == null ||Constants.dllry == null ){
			return view;
		}
		reult = Constants.kllry;
		if (reult.getWFID().equals("anyType{}")) {
			tv_kllry.setText("");
		} else {
			tv_kllry.setText(reult.getWFID());
		}
		reult1 = Constants.wllry;
		if ( reult1.getWFID().equals("anyType{}")) {
			tv_wllry.setText("");
		} else {
			tv_wllry.setText(reult1.getWFID());
		}
		reult2 = Constants.dllry;
		if (reult2.getWFID().equals("anyType{}")) {
			tv_dllry.setText("");
		} else {
			tv_dllry.setText(reult2.getWFID());
		}
		return view;
	}

	private TextView tv_kllry;
	private TextView tv_wllry;
	private TextView tv_dllry;

	private void initViewAndListener() {
		infoFile_ = new InfoFile_(getActivity());
		tv_kllry = (TextView) view.findViewById(R.id.tv_kllry);
		tv_wllry = (TextView) view.findViewById(R.id.tv_wllry);
		tv_dllry = (TextView) view.findViewById(R.id.tv_dllry);
		lv_base = (ListView) view.findViewById(R.id.lv_base);

	}

	GetBrowsePeopleListManager getBrowseUser = new GetBrowsePeopleListManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess() != null
					&& handleResult.getiSuccess().equals("success")) {

				reult = Constants.kllry;
				if (reult.getWFID().equals("anyType{}")) {
					tv_kllry.setText("");
				} else {
					tv_kllry.setText(reult.getWFID());
				}

			}
		}
	};
	GetBrowsePeopleListManager getNotBrowseUser = new GetBrowsePeopleListManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess() != null
					&& handleResult.getiSuccess().equals("success")) {
				// if (lv_base.getAdapter() == null) {
				// list = Constants.listSWONE_Detail_bdxx_bean;
				// if(list.size() == 0){
				// return;
				// }
				// adapter = new Xzsw_Bdxx_Listone_Adapter(getActivity(),list);
				// lv_base.setAdapter(adapter);
				// }
				
			}
		}
	};
	GetBrowsePeopleListManager getOverBrowseUser = new GetBrowsePeopleListManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess() != null
					&& handleResult.getiSuccess().equals("success")) {
				// if (lv_base.getAdapter() == null) {
				// list = Constants.listSWONE_Detail_bdxx_bean;
				// if(list.size() == 0){
				// return;
				// }
				// adapter = new Xzsw_Bdxx_Listone_Adapter(getActivity(),list);
				// lv_base.setAdapter(adapter);
				
				// }
			}
		}
	};

	static Detail_Xzswcl_chakan_Fragment2 newInstance(String s) {
		Detail_Xzswcl_chakan_Fragment2 newFragment = new Detail_Xzswcl_chakan_Fragment2();
		return newFragment;
	}

}
