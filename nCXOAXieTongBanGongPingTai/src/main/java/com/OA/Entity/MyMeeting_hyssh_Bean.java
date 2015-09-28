package com.OA.Entity;

import java.util.List;

public class MyMeeting_hyssh_Bean {
	public String totalcount;
	public List<Meeting_hyssh_Bean> rows;
	public String getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}
	public List<Meeting_hyssh_Bean> getRows() {
		return rows;
	}
	public void setRows(List<Meeting_hyssh_Bean> rows) {
		this.rows = rows;
	}
}
