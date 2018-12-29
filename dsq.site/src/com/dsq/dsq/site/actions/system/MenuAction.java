package com.dsq.dsq.site.actions.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;

/**
 * 系统管理菜单。
 */
@Controller("system.menu")
@RequestMapping("/system")
@Auth(AdminPermission.CODE)
public class MenuAction {
  @RequestMapping("menu")
  public void menu() {}
}
