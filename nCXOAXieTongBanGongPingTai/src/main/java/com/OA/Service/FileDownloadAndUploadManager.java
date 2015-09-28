package com.OA.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import com.OA.Data.Constants;
import com.OA.Entity.CheckPeople_Bean;
import com.OA.Entity.FileDetail_Bean;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Entity.MyRecApply_wdxzsw_Bean;
import com.OA.Entity.MyRecApply_xzswcl_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 人员选择
 */
public abstract class FileDownloadAndUploadManager {
	
	private String TAG = "FileDownloadAndUploadManager";
	
	public void DownloadFile(final Context paramActivity, final int paramInt,final int FileID) {
		// TODO 自动生成的方法存根
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		BaseAsyncTask local = new BaseAsyncTask(paramActivity,true){
			String result_group = null;
			FileDetail_Bean bean;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// TODO 自动生成的方法存根
				try {
					result_group = downloadFile(paramActivity,"DownFile", url, FileID);
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<FileDetail_Bean>() {
					}.getType();
					bean=gson.fromJson(result_group, type);
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result_group == null || result_group.equals("error")
						|| bean == null) {
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
					handleResult.setiSuccess("success");
					handleResult.setBean_file(bean);
				}	else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				FileDownloadAndUploadManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		local.execute(1);
	}
	
	public void uploadFile(final Context paramActivity, final int paramInt,final Map<String,Object> map) {
		// TODO 自动生成的方法存根
		final String url =paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址
		
		BaseAsyncTask local = new BaseAsyncTask(paramActivity,true){
			String result_group = null;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// TODO 自动生成的方法存根
				try {
					result_group = uploadFile(paramActivity,"SaveFile", url, map);
					if(Constants.FileIDs.equals("")){
						Constants.FileIDs = result_group;
					}else
					Constants.FileIDs += ","+result_group;
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result_group == null || result_group.equals("error")) {
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
					handleResult.setiSuccess("success");
				}	else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					Constants.FileIDs = "";
					handleResult.setiSuccess("fail");
					return;
				}
				FileDownloadAndUploadManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		local.execute(1);
	}
	
	
	protected abstract void handlerLoginInfo(Context paramActivity,
			HandleResult handleResult, int paramInt);
		
}
