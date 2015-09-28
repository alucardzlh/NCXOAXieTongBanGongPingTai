package com.OA.Service;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.Xzsw_detai_Bdxxtwo_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class GetSWBrowsePeopleListManager {
	private String TAG="NOT";
	
	public void getBrowsePeopleList(final Context paramActivity,final int paramInt,
			final int PageSize, final int PageNow,final int WFID,
			final String Status){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,false){
			String result=null; //从服务器获取xml数据
			Xzsw_detai_Bdxxtwo_Bean bean;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDBS("GetBrowseUserList", url,PageSize, PageNow, WFID, Status);
					Log.i(TAG,"表单数据===============" + result);
					if(result.contains("\"totalcount\":\"0\"")){
						bean= new Xzsw_detai_Bdxxtwo_Bean();
						bean.setTotalcount("0");
						return Integer.valueOf(0);
					}
					 Gson gson = new Gson();
					 java.lang.reflect.Type type = new TypeToken<Xzsw_detai_Bdxxtwo_Bean>(){}.getType();  
			         bean = gson.fromJson(result, type);  

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
					if (bean != null) {
						handleResult.setiSuccess("success");
						 Constants.listSWTWO_Detail_bdxx_bean=bean.getRows();
						 Constants.COUNT_OF_LIST_XZSW_DETAIL_BDXX_BEAN= bean.getRows().size();
					}
				} else if(paramInteger == 0){
					if (bean != null ) {
						handleResult.setiSuccess("success_0");
						Constants.COUNT_OF_LIST_XZSW_DETAIL_BDXX_BEAN = 0;
					}}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				GetSWBrowsePeopleListManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
	
}
