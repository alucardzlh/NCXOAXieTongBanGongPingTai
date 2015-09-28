package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.R;
import com.OA.Adapter.RecApply_Xzswdy_Adapter.ViewHolder;
import com.OA.Entity.Xzsw_Detail_Bdxxtwo_Bean;
import com.OA.View.MarqueeTextView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Xzsw_Bdxx_ListTwo_Adapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	List<Xzsw_Detail_Bdxxtwo_Bean> list;
	private Context context;
	private String elementType;
//	private List<String> list_data;
//	private String s;
//	private String[] ds;
//	private MarqueeTextView tv_custom;

	public Xzsw_Bdxx_ListTwo_Adapter(Context context, List<Xzsw_Detail_Bdxxtwo_Bean> list) {
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.list = list;
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
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.bdxx_textone_item, null);
			holder.xh = (TextView) convertView.findViewById(R.id.xh);
			holder.ssbm = (TextView) convertView.findViewById(R.id.ssbm);
			holder.llr = (TextView) convertView.findViewById(R.id.llr);
			holder.fbrq = (TextView) convertView.findViewById(R.id.fbrq);
			holder.llrq = (TextView) convertView.findViewById(R.id.llrq);
			holder.llIP = (TextView) convertView.findViewById(R.id.llIP);
			holder.zt = (TextView) convertView.findViewById(R.id.zt);
			convertView.setTag(holder);
	   }else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.xh.setText(list.get(position).getRow_number());
		holder.ssbm.setText(list.get(position).getDeptName());
		holder.llr.setText(list.get(position).getUserName());
		holder.fbrq.setText(list.get(position).getPublicTime());
		holder.llrq.setText(list.get(position).getBrowseDate());
		holder.llIP.setText(list.get(position).getBrowseIP());
		holder.zt.setText(list.get(position).getStatus().equals("100")?"待浏览":"已浏览");
		
		return convertView;
	}
	class ViewHolder{
		TextView xh,ssbm,llr,fbrq,llrq,llIP,zt;
	}
}
		
		
		
		
		
		
