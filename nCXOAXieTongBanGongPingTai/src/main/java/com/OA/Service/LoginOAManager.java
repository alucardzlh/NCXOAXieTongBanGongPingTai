package com.OA.Service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.OA.Activity.LoginActivity;
import com.OA.Entity.HandleResult;
import com.OA.Entity.LoginOA;
import com.OA.Util.BaseAsyncTask;
import com.OA.Util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



/**
 * 登录
 */
public abstract class LoginOAManager {
	private String TAG="LoginOAManager";

	/**
	 * 登录
	 * @param paramActivity
	 * @param paramInt
	 * @param str1 用户名
	 * @param str2 密码
	 * @param str3 登录来源
	 */
	public void getLoginOAManager(final Context paramActivity,final int paramInt,final String str1,final String str2){
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			String result=null; //从服务器获取xml数据
			LoginOA bean;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDB("LoginOA",url,str1,str2,"200");
					// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
					if (result == null || result.equals("error")) {
						return Integer.valueOf(-1);
					}
					if(result.equals("2000") || result.equals("3000")||result.equals("4000")||result.equals("5000")
							||	result.equals("6000")||result.equals("7000")||result.equals("8000")){
						return Integer.valueOf(1);
					}else {
						 bean = new LoginOA();
							Gson gson = new Gson();
							java.lang.reflect.Type type = new TypeToken<LoginOA>() {
							}.getType();
							bean=gson.fromJson(result, type);
							return Integer.valueOf(2);
					}
				} catch (Exception e) {
				//	Toast.makeText(paramActivity, "解析失败！", Toast.LENGTH_SHORT).show();
					Log.e(TAG, e.toString());
					return Integer.valueOf(-1);
				}
			}
			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setiSuccess("success");
					handleResult.setLogin_result("fail");
					if(result.equals("2000")){
						Toast.makeText(paramActivity, "用户名不存在", Toast.LENGTH_SHORT).show();
					}else if(result.equals("3000")){
						Toast.makeText(paramActivity, "密码错误", Toast.LENGTH_SHORT).show();
					}else if(result.equals("4000")){
						Toast.makeText(paramActivity, "该时间段不能登录", Toast.LENGTH_SHORT).show();
					}else if(result.equals("5000")){
						Toast.makeText(paramActivity, "该IP禁止登录", Toast.LENGTH_SHORT).show();
					}else if(result.equals("6000")){
						Toast.makeText(paramActivity, "用户被锁定", Toast.LENGTH_SHORT).show();
					}else if(result.equals("7000")){
						Toast.makeText(paramActivity, "登录失败", Toast.LENGTH_SHORT).show();
					}else if(result.equals("8000")){
						Toast.makeText(paramActivity, "部门信息不完整", Toast.LENGTH_SHORT).show();
					}
				}else if(paramInteger == 2){
					handleResult.setiSuccess("success");
					handleResult.setLogin_result("success");
					handleResult.setBean_login(bean);
				}
				else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setiSuccess("fail");
					handleResult.setLogin_result("fail");
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				LoginOAManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);

}