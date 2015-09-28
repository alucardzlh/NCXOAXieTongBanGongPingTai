package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.Detail_Xzswyy_chakan_MainActivity;
import com.OA.Activity.R;
import com.OA.Data.InfoFile_;
import com.OA.Entity.RecApply_xzswdy_Bean;

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

public class RecApply_Xzswdy_Adapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context = null;
	List<RecApply_xzswdy_Bean> list;
	private String FlowID,WFID,Title,endTime;
	InfoFile_ infoFile_;
	public RecApply_Xzswdy_Adapter(Context context,List<RecApply_xzswdy_Bean> list){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
		infoFile_ = new InfoFile_(context);
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
					R.layout.xzsw_xzswdy_item, null);
			holder.bt = (TextView) convertView
					.findViewById(R.id.bt);
			holder.wh = (TextView) convertView
					.findViewById(R.id.wh);
			holder.lwdw = (TextView) convertView
					.findViewById(R.id.lwdw);
			holder.fqr = (TextView) convertView
					.findViewById(R.id.fqr);
			holder.sslc = (TextView) convertView
					.findViewById(R.id.sslc);
			holder.fqsj= (TextView) convertView
					.findViewById(R.id.fqsj);
			holder.jzsj= (TextView) convertView
					.findViewById(R.id.jzrq);
			holder.btn_apply= (Button) convertView
					.findViewById(R.id.btn_apply);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.bt.setText(list.get(position).getTitle());
		holder.wh.setText(list.get(position).getTildes()+list.get(position).getWenHao()+" "+list.get(position).getLiuShuiHao());
		holder.lwdw.setText(list.get(position).getNiGaoCompany());
		holder.fqr.setText(list.get(position).getUserName());
		holder.sslc.setText(list.get(position).getNiGaoCompany());
		holder.fqsj.setText(list.get(position).getOpearatDate());
		holder.jzsj.setText(list.get(position).getBrowseEndTime());
		
		final int position1 = position;
			
			
			holder.btn_apply.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(context, Detail_Xzswyy_chakan_MainActivity.class);
	//				Bundle bundle = new Bundle();
					FlowID = list.get(position1).getFlowID();
					WFID  = list.get(position1).getWFID();
					Title=list.get(position1).getTitle();
					endTime=list.get(position1).getBrowseEndTime();
					infoFile_.edit().flowid().put(Integer.valueOf(FlowID).intValue()).apply();;
					infoFile_.edit().WFID().put(Integer.valueOf(WFID).intValue()).apply();;
					infoFile_.edit().Title().put(Title).apply();
					infoFile_.edit().endTime().put(endTime).apply();
	//				bundle.putString("FlowID", FlowID);
	//				bundle.putString("WFID", WFID);
	//				bundle.putString("Title", Title);
	//				bundle.putString("endTime", endTime);
	//				intent.putExtras(bundle);
					context.startActivity(intent);
				}
			});

		return convertView;
	}

	class ViewHolder {
		TextView bt,wh,lwdw,fqr,sslc,fqsj,jzsj;
	
		Button btn_apply;
	}
	
}
