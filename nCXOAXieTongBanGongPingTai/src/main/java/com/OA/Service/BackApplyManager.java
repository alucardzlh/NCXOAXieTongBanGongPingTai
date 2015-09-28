package com.OA.Service;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Entity.MyMeetingDetail_bbchry_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *公文退回
 */
public abstract class BackApplyManager {
	private String TAG="NOT";




	/**
	 * @param paramActivity
	 * @param paramInt
	 * @param WFStepID  流程步奏ID
	 * @param UserCode
	 * @param SignCode
	 */
	public void getBackApplyManager(final Context paramActivity,final int paramInt,final int WFStepID ,final String UserCode,final String  SignCode,final String Memo){
//		final String url ="http://ncxceshioa.dichuang.cc/WebServices/AppService.asmx?WSDL";
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
//			 MyMeetingDetail_Bean  bean;
			String result=null; //从服务器获取xml数据
//			private MyMeetingDetail_Bean bean;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
				
					result=downloadDB("BackApply",url, WFStepID,UserCode, SignCode,Memo);
					Log.i(TAG, "公文退回+++++++++"+result);
//					JSONObject jsonObject = new JSONObject(result);
//					String str = jsonObject.getString("status");
//					Gson gson = new Gson();
//					java.lang.reflect.Type type = new TypeToken<MyMeetingDetail_Bean>() {
//					}.getType();
//					bean=gson.fromJson(result, type);
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null ||  result.equals("error")) {
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
					if (result != null) {
						
						if(result.equals("1000")){
							handleResult.setiSuccess("success");
							Toast.makeText(paramActivity, "退回成功！", Toast.LENGTH_LONG)
							.show();
						}else if(result.equals("2000")){
							handleResult.setiSuccess("fail");
							Toast.makeText(paramActivity, "退回失败！", Toast.LENGTH_LONG)
							.show();
						}else{
							handleResult.setiSuccess("fail");
							Toast.makeText(paramActivity, "退回失败，未知错误！", Toast.LENGTH_LONG)
							.show();
						}
//						Constants.mymeetingdetail_bean=bean;
					}
				}else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				BackApplyManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}