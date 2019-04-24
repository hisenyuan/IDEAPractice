package com.hisen.file;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Created by hisenyuan on 2017/5/9 at 18:25.
 */
public class Html2Image {
  public static void main(String[] args) throws Exception {
    //load the webpage into the editor
    JEditorPane ed = new JEditorPane(new URL("http://hisen.me"));
    Thread.sleep(10000);
    ed.setSize(1000,1000);
    //create a new image
    BufferedImage image = new BufferedImage(ed.getWidth(), ed.getHeight(),
        BufferedImage.TYPE_INT_ARGB);

    //paint the editor onto the image
    SwingUtilities.paintComponent(image.createGraphics(),
        ed,
        new JPanel(),
        0, 0, image.getWidth(), image.getHeight());
    //save the image to file
    ImageIO.write((RenderedImage)image, "png", new File("html.png"));
    System.out.println("ok");

  }
}
