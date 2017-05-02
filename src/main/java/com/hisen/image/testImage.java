package com.hisen.image;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by hisenyuan on 2017/3/24 at 18:19.
 */
public class testImage {
    public static void main(String[] args) {
        imageAddFont("~","c:/1/830.jpg");
    }
    public static void exportImg1(){
        int width = 100;
        int height = 100;
        String s = "hisen";
        File file = new File("c:/1/image.png");
        Font font = new Font("Serif", Font.BOLD, 10);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setBackground(Color.WHITE);
        g2.clearRect(0,0,width,height);
        g2.setPaint(Color.RED);

        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(s, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;
        g2.drawString(s, (int)x, (int)baseY);

        try {
            ImageIO.write(bi, "jpg", file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void exportImg2(String username,String headImg){
        try {
            //1.jpg是你的 主图片的路径
            InputStream is = new FileInputStream("c:/1/830.jpg");
            //通过JPEG图象流创建JPEG数据流解码器
            JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
            //解码当前JPEG数据流，返回BufferedImage对象
            BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
            //得到画笔对象
            Graphics g = buffImg.getGraphics();
            /**
             * 去掉图片水印
            //创建你要附加的图象。
            //小图片的路径
            ImageIcon imgIcon = new ImageIcon(headImg);

            //得到Image对象。
            Image img = imgIcon.getImage();

            //将小图片绘到大图片上。
            //5,300 .表示你的小图片在大图片上的位置。
            g.drawImage(img,400,15,null);
            */
            //设置颜色。
            g.setColor(Color.BLACK);


            //最后一个参数用来设置字体的大小
            Font f = new Font("宋体",Font.PLAIN,25);
            Color mycolor = Color.red;//new Color(0, 0, 255);
            g.setColor(mycolor);
            g.setFont(f);

            //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
            g.drawString(username,100,135);
            g.dispose();
            OutputStream os;

            //os = new FileOutputStream("d:/union.jpg");
            String shareFileName = "\\c:\\1\\" + System.currentTimeMillis() + ".jpg";
            os = new FileOutputStream(shareFileName);
            //创键编码器，用于编码内存中的图象数据。
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
            en.encode(buffImg);

            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ImageFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void imageAddFont(String font,String imgPath){
        try {
            //1.jpg是你的 主图片的路径
            InputStream is = new FileInputStream(imgPath);
            //通过JPEG图象流创建JPEG数据流解码器
            JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
            //解码当前JPEG数据流，返回BufferedImage对象
            BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
            //得到画笔对象
            Graphics g = buffImg.getGraphics();
            //设置颜色。
            g.setColor(Color.BLACK);
            //最后一个参数用来设置字体的大小
            Font f = new Font("宋体",Font.PLAIN,75);
            Color mycolor = Color.blue;//new Color(0, 0, 255);
            g.setColor(mycolor);
            g.setFont(f);
            //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
            g.drawString(font,100,135);
            g.dispose();
            OutputStream os;
            //加工后的图片输出路径
            String shareFileName = "\\c:\\1\\" + System.currentTimeMillis() + ".jpg";
            os = new FileOutputStream(shareFileName);
            //创键编码器，用于编码内存中的图象数据。
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
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
