package com.OA.Entity;

import java.io.Serializable;

public class File_ID_Name_Bean implements Serializable{
	private int FileID;			//文件ID
	private String ShowName;	//文件名称
	public int getFileID() {
		return FileID;
	}
	public void setFileID(int fileID) {
		FileID = fileID;
	}
	public String getShowName() {
		return ShowName;
	}
	public void setShowName(String showName) {
		ShowName = showName;
	}
}
