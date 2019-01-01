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
 * 药物。
 */
@Entity
@Table(name = "Medicine")
@Indexed(index = "Medicine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Medicine extends ResourceEntity<User> {
	private static final long serialVersionUID = 2235077315756378154L;
	/** 编号 */
	private String number;
	/** 名称 */
	private String name;
	/** 库存数量 */
	private Long amount;
	/** 商品规格 */
	private String specification;
	/** 包装单位 */
	private String unit;
	/** 生产日期 */
	@Temporal(TemporalType.DATE)
	@LogField(text = "生产日期", format = DateUtils.DAY)
	private Date produceDate;
	/** 失效日期 */
	@Temporal(TemporalType.DATE)
	@LogField(text = "失效日期", format = DateUtils.DAY)
	private Date expireDate;
	/** 仓库名称 */
	private String storeName;

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

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
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

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
