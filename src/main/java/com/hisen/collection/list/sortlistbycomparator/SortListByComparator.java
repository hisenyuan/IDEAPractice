package com.hisen.collection.list.sortlistbycomparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.junit.Test;

/**
 * @author : yhx
 * @date : 2017/11/26 20:50
 * @descriptor : 使用Comparator对List排序
 */
public class SortListByComparator {

  @Test
  public void listComparatorTest() {
    ArrayList<Student> students = new ArrayList<>();
    Student student = null;
    student = new Student();
    student.setName("winnie");
    student.setAge(22);
    student.setAddress("guangxi");
    student.setScore(59);
    students.add(student);

    student = new Student();
    student.setName("hisen");
    student.setAge(25);
    student.setAddress("jiangxi");
    student.setScore(99);
    students.add(student);

    student = new Student();
    student.setName("hisenyuan");
    student.setAge(33);
    student.setAddress("beijing");
    student.setScore(88);
    students.add(student);

    Collections.sort(students, new myComparator());
    // java8的写法
//    students.sort(Student::compareByNameThenAge);
    for (Student s : students) {
      System.out.println(s);
    }
    /**
     * Student{name='hisen', age=25, address='jiangxi', score=99}
     * Student{name='hisenyuan', age=33, address='beijing', score=88}
     * Student{name='winnie', age=22, address='guangxi', score=59}
     */
  }

  class myComparator implements Comparator<Student> {

    /**
     * 重写compare方法
     * @param s1 list中的对象
     * @param s2 list中的对象
     * @return
     */
    @Override
    public int compare(Student s1, Student s2) {
      if (s1.getScore() < s2.getScore()) {
        return 1;
      } else {
        return -1;
      }
    }
  }
}
