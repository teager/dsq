package com.dsq.dsq.api.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import coo.base.exception.BusinessException;
import com.dsq.dsq.core.service.SecurityService;
import coo.mvc.api.ApiUtils;

/**
 * API接口面板。
 */
@Service
public class ApiFacade {
  @Resource
  private SecurityService securityService;

  /**
   * 登录。
   * 
   * @param username 用户名
   * @param password 密码
   */
  public void login(String username, String password) {
    try {
      securityService.signIn(username, password, "255.255.255.255");
    } catch (BusinessException e) {
      ApiUtils.thrown("100", e.getMessage());
    }
  }
}
