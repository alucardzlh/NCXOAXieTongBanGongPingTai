package com.OA.Service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Entity.HandleResult;
import com.OA.Util.BaseAsyncTask;

public abstract class MeetingCheckListManager {
	private String TAG="NOT";
	/**
	 * 获取会议审核
	 * @param paramActivity
	 * @param paramInt
	 * @param PageSize 一页显示数目
	 * @param PageNow	当前页
	 * @param UserCode	当前登录账号
	 * @param SignCode	安全加密码	
	 * @param RoomName	会议室：为了查询需要
	 * @param StarTime	开始时间： 为了查询需要	
	 * @param EndTime	结束时间： 为了查询需要
	 */
	public void getMeetingCheckListManager(final Context paramActivity,final int paramInt,
			final int PageSize, final int PageNow ,final String UserCode,
			final String SignCode,final String RoomName,final String StarTime,
			final String EndTime){
//		final String url ="http://ncxceshioa.dichuang.cc/WebServices/AppService.asmx?WSDL";
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDB("GetMeetingList", url, PageSize, PageNow, UserCode, SignCode, RoomName, StarTime, EndTime);
				} catch (Exception e) {
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
				
			}
			
		

			@Override
			protected void onPostExecute(Integer paramInteger) {
				// TODO 自动生成的方法存根
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					Toast.makeText(paramActivity, "链接服务器成功！", Toast.LENGTH_LONG)
					.show();
					
				} else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				MeetingCheckListManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}