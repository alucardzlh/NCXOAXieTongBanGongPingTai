package com.OA.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;









import android.os.Environment;

import com.OA.Entity.DeptIDAndValue_Bean;
import com.OA.Entity.Detail_Bdxx_Bean;
import com.OA.Entity.File_ID_Name_Bean;
import com.OA.Entity.IDAndValue_Bean;
import com.OA.Entity.LoginOA;
import com.OA.Entity.Meeting_Bean;
import com.OA.Entity.Meeting_hyssh_Bean;
import com.OA.Entity.Meeting_hyssq_Bean;
import com.OA.Entity.MyMeetingDetail_Bean;
import com.OA.Entity.MyMeetingDetail_bbchry_Bean;
import com.OA.Entity.MyNoticeDetail_Bean;
import com.OA.Entity.MyXzfwyy_Bean;
import com.OA.Entity.MyXzfwzg_Bean;
import com.OA.Entity.MyXzswdj_Bean;
import com.OA.Entity.Notice_Bean;
import com.OA.Entity.Notice_sh_Bean;
import com.OA.Entity.RecApply_xzsw_bean;
import com.OA.Entity.RecApply_xzswcl_Bean;
import com.OA.Entity.RecApply_xzswdy_Bean;
import com.OA.Entity.RecApply_xzswyy_Bean;
import com.OA.Entity.Wdxzfw_Bean;
import com.OA.Entity.Xzfwsh_Bean;
import com.OA.Entity.Xzsw_Detail_Bdxxfour_Bean;
import com.OA.Entity.Xzsw_Detail_Bdxxone_Bean;
import com.OA.Entity.Xzsw_Detail_Bdxxthree_Bean;
import com.OA.Entity.Xzsw_Detail_Bdxxtwo_Bean;
import com.OA.Entity.Xzsw_detai_Bdxxtwo_Bean;
import com.OA.Entity.Xzswcl_Detail_Bdxx_Bean;
import com.OA.Entity.Xzswdy_Detail_Bdxx_Bean;
import com.OA.Entity.Xzswyy_Detail_Bdxx_Bean;
import com.OA.Interface.MyCallBack;


public abstract class Constants {
	
	public final static String COPYRIGHT_VALUE_FORTRY ="SxD/phFsuhBWZSmMVtSjKZmm/c/3zSMrkV2Bbj5tznSkEVZmTwJv0wwMmH/+p6wLiUHbjadYueX9v51H9GgnjUhmNW1xPkB++KQqSv/VKLDsR8V6RvNmv0xyTLOrQoGzAT81iKFYb1SZ/Zera1cjGwQSq79AcI/N/6DgBIfpnlwiEiP2am/4w4+38lfUELaNFry8HbpbpTqV4sqXN1WpeJ7CHHwcDBnMVj8djMthFaapMFm/i6swvGEQ2JoygFU3spKowEa/G/fvXKfXfS212NWB43A+NXRsevrx1DJOapLx6aD2yiupr6ji7hzsE6/QCZTwXZQwybuwQzu4N8GoHea+yCsj03ann8gIJ1+pcrGVWEbHWAH22+t7LdPt+jENITQ1ljdcKfJJ264wW1dt8lhvJY2l3E5bmkuxrOncCgeSgCNRP4FpYjl8hG/IVrYX9eNIGdyij/IxvdIf4qITLeW8fXpxdRHfEuWC1PB9ruQ="/*"SxD/phFsuhBWZSmMVtSjKZmm/c/3zSMrkV2Bbj5tznSkEVZmTwJv0wwMmH/+p6wLiUHbjadYueX9v51H9GgnjUhmNW1xPkB++KQqSv/VKLDsR8V6RvNmv0xyTLOrQoGzAT81iKFYb1SZ/Zera1cjGwQSq79AcI/N/6DgBIfpnlwiEiP2am/4w4+38lfUELaNFry8HbpbpTqV4sqXN1WpeJ7CHHwcDBnMVj8djMthFaapMFm/i6swvGEQ2JoygFU3spKowEa/G/fvXKfXfS212Ks1PshAdG1mqa5yFeCbY3Xx6aD2yiupr6ji7hzsE6/QCZTwXZQwybuwQzu4N8GoHT38oWu9CbGPucJL+EXU24eVWEbHWAH22+t7LdPt+jEN+NASVlNoUcGMJX5OdZj1aCLjC1Bk4Y12UXHY2EZ954mSgCNRP4FpYjl8hG/IVrYXtoBHouACoEWxXSN3dZ98PeW8fXpxdRHfEuWC1PB9ruQ="*/;

