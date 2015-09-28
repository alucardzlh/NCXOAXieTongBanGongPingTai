package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.R;
import com.OA.Entity.Xzswdy_Detail_Bdxx_Bean;
import com.OA.Entity.Xzswyy_Detail_Bdxx_Bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Xzswyy_Bdxx_List_Adapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	List<Xzswyy_Detail_Bdxx_Bean> list;
	private Context context = null;
	private String elementType;

	public Xzswyy_Bdxx_List_Adapter(Context context, List<Xzswyy_Detail_Bdxx_Bean> list) {
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.list = list;
	}
	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.xzswdy_bdxx_item, null);
			viewHolder.CheckBoxList = (LinearLayout) convertView
					.findViewById(R.id.ll_checkbox);
			viewHolder.FileUpload = (LinearLayout) convertView
					.findViewById(R.id.ll_upload);
			viewHolder.TXTInput = (LinearLayout) convertView
					.findViewById(R.id.ll_textup);
			viewHolder.Select = (LinearLayout) convertView
					.findViewById(R.id.ll_slect);
			viewHolder.multipleInput = (LinearLayout) convertView
					.findViewById(R.id.ll_tvcontent);
			viewHolder.DateInput = (LinearLayout) convertView
					.findViewById(R.id.ll_date);
			viewHolder.tv_textview = (TextView) convertView
					.findViewById(R.id.tv_textView);
			viewHolder.tv_textviewmore = (TextView) convertView
					.findViewById(R.id.tv_textviewmore);
			viewHolder.tv_check = (TextView) convertView
					.findViewById(R.id.tv_check);
			viewHolder.tv_riqi = (TextView) convertView
					.findViewById(R.id.tv_riqi);
			viewHolder.tv_fujian = (TextView) convertView
					.findViewById(R.id.tv_fujian);
			viewHolder.tv_spinner = (TextView) convertView
					.findViewById(R.id.tv_spinner);
			viewHolder.btn_weizhi = (Button) convertView
					.findViewById(R.id.btn_weizhi);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			// viewHolder.reset();
		}
		elementType = list.get(position).getElementType();
		// 文本输入框
		if (elementType.equals("TxtInput") | elementType.equals("NumberInput")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.VISIBLE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.GONE);
			viewHolder.tv_textview.setText(list.get(position).getElementName());
		}
		// 多行文本输入框
		else if (elementType.equals("PeopleDefault")
				| elementType.equals("DateDefault")
				| elementType.equals("multipleInput")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.VISIBLE);
			viewHolder.DateInput.setVisibility(View.GONE);
			viewHolder.tv_textviewmore.setText(list.get(position)
					.getElementName());
		}
		// 多项选择框
		else if (elementType.equals("CheckBoxList")) {
			viewHolder.CheckBoxList.setVisibility(View.VISIBLE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.GONE);
			viewHolder.tv_check.setText(list.get(position).getElementName());
		}
		// 日期选择框
		else if (elementType.equals("DateInput")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.VISIBLE);
			viewHolder.tv_riqi.setText(list.get(position).getElementName());
		}
		// 文件上传
		else if (elementType.equals("MultipleFileUpload")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.VISIBLE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.GONE);
			viewHolder.tv_fujian.setText(list.get(position).getElementName());
		}
		// 下拉选择框
		else if (elementType.equals("DropDownList")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.VISIBLE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.GONE);
			viewHolder.tv_spinner.setText(list.get(position).getElementName());
			viewHolder.tv_spinner.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
				}

			});
		} else 
	
		{
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.GONE);
		}
		return convertView;
	}
	private CharSequence getElementName() {
		// TODO 自动生成的方法存根
		return null;
	}
	private String getElementType() {
		// TODO 自动生成的方法存根
		return null;
	}
	class ViewHolder {

		LinearLayout DateInput, TXTInput, CheckBoxList, multipleInput, Select,
				FileUpload;
		TextView tv_textview, tv_textviewmore, tv_check, tv_riqi, tv_spinner,
				tv_fujian;
		Button btn_weizhi;
	}
}
