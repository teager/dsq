package com.dsq.dsq.core.model;

import coo.core.model.DateRangeSearchModel;

/**
 * 订单管理模块搜索模型。
 */
public class MedicineSearchModel extends DateRangeSearchModel {

	/** 订单号 */
	private String number;
	/** 影院订单号 */
	private String name;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}