package com.OA.Service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyRecApply_xzswdy_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * 行政收文待阅
 * @author Administrator
 *
 */
public abstract class RecApplyListDYManager {
	private String TAG = "NOT";
	
	public void getRecBrowseList1(final Context paramActivity, final int paramInt,final String UserCode,
			final String SignCode,final int PageSize, final int PageNow ,final String Status,final String Title,final String WenHao,
			final String LaiWenCompany,final String SendStartTime,final String SendEndTime ){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			MyRecApply_xzswdy_Bean bean;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDBBS(paramActivity,"GetRecBrowseList", url, UserCode, SignCode, PageSize, PageNow, Status, Title, 
							LaiWenCompany, WenHao, SendStartTime, SendEndTime);
					Log.i(TAG, "行政收文待阅===============" + result);
					if(result.contains("\"totalcount\":\"0\"")){
						bean = new MyRecApply_xzswdy_Bean();
						bean.setTotalcount("0");
						return Integer.valueOf(0);
					}
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<MyRecApply_xzswdy_Bean>() {}.getType();
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
						Constants.list_recApply_xzswdy_bean = bean.getRows();
						Constants.COUNT_OF_LIST_MyRECAPPLY_XZSWDY_BEAN = bean.getRows().size();
					}
				} else if(paramInteger == 0){
					if (bean != null ) {
						handleResult.setiSuccess("success_0");
							Constants.COUNT_OF_LIST_MyRECAPPLY_XZSWDY_BEAN = 0;
					}
				} 	else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				RecApplyListDYManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}

	protected abstract void handlerLoginInfo(Context paramActivity,
			HandleResult handleResult, int paramInt);
}
