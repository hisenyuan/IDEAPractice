package com.hisen.test;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2018/10/25 16:05
 */
public class StreamForCompareTest {
    static class Student {
        private int id;
        private Short gender;
        private String name;
        private String addr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Short getGender() {
            return gender;
        }

        public void setGender(Short gender) {
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            Student stu = new Student();
            stu.setId(i);
            stu.setName("hisen" + i);
            stu.setGender((short) (i % 1000));
            stu.setAddr("addr" + i);
            students.add(stu);
        }

        long forStart = System.nanoTime();

        List<Student> studentList = new ArrayList<>();
        for (Student stu : students) {
            if (stu.getId() % 5 == 0) {
                studentList.add(stu);
            }
        }
        for (Student stu : studentList) {
            if (stu.getGender() > 0) {
                System.out.println(stu);
            }
        }
        long forEnd = System.nanoTime();

        long streamStart = System.nanoTime();
        students.stream()
                .filter(e -> e.getId() % 5 == 0 && e.getGender() > 0)
                .forEach(System.out::println);
        long streamEnd = System.nanoTime();

        System.out.println("for:" + (forEnd - forStart) + ",stream:" + (streamEnd - streamStart) + ",result:" + ((forEnd - forStart) - (streamEnd - streamStart)));
    }
}
