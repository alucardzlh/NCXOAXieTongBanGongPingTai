package com.OA.Activity;

import java.util.List;

import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Entity.MyMeetingDetail_bbchry_Bean;
import com.OA.Entity.MyMettingDetail_bbchry__list_bean;
import com.OA.Service.AddMtingWorkerListManager;
import com.OA.Service.DropOutMtingManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail_Wdhytg_xiangxi_Fragment2 extends Fragment {
	private View view;
	InfoFile_ infoFile_;
	Boolean isSendMsg;
	int joinID;
	String joinWorkerCode;
	private ListView lv_base;
	private List<MyMettingDetail_bbchry__list_bean> list;
	private MyMeetingDetail_bbchry_Bean bean;
	private MyMeeting_Wdhytz_bbchry_Adapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.xzfw_wdhytg_fragment_2, container,
				false);
		initViewAndListener();
		addMtiing.getAddMtingWorkerListManager(getActivity(), 1,
				getRoomLogID(), Integer.valueOf(infoFile_.DeptID().get()),
				infoFile_.infoUsername().get(), infoFile_.infoUserType().get());

		return view;
	}

	public int getRoomLogID() {
		int RoomLogID = Integer.valueOf(getArguments().getString("RoomLogID"));
		return RoomLogID;

	}

	private void initViewAndListener() {
		infoFile_ = new InfoFile_(getActivity());
		lv_base = (ListView) view.findViewById(R.id.lv_base);

	}

	AddMtingWorkerListManager addMtiing = new AddMtingWorkerListManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess().equals("success")) {
				bean = Constants.mymeetingdetail_bbchry_bean;
				if (bean != null && bean.getStatus().equals("2000")) {
					list = bean.getList();
					adapter = new MyMeeting_Wdhytz_bbchry_Adapter(context, list);
					lv_base.setAdapter(adapter);
				} else if (bean != null && bean.getStatus().equals("0000")) {

				}
			}
		}
	};

	static Detail_Wdhytg_xiangxi_Fragment2 newInstance(String s) {
		Detail_Wdhytg_xiangxi_Fragment2 newFragment = new Detail_Wdhytg_xiangxi_Fragment2();
		return newFragment;
	}

	public class MyMeeting_Wdhytz_bbchry_Adapter extends BaseAdapter {
		private LayoutInflater inflater;
		private Context context = null;
		List<MyMettingDetail_bbchry__list_bean> list;
		InfoFile_ infofile_;
		private String tag = "MyMeeting_Wdhytz_bbchry_Adapter";

		public MyMeeting_Wdhytz_bbchry_Adapter(Context context,
				List<MyMettingDetail_bbchry__list_bean> list) {
			this.context = context;
			this.list = list;
			inflater = LayoutInflater.from(context);
			infofile_ = new InfoFile_(context);
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
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(
						R.layout.hyxx_wdhytz_xiangxi_bbchry_item, null);
				holder.btn_bcj = (Button) convertView
						.findViewById(R.id.btn_bcj);
				holder.tv_chry = (TextView) convertView
						.findViewById(R.id.tv_chry);
				holder.tv_zw = (TextView) convertView.findViewById(R.id.tv_zw);
				holder.tv_lxdh = (TextView) convertView
						.findViewById(R.id.tv_lxdh);
				holder.tv_ssbm = (TextView) convertView
						.findViewById(R.id.tv_ssbm);
				holder.cb_send = (CheckBox) convertView
						.findViewById(R.id.cb_send);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv_chry.setText(list.get(position).getJoinWorkerName());
			holder.tv_ssbm.setText(list.get(position).getUserDeptName());
			holder.tv_lxdh.setText(list.get(position).getUserMobile());
			holder.tv_zw.setText(list.get(position).getUserPostName());

			// final int position1 = position;
			isSendMsg = holder.cb_send.isChecked();
			// joinID = Integer.valueOf(list.get(position1).getJoinID());
			// joinWorkerCode = list.get(position1).getJoinWorkerCode();
			holder.btn_bcj.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showInfo();

				}

				// listview中点击按键弹出对话框
				public void showInfo() {
					new AlertDialog.Builder(getActivity())
							.setTitle("我的提示")
							.setMessage("确定要不参加吗？")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {

											dropOutMting
													.getDropOutMtingManager(
															context,
															position,
															Integer.valueOf(list
																	.get(position)
																	.getJoinID()),
															list.get(position)
																	.getJoinWorkerCode(),
															isSendMsg,
															infofile_
																	.infoUsername()
																	.get(),
															infofile_
																	.infoUserType()
																	.get());

										}
									})
							.setNegativeButton("关闭",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									}).show();
				}
			});

			return convertView;
		}

		class ViewHolder {
			TextView tv_chry, tv_ssbm, tv_lxdh, tv_zw;
			CheckBox cb_send;
			Button btn_bcj;
		}

		DropOutMtingManager dropOutMting = new DropOutMtingManager() {

			private MyMeetingDetail_Bean bean;

			private Handler handler;

			@Override
			protected void handlerLoginInfo(Context context,
					HandleResult handleResult, int position) {
				if (handleResult.getiSuccess().equals("success")) {
					bean = Constants.mymeetingdetail_bean;
					if (bean != null && bean.getStatus().equals("2000")) {
						list.remove(position);
						MyMeeting_Wdhytz_bbchry_Adapter.this
								.notifyDataSetChanged();
					} else if (bean != null && bean.getStatus().equals("5000")) {
						Toast.makeText(context, "操作失败", Toast.LENGTH_LONG)
								.show();
					} else if (bean != null && bean.getStatus().equals("0000")) {
						Toast.makeText(context, "安全验证未通过", Toast.LENGTH_LONG)
								.show();
					}
				}

			}
		};

	}

}
