package com.OA.View;

import com.OA.Activity.R;
import com.OA.Entity.HandleResult;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Dialog_Toast {
	
	public static Dialog create(Context context) {
		final Dialog dlg = new Dialog(context, R.style.MyDialog);
		final LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View layout = inflater.inflate(R.layout.dialog_toast, null);
		layout.setMinimumWidth(2000);
		Window window = dlg.getWindow();

		window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.x = 0;
		lp.y = -1000;
		lp.gravity = Gravity.BOTTOM;
		dlg.onWindowAttributesChanged(lp);
		dlg.setContentView(layout);
		dlg.setCanceledOnTouchOutside(true);

		// 设置遮罩层的透明度
		// WindowManager.LayoutParams winLP = window.getAttributes();
		// winLP.dimAmount = 0.3f;
		// winLP.width = CtripAppController.getWindowWidth();
		// window.setAttributes(winLP);

		/*
		 * if(callback!=null){ callback.call(dlg, layout); }
		 */

		return dlg;
	}


	public static void dialog(Context context, final Handler callbackHandler ,final Integer NoticeID) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage("确定删除该条记录吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Message msg = new Message();
				Bundle bundle = new Bundle();
				bundle.putInt("noticeID", NoticeID);
				msg.setData(bundle);
				msg.what = 0x123;
				callbackHandler.sendMessage(msg);
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();		
	}

	public static void qianshoudialog1(Context context, final Handler callbackHandler ,final Integer NoticeID) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage("签收成功");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();		
	}
		public static void qianshoudialog2(Context context, final Handler callbackHandler ,final Integer NoticeID) {
			AlertDialog.Builder builder = new Builder(context);
			builder.setMessage("签收失败");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();		
		}
			public static void qianshoudialog3(Context context, final Handler callbackHandler ,final Integer NoticeID) {
				AlertDialog.Builder builder = new Builder(context);
				builder.setMessage("安全验证未通过");
				builder.setTitle("提示");
				builder.setPositiveButton("确认", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		
		builder.create().show();		
	}


	public static void dialog(Context context, String string) {
		// TODO 自动生成的方法存根
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage("这条数据已审核通过,不能修改");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", null);
		builder.create().show();
	}

}
