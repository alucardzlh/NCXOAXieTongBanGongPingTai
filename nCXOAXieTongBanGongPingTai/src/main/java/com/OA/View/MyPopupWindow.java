package com.OA.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;






import com.OA.Activity.R;
import com.OA.Adapter.PopupWindowSingleAdapter;
import com.OA.Entity.DeptIDAndValue_Bean;
import com.OA.Entity.IDAndValue_Bean;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;

/**
 * 自定义popupwindow
 * 
 * @author x.y
 * @data 2013-5-24
 */
public class MyPopupWindow {
	private List<String> list;
	private List<Map<String, Object>> list_bdlr;
	private List<IDAndValue_Bean> list_id_value;
	private List<DeptIDAndValue_Bean> list_dept_id_value;
	private Context context;
	private View textView;
	private LayoutInflater inflater;
	private PopupWindow popupWindow;
	public final static int TOP = 0, BOTTOM = 1;
	private int direction;

	public MyPopupWindow(Context context, List<String> list, View textView,
			int direction) {
		this.list = list;
		this.context = context;
		this.textView = textView;
		this.direction = direction;
		inflater = LayoutInflater.from(context);
		showPopupWindow();
	}

	public MyPopupWindow(Context context, List<Map<String, Object>> list,
			View textView, int direction,int map) {
		this.list_bdlr = list;
		this.context = context;
		this.textView = textView;
		this.direction = direction;
		inflater = LayoutInflater.from(context);
		getListFromListMap(list_bdlr);

	}

	public MyPopupWindow(Context context, List<IDAndValue_Bean> list,
			View textView, int direction,String str) {
		this.list_id_value = list;
		this.context = context;
		this.textView = textView;
		this.direction = direction;
		inflater = LayoutInflater.from(context);
		getListFromListIDAndValue(list_id_value);

	}
	
	public MyPopupWindow(Context context, List<DeptIDAndValue_Bean> list,
			View textView, int direction,int a,int b) {
		this.list_dept_id_value = list;
		this.context = context;
		this.textView = textView;
		this.direction = direction;
		inflater = LayoutInflater.from(context);
		getListFromListDeptIDAndValue(list_dept_id_value);

	}
	
	private void getListFromListMap(List<Map<String, Object>> list_bdlr2) {
		List<String> list2 = new ArrayList<String>();
		for (Map<String, Object> map : list_bdlr2) {
			list2.add((String) map.get("Value"));
		}
		this.list = list2;
		showPopupWindow();
	}

	private void getListFromListIDAndValue(List<IDAndValue_Bean> list_bdlr2) {
		List<String> list2 = new ArrayList<String>();
		for (IDAndValue_Bean map : list_bdlr2) {
			list2.add((String) map.getValue());
		}
		this.list = list2;
		showPopupWindow();
	}
	
	
	private void getListFromListDeptIDAndValue(List<DeptIDAndValue_Bean> list_bdlr2) {
		List<String> list2 = new ArrayList<String>();
		for (DeptIDAndValue_Bean map : list_bdlr2) {
			list2.add((String) map.getDeptName());
		}
		this.list = list2;
		showPopupWindow();
	}
	
	private void showPopupWindow() {

		View view = inflater.inflate(R.layout.listview_for_popup_window, null);
		ListView lvPop = (ListView) view.findViewById(R.id.lv_popup_window);
		lvPop.setAdapter(new PopupWindowSingleAdapter(context, list));
		lvPop.setOnItemClickListener(itemClickListener);
		popupWindow = new PopupWindow(view, textView.getWidth(),
				LayoutParams.WRAP_CONTENT);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		// 点击popupWindow以外的地方或者返回键让其消失
		popupWindow.setBackgroundDrawable(context.getResources().getDrawable(
				R.color.transparent));
		if (direction == BOTTOM)
			popupWindow.showAsDropDown(textView);
		else if (direction == TOP) {
			int[] location = new int[2];
			textView.getLocationOnScreen(location);
			popupWindow.showAtLocation(textView, Gravity.NO_GRAVITY,
					location[0], location[1] - textView.getHeight());
		}
	}

	private OnItemClickListener itemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg0.getId()) {
			case R.id.lv_popup_window:
				popupWindow.dismiss();
				popupWindow = null;
				doNext(arg2);
				break;
			}
		}
	};

	// 如果需要对点击进行其他后续操作 调用此方法
	protected void doNext(int position) {
	}

}
