package com.yinww.account.table;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class DataTableResult<T> {
	private int draw;

    private int recordsTotal;

    private int recordsFiltered;

    private List<T> data;
    
    public DataTableResult() {
	}
    
    public DataTableResult(PageInfo<T> pageInfo, int draw) {
    	this.draw = draw;
    	data = pageInfo.getList();
    	recordsTotal = new Long(pageInfo.getTotal()).intValue();
    	recordsFiltered = recordsTotal;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
    
}
