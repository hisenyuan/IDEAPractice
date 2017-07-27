package com.hisen.jars.guava;

import com.google.common.collect.ImmutableSet;
import java.awt.Color;

/**
 * Created by hisenyuan on 2017/7/27 at 14:10.
 */
public class CollectionsTest {

  public static void main(String[] args) {

  }

  public static final ImmutableSet<Color> GOOGLE_COLORS = ImmutableSet.<Color>builder()
      .add(new Color(0,191,255)).build();

}
