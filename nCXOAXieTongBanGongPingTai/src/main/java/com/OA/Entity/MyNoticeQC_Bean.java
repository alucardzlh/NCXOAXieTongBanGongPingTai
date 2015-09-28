package com.OA.Entity;

import java.util.List;

public class MyNoticeQC_Bean {
	public String totalcount;
	public List<Notice_Bean> rows;
	public String getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}
	public List<Notice_Bean> getRows() {
		return rows;
	}
	public void setRows(List<Notice_Bean> rows) {
		this.rows = rows;
	}

	
}
