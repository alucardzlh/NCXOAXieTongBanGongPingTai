package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.R;
import com.OA.Activity.Tzgg_detail_MainActivity;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.Notice_Bean;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyNoticeList_LL_Adapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context = null;
	List<Notice_Bean> list;
	InfoFile_ infofile;
	public MyNoticeList_LL_Adapter(Context context,List<Notice_Bean> list){
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
					R.layout.tzgg_ll_item, null);
			holder.gwbt = (TextView) convertView
					.findViewById(R.id.gwbt);
			holder.ztc = (TextView) convertView
					.findViewById(R.id.ztc);
			holder.tglx = (TextView) convertView
					.findViewById(R.id.tglx);
			holder.shzt = (TextView) convertView
					.findViewById(R.id.shzt);
			holder.cjr = (TextView) convertView
					.findViewById(R.id.cjr);
			holder.cjsj = (TextView) convertView
					.findViewById(R.id.cjsj);
			holder.btn_yuedu = (Button) convertView
					.findViewById(R.id.btn_yuedu);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.gwbt.setText(list.get(position).getTitle());
		holder.ztc.setText(list.get(position).getSubject());
		holder.tglx.setText(list.get(position).getTypeName());
		holder.shzt.setText(list.get(position).getStatus().equals("2000")?"审核通过":"审核不通过");
		holder.cjr.setText(list.get(position).getCreaterName());
		holder.cjsj.setText(list.get(position).getCreateDate());
		final int position1 = position;
		holder.btn_yuedu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Constants.title = "通知公告浏览";
				infofile.edit().statusOfNotice().put(list.get(position1).getStatus()).apply();
				infofile.edit().NoticeID().put(Integer.valueOf(list.get(position1).getNoticeID()).intValue()).apply();
				context.startActivity(new Intent(context, Tzgg_detail_MainActivity.class).addFlags(1));
			}
		});
		return convertView;
	}
	
	
	class ViewHolder {
		TextView gwbt;
		TextView ztc;
		TextView tglx;
		TextView shzt;
		TextView cjr;
		TextView cjsj;
		Button btn_yuedu;
	}
}







