package com.hisen.javaGaiShu.test2;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;

@SuppressWarnings("serial")
public class AddButton extends Applet {

  Button button;

  public void init() {
    setBackground(Color.lightGray);
    button = new Button("我是按钮");
    add(button);
  }
}
