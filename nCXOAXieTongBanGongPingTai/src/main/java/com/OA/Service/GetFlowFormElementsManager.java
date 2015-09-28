package com.OA.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.Detail_Bdxx_Bean;
import com.OA.Entity.FirstNextStepDatas;
import com.OA.Entity.HandleResult;
import com.OA.Entity.ISEnd_Bean;
import com.OA.Entity.MyMeeting_Bean;
import com.OA.Entity.MyNoticeQC_Bean;
import com.OA.Entity.MyXzfw_Bean;
import com.OA.Entity.NextFlowStepsBean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 获取发文表单数据
 */
public abstract class GetFlowFormElementsManager {
private String TAG="NOT";


	
	/**
	 * 获取发文表单数据
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
//		final String url ="http://ncxceshioa.dichuang.cc/WebServices/AppService.asmx?WSDL";
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			String result_selPeople = null;
			private List<Detail_Bdxx_Bean> list;
//			MyXzfw_Bean bean;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDDB("GetFlowFormElements", url, UserCode, SignCode, FlowID, WFID,CurNodeID);
					result_selPeople = IsSelBrowseUsers(paramActivity,"IsSelBrowseUsers", url,FlowID,CurNodeID);
					//					Log.i(TAG, "发文表单++++++++++++++++++"+result);

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
				if (result == null || result.equals("error") || result_selPeople == null || result_selPeople.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
						handleResult.setiSuccess("success");
						 Constants.list_Detail_bdxx_bean=list;
						 if(result_selPeople.equals("1000")){
							 handleResult.setSelPeople(true);
						 }else if(result_selPeople.equals("2000")){
							 handleResult.setSelPeople(false);
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
				GetFlowFormElementsManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	

	/**
	 * 发文撰稿第一步
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode 用户名
	 * @param SignCode 加验证重码
	 * @param FLowID 流程ID
	 * @param WFID 主数据ID
	 */
	public void GetApplyNextStepInfo(final Context paramActivity,final int paramInt,
			final int FlowID, final int FlowStepID,final String UserCode){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			private FirstNextStepDatas datas;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=GetApplyNextStepInfo(paramActivity,"GetApplyNextStepInfo", url, FlowID, FlowStepID, UserCode);
					Type listType = new TypeToken<FirstNextStepDatas>(){}.getType();  
			        Gson gson = new Gson();  
			        datas = gson.fromJson(result, listType);  
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
					if (result != null && datas != null) {
						handleResult.setiSuccess("success");
						handleResult.setDatas(datas);
					}
				}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				GetFlowFormElementsManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	
	/**
	 * 发文审核第一步
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode 用户名
	 * @param SignCode 加验证重码
	 * @param FLowID 流程ID
	 * @param WFID 主数据ID
	 */
	public void GetCheckNextStepInfo(final Context paramActivity,final int paramInt,
			final int FlowID, final int WFID,final int FlowStepID,final String CurNode,final String ISPass,final int ISBrachSel,final String UserCode){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			private FirstNextStepDatas datas;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=GetCheckNextStepInfo(paramActivity,"GetCheckNextStepInfo", url, FlowID, WFID,FlowStepID, CurNode,ISPass,ISBrachSel,UserCode);
					Type listType = new TypeToken<FirstNextStepDatas>(){}.getType();  
			        Gson gson = new Gson();  
			        datas = gson.fromJson(result, listType);  
				} catch (Exception e) {
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error") || datas == null) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (result != null && datas != null) {
						handleResult.setiSuccess("success");
						handleResult.setDatas(datas);
					}
				}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				GetFlowFormElementsManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	
	/**
	 * 发文第二步
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode 用户名
	 * @param SignCode 加验证重码
	 * @param FLowID 流程ID
	 * @param WFID 主数据ID
	 */
	public void GetNextFlowSteps(final Context paramActivity,final int paramInt,
			final int FlowID, final int FlowStepID,final String UserCode){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			private List<NextFlowStepsBean> list_bean;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=GetApplyNextStepInfo(paramActivity,"GetNextFlowSteps", url, FlowID, FlowStepID, UserCode);
					Type listType = new TypeToken<ArrayList<NextFlowStepsBean>>(){}.getType();  
			        Gson gson = new Gson();  
			        list_bean = gson.fromJson(result, listType);  
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
					if (result != null && list_bean != null) {
						handleResult.setiSuccess("success");
						handleResult.setList_bean_steps(list_bean);
					}
				}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				GetFlowFormElementsManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	/**
	 * 公文审核接口
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode 用户名
	 * @param SignCode 加验证重码
	 * @param FLowID 流程ID
	 * @param WFID 主数据ID
	 */
	public void SaveSendCheckStepData(final Context paramActivity,final int paramInt,final Map<String,Object> mapDatas){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
		//	private List<NextFlowStepsBean> list_bean;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=SaveSendCheckStepData(paramActivity,"SaveSendCheckStepData", url ,mapDatas);
			//		Type listType = new TypeToken<ArrayList<NextFlowStepsBean>>(){}.getType();  
			//        Gson gson = new Gson();  
			//        list_bean = gson.fromJson(result, listType);  
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
			//		if (result != null && list_bean != null) {
						
			//			handleResult.setList_bean_steps(list_bean);
			//		}
						if(result.equals("4000")){
							Toast.makeText(paramActivity, "审核成功！", Toast.LENGTH_LONG)
							.show();	
							handleResult.setiSuccess("success");
						}else if(result.equals("3000")){
							Toast.makeText(paramActivity, "下一步未配置审核人，请先配置流程！", Toast.LENGTH_LONG)
							.show();
							handleResult.setiSuccess("fail");
						}else if(result.equals("2000")){
							Toast.makeText(paramActivity, "流程不存在！", Toast.LENGTH_LONG)
							.show();
							handleResult.setiSuccess("fail");
						}else if(result.equals("1000")){
							Toast.makeText(paramActivity, "保存失败！", Toast.LENGTH_LONG)
							.show();
							handleResult.setiSuccess("fail");
						}else{
							Toast.makeText(paramActivity, "未知错误！", Toast.LENGTH_LONG)
							.show();
							handleResult.setiSuccess("fail");
						}
				}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				GetFlowFormElementsManager.this.handlerLoginInfo(paramActivity,
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
				GetFlowFormElementsManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	/**
	 * 保存公文内容
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode 用户名
	 * @param SignCode 加验证重码
	 * @param FLowID 流程ID
	 * @param WFID 主数据ID
	 */
	public void SaveArchiveData(final Context paramActivity,final int paramInt,final int ArcStepID,final int WFID,final int WFStepID,final String UserCode,final String SignCode,final String mFileBody){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			String content = null;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=SaveArchiveData(paramActivity,"SaveArchiveData", url, ArcStepID ,WFID,WFStepID,UserCode,SignCode,mFileBody);
				//	JSONObject jsonObject = new JSONObject(result);
				//	content = jsonObject.getString("FileBody");
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
						handleResult.setArcStepID(Integer.valueOf(result).intValue());
				//		handleResult.setList_bean_steps(list_bean);
				//	}
				}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				GetFlowFormElementsManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	/**
	 * 保存公文内容
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode 用户名
	 * @param SignCode 加验证重码
	 * @param FLowID 流程ID
	 * @param WFID 主数据ID
	 */
	public void SaveSendApplyStepData(final Context paramActivity,final int paramInt,final Map<String,Object> mapDatas){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=SaveSendCheckStepData(paramActivity,"SaveSendApplyStepData", url,mapDatas);
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
					if(result.equals("4000")){
						Toast.makeText(paramActivity, "申请成功！", Toast.LENGTH_LONG)
						.show();	
						handleResult.setiSuccess("success");
					}else if(result.equals("3000")){
						Toast.makeText(paramActivity, "下一步未配置审核人，请先配置流程！", Toast.LENGTH_LONG)
						.show();
						handleResult.setiSuccess("fail");
					}else if(result.equals("2000")){
						Toast.makeText(paramActivity, "流程不存在！", Toast.LENGTH_LONG)
						.show();
						handleResult.setiSuccess("fail");
					}else if(result.equals("1000")){
						Toast.makeText(paramActivity, "保存失败！", Toast.LENGTH_LONG)
						.show();
						handleResult.setiSuccess("fail");
					}else{
						Toast.makeText(paramActivity, "未知错误！", Toast.LENGTH_LONG)
						.show();
						handleResult.setiSuccess("fail");
					}
				}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				GetFlowFormElementsManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	
	/**
	 * 判断流程是否结束
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode 用户名
	 * @param SignCode 加验证重码
	 * @param FLowID 流程ID
	 * @param WFID 主数据ID
	 */
	public void ISEnd(final Context paramActivity,final int paramInt,final String CurStepNode,final String ISPass,final int FlowID){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			ISEnd_Bean bean = null;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=ISEnd(paramActivity,"ISEnd", url, CurStepNode ,ISPass,FlowID);
					Type listType = new TypeToken<ISEnd_Bean>(){}.getType();  
					        Gson gson = new Gson();  
					        bean = gson.fromJson(result, listType);  
				//	JSONObject jsonObject = new JSONObject(result);
				//	content = jsonObject.getString("FileBody");
				} catch (Exception e) {
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error") || bean == null) {
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
						handleResult.setBean_isEnd(bean);;
				//		handleResult.setArcStepID(Integer.valueOf(result).intValue());
				//		handleResult.setList_bean_steps(list_bean);
				//	}
				}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				GetFlowFormElementsManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	/**
	 * 判断流程是否需要选人
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode 用户名
	 * @param SignCode 加验证重码
	 * @param FLowID 流程ID
	 * @param WFID 主数据ID
	 */
	public void IsSelBrowseUsers(final Context paramActivity,final int paramInt,final int FlowID,final int CurNodeID){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			String content = null;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=IsSelBrowseUsers(paramActivity,"IsSelBrowseUsers", url,FlowID,CurNodeID);
				//	JSONObject jsonObject = new JSONObject(result);
				//	content = jsonObject.getString("FileBody");
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
				//		handleResult.setArcStepID(Integer.valueOf(result).intValue());
				//		handleResult.setList_bean_steps(list_bean);
				//	}
				}
				else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				GetFlowFormElementsManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}