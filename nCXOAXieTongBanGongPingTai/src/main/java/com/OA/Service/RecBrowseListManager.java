package com.OA.Service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Entity.HandleResult;
import com.OA.Util.BaseAsyncTask;

/**
 * 收文浏览列表
 */
public abstract class RecBrowseListManager {
	private String TAG="NOT";

	/**
	 * 获取收文浏览列表
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode  用户名
	 * @param SignCode 加验证重码
	 * @param PageSize 每页条数
	 * @param PageNow  当前页
	 * @param Status	状态:100待阅，200已阅
	 * @param Title  公文Title
	 * @param LaiWenCompany 来文单位
	 * @param WenHao 文号
	 * @param SendStartTime 发文日期
	 * @param SendEndTime 发文日期
	 */
	public void GetRecBrowseListManager(final Context paramActivity,final int paramInt,
			final String UserCode, final String SignCode,final int PageSize,
			final int PageNow,final String Staue,final String Title,final String LaiWenCompany,final String WenHao,
			final String SendStartTime,final String SendEndTime){
//			final String url ="http://ncxceshioa.dichuang.cc/WebServices/AppService.asmx?WSDL";
			final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
			
			
			
			BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
				
				String result=null; //从服务器获取xml数据
//				 ZwFw_Fwpy_BizInfo info = new ZwFw_Fwpy_BizInfo();
				@Override
				protected Integer doInBackground(Integer[] paramArrayOfInteger) {
					try {
					
//						result=downloadDB("GetSendCheckList",url,"admin","af8a15d2c3085fb3d818179b13a7eb29",4000);
						result=downloadDB("GetSendCheckList", url, "admin","af8a15d2c3085fb3d818179b13a7eb29", 
								1, 1, "100", "2", "3", "4", "5","6");
//						info = XmlUtil.get_Zwfw_fwpy_Detail(result);
						Log.i(TAG, "收文浏览列表+++++++++++++++++++++"+result);
					
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
					RecBrowseListManager.this.handlerLoginInfo(paramActivity,
							handleResult, paramInt);
				}
			};
			loacl.execute(1);
		}
		protected abstract void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt);
	}