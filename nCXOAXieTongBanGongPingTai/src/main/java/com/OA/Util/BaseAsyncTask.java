package com.OA.Util;

import java.io.IOException;
import java.util.Map;

import org.ksoap2.SoapFault;
import org.xmlpull.v1.XmlPullParserException;

import com.OA.View.LoadingDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

public class BaseAsyncTask extends AsyncTask<Integer, Integer, Integer>{
	private static final int SHOW_BAR = 4097;
	public static final int UPDATE_FAIL = -1;
	public static final int UPDATE_SUCCEED = 1;
	private Handler asyncHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SHOW_BAR:
				if (LD != null && context != null) {
					// if (((BaseActivity) context).isShowDialog()) {
					LD.show();
					// ((BaseActivity) context).setOnCloseProgressDialog(LD);
					// } else {
					// LD.cancel();
					// }
				}
				break;
			}
		}
	};
	private Context context;
	private LoadingDialog LD;
	protected String resultGroup;
	private boolean showBar = false;
	private int showTime = 1000;
	public static String str_url;

	public BaseAsyncTask(Context paramContext) {
		this.context = paramContext;

	}

	public BaseAsyncTask(Context paramContext, boolean paramBoolean) {
		this.context = paramContext;
		this.showBar = paramBoolean;
	}

	private void closeBar() {
		if (this.LD != null) {
			this.LD.dismiss();
			this.LD = null;
			this.context = null;
		}
	}


	/**
	 * 登录
	 */
	public String downloadDB(Context context, String methodName,String endPoint, String str1,String str2,String str3) throws XmlPullParserException,IOException {
		String result = HttpUtil.getLoginOA(context, methodName, endPoint, str1,str2,str3);
		return result;
	}
	/**
	 * 收发文申请
	 */
	public String downloadDB(Context context,String methodName,String endPoint,String UserCode, String SignCode,int RoleID,int KindID) throws XmlPullParserException,IOException {
		String result=HttpUtil.GetSendApplyListManager(context, methodName, endPoint, UserCode, SignCode,RoleID, KindID);
		return result;
	}
	/**
	 * 我的会议申请
	 */
	public String downloadDB(Context context,String methodName, String endPoint, int PageSize,  int PageNow , String UserCode, String SignCode, String RoomName, String StarTime,String EndTime) throws XmlPullParserException,IOException {
		String result =HttpUtil.getMeetingList(context, methodName, endPoint, PageSize, PageNow, UserCode, SignCode, RoomName, StarTime, EndTime);
		return result;
	}
	/**
	 * 会议室申请
	 */
	public String downloadDBB(Context context,String methodName, String endPoint, int PageSize,  int PageNow , String UserCode, String SignCode,String RoomName, String TypeID) throws XmlPullParserException, IOException{
		String result = HttpUtil.getMeetingApplyList(context, methodName, endPoint, PageSize, PageNow, UserCode, SignCode,RoomName,TypeID);
		return result;
	}
	/**
	 * 取消会议、 补报参会人员不参加会议操作
	 */
	public String downloadDB(Context context,String methodName, String endPoint,int RoomLogID,String reasonMsg,Boolean isPhoneMsgSend,String UserCode,String  SignCode)throws XmlPullParserException, IOException {
		String result=HttpUtil.Cancelmting(context, methodName, endPoint, RoomLogID, reasonMsg, isPhoneMsgSend, UserCode, SignCode);
		return result;
	}
	/**
	 * 补报参会人员列表
	 */
	public String downloadDB(Context context,String methodName, String endPoint,int RoomLogID,int DeptID,String UserCode,String  SignCode)throws XmlPullParserException, IOException {
		String result=HttpUtil.AddMtingWorkerList(context, methodName, endPoint, RoomLogID, DeptID, UserCode, SignCode);
		return result;
	}

	/**
	 * 补报参会人员
	 */
	public String downloadDB(Context context,String methodName, String endPoint,int RoomLogID,int DeptID,String UserPostName,String JoinWorkerName,String JoinWorkerNames,String JoinWorkerCodes,String UserMobile,String UserCode,Boolean isSendMsg,String  SignCode)throws XmlPullParserException, IOException {
		String  result=HttpUtil.BuBaoJoinWorker(context, methodName, endPoint, RoomLogID, DeptID, UserPostName, JoinWorkerName, JoinWorkerNames, JoinWorkerCodes, UserMobile, UserCode, isSendMsg, SignCode);
		return result;
	}
	/**
	 * 修改会议时间
	 */
	public String downloadDB(Context context,String methodName, String endPoint,int RoomLogID,String startTime,String endTime,String UserCode,String  SignCode)throws XmlPullParserException, IOException {
		String result=HttpUtil.ChangeMtingTime(context, methodName, endPoint, RoomLogID, startTime, endTime, UserCode, SignCode);
		return result;
	}
	/**
	 * 会议通知签收
	 */
	public String downloadDB(Context context,String methodName, String endPoint,int MeetingID,String UserCode,String  SignCode)throws XmlPullParserException, IOException {
		String result=HttpUtil.Signoff(context, methodName, endPoint, MeetingID, UserCode, SignCode);
		return result;
	}
