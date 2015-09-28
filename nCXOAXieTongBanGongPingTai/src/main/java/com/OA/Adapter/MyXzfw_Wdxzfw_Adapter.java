package com.OA.Adapter;

import java.util.List;




import com.OA.Activity.Detail_Wdxzfw_chakantabs_MainActivity;
import com.OA.Activity.R;
import com.OA.Data.InfoFile_;
import com.OA.Entity.Wdxzfw_Bean;



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

public class MyXzfw_Wdxzfw_Adapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context = null;
	List<Wdxzfw_Bean> list;
	private String FlowID,WFID,Title,endTime;
	int CurNodeID;
	InfoFile_ infoFile_;
	public MyXzfw_Wdxzfw_Adapter(Context context,List<Wdxzfw_Bean> list){
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
	public View getView( int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.xzfw_wdxzfw_item, null);
			holder.tv_xh = (TextView) convertView
					.findViewById(R.id.tv_xh);
			holder.tv_bt = (TextView) convertView
					.findViewById(R.id.tv_bt);
			holder.tv_wh = (TextView) convertView
					.findViewById(R.id.tv_wh);
			holder.tv_ngdw = (TextView) convertView
					.findViewById(R.id.tv_ngdw);
			holder.tv_fqr = (TextView) convertView
					.findViewById(R.id.tv_fqr);
			holder.tv_fqsj = (TextView) convertView
					.findViewById(R.id.tv_fqsj);
			holder.tv_jzrq = (TextView) convertView
					.findViewById(R.id.tv_jzrq);
			holder.tv_status= (TextView) convertView
					.findViewById(R.id.tv_status);
		
			holder.btn_xiangqi= (Button) convertView
					.findViewById(R.id.btn_xiangxi);
			holder.btn_check= (Button) convertView
					.findViewById(R.id.btn_check);
		
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tv_xh.setText(list.get(position).getRow_number());
		holder.tv_bt.setText(list.get(position).getTitle());
		holder.tv_fqr.setText(list.get(position).getUserName());
		holder.tv_ngdw.setText(list.get(position).getNiGaoCompany());
		holder.tv_wh.setText(list.get(position).getTildes()+list.get(position).getWenHao()+" "+list.get(position).getLiuShuiHao());
		holder.tv_fqsj.setText(list.get(position).getOpearatDate());
		holder.tv_jzrq.setText(list.get(position).getBrowseEndTime());
		if(list.get(position).getStatus().equals("1000")){
			holder.tv_status.setText("通过");
		}else if(list.get(position).getStatus().equals("1001")){
			holder.tv_status.setText("待处理");
		}else if(list.get(position).getStatus().equals("1002")){
			holder.tv_status.setText("已退回");
		}else if(list.get(position).getStatus().equals("1003")){
			holder.tv_status.setText("不通过");
		}else if(list.get(position).getStatus().equals("9999")){
			holder.tv_status.setText("操作异常");
		}
		
		final int position1 = position;
	
		holder.btn_xiangqi.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
				FlowID = list.get(position1).getFlowID();
				WFID  = list.get(position1).getWFID();
				Title=list.get(position1).getTitle();
				endTime=list.get(position1).getBrowseEndTime();
				CurNodeID = -1;
				Intent intent=new Intent();
				intent.setClass(context, Detail_Wdxzfw_chakantabs_MainActivity.class);
				infoFile_.edit().flowid().put(Integer.valueOf(FlowID).intValue()).apply();
				infoFile_.edit().WFID().put(Integer.valueOf(WFID).intValue()).apply();
				infoFile_.edit().Title().put(Title).apply();
				infoFile_.edit().endTime().put(endTime).apply();
				infoFile_.edit().CurNode().put(CurNodeID+"").apply();
				context.startActivity(intent);
				
			}
		});
		holder.btn_check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

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
		TextView tv_xh,tv_bt,tv_wh,tv_ngdw,tv_fqr,tv_fqsj,tv_jzrq,tv_status;
	
		Button btn_xiangqi,btn_check;
	}
	
}
