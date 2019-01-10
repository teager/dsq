package com.dsq.dsq.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsq.dsq.core.entity.SaleRecord;
import com.dsq.dsq.core.model.SaleRecordSearchModel;
import com.dsq.dsq.core.util.StringUtil;

import coo.base.model.Page;
import coo.base.util.BeanUtils;
import coo.base.util.StringUtils;
import coo.core.hibernate.dao.Dao;
import coo.core.message.MessageSource;
import coo.core.security.annotations.AutoFillIn;
import coo.core.security.annotations.DetailLog;
import coo.core.security.annotations.DetailLog.LogType;
import coo.core.security.annotations.SimpleLog;

/**
 * 库存管理。
 */
@Service
public class SaleRecordService {
  @Resource
  private Dao<SaleRecord> saleRecordDao;
  @Resource
  private MessageSource messageSource;

  @Transactional(readOnly = true)
  public List<SaleRecord> getAll() {
    return saleRecordDao.searchAll("number", true, SortField.Type.STRING);
  }

  @Transactional(readOnly = true)
  public SaleRecord get(String saleRecordId) {
    return saleRecordDao.get(saleRecordId);
  }

  @Transactional
  @AutoFillIn
  @DetailLog(target = "saleRecord", code = "saleRecord.add.log", vars = "saleRecord.number",
      type = LogType.NEW)
  public void create(SaleRecord saleRecord) {
    if (!saleRecordDao.isUnique(saleRecord, "number")) {
      messageSource.thrown("saleRecord.number.exist");
    }
    saleRecordDao.save(saleRecord);
  }

  @Transactional
  @AutoFillIn
  @DetailLog(target = "saleRecord", code = "saleRecord.edit.log", vars = "saleRecord.number",
      type = LogType.ALL)
  public void update(SaleRecord saleRecord) {
    if (!saleRecordDao.isUnique(saleRecord, "number")) {
      messageSource.thrown("saleRecord.number.exist");
    }
    SaleRecord origCompany = saleRecordDao.get(saleRecord.getId());
    BeanUtils.copyFields(saleRecord, origCompany);
  }

  @Transactional
  @SimpleLog(entityId = "saleRecord.id", code = "saleRecord.delete.log", vars="saleRecord.number")
  public void delete(SaleRecord saleRecord) {
    saleRecordDao.remove(saleRecord);
  }

  /**
	 * 分页搜索药品销售记录。
	 * 
	 * @param searchModel
	 *            搜索条件
	 * 
	 * @return 返回符合条件的药品销售记录分页对象。
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<SaleRecord> searchSaleRecord(
			SaleRecordSearchModel searchModel) {
	
		Criteria criteria = saleRecordDao.createCriteria();
		if (StringUtils.isNotBlank(searchModel.getNumber())) {
			criteria.add(Restrictions.eq("number", searchModel.getNumber()));
		}
		if (StringUtils.isNotBlank(searchModel.getAccountName())) {
			criteria.add(Restrictions.eq("accountName",
					searchModel.getAccountName()));
		}
		if (StringUtils.isNotBlank(searchModel.getProductName())) {
			criteria.add(Restrictions.eq("productName",
					searchModel.getProductName()));
		}
		
		StringUtil.calcTime("saleDate", searchModel.getStartDate(),
				searchModel.getEndDate(), criteria);
		
		criteria.addOrder(Order.asc(searchModel.getOrderBy()));
		int totalCount = saleRecordDao.count(criteria);
		criteria.setFirstResult((searchModel.getPageNo() - 1)
				* searchModel.getPageSize());
		criteria.setMaxResults(searchModel.getPageSize());
		Page<SaleRecord> page = new Page<SaleRecord>(totalCount,
				searchModel.getPageNo(), searchModel.getPageSize());
		page.setContents(criteria.list());
		return page;
	}
}
