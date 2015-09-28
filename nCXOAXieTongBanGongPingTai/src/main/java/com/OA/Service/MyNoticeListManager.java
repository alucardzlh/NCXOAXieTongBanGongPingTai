package com.OA.Service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyNoticeQC_Bean;
import com.OA.Entity.MyNoticeSH_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class MyNoticeListManager {
	private String TAG="NOT";
	/**
	 * 获取通知公告起草列表
	 * 
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode
	 *            用户名
	 * @param SignCode
	 *            加验证重码
	 * @param PageSize
	 *            每页条数
	 * @param PageNow
	 *            当前页
	 * @param Title
	 *            公文Title
	 */
	public void getNoticeListQC(final Context paramActivity,
			final int paramInt, final int PageSize, final int PageNow,
			final String UserCode, final String SignCode, final String Title) {
		final String url = paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址

		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null; // 从服务器获取数据
			MyNoticeQC_Bean bean;
			

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result = downloadDB(paramActivity, "GetNoticeList", url,
							PageSize, PageNow, UserCode, SignCode, Title);
					Log.i(TAG, "我得收文列表===============" + result);
					if(result.contains("\"totalcount\":\"0\"")){
						bean = new MyNoticeQC_Bean();
						bean.setTotalcount("0");
						return Integer.valueOf(0);
					}
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<MyNoticeQC_Bean>() {
					}.getType();
					bean = gson.fromJson(result, type);
				} catch (Exception e) {
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (bean == null || result.equals("error")) {
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
						Constants.list_notice_bean = bean.getRows();
						Constants.COUNT_OF_LIST_NOTICE_BEAN = bean.getRows().size();
					}
				}else if(paramInteger == 0){
					if (bean != null ) {
						handleResult.setiSuccess("success_0");
							Constants.COUNT_OF_LIST_NOTICE_BEAN = 0;
					}
				} 
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				MyNoticeListManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}

	/**
	 * 获取通知公告审核列表
	 * 
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode
	 *            用户名
	 * @param SignCode
	 *            加验证重码
	 * @param PageSize
	 *            每页条数
	 * @param PageNow
	 *            当前页
	 * @param Title
	 *            公文Title
	 */
	public void getNoticeListSH(final Context paramActivity,
			final int paramInt, final int PageSize, final int PageNow,
			final String UserCode, final String SignCode, final String Title) {
		final String url = paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址

		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null; // 从服务器获取数据
			MyNoticeSH_Bean bean = new MyNoticeSH_Bean();
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result = downloadDB(paramActivity, "GetNoticeCheckList", url,
							PageSize, PageNow, UserCode, SignCode, Title);
					Log.i(TAG, "我得列表===============" + result);
					if(result.contains("\"totalcount\":\"0\"")){
						bean = new MyNoticeSH_Bean();
						bean.setTotalcount("0");
						return Integer.valueOf(0);
					}
						Gson gson = new Gson();
						java.lang.reflect.Type type = new TypeToken<MyNoticeSH_Bean>() {
						}.getType();
						bean = gson.fromJson(result, type);	
				} catch (Exception e) {
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (bean == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
				//	Toast.makeText(paramActivity, "链接服务器成功！", Toast.LENGTH_LONG)
				//			.show();
					if (bean != null ) {
						handleResult.setiSuccess("success");
							Constants.list_notice_sh_bean = bean.getRows();
							Constants.COUNT_OF_LIST_NOTICE_BEAN = bean.getRows().size();
					}
				}else if(paramInteger == 0){
					if (bean != null ) {
						handleResult.setiSuccess("success_0");
							Constants.COUNT_OF_LIST_NOTICE_BEAN = 0;
					}
				}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				MyNoticeListManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}