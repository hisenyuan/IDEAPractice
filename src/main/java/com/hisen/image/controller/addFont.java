/*
package com.hisen.image.controller;

import com.hisen.image.imageAddFont;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;



public class addFont extends HttpServlet {
    imageAddFont addFont = new imageAddFont();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        string c = request.getParameter("a");
        System.out.println(c);
        System.out.println("==========================");
        if("yes".equals(c))
            out.write("Welcome!");
        else
            out.print("I don't like you!");
        //获取浏览器信息
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        Version browserVersion = userAgent.getBrowserVersion();
        System.out.println("浏览器"+browser+" 版本"+browserVersion);
        OperatingSystem os = userAgent.getOperatingSystem();

        string ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("ip   "+ip);
        string s = " 系统:"+os+" IP:"+ip+"\n浏览器:"+browser+" 版本:"+browserVersion;
        System.out.println(s);
        addFont.imageAddFont(s,"c:/1/830.jpg");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
 */