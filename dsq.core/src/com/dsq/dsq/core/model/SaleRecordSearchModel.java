package com.dsq.dsq.core.model;

import coo.core.model.DateRangeSearchModel;

/**
 * 订单管理模块搜索模型。
 */
public class SaleRecordSearchModel extends DateRangeSearchModel {

	/** 编号 */
	private String number;
	/** 客户名称 */
	private String accountName;
	/** 产品名称 */
	private String productName;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}