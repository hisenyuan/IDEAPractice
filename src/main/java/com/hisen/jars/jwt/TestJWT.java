package com.hisen.jars.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.UnsupportedEncodingException;

/**
 * Created by hisenyuan on 2017/8/17 at 16:13.
 */
public class TestJWT {

  public static void main(String[] args)
      throws UnsupportedEncodingException, JsonProcessingException {
    String hisen = Jwt.sign("hisen", 60L * 1000L * 30L);
    System.out.println(hisen);
    String unsign = Jwt.unsign(hisen, String.class);
    System.out.println(unsign);
  }
}
