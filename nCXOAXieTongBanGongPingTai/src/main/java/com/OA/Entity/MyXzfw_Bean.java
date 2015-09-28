package com.OA.Entity;

import java.util.List;

public class MyXzfw_Bean {
	public String totalcount;
	public List<Wdxzfw_Bean> rows;
	public String getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}
	public List<Wdxzfw_Bean> getRows() {
		return rows;
	}
	public void setRows(List<Wdxzfw_Bean> rows) {
		this.rows = rows;
	}
}
