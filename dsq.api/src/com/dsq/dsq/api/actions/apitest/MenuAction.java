package com.dsq.dsq.api.actions.apitest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;

@Controller("apitest.menu")
@RequestMapping("/apitest")
@Auth(AdminPermission.CODE)
public class MenuAction {
  @RequestMapping("menu")
  public void menu() {}
}
