package com.OA.Service;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *补报参会人员
 */
public abstract class BuBaoJoinWorkerManager {
	private String TAG="NOT";




	/**
	 * 补报参会人员
	 * @param paramActivity
	 * @param paramInt
	 * @param RoomLogID
	 * @param DeptID
	 * @param UserPostName
	 * @param JoinWorkerName
	 * @param JoinWorkerNames
	 * @param JoinWorkerCodes
	 * @param UserMobile
	 * @param UserCode
	 * @param isSendMsg
	 * @param SignCode
	 */
	public void getBuBaoJoinWorkerManager(final Context paramActivity,final int paramInt ,final int RoomLogID,final int DeptID,final String UserPostName,final String JoinWorkerName,final String JoinWorkerNames ,final String JoinWorkerCodes ,final String UserMobile,final String UserCode,final Boolean isSendMsg,final String  SignCode){
//		final String url ="http://ncxceshioa.dichuang.cc/WebServices/AppService.asmx?WSDL";
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			MyMeetingDetail_Bean bean;
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
				
//					result=downloadDB("BuBaoJoinWorker",url, RoomLogID, DeptID, UserCode, SignCode);
					result=downloadDB("BuBaoJoinWorker", url, RoomLogID, DeptID, UserPostName, JoinWorkerName, JoinWorkerNames, JoinWorkerCodes, UserMobile, UserCode, isSendMsg, SignCode);
					JSONObject jsonObject = new JSONObject(result);
					String str = jsonObject.getString("status");
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<MyMeetingDetail_Bean>() {
					}.getType();
					bean=gson.fromJson(result, type);
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (bean == null || result == null || result.equals("error")) {
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
						Constants.mymeetingdetail_bean = bean;
					}
				}else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				BuBaoJoinWorkerManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}