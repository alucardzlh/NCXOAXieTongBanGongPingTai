package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.Hyxx_detail_MainActivity;
import com.OA.Activity.R;
import com.OA.Data.InfoFile_;
import com.OA.Entity.Meeting_hyssq_Bean;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyMetting_Hyssq_Adapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context = null;
	List<Meeting_hyssq_Bean> list;
	InfoFile_ infofile;
	public MyMetting_Hyssq_Adapter(Context context,List<Meeting_hyssq_Bean> list){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
		infofile = new InfoFile_(context);
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
					R.layout.hyxx_hyssq_item, null);
			holder.lb = (TextView) convertView
					.findViewById(R.id.lb);
			holder.mc = (TextView) convertView
					.findViewById(R.id.mc);
			holder.dz = (TextView) convertView
					.findViewById(R.id.dz);
					holder.rnrs = (TextView) convertView
					.findViewById(R.id.rnrs);
					holder.tyy = (TextView) convertView
					.findViewById(R.id.tyy);
			holder.bz = (TextView) convertView
					.findViewById(R.id.bz);
			holder.zt = (TextView) convertView
					.findViewById(R.id.zt);
			holder.btn_apply = (Button) convertView
					.findViewById(R.id.btn_apply);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final int position1 = position;
		holder.lb.setText(list.get(position).getTypeName());
		holder.mc.setText(list.get(position).getRoomName());
		holder.dz.setText(list.get(position).getPosition());
		holder.rnrs.setText(list.get(position).getContainPeople	());
		holder.tyy.setText(list.get(position).getProjector());
		holder.bz.setText(list.get(position).getMemo());
		holder.zt.setText(list.get(position).getStatus().equals("1000")?"可用":"占用或维护");
		
		holder.btn_apply.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				infofile.edit().typeOfHyxxBottomButton().put(2).apply();
			//	infofile.edit().RoomLogID().put(Integer.valueOf(list.get(position1).getRoomLogID()).intValue()).apply();				
				infofile.edit().typeOfMeeting().put(list.get(position1).getTypeName()).apply();
				infofile.edit().addressOfMeeting().put(list.get(position1).getPosition()).apply();
				infofile.edit().nameOfMeeting().put(list.get(position1).getRoomName()).apply();
				infofile.edit().rnrsOfMeeting().put(list.get(position1).getContainPeople()).apply();
				infofile.edit().ROOMID().put(Integer.valueOf(list.get(position1).getRoomID()).intValue()).apply();
				context.startActivity(new Intent(context, Hyxx_detail_MainActivity.class));
			}
		});
		return convertView;
	}

	class ViewHolder {
		TextView lb;
		TextView mc;
		TextView dz;
		TextView rnrs;
		TextView tyy;
		TextView bz;
		TextView zt;
		Button btn_apply;
	}
	
}
