package com.hisen.jwt.service;

import com.hisen.jwt.entity.LoginReq;
import com.hisen.jwt.entity.LoginRes;

/**
 * @author : hisenyuan
 * @date : 2018/3/16 23:17
 * @descriptor :
 */
public interface LoginService {
  LoginRes login(LoginReq req);
}
