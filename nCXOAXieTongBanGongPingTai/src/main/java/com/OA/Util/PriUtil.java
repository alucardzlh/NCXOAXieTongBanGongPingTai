package com.OA.Util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 通过图片路径获取图片
 * 
 * @author Administrator
 * 
 */
public class PriUtil {

	public static Bitmap getBitmap(String imgUrl) {
		Bitmap bitmap = null;
		URL imageUrl;
		try {
			imageUrl = new URL(imgUrl);
			HttpURLConnection conn = (HttpURLConnection) imageUrl
					.openConnection();
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			conn.setInstanceFollowRedirects(true);
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bitmap;
	}
}
