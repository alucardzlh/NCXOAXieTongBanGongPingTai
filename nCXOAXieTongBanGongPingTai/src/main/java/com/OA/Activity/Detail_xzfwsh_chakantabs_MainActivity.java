package com.OA.Activity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.dom4j.swing.BranchTreeNode;

import com.OA.Data.Constants;
import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.FirstNextStepDatas;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Service.BackApplyManager;
import com.OA.Service.GetBrowsePeopleListManager;
import com.OA.Service.GetFlowFormElementsManager;
import com.OA.Util.FileUtil;
import com.OA.Util.IAppOfficeUtil;
import com.OA.View.DatePickDialog;
import com.kinggrid.iappoffice.IAppOffice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 我的行政发文查看
 * 
 * @author Administrator
 *
 */
public class Detail_xzfwsh_chakantabs_MainActivity extends FragmentActivity
		implements OnClickListener {
	Context context;
	InfoFile_ infoFile;
	private TextView textView1, textView2, textView3, tv_1, tv_gwbt, tv_llry;
	private EditText edt_tgbt, edt_shyj;
	private int FlowID, WFID;
	private String Title, endTime;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private int currIndex;// 当前页卡编号
	private int bmpW;// 横线图片宽度
	private int offset;// 图片移动的偏移量
	private ImageView back;
	private int WFStepID;
	FragmentManager fm;
	InfoFile_ infofile;
	String statusOfShenhe = "";
	FirstNextStepDatas datas;
	IAppOffice iAppOffice = null;
	boolean hasDownZW = false;// 下载正文标志位
	String zw_path = "";// 正文保存路径

	int ArcStepID = 0;

	LinearLayout ll_btn, ll_checkpeople, ll_checkpeople1,ll_textup1;

	Button btn_ty, btn_bty, btn_th, btn_ckzw;
	private String tag;
	receive receive;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xzfw_xzfwsh_tabs_main);
		infofile = new InfoFile_(this);
		FlowID = infofile.FlowID().get();
		WFID = infofile.WFID().get();
		endTime = infofile.endTime().get();
		Title = infofile.Title().get();
		statusOfShenhe = infofile.statusOfSHENHE().get();
		WFStepID = infofile.WFStepID().get();

		InitTextView();
		getDatasFromInterface();

		// 生成广播处理
		receive = new receive();
		// 实例化过滤器并设置要过滤的广播
		IntentFilter intentFilter = new IntentFilter(
				"com.kinggrid.iappoffice.save");
		intentFilter.addAction("checkSuccess");
		// 注册广播
		this.registerReceiver(receive, intentFilter);
	}

	private void getDatasFromInterface() {
		getFlowFormElements.getFlowFormElements(
				Detail_xzfwsh_chakantabs_MainActivity.this, 1, infofile
						.infoUsername().get(), infofile.infoUserType().get(),
				FlowID, WFID, Integer.valueOf(infoFile.CurNode().get())
						.intValue());
	}

	private void InitViewPager() {

		fm = getSupportFragmentManager();

		mPager = (ViewPager) findViewById(R.id.viewPager);
		fragmentList = new ArrayList<Fragment>();
		Detail_xzfwsh_chakan_Fragment1 f1 = new Detail_xzfwsh_chakan_Fragment1()
				.newInstance("this is first fragment");
		Detail_xzfwsh_chakan_Fragment2 f2 = new Detail_xzfwsh_chakan_Fragment2()
				.newInstance("this is first fragment");
		Detail_xzfwsh_chakan_Fragment3 f3 = new Detail_xzfwsh_chakan_Fragment3()
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
		infoFile = new InfoFile_(this);
		zw_path = Environment.getExternalStorageDirectory() + "/OADownFile/"
				+ infoFile.infoUsername().get() + "/";
		tv_1 = (TextView) findViewById(R.id.tv_1);
		edt_tgbt = (EditText) findViewById(R.id.edt_tgbt);
		edt_shyj = (EditText) findViewById(R.id.edt_shyj);
		edt_tgbt.setEnabled(false);
		textView1 = (TextView) findViewById(R.id.tv_a);
		textView2 = (TextView) findViewById(R.id.tv_b);
		textView3 = (TextView) findViewById(R.id.tv_c);
		tv_llry = (TextView) findViewById(R.id.tv_llry);
		btn_ty = (Button) findViewById(R.id.btn_ty);
		btn_bty = (Button) findViewById(R.id.btn_bty);
		btn_th = (Button) findViewById(R.id.btn_th);
		btn_ckzw = (Button) findViewById(R.id.btn_ckzw);
		tv_gwbt = (TextView) findViewById(R.id.tv_gwbt);
		tv_gwbt.setText(infofile.Title().get());
		ll_btn = (LinearLayout) findViewById(R.id.ll_btn);
		ll_checkpeople = (LinearLayout) findViewById(R.id.ll_checkpeople);
		ll_checkpeople1 = (LinearLayout) findViewById(R.id.ll_checkpeople1);
		ll_textup1 = (LinearLayout) findViewById(R.id.ll_textup1);
		btn_ty.setOnClickListener(this);
		btn_bty.setOnClickListener(this);
		btn_th.setOnClickListener(this);
		btn_ckzw.setOnClickListener(this);
		textView1.setOnClickListener(new txListener(0));
		textView2.setOnClickListener(new txListener(1));
		textView3.setOnClickListener(new txListener(2));
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(this);
		tv_1.setText(endTime);
		edt_tgbt.setText(Title);
		if (statusOfShenhe.equals("1000")) {
			ll_textup1.setVisibility(View.VISIBLE);
			btn_ty.setVisibility(View.VISIBLE);
			btn_bty.setVisibility(View.VISIBLE);
			btn_th.setVisibility(View.VISIBLE);
		} else {
			ll_textup1.setVisibility(View.GONE);
			btn_ty.setVisibility(View.GONE);
			btn_bty.setVisibility(View.GONE);
			btn_th.setVisibility(View.GONE);
		}

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
		infofile.edit().ArcStepID().put(0).apply();// 初始化公文ID
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}

	@Override
	protected void onDestroy() {
		IAppOfficeUtil.exit(iAppOffice);
		receive.clearAbortBroadcast();
		this.unregisterReceiver(receive);
		super.onDestroy();
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
		case R.id.btn_ty:
			Constants.shouwenOrfawen = 2000;
			Constants.passOrNot = 1;
			Constants.ShenHeYiJian = edt_shyj.getText().toString();
			Constants.userName = cxry_username.toString();
			Constants.userCode = cxry_usercode.toString();
			getFlowFormElements.GetCheckNextStepInfo(this, 1000, FlowID,
					infofile.WFID().get(), 0, infofile.CurNode().get() + "",
					"1", 0, infofile.infoUsername().get());
			break;
		case R.id.btn_bty:
			Constants.shouwenOrfawen = 2000;
			Constants.passOrNot = 0;
			Constants.ShenHeYiJian = edt_shyj.getText().toString();
			Constants.userName = cxry_username.toString();
			Constants.userCode = cxry_usercode.toString();
			getFlowFormElements.GetCheckNextStepInfo(this, 1000, FlowID,
					infofile.WFID().get(), 0, infofile.CurNode().get() + "",
					"0", 0, infofile.infoUsername().get());
			break;
		case R.id.btn_th:
			showInfo();
			break;
		case R.id.btn_ckzw:
			if (hasDownZW) {
				IAppOfficeUtil.OpenOfficeFile(iAppOffice, zw_path);
			} else {
				getFlowFormElements.LoadArchiveData(
						Detail_xzfwsh_chakantabs_MainActivity.this, 2000, 0,
						ArcStepID, infofile.WFID().get(), infofile.WFStepID()
								.get());
			}
			// FileUtil.writeBase64StringToFile(
			// handleResult.getContent(), zw_path);
			// if (iAppOffice != null) {
			// IAppOfficeUtil.OpenOfficeFile(iAppOffice, zw_path);
			// }
			break;
		default:
			break;
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
		zw_path = zw_path + infoFile.infoUsername().get() + str + ".doc";

	}

	public void showInfo() {
		new AlertDialog.Builder(this)
				.setTitle("提示")
				.setMessage("确定要退回吗?")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						backApply.getBackApplyManager(
								Detail_xzfwsh_chakantabs_MainActivity.this, 1,
								WFStepID, infofile.infoUsername().get(),
								infofile.infoUserType().get(), edt_shyj
										.getText().toString());
					}
				})
				.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
	}

	BackApplyManager backApply = new BackApplyManager() {

		// private MyMeetingDetail_Bean bean;

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess().equals("success")) {
				btn_ty.setVisibility(View.GONE);
				btn_bty.setVisibility(View.GONE);
				btn_th.setVisibility(View.GONE);
				// bean = Constants.mymeetingdetail_bean;
				// if (bean != null && bean.getStatus().equals("2000")) {
				//
				// } else if (bean != null && bean.getStatus().equals("1000")) {
				// Toast.makeText(context, "失败", Toast.LENGTH_LONG)
				// .show();
				// }
			}

		}
	};
	GetFlowFormElementsManager getFlowFormElements = new GetFlowFormElementsManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			switch (paramInt) {
			case 1:
				// if (handleResult.getiSuccess() != null
				// && handleResult.getiSuccess().equals("success")) {
				getBrowseUser.getBrowseUser(
						Detail_xzfwsh_chakantabs_MainActivity.this, 1, WFID);
				// }
				if (handleResult.isSelPeople()) {
					ll_checkpeople.setVisibility(View.VISIBLE);
					ll_checkpeople1.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Detail_xzfwsh_chakantabs_MainActivity.this
									.startActivityForResult(
											new Intent(
													Detail_xzfwsh_chakantabs_MainActivity.this,
													PeopleCheck_Activity.class),
											3000);
						}
					});
				}
				break;
			case 1000:
				if (handleResult.getiSuccess() != null
						&& handleResult.getiSuccess().equals("success")) {
					datas = handleResult.getDatas();
					infoFile.edit().TypeOfLast().put(1000).apply();
					if (!datas.getFlowAction().equals("BranchSel")) {
						Intent intent = new Intent();
						intent.setClass(
								Detail_xzfwsh_chakantabs_MainActivity.this,
								FirstNextStepActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("stepName", datas.getFlowDescript());
						bundle.putString("checkCode", datas.getCheckCode());
						bundle.putString("checkName", datas.getCheckName());
						bundle.putString("NextStepID", datas.getNextStepID());
						intent.putExtras(bundle);
						startActivity(intent);
						// Detail_xzfwsh_chakantabs_MainActivity.this.finish();
					} else {
						Intent intent = new Intent();
						intent.setClass(
								Detail_xzfwsh_chakantabs_MainActivity.this,
								SecondNextStepActivity.class);
						infofile.edit()
								.FlowStepID()
								.put(Integer.valueOf(datas.getNextStepID())
										.intValue()).apply();
						startActivity(intent);
						// Detail_xzfwsh_chakantabs_MainActivity.this.finish();
					}
				}
				break;
			case 2000:
				if (handleResult.getiSuccess() != null
						&& handleResult.getiSuccess().equals("success")) {
					FileUtil.writeBase64StringToFile(handleResult.getContent(),
							zw_path);
					hasDownZW = true;
					if (iAppOffice != null) {
						IAppOfficeUtil.OpenOfficeFile(iAppOffice, zw_path);
					}
				}
				break;
			case 3000:
				if (handleResult.getiSuccess() != null
						&& handleResult.getiSuccess().equals("success")) {
					ArcStepID = handleResult.getArcStepID();
					infoFile.edit().ArcStepID().put(ArcStepID).apply();
				}
				break;
			default:
				break;
			}

		}
	};

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (!Thread.currentThread().isInterrupted()) {
				switch (msg.what) {
				case 1234:
					String str_content = "";
					File file = new File(zw_path);
					if (file.exists()) {
						byte[] bytes = null;
						try {
							bytes = FileUtil.getBytesFromFile(file);
						} catch (IOException e) {
							e.printStackTrace();
						}
						str_content = Base64.encodeToString(bytes,
								Base64.DEFAULT);
						getFlowFormElements.SaveArchiveData(
								Detail_xzfwsh_chakantabs_MainActivity.this,
								3000, ArcStepID, infofile.WFID().get(),
								infofile.WFStepID().get(), infoFile
										.infoUsername().get(), infofile
										.infoUserType().get(), str_content);
					}

					break;
				default:
					break;
				}
			}
		}

	};

	public class receive extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String str = intent.getAction();
			if (str.equals("com.kinggrid.iappoffice.save")) {
				handler.sendEmptyMessage(1234);
			} else if (str.equals("checkSuccess")) {
				btn_ty.setVisibility(View.GONE);
				btn_bty.setVisibility(View.GONE);
				btn_th.setVisibility(View.GONE);
			}

		}

	}

	GetBrowsePeopleListManager getBrowseUser = new GetBrowsePeopleListManager() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			// if (handleResult.getiSuccess() != null
			// && handleResult.getiSuccess().equals("success")) {

			switch (paramInt) {
			case 1:
				getBrowseUser.getNotBrowseUser(
						Detail_xzfwsh_chakantabs_MainActivity.this, 2, WFID);
				break;
			case 2:
				getBrowseUser.getOverBrowseUser(
						Detail_xzfwsh_chakantabs_MainActivity.this, 3, WFID);
				break;
			case 3:
				InitViewPager();
				break;
			default:
				break;
			}

			// }
		}
	};

	StringBuffer cxry_usercode = new StringBuffer();
	StringBuffer cxry_username = new StringBuffer();

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 3000 && resultCode == Activity.RESULT_OK) {
			for (int i = 0; i < Constants.list_right.size(); i++) {
				if (tv_llry.getText().toString().trim()
						.contains(Constants.list_right.get(i).getUserName())) {
					continue;
				}
				if (i == Constants.list_right.size() - 1) {
					if (tv_llry.getText().toString() != null
							&& !tv_llry.getText().toString().equals("")) {
						cxry_username.append(","
								+ Constants.list_right.get(i).getUserName());
						cxry_usercode.append(","
								+ Constants.list_right.get(i).getUserCode());
					} else {
						cxry_username.append(Constants.list_right.get(i)
								.getUserName());
						cxry_usercode.append(Constants.list_right.get(i)
								.getUserCode());
					}

				} else {
					if (tv_llry.getText().toString() != null
							&& !tv_llry.getText().toString().equals("")) {
						cxry_username.append(","
								+ Constants.list_right.get(i).getUserName());
						cxry_usercode.append(","
								+ Constants.list_right.get(i).getUserCode());
					} else {
						cxry_username.append(Constants.list_right.get(i)
								.getUserName() + ",");
						cxry_usercode.append(Constants.list_right.get(i)
								.getUserCode() + ",");
					}
				}
			}
			tv_llry.setText(cxry_username);
		}
	}

}
