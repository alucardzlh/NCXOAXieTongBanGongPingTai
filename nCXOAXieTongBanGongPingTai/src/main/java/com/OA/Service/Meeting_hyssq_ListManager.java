package com.OA.Service;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.Meeting_hyssh_Bean;
import com.OA.Entity.MyMeeting_Bean;
import com.OA.Entity.MyMeeting_hyssq_Bean;
import com.OA.Entity.MyNoticeQC_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 会议室申请
 */
public abstract class Meeting_hyssq_ListManager {
private String TAG="Meeting_hyssq_ListManager";
	/**
	 * 获取会议室申请列表
	 * @param paramActivity
	 * @param paramInt
	 * @param PageSize 一页显示数目
	 * @param PageNow	当前页
	 * @param UserCode	当前登录账号
	 * @param SignCode	安全加密码	
	 * @param RoomName	会议室：为了查询需要
	 */
	public void getMeeting_hyssq_ListManager(final Context paramActivity,final int paramInt,
			final int PageSize, final int PageNow ,final String UserCode,
			final String SignCode, final String RoomName ,final String TypeID){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			MyMeeting_hyssq_Bean bean;
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDBB(paramActivity,"GetMeetingApplyList", url, PageSize, PageNow, UserCode, SignCode, RoomName, TypeID);
					if(result.contains("\"totalcount\":\"0\"")){
						bean = new MyMeeting_hyssq_Bean();
						bean.setTotalcount("0");
						return Integer.valueOf(0);
					}
					/*JSONObject jsonObject = new JSONObject(result);
					String str = jsonObject.getString("totalcount");
					JSONArray  rows= jsonObject.getJSONArray("rows");*/
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<MyMeeting_hyssq_Bean>() {
					}.getType();
					bean=gson.fromJson(result, type);
				} catch (Exception e) {
					//Log.e(TAG, e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (bean == null ||result.equals("error")) {
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
					if (bean != null) {
						handleResult.setiSuccess("success");
						Constants.list_meeting_hyssq_bean = bean.getRows();
						Constants.COUNT_OF_LIST_MEETING_BEAN = bean.getRows().size();
					}
				}
				else if(paramInteger == 0){
					if (bean != null ) {
						handleResult.setiSuccess("success_0");
							Constants.COUNT_OF_LIST_MEETING_BEAN = 0;
					}
				} else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				Meeting_hyssq_ListManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}