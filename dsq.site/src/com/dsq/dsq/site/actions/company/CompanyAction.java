package com.dsq.dsq.site.actions.company;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dsq.dsq.core.entity.Company;
import com.dsq.dsq.core.service.CompanyService;

import coo.core.message.MessageSource;
import coo.core.security.annotations.Auth;
import coo.mvc.dwz.DwzResultBuild;

/**
 * 公司管理。
 */
@Controller
@RequestMapping("/company")
@Auth("COMPANY_MANAGE")
public class CompanyAction {
  @Resource
  private CompanyService companyService;
  @Resource
  private MessageSource messageSource;

  /**
   * 查看公司列表。
   * 
   * @param model 数据模型
   */
  @RequestMapping("company-list")
  public void list(Model model) {
    model.addAttribute("companys", companyService.getAll());
  }

  /**
   * 新增公司。
   * 
   * @param model 数据模型
   */
  @RequestMapping("company-add")
  public void add(Model model) {
    model.addAttribute(new Company());
  }

  /**
   * 保存公司。
   * 
   * @param company 公司
   * @return 返回提示信息。
   */
  @RequestMapping("company-save")
  public ModelAndView save(Company company) {
    companyService.create(company);
    return new DwzResultBuild().success("company.add.success").closeDialog().reloadNavTab().build();
  }

  /**
   * 编辑公司。
   * 
   * @param model 数据模型
   * @param company 公司
   */
  @RequestMapping("company-edit")
  public void edit(Model model, Company company) {
    model.addAttribute(company);
  }

  /**
   * 更新公司。
   * 
   * @param company 公司
   * @return 返回提示信息。
   */
  @RequestMapping("company-update")
  public ModelAndView update(Company company) {
    companyService.update(company);
    return new DwzResultBuild().success("company.edit.success").closeDialog().reloadNavTab()
        .build();
  }

  /**
   * 删除公司。
   * 
   * @param company 公司
   * @return 返回提示信息。
   */
  @RequestMapping("company-delete")
  public ModelAndView delete(Company company) {
    companyService.delete(company);
    return new DwzResultBuild().success("company.delete.success").reloadNavTab().build();
  }
}