	public final static String ACTION_SAVE = "com.kinggrid.iappoffice.save";
	
	// 当前版本�?
	// public static String LOCAL_VERSION_CODE = "";
	 public static String title = "main";
	// public static boolean SHOWSEARCH_BUTTON = false;

	// 当前左侧导航栏菜单list
	// public static List<String> list_leftmenu = new ArrayList<String>();

	// 下载的文件保存路�?
	public static final String LOCAL_DOWNLOAD_DIR = "downLoad/WSZW/";

	// 分页大小
	public static final int PAGE_SIZE_LARGE = 10, PAGE_SIZE_MEDIUM = 8,
			PAGE_SIZE_SMALL = 6;

	// 服务器类型，0电信�?移动�?联�?
	public static final int SERVER_NULL = 0, SERVER_DIANXIN = 1,
			SERVER_YIDONG = 2, SERVER_LIANTONG = 3;
	public static int SERVER_TYPE = SERVER_NULL;

	public static List<Notice_Bean> list_notice_bean = new ArrayList<Notice_Bean>(); 

	public static List<Meeting_Bean> list_meeting_bean = new ArrayList<Meeting_Bean>(); 
	public static List<Wdxzfw_Bean> list_wdxzfw_bean = new ArrayList<Wdxzfw_Bean>(); 
	public static List<MyXzfwyy_Bean> list_xzfwyy_bean = new ArrayList<MyXzfwyy_Bean>(); 
	public static List<Xzfwsh_Bean> list_xzfwsh_bean = new ArrayList<Xzfwsh_Bean>(); 
	
	public static List<Meeting_hyssh_Bean> list_meeting_hyssh_bean = new ArrayList<Meeting_hyssh_Bean>();
	public static List<Meeting_hyssq_Bean> list_meeting_hyssq_bean = new ArrayList<Meeting_hyssq_Bean>();

	public static List<MyXzfwzg_Bean> lis_xzfw_xzwfzg_bean = new ArrayList<MyXzfwzg_Bean>();
	public static List<Detail_Bdxx_Bean> list_Detail_bdxx_bean = new ArrayList<Detail_Bdxx_Bean>();
	public static List<Xzswdy_Detail_Bdxx_Bean> list_Detail_dybdxx_bean = new ArrayList<Xzswdy_Detail_Bdxx_Bean>();
	public static List<Xzswyy_Detail_Bdxx_Bean> list_Detail_yybdxx_bean = new ArrayList<Xzswyy_Detail_Bdxx_Bean>();
	
	//public static List<Xzsw_Detail_Bdxx_Bean> listSW_Detail_bdxx_bean = new ArrayList<Xzsw_Detail_Bdxx_Bean>();
	public static List<Xzsw_Detail_Bdxxtwo_Bean> listSWTWO_Detail_bdxx_bean = new ArrayList<Xzsw_Detail_Bdxxtwo_Bean>();
	public static List<Xzsw_Detail_Bdxxone_Bean> listSWONE_Detail_bdxx_bean = new ArrayList<Xzsw_Detail_Bdxxone_Bean>();
	public static List<Xzsw_Detail_Bdxxthree_Bean> listSWTHREE_Detail_bdxx_bean = new ArrayList<Xzsw_Detail_Bdxxthree_Bean>();
	public static List<Xzsw_Detail_Bdxxfour_Bean> listSWFOUR_Detail_bdxx_bean = new ArrayList<Xzsw_Detail_Bdxxfour_Bean>();
	public static List<Xzsw_detai_Bdxxtwo_Bean> listSWtwo_Detail_bdxx_bean = new ArrayList<Xzsw_detai_Bdxxtwo_Bean>();
	public static List<MyXzswdj_Bean> lis_xzsw_xzswdj_bean = new ArrayList<MyXzswdj_Bean>();
	public static List<Xzswcl_Detail_Bdxx_Bean> listCL_Detail_bdxx_bean = new ArrayList<Xzswcl_Detail_Bdxx_Bean>();

	
	public static List<RecApply_xzsw_bean> list_recApply_xzsw_bean = new ArrayList<RecApply_xzsw_bean>();
	public static List<RecApply_xzswyy_Bean> list_recApply_xzswyy_bean = new ArrayList<RecApply_xzswyy_Bean>();
	public static List<RecApply_xzswdy_Bean> list_recApply_xzswdy_bean = new ArrayList<RecApply_xzswdy_Bean>();
	public static List<RecApply_xzswcl_Bean> list_recApply_xzswcl_bean = new ArrayList<RecApply_xzswcl_Bean>();
	

