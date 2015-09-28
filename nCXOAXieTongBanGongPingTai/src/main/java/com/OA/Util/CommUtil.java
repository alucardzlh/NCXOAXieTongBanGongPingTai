package com.OA.Util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.FieldsDocumentPart;
import org.apache.poi.hwpf.usermodel.Field;
import org.apache.poi.hwpf.usermodel.Fields;
import org.apache.poi.hwpf.usermodel.Range;

import com.OA.Activity.R;
import com.OA.Entity.AppInfo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CommUtil {

	/**
	 * 更改权限
	 * 
	 * @param permission权限
	 * @param path文件路径
	 * @author rjh
	 * @date 2014-5-30
	 */
	public static void chmod(String permission, String path) {
		try {
			String command = "chmod " + permission + " " + path;
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(command);
		} catch (IOException e) {
			Log.e("CommUtil (chmod) --> ", e.toString());
		}
	}

	/**
	 * 打印默认info级别的日志
	 * 
	 * @param tag
	 *            标签
	 * @param info
	 *            信息
	 * @author rjh
	 * @date 2014-5-30
	 */
	public static void log(String tag, String info) {
		Log.i(tag, info);
	}

	/**
	 * 打印debug级别的日志
	 * 
	 * @param tag
	 *            标签
	 * @param info
	 *            信息
	 * @author rjh
	 * @date 2014-5-30
	 */
	public static void logD(String tag, String info) {
		Log.d(tag, info);
	}

	/**
	 * 打印error级别的日志
	 * 
	 * @param tag
	 *            标签
	 * @param info
	 *            信息
	 * @author rjh
	 * @date 2014-5-30
	 */
	public static void logE(String tag, String info) {
		Log.e(tag, info);
	}

	/**
	 * 判断网络是否连接
	 * 
	 * @author rjh
	 * @param context
	 * @return
	 */
	public static boolean isNetworkOk(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
		if (activeInfo == null) {
			return false;
		} else {
			return activeInfo.isConnected();
		}
	}

	/**
	 * @param 获取
	 *            listView 的绝对高度
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {

		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			// listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	/**
	 * 网络配置提醒
	 * 
	 * @author rjh
	 * @date 2014-5-30
	 */
	public static void showNetworkDialog(Context context) {
		// AlertDialog dialog = new AlertDialog(context);
		// dialog.setTitle("当前无可用网络").setMessage("是否跳转到网络设置界面？").setEnsureText("确定").setCancelText("取消").setOnClickListener(new
		// AlertDialog.OnClickAdapter() {
		// @Override
		// public void onEnsureClick(AlertDialog dialog) {
		// startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));//
		// 跳转到网络配置界面
		// finish();
		// }
		//
		// @Override
		// public void onCancelClick(AlertDialog dialog) {
		// finish();
		// }
		// }).cancelable(false).show();
		Toast.makeText(context, "当前无可用网络", Toast.LENGTH_SHORT).show();
	}

	public static Intent getWordFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");

		intent.addCategory("android.intent.category.DEFAULT");

		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		Uri uri = Uri.fromFile(new File(param));

		intent.setDataAndType(uri, "application/msword");

		return intent;

	}

	/**
	 * 判断Intent 是否存在 防止崩溃
	 * 
	 * @param context
	 * @param intent
	 * @return
	 */
	public static boolean isIntentAvailable(Context context, Intent intent) {
		final PackageManager packageManager = context.getPackageManager();
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.GET_ACTIVITIES);
		return list.size() > 0;
	}

	// 三个参数
	// 第一个是文件名字
	// 第二个是文件存放的目录
	// 第三个是文件内容
	public static void writeToSDcardFile(String file, String destDirStr,
			byte[] szOutText) {
		byte[] bytes = new byte[] { 68, 73, 82, 71, 0, 40, 0, 0, 0, 0, 0, 0, 1,
				0, 0, 0 };
		// byte[] bt = new byte[] { 68,73,82,71,0,40,0,0,0,0,0,0,1,0,0,0,
		// 208,207,17,224,161,177,26,225,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,62,0,3,0,254,255,9,0,6,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,16,0,0,2,0,0,0,1,0,0,0,254,255,255,255,0,0,0,0,0,0,0,0,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,253,255,255,255,5,0,0,0,254,255,255,255,4,0,0,0,6,0,0,0,254,255,255,255,7,0,0,0,8,0,0,0,9,0,0,0,10,0,0,0,11,0,0,0,12,0,0,0,13,0,0,0,14,0,0,0,15,0,0,0,16,0,0,0,17,0,0,0,18,0,0,0,254,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,82,0,111,0,111,0,116,0,32,0,69,0,110,0,116,0,114,0,121,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,22,0,5,0,255,255,255,255,255,255,255,255,1,0,0,0,6,9,2,0,0,0,0,0,192,0,0,0,0,0,0,70,0,0,0,0,0,0,0,0,0,0,0,0,112,8,36,146,250,24,208,1,3,0,0,0,64,28,0,0,0,0,0,0,5,0,83,0,117,0,109,0,109,0,97,0,114,0,121,0,73,0,110,0,102,0,111,0,114,0,109,0,97,0,116,0,105,0,111,0,110,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,40,0,2,1,4,0,0,0,2,0,0,0,255,255,255,255,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,204,1,0,0,0,0,0,0,5,0,68,0,111,0,99,0,117,0,109,0,101,0,110,0,116,0,83,0,117,0,109,0,109,0,97,0,114,0,121,0,73,0,110,0,102,0,111,0,114,0,109,0,97,0,116,0,105,0,111,0,110,0,0,0,0,0,0,0,0,0,0,0,56,0,2,1,255,255,255,255,255,255,255,255,255,255,255,255,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,240,1,0,0,0,0,0,0,87,0,111,0,114,0,100,0,68,0,111,0,99,0,117,0,109,0,101,0,110,0,116,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,26,0,2,0,255,255,255,255,255,255,255,255,255,255,255,255,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16,0,0,0,46,14,0,0,0,0,0,0,1,0,0,0,2,0,0,0,3,0,0,0,4,0,0,0,5,0,0,0,6,0,0,0,7,0,0,0,254,255,255,255,9,0,0,0,10,0,0,0,11,0,0,0,12,0,0,0,13,0,0,0,14,0,0,0,15,0,0,0,254,255,255,255,17,0,0,0,18,0,0,0,19,0,0,0,20,0,0,0,21,0,0,0,22,0,0,0,23,0,0,0,24,0,0,0,25,0,0,0,26,0,0,0,27,0,0,0,28,0,0,0,29,0,0,0,30,0,0,0,31,0,0,0,32,0,0,0,33,0,0,0,34,0,0,0,35,0,0,0,36,0,0,0,37,0,0,0,38,0,0,0,39,0,0,0,40,0,0,0,41,0,0,0,42,0,0,0,43,0,0,0,44,0,0,0,45,0,0,0,46,0,0,0,47,0,0,0,48,0,0,0,49,0,0,0,50,0,0,0,51,0,0,0,52,0,0,0,53,0,0,0,54,0,0,0,55,0,0,0,56,0,0,0,57,0,0,0,58,0,0,0,59,0,0,0,60,0,0,0,61,0,0,0,62,0,0,0,63,0,0,0,64,0,0,0,65,0,0,0,66,0,0,0,67,0,0,0,68,0,0,0,69,0,0,0,70,0,0,0,71,0,0,0,72,0,0,0,254,255,255,255,74,0,0,0,75,0,0,0,76,0,0,0,77,0,0,0,78,0,0,0,79,0,0,0,80,0,0,0,81,0,0,0,82,0,0,0,83,0,0,0,84,0,0,0,85,0,0,0,86,0,0,0,87,0,0,0,88,0,0,0,89,0,0,0,90,0,0,0,91,0,0,0,92,0,0,0,93,0,0,0,94,0,0,0,95,0,0,0,96,0,0,0,97,0,0,0,98,0,0,0,99,0,0,0,100,0,0,0,101,0,0,0,102,0,0,0,103,0,0,0,104,0,0,0,105,0,0,0,106,0,0,0,107,0,0,0,108,0,0,0,109,0,0,0,110,0,0,0,111,0,0,0,112,0,0,0,254,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255
		// };
		// 获取扩展SD卡设备状态
		String sDStateString = android.os.Environment.getExternalStorageState();

		File myFile = null;
		// 拥有可读可写权限
		if (sDStateString.equals(android.os.Environment.MEDIA_MOUNTED)) {

			try {

				// 获取扩展存储设备的文件目录
				File SDFile = android.os.Environment
						.getExternalStorageDirectory();

				File destDir = new File(SDFile.getAbsolutePath() + destDirStr);// 文件目录

				if (!destDir.exists()) {// 判断目录是否存在，不存在创建
					destDir.mkdir();// 创建目录
				}
				// 打开文件
				myFile = new File(destDir + File.separator + file);

				// 判断文件是否存在,不存在则创建
				if (!myFile.exists()) {
					myFile.createNewFile();// 创建文件
				}

				// 写数据 注意这里，两个参数，第一个是写入的文件，第二个是指是覆盖还是追加，
				// 默认是覆盖的，就是不写第二个参数，这里设置为true就是说不覆盖，是在后面追加。
				FileOutputStream outputStream = new FileOutputStream(myFile,
						false);
				Writer write = new OutputStreamWriter(outputStream, "UTF-8");
				String temp = Base64.encodeToString(szOutText, Base64.DEFAULT);
				// String isoString = new String(szOutText,"UTF-8");
				Log.e("2222", szOutText + "");
				write.write(temp);
				write.close();// 关闭流
				outputStream.close();

			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}

		}

	}

	public void writeDoc(Context context, Map<String, String> map) {
		try {
			// 读取word模板
			Resources res = context.getResources();
			InputStream in = res.openRawResource(R.raw.ljp);
			HWPFDocument hdt = new HWPFDocument(in);
			Fields fields = hdt.getFields();
			Iterator<Field> it = fields.getFields(FieldsDocumentPart.MAIN)
					.iterator();
			while (it.hasNext()) {
				System.out.println(it.next().getType());
			}

			// 读取word文本内容
			Range range = hdt.getRange();
			System.out.println(range.text());
			// 替换文本内容
			for (Map.Entry<String, String> entry : map.entrySet()) {
				range.replaceText(entry.getKey(), entry.getValue());
			}
			ByteArrayOutputStream ostream = new ByteArrayOutputStream();
			String fileName = "" + System.currentTimeMillis();
			fileName += ".doc";
			FileOutputStream out = new FileOutputStream("/sdcard/" + fileName,
					true);
			Toast.makeText(context, "成功保存到sdcard目录下", Toast.LENGTH_LONG).show();
			hdt.write(ostream);
			// 输出字节流
			out.write(ostream.toByteArray());
			out.close();
			ostream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<AppInfo> getAllAppInfos(Context context) {
		ArrayList<AppInfo> appList = new ArrayList<AppInfo>(); // 用来存储获取的应用信息数据
		List<PackageInfo> packages = context.getPackageManager()
				.getInstalledPackages(0);

		for (int i = 0; i < packages.size(); i++) {
			PackageInfo packageInfo = packages.get(i);
			AppInfo tmpInfo = new AppInfo();
			tmpInfo.appName = packageInfo.applicationInfo.loadLabel(
					context.getPackageManager()).toString();
			tmpInfo.packageName = packageInfo.packageName;
			tmpInfo.versionName = packageInfo.versionName;
			tmpInfo.versionCode = packageInfo.versionCode;
			tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(context
					.getPackageManager());
			appList.add(tmpInfo);

		}

		return appList;
	}

	public static boolean hasInstalledOtherWPSApp(Context context) {
		boolean flag = false;
		List<PackageInfo> packages = context.getPackageManager()
				.getInstalledPackages(0);

		for (int i = 0; i < packages.size(); i++) {
			PackageInfo packageInfo = packages.get(i);
			AppInfo tmpInfo = new AppInfo();
			tmpInfo.appName = packageInfo.applicationInfo.loadLabel(
					context.getPackageManager()).toString();
			tmpInfo.packageName = packageInfo.packageName;
			tmpInfo.versionName = packageInfo.versionName;
			tmpInfo.versionCode = packageInfo.versionCode;
			tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(context
					.getPackageManager());
			if((tmpInfo.packageName.equals("com.kingsoft.moffice_pro") ||  tmpInfo.appName.equals("") )&& !String.valueOf(tmpInfo.versionCode).equals("6.3")){
				flag = true;
			}
		}
		return false;
	}
}
