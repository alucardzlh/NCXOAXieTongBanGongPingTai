package com.OA.Entity;

import java.util.List;

public class SelectPeople_Bean {
	private String status;
	private List<LoginOA> list;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<LoginOA> getList() {
		return list;
	}
	public void setList(List<LoginOA> list) {
		this.list = list;
	}
}
