package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.R;
import com.OA.Activity.Tzgg_detail_MainActivity;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.Notice_Bean;
import com.OA.Entity.Notice_sh_Bean;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyNoticeList_SH_Adapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context = null;
	List<Notice_sh_Bean> list;
	InfoFile_ infofile;
	public MyNoticeList_SH_Adapter(Context context,List<Notice_sh_Bean> list){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
		infofile = new InfoFile_(context);
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
		holder.shzt.setText(list.get(position).getStatus().equals("2000")?"审核通过":"审核不通过");
		if(list.get(position).getStatus().equals("2000")){
			holder.shzt.setText("审核通过");
		}else if(list.get(position).getStatus().equals("1000")){
			holder.shzt.setText("待审核");
		}else if(list.get(position).getStatus().equals("1002")){
			holder.shzt.setText("审核不通过");
		}else{
			holder.shzt.setText("未知状态");
		}
		
		holder.cjr.setText(list.get(position).getCreaterName());
		holder.cjsj.setText(list.get(position).getCreateDate());
		holder.btn_xiugai.setText("查看");
		final int position1 = position;
		holder.btn_xiugai.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Constants.title = "通知公告审核";
				infofile.edit().statusOfNotice().put(list.get(position1).getStatus()).apply();
				infofile.edit().WFStepID().put(Integer.valueOf(list.get(position1).getWFStepID()).intValue()).apply();
				infofile.edit().flowid().put(Integer.valueOf(list.get(position1).getFlowID()).intValue()).apply();
				infofile.edit().NoticeID().put(Integer.valueOf(list.get(position1).getNoticeID()).intValue()).apply();
				context.startActivity(new Intent(context, Tzgg_detail_MainActivity.class).addFlags(1));
			}
		});
		holder.btn_shanchu.setVisibility(View.INVISIBLE);
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
