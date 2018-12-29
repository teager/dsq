package com.dsq.dsq.main.actions;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsq.dsq.core.service.SecurityService;
import coo.core.security.annotations.Auth;

/**
 * 主页。
 */
@Controller
@RequestMapping("/")
@Auth
public class IndexAction {
  @Resource
  private SecurityService securityService;

  /**
   * 查看主页。
   * 
   * @param model 数据模型
   */
  @RequestMapping("index")
  public void index(Model model) {
    model.addAttribute("currentUser", securityService.getCurrentUser());
  }
}
