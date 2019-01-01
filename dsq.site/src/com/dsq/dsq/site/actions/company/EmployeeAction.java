package com.dsq.dsq.site.actions.company;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dsq.dsq.core.entity.Employee;
import com.dsq.dsq.core.service.CompanyService;
import com.dsq.dsq.core.service.EmployeeService;

import coo.core.message.MessageSource;
import coo.core.model.SearchModel;
import coo.core.security.annotations.Auth;
import coo.mvc.dwz.DwzResultBuild;

/**
 * 职员管理。
 */
@Controller
@RequestMapping("/company")
@Auth("EMPLOYEE_MANAGE")
public class EmployeeAction {
  @Resource
  private EmployeeService employeeService;
  @Resource
  private CompanyService companyService;
  @Resource
  private MessageSource messageSource;

  /**
   * 查看职员列表。
   * 
   * @param model 数据模型
   * @param searchModel 搜索条件
   */
  @RequestMapping("employee-list")
  public void list(Model model, SearchModel searchModel) {
    model.addAttribute("employeePage", employeeService.search(searchModel));
  }

  /**
   * 新增职员。
   * 
   * @param model 数据模型
   */
  @RequestMapping("employee-add")
  public void add(Model model) {
    model.addAttribute(new Employee());
    model.addAttribute("companys", companyService.getAll());
  }

  /**
   * 保存职员。
   * 
   * @param employee 职员
   * 
   * @return 返回提示信息。
   */
  @RequestMapping("employee-save")
  public ModelAndView save(Employee employee) {
    employeeService.create(employee);
    return new DwzResultBuild().success("employee.add.success").closeDialog().reloadNavTab()
        .build();
  }

  /**
   * 编辑职员。
   * 
   * @param model 数据模型
   * @param employee 职员
   */
  @RequestMapping("employee-edit")
  public void edit(Model model, Employee employee) {
    model.addAttribute("companys", companyService.getAll());
  }

  /**
   * 更新职员。
   * 
   * @param employee 职员
   * 
   * @return 返回提示信息。
   */
  @RequestMapping("employee-update")
  public ModelAndView update(Employee employee) {
    employeeService.update(employee);
    return new DwzResultBuild().success("employee.edit.success").closeDialog().reloadNavTab()
        .build();
  }

  /**
   * 删除职员。
   * 
   * @param employee 职员
   * 
   * @return 返回提示信息。
   */
  @RequestMapping("employee-delete")
  public ModelAndView delete(Employee employee) {
    employeeService.delete(employee);
    return new DwzResultBuild().success("employee.delete.success").reloadNavTab().build();
  }
}
