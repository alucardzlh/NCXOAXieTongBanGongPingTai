package com.OA.Data;

import java.util.List;
import java.util.Map;

import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref.Scope;

@SharedPref(value = Scope.APPLICATION_DEFAULT)
public interface InfoFile {
	String urlCenterWs(); // 服务器地址
	//登陆相关数据
	boolean isAutoLogin();// 是否自动登录

	String infoUsername();// 用户名,登陆时候填写的
	
	String infoUsername2();//登陆成功以后返回的值

	String infoUserType();//用户名MD5码
	
	String AdminID();// 用户ID
	
	String RoleID();// 用户ID
	
	String WorkID();// 用户ID
	
	String DeptID();// 用户ID

	String infoPassword();// 密码
	
	//登陆相关数据
	
	int NoticeID();		//通知公告列表id
	
	int flowid();
	
	int WFStepID();
	
	String statusOfNotice();	//通知公告状态
	
	int RoomLogID();		//会议申请ID，获取会议详情所需要的参数
	
	//String statusOfMeetingApply();	//会议室申请状态
	
	int typeOfHyxxBottomButton();	//用于区分会议信息详情中的按钮
	
	int ROOMID();				//会议ID
	String typeOfMeeting();		//会议种类
	String nameOfMeeting();		//会议室名称
	String addressOfMeeting();		//会议地址
	String rnrsOfMeeting();			//会议室容纳人数
	
	int StepDataID();		//会议审核接口需要的参数
	//RecApply_Xzswcl_Adapter
	int WFID();
	String Title();
	String endTime();
	
	/*
	 * 收发文审核相关数据
	 * */
	String statusOfSHENHE();	//行政发文收文审核状态
	int FlowID();
	int FlowStepID();
	String CurNode();
	// String infoCookie();
	// boolean isAutoLogin();//是否自动登录
	// String infoUsername();//用户�?
	// String infoUserId();//用户ID
	// String infoPassword();//密码
	// String numOfYslzs(); //预受理�?�?
	// String numOfDspzs(); //待审批�?�?
	// String numOfDffzs(); //待发放�?�?
	// String numOfDqwbjzs(); //到期未办结�?�?
	// String serviceSubjectId();//事项id
	// String serviceSubjectName();//事项名称
	// String postinfoname();//提交材料�?
	// String postinfoid();//提交材料id
	// String postinfotype();//提交材料类型
	// String shengqingrenname();//申请人姓�?
	// String shengqingrenCardId();//申请人身份证�?
	// String yslyw_shengqingrenCardId();//预受理业务申请人身份证号
	// String shengqingriqi();//申请日期
	// String banjieriqi();//办结日期
	// String wssq_wsyslh();//网上预受理号
	// String baselist_yewuliushuihao();//业务流水�?
	// String jgff_yewuliushuihao();//业务流水�?
	// String dxtz_beizhu();//短信通知里面的备�?
	// String dxtz_shibaiyuanyin();//短信通知里面的失败原�?
	// String ZTYWkaishishijian();//暂停业务�?��时间
	// String ZTYWyuanyin();//暂停业务原因
	// String bdlr_ywlsh(); //表单录入�?��的业务流水号
	// String bizArchivesId();//各项信息详情id
	// String bz_sqr_phone();//补正申请人电�?
	// String bz_msg();//补正短信
	// String bz_slczy();//补正受理操作�?
	// String bz_yy();//补正原因
	// String dfflb_bjzt();//待发放业务办结状�?
	
	
	int TypeOfLast();		//1000行政发文审核，2000撰稿，3000行政收文审核，4000登记
	int ArcStepID();		//公文修改后提交接口返回的ID 
}
