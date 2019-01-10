package com.dsq.dsq.core.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Indexed;

import coo.base.util.DateUtils;
import coo.core.security.annotations.LogField;
import coo.core.security.entity.ResourceEntity;

/**
 * 销售记录
 */
@Entity
@Table(name = "SaleRecord")
@Indexed(index = "SaleRecord")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SaleRecord extends ResourceEntity<User> {
	private static final long serialVersionUID = 2235077315756378154L;
	/** 编号 */
	private String number;
	/** 产品名称 */
	private String productName;
	/** 客户名称 */
	private String accountName;
	/** 商品规格 */
	private String specification;
	/** 包装单位 */
	private String unit;
	/** 销售数量 */
	private Long amount;
	/** 销售日期 */
	@Temporal(TemporalType.DATE)
	@LogField(text = "销售日期", format = DateUtils.DAY)
	private Date saleDate;
	/** 生产日期 */
	@Temporal(TemporalType.DATE)
	@LogField(text = "生产日期", format = DateUtils.DAY)
	private Date produceDate;
	/** 商品批号 */
	private String batchNumber;
	/** 生产厂家 */
	private String manufacture;

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

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public String getProductName() {
		return productName;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

}
