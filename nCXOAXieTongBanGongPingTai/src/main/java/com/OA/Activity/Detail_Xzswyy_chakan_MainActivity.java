package com.OA.Activity;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.OA.Activity.Detail_Xzswcl_chakantabs_MainActivity.MyFragmentPagerAdapter;
import com.OA.Activity.Detail_Xzswcl_chakantabs_MainActivity.MyOnPageChangeListener;
import com.OA.Activity.Detail_Xzswcl_chakantabs_MainActivity.txListener;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Service.GetBrowsePeopleListManager;
import com.OA.Service.GetFlowFormElementsManager;
import com.OA.Service.GetShouWenCLFormElementsManager;
import com.OA.Util.FileUtil;
import com.OA.Util.IAppOfficeUtil;
import com.OA.View.DatePickDialog;
import com.kinggrid.iappoffice.IAppOffice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail_Xzswyy_chakan_MainActivity extends FragmentActivity implements OnClickListener{
	
	private TextView textView1, textView2, textView3, tv_1;
	private EditText edt_tgbt;
	private int FlowID, WFID;
	private String Title, endTime;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private int currIndex;// 当前页卡编号
	private int bmpW;// 横线图片宽度
	private int offset;// 图片移动的偏移量
	private ImageView back;
	FragmentManager fm;
	InfoFile_ infofile;
	Button btn_ty,btn_bty,btn_th,btn_ckzw;
	boolean hasDownZW = false;// 下载正文标志位
	String zw_path = "";// 正文保存路径
	IAppOffice iAppOffice = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xzsw_xzswdy_tabs_main);
		infofile = new InfoFile_(this);
		FlowID = infofile.flowid().get();
		WFID = infofile.WFID().get();
		endTime = infofile.endTime().get();
		Title = infofile.Title().get();
		getDatasFromInterface();
		InitTextView();
		
	}

	private void getDatasFromInterface() {
//				getFlowFormElements.getFlowFormElements(Detail_Xzswyy_chakan_MainActivity.this, 1, infofile
//						.infoUsername().get(), infofile.infoUserType().get(),FlowID, WFID,-1);
//	}
		getFlowFormElements.getFlowFormElements(Detail_Xzswyy_chakan_MainActivity.this, 1, infofile
				.infoUsername().get(), infofile.infoUserType().get(),FlowID, WFID,-1);
}

	private void InitViewPager() {
		fm = getSupportFragmentManager();
		mPager = (ViewPager) findViewById(R.id.viewPager);
		fragmentList = new ArrayList<Fragment>();
		Detail_Wdxzfw_chakan_Fragment1 f1 = new Detail_Wdxzfw_chakan_Fragment1()
				.newInstance("this is first fragment");
		Detail_Xzswcl_chakan_Fragment2 f2 = new Detail_Xzswcl_chakan_Fragment2()
				.newInstance("this is first fragment");
		Detail_Xzswcl_chakan_Fragment3 f3 = new Detail_Xzswcl_chakan_Fragment3()
				.newInstance("this is first fragment");
		fragmentList.add(f1);
		fragmentList.add(f2);
		fragmentList.add(f3);
		// 给ViewPager设置适配器
		mPager.setAdapter(new MyFragmentPagerAdapter(fm, fragmentList));
		mPager.setCurrentItem(0);// 设置当前显示标签页为第一页
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());// 页面变化时的监听器
	}

	public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		ArrayList<Fragment> list;

		public MyFragmentPagerAdapter(FragmentManager fm,
				ArrayList<Fragment> list) {
			super(fm);
			this.list = list;

		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {
		// private int one = offset * 2 + bmpW;// 两个相邻页面的偏移量

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO 自动生成的方法存根
			setViewsBackgroundNull();
			setSelectViewBackground(arg0);
		}

		private void setSelectViewBackground(int arg0) {
			if (arg0 == 0) {
				textView1.setBackgroundResource(R.drawable.text_view_border);
			}
			if (arg0 == 1) {
				textView2.setBackgroundResource(R.drawable.text_view_border);
			}
			if (arg0 == 2) {
				textView3.setBackgroundResource(R.drawable.text_view_border);
			}

		}

		private void setViewsBackgroundNull() {
			textView1.setBackground(null);
			textView2.setBackground(null);
			textView3.setBackground(null);

		}
	}

	private void InitTextView() {
		tv_1 = (TextView) findViewById(R.id.tv_1);
		zw_path = Environment.getExternalStorageDirectory() + "/OADownFile/"
				+ infofile.infoUsername().get() + "/";
		edt_tgbt = (EditText) findViewById(R.id.edt_tgbt);
		edt_tgbt.setEnabled(false);
		textView1 = (TextView) findViewById(R.id.tv_a);
		textView2 = (TextView) findViewById(R.id.tv_b);
		textView3 = (TextView) findViewById(R.id.tv_c);
		textView1.setOnClickListener(new txListener(0));
		textView2.setOnClickListener(new txListener(1));
		textView3.setOnClickListener(new txListener(2));
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(this);
		tv_1.setText(endTime);
		edt_tgbt.setText(Title);
		btn_ty = (Button) findViewById(R.id.btn_ty);
		btn_bty = (Button) findViewById(R.id.btn_bty);
		btn_th = (Button) findViewById(R.id.btn_th);
		btn_ckzw = (Button) findViewById(R.id.btn_ckzw);
		btn_ty.setVisibility(View.GONE);
		btn_bty.setVisibility(View.GONE);
		btn_th.setVisibility(View.GONE);
		btn_ckzw.setOnClickListener(this);
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/OADownFile/");
		if (!file.exists()) {
			file.mkdir();
		}
		File file1 = new File(zw_path);
		if (!file1.exists()) {
			file1.mkdir();
		}
		initFilePath();
		iAppOffice = IAppOfficeUtil.getInstance(this);
		if (iAppOffice == null) {
			// btn_qczw.setClickable(false);
			Toast.makeText(this, "请正确安装wps应用！", Toast.LENGTH_SHORT).show();
		} else {
			// btn_qczw.setClickable(true);
		}
	}

	public class txListener implements View.OnClickListener {
		private int index = 0;

		public txListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);

		}
	}
	private void initFilePath() {

		File file = new File(zw_path);
		if (!file.exists()) {
			file.mkdir();
		}
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日HH时mm分ss秒");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		zw_path = zw_path + infofile.infoUsername().get() + str + ".doc";

	}
	
	@Override
	protected void onDestroy() {
		IAppOfficeUtil.exit(iAppOffice);
		super.onDestroy();
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			this.finish();
			break;
		case R.id.ll1:
			DatePickDialog.showDateCheckDialog(this, tv_1, true);
			break;
		case R.id.btn_ckzw:
			if (hasDownZW) {
				IAppOfficeUtil.OpenOfficeFile(iAppOffice, zw_path);
			} else {
				getFlowFormElements.LoadArchiveData(
						Detail_Xzswyy_chakan_MainActivity.this, 2, 0,
						0, infofile.WFID().get(), 0);
			}
			break;
		default:
			break;
		}

	}

	GetFlowFormElementsManager getFlowFormElements = new GetFlowFormElementsManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess() != null
					&& handleResult.getiSuccess().equals("success")) {
				switch (paramInt) {
				case 1:
					getBrowseUser.getBrowseUser(Detail_Xzswyy_chakan_MainActivity.this, 1, WFID);
					break;
				case 2:
					FileUtil.writeBase64StringToFile(handleResult.getContent(),
							zw_path);
					hasDownZW = true;
					if (iAppOffice != null) {
						IAppOfficeUtil.OpenOfficeFile(iAppOffice, zw_path);
					}
					break;
				default:
					break;
				}
				
			}
		}
	};
	GetBrowsePeopleListManager getBrowseUser = new GetBrowsePeopleListManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess() != null
					&& handleResult.getiSuccess().equals("success")) {

				switch (paramInt) {
				case 1:
					getBrowseUser.getNotBrowseUser(Detail_Xzswyy_chakan_MainActivity.this, 2,WFID);
					break;
				case 2:
					getBrowseUser.getOverBrowseUser(Detail_Xzswyy_chakan_MainActivity.this, 3,WFID);
					break;
				case 3:
					InitViewPager();
					break;
				default:
					break;
				}

			}
		}
	};
}

