package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.Detail_Wdhytz_xiangxi_MainActivity;
import com.OA.Activity.R;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.Meeting_Bean;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Service.SignoffManager;
import com.OA.View.Dialog_Toast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyMeeting_Wdhytz_Adapter extends BaseAdapter{
	private String tag="NOT";
	private MyMeetingDetail_Bean bean;
	private LayoutInflater inflater;
	private Context context = null;
	List<Meeting_Bean> list;
	InfoFile_ infofile_;
	public MyMeeting_Wdhytz_Adapter(Context context,List<Meeting_Bean> list){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
		infofile_ = new InfoFile_(context);
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
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.hyxx_wdhytz_item, null);
			holder.yt = (TextView) convertView
					.findViewById(R.id.tv_yt);
			holder.hys = (TextView) convertView
					.findViewById(R.id.tv_hys);
			holder.start_time = (TextView) convertView
					.findViewById(R.id.tv_starttime);
			holder.end_time = (TextView) convertView
					.findViewById(R.id.tv_endtime);
			holder.sq_name= (TextView) convertView
					.findViewById(R.id.tv_sqname);
			holder.zcr_name= (TextView) convertView
					.findViewById(R.id.tv_status);
			holder.btn_xiangqi= (Button) convertView
					.findViewById(R.id.btn_xiangxi);
			holder.btn_qianshou= (Button) convertView
					.findViewById(R.id.btn_qianshou);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.yt.setText(list.get(position).getMeetingTitle());
		holder.hys.setText(list.get(position).getRoomName());
		holder.start_time.setText(list.get(position).getMeetingStart());
		holder.end_time.setText(list.get(position).getMeetingEnd());
		holder.sq_name.setText(list.get(position).getApplicant());
		holder.zcr_name.setText(list.get(position).getHost());
		final int position1=position;
		joinCheck = list.get(position1).getJoinCheck();
		if(joinCheck.equals("100")){
			holder.btn_xiangqi.setVisibility(View.INVISIBLE);;
			holder.btn_qianshou.setVisibility(View.VISIBLE);;
		}else if(joinCheck.equals("99")){
			holder.btn_xiangqi.setVisibility(View.VISIBLE);;
			holder.btn_qianshou.setVisibility(View.INVISIBLE);;
		}
		holder.btn_xiangqi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				bundle.putString("RoomLogID", list.get(position1).RoomLogID);
				intent.putExtras(bundle);
				intent.setClass(context,  Detail_Wdhytz_xiangxi_MainActivity.class);
				
				context.startActivity(intent);
			}
		});
		holder.btn_qianshou.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				signoff.getSignoffManager(context, 1,Integer.valueOf(list.get(position1).RoomLogID), infofile_.infoUsername().get(), infofile_.infoUserType().get());
				if(bean != null && bean.getStatus().equals("2000")){
					holder.btn_qianshou.setVisibility(View.GONE);
				}
			}
		});
	

		return convertView;
	}

	class ViewHolder {
		TextView yt,hys,start_time,end_time,sq_name,zcr_name;
	
		Button btn_xiangqi, btn_qianshou;
	}
	SignoffManager signoff=new SignoffManager() {
		
		private int position1;
		private Handler handler;

		@Override
		protected void handlerLoginInfo(Context context, HandleResult handleResult,
				int paramInt) {
			if(handleResult.getiSuccess().equals("success")){
				bean = Constants.mymeetingdetail_bean;
				if(bean != null && bean.getStatus().equals("2000")){
					Dialog_Toast.qianshoudialog1(context,handler, Integer.valueOf(list.get(position1).getRoomLogID()).intValue());
				}else if(bean != null && bean.getStatus().equals("5000")){
					Dialog_Toast.qianshoudialog2(context,handler, Integer.valueOf(list.get(position1).getRoomLogID()).intValue());
				}else if(bean != null && bean.getStatus().equals("0000")){
					Dialog_Toast.qianshoudialog3(context,handler, Integer.valueOf(list.get(position1).getRoomLogID()).intValue());
				}
			}
		}
	};
	private String joinCheck;
}
