package com.hisen.jwt.filter;

import com.alibaba.fastjson.JSON;
import com.hisen.jwt.entity.CommonResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hisen on 17-8-27.
 * E-mail: hisenyuan@gmail.com
 */
public class JwtCheckInterceptor implements HandlerInterceptor {

  @Value("#{jwt['secret']}")
  private String secret;

  private Logger log = LoggerFactory.getLogger(JwtCheckInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object o) throws Exception {
    response.setCharacterEncoding("utf-8");
    //客户端将token封装在请求头中，格式为：Authorization：token
    final String token = request.getHeader("Authorization");
    CommonResponse commonResponse = new CommonResponse();
    if (token == null) {
      commonResponse.setResCode(9000);
      commonResponse.setResMsg("header 中无 Authorization");
      responseMessage(response,response.getWriter(),commonResponse);
      return false;
    }

    //解密token，拿到里面的对象claims
    Claims claims=null;
    try {
      claims = Jwts.parser().setSigningKey(secret)
          .parseClaimsJws(token).getBody();
    }catch (ExpiredJwtException e){
      commonResponse.setResCode(9001);
      commonResponse.setResMsg("超时");
      responseMessage(response,response.getWriter(),commonResponse);
      log.error(e.getMessage());
      return false;
    }catch (SignatureException e){
      commonResponse.setResCode(9002);
      commonResponse.setResMsg("非法");
      responseMessage(response,response.getWriter(),commonResponse);
      log.error(e.getMessage());
      return false;
    }
    //将对象传递给下一个请求(生成时候设置的值，都可以拿出来，再放到request里面)
    request.setAttribute("claims", claims);
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request,
      HttpServletResponse response, Object o, ModelAndView modelAndView)
      throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

  }


  private void responseMessage(HttpServletResponse response, PrintWriter out,
      CommonResponse commonResponse) {
    response.setContentType("application/json; charset=utf-8");
    String json = JSON.toJSONString(commonResponse);
    out.print(json);
    out.flush();
    out.close();
  }
}
