package com.OA.Service;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.OA.Entity.HandleResult;
import com.OA.Entity.Meeting_hyssh_Bean;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Entity.MyMeeting_Bean;
import com.OA.Entity.MyMeeting_hyssh_Bean;
import com.OA.Entity.MyNoticeDetail_Bean;
import com.OA.Entity.MyNoticeQC_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 调用通知公告详情相关接口
 */
public abstract class Hyxx_Detail_ShManager {
private String TAG="Meeting_hyssh_ListManager";
	/**
	 * 获取会议室详情接口
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode	当前登录账号
	 * @param SignCode	安全加密码	
	 * @param NoticeID	公告ID
	 */
	public void getMeeting_Detail(final Context paramActivity,final int paramInt,
			final String UserCode,
			final String SignCode,final int RoomLogID){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			MyMeetingDetail_Bean bean;
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDBMeeting(paramActivity,"ReadMtRoom", url, UserCode, SignCode,RoomLogID );
					JSONObject jsonObject = new JSONObject(result);
					String str = jsonObject.getString("status");
//					if(str.equals("0")){
//						bean = new MyNoticeDetail_Bean();
//						bean.setTotalcount("0");
//						return Integer.valueOf(0);
//					}
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<MyMeetingDetail_Bean>() {
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
						Constants.mymeetingdetail_bean = bean;
					}
				}else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				Hyxx_Detail_ShManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	/**
	 * 会议室s审核接口
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode	当前登录账号
	 * @param SignCode	安全加密码	
	 * @param NoticeID	公告ID
	 */
	public void VerifyMtRoom(final Context paramActivity,final int paramInt,
			final String UserCode,
			final String SignCode,final int RoomLogID,final String Flag,final int StepDataID){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=VerifyMtRoom(paramActivity,"VerifyMtRoom", url, UserCode, SignCode,RoomLogID,Flag,StepDataID );
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
						handleResult.setiSuccess("success");
						Toast.makeText(paramActivity, "审核失败，安全验证未通过！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("2000")){
						handleResult.setiSuccess("success");
						Toast.makeText(paramActivity, "审核成功！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5000")){
						handleResult.setiSuccess("success");
						Toast.makeText(paramActivity, "审核失败！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5001")){
						handleResult.setiSuccess("success");
						Toast.makeText(paramActivity, "审核失败，会议申请数据不存在！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5002")){
						handleResult.setiSuccess("success");
						Toast.makeText(paramActivity, "审核失败，会议通知失败！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5003")){
						handleResult.setiSuccess("success");
						Toast.makeText(paramActivity, "审核失败，会议室占用！", Toast.LENGTH_LONG)
						.show();
					}
				}else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				Hyxx_Detail_ShManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	/**
	 * 会议室申请接口
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode	当前登录账号
	 * @param SignCode	安全加密码	
	 * @param NoticeID	公告ID
	 */
	public void ApplyMtRoom(final Context paramActivity,final int paramInt,final Map<String,Object> mapDatas){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=ApplyMtRoom(paramActivity,"ApplyMtRoom", url,mapDatas );
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
						Toast.makeText(paramActivity, "申请失败，安全验证未通过！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("2000")){
						handleResult.setiSuccess("success");
						Toast.makeText(paramActivity, "申请成功！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5000")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "申请失败！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5001")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "申请失败，开始时间大于结束时间！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5002")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "申请失败，开始时间小于当前时间！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5003")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "申请失败，此时间段会议室已有预约！", Toast.LENGTH_LONG)
						.show();
					}
				}else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				Hyxx_Detail_ShManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	/**
	 * 会议室申请补充材料
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode	当前登录账号
	 * @param SignCode	安全加密码	
	 * @param NoticeID	公告ID
	 */
	public void SupplyFile(final Context paramActivity,final int paramInt,final Map<String,Object> mapDatas){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=SupplyFile(paramActivity,"SupplyFile", url,mapDatas );
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
					
					 if(result.equals("2000")){
						handleResult.setiSuccess("success");
						Toast.makeText(paramActivity, "补传成功！", Toast.LENGTH_LONG)
						.show();
					}else if(result.equals("5000")){
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "补传失败！", Toast.LENGTH_LONG)
						.show();
					}else {
						handleResult.setiSuccess("fail");
						Toast.makeText(paramActivity, "未知错误！", Toast.LENGTH_LONG)
						.show();
					}
				}else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				Hyxx_Detail_ShManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}