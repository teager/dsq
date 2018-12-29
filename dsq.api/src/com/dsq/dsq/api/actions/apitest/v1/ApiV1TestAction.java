package com.dsq.dsq.api.actions.apitest.v1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsq.dsq.api.model.LoginQuery;

@Controller
@RequestMapping("/apitest/v1")
public class ApiV1TestAction {
  @RequestMapping("login")
  public void login(Model model) {
    model.addAttribute(new LoginQuery());
  }
}
