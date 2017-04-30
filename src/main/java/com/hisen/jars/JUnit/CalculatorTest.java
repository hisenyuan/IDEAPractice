package com.hisen.jars.JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

  private static Calculator calculator = new Calculator();

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testAdd() {
    calculator.add(2);
    calculator.add(3);
    //预判，类似于断言
    assertEquals(5, calculator.getResult());
    fail("Not yet implemented");
  }

  @Test
  public void testSubstract() {
    fail("Not yet implemented");
  }

  @Test
  public void testMultiply() {
    fail("Not yet implemented");
  }

  @Test
  public void testDivide() {
    fail("Not yet implemented");
  }

}
