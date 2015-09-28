package com.OA.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.Detail_Bdxx_Bean;
import com.OA.Entity.HandleResult;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class GetShouWenFormElementsManager {
private String TAG="NOT";


	
	/**
	 * 获取收文表单数据
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
			private List<Detail_Bdxx_Bean> list;
//			MyXzfw_Bean bean;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDDB("GetFlowFormElements", url, UserCode, SignCode, FlowID, WFID,CurNodeID);
//					Log.i(TAG, "收文表单++++++++++++++++++"+result);


					Type listType = new TypeToken<ArrayList<Detail_Bdxx_Bean>>(){}.getType();  
			        Gson gson = new Gson();  
			        list = gson.fromJson(result, listType);  
			        Detail_Bdxx_Bean bean_temp;
			        for (int i = 0;i<list.size();i++) {
						for(int j =i;j<list.size();j++){
							if(Integer.valueOf(list.get(j).getElementOrder()).intValue()
									<Integer.valueOf(list.get(i).getElementOrder()).intValue()){
								bean_temp = list.get(i);
								list.set(i, list.get(j));
								list.set(j, bean_temp);
							}
						}
					}  
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
						 Constants.list_Detail_bdxx_bean=list;
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
				GetShouWenFormElementsManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	/**
	 * 获取公文内容
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode 用户名
	 * @param SignCode 加验证重码
	 * @param FLowID 流程ID
	 * @param WFID 主数据ID
	 */
	public void LoadArchiveData(final Context paramActivity,final int paramInt,final int Tildes,final int ArcStepID,final int WFID,final int WFStepID){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			String content = null;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=LoadArchiveData(paramActivity,"LoadArchiveData", url, Tildes ,ArcStepID,WFID,WFStepID);
					JSONObject jsonObject = new JSONObject(result);
					content = jsonObject.getString("FileBody");
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
				//	if (result != null && list_bean != null) {
						handleResult.setiSuccess("success");
						handleResult.setContent(content);
				//		handleResult.setList_bean_steps(list_bean);
				//	}
				}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				GetShouWenFormElementsManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}
