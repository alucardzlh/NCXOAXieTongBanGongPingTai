package com.OA.Entity;

import java.util.List;

public class MyNoticeSH_Bean {
	public String totalcount;
	public List<Notice_sh_Bean> rows;
	public String getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}
	public List<Notice_sh_Bean> getRows() {
		return rows;
	}
	public void setRows(List<Notice_sh_Bean> rows) {
		this.rows = rows;
	}
}
