package com.OA.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.Xzswcl_Detail_Bdxx_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class GetShouWenCLFormElementsManager {
private String TAG="NOT";


	
	/**
	 * 获取收文处理表单数据
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode 用户名
	 * @param SignCode 加验证重码
	 * @param FLowID 流程ID
	 * @param WFID 主数据ID
	 */
	public void getFlowFormElements(final Context paramActivity,final int paramInt,
			final String UserCode, final String SignCode,final int FlowID,
			final int WFID,final int CurNodeID){
//		final String url ="http://192.168.1.254:8099/WebServices/AppService.asmx?WSDL";
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			private List<Xzswcl_Detail_Bdxx_Bean> list;
//			MyXzfw_Bean bean;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDDB("GetFlowFormElements", url, UserCode, SignCode, FlowID, WFID,CurNodeID);
					Type listType = new TypeToken<ArrayList<Xzswcl_Detail_Bdxx_Bean>>(){}.getType();  
			        Gson gson = new Gson();  
			        list = gson.fromJson(result, listType);  
			 //       Constants.list_Detail_bdxx_bean=list;
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
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (result != null) {
						handleResult.setiSuccess("success");
						 Constants.listCL_Detail_bdxx_bean=list;
					}
				} else if(paramInteger == 0){
					if (result != null ) {
						handleResult.setiSuccess("success_0");
					}}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				GetShouWenCLFormElementsManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}
