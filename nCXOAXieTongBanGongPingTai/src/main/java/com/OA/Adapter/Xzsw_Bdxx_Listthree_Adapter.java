package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.R;
import com.OA.Entity.Xzsw_Detail_Bdxxfour_Bean;
import com.OA.Entity.Xzsw_Detail_Bdxxone_Bean;
import com.OA.Entity.Xzsw_Detail_Bdxxthree_Bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Xzsw_Bdxx_Listthree_Adapter extends BaseAdapter{

	private LayoutInflater inflater;
	private Context context = null;
	private List<Xzsw_Detail_Bdxxthree_Bean> list;
	//private List<Xzsw_Detail_Bdxxfour_Bean> list2;
	
	
	public Xzsw_Bdxx_Listthree_Adapter(Context context, List<Xzsw_Detail_Bdxxthree_Bean> list){
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.list = list;
//		this.list1 = list1;
//		this.list2 = list2;
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
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.bdxx_texttwo_item, null);
	//		holder.kllry = (TextView) convertView.findViewById(R.id.tv_kllry);
			holder.wllry = (TextView) convertView.findViewById(R.id.tv_wllry);
	//		holder.dllry = (TextView) convertView.findViewById(R.id.tv_dllry);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
//		holder.kllry.setText(list.get(position).getWFID());
		holder.wllry.setText(list.get(position).getWFID());
//		holder.dllry.setText(list.get(position).getWFID());
		
		return convertView;
	}
	class ViewHolder{
		TextView kllry,wllry,dllry;
	}
}
