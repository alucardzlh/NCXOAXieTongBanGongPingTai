package com.OA.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import com.OA.Data.Constants;
import com.OA.Entity.CheckPeople_Bean;
import com.OA.Entity.DeptCheckPeople_Bean;
import com.OA.Entity.DeptIDAndValue_Bean;
import com.OA.Entity.HandleResult;
import com.OA.Entity.IDAndValue_Bean;
import com.OA.Entity.LoginOA;
import com.OA.Entity.MyRecApply_wdxzsw_Bean;
import com.OA.Entity.MyRecApply_xzswcl_Bean;
import com.OA.Entity.MyXzswdj_Bean;
import com.OA.Entity.SelectPeople_Bean;
import com.OA.Util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 人员选择
 */
public abstract class PeopleCheckManager {

	private String TAG = "PeopleCheckManager";

	public void getListDatas(final Context paramActivity, final int paramInt,
			final String UserCode, final int DeptID) {
		// TODO 自动生成的方法存根
		final String url = paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址

		BaseAsyncTask local = new BaseAsyncTask(paramActivity, true) {
			String result_dept = null;
			String result_group = null;
			String result_post = null;
			String result_role = null;
			DeptCheckPeople_Bean bean_dept = null;
			CheckPeople_Bean bean_group = null;
			CheckPeople_Bean bean_post = null;
			CheckPeople_Bean bean_role = null;

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// TODO 自动生成的方法存根
				try {
					result_dept = downloadDPeoPle(paramActivity, "LoadDept",
							url, DeptID);
					result_group = downloadDPeoPle(paramActivity, "LoadGroup",
							url, UserCode);
					result_post = downloadDPeoPle(paramActivity, "LoadPost",
							url, "");
					result_role = downloadDPeoPle(paramActivity, "LoadRole",
							url, "");
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<CheckPeople_Bean>() {
					}.getType();
					Gson gsondept = new Gson();
					java.lang.reflect.Type typedept = new TypeToken<DeptCheckPeople_Bean>() {
					}.getType();
					bean_dept = gsondept.fromJson(result_dept, typedept);
					bean_group = gson.fromJson(result_group, type);
					bean_post = gson.fromJson(result_post, type);
					bean_role = gson.fromJson(result_role, type);
					if (!bean_dept.getStatus().equals("2000")) {
						Toast.makeText(paramActivity, "部门数据获取失败！",
								Toast.LENGTH_SHORT).show();
						bean_dept = new DeptCheckPeople_Bean();
						return 0;
					}
					if (!bean_group.getStatus().equals("2000")) {
						Toast.makeText(paramActivity, "工作组数据获取失败！",
								Toast.LENGTH_SHORT).show();
						bean_group = new CheckPeople_Bean();
						return 0;
					}
					if (!bean_post.getStatus().equals("2000")) {
						Toast.makeText(paramActivity, "职务数据获取失败！",
								Toast.LENGTH_SHORT).show();
						bean_post = new CheckPeople_Bean();
						return 0;
					}
					if (!bean_role.getStatus().equals("2000")) {
						Toast.makeText(paramActivity, "角色数据获取失败！",
								Toast.LENGTH_SHORT).show();
						bean_role = new CheckPeople_Bean();
						return 0;
					}
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result_group == null || result_group.equals("error")
						|| result_dept == null || result_dept.equals("error")
						|| result_post == null || result_post.equals("error")
						|| result_role == null || result_role.equals("error")) {
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
					List<DeptIDAndValue_Bean> list_dept = new ArrayList<DeptIDAndValue_Bean>();
					DeptIDAndValue_Bean dept = new DeptIDAndValue_Bean();
					dept.setDeptID("0");
					dept.setDeptName("所有部门");
					list_dept.add(dept);
					list_dept.addAll(bean_dept.getList());

					List<IDAndValue_Bean> list_jiaose = new ArrayList<IDAndValue_Bean>();
					IDAndValue_Bean jiaose = new IDAndValue_Bean();
					jiaose.setID(0);
					jiaose.setValue("所有角色");
					list_jiaose.add(jiaose);
					list_jiaose.addAll(bean_role.getList());

					List<IDAndValue_Bean> list_zhiwu = new ArrayList<IDAndValue_Bean>();
					IDAndValue_Bean zhiwu = new IDAndValue_Bean();
					zhiwu.setID(0);
					zhiwu.setValue("所有职务");
					list_zhiwu.add(zhiwu);
					list_zhiwu.addAll(bean_post.getList());

					List<IDAndValue_Bean> list_gongzuozu = new ArrayList<IDAndValue_Bean>();
					IDAndValue_Bean gongzuozu = new IDAndValue_Bean();
					gongzuozu.setID(0);
					gongzuozu.setValue("所有工作组");
					list_gongzuozu.add(gongzuozu);
					list_gongzuozu.addAll(bean_group.getList());

					Constants.list_bumen = list_dept;
					Constants.list_jiaose = list_jiaose;
					Constants.list_zhiwu = list_zhiwu;
					Constants.list_gongzuozu = list_gongzuozu;
				} else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				PeopleCheckManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		local.execute(1);
	}

	/**
	 * 搜索人员接口
	 * 
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode
	 *            用户名
	 * @param SignCode
	 *            安全加密码
	 * @param KindID
	 *            收发文类别，发文2000，收文4000
	 */
	public void SelectDeptPerson(final Context paramActivity,
			final int paramInt, final Map<String, Object> mapDatas) {
		// final String url
		// ="http://192.168.1.254:8099/WebServices/AppService.asmx?WSDL";
		final String url = paramActivity.getResources().getString(com.OA.Activity.R.string.webservice_rul_test);// 调用的接口地址

		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {

			String result = null; // 从服务器获取xml数据
			private SelectPeople_Bean bean;

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {

					result = SelectDeptPerson(paramActivity,
							"SelectDeptPerson", url, mapDatas);
					Type listType = new TypeToken<SelectPeople_Bean>() {
					}.getType();
					Gson gson = new Gson();
					bean = gson.fromJson(result, listType);
					// Constants.map_people_check=list;

				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (bean == null || result.equals("error")) {
					return Integer.valueOf(-1);

				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (bean != null) {
						handleResult.setiSuccess("success");
						Constants.list_left = bean.getList();
					}
				} else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				PeopleCheckManager.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}

	protected abstract void handlerLoginInfo(Context paramActivity,
			HandleResult handleResult, int paramInt);

}
