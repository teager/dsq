package com.dsq.dsq.site.actions.company;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dsq.dsq.core.entity.SaleRecord;
import com.dsq.dsq.core.model.SaleRecordSearchModel;
import com.dsq.dsq.core.service.SaleRecordService;

import coo.base.util.DateUtils;
import coo.core.message.MessageSource;
import coo.core.security.annotations.Auth;
import coo.mvc.dwz.DwzResultBuild;

/**
 * 销售记录管理。
 */
@Controller
@RequestMapping("/company")
@Auth({"SALES_MANAGE_WRITE","SALES_MANAGE_READ"})
public class SaleRecordAction {
	@Resource
	private SaleRecordService saleRecordService;
	@Resource
	private MessageSource messageSource;

	/**
	 * 查看销售记录列表。
	 * 
	 * @param model 数据模型
	 */
	@RequestMapping("saleRecord-list")
	public void list(Model model, SaleRecordSearchModel searchModel) {
		searchModel.setOrderBy("createDate");
		if (searchModel.getStartDate() == null && searchModel.getEndDate() == null) {
			searchModel.setStartDate(DateUtils.getPrevDay(30));
			searchModel.setEndDate(DateUtils.getToday());
		}
		model.addAttribute("searchModel", searchModel);

		model.addAttribute("saleRecordPages", saleRecordService.searchSaleRecord(searchModel));
	}

	/**
	 * 新增销售记录。
	 * 
	 * @param model 数据模型
	 */
	@RequestMapping("saleRecord-add")
	public void add(Model model) {
		model.addAttribute(new SaleRecord());
	}

	/**
	 * 保存销售记录。
	 * 
	 * @param saleRecord 销售记录
	 * @return 返回提示信息。
	 */
	@RequestMapping("saleRecord-save")
	public ModelAndView save(SaleRecord saleRecord) {
		saleRecordService.create(saleRecord);
		return new DwzResultBuild().success("saleRecord.add.success").closeDialog().reloadNavTab().build();
	}

	/**
	 * 编辑销售记录。
	 * 
	 * @param model      数据模型
	 * @param saleRecord 销售记录
	 */
	@RequestMapping("saleRecord-edit")
	public void edit(Model model, SaleRecord saleRecord) {
		model.addAttribute(saleRecord);
	}

	/**
	 * 更新销售记录。
	 * 
	 * @param saleRecord 销售记录
	 * @return 返回提示信息。
	 */
	@RequestMapping("saleRecord-update")
	public ModelAndView update(SaleRecord saleRecord) {
		saleRecordService.update(saleRecord);
		return new DwzResultBuild().success("saleRecord.edit.success").closeDialog().reloadNavTab().build();
	}

	/**
	 * 删除销售记录。
	 * 
	 * @param saleRecord 销售记录
	 * @return 返回提示信息。
	 */
	@RequestMapping("saleRecord-delete")
	public ModelAndView delete(SaleRecord saleRecord) {
		saleRecordService.delete(saleRecord);
		return new DwzResultBuild().success("saleRecord.delete.success").reloadNavTab().build();
	}
}
