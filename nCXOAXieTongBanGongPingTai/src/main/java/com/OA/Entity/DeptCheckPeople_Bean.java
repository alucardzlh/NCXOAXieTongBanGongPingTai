package com.OA.Entity;

import java.util.List;
import java.util.Map;

public class DeptCheckPeople_Bean {
	private String status;
	private List<DeptIDAndValue_Bean> list;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<DeptIDAndValue_Bean> getList() {
		return list;
	}
	public void setList(List<DeptIDAndValue_Bean> list) {
		this.list = list;
	}
}
