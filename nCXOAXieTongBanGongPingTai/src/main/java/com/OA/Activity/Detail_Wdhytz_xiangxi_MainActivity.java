package com.OA.Activity;

import java.util.ArrayList;

import com.OA.Data.Constants;
import com.OA.Data.InfoFile_;
import com.OA.Entity.HandleResult;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Service.Hyxx_Detail_ShManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 我的会议通知详细
 * @author Administrator
 *
 */
public class Detail_Wdhytz_xiangxi_MainActivity extends FragmentActivity  implements OnClickListener{
	 
	 
	private TextView textView1,textView2;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private int currIndex;// 当前页卡编号
	private int bmpW;// 横线图片宽度
	private int offset;// 图片移动的偏移量
	private ImageView back;
		FragmentManager fm;
		private Button btn_tjry;
		private Intent intent;
		private String RoomLogID;
		private Bundle bundle;

	@Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
      setContentView(R.layout.hyxx_wdhytz_tabs_main);
        btn_tjry = (Button) findViewById(R.id.btn_tjry);
        intent = getIntent();
        RoomLogID = intent.getExtras().getString("RoomLogID");
		btn_tjry.setOnClickListener(this);
    	fm = getSupportFragmentManager();
    	InitTextView();
        InitViewPager();
	}

//	@Override
//	protected void onSaveInstanceState(Bundle outState) {
////		super.onSaveInstanceState(outState);
//	}
	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.viewPager);
		fragmentList = new ArrayList<Fragment>();
		Detail_Wdhytz_xiangxi_Fragment1 f1=new Detail_Wdhytz_xiangxi_Fragment1().newInstance("this is first fragment");;
		Detail_Wdhytg_xiangxi_Fragment2 f2=new Detail_Wdhytg_xiangxi_Fragment2().newInstance("this is first fragment");;
		bundle = new Bundle();
		bundle.putString("RoomLogID",RoomLogID);
		f1.setArguments(bundle);
		f2.setArguments(bundle);
		fragmentList.add(f1);
		fragmentList.add(f2);
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
//		private int one = offset * 2 + bmpW;// 两个相邻页面的偏移量

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
			if(arg0==0){
				textView1.setBackgroundResource(R.drawable.text_view_border);
			}
			if(arg0==1){
				textView2.setBackgroundResource(R.drawable.text_view_border);
			}
		
			
		}

		 private void setViewsBackgroundNull() {
			 
			textView1.setBackground(null);
			textView2.setBackground(null);
		
			
		}
	}




	private void InitTextView() {
		
			textView1 = (TextView) findViewById (R.id.tv_a);
	        textView2 = (TextView) findViewById (R.id.tv_b);
	        textView1.setOnClickListener(new txListener(0));
	        textView2.setOnClickListener(new txListener(1));
	        back = (ImageView) findViewById(R.id.imageView3);
			back.setOnClickListener(this);
			
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageView3:
			this.finish();
			break;
		case R.id.btn_tjry:
			Intent intent= new Intent();
			intent.setClass(Detail_Wdhytz_xiangxi_MainActivity.this, TjryDialog_Toast_MainAcitvity.class);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}
}

