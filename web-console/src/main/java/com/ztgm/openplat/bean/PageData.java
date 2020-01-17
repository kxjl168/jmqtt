package com.ztgm.openplat.bean;

public class PageData {
	private String Action;// 要执行的操作，取值接口名称：ListRuleActions
	private Integer PageSize = 10;
	private Integer CurrentPage = 1;

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public Integer getPageSize() {
		return PageSize;
	}

	public void setPageSize(Integer pageSize) {
		PageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return CurrentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		CurrentPage = currentPage;
	}

}
