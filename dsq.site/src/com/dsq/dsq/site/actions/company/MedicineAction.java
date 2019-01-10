package com.dsq.dsq.site.actions.company;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dsq.dsq.core.entity.Medicine;
import com.dsq.dsq.core.model.MedicineSearchModel;
import com.dsq.dsq.core.service.MedicineService;

import coo.base.util.DateUtils;
import coo.core.message.MessageSource;
import coo.core.security.annotations.Auth;
import coo.mvc.dwz.DwzResultBuild;

/**
 * 库存管理。
 */
@Controller
@RequestMapping("/company")
@Auth({"MEDICINE_MANAGE_WRITE","MEDICINE_MANAGE_READ"})
public class MedicineAction {
	@Resource
	private MedicineService medicineService;
	@Resource
	private MessageSource messageSource;

	/**
	 * 查看库存列表。
	 * 
	 * @param model 数据模型
	 */
	@RequestMapping("medicine-list")
	public void list(Model model, MedicineSearchModel searchModel) {
		searchModel.setOrderBy("createDate");
		if (searchModel.getStartDate() == null && searchModel.getEndDate() == null) {
			searchModel.setStartDate(DateUtils.getPrevDay(30));
			searchModel.setEndDate(DateUtils.getToday());
		}
		model.addAttribute("searchModel", searchModel);
		model.addAttribute("medicinePages", medicineService.searchMedicine(searchModel));
	}

	/**
	 * 新增库存。
	 * 
	 * @param model 数据模型
	 */
	@RequestMapping("medicine-add")
	public void add(Model model) {
		model.addAttribute(new Medicine());
	}

	/**
	 * 保存库存。
	 * 
	 * @param medicine 库存
	 * @return 返回提示信息。
	 */
	@RequestMapping("medicine-save")
	public ModelAndView save(Medicine medicine) {
		medicineService.create(medicine);
		return new DwzResultBuild().success("medicine.add.success").closeDialog().reloadNavTab().build();
	}

	/**
	 * 编辑库存。
	 * 
	 * @param model    数据模型
	 * @param medicine 库存
	 */
	@RequestMapping("medicine-edit")
	public void edit(Model model, Medicine medicine) {
		model.addAttribute(medicine);
	}

	/**
	 * 更新库存。
	 * 
	 * @param medicine 库存
	 * @return 返回提示信息。
	 */
	@RequestMapping("medicine-update")
	public ModelAndView update(Medicine medicine) {
		medicineService.update(medicine);
		return new DwzResultBuild().success("medicine.edit.success").closeDialog().reloadNavTab().build();
	}

	/**
	 * 删除库存。
	 * 
	 * @param medicine 库存
	 * @return 返回提示信息。
	 */
	@RequestMapping("medicine-delete")
	public ModelAndView delete(Medicine medicine) {
		medicineService.delete(medicine);
		return new DwzResultBuild().success("medicine.delete.success").reloadNavTab().build();
	}
}
