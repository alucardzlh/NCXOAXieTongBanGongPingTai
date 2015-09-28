package com.OA.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.OA.Activity.R;
import com.OA.Activity.UpLoadFile_Activity;
import com.OA.Data.Constants;
import com.OA.Entity.Detail_Bdxx_Bean;
import com.OA.Entity.File_ID_Name_Bean;
import com.OA.Interface.MyCallBack;
import com.OA.Util.StringUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FormFillViewOnlyForShow {
	Context context;
	List<Detail_Bdxx_Bean> list;
	private String elementType;
	MyCallBack callBack;
	private LinearLayout view;

	public FormFillViewOnlyForShow(Context context, List<Detail_Bdxx_Bean> list,
			MyCallBack callback) {
		this.list = list;
		this.context = context;
		this.callBack = callback;
		initView();
	}

	public View getView() {
		return view;
	}

	private void initView() {
		view = new LinearLayout(context);
		view.setOrientation(LinearLayout.VERTICAL);
		for (int i = 0; i < list.size(); i++) {
			View v = null;
			v = initItemView(list.get(i), i);
			view.addView(v);
			
		}
	}

	private View getItemView() {
		ViewHolder viewHolder = null;
		View convertView = null;
		viewHolder = new ViewHolder();
		convertView = LayoutInflater.from(context).inflate(R.layout.bdxx_item,
				null);
		viewHolder.CheckBoxList = (LinearLayout) convertView
				.findViewById(R.id.ll_checkbox);
		viewHolder.FileUpload = (LinearLayout) convertView
				.findViewById(R.id.ll_upload);
		viewHolder.TXTInput = (LinearLayout) convertView
				.findViewById(R.id.ll_textup);
		viewHolder.Select = (LinearLayout) convertView
				.findViewById(R.id.ll_slect);
				viewHolder.ll_tzgg_tglx = (LinearLayout) convertView
				.findViewById(R.id.ll_tzgg_tglx);
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
		viewHolder.tv_riqi = (TextView) convertView.findViewById(R.id.tv_riqi);
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
		viewHolder.cb_send = (CheckBox) convertView.findViewById(R.id.cb_send);
		viewHolder.download = (ImageButton) convertView.findViewById(R.id.download);
		viewHolder.upload = (ImageButton) convertView.findViewById(R.id.upload);
		viewHolder.edt_tgbt = (TextView) convertView
				.findViewById(R.id.edt_tgbt);
		viewHolder.edt_hynr = (TextView) convertView
				.findViewById(R.id.edt_hynr);
		convertView.setTag(viewHolder);
		return convertView;
	}

	private View initItemView(final Detail_Bdxx_Bean map, final int position) {
		View item = getItemView();
		ViewHolder viewHolder = (ViewHolder) item.getTag();
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
			viewHolder.edt_tgbt.setText(list.get(position).getElementValue());
			if (!list.get(position).getElementLabelName().equals("")) {
				viewHolder.tv_textview.setText(list.get(position)
						.getElementLabelName());
			} else
				viewHolder.tv_textview.setText(list.get(position)
						.getElementName());
		}
		// 多行文本输入框
		else if (elementType.equals("multipleInput")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.VISIBLE);
			viewHolder.DateInput.setVisibility(View.GONE);
			viewHolder.edt_hynr.setText(list.get(position).getElementValue());
			if (!list.get(position).getElementLabelName().equals("")) {
				viewHolder.tv_textviewmore.setText(list.get(position)
						.getElementLabelName());
			} else
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
			viewHolder.tv_1.setText(list.get(position).getElementValue());

		} else if (elementType.equals("DateTimeInput")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.VISIBLE);
			viewHolder.tv_riqi.setText(list.get(position).getElementName());
			viewHolder.tv_1.setText(list.get(position).getElementValue());
		} else if (elementType.equals("DateDefault")) {
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.VISIBLE);
			viewHolder.tv_riqi.setText(list.get(position).getElementName());
			viewHolder.tv_1.setText(list.get(position).getElementValue());
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
			viewHolder.upload.setVisibility(View.GONE);
			final List<String> list_IDs = StringUtil.stringToNormalList(list.get(position).getElementValue());
			/*Toast.makeText(context, "!"+list_IDs.get(0)+"!",
					Toast.LENGTH_SHORT).show();*/
			viewHolder.download.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
					if(list_IDs != null && list_IDs.size() != 0){
						List<File_ID_Name_Bean> list = new ArrayList<File_ID_Name_Bean>();
						for(int i=0;i<list_IDs.size();i++){
						
							File_ID_Name_Bean bean = new File_ID_Name_Bean();
							bean.setShowName("file"+i);
							if(list_IDs.get(i)!=null&&list_IDs.get(i)!=""){
							bean.setFileID(Integer.valueOf(list_IDs.get(i)).intValue());
							list.add(bean);
							}else{Toast.makeText(context, "!"+list_IDs.get(i)+"!",
									Toast.LENGTH_SHORT).show();}
							
						}
						Constants.list_files = list;
						context.startActivity(new Intent(context,
								UpLoadFile_Activity.class).addFlags(2000));
					}
				
					else{
						Toast.makeText(context, "无附件！",
								Toast.LENGTH_SHORT).show();
					}
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
			viewHolder.tv_custom.setText(list.get(position).getElementValue());
			viewHolder.tv_spinner.setText(list.get(position).getElementName());

		}else if(list.get(position).getElementID().equals("-1000")){
			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.VISIBLE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.GONE);
			viewHolder.tv_spinner.setText(list.get(position).getElementName());
			viewHolder.tv_custom.setText(list.get(position).getElementValue());
			final List<String> list_optionText =  StringUtil.stringToNormalList(list.get(position)
					.getOptionsText());
			final List<String> list_option_value = StringUtil.stringToNormalList(list
					.get(position).getOptionValue());
			if(list_optionText != null && list_option_value != null){
				for(int i=0;i<list_option_value.size();i++){
					if(list_option_value.get(i).equals(list.get(position).getElementValue())){
						viewHolder.tv_custom.setText(list_optionText.get(i));
					}
				}
			}
			
		}
		else {

			viewHolder.CheckBoxList.setVisibility(View.GONE);
			viewHolder.FileUpload.setVisibility(View.GONE);
			viewHolder.Select.setVisibility(View.GONE);
			viewHolder.TXTInput.setVisibility(View.GONE);
			viewHolder.multipleInput.setVisibility(View.GONE);
			viewHolder.DateInput.setVisibility(View.GONE);
		}
		return item;
	}

	class ViewHolder {

		LinearLayout DateInput, TXTInput, CheckBoxList, multipleInput, Select,
				FileUpload,ll_tzgg_tglx;
		TextView tv_textview, tv_textviewmore, tv_check, tv_riqi, tv_spinner,
				tv_fujian, tv_1;
		Button btn_weizhi;
		CheckBox cb_send;
		ImageButton upload,download;
		TextView edt_tgbt, edt_hynr;
		TextView tv_custom;
	}

}
