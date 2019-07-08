package com.hisen.string;

import com.hisen.utils.Base64Util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;

/**
 * Created by hisenyuan on 2017/4/18 at 10:45.
 */
public class String2Base64 {

  public static void main(String[] args) throws UnsupportedEncodingException {
    String str = "hisen";
    //commons-codec加密
    String encode = Base64Util.encodeBase64(str.getBytes());
    System.out.println(str + " ---> " + encode);
    //commons-codec解密
    byte[] bytes = Base64Util.decodeBase64(encode);
    System.out.println(encode + " ---> " + new String(bytes, "UTF-8"));
    System.out.println("------------------------------");
    //sun.misc加密
    byte[] encode1 = Base64Util.encode(str.getBytes());
    System.out.println(str + " ---> " + new String(encode1, "UTF-8"));
    //sun.misc解密
    byte[] decode = Base64Util.decode(encode1);
    System.out.println(new String(encode1, "UTF-8") + " ---> " + new String(decode, "UTF-8"));
    str2File(new String(encode1, "UTF-8") + " ---> " + new String(decode, "UTF-8"));


  }

  public static void str2File(String str) {
    String path = "src/main/java/com/hisen/string/text/str2File1.txt";
    File file = new File(path);
    if (!file.exists()) {
      File parent = file.getParentFile();
      if (parent != null && !parent.exists()) {
        parent.mkdirs();
        System.out.println("创建父目录成功！");
      }
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    FileWriterWithEncoding fw = null;
    BufferedWriter bw = null;
    try {
      fw = new FileWriterWithEncoding(file.getAbsoluteFile(), "UTF-8", true);
      bw = new BufferedWriter(fw);
      bw.write(str);
      bw.newLine();
      bw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(fw,bw);
    }

  }
}
