package com.OA.Service;


import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.Xzsw_Detail_Bdxxfour_Bean;
import com.OA.Entity.Xzsw_Detail_Bdxxone_Bean;
import com.OA.Entity.Xzsw_Detail_Bdxxthree_Bean;
import com.OA.Entity.Xzsw_detai_Bdxxowo_Bean;
import com.OA.Entity.Xzsw_detai_Bdxxtwo_Bean;
import com.OA.Util.BaseAsyncTask;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public abstract class GetBrowsePeopleListManager {
	private String TAG = "NOT";
	
	
	public void getBrowseUser(final Context paramActivity, final int paramInt, final int WFID){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			Xzsw_Detail_Bdxxone_Bean bean;
			String result; //从服务器获取xml数据
			private Xzsw_Detail_Bdxxone_Bean result1;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDBS("GetBrowseUser", url, WFID);
					/*if(result.contains("\"totalcount\":\"0\"")){
						bean= new Xzsw_detai_Bdxxowo_Bean();
						bean.setTotalcount("0");
						return Integer.valueOf(0);
					}*/
					result1 = new Xzsw_Detail_Bdxxone_Bean();
					result1.setWFID(result);
				} catch (Exception e) {
					Log.e("", e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (result != null) {
						handleResult.setiSuccess("success");
						Constants.kllry =result1;
						Constants.COUNT_OF_LIST_XZSW_DETAIL_BDXX_BEAN = 0;
					}
				} else if(paramInteger == 0){
					if (result != null ) {
						handleResult.setiSuccess("success_0");
						Constants.COUNT_OF_LIST_XZSW_DETAIL_BDXX_BEAN = 0;
					}}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
				//	return;
				}
				GetBrowsePeopleListManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	/**
	 * 未浏览人员
	 * @param paramActivity
	 * @param paramInt
	 * @param WFID
	 */
	public void getNotBrowseUser(final Context paramActivity, final int paramInt, final int WFID){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			private Xzsw_Detail_Bdxxthree_Bean result1;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDBBS("GetNotBrowseUser", url, WFID);
					Log.i(TAG,"未浏览人员===============" + result);
					result1 = new Xzsw_Detail_Bdxxthree_Bean();
					result1.setWFID(result);

				} catch (Exception e) {
					Log.e("", e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (result != null) {
						handleResult.setiSuccess("success");
						Constants.wllry =result1;
					}
				} else if(paramInteger == 0){
					if (result != null ) {
						handleResult.setiSuccess("success_0");
					}}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
				//	return;
				}
				GetBrowsePeopleListManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	/**
	 * 超时浏览人员
	 * @param paramActivity
	 * @param paramInt
	 * @param WFID
	 */
	public void getOverBrowseUser(final Context paramActivity, final int paramInt, final int WFID){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			private Xzsw_Detail_Bdxxfour_Bean result1;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDBB("GetOverBrowseUser", url, WFID);
					Log.i(TAG,"超时浏览人员===============" + result);
					result1 = new Xzsw_Detail_Bdxxfour_Bean();
					result1.setWFID(result);

					 /*Gson gson = new Gson();
					 java.lang.reflect.Type type = new TypeToken<Xzsw_detai_Bdxxowo_Bean>(){}.getType();  
			         bean = gson.fromJson(result, type);*/
					

				} catch (Exception e) {
					Log.e("", e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (result != null) {
						handleResult.setiSuccess("success");
						Constants.dllry =result1;
					}
				} else if(paramInteger == 0){
					if (result != null ) {
						handleResult.setiSuccess("success_0");
					}}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
				//	return;
				}
				GetBrowsePeopleListManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}
