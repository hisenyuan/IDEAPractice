package com.hisen.jwt.service.impl;

import com.hisen.jwt.entity.LoginReq;
import com.hisen.jwt.entity.LoginRes;
import com.hisen.jwt.service.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author : hisenyuan
 * @date : 2018/3/16 23:17
 * @descriptor :
 */
@Service
public class LoginServiceImpl implements LoginService {
  @Value("#{jwt['secret']}")
  private String secret;
  @Value("#{jwt['userName']}")
  private String userName;
  @Value("#{jwt['pwd']}")
  private String pwd;
  @Value("#{jwt['isLogin']}")
  private String isLogin;

  @Override
  public LoginRes login(LoginReq req) {
    if (userName.equals(req.getUserName()) && pwd.equals(req.getPwd())){
      //  验证通过，生成token
      String jwt = Jwts.builder()
          // JWT 所面向的用户
          .setSubject("hisen")
          // 设置密钥和加密算法
          .signWith(SignatureAlgorithm.HS512, secret)
          // 设置签发时间
          .setIssuedAt(new DateTime().toDate())
          // 设置生效时间
          .setNotBefore(new DateTime(System.currentTimeMillis()).toDate())
          // 设置过期时间
          .setExpiration(new DateTime(System.currentTimeMillis() + 60000).toDate())
              // 存放各种业务数据 KV形式，value存任何信息(eg.对象转json存,请勿存放敏感信息),可以存放多个
              .claim("hisen", isLogin)
              .compact();
      LoginRes res = new LoginRes();
      res.setJwt(jwt);
      res.setUserName(userName);
      return res;
    }
    return null;
  }
}
