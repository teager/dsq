package com.dsq.dsq.api.actions.api;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsq.dsq.api.model.LoginQuery;
import com.dsq.dsq.api.model.LoginReply;
import com.dsq.dsq.api.service.ApiFacade;
import coo.mvc.api.ApiAction;

@Controller
@RequestMapping("/api/v1")
public class ApiV1Action extends ApiAction {
  @Resource
  private ApiFacade apiFacade;

  @RequestMapping("login")
  public LoginReply login(@Valid LoginQuery query) {
    apiFacade.login(query.getUsername(), query.getPassword());
    return new LoginReply();
  }
}
