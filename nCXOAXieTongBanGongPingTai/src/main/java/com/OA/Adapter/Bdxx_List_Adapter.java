package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.R;
import com.OA.Activity.Tzgg_detail_MainActivity;
import com.OA.Activity.UpLoadFile_Activity;
import com.OA.Entity.Detail_Bdxx_Bean;
import com.OA.View.DatePickDialog;
import com.OA.View.MarqueeTextView;
import com.OA.View.MyPopupWindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Bdxx_List_Adapter extends BaseAdapter {
	private LayoutInflater inflater;
	List<Detail_Bdxx_Bean> list;
	private Context context = null;
	private String elementType;
	String files = "";

	// private List<String> list_data;
	// private String s;
	// private String[] ds;
	// private MarqueeTextView tv_custom;

	public Bdxx_List_Adapter(Context context, List<Detail_Bdxx_Bean> list) {
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.bdxx_item, null);
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
			viewHolder.tv_custom = (TextView) convertView
					.findViewById(R.id.tv_custom);
			viewHolder.tv_1 = (TextView) convertView.findViewById(R.id.tv_1);
			viewHolder.btn_weizhi = (Button) convertView
					.findViewById(R.id.btn_weizhi);
			viewHolder.cb_send = (CheckBox) convertView
					.findViewById(R.id.cb_send);
			viewHolder.bccl = (ImageButton) convertView.findViewById(R.id.bccl);
			viewHolder.edt_tgbt = (EditText) convertView
					.findViewById(R.id.edt_tgbt);
			viewHolder.edt_hynr = (EditText) convertView
					.findViewById(R.id.edt_hynr);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			// viewHolder.reset();
		}
		elementType = list.get(position).getElementType();
		// 文本输入框
		if (elementType.equals("TxtInput") || elementType.equals("NumberInput")
				|| elementType.equals("RichInput")
				|| elementType.equals("MoneyInput")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.VISIBLE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.GONE);
			if (!list.get(position).getElementLabelName().equals("")) {
				viewHolder.tv_textview.setText(list.get(position)
						.getElementLabelName());
			} else
				viewHolder.tv_textview.setText(list.get(position)
						.getElementName());
			final EditText edt_1 = viewHolder.edt_tgbt;
			/*
			 * viewHolder.edt_tgbt.setOnFocusChangeListener(new
			 * OnFocusChangeListener() {
			 * 
			 * @Override public void onFocusChange(View v, boolean hasFocus) {
			 * if(hasFocus){ Toast.makeText(context, edt_1.getText().toString(),
			 * Toast.LENGTH_SHORT).show(); }else{ Toast.makeText(context,
			 * "tgbt", Toast.LENGTH_SHORT).show(); }
			 * 
			 * } });
			 */
			viewHolder.edt_tgbt.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				@Override
				public void afterTextChanged(Editable s) {
					Toast.makeText(context, edt_1.getText().toString(),
							Toast.LENGTH_SHORT).show();
					list.get(position).setElementValue(edt_1.getText().toString());
				}
			});
		}
		// 多行文本输入框
		else if (elementType.equals("multipleInput")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.VISIBLE);
			viewHolder.DateInput.setVisibility(View.GONE);
			if (!list.get(position).getElementLabelName().equals("")) {
				viewHolder.tv_textviewmore.setText(list.get(position)
						.getElementLabelName());
			} else
				viewHolder.tv_textviewmore.setText(list.get(position)
						.getElementName());
			final EditText edt_2 = viewHolder.edt_hynr;
			viewHolder.edt_hynr.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				@Override
				public void afterTextChanged(Editable s) {
					Toast.makeText(context, edt_2.getText().toString(),
							Toast.LENGTH_SHORT).show();
				}
			});
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
			final TextView tv = viewHolder.tv_1;
			viewHolder.DateInput.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(context,"viewHolder.DateInput clicked", Toast.LENGTH_SHORT).show();
					DatePickDialog.showDateCheckDialog((Activity) context, tv,
							false);
				}
			});
		} else if (elementType.equals("DateTimeInput")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.VISIBLE);
			viewHolder.tv_riqi.setText(list.get(position).getElementName());
			final TextView tv = viewHolder.tv_1;
			viewHolder.tv_riqi.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(context,"viewHolder.tv_riqi clicked", Toast.LENGTH_SHORT).show();
					DatePickDialog.showDateCheckDialog((Activity) context, tv,
							true);
				}
			});
		} else if (elementType.equals("DateDefault")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.VISIBLE);
			viewHolder.tv_riqi.setText(list.get(position).getElementName());
			final TextView tv = viewHolder.tv_1;
			viewHolder.tv_riqi.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(context,"viewHolder.tv_riqi clicked", Toast.LENGTH_SHORT).show();
					DatePickDialog.showDateCheckDialog((Activity) context, tv,
							false);
				}
			});
		}
		// 文件上传

		else if (elementType.equals("MultipleFileUpload")
				|| elementType.equals("FileUpload")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.VISIBLE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.GONE);
			viewHolder.tv_fujian.setText(list.get(position).getElementName());
			viewHolder.tv_fujian.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(context,"viewHolder.tv_fujian clicked", Toast.LENGTH_SHORT).show();
					context.startActivity(new Intent(context,
							UpLoadFile_Activity.class).addFlags(1000));
				}
			});

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
			// if (list_data != null) {
			// s = list.get(position).getOptionValue();
			// ds = s.split(",");
			// list_data = new ArrayList<String>();
			//
			// for (int i = 0; i < ds.length; i++) {
			// list_data.add(ds[i]);
			// }
			// if (list_data.size() > 0) {
			//
			// new MyPopupWindow(context, list_liucheng,
			// ll_tzgg_tglc, MyPopupWindow.BOTTOM, "") {
			// @Override
			// protected void doNext(int position) {
			// edt_tglc.setText((CharSequence) list_liucheng.get(
			// position).getValue());
			// FlowID = (int) list_liucheng.get(position).getID();
			// }
			// };
			// }
			viewHolder.Select.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// new MyPopupWindow(context, list_data, Select,
					// MyPopupWindow.BOTTOM){
					// @Override
					// protected void doNext(int position) {
					// tv_custom.setText(list_data.get(position));
					// }
					// };
					// }
					Toast.makeText(context,"viewHolder.Select clicked", Toast.LENGTH_SHORT).show();
				}

			});
		} else{

			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.GONE);
		}
		return convertView;
	}

	class ViewHolder {

		LinearLayout DateInput, TXTInput, CheckBoxList, multipleInput, Select,
				FileUpload;
		TextView tv_textview, tv_textviewmore, tv_check, tv_riqi, tv_spinner,
				tv_fujian, tv_1;
		Button btn_weizhi;
		CheckBox cb_send;
		ImageButton bccl;
		EditText edt_tgbt, edt_hynr;
		TextView tv_custom;
	}

}
