package com.OA.Util;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.widget.Toast;

import com.OA.Data.Constants;
import com.kinggrid.iappoffice.IAppOffice;

public class IAppOfficeUtil {
	static int mResult;
	final static int INIT_FAIL = 0;
	final static int INIT_SUCCESS = 1;
	final static int COPYRIGHT_VALUE_NULL = 1000;
	final static int COPYRIGHT_RESULT_FAIL = 1001;
	final static int COPYRIGHT_RESULT_SUCCESS = 1002;
	static Context contexts; 
	//static Activity activity;
	public static IAppOffice getInstance(Activity context) {
//		if(CommUtil.hasInstalledOtherWPSApp(context)){
//			showToast("请联系服务供应商获取正确WPS手机版！");
//			return null;
//		}
		contexts = context;
		IAppOffice iAppOffice = new IAppOffice(context);
		iAppOffice.setCopyRight(Constants.COPYRIGHT_VALUE_FORTRY);
		mResult = iAppOffice.init();
		if (!iAppOffice.isWPSInstalled()) {
			showToast("请安装WPS手机版！");
			return null;
		}
		if (!showCopyRightResult()) {
			return null;
		}
		return iAppOffice;
	}

	public static void CreateAndOpenNewOffice(IAppOffice iAppOffice, String path) {
		File file = new File(path);
		if (file.exists()) {
		//	showToast("文件" + path + "已存在");
			OpenOfficeFile(iAppOffice, path);
		} else {
			iAppOffice.setFileName(path);
			iAppOffice.newAndOpen();
		}
	}

	public static void OpenOfficeFile(IAppOffice iAppOffice, String path) {
		iAppOffice.setFileName(path);
		iAppOffice.appOpen(true);
	}

	public static boolean showCopyRightResult() {
		if (mResult == COPYRIGHT_VALUE_NULL) {
			showToast("请授权！");
			return false;
		} else if (mResult == COPYRIGHT_RESULT_FAIL) {
			showToast("试用期已到，请更新版本！");
			return false;
		} else if (mResult == INIT_SUCCESS) {
			return true;
		} else if (mResult == INIT_FAIL) {
			return true;
		} else {
			return false;
		}
	}

	public static void showToast(String str) {
		Toast.makeText(contexts, str, Toast.LENGTH_SHORT).show();
	}

	public static void InsertText(IAppOffice iAppOffice, String content){
		if(iAppOffice!=null){
			try {
				iAppOffice.acceptAllRevision();
				iAppOffice.enterReviseMode();
				iAppOffice.insertText(content);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void setText(IAppOffice iAppOffice, String content){
		if(iAppOffice!=null){
			iAppOffice.acceptAllRevision();
			iAppOffice.enterReviseMode();
			iAppOffice.setText(content);
		}
	}
	
	public static void exit(IAppOffice iAppOffice) {
		iAppOffice.unInit();
	}
}
