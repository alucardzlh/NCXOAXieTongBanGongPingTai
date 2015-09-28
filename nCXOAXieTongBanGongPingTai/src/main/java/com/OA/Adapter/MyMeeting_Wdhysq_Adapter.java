package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.Hyxx_detail_MainActivity;
import com.OA.Activity.QuxiaoDialog_Toast_MainAcitvity;
import com.OA.Activity.R;
import com.OA.Data.InfoFile_;
import com.OA.Entity.Meeting_Bean;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyMeeting_Wdhysq_Adapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context = null;
	List<Meeting_Bean> list;
	private String Title;
	InfoFile_ infofile;
	public MyMeeting_Wdhysq_Adapter(Context context,List<Meeting_Bean> list){
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
	public View getView( int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.hyxx_wdhysq_item, null);
			holder.yt = (TextView) convertView
					.findViewById(R.id.tv_yt);
			holder.hys = (TextView) convertView
					.findViewById(R.id.tv_hys);
			holder.sq_time = (TextView) convertView
					.findViewById(R.id.tv_sqtime);
			holder.start_time = (TextView) convertView
					.findViewById(R.id.tv_starttime);
			holder.end_time = (TextView) convertView
					.findViewById(R.id.tv_endtime);
			holder.sq_name= (TextView) convertView
					.findViewById(R.id.tv_sqname);
			holder.status= (TextView) convertView
					.findViewById(R.id.tv_status);
			holder.btn_xiangqi= (Button) convertView
					.findViewById(R.id.btn_xiangxi);
			holder.btn_check= (Button) convertView
					.findViewById(R.id.btn_check);
			holder.btn_quxiao= (Button) convertView
					.findViewById(R.id.btn_quxiao);
			holder.btn_anpai= (Button) convertView
					.findViewById(R.id.btn_anpai);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(list.get(position).getStatus().equals("2000")){
			holder.status.setText("通过");
			holder.btn_quxiao.setVisibility(View.VISIBLE);
		}else if(list.get(position).getStatus().equals("1000")){
			holder.status.setText("待审核");
			holder.btn_quxiao.setVisibility(View.VISIBLE);
		}else if(list.get(position).getStatus().equals("999")){
			holder.status.setText("已取消");
			holder.btn_quxiao.setVisibility(View.GONE);
		}else if(list.get(position).getStatus().equals("1002")){
			holder.status.setText("未通过");
			holder.btn_quxiao.setVisibility(View.VISIBLE);
		}else{
			holder.status.setText("操作失败");
			holder.btn_quxiao.setVisibility(View.VISIBLE);
		}
		holder.yt.setText(list.get(position).getMeetingTitle());
		holder.hys.setText(list.get(position).getRoomName());
		holder.sq_time.setText(list.get(position).getCreateTime());
		holder.start_time.setText(list.get(position).getMeetingStart());
		holder.end_time.setText(list.get(position).getMeetingEnd());
		holder.sq_name.setText(list.get(position).getApplicant());
		
		final int position1 = position;
		holder.btn_xiangqi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				infofile.edit().typeOfHyxxBottomButton().put(1).apply();
				infofile.edit().RoomLogID().put(Integer.valueOf(list.get(position1).getRoomLogID()).intValue()).apply();
				context.startActivity(new Intent(context, Hyxx_detail_MainActivity.class));
			}
		});
		holder.btn_check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		holder.btn_quxiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				infofile.edit().RoomLogID().put(Integer.valueOf(list.get(position1).getRoomLogID()).intValue()).apply();
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				Title=list.get(position1).getMeetingTitle();
				bundle.putString("Title", Title);
				intent.setClass(context, QuxiaoDialog_Toast_MainAcitvity.class);
				intent.putExtras(bundle);
				context.startActivity(intent);
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
		TextView yt,hys,sq_time,start_time,end_time,sq_name,status;
	
		Button btn_xiangqi, btn_check,btn_quxiao,btn_anpai;
	}
	
}
