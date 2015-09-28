package com.OA.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.OA.Data.InfoFile;
import com.OA.Data.InfoFile_;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

/**
 * http请求工具类
 * 
 * @author yuhuihui
 * @data 2014-5-30
 */
public class HttpUtil {
	/**
	 * 获取xml 类型数据
	 * 
	 * @param activity上下文
	 * @param methodName
	 *            方法
	 * @param paramList
	 *            参数
	 * @return result.toString
	 * @throws SoapFault
	 */
	public static String getLoginOA(Context context, String methodName,
		String endPoint,String str1,String str2,String str3) throws SoapFault {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;

		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);

		// 设置需调用WebService接口需要传入的参数
		if (str1!=null&&str2!=null&&str3!=null) {
			if (methodName.equals("LoginOA")) {
				rpc.addProperty("UserCode",str1);
				rpc.addProperty("strPWD",str2);
				rpc.addProperty("strType",str3);
			}
		}
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		 envelope.dotNet = true;//这里如果设置为TRUE,那么在服务器端将获取不到参数值(如:将这些数据插入到数据库中的话)
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);

		HttpTransportSE transport = new HttpTransportSE(endPoint);
//		transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		} else {
			result= null;
		}
		return "error";
	}
	/**
	 *登录
	 */
	public static String GetSendApplyListManager(Context context,
			String methodName, String endPoint, String UserCode,
			String SignCode,int RoleID, int KindID) throws SoapFault {
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		SoapObject soapChilds =null;
		
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;

		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);

		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("GetSendApplyList")) {
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("KindID", KindID);
				rpc.addProperty("RoleID",RoleID);
			}

		}

		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
	
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);

		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			
			return str;
		}
		return "error";
	}


	/**
	 * 发文审核、收文审核
	 */
	public static String GetSendCheckListManager(Context context,
			String methodName, String endPoint, String UserCode, String SignCode, int PageSize, int PageNow,String Title, String strr1, String strr2, String SendStartTime, String SendEndTime) throws SoapFault {
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("GetSendCheckList")) {
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("Title", Title);
				rpc.addProperty("WenHao", strr1);
				rpc.addProperty("NiGaoCompany", strr2);
				rpc.addProperty("SendStartTime", SendStartTime);
				rpc.addProperty("SendEndTime", SendEndTime);
			}
			if (methodName.equals("GetRecCheckList")) {
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("Title", Title);
				rpc.addProperty("LaiWenCompany", strr1);
				rpc.addProperty("WenHao", strr2);
				rpc.addProperty("SendStartTime", SendStartTime);
				rpc.addProperty("SendEndTime", SendEndTime);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str = result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 公文退回
	 */
	public static String GetBackApply(Context context,
			String methodName, String endPoint, int WFStepID,String UserCode, String SignCode,String Memo) throws SoapFault {
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("BackApply")) {
				rpc.addProperty("WFStepID", WFStepID);
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("Memo", Memo);
	
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str = result.getProperty(0).toString();
			return str;
		}
		return "error";
	}

	/**
	 * 发文浏览、收文浏览、我的发文、我的收文
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param PageSize
	 * @param PageNow
	 * @param Status
	 * @param Title
	 * @param WenHao （发文）、LaiWenCompany（收文）
	 * @param NiGaoCompany（发文）、WenHao（收文）
	 * @param SendStartTime
	 * @param SendEndTime
	 * @return
	 * @throws SoapFault
	 */
	public static String GetSendBrowseListManager(Context context,
			String methodName, String endPoint, String UserCode, String SignCode, int PageSize, int PageNow, String Status,String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime) throws SoapFault {
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名-
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("GetSendBrowseList")) {
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("Status", Status);
				rpc.addProperty("Title", Title);
				rpc.addProperty("WenHao", WenHao);
				rpc.addProperty("NiGaoCompany", NiGaoCompany);
				rpc.addProperty("SendStartTime", SendStartTime);
				rpc.addProperty("SendEndTime", SendEndTime);
			}
			if (methodName.equals("GetMySendApplyList")) {
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("Status", Status);
				rpc.addProperty("Title", Title);
				rpc.addProperty("WenHao", WenHao);
				rpc.addProperty("NiGaoCompany", NiGaoCompany);
				rpc.addProperty("SendStartTime", SendStartTime);
				rpc.addProperty("SendEndTime", SendEndTime);
			}
			if (methodName.equals("GetRecBrowseList")) {
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("Status", Status);
				rpc.addProperty("Title", Title);
				rpc.addProperty("LaiWenCompany", WenHao);
				rpc.addProperty("WenHao", NiGaoCompany);
				rpc.addProperty("SendStartTime", SendStartTime);
				rpc.addProperty("SendEndTime", SendEndTime);
			}
			if (methodName.equals("GetRecBrowseList")) {
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("Status", Status);
				rpc.addProperty("Title", Title);
				rpc.addProperty("LaiWenCompany", WenHao);
				rpc.addProperty("WenHao", NiGaoCompany);
				rpc.addProperty("SendStartTime", SendStartTime);
				rpc.addProperty("SendEndTime", SendEndTime);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
//	/**
//	 * 我的发文
//	 */
//	public static String GetMySendApplyList(Context context,
//			String methodName, String endPoint, String UserCode, String SignCode, int PageSize, int PageNow, String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime) throws SoapFault {
//		SoapObject result = null;
//		// SoapPrimitive result = null;
//		// SoapObject result_1 = null;
//		String nameSpace = "http://tempuri.org/";
//		String soapAction = nameSpace + methodName;
//		
//		// 指定WebService的命名空间和调用的方法名
//		SoapObject rpc = new SoapObject(nameSpace, methodName);
//		
//		// 设置需调用WebService接口需要传入的参数
//		if (UserCode != null) {
//			if (methodName.equals("GetMySendApplyList")) {
//				rpc.addProperty("UserCode", UserCode);
//				rpc.addProperty("SignCode", SignCode);
//				rpc.addProperty("PageSize", PageSize);
//				rpc.addProperty("PageNow", PageNow);
//				rpc.addProperty("Title", Title);
//				rpc.addProperty("WenHao", WenHao);
//				rpc.addProperty("NiGaoCompany", NiGaoCompany);
//				rpc.addProperty("SendStartTime", SendStartTime);
//				rpc.addProperty("SendEndTime", SendEndTime);
//			}
//		}
//		
//		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
//				SoapEnvelope.VER11);
//		
//		envelope.bodyOut = rpc;
//		// 设置是否调用的是dotNet开发的WebService
//		envelope.dotNet = true;
//		// 等价于envelope.bodyOut = rpc;
//		// envelope.setOutputSoapObject(rpc);
//		
//		HttpTransportSE transport = new HttpTransportSE(endPoint);
//		// transport.debug=true;
//		try {
//			// 调用WebService
//			transport.call(soapAction, envelope);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (envelope.bodyIn != null) {
//			result = (SoapObject) envelope.bodyIn;
//			String str = result.toString();
//			return str;
//		}
//		return "error";
//	}
	/**
	 * 我的会议申请、会议审核
	 */
	public static String getMeetingList(Context context,String methodName, 
			String endPoint, int PageSize,  int PageNow , 
			String UserCode,String SignCode,
			String RoomName, String StarTime,String EndTime) throws SoapFault {
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("GetMeetingList")) {
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("RoomName", RoomName);
				rpc.addProperty("StarTime", StarTime);
				rpc.addProperty("EndTime", EndTime);
			}
			if (methodName.equals("GetMeetingCheckList")) {
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("RoomName", RoomName);
				rpc.addProperty("StarTime", StarTime);
				rpc.addProperty("EndTime", EndTime);
			}
			if (methodName.equals("GetMyMeetingNoticeList")) {
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("RoomName", RoomName);
				rpc.addProperty("StarTime", StarTime);
				rpc.addProperty("EndTime", EndTime);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}

	
	/**
	 * 通知公告起草
	 */
	public static String getNoticeListQC(Context context,
			String methodName, String endPoint, int PageSize,  int PageNow , String UserCode,
			 String SignCode, String Title) throws SoapFault {
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("GetNoticeList")) {
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("Title", Title);
			}
			if (methodName.equals("GetNoticeCheckList")) {
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("Title", Title);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}

	
	
	/**
	 * 通知公告详情
	 */
	public static String getNoticeDetail(Context context,
			String methodName, String endPoint,  String UserCode,
			 String SignCode, int NoticeID) throws SoapFault {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if(methodName.equals("ReadNotice")){
				rpc.addProperty("SignCode",SignCode);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("NoticeID",NoticeID);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			try {
				writeSDCardFile(foldername, str.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}
		return "error";
	}
	
	/**
	 * 会议信息详情
	 */
	public static String getMeetingDetail(Context context,
			String methodName, String endPoint,  String UserCode,
			 String SignCode, int RoomLogID) throws SoapFault {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if(methodName.equals("ReadMtRoom")){
				rpc.addProperty("SignCode",SignCode);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("RoomLogID",RoomLogID);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			try {
				writeSDCardFile(foldername, str.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}
		return "error";
	}
	
	
	/**
	 * 公告浏览
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param PageSize
	 * @param PageNow
	 * @param UserCode
	 * @param SignCode
	 * @param Title
	 * @return
	 * @throws SoapFault
	 */
	public static String getNoticeReadList(Context context,
			String methodName, String endPoint, int PageSize,  int PageNow , String UserCode,
			 String SignCode, String Title) throws SoapFault{
			
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("GetNoticeReadList")) {
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("Title", Title);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		
				return "error";
		
	}
	
	/**
	 * 会议室申请
	 */
	
	public static String getMeetingApplyList(Context context,
			String methodName, String endPoint, int PageSize,  int PageNow , String UserCode,
			 String SignCode, String RoomName, String TypeID) throws SoapFault{
			
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("GetMeetingApplyList")) {
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("TypeID", TypeID);
				rpc.addProperty("RoomName", RoomName);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		
				return "error";
		
	}
	/**
	 * 我的行政收文
	 */
	
	public static String getMyRecApplyList(Context context,
			String methodName, String endPoint, String UserCode,
			 String SignCode, int PageSize,  int PageNow ,String Status, String Title, String LaiWenCompany,
			 String WenHao,String SendStartTime,String SendEndTime) throws SoapFault{
			
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("GetMyRecApplyList")) {
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("Title", Title);
				rpc.addProperty("LaiWenCompany", LaiWenCompany);
				rpc.addProperty("WenHao", WenHao);
				rpc.addProperty("SendStartTime", SendStartTime);
				rpc.addProperty("SendEndTime", SendEndTime);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		
				return "error";
		
	}
	/**
	 * 收文浏览列表
	 */
	
	public static String getRecBrowseList(Context context,
			String methodName, String endPoint, String UserCode,
			 String SignCode, int PageSize,  int PageNow ,String Status, String Title, String LaiWenCompany,
			 String WenHao,String SendStartTime,String SendEndTime) throws SoapFault{
			
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("GetRecBrowseList")) {
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("Status", 200);
				rpc.addProperty("Title", Title);
				rpc.addProperty("LaiWenCompany", LaiWenCompany);
				rpc.addProperty("WenHao", WenHao);
				rpc.addProperty("SendStartTime", SendStartTime);
				rpc.addProperty("SendEndTime", SendEndTime);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		
				return "error";
		
	}
	/**
	 * 收文待阅
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param PageSize
	 * @param PageNow
	 * @param Status
	 * @param Title
	 * @param LaiWenCompany
	 * @param WenHao
	 * @param SendStartTime
	 * @param SendEndTime
	 * @return
	 * @throws SoapFault
	 */
	public static String getRecBrowseList1(Context context,
			String methodName, String endPoint, String UserCode,
			 String SignCode, int PageSize,  int PageNow ,String Status, String Title, String LaiWenCompany,
			 String WenHao,String SendStartTime,String SendEndTime) throws SoapFault{
			
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("GetRecBrowseList")) {
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("Status", 100);
				rpc.addProperty("Title", Title);
				rpc.addProperty("LaiWenCompany", LaiWenCompany);
				rpc.addProperty("WenHao", WenHao);
				rpc.addProperty("SendStartTime", SendStartTime);
				rpc.addProperty("SendEndTime", SendEndTime);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		
				return "error";
		
	}
	/**
	 * 收文处理
	 */
	public static String getRecCheckList(Context context,
			String methodName, String endPoint, String UserCode,
			 String SignCode, int PageSize,  int PageNow , String Title, String LaiWenCompany,
			 String WenHao,String SendStartTime,String SendEndTime) throws SoapFault{
			
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("GetRecCheckList")) {
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				//rpc.addProperty("Status", 100);
				rpc.addProperty("Title", Title);
				rpc.addProperty("LaiWenCompany", LaiWenCompany);
				rpc.addProperty("WenHao", WenHao);
				rpc.addProperty("SendStartTime", SendStartTime);
				rpc.addProperty("SendEndTime", SendEndTime);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		
				return "error";
		
	}
	
	static String foldername = Environment.getExternalStorageDirectory()
			.getPath() + "/log_ssss.txt";
	
	public static void writeSDCardFile(String path, byte[] buffer)
			throws IOException {

		File file = new File(path);

		FileOutputStream fos = new FileOutputStream(file);

		fos.write(buffer);// 写入buffer数组。如果想写入一些简单的字符，可以将String.getBytes()再写入文件;

		fos.close();

	}
	/**
	 * 通知公告审核,删除
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String VerifyNotice(Context context, String methodName,
			String endPoint, String UserCode, String SignCode, int NoticeID,
			String Flag, int flowid, int WFStepID) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (NoticeID != 0) {
			if(methodName.equals("VerifyNotice")){
				rpc.addProperty("SignCode",SignCode);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("NoticeID",NoticeID);
				rpc.addProperty("Flag",Flag);
				rpc.addProperty("FlowID",flowid);
				rpc.addProperty("WFStepID",WFStepID);
			}
			if(methodName.equals("DelNotice")){
				rpc.addProperty("NoticeID",NoticeID);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 通知公告添加,修改
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String VerifyNotice(Context context, String methodName,
			String endPoint, Map<String, Object> mapDatas) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (mapDatas != null) {
			if(methodName.equals("SaveNotice")){
				rpc.addProperty("SignCode",mapDatas.get("SignCode"));
				rpc.addProperty("UserCode",mapDatas.get("UserCode"));
				rpc.addProperty("Title",mapDatas.get("Title"));
				rpc.addProperty("Subject",mapDatas.get("Subject"));
				rpc.addProperty("Content",mapDatas.get("Content"));
				rpc.addProperty("BrowseName",mapDatas.get("BrowseName"));
				rpc.addProperty("BrowseCode",mapDatas.get("BrowseCode"));
				rpc.addProperty("EndDate",mapDatas.get("EndDate"));
				rpc.addProperty("FileIDs",mapDatas.get("FileIDs"));
		//		rpc.addProperty("TypeID",74);
		//		rpc.addProperty("FlowID",49);
				rpc.addProperty("TypeID",mapDatas.get("TypeID"));
				rpc.addProperty("FlowID",mapDatas.get("FlowID"));
				rpc.addProperty("NoticeID",mapDatas.get("NoticeID"));
				try {
					writeSDCardFile(foldername,mapDatas.toString().getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	/**
	 * 会议室审核
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String VerifyMtRoom(Context context, String methodName,
			String endPoint, String UserCode, String SignCode, int RoomLogID,
			String Flag, int StepDataID) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if(methodName.equals("VerifyMtRoom")){
				rpc.addProperty("SignCode",SignCode);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("RoomLogID",RoomLogID);
				rpc.addProperty("Flag",Flag);
				rpc.addProperty("StepDataID",StepDataID);
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
//	/**
//	 * 会议室申请预约接口
//	 */
//	public static String ApplyMtRoom(Context context, String methodName,String endPoint,
//			String SignCode, String UserCode, String Host, String Applicant,
//			String InvitingCode, String InvitingName, String MeetingContent,
//			String MeetingStart, String MeetingEnd, String MeetingTitle,
//			String Memo, String Position, String RecorderName, String RoomName,
//			String SendType, String FileIDs, int RoomID, int InvitingCount,
//			int MatCard, int ContainPeople, int RoomLogID) {
//		SoapObject result = null;
//		String nameSpace = "http://tempuri.org/";
//		String soapAction = nameSpace + methodName;
//		
//		// 指定WebService的命名空间和调用的方法名
//		SoapObject rpc = new SoapObject(nameSpace, methodName);
//		
//		// 设置需调用WebService接口需要传入的参数
//		if (UserCode != null) {
//			if(methodName.equals("ApplyMtRoom")){
//				rpc.addProperty("SignCode",SignCode);
//				rpc.addProperty("UserCode",UserCode);
//				rpc.addProperty("Host",Host);
//				rpc.addProperty("Applicant",Applicant);
//				rpc.addProperty("InvitingCode",InvitingCode);
//				rpc.addProperty("InvitingName",InvitingName);
//				rpc.addProperty("MeetingContent",MeetingContent);
//				rpc.addProperty("MeetingStart",MeetingStart);
//				rpc.addProperty("MeetingEnd",MeetingEnd);
//				rpc.addProperty("MeetingTitle",MeetingTitle);
//				rpc.addProperty("Memo",Memo);
//				rpc.addProperty("Position",Position);
//				rpc.addProperty("RecorderName",RecorderName);
//				rpc.addProperty("RoomName",RoomName);
//				rpc.addProperty("SendType",SendType);
//				rpc.addProperty("FileIDs",FileIDs);
//				rpc.addProperty("RoomID",RoomID);
//				rpc.addProperty("InvitingCount",InvitingCount);
//				rpc.addProperty("MatCard",MatCard);
//				rpc.addProperty("ContainPeople",ContainPeople);
//				rpc.addProperty("RoomLogID",RoomLogID);
//			}
//		}
//		
//		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
//				SoapEnvelope.VER11);
//		
//		envelope.bodyOut = rpc;
//		// 设置是否调用的是dotNet开发的WebService
//		envelope.dotNet = true;
//		// 等价于envelope.bodyOut = rpc;
//		// envelope.setOutputSoapObject(rpc);
//		
//		HttpTransportSE transport = new HttpTransportSE(endPoint);
//		// transport.debug=true;
//		try {
//			// 调用WebService
//			transport.call(soapAction, envelope);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (envelope.bodyIn != null) {
//			result = (SoapObject) envelope.bodyIn;
//			String str =result.getProperty(0).toString();
//			return str;
//		}
//		return "error";
//	}
	/**
	 * 获取发文表单
	 */
	public static String getFlowFormElements(Context context,
			String methodName, String endPoint, String UserCode,
			String SignCode, int FlowID,int WFID,int CurNodeID) throws SoapFault {
		SoapObject result = null;
		// SoapPrimitive result = null;
		// SoapObject result_1 = null;
		SoapObject soapChilds =null;
		
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;

		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);

		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if (methodName.equals("GetFlowFormElements")) {
				rpc.addProperty("UserCode", UserCode);
				rpc.addProperty("SignCode", SignCode);
				rpc.addProperty("FlowID", FlowID);
				rpc.addProperty("WFID", WFID);
				rpc.addProperty("CurNodeID", CurNodeID);
			}
		}

		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
	
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);

		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			
			return str;
		}
		return "error";
	}
	
	/**
	 * 会议室申请
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String ApplyMtRoom(Context context, String methodName,
			String endPoint,Map<String,Object> map) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (map != null) {
			if(methodName.equals("ApplyMtRoom")){
				rpc.addProperty("SignCode",map.get("SignCode"));
				rpc.addProperty("UserCode",map.get("UserCode"));
				rpc.addProperty("Host",map.get("Host"));
				rpc.addProperty("Applicant",map.get("Applicant"));
				rpc.addProperty("InvitingCode",map.get("InvitingCode"));
				rpc.addProperty("InvitingName",map.get("InvitingName"));
				rpc.addProperty("MeetingContent",map.get("MeetingContent"));
				rpc.addProperty("MeetingStart",map.get("MeetingStart"));
				rpc.addProperty("MeetingEnd",map.get("MeetingEnd"));
				rpc.addProperty("MeetingTitle",map.get("MeetingTitle"));
				rpc.addProperty("Memo",map.get("Memo"));
				rpc.addProperty("Position",map.get("Position"));
				rpc.addProperty("RecorderName",map.get("RecorderName"));
				rpc.addProperty("RoomName",map.get("RoomName"));
				rpc.addProperty("SendType",map.get("SendType"));
				rpc.addProperty("FileIDs",map.get("FileIDs"));
				rpc.addProperty("RoomID",map.get("RoomID"));
				rpc.addProperty("InvitingCount",map.get("InvitingCount"));
				rpc.addProperty("MatCard",map.get("MatCard"));
				rpc.addProperty("ContainPeople",map.get("ContainPeople"));
				rpc.addProperty("RoomLogID","0");
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 会议室申请
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String SupplyFile(Context context, String methodName,
			String endPoint,Map<String,Object> map) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (map != null) {
			if(methodName.equals("SupplyFile")){
				rpc.addProperty("fileIDs",map.get("fileIDs"));
				rpc.addProperty("TableID",map.get("RoomLogID"));
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 会议取消、参加人员不参加会议操作
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param RoomLogID  、JoinID
	 * @param reasonMsg  、JoinWorkerCode
	 * @param isPhoneMsgSend  、IsSendMsg
	 * @param UserCode
	 * @param SignCode
	 * @return
	 */
	public static String Cancelmting(Context context, String methodName,
			 String endPoint,int RoomLogID,String reasonMsg,Boolean isPhoneMsgSend,String UserCode,String  SignCode) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if(methodName.equals("CancelMting")){
				rpc.addProperty("RoomLogID",RoomLogID);
				rpc.addProperty("reasonMsg",reasonMsg);
				rpc.addProperty("isPhoneMsgSend",isPhoneMsgSend);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("SignCode",SignCode);
			
			}
			if(methodName.equals("DropOutMting")){
				rpc.addProperty("JoinID",RoomLogID);
				rpc.addProperty("JoinWorkerCode",reasonMsg);
				rpc.addProperty("IsSendMsg",isPhoneMsgSend);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("SignCode",SignCode);
				
			}
			
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 补报参会人员列表
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param RoomLogID
	 * @param DeptID
	 * @param UserCode
	 * @param SignCode
	 * @return
	 */
	public static String AddMtingWorkerList(Context context, String methodName,
			String endPoint,int RoomLogID,int DeptID,String UserCode,String  SignCode) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if(methodName.equals("AddMtingWorkerList")){
				rpc.addProperty("RoomLogID",RoomLogID);
				rpc.addProperty("DeptID",DeptID);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("SignCode",SignCode);
				
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 补报参会人员
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param RoomLogID
	 * @param DeptID
	 * @param UserPostName //职务名
	 * @param JoinWorkerName 外部参会人员名
	 * @param JoinWorkerNames 内部参会人员名
	 * @param JoinWorkerCodes
	 * @param UserMobile
	 * @param UserCode
	 * @param isSendMsg
	 * @param SignCode
	 * @return
	 */
	public static String BuBaoJoinWorker(Context context, String methodName,
			String endPoint,int RoomLogID,int DeptID,String UserPostName,String JoinWorkerName,String JoinWorkerNames,String JoinWorkerCodes,String UserMobile,String UserCode,Boolean isSendMsg,String  SignCode) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if(methodName.equals("BuBaoJoinWorker")){
				rpc.addProperty("RoomLogID",RoomLogID);
				rpc.addProperty("DeptID",DeptID);
				rpc.addProperty("UserPostName",UserPostName);
				rpc.addProperty("JoinWorkerName",JoinWorkerName);
				rpc.addProperty("JoinWorkerNames",JoinWorkerNames);
				rpc.addProperty("JoinWorkerCodes",JoinWorkerCodes);
				rpc.addProperty("UserMobile",UserMobile);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("isSendMsg",isSendMsg);
				rpc.addProperty("SignCode",SignCode);
				
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	/**
	 * 修改会议时间
	 */
	public static String ChangeMtingTime(Context context, String methodName,
			String endPoint,int RoomLogID,String startTime,String endTime,String UserCode,String  SignCode) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if(methodName.equals("ChangeMtingTime")){
				rpc.addProperty("RoomLogID",RoomLogID);
				rpc.addProperty("startTime",startTime);
				rpc.addProperty("endTime",endTime);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("SignCode",SignCode);
				
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 *会议通知签收
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param MeetingID
	 * @param UserCode
	 * @param SignCode
	 * @return
	 */
	public static String Signoff(Context context, String methodName,
			String endPoint,int MeetingID,String UserCode,String  SignCode) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if(methodName.equals("Signoff")){
				rpc.addProperty("MeetingID",MeetingID);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("SignCode",SignCode);
				
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 浏览人员列表
	 */
	public static String browsePeopleList(Context context, String methodName,String endPoint, int PageSize,
			int PageNow, int WFID, String Status){
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		//指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		//设置需要调用WebService接口需要传入的参数
	//	if(Status != null){
			if(methodName.equals("GetBrowseUserList")){
				rpc.addProperty("PageSize", PageSize);
				rpc.addProperty("PageNow", PageNow);
				rpc.addProperty("WFID", WFID);
				rpc.addProperty("Status", Status);
	//		}
		}
		
		//生成调用WebService方法的SOAP请求信息，并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		//设置是否调用的是.net开发的WebService
		envelope.dotNet = true;
		HttpTransportSE transportSE = new HttpTransportSE(endPoint);
		
		try {
			transportSE.call(soapAction, envelope);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(envelope.bodyIn != null){
			result = (SoapObject) envelope.bodyIn;
			String str = result.getProperty(0).toString();
			return str;
		}
				return "error";
		
	}
	/**
	 * 可浏览人员
	 */
	public static String browsePeople(Context context, String methodName,String endPoint,int WFID){
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		//指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		//设置需要调用WebService接口需要传入的参数
		if(methodName.equals("GetBrowseUser")){
			rpc.addProperty("WFID", WFID);
		}
		//生成调用WebService方法的SOAP请求信息，并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		//设置是否调用的是.net开发的WebService
		envelope.dotNet = true;
		HttpTransportSE transportSE = new HttpTransportSE(endPoint);
		
		try {
			transportSE.call(soapAction, envelope);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(envelope.bodyIn != null){
			result = (SoapObject) envelope.bodyIn;
			String str = result.getProperty(0).toString();
			return str;
		}
				return "error";
		
	}
	/**
	 * 未浏览人员
	 */
	public static String notbrowsePeople(Context context, String methodName,String endPoint,int WFID){
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		//指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		//设置需要调用WebService接口需要传入的参数
		if(methodName.equals("GetNotBrowseUser")){
			rpc.addProperty("WFID", WFID);
		}
		//生成调用WebService方法的SOAP请求信息，并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		//设置是否调用的是.net开发的WebService
		envelope.dotNet = true;
		HttpTransportSE transportSE = new HttpTransportSE(endPoint);
		
		try {
			transportSE.call(soapAction, envelope);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(envelope.bodyIn != null){
			result = (SoapObject) envelope.bodyIn;
			String str = result.getProperty(0).toString();
			return str;
		}
				return "error";
		
	}
	/**
	 * 超时浏览人员
	 */
	public static String overbrowsePeople(Context context, String methodName,String endPoint,int WFID){
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		//指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		//设置需要调用WebService接口需要传入的参数
		if(methodName.equals("GetOverBrowseUser")){
			rpc.addProperty("WFID", WFID);
		}
		//生成调用WebService方法的SOAP请求信息，并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		//设置是否调用的是.net开发的WebService
		envelope.dotNet = true;
		HttpTransportSE transportSE = new HttpTransportSE(endPoint);
		
		try {
			transportSE.call(soapAction, envelope);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(envelope.bodyIn != null){
			result = (SoapObject) envelope.bodyIn;
			String str = result.getProperty(0).toString();
			return str;
		}
				return "error";
		
	}
	
	/**
	 * 人员选择
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String checkPeople(Context context, String methodName,
			String endPoint, String UserCode ,int DeptID) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);	
		
		// 设置需调用WebService接口需要传入的参数
		if (UserCode != null) {
			if(methodName.equals("LoadGroup")){
				rpc.addProperty("UserCode",UserCode);
			}
			if(methodName.equals("LoadDept")){
				rpc.addProperty("DeptID",DeptID);
			}
			if(methodName.equals("GetFileSavePath")){
				rpc.addProperty("Module","ArchivesNoteice");
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 人员选择搜索接口
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String SelectDeptPerson(Context context, String methodName,
			String endPoint,  Map<String,Object> mapDatas) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (mapDatas != null) {
			if(methodName.equals("SelectDeptPerson")){
				rpc.addProperty("strUserCode",mapDatas.get("strUserCode"));
				rpc.addProperty("strUserName",mapDatas.get("strUserName"));
				rpc.addProperty("strDeptID",mapDatas.get("strDeptID"));
				rpc.addProperty("strRoleID",mapDatas.get("strRoleID"));
				rpc.addProperty("strPostID",mapDatas.get("strPostID"));
				rpc.addProperty("strGroupID",mapDatas.get("strGroupID"));
				rpc.addProperty("strMyDeptID",mapDatas.get("strMyDeptID"));
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	
	/**
	 * 附件下载
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String DownloadFile(Context context, String methodName,
			String endPoint, int FileID) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
	//	if (FileID == 0) {
			if(methodName.equals("DownFile")){
				rpc.addProperty("FileID",FileID);
			}
	//	}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 附件上传
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String DownloadFile(Context context, String methodName,
			String endPoint, Map<String,Object> map) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if (map != null) {
			if(methodName.equals("SaveFile")){
				rpc.addProperty("Module",map.get("Module"));
				rpc.addProperty("FileName",map.get("FileName"));
				rpc.addProperty("FileExt",map.get("FileExt"));
				rpc.addProperty("Stream",map.get("StreamByte"));
				rpc.addProperty("ContentLength",map.get("ContentLength"));
				rpc.addProperty("Creater",map.get("Creater"));
			}
		}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			Log.e("11111111", str);
			return str;
		}
		return "error";
	}
	/**
	 * 撰稿第一步
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String GetApplyNextStepInfo(Context context, String methodName,
			String endPoint, final int FlowID, final int FlowStepID,final String UserCode) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		InfoFile_ infoFile = new InfoFile_(context);
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
			if(methodName.equals("GetApplyNextStepInfo")){
				rpc.addProperty("FlowID",FlowID);
				rpc.addProperty("FlowStepID",FlowStepID);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("DeptID",infoFile.DeptID().get());
			}else if(methodName.equals("GetNextFlowSteps")){
				rpc.addProperty("FlowStepID",FlowStepID);
			}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 审核第一步
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String GetCheckNextStepInfo(Context context, String methodName,
			String endPoint,final int FlowID, final int WFID,final int FlowStepID,final String CurNode,final String ISPass,final int ISBrachSel,final String UserCode) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		InfoFile_ infoFile = new InfoFile_(context);
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
			if(methodName.equals("GetCheckNextStepInfo")){
				rpc.addProperty("FlowID",FlowID);
				rpc.addProperty("WFID",WFID);
				rpc.addProperty("FlowStepID",FlowStepID);
				rpc.addProperty("CurNode",CurNode);
				rpc.addProperty("ISPass",ISPass);
				rpc.addProperty("ISBrachSel",ISBrachSel);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("DeptID",infoFile.DeptID().get());
			}
		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	
	/**
	 * 第二步
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String SaveSendCheckStepData(Context context, String methodName,
			String endPoint, final Map<String,Object> mapDatas) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
		if(mapDatas != null){
			if(methodName.equals("SaveSendCheckStepData")){
				rpc.addProperty("FlowID",mapDatas.get("FlowID"));
				rpc.addProperty("WFID",mapDatas.get("WFID"));
				rpc.addProperty("StepID",mapDatas.get("StepID"));
				rpc.addProperty("WFStepID",mapDatas.get("WFStepID"));
				rpc.addProperty("ISPass",mapDatas.get("ISPass"));
				rpc.addProperty("PreStepNode",mapDatas.get("PreStepNode"));
		//		rpc.addProperty("Title",mapDatas.get("Title"));
				rpc.addProperty("CheckUserCode",mapDatas.get("CheckUserCode"));
				rpc.addProperty("BrowseCode",mapDatas.get("BrowseCode"));
				rpc.addProperty("BrowseName",mapDatas.get("BrowseName"));
				rpc.addProperty("ApplyUserCode",mapDatas.get("ApplyUserCode"));
				rpc.addProperty("ApplyUserName",mapDatas.get("ApplyUserName"));
		//		rpc.addProperty("EndTime",mapDatas.get("EndTime"));
				rpc.addProperty("ISSendMessage",mapDatas.get("ISSendMessage"));
		//		rpc.addProperty("ISFiliingDocument",mapDatas.get("ISFiliingDocument"));
				rpc.addProperty("ArcStepID",mapDatas.get("ArcStepID"));
				rpc.addProperty("Memo",mapDatas.get("Memo"));
				rpc.addProperty("KindID",mapDatas.get("KindID"));
				rpc.addProperty("Json",mapDatas.get("Json"));
			}	
			if(methodName.equals("SaveSendApplyStepData")){
				rpc.addProperty("FlowID",mapDatas.get("FlowID"));
				rpc.addProperty("StepID",mapDatas.get("StepID"));
				rpc.addProperty("Title",mapDatas.get("Title"));
				rpc.addProperty("CheckUserCode",mapDatas.get("CheckUserCode"));
				rpc.addProperty("BrowseCode",mapDatas.get("BrowseCode"));
				rpc.addProperty("BrowseName",mapDatas.get("BrowseName"));
				rpc.addProperty("ApplyUserCode",mapDatas.get("ApplyUserCode"));
				rpc.addProperty("ApplyUserName",mapDatas.get("ApplyUserName"));
				rpc.addProperty("EndTime",mapDatas.get("EndTime"));
				rpc.addProperty("ISSendMessage",mapDatas.get("ISSendMessage"));
				rpc.addProperty("ISFiliingDocument",mapDatas.get("ISFiliingDocument"));
				rpc.addProperty("ArcStepID",mapDatas.get("ArcStepID"));
				rpc.addProperty("KindID",mapDatas.get("KindID"));
				rpc.addProperty("Json",mapDatas.get("Json"));
			}
		}

		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 获取正文接口
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String LoadArchiveData(Context context, String methodName,
			String endPoint,final int Tildes,final int ArcStepID,final int WFID,final int WFStepID) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
			if(methodName.equals("LoadArchiveData")){
				rpc.addProperty("Tildes",Tildes);
				rpc.addProperty("ArcStepID",ArcStepID);
				rpc.addProperty("WFID",WFID);
				rpc.addProperty("WFStepID",WFStepID);
			}	

		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 保存正文接口
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String SaveArchiveData(Context context, String methodName,
			String endPoint,final int ArcStepID,final int WFID,final int WFStepID,final String UserCode,final String SignCode,final String mFileBody) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
			if(methodName.equals("SaveArchiveData")){
				rpc.addProperty("ArcStepID",ArcStepID);
				rpc.addProperty("WFID",WFID);
				rpc.addProperty("WFStepID",WFStepID);
				rpc.addProperty("UserCode",UserCode);
				rpc.addProperty("SignCode",SignCode);
				rpc.addProperty("mFileBody",mFileBody);
			}	

		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 判断流程是否结束
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String ISEnd(Context context,String methodName,String endPoint,final String CurStepNode,final String ISPass,final int FlowID) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
			if(methodName.equals("ISEnd")){
				rpc.addProperty("CurStepNode",CurStepNode);
				rpc.addProperty("ISPass",ISPass);
				rpc.addProperty("FlowID",FlowID);
			}	

		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
	
	/**
	 * 判断是否需要选人
	 * @param context
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param NoticeID
	 * @param Flag
	 * @param flowid
	 * @param WFStepID
	 * @return
	 */
	public static String IsSelBrowseUsers(Context context,String methodName,String endPoint,final int FlowID,final int CurNodeID) {
		SoapObject result = null;
		String nameSpace = "http://tempuri.org/";
		String soapAction = nameSpace + methodName;
		
		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		
		// 设置需调用WebService接口需要传入的参数
			if(methodName.equals("IsSelBrowseUsers")){
				rpc.addProperty("FlowID",FlowID);
				rpc.addProperty("CurNodeID",CurNodeID);
			}	

		
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(rpc);
		
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		// transport.debug=true;
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (envelope.bodyIn != null) {
			result = (SoapObject) envelope.bodyIn;
			String str =result.getProperty(0).toString();
			return str;
		}
		return "error";
	}
}
