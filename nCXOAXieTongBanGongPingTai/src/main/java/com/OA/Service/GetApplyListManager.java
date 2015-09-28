package com.OA.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyXzswdj_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class GetApplyListManager {
private String TAG="NOT";


	
	/**
	 * 获取发文申请列表
	
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode  用户名
	 * @param SignCode  安全加密码
	 * @param KindID	收发文类别，发文2000，收文4000
	 */
	public void getApplyListManager(final Context paramActivity,final int paramInt,final String UserCode,final String SignCode,final int RoleID,final int KindID){
//		final String url ="http://192.168.1.254:8099/WebServices/AppService.asmx?WSDL";
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			
			String result=null; //从服务器获取xml数据
			private List<MyXzswdj_Bean> list;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
				
					result=downloadDB("GetSendApplyList",url,UserCode,SignCode,RoleID,KindID);
					Type listType = new TypeToken<ArrayList<MyXzswdj_Bean>>(){}.getType();  
			        Gson gson = new Gson();  
			        list = gson.fromJson(result, listType);  
			        Constants.lis_xzsw_xzswdj_bean=list;

				} catch (Exception e) {
				}
//				 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (list == null || result.equals("error")) {
					return Integer.valueOf(-1);
					
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
						if (list != null) {
							handleResult.setiSuccess("success");
							 Constants.lis_xzsw_xzswdj_bean=list;
						}
					} else if(paramInteger == 0){
						if (list != null ) {
							handleResult.setiSuccess("success_0");
						}}
					else if (paramInteger == -1) {// 链接服务器失败
						Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
								.show();
						handleResult.setiSuccess("fail");
						return;
					}
				GetApplyListManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}
