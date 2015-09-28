package com.OA.Adapter;

import java.util.List;

import android.R.integer;
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

import com.OA.Activity.BaseActivity;
import com.OA.Activity.Detail_Wdxzfw_chakantabs_MainActivity;
import com.OA.Activity.Detail_Wdxzsw_chakantabs_MainActivity;
import com.OA.Activity.R;
import com.OA.Data.InfoFile_;
import com.OA.Entity.MyRecApply_wdxzsw_Bean;
import com.OA.Entity.RecApply_xzsw_bean;

public class MyReceApply_Wdxzsw_Adapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context = null;
	List<RecApply_xzsw_bean> list;
	private String FlowID,WFID,Title,endTime;
	InfoFile_ infoFile_;
	public MyReceApply_Wdxzsw_Adapter(Context context,List<RecApply_xzsw_bean> list){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
		infoFile_ = new InfoFile_(context);
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
		final ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate
					(R.layout.xzsw_myxzsw_item, null);
			/*holder.xh = (TextView) convertView
					.findViewById(R.id.xh);*/
			holder.bt = (TextView) convertView
					.findViewById(R.id.bt);
			holder.fqr = (TextView) convertView
					.findViewById(R.id.fqr);
			holder.wh = (TextView) convertView
					.findViewById(R.id.wh);
			holder.lwdw = (TextView) convertView
					.findViewById(R.id.lwdw);
			holder.fqsj = (TextView) convertView
					.findViewById(R.id.fqsj);
			holder.jzsj = (TextView) convertView
					.findViewById(R.id.jzrq);
			holder.zt = (TextView) convertView
					.findViewById(R.id.zt);
			holder.btn_apply = (Button) convertView
					.findViewById(R.id.btn_apply);

			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.bt.setText(list.get(position).getTitle());
		holder.fqr.setText(list.get(position).getUserName());
		holder.wh.setText(list.get(position).getWenHao());
		holder.lwdw.setText(list.get(position).getNiGaoCompany());
		holder.jzsj.setText(list.get(position).getBrowseEndTime());
		holder.fqsj.setText(list.get(position).getOpearatDate());
		if(list.get(position).getStatus().equals("1000")){
			holder.zt.setText("通过");
		}else if(list.get(position).getStatus().equals("1001")){
			holder.zt.setText("待处理");
		}else if(list.get(position).getStatus().equals("1002")){
			holder.zt.setText("已退回");
		}else if(list.get(position).getStatus().equals("1003")){
			holder.zt.setText("不通过");
		}else if(list.get(position).getStatus().equals("9999")){
			holder.zt.setText("操作异常");
		}

		final int position1 = position;
		holder.btn_apply.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FlowID = list.get(position1).getFlowID();
				WFID  = list.get(position1).getWFID();
				Title=list.get(position1).getTitle();
				endTime=list.get(position1).getBrowseEndTime();
				Intent intent = new Intent();
				intent.setClass(context, Detail_Wdxzsw_chakantabs_MainActivity.class);
				infoFile_.edit().flowid().put(Integer.valueOf(FlowID).intValue()).apply();
				infoFile_.edit().WFID().put(Integer.valueOf(WFID).intValue()).apply();
				infoFile_.edit().Title().put(Title).apply();
				infoFile_.edit().endTime().put(endTime).apply();
				context.startActivity(intent);
			}
		});
		
		return convertView;
	}
	class ViewHolder{
		//TextView xh;
		TextView bt;
		TextView wh;
		TextView lwdw;
		TextView fqr;
		TextView jzsj;
		TextView fqsj;
		TextView zt;
		Button btn_apply;
	}
}
