package com.OA.Activity;


import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Service.LoginOAManager;
import com.OA.Service.MeetingCheckListManager;
import com.OA.Service.MeetingListManager;
import com.OA.Service.MyRecApplyListManager;
import com.OA.Service.MySendApplyListManager;
import com.OA.Service.RecBrowseListManager;
import com.OA.Service.RecCheckListManager;
import com.OA.Service.SendApplyListManager;
import com.OA.Service.SendBrowseListManager;
import com.OA.Service.SendCheckListManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends BaseActivity implements OnClickListener{
	private String TAG="MainActivity";
	InfoFile_ info;

	ImageView hyxx,xzfw,xzsw,tzgg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ncxwszw);
		initViewAndListener();
//		managerService.getLoginOAManager(getBaseContext(), 1, null, null, null);
//		getSendService.GetSendApplyListManager(getBaseContext(), 1, null, null,400);
//		getSendCheckList.getSendCheckListManager(getBaseContext(), 1, null, null, 1, 1, null, null, null, null, null);
//		getSendBrowseList.GetSendBrowseListManager(getBaseContext(), 1, null, null, 1, 1,null, null, null, null, null, null);
//		getMySendApplyList.getMySendApplyListManager(getBaseContext(), 1, null, null, 1, 1, null,null, null, null, null, null);
//		getRecCheckList.getRecCheckListManager(getBaseContext(), 1, null, null, 1, 1, null, null, null, null, null);
//		getMeetingList.getMeetingListManager(getBaseContext(), 1, 1, 1, null, null, null, null, null);
//		getRecBrowseList.GetRecBrowseListManager(getBaseContext(), 1, null, null, 1, 1, null, null, null, null, null, null);
//		getMyRecApplyList.getGetMyRecApplyList(getBaseContext(), 1, null, null, 1, 1, null, null, null, null, null, null);
//		getMeetingCheckList.getMeetingCheckListManager(getBaseContext(), 1, 10, 1, null, null, null, null, null);
//		getApplyMtRoomManager.getApplyMtRoomManager(getBaseContext(), 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1, 1, 1, 1, 1);
	}

	private void initViewAndListener(){
		hyxx = (ImageView)findViewById(R.id.hyxx);
		xzfw = (ImageView)findViewById(R.id.xzfw);
		xzsw = (ImageView)findViewById(R.id.xzsw);
		tzgg = (ImageView)findViewById(R.id.tzgg);
		hyxx.setOnClickListener(this);
		xzfw.setOnClickListener(this);
		xzsw.setOnClickListener(this);
		tzgg.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.hyxx:
			Constants.Module = "Meeting";
			startActivity(new Intent(MainActivity.this, HyxxMainActivity.class));
			break;
		case R.id.xzfw:
			Constants.Module = "ArchivesSend";
			startActivity(new Intent(MainActivity.this, XzfwMainActivity.class));
			break;
		case R.id.xzsw:
			Constants.Module = "ArchivesRec";
			startActivity(new Intent(MainActivity.this, XzswMainActivity.class));
			break;
		case R.id.tzgg:
			Constants.Module = "ArchivesNotice";
			startActivity(new Intent(MainActivity.this, TzggMainActivity.class));
			break;
		default:
			break;
		}
		
	}

	private LoginOAManager managerService = new LoginOAManager() {
	
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
		}
	};
	private SendApplyListManager getSendService = new SendApplyListManager() {
		
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
		}
	};
	private SendCheckListManager getSendCheckList = new SendCheckListManager() {
		
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
		}
	};
	private SendBrowseListManager getSendBrowseList = new SendBrowseListManager() {
		
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
		}
	};
	private MySendApplyListManager getMySendApplyList = new MySendApplyListManager() {
		
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
		}
	};
	private RecCheckListManager getRecCheckList = new RecCheckListManager() {
		
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
		}
	};
	private MeetingListManager getMeetingList = new MeetingListManager() {
		
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
		}
	};
	private RecBrowseListManager getRecBrowseList = new RecBrowseListManager() {
		
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
		}
	};
	private MyRecApplyListManager getMyRecApplyList = new MyRecApplyListManager() {
		
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
		}
	};
	private MeetingCheckListManager getMeetingCheckList = new MeetingCheckListManager() {
		
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
		}
	};
}