//	/**
//	 * 会议室申请预约接口
//	 * @param endPoint 
//	 */
//	public String downloadDB(Context context,String methodName, String endPoint,
//			String SignCode,  String UserCode,
//			String Host,  String Applicant,
//			String InvitingCode,  String InvitingName,
//			String MeetingContent,  String MeetingStart,
//			String MeetingEnd,  String MeetingTitle,
//			String Memo,  String Position,
//			String RecorderName,  String RoomName,
//			String SendType,  String FileIDs,  int RoomID,
//			int InvitingCount,  int MatCard,
//			int ContainPeople,  int RoomLogID){
//		String result = HttpUtil.ApplyMtRoom(context, methodName, endPoint, SignCode, UserCode, Host, Applicant, InvitingCode, InvitingName, MeetingContent, MeetingStart, MeetingEnd, MeetingTitle, Memo, Position, RecorderName, RoomName, SendType, FileIDs, RoomID, InvitingCount, MatCard, ContainPeople, RoomLogID);
//		return result;
//	}
	/**
	 * 收文审核、发文审核、我的收文
	 * @return
	 */
	public String downloadDB(Context context, String methodName,String endPoint,String UserCode, String SignCode, int PageSize, int PageNow,String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime) throws XmlPullParserException,IOException{
		String result=HttpUtil.GetSendCheckListManager(context, methodName, endPoint, UserCode, SignCode, PageSize, PageNow, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
		return result;
	}
	/**
	 * 收文浏览、发文浏览、我的发文、我的收文
	 * @return
	 */
	public String downloadDB(Context context, String methodName,String endPoint,String UserCode, String SignCode, int PageSize, int PageNow,String Status,String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime) throws XmlPullParserException,IOException{
		String result=HttpUtil.GetSendBrowseListManager(context, methodName, endPoint, UserCode, SignCode, PageSize, PageNow,Status, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
		return result;
	}
	/**
	 * 我的收文
	 */
	public String downloadDBS(Context context, String methodName,String endPoint,String UserCode, String SignCode, int PageSize, int PageNow,String Status,String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime) throws XmlPullParserException,IOException{
		String result=HttpUtil.getMyRecApplyList(context, methodName, endPoint, UserCode, SignCode, PageSize, PageNow,Status, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
		return result;
	}
	/**
	 * 收文浏览已阅列表
	 */
	public String downloadDBB(Context context, String methodName,String endPoint,String UserCode, String SignCode, int PageSize, int PageNow,String Status,String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime) throws XmlPullParserException,IOException{
		String result=HttpUtil.getRecBrowseList(context, methodName, endPoint, UserCode, SignCode, PageSize, PageNow,Status, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
		return result;
	}
	/**
	 * 浏览人员列表
	 */
	public String downloadDBS(Context context, String methodName, String endPoint, int PageSize, int PageNow, int WFID, String Status) throws XmlPullParserException, IOException{
		String result = HttpUtil.browsePeopleList(context, methodName, endPoint, PageSize, PageNow, WFID, Status);
		return result;
	}
	/**
	 * 可浏览人员
	 */
	public String downloadDBS(Context context, String methodName, String endPoint, int WFID) throws XmlPullParserException, IOException{
		String result = HttpUtil.browsePeople(context, methodName, endPoint, WFID);
		return result;
	}
	/**
	 * 未浏览人员
	 */
	public String downloadDBBS(Context context, String methodName, String endPoint, int WFID) throws XmlPullParserException, IOException{
		String result = HttpUtil.notbrowsePeople(context, methodName, endPoint, WFID);
		return result;
	}
	/**
	 * 超时浏览人员
	 */
	public String downloadDBB(Context context, String methodName, String endPoint, int WFID) throws XmlPullParserException, IOException{
		String result = HttpUtil.overbrowsePeople(context, methodName, endPoint, WFID);
		return result;
	}
	
	/**
	 * 收文处理
	 */
	public String downloadDBB(Context context, String methodName,String endPoint,String UserCode, String SignCode, int PageSize, int PageNow,String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime) throws XmlPullParserException,IOException{
		String result=HttpUtil.getRecCheckList(context, methodName, endPoint, UserCode, SignCode, PageSize, PageNow, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
		return result;
	}
	/**
	 * 收文待阅列表
	 */
	public String downloadDBBS(Context context, String methodName,String endPoint,String UserCode, String SignCode, int PageSize, int PageNow,String Status,String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime) throws XmlPullParserException,IOException{
		String result=HttpUtil.getRecBrowseList1(context, methodName, endPoint, UserCode, SignCode, PageSize, PageNow,Status, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
		return result;
	}

	/**
	 * 获取通知公告起草,审核列表
	 * @return
	 */
	public String downloadDB(Context context, String methodName,String endPoint,int PageSize, int PageNow, String UserCode, String SignCode,String Title) throws XmlPullParserException,IOException{
		String result=HttpUtil.getNoticeListQC(context, methodName, endPoint, PageSize, PageNow, UserCode, SignCode, Title);
		return result;
	}
	/**
	 * 获取通知浏览公告列表
	 */
	public String downloadDBB(Context context, String methodName,String endPoint,int PageSize, int PageNow, String UserCode, String SignCode,String Title) throws XmlPullParserException,IOException{
		String result = HttpUtil.getNoticeReadList(context, methodName, endPoint, PageSize, PageNow, UserCode, SignCode, Title);
		return result;
		
	}
	
	/**
	 * 获取通知公告详情接口
	 */
	public String downloadDBNotice(Context context,String methodName,String endPoint,String UserCode, String SignCode,int NoticeID) throws XmlPullParserException,IOException {
		String result=HttpUtil.getNoticeDetail(context, methodName, endPoint, UserCode, SignCode, NoticeID);
		return result;
	}
	
	
	/**
	 * 通知公告审核,删除接口
	 */
	public String VerifyNotice(Context context,String methodName,String endPoint,String UserCode, String SignCode,int NoticeID,String Flag,int flowid,int WFStepID ) throws XmlPullParserException,IOException {
		String result=HttpUtil.VerifyNotice(context, methodName, endPoint, UserCode, SignCode, NoticeID,Flag,flowid,WFStepID);
		return result;
	}

	/**
	 * 通知公告修改,添加接口
	 */
	public String SaveNotice(Context context,String methodName,String endPoint,Map<String, Object> mapDatas ) throws XmlPullParserException,IOException {
		String result=HttpUtil.VerifyNotice(context, methodName, endPoint, mapDatas);
		return result;
	}
	
	/**
	 * 通知公告修改,添加接口
	 */
	public String getDatasTZGG(Context context,String methodName,String endPoint,Map<String, Object> mapDatas ) throws XmlPullParserException,IOException {
		String result=HttpUtil.VerifyNotice(context, methodName, endPoint, mapDatas);
		return result;
	}
	
	/**
	 * 获取会议信息详情接口
	 */
	public String downloadDBMeeting(Context context,String methodName,String endPoint,String UserCode, String SignCode,int RoomLogID) throws XmlPullParserException,IOException {
		String result=HttpUtil.getMeetingDetail(context, methodName, endPoint, UserCode, SignCode, RoomLogID);
		return result;
	}
	
	/**
	 * 会议信息审核接口
	 */
	public String VerifyMtRoom(Context context,String methodName,String endPoint,String UserCode, String SignCode,int RoomLogID,String Flag,int StepDataID ) throws XmlPullParserException,IOException {
		String result=HttpUtil.VerifyMtRoom(context, methodName, endPoint, UserCode, SignCode, RoomLogID,Flag,StepDataID);
		return result;
	}
	
	/**
	 * 获取发文表单数据
	 */
	public  String downloadDDB(Context context, String methodName,String endPoint, String UserCode, String SignCode, int FlowID,
			int WFID ,int CurNodeID)throws XmlPullParserException,IOException {
		String result=HttpUtil.getFlowFormElements(context, methodName, endPoint, UserCode, SignCode, FlowID, WFID,CurNodeID);
		return result;
	}

	
	/**
	 * 会议室申请接口
	 */
	public String ApplyMtRoom(Context context,String methodName,String endPoint,Map<String,Object> map) throws XmlPullParserException,IOException {
		String result=HttpUtil.ApplyMtRoom(context, methodName, endPoint, map);
		return result;
	}
	
	/**
	 * 会议室申请接口
	 */
	public String SupplyFile(Context context,String methodName,String endPoint,Map<String,Object> map) throws XmlPullParserException,IOException {
		String result=HttpUtil.SupplyFile(context, methodName, endPoint, map);
		return result;
	}
	
	/**
	 * 公文退回
	 */
	private String downloadDB(Context context2, String methodName,String endPoint, int WFStepID,String UserCode,String SignCode,String Memo)throws XmlPullParserException,IOException {
		String result=HttpUtil.GetBackApply(context2, methodName, endPoint, WFStepID, UserCode, SignCode, Memo);
		return result;
	}
//	/**
//	 * 我的会议
//	 */
//	public static String downloadDBB(Context context,String methodName,String endPoint,String UserCode, String SignCode, int PageSize, int PageNow, String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime) throws XmlPullParserException,IOException {
//		String result=HttpUtil.GetMySendApplyList(context, methodName, endPoint, UserCode, SignCode, PageSize, PageNow, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
//		return result;
//	}
	private void setDownLoadProgress() {
		this.LD = new LoadingDialog(context, "", false);
		this.LD.setTitle("正在交互数据");
		this.LD.setMessage("请稍候...");
		this.LD.setCanceledOnTouchOutside(false);
		this.LD.setOnCancelListener(new DialogInterface.OnCancelListener() {
			public void onCancel(DialogInterface paramDialogInterface) {

			}
		});
		// this.LD.setProgressStyle(0);
		this.asyncHandler.sendEmptyMessageDelayed(SHOW_BAR, this.showTime);
	}

	protected Integer doInBackground(Integer[] paramArrayOfInteger) {
		return 1;
	}

	
	/**
	 *登录
	 * @param methodName
	 * @param endPoint
	 * @param str1 用户名
	 * @param str2 密码
	 * @param str3 登录来源
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public String downloadDB(String methodName, String endPoint, String str1,String str2, String str3) throws XmlPullParserException,IOException {
		return downloadDB(this.context, methodName, endPoint, str1, str2, str3);
	}
	/**
	 * 收发文申请,获取发文表单
	 */
	public String downloadDB(String methodName, String endPoint,String UserCode, String SignCode,int RoleID, int KindID)throws XmlPullParserException, IOException {
		return downloadDB(this.context, methodName, endPoint, UserCode,SignCode, RoleID,KindID);
	}
	
	/**
	 * 公文退回
	 */
	public String downloadDB(String methodName,String endPoint,int WFStepID,String UserCode,String SingCode,String Memo)throws XmlPullParserException, IOException{
		return downloadDB(this.context,methodName,endPoint,WFStepID,UserCode,SingCode,Memo);
		
	}

	/**
	 * 取消会议、 补报参会人员不参加会议操作
	 */
	public String downloadDB(String methodName, String endPoint,int RoomLogID,String reasonMsg,Boolean isPhoneMsgSend,String UserCode,String  SignCode)throws XmlPullParserException, IOException {
		return downloadDB(this.context, methodName, endPoint, RoomLogID,reasonMsg, isPhoneMsgSend,UserCode,SignCode);
	}
	/**
	 * 补报参会人员列表
	 */
	public String downloadDB(String methodName, String endPoint,int RoomLogID,int DeptID,String UserCode,String  SignCode)throws XmlPullParserException, IOException {
		return downloadDB(this.context, methodName, endPoint, RoomLogID,DeptID,UserCode,SignCode);
	}

	/**
	 * 补报参会人员
	 */
	public String downloadDB(String methodName, String endPoint,int RoomLogID,int DeptID,String UserPostName,String JoinWorkerName,String JoinWorkerNames,String JoinWorkerCodes,String UserMobile,String UserCode,Boolean isSendMsg,String  SignCode)throws XmlPullParserException, IOException {
		return downloadDB(this.context,methodName, endPoint, RoomLogID, DeptID, UserPostName, JoinWorkerName, JoinWorkerNames, JoinWorkerCodes, UserMobile, UserCode, isSendMsg, SignCode);
	}
	/**
	 * 修改会议时间
	 */
	public String downloadDB(String methodName, String endPoint,int RoomLogID,String startTime,String endTime,String UserCode,String  SignCode)throws XmlPullParserException, IOException {
		return downloadDB(this.context, methodName, endPoint, RoomLogID,startTime,endTime,UserCode,SignCode);
	}
	/**
	 *会议通知签收
	 */
	public String downloadDB(String methodName, String endPoint,int MeetingID,String UserCode,String  SignCode)throws XmlPullParserException, IOException {
		return downloadDB(this.context, methodName, endPoint, MeetingID,UserCode,SignCode);
	}
	/**
	 * 人员选择
	 */
	public String downloadDPeoPle(String methodName, String endPoint,String UserCode)throws XmlPullParserException, IOException {
		return downloadDPeoPle(this.context, methodName, endPoint, UserCode);
	}
	/**
	 * 获取下拉列表数据
	 */
	public String downloadDPeoPle(Context context,String methodName,String endPoint,String UserCode) throws XmlPullParserException,IOException {
		String result=HttpUtil.checkPeople(context, methodName, endPoint, UserCode,0);
		return result;
	}
	
	/**
	 * 获取下拉列表数据
	 */
	public String downloadDPeoPle(Context context,String methodName,String endPoint,int DeptID) throws XmlPullParserException,IOException {
		String result=HttpUtil.checkPeople(context, methodName, endPoint,"", DeptID);
		return result;
	}
	
	/**
	 * 获取搜索人员结果
	 */
	public String SelectDeptPerson(Context context,String methodName,String endPoint, Map<String,Object> mapDatas) throws XmlPullParserException,IOException {
		String result=HttpUtil.SelectDeptPerson(context, methodName, endPoint, mapDatas);
		return result;
	}
	/**
	 * 发文审核、收文审核、我的收文
	 * @param methodName 
	 * @param endPoint 
	 * @param UserCode  用户名
	 * @param SignCode 加验证重码
	 * @param PageSize 每页条数
	 * @param PageNow  当前页
	 * @param Title  公文Title
	 * @param WenHao 文号/  LaiWenCompany来文单位（收文审核）
	 * @param NiGaoCompany 拟稿单位   / WenHao 文号（收文审核）
	 * @param SendStartTime 发文日期
	 * @param SendEndTime 发文日期
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */

	public String downloadDB(String methodName, String endPoint,String UserCode, String SignCode, int PageSize, int PageNow,String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime)throws XmlPullParserException, IOException {
		return downloadDB(this.context,methodName, endPoint, UserCode, SignCode, PageSize, PageNow, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
	
	
	}
	
	/**
	 * 发文浏览、收文浏览，我的发文
	 * @param methodName
	 * @param endPoint
	 * @param UserCode  用户名
	 * @param SignCode 加验证重码
	 * @param PageSize 每页条数
	 * @param PageNow  当前页
	 * @param Status	状态:100待阅，200已阅
	 * @param Title  公文Title
	 * @param WenHao 文号
	 * @param NiGaoCompany 拟稿单位
	 * @param SendStartTime 发文日期
	 * @param SendEndTime 发文日期
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public String downloadDB(String methodName, String endPoint,String UserCode, String SignCode, int PageSize, int PageNow, String Status,String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime)throws XmlPullParserException, IOException {
		return downloadDB(context, methodName, endPoint, UserCode, SignCode, PageSize, PageNow, Status, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
	}
	/**
	 * 获取发文表单
	 */
	public String downloadDDB(String methodName,String endPoint,String UserCode,String SignCode,int FlowID,int WFID ,int CurNodeID)throws XmlPullParserException, IOException {
		return downloadDDB( context,methodName, endPoint, UserCode, SignCode, FlowID, WFID,CurNodeID);
	}
	/**
	 * 收文处理
	 */
	public String downloadDBB(String methodName, String endPoint,String UserCode, String SignCode, int PageSize, int PageNow, String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime)throws XmlPullParserException, IOException {
		return downloadDBB(context, methodName, endPoint, UserCode, SignCode, PageSize, PageNow, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
	}

	/**
	 * 我的收文
	 * @param methodName
	 * @param endPoint
	 * @param UserCode
	 * @param SignCode
	 * @param PageSize
	 * @param PageNow
	 * @param Status
	 * @param Title
	 * @param WenHao
	 * @param NiGaoCompany
	 * @param SendStartTime
	 * @param SendEndTime
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public String downloadDBS(String methodName, String endPoint,String UserCode, String SignCode, int PageSize, int PageNow, String Status,String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime)throws XmlPullParserException, IOException {
		return downloadDBS(context, methodName, endPoint, UserCode, SignCode, PageSize, PageNow, Status, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
	}
	/**
	 * 收文已阅列表
	 */
	public String downloadDBB(String methodName, String endPoint,String UserCode, String SignCode, int PageSize, int PageNow, String Status,String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime)throws XmlPullParserException, IOException {
		return downloadDBB(context, methodName, endPoint, UserCode, SignCode, PageSize, PageNow, Status, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
	}
	
	/**
	 * 收文待阅列表
	 */
	public String downloadDBBS(String methodName, String endPoint,String UserCode, String SignCode, int PageSize, int PageNow, String Status,String Title, String WenHao, String NiGaoCompany, String SendStartTime, String SendEndTime)throws XmlPullParserException, IOException {
		return downloadDBBS(context, methodName, endPoint, UserCode, SignCode, PageSize, PageNow, Status, Title, WenHao, NiGaoCompany, SendStartTime, SendEndTime);
	}
	
	/**
	 * 收文浏览列表
	 */
	public String downloadDBS(String methodName, String endPoint, int PageSize, int PageNow, int WFID, String Status) throws XmlPullParserException, IOException{
		return downloadDBS(context, methodName, endPoint, PageSize, PageNow, WFID, Status);
	}
	/**
	 * 可浏览人员
	 */
	public String downloadDBS(String methodName, String endPoint,int WFID) throws XmlPullParserException, IOException{
		return downloadDBS(context, methodName, endPoint,  WFID);
	}
	/**
	 * 未浏览人员
	 */
	public String downloadDBBS(String methodName, String endPoint,int WFID) throws XmlPullParserException, IOException{
		return downloadDBBS(context, methodName, endPoint, WFID);
	}
	/**
	 * 超时浏览人员
	 */
	public String downloadDBB(String methodName, String endPoint,int WFID) throws XmlPullParserException, IOException{
		return downloadDBB(context, methodName, endPoint, WFID);
	}
	
	/**
	 * 我的会议申请、会议审核
	 * @param methodName 
	 * @param endPoint
	 * @param PageSize 一页显示数目
	 * @param PageNow	当前页
	 * @param UserCode	当前登录账号
	 * @param SignCode	安全加密码	
	 * @param RoomName	会议室：为了查询需要
	 * @param StarTime	开始时间： 为了查询需要	
	 * @param EndTime	结束时间： 为了查询需要
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public String downloadDB(String methodName, String endPoint, int PageSize,  int PageNow , String UserCode,
			 String SignCode, String RoomName, String StarTime,
			 String EndTime)throws XmlPullParserException, IOException {
				return downloadDB(context, methodName, endPoint, PageSize, PageNow, UserCode, SignCode, RoomName, StarTime, EndTime);
		
	}
//	/**
//	 * 会议室申请预约
//	 * @param methodName
//	 * @param endPoint
//	 * @param SignCode 安全验证码
//	 * @param UserCode 用户名
//	 * @param Host 主持人
//	 * @param Applicant 申请人
//	 * @param InvitingCode 出席人员的UserCode多个人用“,”分割
//	 * @param InvitingName 出席人员的UserName多个人用“,”分割
//	 * @param MeetingContent 会议内容
//	 * @param MeetingStart 会议开始时间
//	 * @param MeetingEnd 会议结束时间
//	 * @param MeetingTitle 会议议题
//	 * @param Memo 备注
//	 * @param Position 地址
//	 * @param RecorderName 纪录人
//	 * @param RoomName 会议室名
//	 * @param SendType 发送方式 1:短信发送 + 系统发送  2：系统发送
//	 * @param FileIDs 文件ID多个文件ID以“,”分割
//	 * @param RoomID 会议室ID
//	 * @param InvitingCount 出席人数
//	 * @param MatCard 席卡数
//	 * @param ContainPeople 容纳人数
//	 * @param RoomLogID 会议申请记录的ID
//	 * @return
//	 * @throws XmlPullParserException
//	 * @throws IOException
//	 */
//	public String downloadDB(String methodName, String endPoint, 
//			 String SignCode,  String UserCode,
//			 String Host,  String Applicant,
//			 String InvitingCode,  String InvitingName,
//			 String MeetingContent,  String MeetingStart,
//			 String MeetingEnd,  String MeetingTitle,
//			 String Memo,  String Position,
//			 String RecorderName,  String RoomName,
//			 String SendType,  String FileIDs,  int RoomID,
//			 int InvitingCount,  int MatCard,
//			 int ContainPeople,  int RoomLogID
//			)throws XmlPullParserException, IOException {
//		return downloadDB(context,methodName, endPoint, SignCode, UserCode, Host, Applicant, InvitingCode, InvitingName, MeetingContent, MeetingStart, MeetingEnd, MeetingTitle, Memo, Position, RecorderName, RoomName, SendType, FileIDs, RoomID, InvitingCount, MatCard, ContainPeople, RoomLogID);
//	}
	
	/**
	 * 会议室申请
	 */
	public String downloadDBBS(Context context,String methodName, String endPoint, int PageSize,  
			int PageNow , String UserCode, String SignCode,String RoomName, String TypeName) throws XmlPullParserException, IOException{
		return downloadDBB(context, methodName, endPoint, PageSize, PageNow, UserCode, SignCode, RoomName, TypeName);
	}
	
	/**
	 * 获取通知公告起草列表
	 * @param paramActivity
	 * @param paramInt
	 * @param UserCode  用户名
	 * @param SignCode 加验证重码
	 * @param PageSize 每页条数
	 * @param PageNow  当前页
	 * @param Title  公文Title
	 */

	public String downloadDB(String methodName, String endPoint,int PageSize, int PageNow, String UserCode, String SignCode,String Title)throws XmlPullParserException, IOException {
		return downloadDB(this.context,methodName, endPoint, PageSize, PageNow, UserCode, SignCode, Title);
	
	
	}
	/**
	 * 获取通知公告阅读列表
	 * @param methodName
	 * @param endPoint
	 * @param PageSize
	 * @param PageNow
	 * @param UserCode
	 * @param SignCode
	 * @param Title
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public String downloadDBB(String methodName, String endPoint,int PageSize, int PageNow, String UserCode, String SignCode,String Title)throws XmlPullParserException, IOException{
		return downloadDBB(this.context,methodName, endPoint, PageSize, PageNow, UserCode, SignCode, Title);
	}
	
	/**
	 * 下载附件接口
	 */
	public String downloadFile(Context context,String methodName,String endPoint,int FileID) throws XmlPullParserException,IOException {
		String result=HttpUtil.DownloadFile(context, methodName, endPoint, FileID);
		return result;
	}
	/**
	 * 撰稿获取第一步信息接口
	 */
	public String GetApplyNextStepInfo(Context context,String methodName,String endPoint,final int FlowID, final int FlowStepID,final String UserCode) throws XmlPullParserException,IOException {
		String result=HttpUtil.GetApplyNextStepInfo(context, methodName,endPoint, FlowID, FlowStepID,UserCode);
		return result;
	}
	/**
	 * 审核获取第一步审核信息接口
	 */
	public String GetCheckNextStepInfo(Context context,String methodName,String endPoint,final int FlowID, final int WFID,final int FlowStepID,final String CurNode,final String ISPass,final int ISBrachSel,final String UserCode) throws XmlPullParserException,IOException {
		String result=HttpUtil.GetCheckNextStepInfo(context, methodName,endPoint, FlowID, WFID,FlowStepID,CurNode,ISPass,ISBrachSel,UserCode);
		return result;
	}
	/**
	 * 发文收文审核接口和公文申请保存接口
	 */
	public String SaveSendCheckStepData(Context context,String methodName,String endPoint,final Map<String,Object> mapDatas) throws XmlPullParserException,IOException {
		String result=HttpUtil.SaveSendCheckStepData(context, methodName,endPoint,mapDatas);
		return result;
	}
	/**
	 * 判断流程是否结束
	 */
	public String ISEnd(Context context,String methodName,String endPoint,final String CurStepNode,final String ISPass,final int FlowID) throws XmlPullParserException,IOException {
		String result=HttpUtil.ISEnd(context, methodName,endPoint,CurStepNode,ISPass,FlowID);
		return result;
	}
	
	/**
	 * 判断流程是否需要选人
	 */
	public String IsSelBrowseUsers(Context context,String methodName,String endPoint,final int FlowID,final int CurNodeID) throws XmlPullParserException,IOException {
		String result=HttpUtil.IsSelBrowseUsers(context, methodName,endPoint,FlowID,CurNodeID);
		return result;
	}
	/**
	 * 下载公文
	 */
	public String LoadArchiveData(Context context,String methodName,String endPoint,final int Tildes,final int ArcStepID,final int WFID,final int WFStepID) throws XmlPullParserException,IOException {
		String result=HttpUtil.LoadArchiveData(context, methodName,endPoint,Tildes,ArcStepID,WFID,WFStepID);
		return result;
	}
	/**
	 * 保存公文
	 */
	public String SaveArchiveData(Context context,String methodName,String endPoint,final int ArcStepID,final int WFID,final int WFStepID,final String UserCode,final String SignCode,final String mFileBody) throws XmlPullParserException,IOException {
		String result=HttpUtil.SaveArchiveData(context, methodName,endPoint,ArcStepID ,WFID,WFStepID,UserCode,SignCode,mFileBody);
		return result;
	}
	
	/**
	 * 上传附件接口
	 */
	public String uploadFile(Context context,String methodName,String endPoint,Map<String,Object> map) throws XmlPullParserException,IOException {
		String result=HttpUtil.DownloadFile(context, methodName, endPoint, map);
		return result;
	}
	
	
	protected void onPostExecute(Integer paramInteger) {
		super.onPostExecute(paramInteger);
		if (this.showBar)
			closeBar();
	}

	protected void onPreExecute() {
		super.onPreExecute();
		if (this.showBar) {
			setDownLoadProgress();
		}
	}

	public int getShowTime() {
		return this.showTime;
	}

	public boolean isShowBar() {
		return this.showBar;
	}

	public void setShowBar(boolean paramBoolean) {
		this.showBar = paramBoolean;
	}

	public void setShowTime(int paramInt) {
		this.showTime = paramInt;
	}



}
