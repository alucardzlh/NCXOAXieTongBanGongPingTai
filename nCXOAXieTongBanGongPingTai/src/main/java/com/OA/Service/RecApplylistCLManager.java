package com.OA.Service;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyRecApply_xzswcl_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 行政收文处理
 */
public abstract class RecApplylistCLManager {
	
	private String TAG = "NOT";
	
	public void getRecCheckList(final Context paramActivity, final int paramInt,final String UserCode,
			final String SignCode,final int PageSize, final int PageNow ,final String Title,final String WenHao,
			final String LaiWenCompany,final String SendStartTime,final String SendEndTime ) {
		// TODO 自动生成的方法存根
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		BaseAsyncTask local = new BaseAsyncTask(paramActivity,true){
			String result = null;
			MyRecApply_xzswcl_Bean bean;
			
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// TODO 自动生成的方法存根
				try {
					result = downloadDBB(paramActivity,"GetRecCheckList", url, UserCode, SignCode, PageSize, PageNow, Title, 
								LaiWenCompany, WenHao, SendStartTime, SendEndTime);
		//			Log.i(TAG, "我得收文处理========="+result);
					if(result.contains("\"totalcount\":\"0\"")){
						bean = new MyRecApply_xzswcl_Bean();
						bean.setTotalcount("0");
						return Integer.valueOf(0);
					}
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<MyRecApply_xzswcl_Bean>() {}.getType();
					bean=gson.fromJson(result, type);
					Log.i(TAG, "我文列表===============" + bean);
					
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
					if (bean != null) {	
						handleResult.setiSuccess("success");
						Constants.list_recApply_xzswcl_bean = bean.getRows();
						Constants.COUNT_OF_LIST_MyRECAPPLY_XZSWCL_BEAN = bean.getRows().size();
					}
				} else if(paramInteger == 0){
					if (bean != null ) {
						handleResult.setiSuccess("success_0");
							Constants.COUNT_OF_LIST_MyRECAPPLY_XZSWCL_BEAN = 0;
					}
				} 	else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				RecApplylistCLManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		local.execute(1);
	}
	protected abstract void handlerLoginInfo(Context paramActivity,
			HandleResult handleResult, int paramInt);
		
}
