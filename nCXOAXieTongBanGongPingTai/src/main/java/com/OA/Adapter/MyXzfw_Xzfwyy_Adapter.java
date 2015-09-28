package com.OA.Adapter;

import java.util.List;

import org.apache.poi.ss.formula.functions.IfFunc;

import com.OA.Activity.Detail_Wdxzfw_chakantabs_MainActivity;
import com.OA.Activity.Detail_Xzfwyy_chakantabs_MainActivity;
import com.OA.Activity.R;
import com.OA.Data.InfoFile_;
import com.OA.Entity.MyXzfwyy_Bean;
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

public class MyXzfw_Xzfwyy_Adapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context = null;
	List<MyXzfwyy_Bean> list;
	private String FlowID,WFID,Title,endTime;
	InfoFile_ infoFile_;
	public MyXzfw_Xzfwyy_Adapter(Context context,List<MyXzfwyy_Bean> list){
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
					R.layout.xzfw_xzfwyy_item, null);
			holder.tv_xh = (TextView) convertView
					.findViewById(R.id.tv_xh);
			holder.tv_bt = (TextView) convertView
					.findViewById(R.id.tv_bt);
			holder.tv_wh= (TextView) convertView
					.findViewById(R.id.tv_wh);
			holder.tv_ngdw= (TextView) convertView
					.findViewById(R.id.tv_ngdw);
			holder.tv_fqr = (TextView) convertView
					.findViewById(R.id.tv_fqr);
			holder.tv_creattime= (TextView) convertView
					.findViewById(R.id.tv_creattime);
			holder.tv_endtime= (TextView) convertView
					.findViewById(R.id.tv_endtime);
		
			holder.btn_xiangqi= (Button) convertView
					.findViewById(R.id.btn_xiangxi);
		
		
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tv_xh.setText((position+1)+"");
		holder.tv_bt.setText(list.get(position).getTitle());
		holder.tv_wh.setText(list.get(position).getTildes()+list.get(position).getWenHao()+" "+list.get(position).getLiuShuiHao());
		holder.tv_fqr.setText(list.get(position).getUserName());
		holder.tv_ngdw.setText(list.get(position).getNiGaoCompany());
		holder.tv_creattime.setText(list.get(position).getOpearatDate());
		holder.tv_endtime.setText(list.get(position).getBrowseEndTime());
		
		final int position1 = position;
		
		holder.btn_xiangqi.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(context, Detail_Xzfwyy_chakantabs_MainActivity.class);
				Bundle bundle=new Bundle();
				FlowID = list.get(position1).getFlowID();
				WFID  = list.get(position1).getWFID();
				Title=list.get(position1).getTitle();
				endTime=list.get(position1).getBrowseEndTime();
				infoFile_.edit().flowid().put(Integer.valueOf(FlowID).intValue()).apply();
				infoFile_.edit().WFID().put(Integer.valueOf(WFID).intValue()).apply();
				infoFile_.edit().Title().put(Title).apply();
				infoFile_.edit().endTime().put(endTime).apply();
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
		TextView tv_xh,tv_bt,tv_fqr,tv_wh,tv_creattime,tv_ngdw,tv_endtime;
	
		Button btn_xiangqi;
	}
	
}
