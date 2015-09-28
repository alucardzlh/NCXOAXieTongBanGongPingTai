package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.Detail_Wdxzsw_chakantabs_MainActivity;
import com.OA.Activity.Detail_Xzswcl_chakantabs_MainActivity;
import com.OA.Activity.R;
import com.OA.Adapter.RecApply_Xzswyy_Adapter.ViewHolder;
import com.OA.Data.InfoFile;
import com.OA.Data.InfoFile_;
import com.OA.Entity.RecApply_xzswcl_Bean;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecApply_Xzswcl_Adapter extends BaseAdapter{
 private LayoutInflater inflater;
 private Context context = null;
 List<RecApply_xzswcl_Bean> list;
 private String FlowID,WFID,Title,endTime,FlowStepID,statusOfShenhe;
 InfoFile_ infoFile;
 public RecApply_Xzswcl_Adapter(Context context, List<RecApply_xzswcl_Bean> list){
	 this.context = context;
	 this.list = list;
	 inflater = LayoutInflater.from(context);
	 infoFile = new InfoFile_(context);
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

	TextView tv;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.xzsw_xzswcl_item, null);
			holder.bt = (TextView) convertView
					.findViewById(R.id.bt);
			holder.wh = (TextView) convertView
					.findViewById(R.id.wh);
			holder.lwdw = (TextView) convertView
					.findViewById(R.id.lwdw);
			holder.fqr = (TextView) convertView
					.findViewById(R.id.fqr);
			holder.fqsj= (TextView) convertView
					.findViewById(R.id.fqsj);
			holder.jzsj= (TextView) convertView
					.findViewById(R.id.jzrq);
			holder.zt = (TextView) convertView
					.findViewById(R.id.zt);
			holder.btn_apply= (Button) convertView
					.findViewById(R.id.btn_apply);
			holder.btn_applyes = (Button) convertView.
					findViewById(R.id.btn_applyes);
			holder.btn_check = (Button) convertView.
					findViewById(R.id.btn_check);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.bt.setText(list.get(position).getTitle());
		holder.wh.setText(list.get(position).getTildes()+list.get(position).getWenHao()+" "+list.get(position).getLiuShuiHao());
		holder.lwdw.setText(list.get(position).getNiGaoCompany());
		holder.fqr.setText(list.get(position).getUserName());
		//holder.sslc.setText(list.get(position).getNiGaoCompany());
		holder.fqsj.setText(list.get(position).getOpearatDate());
		holder.jzsj.setText(list.get(position).getBrowseEndTime());
	//	holder.zt.setText(list.get(position).getStepStauts().equals("1000")?"待审核":"审核通过");
		if(list.get(position).getStepStauts().equals("1000")){
			holder.btn_check.setVisibility(View.VISIBLE);
			holder.zt.setText("待审核");
			infoFile.edit().statusOfSHENHE().put("1000").apply();
		}else if(list.get(position).getStepStauts().equals("1001")){
			holder.btn_check.setVisibility(View.GONE);
			holder.zt.setText("审核通过");
			infoFile.edit().statusOfSHENHE().put("1001").apply();
		}else if(list.get(position).getStepStauts().equals("1002")){
			holder.btn_check.setVisibility(View.GONE);
			holder.zt.setText("审核转移");
			infoFile.edit().statusOfSHENHE().put("10002").apply();
		}else if(list.get(position).getStepStauts().equals("1003")){
			holder.btn_check.setVisibility(View.GONE);
			holder.zt.setText("审核退回");
			infoFile.edit().statusOfSHENHE().put("1003").apply();
		}else if(list.get(position).getStepStauts().equals("2001")){
			holder.btn_check.setVisibility(View.GONE);
			holder.zt.setText("审核不通过");
			infoFile.edit().statusOfSHENHE().put("2001").apply();
		}else if(list.get(position).getStepStauts().equals("2003")){
			holder.btn_check.setVisibility(View.GONE);
			holder.zt.setText("被审核退回");
			infoFile.edit().statusOfSHENHE().put("2003").apply();
		}else{
			holder.btn_check.setVisibility(View.GONE);
			holder.zt.setText("未知状态");
			infoFile.edit().statusOfSHENHE().put("10000").apply();
		}
		/*ll1 = (LinearLayout) holder.ll1.findViewById(R.id.ll1);
		ll2 = (LinearLayout) holder.ll2.findViewById(R.id.ll2);*/
		
		final int position1 = position;
		
		
		
		if(list.get(position).getStepStauts().equals("1000")){
			holder.btn_check.setVisibility(View.VISIBLE);
		}
		else{
			holder.btn_check.setVisibility(View.GONE);
		}
		holder.btn_apply.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, Detail_Xzswcl_chakantabs_MainActivity.class);
				FlowID = list.get(position1).getFlowID();
				WFID  = list.get(position1).getWFID();
				Title=list.get(position1).getTitle();
				endTime=list.get(position1).getBrowseEndTime();
				FlowStepID = list.get(position1).getWFStepID();
				statusOfShenhe=list.get(position1).getStepStauts();
				infoFile.edit().flowid().put(Integer.valueOf(FlowID).intValue()).apply();
				infoFile.edit().WFStepID().put(Integer.valueOf(FlowStepID).intValue()).apply();
				infoFile.edit().statusOfSHENHE().put(statusOfShenhe).apply();
				infoFile.edit().FlowID().put(Integer.valueOf(FlowID).intValue()).apply();
				infoFile.edit().FlowStepID().put(Integer.valueOf(FlowStepID).intValue()).apply();
				infoFile.edit().WFID().put(Integer.valueOf(WFID).intValue()).apply();
				infoFile.edit().CurNode().put(list.get(position1).getCurStepNode()).apply();
				infoFile.edit().Title().put(Title).apply();
				infoFile.edit().endTime().put(endTime).apply();
				context.startActivity(intent);
			}
		});
		holder.btn_check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {				
				Intent intent = new Intent();
				intent.setClass(context, Detail_Xzswcl_chakantabs_MainActivity.class);
				FlowID = list.get(position1).getFlowID();
				WFID  = list.get(position1).getWFID();
				Title=list.get(position1).getTitle();
				endTime=list.get(position1).getBrowseEndTime();
				FlowStepID = list.get(position1).getWFStepID();
				statusOfShenhe=list.get(position1).getStepStauts();
				infoFile.edit().flowid().put(Integer.valueOf(FlowID).intValue()).apply();
				infoFile.edit().WFStepID().put(Integer.valueOf(FlowStepID).intValue()).apply();
				infoFile.edit().statusOfSHENHE().put(statusOfShenhe).apply();
				infoFile.edit().FlowID().put(Integer.valueOf(FlowID).intValue()).apply();
				infoFile.edit().FlowStepID().put(Integer.valueOf(FlowStepID).intValue()).apply();
				infoFile.edit().WFID().put(Integer.valueOf(WFID).intValue()).apply();
				infoFile.edit().CurNode().put(list.get(position1).getCurStepNode()).apply();
				infoFile.edit().Title().put(Title).apply();
				infoFile.edit().endTime().put(endTime).apply();
				context.startActivity(intent);
			}
		});

		return convertView;
	}
	
	class ViewHolder {
		TextView bt,wh,lwdw,fqr,fqsj,jzsj,zt,ll1,ll2,tv;
	
		Button btn_apply,btn_applyes,btn_check;
	}

}
