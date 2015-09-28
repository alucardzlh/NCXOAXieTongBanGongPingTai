package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.R;
import com.OA.Entity.MyXzswdj_Bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyXzsw_xzswdj_Adapter extends BaseAdapter{

	private LayoutInflater inflater;
	private Context context = null;
	List<MyXzswdj_Bean> list;
	
	public MyXzsw_xzswdj_Adapter(Context context,List<MyXzswdj_Bean> list){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
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
			convertView = inflater.inflate(R.layout.xzsw_xzswdj_item, null);
			holder.tv = (TextView) convertView.findViewById(R.id.textview);
			holder.line = convertView.findViewById(R.id.v_line);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv.setText(list.get(position).getFlowName());
		
		return convertView;
	}
	
	class ViewHolder {
		TextView tv;
		View line;
	
	}

}
