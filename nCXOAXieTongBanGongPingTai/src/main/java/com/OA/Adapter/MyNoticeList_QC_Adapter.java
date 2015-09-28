package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.R;
import com.OA.Activity.Tzgg_detail_MainActivity;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.Notice_Bean;
import com.OA.View.Dialog_Toast;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyNoticeList_QC_Adapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context = null;
	List<Notice_Bean> list;
	InfoFile_ infofile;
	Handler handler;
	public MyNoticeList_QC_Adapter(Context context,List<Notice_Bean> list,Handler handler){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
		infofile = new InfoFile_(context);
		this.handler = handler;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.tzgg_qc_item, null);
			holder.gwbt = (TextView) convertView
					.findViewById(R.id.gwbt);
			holder.ztc = (TextView) convertView
					.findViewById(R.id.ztc);
			holder.tglx = (TextView) convertView
					.findViewById(R.id.tglx);
			holder.shzt = (TextView) convertView
					.findViewById(R.id.shzt);
			holder.cjr = (TextView) convertView
					.findViewById(R.id.cjr);
			holder.cjsj = (TextView) convertView
					.findViewById(R.id.cjsj);
			holder.btn_xiugai = (Button) convertView
					.findViewById(R.id.btn_xiugai);
			holder.btn_shanchu = (Button) convertView
					.findViewById(R.id.btn_shanchu);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.gwbt.setText(list.get(position).getTitle());
		holder.ztc.setText(list.get(position).getSubject());
		holder.tglx.setText(list.get(position).getTypeName());
		holder.shzt.setText(list.get(position).getStatus().equals("2000")?"审核通过":"待审核");
		holder.cjr.setText(list.get(position).getCreaterName());
		holder.cjsj.setText(list.get(position).getCreateDate());
		holder.btn_xiugai.setText("修改");
		final int position1 = position;
		holder.btn_xiugai.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				if(list.get(position1).getStatus().equals("1000")){
					Constants.title = "通知公告修改";
					infofile.edit().statusOfNotice().put(list.get(position1).getStatus()).apply();
					//infofile.edit().flowid().put(Integer.valueOf(list.get(position1).getFlowID()).intValue()).apply();
					//infofile.edit().flowid().put(Integer.valueOf(list.get(position1).getSubject()).intValue()).apply();
						infofile.edit().NoticeID().put(Integer.valueOf(list.get(position1).getNoticeID()).intValue()).apply();
							context.startActivity(new Intent(context, Tzgg_detail_MainActivity.class).addFlags(2));
				}else{
					AlertDialog.Builder builder = new Builder(context);
					builder.setMessage("此数据已审核通过,不能修改");
					builder.setTitle("提示");
					builder.setPositiveButton("确认", null);
					builder.create().show();
				}

			}
		});
		holder.btn_shanchu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Dialog_Toast.dialog(context,handler, Integer.valueOf(list.get(position1).getNoticeID()).intValue());
			}
		});
		/*
		 * if(datas.get(position).lendNum!=null) {
		 * if(Integer.parseInt(datas.get(position).lendNum) >0) {
		 * holder.btn_yj.setVisibility(View.GONE); } }
		 */
		/*
		 * boolean isFind = false;
		 * 
		 * String barCode = ""; Log.i(Constants.datacurrent.size() + "",
		 * "HHHHH"); if (Constants.datacurrent.size() == 0)
		 * holder.btn_yj.setVisibility(View.GONE); else { for (int i = 0; i <
		 * Constants.datacurrent.size(); i++) {
		 * 
		 * if (datas.get(position).marcRecNo.equals(Constants.datacurrent
		 * .get(i).marcRecNo)) { isFind = true; barCode =
		 * Constants.datacurrent.get(i).barCode; break; } } if (isFind) {
		 * holder.btn_yj.setText("续借"); } else { holder.btn_yj.setText("预约"); }
		 * final boolean isFindF = isFind; final String barCodeF = barCode;
		 */

		return convertView;
	}
	



	
	class ViewHolder {
		TextView gwbt;
		TextView ztc;
		TextView tglx;
		TextView shzt;
		TextView cjr;
		TextView cjsj;
		Button btn_xiugai, btn_shanchu;
	}
	
}
