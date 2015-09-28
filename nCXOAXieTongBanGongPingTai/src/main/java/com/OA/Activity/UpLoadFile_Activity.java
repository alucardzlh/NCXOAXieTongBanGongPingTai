package com.OA.Activity;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.OA.Data.Constants;
import com.OA.Data.InfoFile;
import com.OA.Data.InfoFile_;
import com.OA.Entity.FileDetail_Bean;
import com.OA.Entity.File_ID_Name_Bean;
import com.OA.Entity.HandleResult;
import com.OA.Entity.IDAndValue_Bean;
import com.OA.Service.FileDownloadAndUploadManager;
import com.OA.Util.FileUtil;
import com.OA.Interface.MyCallBack;
import com.OA.Util.PriUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class UpLoadFile_Activity extends BaseActivity implements
		OnClickListener {
	InfoFile_ infoFile;
	LinearLayout ib_return;
	Button btn_close, btn_add;
	ImageButton ib_return2;
	ListView lv_base;
	UploadFile_Adapter adapter;

	TextView tv_download;

	InputStream inputStream = null;
	URLConnection connection = null;
	OutputStream outputStream;
	int FileLength;
	int DownedFileLength = 0;

	ProgressBar progressBar;

	boolean uploadFinished = false;

	boolean downloadFinished = false;

	int type = 0;// 判断次activity是用来上传附件还是用来下载附件,1000表示上传，2000表示下载

	List<Map<String, Object>> list_show = new ArrayList<Map<String, Object>>();

	List<File_ID_Name_Bean> list_files = new ArrayList<File_ID_Name_Bean>();

	Intent intent;
	
	String savePAth = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uploadfile_main);
		initViewAndListener();
	}

	private void initViewAndListener() {
		infoFile = new InfoFile_(this);
		savePAth = Environment.getExternalStorageDirectory()
				+ "/OADownFile/"+infoFile.infoUsername().get()+"/";
		intent = getIntent();
		type = getIntent().getFlags();
		progressBar = (ProgressBar) findViewById(R.id.progress_horizontal);
		ib_return = (LinearLayout) findViewById(R.id.ib_return);
		
		ib_return2=(ImageButton) findViewById(R.id.ib_return2);
		
		btn_close = (Button) findViewById(R.id.btn_close);
		btn_add = (Button) findViewById(R.id.btn_add);
		tv_download = (TextView) findViewById(R.id.tv_download);
		lv_base = (ListView) findViewById(R.id.lv_base);
		ib_return.setOnClickListener(this);
		
		ib_return2.setOnClickListener(this);
		
		btn_add.setOnClickListener(this);
		btn_close.setOnClickListener(this);
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/OADownFile/");
		if(!file.exists()){
			file.mkdir();
		}
		File file1 = new File(savePAth);
		if(!file1.exists()){
			file1.mkdir();
		}
		initViewByType();
	}

	private void initViewByType() {
		switch (type) {
		case 1000:

			btn_add.setVisibility(View.VISIBLE);
			break;
		case 2000:
			adapter = new UploadFile_Adapter(UpLoadFile_Activity.this,
					list_show);
			lv_base.setAdapter(adapter);
			lv_base.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					final int position1 = position;
					File file = new File((String) list_show.get(position1).get(
							"path"));
					if (file.exists()) {
						FileUtil.openFile(UpLoadFile_Activity.this, file);
					//	Toast.makeText(UpLoadFile_Activity.this,
					//			(String) list_show.get(position).get("path"),
					//			Toast.LENGTH_SHORT).show();
					} else {
						tv_download.setVisibility(View.VISIBLE);
						progressBar.setVisibility(View.VISIBLE);
						Thread thread = new Thread() {
							public void run() {
								try {
//									 if (list_show.get(position1).get("ext")
//									 .equals("jpg")
//									 || list_show.get(position1)
//									 .get("ext").equals("png")
//									 || list_show.get(position1)
//									 .get("ext").equals("JPEG")
//									 || list_show.get(position1)
//									 .get("ext").equals("bmp")
//									 || list_show.get(position1)
//									 .get("ext").equals("gif")) {
//											 DownFilePicture((String) list_show.get(position1)
//														.get("url"), handler,
//														(String) list_show.get(position1)
//																.get("name"));
//									 } else {

									DownFile((String) list_show.get(position1)
									.get("url"), handler,
											(String) list_show.get(position1)
													.get("name"),position1);
					//				 }

								} catch (Exception e) {
									Log.e("1111", e.toString());
								}
							}
						};
						thread.start();
					}
				}
			});
			btn_add.setVisibility(View.GONE);
			list_files = Constants.list_files;
			for (File_ID_Name_Bean bean : list_files) {
				manager.DownloadFile(UpLoadFile_Activity.this, 2,
						bean.getFileID());
			}
			break;
		case 0:
			break;
		default:
			break;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (!Thread.currentThread().isInterrupted()) {
				switch (msg.what) {
				case 0:
					progressBar.setMax(FileLength);
					Log.i("文件长度----------->", progressBar.getMax() + "");
					break;
				case 1:
					progressBar.setProgress(DownedFileLength);
					int x = DownedFileLength * 100 / FileLength;
					tv_download.setText("                下载中......" + x + "%");
					break;
				case 2:
					tv_download.setVisibility(View.GONE);
					progressBar.setVisibility(View.GONE);
					progressBar.setProgress(0);
					progressBar.setMax(100);
					DownedFileLength = 0;
					Map<String,Object> map = list_show.get(msg.arg1);
					Bundle bundle = new Bundle();
					bundle = msg.getData();
					map.put("size", bundle.get("size"));
					list_show.set(msg.arg1, map);
					adapter.notifyDataSetChanged();
				//	LayoutInflater inflate = 
				//	TextView tv = (TextView) findViewById(id)
				//	lv_base.getAdapter().getItem(msg.arg1);
					Toast.makeText(getApplicationContext(), "下载完成",
							Toast.LENGTH_LONG).show();
					break;
				case 1000:
					tv_download.setVisibility(View.GONE);
					progressBar.setVisibility(View.GONE);
					progressBar.setProgress(0);
					progressBar.setMax(100);
					Toast.makeText(getApplicationContext(), "网络错误",
							Toast.LENGTH_LONG).show();
					finish();
					break;
				default:
					break;
				}
			}
		}

	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_return2:
			finish();
		break;
		case R.id.ib_return:
			/*UpLoadFile_Activity.this.*/finish();
			break;
		case R.id.btn_add:
			// 弹出对话框选择获取图片方式
			Intent intent = new Intent(UpLoadFile_Activity.this,
					UploadFile_CheckFile_MainActivity_.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.btn_close:
			String str;
			if (type == 1000) {
				Constants.FileIDs = "";
				for (Map<String, Object> map : list_show) {
					File file = new File((String) map.get("path"));
					if (FileUtil.isImage(FileUtil.getFileType((String) map
							.get("path")))) {
						str = uploadImage((String) map.get("path"));
					} else {
						byte[] bytes = null;
						try {
							bytes = FileUtil.getBytesFromFile(file);
						} catch (IOException e) {
							e.printStackTrace();
						}
						str = Base64.encodeToString(bytes, Base64.DEFAULT);
					}
//					testForWriteFile(str,
//							"/mnt/sdcard/testForDownload/" + file.getName());
					Map<String, Object> mapdatas = new HashMap<String, Object>();
					mapdatas.put("Module", Constants.Module);
					mapdatas.put("FileName", map.get("name"));
					mapdatas.put("FileExt", map.get("ext"));
					mapdatas.put("StreamByte", str);
					java.text.DecimalFormat df = new java.text.DecimalFormat(
							"#.##");
					mapdatas.put(
							"ContentLength",
							df.format(((Long) map.get("size"))
									/ (1024.0 * 1024.0))
									+ "MB");
					mapdatas.put("Creater", infoFile.infoUsername2().get());
					manager.uploadFile(UpLoadFile_Activity.this, 1, mapdatas);
				}
				uploadFinished = true;
			}else{
				UpLoadFile_Activity.this.finish();
			}
			break;
		default:
			break;
		}
	}

	private String uploadImage(String path) {
		String ss = FileUtil.getimage(path);
		return ss;
	}

	private FileDownloadAndUploadManager manager = new FileDownloadAndUploadManager() {

		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			switch (paramInt) {
			case 1://上传
				if (handleResult.getiSuccess().equals("success")) {
					Toast.makeText(UpLoadFile_Activity.this, "上传完成！",
							Toast.LENGTH_SHORT).show();
					MyCallBack callBack = Constants.callback;
					int position = 0;
							position = UpLoadFile_Activity.this.getIntent().getIntExtra("position", 0);
					if(callBack != null){
						callBack.callback("", "", Constants.FileIDs, position);
					}
					UpLoadFile_Activity.this.setResult(Activity.RESULT_OK,intent);
					UpLoadFile_Activity.this.finish();
				}
				break;
			case 2:
				if (handleResult.getiSuccess().equals("success")) {
					Map<String, Object> map = new HashMap<String, Object>();
					String ext = "";
					if(handleResult.getBean_file().getFileType().equals("jpg") 
							|| handleResult.getBean_file().getFileType().equals("JPG")){
						ext = "png";
					}else{
						ext = handleResult.getBean_file().getFileType();
					}
					map.put("url", handleResult.getBean_file().getFileBuff());
					map.put("path", savePAth
							+ handleResult.getBean_file().getFileName()/*+"."+ext*/);
					File file = new File(savePAth
							+ handleResult.getBean_file().getFileName()/*+"."+ext*/);
					if(file.exists()){
						FileInputStream in = null;
						long size =0;
						try {
							in = new FileInputStream(file);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							size = in.available();
							map.put("size", size);
							in.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						map.put("size", (long) 0);
					}
					
					map.put("ext", ext);
					map.put("name", handleResult.getBean_file().getFileName()/*+"."+ext*/);
					list_show.add(map);
					adapter.notifyDataSetChanged();
				}
				break;
			default:
				break;
			}
		}
	};

	public void DownFile(String urlString, Handler handler, String fileName,int position) {
		Message message = new Message();
		/*
		 * 连接到服务器
		 */
		try {
			URL url = new URL("http://117.40.244.140/"+urlString);
			connection = url.openConnection();
			connection.setConnectTimeout(5000);//请求超时时间 5s
			
			if (connection.getReadTimeout() == 5) {
				Log.i("---------->", "当前网络有问题");
				message.what = 1000;
				handler.sendMessage(message);
				return;
			}
			inputStream = connection.getInputStream();
		} catch (MalformedURLException e1) {
			message.what = 1000;
			handler.sendMessage(message);
			e1.printStackTrace();
		} catch (IOException e) {
			message.what = 1000;
			handler.sendMessage(message);
			e.printStackTrace();
		}
		/*
		 * 文件的保存路径和和文件名，如果不存在则新建
		 */

		String savePathString = savePAth + fileName;
		File file1 = new File(savePAth);
		if(!file1.exists()){
			file1.mkdir();
		}
		File file = new File(savePathString.trim());
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*
		 * 向SD卡中写入文件,用Handle传递线程
		 */
		
		try {
			outputStream = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			FileLength = connection.getContentLength();
			message.what = 0;
			handler.sendMessage(message);
			int len;
			// 开始读取
			while ((len = (inputStream.read(buffer))) > 0) {
				outputStream.write(buffer, 0, len);
				DownedFileLength += len;
				Log.i("-------->", DownedFileLength + "");
				Message message1 = new Message();
				message1.what = 1;
				handler.sendMessage(message1);
			}
			// 完毕，关闭所有链接
			outputStream.close();
			inputStream.close();
			/*
			 * 获取文件大小
			 * **/
			FileInputStream in = new FileInputStream(file);
			long size = in.available();
			in.close();
			/*
			 * 发送消息更新UI
			 * */
			Message message2 = new Message();
			message2.what = 2;
			message2.arg1 = position;
			Bundle bundle = new Bundle();
			bundle.putLong("size", size);
			message2.setData(bundle);
			handler.sendMessage(message2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	Bitmap bitmap = null;

	public void DownFilePicture(String urlString, Handler handler,
			String fileName) {
		/*
		 * 连接到服务器
		 */
		try {
			URL url = new URL(urlString);
			connection = url.openConnection();
			if (connection.getReadTimeout() == 5) {
				Log.i("---------->", "当前网络有问题");
				return;
			}
			inputStream = connection.getInputStream();
			if (inputStream == null) {
				throw new RuntimeException("stream is null");
			} else {
				try {
					byte[] data = readStream(inputStream);
					if (data != null) {
						bitmap = BitmapFactory.decodeByteArray(data, 0,
								data.length);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				inputStream.close();
			}
			saveFile(bitmap, fileName);
			handler.sendEmptyMessage(2);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 得到图片字节流 数组大小
	 */
	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}

	// 指定保存的路径：
	public void saveFile(Bitmap bm, String fileName) throws IOException {
		String ALBUM_PATH = savePAth;
		File myCaptureFile = new File(ALBUM_PATH + fileName);
		if (!myCaptureFile.exists()) {
			try {
				myCaptureFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(myCaptureFile));
		bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
		bos.flush();
		bos.close();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
			// 将图片地址保存，用于展示图片
			String path = data
					.getStringExtra(UploadFile_CheckFile_MainActivity_.KEY_FILE_PATH);
			if (path != null) {
				File file = new File(path);
				long s = 0;
				if (file.exists()) {
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						s = fis.available();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				String filename = file.getName();
				String prefix = filename
						.substring(filename.lastIndexOf(".") + 1);
				String name = filename.replace("." + prefix, "");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", name);
				map.put("path", path);
				map.put("ext", prefix);
				if (file.isFile()) {
					map.put("size", s);
				}
				list_show.add(map);
				adapter = new UploadFile_Adapter(UpLoadFile_Activity.this,
						list_show);
				lv_base.setAdapter(adapter);
				lv_base.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						File file = new File((String) list_show.get(position)
								.get("path"));
						if (file.exists()) {
							FileUtil.openFile(UpLoadFile_Activity.this, file);
						//	Toast.makeText(
						//			UpLoadFile_Activity.this,
						//			(String) list_show.get(position)
						//					.get("path"), Toast.LENGTH_SHORT)
						//			.show();
						}
					}
				});
			}
		}
	};

	public class UploadFile_Adapter extends BaseAdapter {

		List<Map<String, Object>> list;
		private LayoutInflater inflater;
		private Context context = null;
		InfoFile_ infofile;

		public UploadFile_Adapter(Context context,
				List<Map<String, Object>> list) {
			this.context = context;
			this.list = list;
			this.inflater = LayoutInflater.from(context);
			infofile = new InfoFile_(context);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			final int pst = position;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.upload_file_item, null);
				holder.name = (TextView) convertView.findViewById(R.id.tv_name);
				holder.size = (TextView) convertView.findViewById(R.id.tv_size);
				holder.btn_delete = (Button) convertView
						.findViewById(R.id.btn_delete);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
			holder.name
					.setText((String) (list.get(position).get("path") != null ? list
							.get(position).get("path") + ""
							: "0"));
			holder.size
					.setText((String) (list.get(position).get("size") != null ? df
							.format(((Long) list.get(position).get("size"))
									/ (1024.0 * 1024.0))
							+ "MB" : "0"));
			holder.btn_delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					list_show.remove(pst);
					UploadFile_Adapter.this.notifyDataSetChanged();
				}
			});
			return convertView;
		}

		class ViewHolder {
			TextView name;
			TextView size;
			Button btn_delete;
		}
	}
}
