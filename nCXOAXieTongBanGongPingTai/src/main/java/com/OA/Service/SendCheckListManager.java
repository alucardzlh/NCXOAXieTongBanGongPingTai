package com.OA.Service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyXzfwsh_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 发文审核列表
 */
public abstract class SendCheckListManager {
private String TAG="NOT";


	
	/**
	 * 获取发文审核列表
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode  用户名
	 * @param SignCode 加验证重码
	 * @param PageSize 每页条数
	 * @param PageNow  当前页
	 * @param Title  公文Title
	 * @param WenHao 文号/  LaiWenCompany来文单位（收文审核）
	 * @param NiGaoCompany 拟稿单位   / WenHao 文号（收文审核）
	 * @param SendStartTime 发文日期
	 * @param SendEndTime 发文日期
	 */
	public void getSendCheckListManager(final Context paramActivity,final int paramInt,
			final String UserCode, final String SignCode,final int PageSize,
			final int PageNow,final String Title,final String WenHao,
			final String NiGaoCompany,final String SendStartTime,final String SendEndTime){
//		final String url ="http://ncxceshioa.dichuang.cc/WebServices/AppService.asmx?WSDL";
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			
			MyXzfwsh_Bean bean;
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {				
				try {
//					result=downloadDB("GetSendCheckList",url,"admin","af8a15d2c3085fb3d818179b13a7eb29",4000);
					result=downloadDB("GetSendCheckList", url, UserCode, SignCode, PageSize, PageNow, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
					if(result.contains("\"totalcount\":\"0\"")){
						bean = new MyXzfwsh_Bean();
						bean.setTotalcount("0");
						return Integer.valueOf(0);
					}
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<MyXzfwsh_Bean>() {
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
				// TODO 自动生成的方法存根
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (bean != null) {
						handleResult.setiSuccess("success");
						Constants.list_xzfwsh_bean =bean.getRows();
						Constants.COUNT_OF_LIST_MYXZFW_BEAN = bean.getRows().size();
						}
				}else if(paramInteger == 0){
					if (bean != null ) {
						handleResult.setiSuccess("success_0");
						Constants.COUNT_OF_LIST_MYXZFW_BEAN = 0;
					}
				} else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				SendCheckListManager.this.handlerLoginInfo(paramActivity,handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	/**
	 * 公文审核接口
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode  用户名
	 * @param SignCode 加验证重码
	 * @param PageSize 每页条数
	 * @param PageNow  当前页
	 * @param Title  公文Title
	 * @param WenHao 文号/  LaiWenCompany来文单位（收文审核）
	 * @param NiGaoCompany 拟稿单位   / WenHao 文号（收文审核）
	 * @param SendStartTime 发文日期
	 * @param SendEndTime 发文日期
	 */
	public void SaveSendCheckStepData(final Context paramActivity,final int paramInt,
			final String UserCode, final String SignCode,final int PageSize,
			final int PageNow,final String Title,final String WenHao,
			final String NiGaoCompany,final String SendStartTime,final String SendEndTime){
//		final String url ="http://ncxceshioa.dichuang.cc/WebServices/AppService.asmx?WSDL";
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			
			MyXzfwsh_Bean bean;
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {				
				try {
//					result=downloadDB("GetSendCheckList",url,"admin","af8a15d2c3085fb3d818179b13a7eb29",4000);
					result=downloadDB("GetSendCheckList", url, UserCode, SignCode, PageSize, PageNow, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
					Log.i(TAG, "行政发文审核+++++++"+result);
					if(result.contains("\"totalcount\":\"0\"")){
						bean = new MyXzfwsh_Bean();
						bean.setTotalcount("0");
						return Integer.valueOf(0);
					}
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<MyXzfwsh_Bean>() {
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
				// TODO 自动生成的方法存根
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (bean != null) {
						handleResult.setiSuccess("success");
						Constants.list_xzfwsh_bean =bean.getRows();
						Constants.COUNT_OF_LIST_MYXZFW_BEAN = bean.getRows().size();
						}
				}else if(paramInteger == 0){
					if (bean != null ) {
						handleResult.setiSuccess("success_0");
						Constants.COUNT_OF_LIST_MYXZFW_BEAN = 0;
					}
				} else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				SendCheckListManager.this.handlerLoginInfo(paramActivity,handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}