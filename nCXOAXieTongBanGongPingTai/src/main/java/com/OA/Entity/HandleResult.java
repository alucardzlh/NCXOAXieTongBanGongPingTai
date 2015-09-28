package com.OA.Entity;

import java.util.List;

public class HandleResult {
	private String iSuccess;
	private String login_result;
	private MyNoticeQC_Bean bean;	//通知公告起草列表
	private Tzgg_tg_datas_Bean bean_fenlei;
	private Tzgg_tg_datas_Bean bean_liucheng;
	private LoginOA bean_login;
	private String saveFileResult;
	private FileDetail_Bean bean_file;
	private FirstNextStepDatas datas;
	private List<NextFlowStepsBean> list_bean_steps;
	private String content;
	private int ArcStepID;
	private ISEnd_Bean bean_isEnd;
	private boolean selPeople;
	
	public FirstNextStepDatas getDatas() {
		return datas;
	}

	public void setDatas(FirstNextStepDatas datas) {
		this.datas = datas;
	}

	public String getiSuccess() {
		return iSuccess;
	}

	public void setiSuccess(String iSuccess) {
		this.iSuccess = iSuccess;
	}

	public MyNoticeQC_Bean getBean() {
		return bean;
	}

	public void setBean(MyNoticeQC_Bean bean) {
		this.bean = bean;
	}

	public String getLogin_result() {
		return login_result;
	}

	public void setLogin_result(String login_result) {
		this.login_result = login_result;
	}

	public Tzgg_tg_datas_Bean getBean_fenlei() {
		return bean_fenlei;
	}

	public void setBean_fenlei(Tzgg_tg_datas_Bean bean_fenlei) {
		this.bean_fenlei = bean_fenlei;
	}

	public Tzgg_tg_datas_Bean getBean_liucheng() {
		return bean_liucheng;
	}

	public void setBean_liucheng(Tzgg_tg_datas_Bean bean_liucheng) {
		this.bean_liucheng = bean_liucheng;
	}

	public LoginOA getBean_login() {
		return bean_login;
	}

	public void setBean_login(LoginOA bean_login) {
		this.bean_login = bean_login;
	}

	public String getSaveFileResult() {
		return saveFileResult;
	}

	public void setSaveFileResult(String saveFileResult) {
		this.saveFileResult = saveFileResult;
	}

	public FileDetail_Bean getBean_file() {
		return bean_file;
	}

	public void setBean_file(FileDetail_Bean bean_file) {
		this.bean_file = bean_file;
	}

	public List<NextFlowStepsBean> getList_bean_steps() {
		return list_bean_steps;
	}

	public void setList_bean_steps(List<NextFlowStepsBean> list_bean_steps) {
		this.list_bean_steps = list_bean_steps;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getArcStepID() {
		return ArcStepID;
	}

	public void setArcStepID(int arcStepID) {
		ArcStepID = arcStepID;
	}

	public ISEnd_Bean getBean_isEnd() {
		return bean_isEnd;
	}

	public void setBean_isEnd(ISEnd_Bean bean_isEnd) {
		this.bean_isEnd = bean_isEnd;
	}

	public boolean isSelPeople() {
		return selPeople;
	}

	public void setSelPeople(boolean selPeople) {
		this.selPeople = selPeople;
	}
}
