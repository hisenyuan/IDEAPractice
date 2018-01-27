package com.hisen.interview.effective.no2buildpatter;

import com.hisen.interview.effective.no2buildpatter.NutritionFacts.Builder;

/**
 * @author : yhx
 * @date : 2018/1/27 23:01
 * @descriptor :
 */
public class TestBuilderPatter {

  public static void main(String[] args) {
    NutritionFacts cocaCola = new Builder(240, 8)
        .calories(100)
        .sodium(35)
        .calories(27)
        .carbohydrate(18)
        .build();
    System.out.println(cocaCola);
  }
}
