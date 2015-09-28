package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.Detail_Wdxzfw_chakantabs_MainActivity;
import com.OA.Activity.Detail_xzfwsh_chakantabs_MainActivity;
import com.OA.Activity.R;
import com.OA.Data.InfoFile_;
import com.OA.Entity.Xzfwsh_Bean;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyXzfw_Xzfwsh_Adapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context = null;
	List<Xzfwsh_Bean> list;
	private String FlowID,WFID,Title,endTime,FlowStepID,statusOfShenhe;
	InfoFile_ infoFile_;
	private String tag;
	public MyXzfw_Xzfwsh_Adapter(Context context,List<Xzfwsh_Bean> list){
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
					R.layout.xzfw_xzfwsh_item, null);
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
			holder.tv_status= (TextView) convertView
					.findViewById(R.id.tv_status);
		
			holder.btn_xiangqi= (Button) convertView
					.findViewById(R.id.btn_xiangxi);
			holder.btn_cklcbz= (Button) convertView
					.findViewById(R.id.btn_cklcbz);
			holder.btn_sh= (Button) convertView
					.findViewById(R.id.btn_sh);
		
		
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
		
		if(list.get(position).getStepStauts().equals("1000")){
			holder.btn_sh.setVisibility(View.VISIBLE);
			holder.tv_status.setText("待审核");
			infoFile_.edit().statusOfSHENHE().put("1000").apply();
		}else if(list.get(position).getStepStauts().equals("1001")){
			holder.btn_sh.setVisibility(View.GONE);
			holder.tv_status.setText("审核通过");
			infoFile_.edit().statusOfSHENHE().put("1001").apply();
		}else if(list.get(position).getStepStauts().equals("1002")){
			holder.btn_sh.setVisibility(View.GONE);
			holder.tv_status.setText("审核转移");
			infoFile_.edit().statusOfSHENHE().put("10002").apply();
		}else if(list.get(position).getStepStauts().equals("1003")){
			holder.btn_sh.setVisibility(View.GONE);
			holder.tv_status.setText("审核退回");
			infoFile_.edit().statusOfSHENHE().put("1003").apply();
		}else if(list.get(position).getStepStauts().equals("2001")){
			holder.btn_sh.setVisibility(View.GONE);
			holder.tv_status.setText("审核不通过");
			infoFile_.edit().statusOfSHENHE().put("2001").apply();
		}else if(list.get(position).getStepStauts().equals("2003")){
			holder.btn_sh.setVisibility(View.GONE);
			holder.tv_status.setText("被审核退回");
			infoFile_.edit().statusOfSHENHE().put("2003").apply();
		}else{
			holder.btn_sh.setVisibility(View.GONE);
			holder.tv_status.setText("未知状态");
			infoFile_.edit().statusOfSHENHE().put("10000").apply();
		}
		final int position1 = position;
		
		holder.btn_xiangqi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(context, Detail_xzfwsh_chakantabs_MainActivity.class);
				FlowID = list.get(position1).getFlowID();
				WFID  = list.get(position1).getWFID();
				Title=list.get(position1).getTitle();
				endTime=list.get(position1).getBrowseEndTime();
				FlowStepID = list.get(position1).getWFStepID();
				statusOfShenhe=list.get(position1).getStepStauts();
				infoFile_.edit().flowid().put(Integer.valueOf(FlowID).intValue()).apply();
				infoFile_.edit().WFStepID().put(Integer.valueOf(FlowStepID).intValue()).apply();
				infoFile_.edit().statusOfSHENHE().put(statusOfShenhe).apply();
				infoFile_.edit().FlowID().put(Integer.valueOf(FlowID).intValue()).apply();
				infoFile_.edit().FlowStepID().put(Integer.valueOf(FlowStepID).intValue()).apply();
				infoFile_.edit().WFID().put(Integer.valueOf(WFID).intValue()).apply();
				infoFile_.edit().CurNode().put(list.get(position1).getCurStepNode()).apply();
				infoFile_.edit().Title().put(Title).apply();
				infoFile_.edit().endTime().put(endTime).apply();
				context.startActivity(intent);
			}
		});
		holder.btn_cklcbz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});

		holder.btn_sh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
//				intent.setClass(context, Detail_Wdxzfw_chakantabs_MainActivity.class);
	//			Bundle bundle=new Bundle();
				intent.setClass(context, Detail_xzfwsh_chakantabs_MainActivity.class);
				FlowID = list.get(position1).getFlowID();
				WFID  = list.get(position1).getWFID();
				Title=list.get(position1).getTitle();
				endTime=list.get(position1).getBrowseEndTime();
				FlowStepID = list.get(position1).getWFStepID();
				statusOfShenhe=list.get(position1).getStepStauts();
				infoFile_.edit().flowid().put(Integer.valueOf(FlowID).intValue()).apply();
				infoFile_.edit().WFStepID().put(Integer.valueOf(FlowStepID).intValue()).apply();
				infoFile_.edit().statusOfSHENHE().put(statusOfShenhe).apply();
				infoFile_.edit().FlowID().put(Integer.valueOf(FlowID).intValue()).apply();
				infoFile_.edit().FlowStepID().put(Integer.valueOf(FlowStepID).intValue()).apply();
				infoFile_.edit().WFID().put(Integer.valueOf(WFID).intValue()).apply();
				infoFile_.edit().CurNode().put(list.get(position1).getCurStepNode()).apply();
				infoFile_.edit().Title().put(Title).apply();
				infoFile_.edit().endTime().put(endTime).apply();
	//			bundle.putString("FlowID", FlowID);
	//			bundle.putString("WFID", WFID);
	//			bundle.putString("Title", Title );
	//			bundle.putString("endTime", endTime );
	//			intent.putExtras(bundle);
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
		TextView tv_xh,tv_bt,tv_fqr,tv_wh,tv_creattime,tv_ngdw,tv_endtime,tv_status;
	
		Button btn_xiangqi,btn_cklcbz,btn_sh;
	}
	
}
