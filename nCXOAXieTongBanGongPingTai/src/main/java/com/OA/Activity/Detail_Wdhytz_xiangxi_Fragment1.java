package com.OA.Activity;

import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Service.Hyxx_Detail_ShManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Detail_Wdhytz_xiangxi_Fragment1 extends Fragment {
	private View view;
 MyMeetingDetail_Bean bean;
	Context context;
	private TextView tv_hyzl;
	private TextView tv_hys;
	private TextView tv_dz;
	private TextView tv_rnrs;
	private TextView kssj;
	private TextView jssj;
	private TextView tv_zcr;
	private TextView tv_jlr;
	private TextView tv_hyyt;
	private TextView tv_cxrs;
	private TextView tv_xks;
	private TextView tv_sqr;
	private TextView tv_cxry;
	private TextView tv_hynr;
	private TextView tv_bz;
	private TextView fjll;
	private CheckBox cb_send;
	private InfoFile_ infoFile_;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.hyxx_wdhytz_xiangxi_fragment_1,
				container, false);
		initView();
		return view;
	}
	public int getRoomLogID(){
		int RoomLogID=Integer.valueOf(getArguments().getString("RoomLogID"));
		return RoomLogID;
		
	}
	private void initView() {
		infoFile_ = new InfoFile_(getActivity());
		hyxx_detail.getMeeting_Detail(getActivity(), 1, infoFile_.infoUsername().get(), infoFile_.infoUserType().get()
				,getRoomLogID());
		tv_hyzl = (TextView) view.findViewById(R.id.tv_hyzl);
		tv_hys = (TextView) view.findViewById(R.id.tv_hys);
		tv_dz = (TextView) view.findViewById(R.id.tv_dz);
		tv_rnrs = (TextView) view.findViewById(R.id.tv_rnrs);
		kssj = (TextView) view.findViewById(R.id.kssj);
		jssj = (TextView) view.findViewById(R.id.jssj);
		tv_zcr = (TextView) view.findViewById(R.id.tv_zcr);
		tv_jlr = (TextView) view.findViewById(R.id.tv_jlr);
		tv_hyyt = (TextView) view.findViewById(R.id.tv_hyyt);
		tv_cxrs = (TextView) view.findViewById(R.id.tv_cxrs);
		tv_xks = (TextView) view.findViewById(R.id.tv_xks);
		tv_sqr = (TextView) view.findViewById(R.id.tv_sqr);
		tv_cxry = (TextView) view.findViewById(R.id.tv_cxry);
		tv_hynr = (TextView) view.findViewById(R.id.tv_hynr);
		tv_bz = (TextView) view.findViewById(R.id.tv_bz);
		fjll = (TextView) view.findViewById(R.id.fjll);
		fjll.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		cb_send = (CheckBox) view.findViewById(R.id.cb_send);
		fjll.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (bean != null && bean.getObj().getFileList() != null
						&& bean.getObj().getFileList().size() != 0) {
					Constants.list_files = bean.getObj().getFileList();
					startActivityForResult(new Intent(
							Detail_Wdhytz_xiangxi_Fragment1.this.getActivity(),
							UpLoadFile_Activity.class).addFlags(2000), 1000);
				} else {
					Toast.makeText(Detail_Wdhytz_xiangxi_Fragment1.this.getActivity(), "无附件!",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	private void initData() {
			tv_hys.setText( bean.getObj().getRoomName() );
			tv_dz.setText( bean.getObj().getPosition());
			tv_rnrs.setText( bean.getObj().getContainPeople() );
			kssj.setText(bean.getObj().getMeetingStart());
			jssj.setText( bean.getObj().getMeetingEnd());
			tv_zcr.setText( bean.getObj().getHost());
			tv_jlr.setText( bean.getObj().getRecorderName());
			tv_hyyt.setText( bean.getObj().getMeetingTitle());
			tv_cxrs.setText(bean.getObj().getInvitingCount());
			tv_xks.setText(bean.getObj().getMatCard());
			tv_sqr.setText( bean.getObj().getApplicant() );
			tv_cxry.setText( bean.getObj().getInvitingName());
			tv_hynr.setText(bean.getObj().getMeetingContent());
			tv_bz.setText(bean.getObj().getMemo());
			if(bean.getObj().getSendType().equals("q")){
				cb_send.setChecked(true);
			}else{
				cb_send.setChecked(false);
			}
				
	}
private Hyxx_Detail_ShManager hyxx_detail = new Hyxx_Detail_ShManager() {
		
		@Override
		protected void handlerLoginInfo(Context context, HandleResult handleResult,
				int paramInt) {
			
				if(handleResult.getiSuccess().equals("success")){
				bean = Constants.mymeetingdetail_bean;
				initData();
			}
		}
	};

	static Detail_Wdhytz_xiangxi_Fragment1 newInstance(String s) {
		Detail_Wdhytz_xiangxi_Fragment1 newFragment = new Detail_Wdhytz_xiangxi_Fragment1();
		return newFragment;
	}
	
}
