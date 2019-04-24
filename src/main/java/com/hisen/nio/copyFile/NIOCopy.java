package com.hisen.nio.copyFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

import org.junit.Test;

/**
 * Created by hisenyuan on 2017/6/1 at 11:28.
 */
public class NIOCopy {

    @Test
    public void testCopy() {
        String oldFileName = "/Users/hisenyuan/hisen/blog/source/_posts/Test-Java-Code.md";
        String newFileName = "/Users/hisenyuan/hisen/test/Test-Java-Code.md";
        nioCopy(oldFileName, newFileName);
        ioCopy(oldFileName, newFileName.replace(".md", ".md.bak"));
        ioCopyByLine(oldFileName,newFileName.replace(".md",".bak." + System.currentTimeMillis()));
    }

    /**
     * 利用NIO进行读写文件
     *
     * @param oldFileName 原文件的路径
     * @param newFileName 新文件的路径
     */
    public static void nioCopy(String oldFileName, String newFileName) {
        try {
            FileChannel fileChannelIn = new FileInputStream(new File(oldFileName)).getChannel();
            FileChannel fileChannelOut = new FileOutputStream(new File(newFileName)).getChannel();
            //获取文件大小
            long size = fileChannelIn.size();
            System.out.printf("文件大小为：%s byte \n", size);
            //缓冲
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            long start = System.currentTimeMillis();
            while (fileChannelIn.read(byteBuffer) != -1) {
                //准备写
                byteBuffer.flip();
                fileChannelOut.write(byteBuffer);
                //准备读
                byteBuffer.clear();
            }
            long end = System.currentTimeMillis();
            System.out.printf("NIO方式复制完成，耗时 %s 秒\n", (end - start) / 1000);
            //关闭
            fileChannelIn.close();
            fileChannelOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * IO方式复制文件
     *
     * @param oldFileName
     * @param newFileName
     */
    public static void ioCopy(String oldFileName, String newFileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(oldFileName));
            FileOutputStream fileOutputStream = new FileOutputStream(new File(newFileName));

            long length = new File(oldFileName).length();

            System.out.printf("文件大小为：%s byte \n", length);
            byte[] buffer = new byte[1024];

            long start = System.currentTimeMillis();
            int len = 0;
            //EOF = End Of File，其默认值就是-1
            while ((len = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            long end = System.currentTimeMillis();
            System.out.printf("IO方式复制完成，耗时 %s 秒\n", (end - start) / 1000);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ioCopyByLine(String oldFileName, String newFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(oldFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("error:" + e);
        }
    }
}
