package com.OA.Entity;

import java.util.List;
import java.util.Map;

public class CheckPeople_Bean {
	private String status;
	private List<IDAndValue_Bean> list;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<IDAndValue_Bean> getList() {
		return list;
	}
	public void setList(List<IDAndValue_Bean> list) {
		this.list = list;
	}
}
