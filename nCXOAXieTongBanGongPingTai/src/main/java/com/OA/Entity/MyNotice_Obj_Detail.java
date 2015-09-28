package com.OA.Entity;

import java.util.List;

public class MyNotice_Obj_Detail {
	private String Title;
	private String Subject;
	private String BrowseName;
	private String BrowseCode;
	private String EndDate;
	private int TypeID;
	private int NoticeID;
	private String Content;
	private String TypeName;
	private List<File_ID_Name_Bean> FileList;
	
	public String getTypeName() {
		return TypeName;
	}
	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getBrowseName() {
		return BrowseName;
	}
	public void setBrowseName(String browseName) {
		BrowseName = browseName;
	}
	public String getBrowseCode() {
		return BrowseCode;
	}
	public void setBrowseCode(String browseCode) {
		BrowseCode = browseCode;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public int getTypeID() {
		return TypeID;
	}
	public void setTypeID(int typeID) {
		TypeID = typeID;
	}
	public int getNoticeID() {
		return NoticeID;
	}
	public void setNoticeID(int noticeID) {
		NoticeID = noticeID;
	}
	public List<File_ID_Name_Bean> getFileList() {
		return FileList;
	}
	public void setFileList(List<File_ID_Name_Bean> fileList) {
		FileList = fileList;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
}
