package com.cjc.bookstore.domain;

import java.util.List;

public class PageResult<T> {

	private List<T> list;//集合
	private long totalCount;//总记录数
	private int totalPage;//总页数
	private int currentPage;//当前页
	private int pageCount = 5;//每页显示的条数
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "PageResult [list=" + list + ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", currentPage="
				+ currentPage + ", pageCount=" + pageCount + "]";
	}
	
}
