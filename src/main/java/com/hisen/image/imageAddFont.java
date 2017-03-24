package com.hisen.image;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 给图片加上文字
 * Created by hisenyuan on 2017/3/24 at 18:54.
 */
public class imageAddFont {
    public static void main(String[] args) {
        imageAddFont("hisenyuan","c:/1/830.jpg");
    }

    /**
     * 给图片加上文字
     * @param font 你要添加的字
     * @param imgPath 将要加工图片的路径
     */
    public static void imageAddFont(String font,String imgPath){
        try {

            InputStream is = new FileInputStream(imgPath);//图片
            JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);//通过JPEG图象流创建JPEG数据流解码器
            BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();//解码当前JPEG数据流，返回BufferedImage对象
            Graphics g = buffImg.getGraphics();//得到画笔对象
            g.setColor(Color.BLACK);//设置颜色。
            Font f = new Font("宋体", Font.PLAIN,25);
            g.setColor(Color.blue);//或者括号写：new Color(0, 0, 255)
            g.setFont(f);
            g.drawString(font,10,900);//10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
            g.dispose();
            String shareFileName = "\\c:\\1\\" + System.currentTimeMillis() + ".jpg";//加工后的图片输出路径
            OutputStream os = new FileOutputStream(shareFileName);
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os); //创键编码器，用于编码内存中的图象数据。
            en.encode(buffImg);
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ImageFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
