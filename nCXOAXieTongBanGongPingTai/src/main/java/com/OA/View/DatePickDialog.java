package com.OA.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.OA.Activity.R;
import com.OA.DatePickDialog.JudgeDate;
import com.OA.DatePickDialog.ScreenInfo;
import com.OA.DatePickDialog.WheelMain;
import com.OA.DatePickDialog.WheelMain.OnChangeListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


public class DatePickDialog {

	public static void showDateCheckDialog(Activity activity,
			final TextView tv, boolean flag, final OnClickListener c) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		final WheelMain wheelMain;
		LayoutInflater inflater = LayoutInflater.from(activity);
		final View timepickerview = inflater.inflate(R.layout.timepicker, null);
		ScreenInfo screenInfo = new ScreenInfo(activity);
		wheelMain = new WheelMain(timepickerview, flag);
		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		wheelMain.setOnchangerlistener(new OnChangeListener() {

			@Override
			public void change(String time) {
				// TODO Auto-generated method stub
				Log.i("test", time);
				builder.setTitle(time);
			}
		});
		wheelMain.screenheight = screenInfo.getHeight();
		String time = tv.getText().toString();
		Calendar calendar = Calendar.getInstance();
		if (JudgeDate.isDate(time, "yyyy-MM-dd")) {
			try {
				calendar.setTime(dateFormat.parse(time));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		wheelMain.initDateTimePicker(year, month, day, hour, minute, second);
		builder.setTitle("请选择日期").setView(timepickerview)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						tv.setText(wheelMain.getTime());
						if (c != null) {
							c.onClick(dialog, which);
						}
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});

		builder.show();

	}

	public static void showDateCheckDialog(Activity activity,
			final TextView tv, boolean flag) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		final WheelMain wheelMain;
		LayoutInflater inflater = LayoutInflater.from(activity);
		final View timepickerview = inflater.inflate(R.layout.timepicker, null);
		ScreenInfo screenInfo = new ScreenInfo(activity);
		wheelMain = new WheelMain(timepickerview, flag);
		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		wheelMain.setOnchangerlistener(new OnChangeListener() {

			@Override
			public void change(String time) {
				// TODO Auto-generated method stub
				Log.i("test", time);
				builder.setTitle(time);
			}
		});
		wheelMain.screenheight = screenInfo.getHeight();
		String time = tv.getText().toString();
		Calendar calendar = Calendar.getInstance();
		if (JudgeDate.isDate(time, "yyyy-MM-dd")) {
			try {
				calendar.setTime(dateFormat.parse(time));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		wheelMain.initDateTimePicker(year, month, day, hour, minute, second);
		builder.setTitle("请选择日期").setView(timepickerview)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						tv.setText(wheelMain.getTime());
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});

		builder.show();
	}
}
