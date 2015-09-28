package com.OA.Service;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.Meeting_hyssh_Bean;
import com.OA.Entity.MyMeeting_Bean;
import com.OA.Entity.MyMeeting_hyssh_Bean;
import com.OA.Entity.MyNoticeDetail_Bean;
import com.OA.Entity.MyNoticeQC_Bean;
import com.OA.Entity.MyNoticeSH_Bean;
import com.OA.Entity.Tzgg_tg_datas_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 调用通知公告详情相关接口
 */
public abstract class Tzgg_Detail_ShManager {
private String TAG="Tzgg_Detail_ShManager";
	/**
	 * 获取通知公告详情接口
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode	当前登录账号
	 * @param SignCode	安全加密码	
	 * @param NoticeID	公告ID
	 */
	public void getNotice_Detail(final Context paramActivity,final int paramInt,
			final String UserCode,
			final String SignCode,final int NoticeID){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			MyNoticeDetail_Bean bean;
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDBNotice(paramActivity,"ReadNotice", url, UserCode, SignCode,NoticeID );
					JSONObject jsonObject = new JSONObject(result);
					String str = jsonObject.getString("status");
//					if(str.equals("0")){
//						bean = new MyNoticeDetail_Bean();
//						bean.setTotalcount("0");
//						return Integer.valueOf(0);
//					}
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<MyNoticeDetail_Bean>() {
					}.getType();
					bean=gson.fromJson(result, type);
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (bean == null || result == null || result.equals("error")) {
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
						Constants.mynoticedetail_bean = bean;
					}
				}else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				Tzgg_Detail_ShManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	/**
	 * 通知公告审核接口
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode	当前登录账号
	 * @param SignCode	安全加密码	
	 * @param NoticeID	公告ID
	 */
	public void VerifyNotice(final Context paramActivity,final int paramInt,
			final String UserCode,
			final String SignCode,final int NoticeID,final String Flag,final int flowid,final int WFStepID){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=VerifyNotice(paramActivity,"VerifyNotice", url, UserCode, SignCode,NoticeID,Flag,flowid,WFStepID );
					JSONObject jsonObject = new JSONObject(result);
					result = jsonObject.getString("status");
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if ( result == null || result.equals("error")) {
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
					
					if(result.equals("0000")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "审核失败，安全验证未通过！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("2000")){
						handleResult.setiSuccess("success");
						Toast.makeText(paramActivity, "审核成功！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5000")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "审核失败！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5001")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "审核失败，公告数据不存在！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5002")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "审核失败，流程配置错误！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5003")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "审核失败，流程数据不存在！", Toast.LENGTH_LONG)
						.show();
					}
				}else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				Tzgg_Detail_ShManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	/**
	 * 通知公告删除接口
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
	public void DelNotice(final Context paramActivity,
			final int paramInt, final int NoticeID) {
		final String url = paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址

		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null; // 从服务器获取数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result = VerifyNotice(paramActivity, "DelNotice", url, "", "", NoticeID, "", 0, 0);
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
						handleResult.setiSuccess("success");
						if(result.equals("1000")){
							Toast.makeText(paramActivity, "删除成功！", Toast.LENGTH_LONG)
							.show();
						}else if(result.equals("2000")){
							Toast.makeText(paramActivity, "该审核状态下不能删除！", Toast.LENGTH_LONG)
							.show();
						}else if(result.equals("3000")){
							Toast.makeText(paramActivity, "删除失败！", Toast.LENGTH_LONG)
							.show();
						}else{
							Toast.makeText(paramActivity, "未知错误！", Toast.LENGTH_LONG)
							.show();
						}
				}else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				Tzgg_Detail_ShManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}

	/**
	 * 通知公告添加或者修改接口
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
	public void SaveNotice(final Context paramActivity,
			final int paramInt, final Map<String, Object> mapDatas) {
		final String url = paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址

		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null; // 从服务器获取数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result = SaveNotice(paramActivity, "SaveNotice", url,mapDatas);
					JSONObject jsonObject = new JSONObject(result);
					result = jsonObject.getString("status");
				} catch (Exception e) {
					Log.e(TAG, e.toString());
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
					if(result.equals("0000")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "保存失败，安全验证未通过！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("2000")){
						handleResult.setiSuccess("success");
						Toast.makeText(paramActivity, "保存成功！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5000")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "保存失败！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5001")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "保存失败，公告数据不存在！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5002")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "保存失败，流程数据不存在！", Toast.LENGTH_LONG)
						.show();
					}
						handleResult.setiSuccess("success");
				}else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				Tzgg_Detail_ShManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
			
	
	/**
	 * 通知公告获取分类下拉列表和通告流程下拉列表接口
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
	public void GetNoticeTypeListAndFlowSetList(final Context paramActivity,
			final int paramInt, final Map<String, Object> mapDatas) {
		final String url = paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result_fenlei = null; // 从服务器获取数据
			String result_liucheng = null; 
			String str_liucheng,str_fenlei;
			Tzgg_tg_datas_Bean bean_fenlei,bean_liucheng;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result_fenlei = getDatasTZGG(paramActivity, "GetNoticeTypeList", url,mapDatas);
					result_liucheng = getDatasTZGG(paramActivity, "GetFlowSetList", url,mapDatas);
					JSONObject jsonObject_fenlei = new JSONObject(result_fenlei);
					 str_fenlei = jsonObject_fenlei.getString("status");
					JSONObject jsonObject_liucheng = new JSONObject(result_fenlei);
					 str_liucheng = jsonObject_liucheng.getString("status");
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<Tzgg_tg_datas_Bean>() {
					}.getType();
					bean_fenlei = gson.fromJson(result_fenlei, type);
					bean_liucheng = gson.fromJson(result_liucheng, type);
				} catch (Exception e) {
					Log.e("GetNoticeTypeListAndFlowSetList", e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result_fenlei == null || result_fenlei.equals("error")
			||result_liucheng == null || result_liucheng.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if(!str_fenlei.equals("2000") || !str_liucheng.equals("2000")){
						Toast.makeText(paramActivity, "获取通告类型和通告流程失败！", Toast.LENGTH_LONG)
						.show();
						handleResult.setiSuccess("fail");
						return;
					}
						handleResult.setBean_fenlei(bean_fenlei);
						handleResult.setBean_liucheng(bean_liucheng);
						handleResult.setiSuccess("success");
				}else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				Tzgg_Detail_ShManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}