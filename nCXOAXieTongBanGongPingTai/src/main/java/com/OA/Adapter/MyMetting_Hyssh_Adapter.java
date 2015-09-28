package com.OA.Adapter;

import java.util.List;

import com.OA.Activity.Hyxx_detail_MainActivity;
import com.OA.Activity.R;
import com.OA.Data.InfoFile_;
import com.OA.Entity.Meeting_hyssh_Bean;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyMetting_Hyssh_Adapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context = null;
	List<Meeting_hyssh_Bean> list;
	InfoFile_ infofile;

	public MyMetting_Hyssh_Adapter(Context context,
			List<Meeting_hyssh_Bean> list) {
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
			convertView = inflater.inflate(R.layout.hyxx_hyssh_item, null);
			holder.hys = (TextView) convertView.findViewById(R.id.hys);
			holder.sqsj = (TextView) convertView.findViewById(R.id.sqsj);
			holder.kssj = (TextView) convertView.findViewById(R.id.kssj);
			holder.jssj = (TextView) convertView.findViewById(R.id.jssj);
			holder.zcr = (TextView) convertView.findViewById(R.id.zcr);
			holder.sqr = (TextView) convertView.findViewById(R.id.sqr);
			holder.hyyt = (TextView) convertView.findViewById(R.id.hyyt);
			holder.sqzt = (TextView) convertView.findViewById(R.id.sqzt);
			holder.btn_shenhe = (Button) convertView
					.findViewById(R.id.btn_shenhe);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.hys.setText(list.get(position).getRoomName());
		holder.sqsj.setText(list.get(position).getCreateTime());
		holder.kssj.setText(list.get(position).getMeetingStart());
		holder.jssj.setText(list.get(position).getMeetingEnd());
		holder.zcr.setText(list.get(position).getHost());
		holder.sqr.setText(list.get(position).getApplicant());
		holder.hyyt.setText(list.get(position).getMeetingTitle());
		if(list.get(position).getStatus().equals("2000")){
			holder.sqzt.setText("通过");
		}else if(list.get(position).getStatus().equals("1000")){
			holder.sqzt.setText("待审核");
		}else if(list.get(position).getStatus().equals("1002")){
			holder.sqzt.setText("未通过");
		}else{
			holder.sqzt.setText("操作失败");
		}
		final int position1 = position;
		holder.btn_shenhe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (list.get(position1).getStatus().equals("1000")) {
					infofile.edit().typeOfHyxxBottomButton().put(3).apply();
					infofile.edit()
							.StepDataID()
							.put(Integer.valueOf(
									list.get(position1).getWFStepID())
									.intValue()).apply();
					infofile.edit()
							.RoomLogID()
							.put(Integer.valueOf(
									list.get(position1).getRoomLogID())
									.intValue()).apply();
					context.startActivity(new Intent(context,
							Hyxx_detail_MainActivity.class));
				} else {
					Toast.makeText(context, "此申请无法审核！", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});
		return convertView;
	}

	class ViewHolder {
		TextView hys;
		TextView sqsj;
		TextView kssj;
		TextView jssj;
		TextView zcr;
		TextView sqr;
		TextView hyyt;
		TextView sqzt;
		Button btn_shenhe;
	}

}
