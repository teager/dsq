package com.dsq.dsq.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsq.dsq.core.entity.Medicine;
import com.dsq.dsq.core.model.MedicineSearchModel;
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
public class MedicineService {
  @Resource
  private Dao<Medicine> medicineDao;
  @Resource
  private MessageSource messageSource;

  @Transactional(readOnly = true)
  public List<Medicine> getAll() {
    return medicineDao.searchAll("name", true, SortField.Type.STRING);
  }

  @Transactional(readOnly = true)
  public Medicine get(String medicineId) {
    return medicineDao.get(medicineId);
  }

  @Transactional
  @AutoFillIn
  @DetailLog(target = "medicine", code = "medicine.add.log", vars = "medicine.number",
      type = LogType.NEW)
  public void create(Medicine medicine) {
    if (!medicineDao.isUnique(medicine, "number")) {
      messageSource.thrown("medicine.number.exist");
    }
    medicineDao.save(medicine);
  }

  @Transactional
  @AutoFillIn
  @DetailLog(target = "medicine", code = "medicine.edit.log", vars = "medicine.number",
      type = LogType.ALL)
  public void update(Medicine medicine) {
    if (!medicineDao.isUnique(medicine, "number")) {
      messageSource.thrown("medicine.number.exist");
    }
    Medicine origCompany = medicineDao.get(medicine.getId());
    BeanUtils.copyFields(medicine, origCompany);
  }

  @Transactional
  @SimpleLog(entityId = "medicine.id", code = "medicine.delete.log", vars="medicine.number")
  public void delete(Medicine medicine) {
    medicineDao.remove(medicine);
  }

  /**
	 * 分页搜索药品库存。
	 * 
	 * @param searchModel
	 *            搜索条件
	 * 
	 * @return 返回符合条件的药品库存分页对象。
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Medicine> searchMedicine(
			MedicineSearchModel searchModel) {
	
		Criteria criteria = medicineDao.createCriteria();
		if (StringUtils.isNotBlank(searchModel.getNumber())) {
			criteria.add(Restrictions.eq("number", searchModel.getNumber()));
		}
		if (StringUtils.isNotBlank(searchModel.getName())) {
			criteria.add(Restrictions.eq("name",
					searchModel.getName()));
		}

		StringUtil.calcTime("produceDate", searchModel.getStartDate(),
				searchModel.getEndDate(), criteria);
		
		criteria.addOrder(Order.asc(searchModel.getOrderBy()));
		int totalCount = medicineDao.count(criteria);
		criteria.setFirstResult((searchModel.getPageNo() - 1)
				* searchModel.getPageSize());
		criteria.setMaxResults(searchModel.getPageSize());
		Page<Medicine> page = new Page<Medicine>(totalCount,
				searchModel.getPageNo(), searchModel.getPageSize());
		page.setContents(criteria.list());
		return page;
	}
}
