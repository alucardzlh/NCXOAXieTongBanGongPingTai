package com.OA.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
//import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.OA.Util.FileUtil;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

/**
 * 选择获取文件的方式activity
 * 
 * @author rjh
 * @data 2014-12-25
 */
@EActivity(R.layout.layout_select_pic)
public class UploadFile_CheckFile_MainActivity extends Activity implements OnClickListener {
	// 使用照相机拍照获取图片
		public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
		// 使用系统中的文件
		public static final int SELECT_FILE_BY_PICK_SYSTEM = 2;
		// 从Intent获取文件路径的KEY
		public static final String KEY_FILE_PATH = "file_path";
		private static final String TAG = "UploadFile_CheckFile_MainActivity";

		/** 获取到的文件路径 */
		private String filePath;
		private Intent lastIntent;
		private Uri fileUri;

		@ViewById
		LinearLayout dialog_layout;
		@ViewById
		Button btn_take_photo, btn_pick_file, btn_cancel_buttom;

		@AfterViews
		void initView() {
			lastIntent = getIntent();// 获取intent
			btn_cancel_buttom.setOnClickListener(this);
			btn_pick_file.setOnClickListener(this);
			btn_take_photo.setOnClickListener(this);
			dialog_layout.setOnClickListener(this);

		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_take_photo:
				// 拍照
				takePhoto();
				break;
			case R.id.btn_pick_file:
				// 相册
				pickPhoto();
				break;
			case R.id.btn_cancel_buttom:
				finish();
				break;
			case R.id.dialog_layout:
				finish();
				break;
			default:
				break;
			}
		}

		/***
		 * 从相册中取图片
		 */
		private void pickPhoto() {
			Intent intent = new Intent();
			intent.setType("*/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(intent, SELECT_FILE_BY_PICK_SYSTEM);
		}

		/**
		 * 拍照获取图片
		 */
		private void takePhoto() {
			// 执行拍照前，应该先判断SD卡是否存在
			String SDState = Environment.getExternalStorageState();
			if (SDState.equals(Environment.MEDIA_MOUNTED)) {

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// "android.media.action.IMAGE_CAPTURE"
				/***
				 * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
				 * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
				 */
				ContentValues values = new ContentValues();
				fileUri = this.getContentResolver().insert(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
				intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
				/** ----------------- */
				startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
			} else {
				Toast.makeText(this, "内存卡不存在", Toast.LENGTH_LONG).show();
			}
		}

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			if (resultCode == Activity.RESULT_OK) {
				doPhoto(requestCode, data);
			}
			super.onActivityResult(requestCode, resultCode, data);
		}

		/**
		 * 选择图片后，获取图片的路径
		 * 
		 * @param requestCode
		 * @param data
		 */
		private void doPhoto(int requestCode, Intent data) {
			if (requestCode == SELECT_FILE_BY_PICK_SYSTEM) // 从相册取图片，有些手机有异常情况，请注意
			{
				if (data == null) {
					Toast.makeText(this, "选择文件出错111", Toast.LENGTH_LONG).show();
					return;
				}
				fileUri = data.getData();
				if (fileUri == null) {
					Toast.makeText(this, "选择文件出错222", Toast.LENGTH_LONG).show();
					return;
				}
			}

//			String[] pojo = {MediaStore.Images.Media.DATA };
//			Cursor cursor = managedQuery(fileUri, pojo, null, null, null);
//			if (cursor != null) {
//				int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
//				cursor.moveToFirst();
//				filePath = cursor.getString(columnIndex);
//			}
			
			filePath = FileUtil.getPath(UploadFile_CheckFile_MainActivity.this,fileUri);
			Log.e(TAG, "imagePath = " + filePath);
			/*
			 * if(picPath != null && ( picPath.endsWith(".png") ||
			 * picPath.endsWith(".PNG") ||picPath.endsWith(".jpg")
			 * ||picPath.endsWith(".JPG") ))
			 */
			// 将获取的图片路径进行回调
			if (filePath != null) {
				lastIntent.putExtra(KEY_FILE_PATH, filePath);
				setResult(Activity.RESULT_OK, lastIntent);
		//		cursor.close();
				finish();
			} else {
				Toast.makeText(this, "选择文件不正确!", Toast.LENGTH_LONG).show();

			}
		}
//		Cursor cursor;
//		private String getRealPathFromURI(Uri contentUri) {
//		    String[] proj = { MediaStore.Files.getContentUri(volumeName) };
//		    CursorLoader loader = new CursorLoader(UploadFile_CheckFile_MainActivity.this, contentUri, proj, null, null, null);
//		     cursor = loader.loadInBackground();
//		    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//		    cursor.moveToFirst();
//		    return cursor.getString(column_index);
//		}
}
