package com.hisen.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by hisenyuan on 2017/7/13 at 23:00.
 *
 * 当classpath里有dbcTemplate，加了这个注解的类才会被创建为bean
 * 如下所示
 */
//@Conditional(JdbcTemplateCondition.class)
public class JdbcTemplateCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context,
      AnnotatedTypeMetadata metadata) {
    try {
      context.getClassLoader().loadClass("org.springframework.jdbc.core.JdbcTemplate");
      return true;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return false;
  }
}
