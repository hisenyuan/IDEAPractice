package com.hisen.jwt.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author : hisenyuan
 * @date : 2018/3/16 23:24
 * @descriptor :
 */
@ComponentScan
public class JwtFilter extends GenericFilterBean {
  @Value("#{jwt['secret']}")
  private String secret;

  @Override
  public void doFilter(ServletRequest req, ServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest request = (HttpServletRequest) req;

    //客户端将token封装在请求头中，格式为：Authorization：token
    final String token = request.getHeader("Authorization");
    if (token == null) {
      throw new ServletException("Missing or invalid Authorization header.");
    }
    //解密token，拿到里面的对象claims
    final Claims claims = Jwts.parser().setSigningKey(secret)
        .parseClaimsJws(token).getBody();
    //将对象传递给下一个请求(生成时候设置的值，都可以拿出来，再放到request里面)
    request.setAttribute("claims", claims);
    //Object hisenK = claims.get("hisenK");
    //System.out.println(hisenK);//输出：hisenV
    chain.doFilter(req, res);
  }
}
