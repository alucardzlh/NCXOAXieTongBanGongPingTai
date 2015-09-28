package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.R;
import com.OA.Entity.MyXzfwzg_Bean;
import com.OA.Entity.Wdxzfw_Bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyXzfw_xzfwzg_Adapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context = null;
	List<MyXzfwzg_Bean> list;
	public MyXzfw_xzfwzg_Adapter(Context context,List<MyXzfwzg_Bean> list){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
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
					R.layout.xzfw_xzfwzg_item, null);
			holder.tv= (TextView) convertView
					.findViewById(R.id.textview);
			holder.line=convertView.findViewById(R.id.v_line);
		
			convertView.setTag(holder);

		} else {
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
