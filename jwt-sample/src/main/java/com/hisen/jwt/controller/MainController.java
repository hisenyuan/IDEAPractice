package com.hisen.jwt.controller;

import com.alibaba.fastjson.JSON;
import com.hisen.jwt.entity.LoginReq;
import com.hisen.jwt.entity.LoginRes;
import com.hisen.jwt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : hisenyuan
 * @date : 2018/3/16 23:02
 * @descriptor :
 */
@Controller
@RequestMapping("/hisen")
public class MainController {
  @Autowired
  LoginService loginService;


  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String login(LoginReq req){
    LoginRes login = loginService.login(req);
    return JSON.toJSONString(login);
  }
  @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String test(LoginReq req){

    return "hisenyuan";
  }
}
