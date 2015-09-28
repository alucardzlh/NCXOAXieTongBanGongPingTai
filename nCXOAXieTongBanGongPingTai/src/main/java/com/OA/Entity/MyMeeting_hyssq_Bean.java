package com.OA.Entity;

import java.util.List;

public class MyMeeting_hyssq_Bean {
	public String totalcount;
	public List<Meeting_hyssq_Bean> rows;
	public String getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}
	public List<Meeting_hyssq_Bean> getRows() {
		return rows;
	}
	public void setRows(List<Meeting_hyssq_Bean> rows) {
		this.rows = rows;
	}
}
