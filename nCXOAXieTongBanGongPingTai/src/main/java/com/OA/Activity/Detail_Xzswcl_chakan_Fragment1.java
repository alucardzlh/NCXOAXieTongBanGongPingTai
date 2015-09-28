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
import android.widget.Toast;

public class Detail_Xzswcl_chakan_Fragment1 extends Fragment{
	
	private View view;
	InfoFile_ infoFile_;
	private String FlowID, WFID;
	private LinearLayout ll_form;
//	private Xzfw_wdxzfw_Bdxx_List_Adapter adapter;
	private List<Detail_Bdxx_Bean> list;
	//private ListView lv_base;
	Context context;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			view = inflater.inflate(R.layout.xzsw_xzswcl_fragment_1, null);
			initViewAndListener();
		context = this.getActivity();
		//if (lv_base.getAdapter() == null) {
			list = Constants.list_Detail_bdxx_bean;
			if (list.size() != 0) {
				FormFillView fillView = new FormFillView(context, list,
						callBack);
				ll_form.addView(fillView.getView());
			//	adapter = new Xzfw_wdxzfw_Bdxx_List_Adapter(getActivity(), list);
			//	lv_base.setAdapter(adapter);
			}
		//}
		return view;
	}

	private void initViewAndListener() {
		infoFile_ = new InfoFile_(getActivity());
		ll_form = (LinearLayout) view.findViewById(R.id.ll_form);

	//	lv_base = (ListView) view.findViewById(R.id.lv_base);

	}

	
	static Detail_Xzswcl_chakan_Fragment1 newInstance(String s) {
		Detail_Xzswcl_chakan_Fragment1 newFragment = new Detail_Xzswcl_chakan_Fragment1();
		return newFragment;
	}

	MyCallBack callBack = new MyCallBack() {

		@Override
		public void callback(String id, String type, String value, int position) {
			if(list.get(position).getElementType().equals("MultipleFileUpload")
					||list.get(position).getElementType().equals("FileUpload")){
				if(list.get(position).getElementValue().equals("")){
					list.get(position).setElementValue(value);
				}else{
					String str = list.get(position).getElementValue();
					list.get(position).setElementValue(str+","+value);
				}
			}else{
				list.get(position).setElementValue(value);
			}
			
			Constants.list_Detail_bdxx_bean = list;
		}
	};
	
}
