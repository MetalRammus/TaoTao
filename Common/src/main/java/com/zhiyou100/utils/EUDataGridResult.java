package com.zhiyou100.utils;

import java.util.List;

/**
 * 返回视图的json
 * 
 * @author 19229
 *
 */
public class EUDataGridResult {

	private long total;
	private List<?> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