	public static List<Notice_sh_Bean> list_notice_sh_bean = new ArrayList<Notice_sh_Bean>(); 

	public static MyNoticeDetail_Bean mynoticedetail_bean;
	public static Xzsw_Detail_Bdxxone_Bean kllry ;
	public static Xzsw_Detail_Bdxxthree_Bean wllry;
	public static Xzsw_Detail_Bdxxfour_Bean dllry;
	
	public static MyMeetingDetail_Bean mymeetingdetail_bean;
	public static MyMeetingDetail_bbchry_Bean mymeetingdetail_bbchry_bean;
	
	public static int COUNT_OF_LIST_NOTICE_BEAN = 0;
	
	public static int COUNT_OF_LIST_MYMEETING_BEAN = 0;
	public static int COUNT_OF_LIST_MyRECAPPLY_XZSWYY_BEAN = 0;
	public static int COUNT_OF_LIST_MyRECAPPLY_XZSWDY_BEAN = 0;
	public static int COUNT_OF_LIST_XZSW_DETAIL_BDXX_BEAN = 0;
	public static int COUNT_OF_LIST_MyRECAPPLY_XZSWCL_BEAN = 0;
	public static int COUNT_OF_LIST_MEETING_HYSSH_BEAN = 0;

	public static int COUNT_OF_LIST_MYXZFW_BEAN = 0;
//	public static int COUNT_OF_LIST_XZFWLL_BEAN = 0;
//	public static int COUNT_OF_LIST_XZFWSH_BEAN = 0;

	public static int COUNT_OF_LIST_MEETING_BEAN = 0;
	public static int COUNT_OF_LIST_RECAPPLY_XZSW_BEAN = 0;

//	List<Map<String,Object>> map_tzgg_fenlei;		//通知公告分类下拉列表
//	List<Map<String,Object>> map_tzgg_liucheng;		//通知公告流程下拉列表
	
	
//	public static List<LoginOA> map_people_check;	
	
	// 
	public static int TYPE_IF_XZCF = 0;
	
	//人员查询功能相关数据
	public static List<DeptIDAndValue_Bean> list_bumen = new ArrayList<DeptIDAndValue_Bean>();
	public static List<IDAndValue_Bean> list_jiaose= new ArrayList<IDAndValue_Bean>();
	public static List<IDAndValue_Bean> list_zhiwu= new ArrayList<IDAndValue_Bean>();
	public static List<IDAndValue_Bean> list_gongzuozu= new ArrayList<IDAndValue_Bean>();
	public static List<LoginOA> list_left= new ArrayList<LoginOA>();
	public static List<LoginOA> list_right= new ArrayList<LoginOA>();

	public static List<File_ID_Name_Bean> list_files = new ArrayList<File_ID_Name_Bean>();		//下载的文件列表
	
	public static String FileIDs = "";			//上传文件后返回的文件ID
	
	public static String FileIDs_bdxx = "";		//表单信息中的上传附件文件ID
	
	public static String Module = "";//上传附件时需要传入的栏目类别
	
	public static int passOrNot = 1000;			//通过1，不通过0
	
	public static int shouwenOrfawen = 0;		//发文2000，收文4000
	
	public static int tildes = 0;		//表单信息中的机关代字号，选择文种文号后会赋值，用户获取正文

	public static int IfSendMsg = 1000;	//撰稿时选择是否发送短信，1是，0否
	
	public static int IfguiDang = 1000;	//撰稿时选择是否归档，1是，0否
	
	public static String endTime = "";	//撰稿时填写的截止日期
	
	public static String zg_biaoti = "";	//撰稿时填写的截止日期
	
	public static String ShenHeYiJian = "";	//审核时填写的审核意见
	
	public static MyCallBack callback = null;	//用于发文收文附件上传
	
	public static boolean IfFWSHShowCheckPeople = false; 
	public static boolean IfSWCLShowCheckPeople = false; 
	
	public static String userName = "";			//发文收文选择人员时得到的人员名称，将作为参数使用
	public static String userCode = "";			//发文收文选择人员时得到的人员code，将作为参数使用
}
