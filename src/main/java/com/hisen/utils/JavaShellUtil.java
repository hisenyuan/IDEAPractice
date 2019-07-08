package com.hisen.utils;

import java.io.*;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2018/12/5 16:07
 */
public class JavaShellUtil {
    public static String execute(String command) {
        String returnString = "";
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();
        if (runTime == null) {
            System.err.println("Create runtime false!");
        }
        try {
            System.out.println("开始转换");
            pro = runTime.exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println("line: " + line);
                returnString = returnString + line + "\n";
            }
            System.out.println("返回值:" + returnString);
            input.close();
            output.close();
            pro.destroy();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return returnString;
    }
}
