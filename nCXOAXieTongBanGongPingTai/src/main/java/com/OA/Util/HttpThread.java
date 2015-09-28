package com.OA.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

class HttpThread implements Runnable {

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    private String resultCode;

	@Override
    public void run() {
        // TODO Auto-generated method stub
        //组建xml数据
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        xml.append("<soap:Body>");
        xml.append("<LoginOA xmlns=\"http://tempuri.org/\">");
        xml.append("<UserCode>admin</UserCode>");
        xml.append("<strPWD>123</strPWD>");
        xml.append("<strType>200</strType>");
        xml.append("</LoginOA>");
        xml.append("</soap:Body>");
        xml.append("</soap:Envelope>");
   

        try {
            byte[] xmlbyte = xml.toString().getBytes("UTF-8");
            
            System.out.println(xml);

            URL url = new URL("http://192.168.1.254:8099/WebServices/AppService.asmx?op=LoginOA");
            
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);// 允许输出
            conn.setDoInput(true);
            conn.setUseCaches(false);// 不使用缓存
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Length",
                    String.valueOf(xmlbyte.length));
            conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
//            conn.setRequestProperty("X-ClientType", "2");//发送自定义的头信息

            conn.getOutputStream().write(xmlbyte);
            conn.getOutputStream().flush();
            conn.getOutputStream().close();


            if (conn.getResponseCode() != 200)
                throw new RuntimeException("请求url失败");

            InputStream is = conn.getInputStream();// 获取返回数据
              
            // 使用输出流来输出字符(可选)
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            String string = out.toString("UTF-8");
            System.out.println(string);
            out.close();
                 
                     
            // xml解析
            String version = null;
            String LoginOAResult = null;
            XmlPullParser parser = Xml.newPullParser();
            try {
                parser.setInput(new ByteArrayInputStream(string.substring(1)
                        .getBytes("UTF-8")), "UTF-8");
                 parser.setInput(is, "UTF-8");
                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if ("LoginOAResult".equals(parser.getName())) {
                        	LoginOAResult = parser.nextText();
                        } 
                    }
                    eventType = parser.next();
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                System.out.println(e);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
           
            System.out.println("LoginOAResult = " + LoginOAResult);
          

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e);
        }
    }
}