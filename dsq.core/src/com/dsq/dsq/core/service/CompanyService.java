package com.dsq.dsq.core.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.search.SortField;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsq.dsq.core.entity.Company;
import com.dsq.dsq.core.model.CompanyExtendInfo;

import coo.base.util.BeanUtils;
import coo.base.util.DateUtils;
import coo.core.hibernate.dao.Dao;
import coo.core.message.MessageSource;
import coo.core.security.annotations.DetailLog;
import coo.core.security.annotations.DetailLog.LogType;
import coo.core.security.annotations.SimpleLog;

/**
 * 公司管理。
 */
@Service
public class CompanyService {
  @Resource
  private Dao<Company> companyDao;
  @Resource
  private MessageSource messageSource;

  @Transactional(readOnly = true)
  public List<Company> getAll() {
    return companyDao.searchAll("name", true, SortField.Type.STRING);
  }

  @Transactional(readOnly = true)
  public Company get(String companyId) {
    return companyDao.get(companyId);
  }

  @Transactional
  @DetailLog(target = "company", code = "company.add.log", vars = "company.name",
      type = LogType.NEW)
  public void create(Company company) {
    if (!companyDao.isUnique(company, "name")) {
      messageSource.thrown("company.name.exist");
    }
    companyDao.save(company);
  }

  @Transactional
  @DetailLog(target = "company", code = "company.edit.log", vars = "company.name",
      type = LogType.ALL)
  public void update(Company company) {
    if (!companyDao.isUnique(company, "name")) {
      messageSource.thrown("company.name.exist");
    }
    Company origCompany = companyDao.get(company.getId());
    BeanUtils.copyFields(company, origCompany);
  }

  @Transactional
  @SimpleLog(entityId = "company.id", code = "company.delete.log", vars="company.name")
  public void delete(Company company) {
    companyDao.remove(company);
  }

  /**
   * 自动创建公司，用于定时任务。
   */
  @Transactional
  public Company autoCreate() {
    Company company = new Company();
    company.setName("c" + DateUtils.format(new Date(), DateUtils.SECOND_N));
    company.setFoundDate(new Date());
    CompanyExtendInfo extendInfo = new CompanyExtendInfo();
    extendInfo.setTel("12345678");
    company.setExtendInfo(extendInfo);
    create(company);
    return company;
  }
}
